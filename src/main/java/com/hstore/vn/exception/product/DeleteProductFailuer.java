package com.hstore.vn.exception.product;

public class DeleteProductFailuer extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public DeleteProductFailuer(String message) {
		super(message);
		this.message = message;
	}
	
	public DeleteProductFailuer() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
