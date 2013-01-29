package com.bancoazteca.eservice.filters;

import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FiltroCuentaRequestTO;
import com.bancoazteca.elite.beans.FiltroCuentaResponseTO;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.session.Session;

public class CuentasRedMovilFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(AlnovaRedMovilFilterException.class);
	private static final String ESTATUS_CUENTAS = "estatus_cuentas";
	
	public void ejecutar(HashMap<String, Object> sessionFilter )throws AlnovaRedMovilFilterException, Exception{
		String xml = (String) sessionFilter.get("xml");
		String idServicio = (String) sessionFilter.get("idServicio");
		
		if (!idServicio.equals(ESTATUS_CUENTAS)) {
			log.info(" --- Filtrando Cuentas peticion XML ----");
			Session session = (Session) sessionFilter.get("session");
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CommandConstantes.CLIENTE_TO);
			ResourceFacadeSL bean = getDelegate();

			if (clienteTO!=null) {
				FiltroCuentaResponseTO responseTO = null;
				FiltroCuentaRequestTO requestTO = new FiltroCuentaRequestTO();
				
				requestTO.setXml(xml);
				requestTO.setIdUsuario(clienteTO.getNumero());	
				responseTO = bean.filtraCuentasOcultas(requestTO);
				xml = responseTO.getXml();
				sessionFilter.put("xml", xml);
				sessionFilter.put("respuesta", "Filtro Cuentas Aplicado");
			}
		}
		else {
			sessionFilter.put("respuesta", "Filtro Cuentas NO Aplicado");
		}
	}
	
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
