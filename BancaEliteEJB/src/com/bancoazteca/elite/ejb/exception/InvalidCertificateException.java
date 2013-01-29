package com.bancoazteca.elite.ejb.exception;

public class InvalidCertificateException extends Exception{

	private static final long serialVersionUID = -6001466788757232038L;

	public InvalidCertificateException(String message, Throwable e) {
		super(message, e);
	}

	public InvalidCertificateException(String message) {
		super(message);
	}

	public InvalidCertificateException(Throwable e) {
		super(e);
	}

}
