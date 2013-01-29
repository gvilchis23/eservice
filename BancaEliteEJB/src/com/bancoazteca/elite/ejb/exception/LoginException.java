package com.bancoazteca.elite.ejb.exception;

public class LoginException extends Exception {


	private static final long serialVersionUID = 4400630844413000280L;

	public LoginException(String message, Throwable e) {
		super(message, e);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable e) {
		super(e);
	}

}
