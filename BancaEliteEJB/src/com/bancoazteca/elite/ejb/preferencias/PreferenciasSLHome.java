package com.bancoazteca.elite.ejb.preferencias;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


public interface PreferenciasSLHome extends EJBLocalHome{
	PreferenciasSL create() throws CreateException;
}
