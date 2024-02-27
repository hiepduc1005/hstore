package com.hstore.vn.payload;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class PurchaseDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	public UserDto user;
	
	@ManyToMany
	@JoinTable(name = "purchases_products",
	           joinColumns = @JoinColumn(name = "purchase_id"),
	           inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<ProductDto> products;
	
	@ManyToOne
	@JoinColumn(name = "purchase_status_id")
	public PurchaseStatusDto purchaseStatus;

	@Column(name = "date")
	public String localDateTime;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public UserDto getUser() {
		return user;
	}
	
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public List<ProductDto> getProducts() {
		return products;
	}
	
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
	public PurchaseStatusDto getPurchaseStatus() {
		return purchaseStatus;
	}
	
	public void setPurchaseStatus(PurchaseStatusDto purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	
	public String getLocalDateTime() {
		return localDateTime;
	}
	
	
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
}
