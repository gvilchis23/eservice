package com.bancoazteca.elite.ejb.exception;

public class SessionExpiredException extends Exception {

	private static final long serialVersionUID = -447145695441039763L;


	public SessionExpiredException(String message, Throwable e) {
		super(message, e);
	}

	public SessionExpiredException(String message) {
		super(message);
	}

	public SessionExpiredException(Throwable e) {
		super(e);
	}
	
	

}
