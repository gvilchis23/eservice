package com.bancoazteca.elite.ejb.inversiones;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;


public interface InversionesSLHome extends EJBLocalHome {

	InversionesSL create() throws CreateException;
	
}
