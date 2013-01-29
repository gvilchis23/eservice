package com.bancoazteca.elite.ejb.exception;

/**
 * @author Banco Azteca S.A. [JFAV] Octubre 6, 2008.
 */
public class EncryptionException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3248789298665379229L;

	public EncryptionException() {
		super();
	}

	public EncryptionException(String msg, Throwable t) {
		super(msg, t);
	}

	public EncryptionException(String msg) {
		super(msg);
	}

	public EncryptionException(Throwable t) {
		super(t);
	}
}

