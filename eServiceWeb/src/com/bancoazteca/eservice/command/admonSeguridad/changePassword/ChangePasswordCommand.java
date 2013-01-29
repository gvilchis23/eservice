package com.bancoazteca.eservice.command.admonSeguridad.changePassword;
		
import com.bancoazteca.elite.beans.ChangePasswordResponseTO;
import com.bancoazteca.elite.beans.ChangePaswordRequestTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cambiar_ContrasenaTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ChangePasswordCommand extends CommandBase {
	
	public Response solicitud(Session session)throws Exception{
		Response response=new Response();
		ChangePaswordRequestTO cambiarContrasenaRequestTO = new ChangePaswordRequestTO();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			cambiarContrasenaRequestTO.setUser(clienteTO.getUserName());
			
			ChangePasswordResponseTO cambiarContrasenaResponseTO = resourceFacadeSL.getInitialCambiarContrasena(cambiarContrasenaRequestTO);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response validacion(Session session)throws Exception{
		Response response=new Response();
		ChangePaswordRequestTO cambiarContrasenaRequestTO = new ChangePaswordRequestTO();
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ChangePasswordForm actualizaContrasenaForm = (ChangePasswordForm)getFormBean();
			cambiarContrasenaRequestTO.setUser(clienteTO.getUserName());
					
			cambiarContrasenaRequestTO.setContrasenia_actual(actualizaContrasenaForm.getContrasenia_actual().toString());
			cambiarContrasenaRequestTO.setNueva_contrasenia(actualizaContrasenaForm.getNueva_contrasenia().toString());
			cambiarContrasenaRequestTO.setConfirmacion_nueva_contrasenia(actualizaContrasenaForm.getConfirmacion_nueva_contrasenia().toString());
			
			ChangePasswordResponseTO cambiarContrasenaResponseTO = resourceFacadeSL.changePassword(cambiarContrasenaRequestTO);
			
			HuellaTO huellaTO = new HuellaTO();
			DispositivoHuellaTO dispositivoHuellaTO = cambiarContrasenaResponseTO.getDispositivoHuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}
			
			response.addAttribute( huellaTO );
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		Response response=new Response();
		ChangePaswordRequestTO cambiarContrasenaRequestTO = new ChangePaswordRequestTO();
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ChangePasswordForm actualizaContrasenaForm = (ChangePasswordForm)getFormBean();
			cambiarContrasenaRequestTO.setUser(clienteTO.getUserName());
			
			if( actualizaContrasenaForm.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ) {
				cambiarContrasenaRequestTO.setOptionDispositive( OPCION_TOKEN );
				cambiarContrasenaRequestTO.setTokencode( actualizaContrasenaForm.getClave_seguridad().toString() );
			} else if (actualizaContrasenaForm.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ) {
				cambiarContrasenaRequestTO.setOptionDispositive( OPCION_HUELLA );
				cambiarContrasenaRequestTO.setClave( actualizaContrasenaForm.getHuella_seguridad().toString() );
			}
			
			cambiarContrasenaRequestTO.setTokencode(actualizaContrasenaForm.getClave_seguridad().toString());
		
			ChangePasswordResponseTO cambiarContrasenaResponseTO = resourceFacadeSL.getEjecutaCambiarContrasena(cambiarContrasenaRequestTO);
			
			Cambiar_ContrasenaTO cambiar_ContrasenaTO = new Cambiar_ContrasenaTO();
			cambiar_ContrasenaTO.setMensaje(cambiarContrasenaResponseTO.getMessaje());
			response.addAttribute(cambiar_ContrasenaTO);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;		
	}

}
