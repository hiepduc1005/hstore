package com.hstore.vn.dto.response;

import java.math.BigDecimal;

public class TotalPriceResponse {
	
	public BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TotalPriceResponse(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TotalPriceResponse() {
	}

}
