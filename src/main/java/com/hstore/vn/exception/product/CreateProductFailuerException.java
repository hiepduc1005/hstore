package com.hstore.vn.exception.product;

public class CreateProductFailuerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public CreateProductFailuerException(String message) {
		super(message);
		this.message = message;
	}
	
	public CreateProductFailuerException() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
