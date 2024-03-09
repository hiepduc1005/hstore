package com.hstore.vn.exception.purchase;

public class DeletePurchaseFailure extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public DeletePurchaseFailure(String message) {
		super(message);
		this.message = message;
	}
	
	public DeletePurchaseFailure() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
