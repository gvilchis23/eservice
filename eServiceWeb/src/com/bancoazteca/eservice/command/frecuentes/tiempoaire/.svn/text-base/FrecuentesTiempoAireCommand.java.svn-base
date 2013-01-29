package com.bancoazteca.eservice.command.frecuentes.tiempoaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireRequestTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosException;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.beans.Frecuentes_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.beans.ResponseTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;



public class FrecuentesTiempoAireCommand extends CommandBase {
	
	
	public Response ejecucion(Session session) throws PagoServiciosException, SessionExpiredException, ServiceLocatorException
	{
		Response response=new Response();
		FrecuentesTiempoAireForm forma=(FrecuentesTiempoAireForm) getFormBean();
		
		try{	
			
			ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			PagoServiciosTiempoAireRequestTO requestTO = new PagoServiciosTiempoAireRequestTO();
			requestTO.setUser(clienteTO.getUserName());	
			requestTO.setCarrier(forma.getCompania());
			resourceFacadeSL.setMenuTiempoAirePayment(requestTO);
			requestTO.setCarrier(forma.getCompania().toLowerCase());

			if(forma.getIdservicio().toLowerCase().contains("mostrar"))
			{

				Frecuentes_Tiempo_AireTO numerosFrecuentesTiempoAireTO=new Frecuentes_Tiempo_AireTO();
				Collection<Frecuente_Tiempo_AireTO> frecuentesTiempoAireTOCollection=new ArrayList<Frecuente_Tiempo_AireTO>();
				
				if(requestTO.getCarrier().equalsIgnoreCase("TODAS")){
					requestTO.setCarrier("telcel");
					resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
					frecuentesTiempoAireTOCollection=mostrar(session,"telcel");
					requestTO.setCarrier("movistar");
					resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
					frecuentesTiempoAireTOCollection.addAll(mostrar(session,"movistar"));
					requestTO.setCarrier("iusacell");
					resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
					frecuentesTiempoAireTOCollection.addAll(mostrar(session,"iusacell"));
					requestTO.setCarrier("unefon");
					resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
					frecuentesTiempoAireTOCollection.addAll(mostrar(session,"unefon"));
					
				}else{
					resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
					frecuentesTiempoAireTOCollection=mostrar(session,requestTO.getCarrier());
				}
				
				numerosFrecuentesTiempoAireTO.setColeccion_frecuentes(frecuentesTiempoAireTOCollection);
				response.addAttribute(numerosFrecuentesTiempoAireTO);
				
			}
					
		}catch (EliteDataException e) {
				buildErrorResponse(e, response);
			}
		
		
		return response;
	}
	
	private int indice(String numeroAbuscar,Session session, String carrier,PagoServiciosTiempoAireRequestTO requestTO,FrecuentesTiempoAireForm forma)throws PagoServiciosException, SessionExpiredException, ServiceLocatorException{
		//busca el indice que le corresponde al numero enviado
		Integer index=0;
		try{
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		resourceFacadeSL.setInitialTiempoAirePayment(requestTO);
		Collection<Frecuente_Tiempo_AireTO> regresa = new ArrayList<Frecuente_Tiempo_AireTO>();
		regresa=mostrar(session,carrier);
		Iterator iterator = regresa.iterator();
		Integer inc=0;
		while(iterator.hasNext()){
			Frecuente_Tiempo_AireTO telefono = (Frecuente_Tiempo_AireTO)iterator.next();
			if(telefono.getNumero_telefonico().equals(numeroAbuscar)){
				index=inc;
			}
			inc++;
		}
		}catch (EliteDataException e) {
			
		}
		return index;
	}
	
	private Collection  createCollectionFromMap2(Collection<Map> mapaTelefonos,String compania){
		Iterator<Map> iterator = mapaTelefonos.iterator();
		Collection<Frecuente_Tiempo_AireTO> telefonosFrecuentes = new ArrayList<Frecuente_Tiempo_AireTO>();
		while(iterator.hasNext()){
			Map telefono = (Map)iterator.next();
			Frecuente_Tiempo_AireTO temp = new Frecuente_Tiempo_AireTO();
			temp.setNumero_telefonico((String)telefono.get("telefono"));
			temp.setAlias((String)telefono.get("referencia"));
			temp.setCompania(compania);
			telefonosFrecuentes.add(temp);
		}
		return telefonosFrecuentes;
	}
	
	private Collection<Frecuente_Tiempo_AireTO> mostrar(Session session, String carrier) throws PagoServiciosException, SessionExpiredException, ServiceLocatorException, EliteDataException
	{
		Response response=new Response();	
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
		FrecuentesTiempoAireForm forma=(FrecuentesTiempoAireForm) getFormBean();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		frecuentesRequestTO.setUser(clienteTO.getUserName());
		FrecuentesResponseTO frecuentesResponseTO = resourceFacadeSL.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
		Collection<FrecuentesResponseTO> colletionFrecuentes=createCollectionFromMap(frecuentesResponseTO.getTelefonosFrecuentes());
		synchronized(session){
			session.addAttribute(CommandConstantes.PAGO_SERVICIO_TIEMPO_AIRE_FRECUENTES_RESPONSE, frecuentesResponseTO);
		}
		
		ResponseTO responseTO=new ResponseTO();
		Collection<Frecuente_Tiempo_AireTO> frecuentesTiempoAireTOCollection=new ArrayList<Frecuente_Tiempo_AireTO>();
		Frecuentes_Tiempo_AireTO numerosFrecuentesTiempoAireTO=new Frecuentes_Tiempo_AireTO();
		Frecuente_Tiempo_AireTO frecuenteTiempoAireTO;
		
		for(FrecuentesResponseTO frecuentesResponseTOtemp: colletionFrecuentes)
		{
			frecuenteTiempoAireTO=new Frecuente_Tiempo_AireTO();
			frecuenteTiempoAireTO.setAlias(frecuentesResponseTOtemp.getReferencia());
			frecuenteTiempoAireTO.setNumero_telefonico(frecuentesResponseTOtemp.getTelefono());
			frecuenteTiempoAireTO.setCompania(carrier.toLowerCase());
			frecuentesTiempoAireTOCollection.add(frecuenteTiempoAireTO);
		}
		
		numerosFrecuentesTiempoAireTO.setColeccion_frecuentes(frecuentesTiempoAireTOCollection);

		response.addAttribute(numerosFrecuentesTiempoAireTO);

		return frecuentesTiempoAireTOCollection;
	}
	
	
	
	private Collection<FrecuentesResponseTO> createCollectionFromMap(Collection<Map> mapaTelefonos){
		Iterator<Map> iterator = mapaTelefonos.iterator();
		Collection<FrecuentesResponseTO> telefonosFrecuentes = new ArrayList<FrecuentesResponseTO>();
		while(iterator.hasNext()){
			Map telefono = (Map)iterator.next();
			FrecuentesResponseTO temp = new FrecuentesResponseTO();
			temp.setReferencia((String)telefono.get("referencia"));
			temp.setTelefono((String)telefono.get("telefono"));
			telefonosFrecuentes.add(temp);
		}
		return telefonosFrecuentes;
	}
}