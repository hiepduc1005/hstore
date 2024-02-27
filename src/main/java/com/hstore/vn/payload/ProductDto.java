package com.hstore.vn.payload;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "product")
@Table(name =  "product")
public class ProductDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "product_name")
	public String name;
	
	
	@ManyToOne
	@JoinColumn(name ="fk_category_id")
	public CategoryDto category;
	
	@Column(name = "price")
	public BigDecimal price;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "img_name")
	public String imgName;
	
	@Column(name = "guid" , updatable = false , nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	public String guid;
	
	public Integer getId() {
		return id;
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
	
	public CategoryDto getCategory() {
		return category;
	}
	
	public void setCategory(CategoryDto category) {
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
