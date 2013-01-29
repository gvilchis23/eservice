/**
 * 
 */
package com.bancoazteca.elite.ejb.usuario;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * @author Banco Azteca S.A. [JFAV]
 * Agosto 25, 2008.
 */
public interface UsuarioSLHome extends EJBLocalHome {
	
	UsuarioSL create() throws CreateException;

}
