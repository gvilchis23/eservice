package com.bancoazteca.eservice.command.prueba;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.LEVEL_WEB_SERVICES;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.ejb.inversiones.utils.InversionRule;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PruebaCommand extends CommandBase {
	
	public Response retencion(Session session)throws Exception{
		Response response = new Response();
		PruebaForm pruebaForm = (PruebaForm) getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		B706_RegistroRetencionRequestTO registroRetencionRequestTO=new B706_RegistroRetencionRequestTO();
		registroRetencionRequestTO.setCodigoCuentaCliente(pruebaForm.getNumeroCuenta());
		registroRetencionRequestTO.setDiasRetencion("3");
		registroRetencionRequestTO.setMonto(InversionRule.formateaMontoRetencion(pruebaForm.getMonto()));
		registroRetencionRequestTO.setPortal("Banca Elite");
		registroRetencionRequestTO.setIdAlnova(clienteTO.getNumero());
		
		try{
			ResourceFacadeSegundoSL facade = getDelegateSegundo();

			B706_RegistroRetencionResponseTO retencionAlnovaResponseTO=facade.retencion_alnova(registroRetencionRequestTO);
			String mensajeAlnova=retencionAlnovaResponseTO.getMensaje();
			System.out.println("Numero Retencion: "+retencionAlnovaResponseTO.getNumeroRetencion());
			if(mensajeAlnova.toLowerCase().contains("error")){
				String codigo = Formatter.getCodigoErrorAlnova(mensajeAlnova);
				System.out.println("Error alnova: " + codigo);			
				InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
				exceptionTO.setLevel(LEVEL_WEB_SERVICES);
				exceptionTO.setMessage("Lo sentimos, ha ocurrido un error en el proceso. "+codigo);
				throw new EliteDataException("error_retencion", null, 1 );
			}
					
			response.addAttribute(retencionAlnovaResponseTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response quita_retencion(Session session)throws Exception{
		Response response = new Response();
		PruebaForm pruebaForm = (PruebaForm) getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);

		B706_RegistroRetencionRequestTO registroRetencionRequestTO=new B706_RegistroRetencionRequestTO();
		registroRetencionRequestTO.setCodigoCuentaCliente("");
		registroRetencionRequestTO.setDiasRetencion("3");
		registroRetencionRequestTO.setMonto(InversionRule.formateaMontoRetencion(pruebaForm.getMonto()));
		registroRetencionRequestTO.setPortal("Banca Elite");
		registroRetencionRequestTO.setIdAlnova(clienteTO.getNumero());
		
		ResourceFacadeSegundoSL facade = getDelegateSegundo();

		B756_QuitarRetencionRequest requestRetencion = new B756_QuitarRetencionRequest();
		requestRetencion.setCodigoCuentaCliente(pruebaForm.getNumeroCuenta());
		requestRetencion.setCodigoMonedaExtranjera("MXP");
		requestRetencion.setFree("");
		requestRetencion.setMonto(InversionRule.formateaMontoRetencion(pruebaForm.getMonto()));
		requestRetencion.setNumeroRetencion(pruebaForm.getFolio_retencion());
		requestRetencion.setPapel("S");
		requestRetencion.setRemueveRetencionParcialTotal("N");

		B756_QuitarRetencionResponse quitarRetencionResponse= facade.quitar_retencion(requestRetencion,clienteTO.getNumero(),"Banca Elite");

		System.out.println("Retencion "+ pruebaForm.getFolio_retencion() +" status");
		System.out.println("Codigo Mensaje: "+quitarRetencionResponse.getCodigoMensaje());
		System.out.println("Mensaje: "+quitarRetencionResponse.getMensaje());
		System.out.println("Mensaje Real: "+quitarRetencionResponse.getMensajeReal());
		
		response.addAttribute(quitarRetencionResponse);
		
		return response;
	}
	
	public Response actualiza_saldo(Session session)throws Exception{
		Response response = new Response();
		PruebaForm pruebaForm = (PruebaForm) getFormBean();
		
		try{
			updateBalance(session, pruebaForm.getNumeroCuenta());
			
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			
			response.addAttribute(clienteTO);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
				
		return response;
	}

}
