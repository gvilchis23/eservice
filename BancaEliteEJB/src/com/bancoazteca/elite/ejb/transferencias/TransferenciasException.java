package com.bancoazteca.elite.ejb.transferencias;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 26, 2008.
 */
public class TransferenciasException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -4401897910752695381L;

	/**
	 * 
	 */
	public TransferenciasException() { }

	/**
	 * @param message
	 */
	public TransferenciasException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TransferenciasException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TransferenciasException(String message, Throwable cause) {
		super(message, cause);
	}

}
