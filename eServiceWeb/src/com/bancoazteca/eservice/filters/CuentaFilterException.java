package com.bancoazteca.eservice.filters;


public class CuentaFilterException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717950083651584741L;

	/**
	 * @param message
	 */
	public CuentaFilterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CuentaFilterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CuentaFilterException(String message, Throwable cause) {
		super(message, cause);
	}


}
