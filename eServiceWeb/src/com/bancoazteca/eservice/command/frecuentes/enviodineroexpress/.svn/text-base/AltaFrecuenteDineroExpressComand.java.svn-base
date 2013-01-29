package com.bancoazteca.eservice.command.frecuentes.enviodineroexpress;

import com.bancoazteca.elite.beans.BeneficiarioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Beneficiario_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AltaFrecuenteDineroExpressComand extends CommandBase{
	
	 public Response validacion(Session session) throws Exception{
		 
		Response response = new Response();
		HuellaTO huellaTO = new HuellaTO();
		BeneficiarioDineroExpressRequestTO requestTO = new BeneficiarioDineroExpressRequestTO();

		BeneficiarioDineroExpressResponseTO responseTO = null;
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			AltaFrecuenteDineroExpressForm frecuentesForm = (AltaFrecuenteDineroExpressForm) getFormBean();
			Beneficiario_Dinero_ExpressTO beneficiario    = new Beneficiario_Dinero_ExpressTO();
			beneficiario.setApellido_materno( frecuentesForm.getApellido_materno() );
			beneficiario.setApellido_paterno( frecuentesForm.getApellido_paterno() );
			beneficiario.setFecha_nacimiento( frecuentesForm.getFecha_nacimiento() );
			beneficiario.setNombre_beneficiario( frecuentesForm.getNombre_beneficiario() );
			beneficiario.setNombre_corto(frecuentesForm.getNombre_corto());
			
			requestTO.setApellidoMaterno( frecuentesForm.getApellido_materno() );
			requestTO.setApellidoPaterno( frecuentesForm.getApellido_paterno() );
			requestTO.setFechaNacimiento( frecuentesForm.getFecha_nacimiento() );
			requestTO.setNombrecorto(frecuentesForm.getNombre_corto());
			requestTO.setNombreBeneficiario( frecuentesForm.getNombre_beneficiario() );
			
			requestTO.setOpcionSeguridad(OPCION_TOKEN);
			requestTO.setOptionDispositive(OPCION_TOKEN);
			requestTO.setUser( clienteTO.getUserName());


			requestTO.setMethod( "confirmar" );
	
			responseTO = resourceFacadeSL.setDataEnvioDineroExpressAltaFrecuente( requestTO );
		    
			if(responseTO.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica( responseTO.getDispositivoHuellaTO().getLlavePublica() );
				huellaTO.setLongitud_huella( responseTO.getDispositivoHuellaTO().getLongitudHuella() );
			}
	
			session.addAttribute("Beneficiario_Dinero_ExpressTO", beneficiario);
			response.addAttribute(beneficiario);
			synchronized(session){
				session.addAttribute(DINERO_EXPRESS_FRECUENTE_RESPONSE, responseTO );
			}
			response.addAttribute(huellaTO);


		} catch (EliteDataException e) {					
			buildErrorResponse(e, response);
		}		
		 return response;
	 }
	 
	 public Response ejecucion(Session session) throws Exception{
	 
		Response response = new Response();
		
		AltaFrecuenteDineroExpressForm forma = ( AltaFrecuenteDineroExpressForm )getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute( CLIENTE_TO );
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
	
			BeneficiarioDineroExpressResponseTO responseTO = ( BeneficiarioDineroExpressResponseTO ) session.getAttribute(DINERO_EXPRESS_FRECUENTE_RESPONSE );
			BeneficiarioDineroExpressRequestTO requestTO = new BeneficiarioDineroExpressRequestTO();
		    Beneficiario_Dinero_ExpressTO beneficiario= ( Beneficiario_Dinero_ExpressTO ) session.getAttribute( "Beneficiario_Dinero_ExpressTO" );
	
		    requestTO.setUser( clienteTO.getUserName() );
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				requestTO.setClave(forma.getHuella_seguridad().toString());
				requestTO.setOptionDispositive(OPCION_HUELLA);
			}
			else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				requestTO.setTokenCode(forma.getClave_seguridad().toString());
				requestTO.setOptionDispositive(OPCION_TOKEN);
			}
	
		    responseTO = resourceFacadeSL.setEnvioDineroExpressAltaFrecuenteEjecutar( requestTO );
		    
		    session.addAttribute(ConsultaFrecuenteDineroExpressComand.BENEFICIARIOS_DEX,responseTO.getBeneficiarios());
		    
		    
	        response.addAttribute(beneficiario);
	        
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}		
		return response;
	 }

}
