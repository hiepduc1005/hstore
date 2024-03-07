package com.hstore.vn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hstore.vn.SetupDataLoader;
import com.hstore.vn.dao.RoleDao;
import com.hstore.vn.dao.UserDao;
import com.hstore.vn.entity.Role;
import com.hstore.vn.entity.User;
import com.hstore.vn.exception.user.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaUserDao implements UserDao {
	
	@Autowired
	public EntityManager em;
	
	@Autowired
	public RoleDao roleDao;

	@Transactional
	@Override
	public User getUserById(Integer id) {
		
	User userDto = em.find(User.class, id);
		
		return userDto;
	}

	@Transactional
	@Override
	public User saveUser(User user) {
		User userRes = em.merge(user);
		user.addRole(roleDao.getRoleByName(SetupDataLoader.ROLE_CUSTOMER));
		em.flush();
		return userRes;
	}

	@Transactional
	@Override
	@Modifying
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		em.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public User getUserByEmail(String email) {
		System.out.println(email);
		
		Query query = em.createNativeQuery("SELECT * FROM user WHERE email = :email" , User.class);
		
//	    TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.email = :email",User.class);
		
		query.setParameter("email",email); 
	    User user = (User) query.getResultList().stream().findFirst().orElse(null);
	   
		return user;
	}
	

	@Transactional
	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM user u",User.class);
		List<User> userDtos = typedQuery.getResultList();
		
		if(userDtos == null) {
	    	throw new UserNotFoundException("Can not found any user");
	    }
		
		return userDtos;
	}

	@Transactional
	@Override
	public User getUserByPartnerCode(String partnerCode) {
		TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.partnerCode = :partnerCode",User.class);
		typedQuery.setParameter("partnerCode",partnerCode);
		User userDto = typedQuery.getResultList().stream().findFirst().orElse(null);
		return userDto;
	}

	@Transactional
	@Override
	public List<User> getRefferedByUserId(Integer id) {
		TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM user u WHERE u.reffererUser.id = :id",User.class);
		typedQuery.setParameter("id", id);
		List<User> userDtos = typedQuery.getResultList();
		return userDtos;
	}

	
	@Transactional
	@Override
	public void deleteUser(Integer id) {
		User user = getUserById(id);
		if(id == null ) {
			throw new IllegalArgumentException("User Id must not be null");
		}
		
		if(user == null) {
			throw new UserNotFoundException("Can not found user with id : " + id);
		}
		em.remove(user);
		
//		Query cartId = em.createNativeQuery("SELECT cart_id FROM user u WHERE u.id = :id", Integer.class);
//		cartId.setParameter("id",id);	
//		Integer cartIdWithUser = (Integer) cartId.getResultList().stream().findFirst().orElse(null);
//		
//		Query queryDeleteFKUserInPurchase = em.createNativeQuery("DELETE FROM purchase p WHERE p.fk_user_id = :id");
//		queryDeleteFKUserInPurchase.setParameter("id", id);
//		queryDeleteFKUserInPurchase.executeUpdate();
//		
//		Query queryDeleteFKRole = em.createNativeQuery("DELETE FROM users_roles WHERE user_id = :id");
//		queryDeleteFKRole.setParameter("id", id);
//		queryDeleteFKRole.executeUpdate();
//		
				
//		Query query = em.createNativeQuery("DELETE FROM user u WHERE u.id = :id" , User.class);
//		
//		query.setParameter("id", id);
//		
//		int rowsAffected = query.executeUpdate();      
//		if (rowsAffected == 0) {
//        	throw new UserNotFoundException("User with id " + id + " not found.");
//        }
//		
		
                  
//        Query queryDeleteFKCart = 
//				em.createNativeQuery("DELETE FROM cart c WHERE c.id = :cartId");
//		queryDeleteFKCart.setParameter("cartId", cartIdWithUser);
//		queryDeleteFKCart.executeUpdate();
//		
	}

	@Transactional
	@Override
	public List<Role> getRoleByUser(User user) {
		if(user == null) {
			throw new UserNotFoundException("Can not found user!");
		}
		
		TypedQuery<Role> typedQuery = em.createQuery(
				"SELECT ur.role FROM usersRoles ur WHERE ur.user = :user" , Role.class);
		
		typedQuery.setParameter("user", user);
		
		List<Role> roles = typedQuery.getResultList();
		
		return roles;
	}
	
	

}
