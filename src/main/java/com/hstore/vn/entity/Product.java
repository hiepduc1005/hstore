package com.hstore.vn.entity;

import java.math.BigDecimal;

public interface Product {
	
	Integer getProductId();
	void setProductId(Integer id);
	
	String getProductName();
	void setProductName(String name);
	
	Category getCategory();
	void setCategory(Category category);
	
	BigDecimal getPrice();
	void setPrice(BigDecimal price);
	
	String getProductDescription();
	void setProductDescription(String description);
	
	String getImg();
	void setImg(String img);
	
	String getProductGUID();
	void setProductGUID(String guid);
	
}
