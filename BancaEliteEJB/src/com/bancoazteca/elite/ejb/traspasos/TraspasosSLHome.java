package com.bancoazteca.elite.ejb.traspasos;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 26, 2008.
 */
public interface TraspasosSLHome extends EJBLocalHome {
	
	TraspasosSL create() throws CreateException;

}
