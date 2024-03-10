package com.hstore.vn.dto.response;

import java.math.BigDecimal;


public class ProductResponse {	
	
	public Integer id;

	public ProductResponse(Integer id, String name, CategoryResponse category, BigDecimal price, String description,
			String imgName, String guid) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.imgName = imgName;
		this.guid = guid;
	}

	public String name;
	
	public CategoryResponse category;
	
	public BigDecimal price;
	
	public String description;
	
	public String imgName;
	
	public String guid;

}
