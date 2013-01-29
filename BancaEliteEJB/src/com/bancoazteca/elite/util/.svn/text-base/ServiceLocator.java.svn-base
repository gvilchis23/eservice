/**
 * 
 */
package com.bancoazteca.elite.util;

import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.bancoazteca.elite.ejb.facade.ResourceFacadeSLHome;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSLHome;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public class ServiceLocator {

	// private static final Logger $log =
	// Logger.getLogger(ServiceLocator.class.getName());
	private static ServiceLocator serviceLocator;
	InitialContext context = null;

	/**
	 * 
	 * @throws ServiceLocatorException
	 */
	private ServiceLocator() throws ServiceLocatorException {
		try {
			context = new InitialContext();
		} catch (NamingException nex) {
			throw new ServiceLocatorException(nex.getMessage());
		}
	}

	/**
	 * Regresa la instancia de ServiceLocator.
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static ServiceLocator getInstance() throws ServiceLocatorException {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}



	/**
	 * Regresa el Home Local de un EJB con base a su nombre y Clase.
	 * 
	 * @param name
	 * @param clazz
	 * @return EJBHome
	 * @throws ServiceLocatorException
	 */
	public EJBLocalHome getLocalHome(String name) throws ServiceLocatorException {
		EJBLocalHome home = null;
		try {
			Object objref = context.lookup(name);
			home = (EJBLocalHome) objref;
		} catch (NamingException nex) {
			throw new ServiceLocatorException(nex.getMessage());
		}
		return home;
	}

	public ResourceFacadeSLHome getFacadeEJBHome() throws ServiceLocatorException {
		ResourceFacadeSLHome resourceFacadeHome = null;
		try {
			Object obj = this.context.lookup(JNDINames.RESOURCE_FACADE);
			EJBLocalHome home = (EJBLocalHome) obj;
			resourceFacadeHome = (ResourceFacadeSLHome) home;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}
		return resourceFacadeHome;
	}
	public ResourceFacadeSegundoSLHome getFacadeSegundoEJBHome() throws ServiceLocatorException {
		ResourceFacadeSegundoSLHome resourceFacadeHome = null;
		try {
			Object obj = this.context.lookup(JNDINames.RESOURCE_FACADE2);
			EJBLocalHome home = (EJBLocalHome) obj;
			resourceFacadeHome = (ResourceFacadeSegundoSLHome) home;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}
		return resourceFacadeHome;
	}
}
