/**
 * 
 */
package com.bancoazteca.elite.ejb.exception;

/**
 * @author acc31867
 *
 */
public class EliteException extends Exception {
	
	private static final long serialVersionUID = -4937849847298343497L;
	
	private Object dataError;

	public Object getDataError() {
		return dataError;
	}

	public void setDataError(Object dataError) {
		this.dataError = dataError;
	}

	/**
	 * 
	 */
	public EliteException() {
	}

	/**
	 * @param message
	 */
	public EliteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EliteException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EliteException(String message, Throwable cause) {
		super(message, cause);
	}

}
