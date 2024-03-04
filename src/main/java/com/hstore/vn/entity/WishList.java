package com.hstore.vn.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "wish_list")
@Table(name = "wish_list")
public class WishList {
	
	@Id
	@Column(name = "user_id")
	public Integer userId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "wishlists_products",
	           joinColumns = @JoinColumn(name = "user_wishlist_id"),
	           inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
