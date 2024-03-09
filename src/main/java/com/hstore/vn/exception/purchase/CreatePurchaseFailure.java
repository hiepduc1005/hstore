package com.hstore.vn.exception.purchase;

public class CreatePurchaseFailure extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public CreatePurchaseFailure(String message) {
		super(message);
		this.message = message;
	}
	
	public CreatePurchaseFailure() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
