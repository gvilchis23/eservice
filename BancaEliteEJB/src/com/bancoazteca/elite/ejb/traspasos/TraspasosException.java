package com.bancoazteca.elite.ejb.traspasos;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 26, 2008. 
 *
 */
public class TraspasosException extends Exception {


	private static final long serialVersionUID = -7192497170113414188L;

	public TraspasosException(String message) {
		super(message);
	}

	public TraspasosException(Throwable e) {
		super(e);
	}

	public TraspasosException(String message, Throwable e) {
		super(message, e);
	}

}
