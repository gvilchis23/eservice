package com.bancoazteca.monitoreo;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.struts.actions.DispatchAction;

import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;

public class MonitoreoAction extends DispatchAction {
	
	protected ResourceFacadeSL getDelegate() throws ServiceLocatorException{
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		ResourceFacadeSL facade;
		try {
			facade = serviceLocator.getFacadeEJBHome().create();
		} catch (EJBException e) {
			throw new ServiceLocatorException(e);
		} catch (CreateException e) {
			throw new ServiceLocatorException(e);
		}
		return facade;
	}
	
}
