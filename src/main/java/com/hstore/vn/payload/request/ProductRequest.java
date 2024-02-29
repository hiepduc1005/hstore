package com.hstore.vn.payload.request;

import java.math.BigDecimal;


public class ProductRequest {
	
	public String name;
	public CategoryRequest category;
	public BigDecimal price;
	public String description;
	public String imgName;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryRequest getCategory() {
		return category;
	}
	public void setCategory(CategoryRequest category) {
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
	public ProductRequest( String name, CategoryRequest category, BigDecimal price, String description,
			String imgName) {
		
		
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.imgName = imgName;
	}
	
	
	

}
