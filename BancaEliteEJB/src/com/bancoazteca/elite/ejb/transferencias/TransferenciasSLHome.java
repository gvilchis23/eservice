package com.bancoazteca.elite.ejb.transferencias;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 26, 2008.
 */
public interface TransferenciasSLHome extends EJBLocalHome {
	
	TransferenciasSL create() throws CreateException;

}
