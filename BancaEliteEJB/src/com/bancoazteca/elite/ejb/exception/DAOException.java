package com.bancoazteca.elite.ejb.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1509585056658980003L;

	public DAOException(String message, Throwable e) {
		super(message, e);
	}

	public DAOException(String message) {
		super(message);	
	}

	public DAOException(Throwable e) {
		super(e);	
	}

}
