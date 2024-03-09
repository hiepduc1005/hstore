package com.hstore.vn.exception.auth;

public class EmailAlreadyExitsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public EmailAlreadyExitsException(String message) {
		super(message);
		this.message = message;
	}
	
	public EmailAlreadyExitsException() {
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
