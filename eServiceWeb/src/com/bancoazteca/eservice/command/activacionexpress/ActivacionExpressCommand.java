package com.bancoazteca.eservice.command.activacionexpress;

import com.bancoazteca.elite.beans.ActivarCuentaExpressRequestTO;
import com.bancoazteca.elite.beans.ActivarCuentaExpressResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Activacion_Express_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.Activacion_ExpressTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ActivacionExpressCommand extends CommandBase {
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();

		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ActivarCuentaExpressRequestTO activarCuentaTO = new ActivarCuentaExpressRequestTO();
						
			String identificadorSesion=String.valueOf(System.currentTimeMillis());
			int longitud = identificadorSesion.length();
			identificadorSesion =  identificadorSesion.substring((longitud-5), longitud);
			
			int extra=(int)(Math.random()*100);
			
			String idIdentificador =  String.valueOf(identificadorSesion+extra);
			System.out.println("idIdentificador: " + idIdentificador);
			activarCuentaTO.setUser(idIdentificador);
			
			ActivarCuentaExpressResponseTO expressResponseTO = resourceFacadeSL.activacionExpressInicio(activarCuentaTO);
			
			HuellaTO huellaTO = new HuellaTO();
			
			huellaTO.setLlave_publica(expressResponseTO.getLlavePublica());
			huellaTO.setLongitud_huella(expressResponseTO.getLongitudHuella());
			
			Activacion_ExpressTO activacion_ExpressTO = new Activacion_ExpressTO();
			activacion_ExpressTO.setHuella(huellaTO);
			activacion_ExpressTO.setIdentificador_sesion(idIdentificador);
			
			response.addAttribute(activacion_ExpressTO);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
						
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {	
		Response response = new Response();

		try {
			ActivacionExpressForm forma = (ActivacionExpressForm) getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			Activacion_Express_EjecucionTO activacionExpressEjecucion = new Activacion_Express_EjecucionTO();		
			ActivarCuentaExpressRequestTO activarCuentaTO = new ActivarCuentaExpressRequestTO();
			activarCuentaTO.setTarjetaCuenta(forma.getNumero_tarjeta());
			activarCuentaTO.setOperacion(forma.getOperacion());
			activarCuentaTO.setClave(forma.getHuella_seguridad());
			activarCuentaTO.setAlias(forma.getAlias());
			activarCuentaTO.setCelular(forma.getNumero_celular());
			activarCuentaTO.setCompania(forma.getCompania_celular());
			activarCuentaTO.setContrasena(forma.getContrasenia());
			activarCuentaTO.setContrasena_confirmacion(forma.getConfirmacion_contrasenia());
			activarCuentaTO.setCorreo(forma.getCorreo_electronico());
			activarCuentaTO.setNumeroToken(forma.getNumero_token());
			activarCuentaTO.setNumeroToken_confirmacion(forma.getNumero_token_confirmacion());
			String[] mailUserDominio = forma.getCorreo_electronico().split("@");
			activarCuentaTO.setCorreo(forma.getCorreo_electronico());
			activarCuentaTO.setMailUser(mailUserDominio[0]);
			activarCuentaTO.setMailDominio(mailUserDominio[1]);
			
			activarCuentaTO.setUser(forma.getIdentificador_sesion());
				
			ActivarCuentaExpressResponseTO expressResponseTO = resourceFacadeSL.activacionExpressValidacion(activarCuentaTO);
			activacionExpressEjecucion.setEstatus_activacion(expressResponseTO.getStatusRespuesta());

			response.addAttribute(activacionExpressEjecucion);
		} catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public static void main(String[] args) {
		
		
		
	}
	
}
