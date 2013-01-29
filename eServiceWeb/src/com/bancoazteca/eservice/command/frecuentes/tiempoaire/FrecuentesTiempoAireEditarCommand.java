package com.bancoazteca.eservice.command.frecuentes.tiempoaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.beans.FrecuentesTiempoAireRequestTO;
import com.bancoazteca.elite.beans.FrecuentesTiempoAireResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosException;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.beans.Frecuentes_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class FrecuentesTiempoAireEditarCommand extends CommandBase {
	
	public Response validacion(Session session) throws PagoServiciosException, SessionExpiredException, ServiceLocatorException{
		Response response = new Response();	
		FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO = new FrecuentesTiempoAireRequestTO();	
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		Frecuente_Tiempo_AireTO frecuenteTiempoAireTO = new Frecuente_Tiempo_AireTO();
		HashMap<String, String> errors = null;
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
			FrecuentesTiempoAireForm forma=(FrecuentesTiempoAireForm) getFormBean();					
			
			frecuentesTiempoAireRequestTO.setUser(clienteTO.getUserName());
			frecuentesTiempoAireRequestTO.setAlias(forma.getAlias());
			frecuentesTiempoAireRequestTO.setTelefono(forma.getNumero_telefonico());
			frecuentesTiempoAireRequestTO.setCompania(forma.getCompania().toLowerCase());
			
			frecuentesTiempoAireResponseTO = resourceFacadeSL.referenciasFrecuentesTiempoAireInit(frecuentesTiempoAireRequestTO);
			
			Iterator<Map> iterator = frecuentesTiempoAireResponseTO.getTelefonosFrecuentes().iterator();
			FrecuentesResponseTO temp = null;
			int index = -1;
			int cont = 0;
			while(iterator.hasNext()){
				Map telefono = (Map)iterator.next();
				temp = new FrecuentesResponseTO();
				temp.setTelefono((String)telefono.get("telefono"));
				if(temp.getTelefono()!=null){
					if(temp.getTelefono().equalsIgnoreCase(forma.getNumero_telefonico())){
						index = cont;
					}
				}
				cont++;
			}
			
			if(index == -1){
				//error no existe en frecuente
				errors = new HashMap<String, String>();
				errors.put("numero_telefonico","Este n�mero de tel�fono celular no existe en su registro.");
				returnErrorMap(response, errors);
			}else{
				frecuentesTiempoAireRequestTO.setIndex(""+index);
				session.addAttribute(INDICE_REFERENCIA_EDITAR, frecuentesTiempoAireRequestTO.getIndex());
				resourceFacadeSL.setModificaReferenciasFrecuentesTiempoAireValidacion(frecuentesTiempoAireRequestTO);
				frecuenteTiempoAireTO.setAlias(forma.getAlias());
				frecuenteTiempoAireTO.setCompania(forma.getCompania().toUpperCase());
				frecuenteTiempoAireTO.setNumero_telefonico(forma.getNumero_telefonico());
			}
			response.addAttribute(frecuenteTiempoAireTO);
		
		}catch(EliteDataException e) {
			session.deleteAttribute(INDICE_REFERENCIA_EDITAR);
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws PagoServiciosException, SessionExpiredException, ServiceLocatorException{
		Response response = new Response();
		FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO = new FrecuentesTiempoAireRequestTO();	
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		Frecuentes_Tiempo_AireTO numerosFrecuentesTiempoAireTO=new Frecuentes_Tiempo_AireTO();		
		HashMap<String, String> errors = null;
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
			FrecuentesTiempoAireForm forma=(FrecuentesTiempoAireForm) getFormBean();					
			
			frecuentesTiempoAireRequestTO.setUser(clienteTO.getUserName());
			frecuentesTiempoAireRequestTO.setAlias(forma.getAlias());
			frecuentesTiempoAireRequestTO.setTelefono(forma.getNumero_telefonico());
			frecuentesTiempoAireRequestTO.setCompania(forma.getCompania().toLowerCase());
			
			String index = (String)session.getAttribute(INDICE_REFERENCIA_EDITAR);
			if(index!=null){
				frecuentesTiempoAireRequestTO.setIndex(index);
				frecuentesTiempoAireResponseTO = resourceFacadeSL.setModificaReferenciasFrecuentesTiempoAireEjecucion(frecuentesTiempoAireRequestTO);
				numerosFrecuentesTiempoAireTO.setColeccion_frecuentes(createCollectionFromMap(frecuentesTiempoAireResponseTO.getTelefonosFrecuentes(),forma.getCompania()));
			}else{
				//error si index es nulo
				errors = new HashMap<String, String>();
				errors.put("comando","Estimado usuario el tipo de solicitud que esta intentando de ejecutar es incorrecto favor de verificar su petici�n.");
				returnErrorMap(response, errors);
			}
			response.addAttribute(numerosFrecuentesTiempoAireTO);
		
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);
		}finally{
			session.deleteAttribute(INDICE_REFERENCIA_EDITAR);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Frecuente_Tiempo_AireTO> createCollectionFromMap(Collection<Map> mapaTelefonos, String carrier){
		Iterator<Map> iterator = mapaTelefonos.iterator();
		Collection<Frecuente_Tiempo_AireTO> frecuentesTiempoAireTOCollection=new ArrayList<Frecuente_Tiempo_AireTO>();
		Frecuente_Tiempo_AireTO temp = null;
		while(iterator.hasNext()){
			Map telefono = (Map)iterator.next();
			temp = new Frecuente_Tiempo_AireTO();
			temp.setAlias((String)telefono.get("referencia"));
			temp.setNumero_telefonico((String)telefono.get("telefono"));
			temp.setCompania(carrier.toUpperCase());
			frecuentesTiempoAireTOCollection.add(temp);
		}
		return frecuentesTiempoAireTOCollection;
	}
}
