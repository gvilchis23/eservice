
package com.bancoazteca.elite.ejb.chequera;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class ChequeraException extends Exception {


	private static final long serialVersionUID = -7521143031182466005L;


	public ChequeraException() { }


	public ChequeraException(String message) {
		super(message);
	}

	
	public ChequeraException(Throwable cause) {
		super(cause);
	}


	public ChequeraException(String message, Throwable cause) {
		super(message, cause);
	}

}
