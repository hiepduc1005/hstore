package com.hstore.vn.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity(name = "user")
@Table(name = "user")
public class User {

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

	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
	public List<UsersRoles> roles = new ArrayList<UsersRoles>();
	
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
	public WishList wishList;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	public Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
	public List<Purchase> purchases;
	
	@Column(name = "money")
	public BigDecimal money;
	
	@Column(name = "card_number")
	public String creditNum;
	
	@Column(name = "partner_code")
	public String partnerCode;
	
	@Column(name = "refferer_user_id")
	public Integer reffererUser;
	
	 public List<Purchase> getPurchases() {
			return purchases;
		}
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public void addRole(Role role) {
		UsersRoles usersRoles = new UsersRoles(this, role);
		roles.add(usersRoles);
		role.getRoles().add(usersRoles);
	}
	
	public void removeRole(Role role) {
		UsersRoles usersRoles = new UsersRoles(this, role);
		role.getRoles().remove(usersRoles);
		roles.remove(usersRoles);
		usersRoles.setRole(null);
		usersRoles.setUser(null);
	}
	
	public List<UsersRoles> getRoles() {
		return roles;
	}
	public void setRoles(List<UsersRoles> roles) {
		this.roles = roles;
	}
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

	public WishList getWishList() {
		return wishList;
	}
	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
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
	public Integer getReffererUser() {
		return reffererUser;
	}
	public void setReffererUser(Integer reffererUser) {
		this.reffererUser = reffererUser;
	}
	
	
	
	

}
