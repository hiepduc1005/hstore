package com.hstore.vn.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "wish_list")
@Table(name = "wish_list")
public class WishList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	
	@OneToOne
	@JoinColumn(name = "user_id")
	public User user;
	
	@OneToMany(mappedBy = "wishList" ,cascade = CascadeType.ALL)
	public List<WishListsProducts> products = new ArrayList<WishListsProducts>();

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

	public void addProduct(Product product) {
		WishListsProducts wishListsProducts = new WishListsProducts(this, product);
		products.add(wishListsProducts);
		product.getWishListsProducts().add(wishListsProducts);
	}
	
	public void removeProduct(Product product) {
		WishListsProducts wishListsProducts = new WishListsProducts(this, product);
		product.getWishListsProducts().remove(wishListsProducts);
		products.remove(wishListsProducts);
		wishListsProducts.setProduct(null);
		wishListsProducts.setWishList(null);
	}
	

	public void setProducts(List<WishListsProducts> products) {
		this.products = products;
	}


	
}
