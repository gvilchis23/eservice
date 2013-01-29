package com.bancoazteca.eservice.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.ejb.alnova.beans.B023CuentaConRedMovilAztecaWEBRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.B023CuentaConRedMovilAztecaWEBResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;

public class AlnovaRedMovilFilter implements Filter {
	private static final Logger log = Logger.getLogger(AlnovaRedMovilFilter.class);

	public void ejecutar(HashMap<String, Object> sessionFilter )throws AlnovaRedMovilFilterException, Exception{
		PropertiesService propertiesService = PropertiesService.getInstance();
		ClienteTO clienteTO = (ClienteTO) sessionFilter.get(CLIENTE_TO);
		String cuentaAlnova = (String) sessionFilter.get(CUENTA_ALNOVA);
		String idAplicacion = (String) sessionFilter.get(ID_APLICACION);
		String opcionAlnova = getOpcionProperties( idAplicacion );
		B023CuentaConRedMovilAztecaWEBRequestTO requestTO = new B023CuentaConRedMovilAztecaWEBRequestTO();
		B023CuentaConRedMovilAztecaWEBResponseTO responseTO  = null;

		String clienteAlnova = clienteTO.getNumero();
		ResourceFacadeSegundoSL facadeSegundoSL;
		
		requestTO.setNumeroClienteAlnova( clienteAlnova );
		requestTO.setCuentaAlnova( cuentaAlnova );
		
		requestTO.setIdAplicacion( idAplicacion );
		requestTO.setOpcion( opcionAlnova );
		
		validarRequest(requestTO);
		try {
			facadeSegundoSL = getDelegateSegundo();
			responseTO = (B023CuentaConRedMovilAztecaWEBResponseTO) facadeSegundoSL.ejecutaTransaccionAlnova(requestTO);
			validarResponse(responseTO, idAplicacion);
		} catch (AlnovaException e) {
			if( e.getAlnovaExceptionTO().getMessage().contains( propertiesService.getPropertie( FILTRO_PROPERTIES,  ALNOVA_ERROR_CODE ) ) ){
				log.info("El usuario no tiene contratado el servicio de red móvil ázteca");
				throw new AlnovaRedMovilFilterException("El usuario no tiene contratado el servicio de red móvil ázteca", e);
			}else{
				throw new AlnovaRedMovilFilterException("Ocurrió un problema al validar el estado de la cuenta " ,e);
			}
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
			log.info("El TO de petición no contiene valores ");
			throw new AlnovaRedMovilFilterException("La petición no es válida");
		}
	}
	
	private void validarResponse(B023CuentaConRedMovilAztecaWEBResponseTO responseTO, String idAplicacion) throws AlnovaRedMovilFilterException{
		try {
			String estatus = (String)PropertiesService.getInstance().getPropertie(FILTRO_PROPERTIES , PREFIJO_FILTROS_PROPERTIES + idAplicacion+ ALNOVA_ESTATUS );
			if (! responseTO.getEstatus().equalsIgnoreCase( estatus )){
				throw new AlnovaRedMovilFilterException("El usuario no tiene activo el servicio de Red Móvil Azteca");
			}
		} catch (IOException e) {
			log.info("No se pudo leer el valor estatus de: "  + FILTRO_PROPERTIES);
		}
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
