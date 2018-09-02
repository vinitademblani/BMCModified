package com.springsecurity.exception;


public class MovieNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	public MovieNotFoundException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
