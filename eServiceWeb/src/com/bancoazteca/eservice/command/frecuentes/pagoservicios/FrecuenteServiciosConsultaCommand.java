package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Servicio_ParametrizadoTO;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Servicio_TelmexTO;
import com.bancoazteca.eservice.command.base.beans.Frecuentes_Pago_ServiciosTO;
import com.bancoazteca.eservice.command.base.beans.Frecuentes_Pago_Servicios_TelmexTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.pagoservicios.iusacell.IusacellCommand;
import com.bancoazteca.eservice.command.pagoservicios.movistar.PagoServiciosMovistarCommand;
import com.bancoazteca.eservice.command.pagoservicios.parametrizable.ParametrizableCommand;
import com.bancoazteca.eservice.command.pagoservicios.sky.PagoServiciosSkyCommand;
import com.bancoazteca.eservice.command.pagoservicios.telmex.PagoServiciosTelmexCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class FrecuenteServiciosConsultaCommand extends CommandBase{

	public static final Logger log = Logger.getLogger(FrecuenteServiciosConsultaCommand.class);
	
	public Response ejecucion(Session session)throws Exception{
		FrecuenteServiciosConsultaForm forma = (FrecuenteServiciosConsultaForm)getFormBean();
		Response response=new Response();
		try{	
			FrecuentesResponseTO frecuentesResponseTO=SolicitudPagoServicios(forma.getTipo_servicio().toLowerCase(), session);
			
			Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
			Collection<Frecuente_ServicioTO> coleccion= new ArrayList <Frecuente_ServicioTO>();
			Frecuente_ServicioTO frecuente = null;
			for (FrecuentesResponseTO frecuenteResponse:frecuentesResponseTO.getFrecuentes()){
				frecuente= new Frecuente_ServicioTO();
				frecuente.setNumero_referencia(frecuenteResponse.getReferencia());
				if(frecuenteResponse.getStatus().equals("2")){
					frecuente.setEstatus("BLOQUEADA");
				}else{
					frecuente.setEstatus("DESBLOQUEADA");
				}
				coleccion.add(frecuente);
			}
			frecuentesPagoServicios.setTipo_servicio(forma.getTipo_servicio().toUpperCase());
			frecuentesPagoServicios.setColeccion_frecuentes(coleccion);
			response.addAttribute(frecuentesPagoServicios);

		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
		
	}

	public FrecuentesResponseTO SolicitudPagoServicios(String servicio, Session session) throws Exception{
		ParametrizableCommand comando = new ParametrizableCommand();
		session.addAttribute(REFERENCIA_FRECUENTE_SERVICIO, servicio);
		comando.solicitud(session);
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		frecuentesRequestTO.setUser(clienteTO.getUserName());
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		FrecuentesResponseTO frecuentesResponseTO = resourceFacadeSL.setInitialFrecuentesParametrizado(frecuentesRequestTO);
		session.addAttribute(USER_ID, frecuentesResponseTO.getUser_id());
		session.deleteAttribute(REFERENCIA_FRECUENTE_SERVICIO);
		return frecuentesResponseTO;
	}
	
	public Object respuesta(String servicio,FrecuentesResponseTO frecuentesResponseTO) throws Exception{
		if (servicio.equalsIgnoreCase("telmex__")){
			Frecuentes_Pago_Servicios_TelmexTO frecuentesPagoServicios= new Frecuentes_Pago_Servicios_TelmexTO();
			Collection<Frecuente_Servicio_TelmexTO> coleccion= new ArrayList <Frecuente_Servicio_TelmexTO>();
			for (FrecuentesResponseTO frecuenteResponse:frecuentesResponseTO.getFrecuentes()){
				Frecuente_Servicio_TelmexTO frecuente= new Frecuente_Servicio_TelmexTO();
				frecuente.setNumero_telefonico(frecuenteResponse.getReferencia());
				frecuente.setDigito_verificador(frecuenteResponse.getVerifyDigit());
				coleccion.add(frecuente);
			}
			frecuentesPagoServicios.setColeccion_frecuentes(coleccion);
			return frecuentesPagoServicios;
		}
		else{
			Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
			Collection<Frecuente_ServicioTO> coleccion= new ArrayList <Frecuente_ServicioTO>();
			for (FrecuentesResponseTO frecuenteResponse:frecuentesResponseTO.getFrecuentes()){
				Frecuente_ServicioTO frecuente= new Frecuente_ServicioTO();
				frecuente.setNumero_referencia(frecuenteResponse.getReferencia());
				coleccion.add(frecuente);
			}
			frecuentesPagoServicios.setTipo_servicio(servicio.toUpperCase());
			frecuentesPagoServicios.setColeccion_frecuentes(coleccion);
			return frecuentesPagoServicios;
		}	
	}
}
