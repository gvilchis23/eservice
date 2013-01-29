package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Servicio_ParametrizadoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class FrecuenteServiciosAgregaCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(FrecuenteServiciosAgregaCommand.class);
	
	public Response validacion(Session session)throws Exception{

		Response response = new Response();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		Frecuente_Servicio_ParametrizadoTO frecuente = new Frecuente_Servicio_ParametrizadoTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		try{
			FrecuenteServiciosAgregaForm forma = (FrecuenteServiciosAgregaForm)getFormBean();
			String userId = (String)session.getAttribute(USER_ID);
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			
			//obtenemos del properties el idServicio.
			PropertiesService propertiesService = PropertiesService.getInstance();
			String idServicio = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO + forma.getTipo_servicio().toLowerCase() );
			
			frecuentesRequestTO.setUser(clienteTO.getUserName());	
			frecuentesRequestTO.setPaso("1");
			frecuentesRequestTO.setReferencia("");
			frecuentesRequestTO.setServicio(idServicio);
			frecuentesRequestTO.setUserId(userId);
			frecuentesRequestTO.setMethod("verificaNueva");

			//Obtenemos el tipo de referencia 
			String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + forma.getTipo_servicio().toLowerCase() );
			frecuentesRequestTO.setTipoServicio(tipoReferencia);
			frecuentesRequestTO.setReferenciaServicio(forma.getNumero_referencia());
					
			frecuentesResponseTO = resourceFacadeSL.setAgregarFrecuenteParametrizado(frecuentesRequestTO);
			
			frecuente.setTipo_servicio(forma.getTipo_servicio());
			frecuente.setNumero_referencia(forma.getNumero_referencia());
			
			HuellaTO huellaTO = new HuellaTO();
			DispositivoHuellaTO dispositivoHuellaTO = frecuentesResponseTO.getDispositivoHuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}
			
			response.addAttribute(huellaTO);
			
			session.addAttribute(REFERENCIA_FRECUENTE_ACTIVAR, forma.getNumero_referencia());
			response.addAttribute(frecuente);
			
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}		
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		
		Response response = new Response();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		Frecuente_Servicio_ParametrizadoTO frecuente = new Frecuente_Servicio_ParametrizadoTO();
		
		try{
			FrecuenteServiciosAgregaForm forma = (FrecuenteServiciosAgregaForm)getFormBean();
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();			
			frecuentesRequestTO.setUser(clienteTO.getUserName());
			
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				frecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
				frecuentesRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				frecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
				frecuentesRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}
			
			frecuentesRequestTO.setMethod("agrega");			
			resourceFacadeSL.setAgregarFrecuenteEjecucionParametrizado(frecuentesRequestTO);
			session.deleteAttribute(USER_ID);	
			frecuente.setTipo_servicio(forma.getTipo_servicio());
			frecuente.setNumero_referencia((String)session.getAttribute(REFERENCIA_FRECUENTE_ACTIVAR));
			session.deleteAttribute(REFERENCIA_FRECUENTE_ACTIVAR);
			response.addAttribute(frecuente);
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		
		return response;
	}
}