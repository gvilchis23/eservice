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

public class FrecuentesTiempoAireAltaCommand extends CommandBase {
	
	@SuppressWarnings("unchecked")
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
			
			frecuentesTiempoAireResponseTO = resourceFacadeSL.referenciasFrecuentesTiempoAireInit(frecuentesTiempoAireRequestTO);
			
			Iterator<Map> iterator = frecuentesTiempoAireResponseTO.getTelefonosFrecuentes().iterator();
			FrecuentesResponseTO temp = null;
			boolean existeFrecuente = false;
			while(iterator.hasNext()){
				Map telefono = (Map)iterator.next();
				temp = new FrecuentesResponseTO();
				temp.setTelefono((String)telefono.get("telefono"));
				if(temp.getTelefono()!=null){
					if(temp.getTelefono().equalsIgnoreCase(forma.getNumero_telefonico())){
						existeFrecuente = true;
					}
				}
			}
			
			if(existeFrecuente){
				//error existe frecuente
				errors = new HashMap<String, String>();
				errors.put("numero_telefonico","Este número de teléfono celular ya existe en su registro.");
				returnErrorMap(response, errors);
			}else{
				frecuentesTiempoAireResponseTO = resourceFacadeSL.setAgregaReferenciasFrecuentesTiempoAire(frecuentesTiempoAireRequestTO);
				numerosFrecuentesTiempoAireTO.setColeccion_frecuentes(createCollectionFromMap(frecuentesTiempoAireResponseTO.getTelefonosFrecuentes(),forma.getCompania()));			
			}			
			response.addAttribute(numerosFrecuentesTiempoAireTO);
			
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);
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
