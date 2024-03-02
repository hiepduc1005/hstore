package com.hstore.vn.exception.purchase;

public class PurchaseNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PurchaseNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	

}
