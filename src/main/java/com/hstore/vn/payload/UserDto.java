package com.hstore.vn.payload;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity(name = "user")
@Table(name = "user")
public class UserDto implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "first_name")
	public String firstName;
	
	@Column(name = "last_name")
	public String lastName;
	
	@Column(name = "email", unique = true)
	@Email
	public String email;
	
	@Column(name = "phone_number")
	public String phoneNum;
	
	@Column(name = "password")
	public String password;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",	
	           joinColumns = @JoinColumn(name = "user_id"),
	           inverseJoinColumns = @JoinColumn(name = "role_id"))
	public List<RoleDto> rolesDto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	public CartDto cart;
	
	@Column(name = "money")
	public BigDecimal money;
	
	@Column(name = "card_number")
	public String creditNum;
	
	@Column(name = "partner_code")
	public String partnerCode;
	
	@Column(name = "refferer_user")
	public UserDto reffererUser;
	
	 private Boolean locked = false;
	 
	 @Column(name = "enabled", columnDefinition = "BOOLEAN")
	 public Boolean enabled = true;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleDto> getRoles() {
		return rolesDto;
	}
	public void setRoles(List<RoleDto> rolesDto) {
		this.rolesDto = rolesDto;
	}
	public CartDto getCart() {
		return cart;
	}
	public void setCart(CartDto cart) {
		this.cart = cart;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public UserDto getReffererUser() {
		return reffererUser;
	}
	public void setReffererUser(UserDto reffererUser) {
		this.reffererUser = reffererUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<String> privileges = getPrivileges(rolesDto);
		
		for(String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return  authorities;
	}
	
	public List<String> getPrivileges(List<RoleDto> roles ){
        List<PrivilegeDto> privilegeDtos = new ArrayList<PrivilegeDto>();
		List<String> privileges = new ArrayList<String>();
		for(RoleDto role : roles) {
			privilegeDtos.addAll(role.getPrivileges());
			privileges.add(role.getName());
		}
		
		for(PrivilegeDto privilege : privilegeDtos ) {
			privileges.add(privilege.getName());
		}
		
		return privileges;
	}
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}


}
