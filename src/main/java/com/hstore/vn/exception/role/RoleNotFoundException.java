package com.hstore.vn.exception.role;

public class RoleNotFoundException extends RuntimeException{
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

	public RoleNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
