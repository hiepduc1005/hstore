package com.hstore.vn.entity;


import java.util.List;

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
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;


@Entity(name = "purchase")
@Table(name = "purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User user;
	

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "purchases_products",
	           joinColumns = @JoinColumn(name = "purchase_id"),
	           inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<Product> products;

	
	@ManyToOne
	@JoinColumn(name = "purchase_status_id")
	public PurchaseStatus purchaseStatus;
	
	@Column(name = "address")
	public String address;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name = "date")
	public String localDateTime;
	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}
	
	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	
	public String getLocalDateTime() {
		return localDateTime;
	}
	
	
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
}
