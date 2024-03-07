package com.hstore.vn.entity;

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


@Entity(name = "purchase")
@Table(name = "purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User user;
	

	@OneToMany(mappedBy = "purchase" , cascade = CascadeType.ALL , orphanRemoval =  true)
	public List<PurchasesProducts> products = new ArrayList<PurchasesProducts>();
	
	@ManyToOne
	@JoinColumn(name = "purchase_status_id")
	public PurchaseStatus purchaseStatus;
	
	@Column(name = "address")
	public String address;
	
	public void addProduct(Product product ) {
		PurchasesProducts purchasesProducts = new PurchasesProducts(this, product);
		products.add(purchasesProducts);
		product.getPurchasesProduct().add(purchasesProducts);
	}
	
	public void removeProduct(Product product ) {
		PurchasesProducts purchasesProducts = new PurchasesProducts(this, product);
		product.getPurchasesProduct().remove(purchasesProducts);
		products.remove(purchasesProducts);
		purchasesProducts.setProduct(null);
		purchasesProducts.setPurchase(null);
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	


	@Column(name = "date")
	public String localDateTime;
	
	public List<PurchasesProducts> getProducts() {
		return products;
	}

	public void setProducts(List<PurchasesProducts> products) {
		this.products = products;
	}
	
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
