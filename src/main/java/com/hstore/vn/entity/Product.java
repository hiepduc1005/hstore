package com.hstore.vn.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "product")
@Table(name =  "product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "product_name")
	public String name;
	
	
	@ManyToOne
	@JoinColumn(name ="fk_category_id")
	public Category category;
	
	@Column(name = "price")
	public BigDecimal price;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "img_name")
	public String imgName;
	
	@Column(name = "guid" , updatable = false , nullable = false)
	public String guid;
	
	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval =  true)
	public List<CartsProducts> cartsProducts = new ArrayList<CartsProducts>();
	
	@OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL , orphanRemoval =  true)
	public List<WishListsProducts> wishListsProducts = new ArrayList<WishListsProducts>();
	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , orphanRemoval =  true)
	public List<PurchasesProducts> purchasesProduct = new ArrayList<PurchasesProducts>();
	
	public Integer getId() {
		return id;
	}
	

	public List<CartsProducts> getCartsProducts() {
		return cartsProducts;
	}

	public void setCartsProducts(List<CartsProducts> cartsProducts) {
		this.cartsProducts = cartsProducts;
	}



	public List<WishListsProducts> getWishListsProducts() {
		return wishListsProducts;
	}


	public void setWishListsProducts(List<WishListsProducts> wishListsProducts) {
		this.wishListsProducts = wishListsProducts;
	}


	public List<PurchasesProducts> getPurchasesProduct() {
		return purchasesProduct;
	}


	public void setPurchasesProduct(List<PurchasesProducts> purchasesProduct) {
		this.purchasesProduct = purchasesProduct;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImgName() {
		return imgName;
	}
	
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
}
