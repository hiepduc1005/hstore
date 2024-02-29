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
import jakarta.persistence.Table;

@Entity(name = "cart")
@Table(name = "cart")
public class CartDto {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@ManyToMany
	@JoinTable(name = "carts_products",
	           joinColumns = @JoinColumn(name = "cart_id"),
	           inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<ProductDto> products;
	
	
	
	public List<ProductDto> getProducts() {
		return products;
	}
	
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}
	
	
	
	
}
