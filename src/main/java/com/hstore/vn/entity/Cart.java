package com.hstore.vn.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "cart")
@Table(name = "cart")
public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	
	@OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL , orphanRemoval =  true)
	public List<CartsProducts> products = new ArrayList<CartsProducts>();
	
	@OneToOne
    @JoinColumn(name = "user_id")
    public User user;
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public void addProduct(Product product) {
		CartsProducts cartsProducts = new CartsProducts(this, product);
		products.add(cartsProducts);
		product.getCartsProducts().add(cartsProducts);
	}
	
	public void removeProduct(Product product) {
		CartsProducts cartsProducts = new CartsProducts(this, product);
		product.getCartsProducts().remove(cartsProducts);
		products.remove(cartsProducts);
		cartsProducts.setCart(null);
		cartsProducts.setProduct(null);
	}
	

	public List<CartsProducts> getProducts() {
		return products;
	}

	public void setProducts(List<CartsProducts> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}
	
	
	
	
}
