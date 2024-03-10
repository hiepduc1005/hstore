package com.hstore.vn.dto.request;

import java.math.BigDecimal;

public class ProductRequestUpdate {
	
	public Integer id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public ProductRequestUpdate(Integer id , String name, CategoryRequest category, BigDecimal price, String description,
			String imgName) {
		
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.imgName = imgName;
	}
	
	

}
