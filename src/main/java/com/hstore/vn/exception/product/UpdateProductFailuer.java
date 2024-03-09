package com.hstore.vn.exception.product;

public class UpdateProductFailuer extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public UpdateProductFailuer(String message) {
		super(message);
		this.message = message;
	}
	
	public UpdateProductFailuer() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
