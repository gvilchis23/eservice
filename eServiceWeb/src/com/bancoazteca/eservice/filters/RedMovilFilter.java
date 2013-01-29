package com.bancoazteca.eservice.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.ejb.alnova.beans.B023CuentaConRedMovilAztecaWEBRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.B023CuentaConRedMovilAztecaWEBResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;

public class RedMovilFilter implements Filter {
	private static final Logger log = Logger.getLogger(AlnovaRedMovilFilterException.class);

	public void ejecutar(HashMap<String, Object> sessionFilter )throws AlnovaRedMovilFilterException, Exception{
		B023CuentaConRedMovilAztecaWEBRequestTO requestTO = null;
		B023CuentaConRedMovilAztecaWEBResponseTO responseTO  = null;
		
		ClienteTO clienteTO = (ClienteTO) sessionFilter.get(CLIENTE_TO);
		String idAplicacion = (String) sessionFilter.get(ConstantesFiltro.ID_APLICACION);
		
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();
		String clienteAlnova = clienteTO.getNumero();
		String opcionAlnova = getOpcionProperties( idAplicacion );
	
		boolean contratado = false;
		
		for(CuentaTO cuenta: cuentas){
			String cuentaAlnova = cuenta.getNumero();
			requestTO = new B023CuentaConRedMovilAztecaWEBRequestTO();
			
			requestTO.setNumeroClienteAlnova( clienteAlnova );
			requestTO.setCuentaAlnova( cuentaAlnova );
			requestTO.setIdAplicacion( idAplicacion );
			requestTO.setOpcion( opcionAlnova );
			
			
			try {
				ResourceFacadeSegundoSL facadeSegundoSL;
				facadeSegundoSL = getDelegateSegundo();
				responseTO = (B023CuentaConRedMovilAztecaWEBResponseTO) facadeSegundoSL.ejecutaTransaccionAlnova(requestTO);
				contratado = validarResponse(responseTO, idAplicacion);
				if(contratado){
					break;
				}
			} catch (AlnovaException e) {
				if( e.getAlnovaExceptionTO().getLevel().equals(AlnovaException.LEVEL_WEB_SERVICES) ){
					log.info("La cuenta "+cuenta.getDescripcion() +" "+cuenta.getCuentaFormateada()+" no es posible comparar el pago de red movil  AlnovaError("+ e.getAlnovaExceptionTO().getMessage()+")");
				}else{
					throw e;
				}
			}
		}
		
		if( ! contratado ){
			throw new CuentaFilterException("El usuario no tiene contratado el servicio");
		}
		
	}

	
	protected ResourceFacadeSegundoSL getDelegateSegundo() throws ServiceLocatorException{
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		ResourceFacadeSegundoSL facade;
		try {
			facade = serviceLocator.getFacadeSegundoEJBHome().create();
		} catch (EJBException e) {
			throw new ServiceLocatorException(e);
		} catch (CreateException e) {
			throw new ServiceLocatorException(e);
		}
		return facade;
	}
	
	private void validarRequest(B023CuentaConRedMovilAztecaWEBRequestTO requestTO) throws AlnovaRedMovilFilterException{
		if( Validator.isEmptyData(requestTO.getIdAplicacion()) 
				|| Validator.isEmptyData( requestTO.getNumeroClienteAlnova() ) 
				|| Validator.isEmptyData( requestTO.getCuentaAlnova() )		
				|| Validator.isEmptyData( requestTO.getOpcion() ) ){
			log.info("El TO: B023CuentaConRedMovilAztecaWEBRequestTO de petición no contiene valores ");
			throw new AlnovaRedMovilFilterException("La petición B023 no es válida.");
		}
	}
	
	private boolean validarResponse(B023CuentaConRedMovilAztecaWEBResponseTO responseTO, String idAplicacion) throws AlnovaRedMovilFilterException{
		boolean validacion = false;
		try {
			String estatus = (String)PropertiesService.getInstance().getPropertie(FILTRO_PROPERTIES , PREFIJO_FILTROS_PROPERTIES + idAplicacion+ ALNOVA_ESTATUS );
//			String estatus_no_pago = (String)PropertiesService.getInstance().getPropertie(FILTRO_PROPERTIES , PREFIJO_FILTROS_PROPERTIES + idAplicacion+ ALNOVA_ESTATUS_NO_PAGO );

			if ( responseTO.getEstatus().equalsIgnoreCase( estatus ) ){
				validacion =  true;
			}else{
				throw new AlnovaRedMovilFilterException("El usuario no tiene acceso al sistema");
			}
		} catch (IOException e) {
			log.info("No se pudo leer el valor estatus de: "  + FILTRO_PROPERTIES);
		}
		
		return validacion;
	}
	
	private String getOpcionProperties(String idAplicacion){
		String opcion = "";
		try {
			opcion = (String)PropertiesService.getInstance().getPropertie(FILTRO_PROPERTIES, PREFIJO_FILTROS_PROPERTIES + idAplicacion + ALNOVA_OPCION); 
			opcion = opcion.trim();
			return opcion;
		} catch (IOException e) {
			return "";
		}
	}

}
