package com.vikram.openconnect.login.exception;

public class NoOAuthCredentialException extends RuntimeException {

	private static final long serialVersionUID = -1970984094706370205L;
	
	public NoOAuthCredentialException(String message){
		super(message);
	}

}
