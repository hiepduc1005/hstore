package com.hstore.vn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hstore.vn.dao.PrivilegeDao;
import com.hstore.vn.dao.PurchaseStatusDao;
import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Privilege;
import com.hstore.vn.entity.PurchaseStatus;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;



@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	public static final String ROLE_CUSTOMER ="ROLE_CUSTOMER";
	public static final String ROLE_MANAGER ="ROLE_MANAGER";
	public static final String ROLE_ADMIN ="ROLE_ADMIN";
	public static final String READ_PRIVILEGE = "READ_PRIVILEGE";
	public static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
	public static final String DELETE_PRIVILEGE = "DELETE_PRIVILEGE";
	
	public static final String RECEIVE_REQUEST = "RECEIVE_REQUEST";
	public static final String WAITING_FOR_PAYMENT = "WAITING_FOR_PAYMENT";
	public static final String PAYED = "PAYED";
	public static final String SHIPPING = "SHIPPING";
	public static final String SHIPPED = "SHIPPED";
	public static final String COMPLETED = "COMPLETED";

	
	
	
	public boolean isConfigured;
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public PrivilegeDao privilegeDao;
	
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public PurchaseStatusDao orderStatusDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(isConfigured) {
			return;
		}
		
		Privilege readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE);
		Privilege writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE);
		Privilege deletePrivilege = createPrivilegeIfNotFound(DELETE_PRIVILEGE);
		
		Role roleCustomer =  createRoleIfNotFound(ROLE_CUSTOMER,Arrays.asList(readPrivilege));
		Role roleManager = createRoleIfNotFound(ROLE_MANAGER,Arrays.asList(readPrivilege,writePrivilege));
		Role roleAdmin  = createRoleIfNotFound(ROLE_ADMIN,Arrays.asList(readPrivilege,writePrivilege,deletePrivilege));
		
		createOrderStatusIfNotFound(RECEIVE_REQUEST);
		createOrderStatusIfNotFound(SHIPPING);
		createOrderStatusIfNotFound(SHIPPED);
        createOrderStatusIfNotFound(WAITING_FOR_PAYMENT);
        createOrderStatusIfNotFound(PAYED);
        createOrderStatusIfNotFound(COMPLETED);
       
       
       
        
        createUserIfNotFound(roleAdmin,"admin@gmail.com","admin");
        createUserIfNotFound(roleManager,"manager@gmail.com" ,"manager");
        createUserIfNotFound(roleCustomer,"test@gmail.com", "test");
//        
        
		

		isConfigured = true;
	}
	
	
	private void createUserIfNotFound(Role roleDto , String email , String password) {
		User userDto = userDao.getUserByEmail(email);
		passwordEncoder = new BCryptPasswordEncoder();
		if(userDto == null) {
			userDto = new User();
			userDto.setEmail(email);
			userDto.setFirstName("Admin");
			userDto.setLastName("Admin");
			userDto.setPassword(passwordEncoder.encode(password));

			userDto.setRoles(Arrays.asList(roleDto));

			userDto.setMoney(BigDecimal.valueOf(0));
			userDao.saveUser(userDto);
		}
		
	}
	
	
	private Role createRoleIfNotFound(String roleName , List<Privilege> privilegeDtos) {
		
		Role roleDto = roleDao.getRoleByName(roleName);
		if(roleDto == null ) {
			roleDto = new Role();
			roleDto.setName(roleName);

			roleDto.setPrivileges(privilegeDtos);

			roleDao.save(roleDto);
		}
		return roleDto;
		
	}
	
	private Privilege createPrivilegeIfNotFound(String name ) {
		Privilege privilegeDto = privilegeDao.getPrivilegeByName(name);
		if(privilegeDto == null) {
			privilegeDto = new Privilege();
			privilegeDto.setName(name);
			privilegeDao.save(privilegeDto);
		}
		return privilegeDto;
	}
	
	private void createOrderStatusIfNotFound(String statusName) {
		PurchaseStatus orderStatusDto = orderStatusDao.getPurchaseStatusByName(statusName);
		if(orderStatusDto == null) {
			orderStatusDto = new PurchaseStatus();
			orderStatusDto.setStatusName(statusName);
			orderStatusDao.savePurchaseStatus(orderStatusDto);
		}
	}
	
	

}
