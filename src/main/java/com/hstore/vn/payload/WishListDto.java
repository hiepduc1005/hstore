package com.hstore.vn.payload;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "wish_list")
@Table(name = "wish_list")
public class WishListDto {
	
	@Id
	@Column(name = "user_id")
	public Integer userId;
	
	@ManyToMany
	@JoinTable(name = "wishlists_products",
	           joinColumns = @JoinColumn(name = "user_wishlist_id"),
	           inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<ProductDto> products;

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
}
