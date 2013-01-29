package com.bancoazteca.eservice.command.recuperar_password;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.RecuperaPasswordRequestTO;
import com.bancoazteca.elite.beans.RecuperaPasswordResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.SessionManagerUtil;
import com.bancoazteca.eservice.command.base.beans.Confirmacion_datos_clienteTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;


public class Recupera_PasswordCommand extends CommandBase {
	
	private static final Logger log= Logger.getLogger(Recupera_PasswordCommand.class);
	private static final String DATOS_CUENTA="datos_cuenta";
	private static final String TARJETA_CUENTA="tarjeta_cuenta";
	
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response(); 
		log.info("Por verificar el login de aplicacion");
		RecuperaPasswordForm forma = (RecuperaPasswordForm)getFormBean();
		
		if(SessionManagerUtil.isAplicacionInvalida(forma)){
			log.info("El id de aplicacion no es valido, realize el login de aplicacion");
			return SessionManagerUtil.generaErrorAppInvalida(forma);
		}
		session=SessionManagerUtil.crearSession(forma,response,forma.getTarjeta_cuenta());
		
		RecuperaPasswordRequestTO recuperaPasswordTO = new RecuperaPasswordRequestTO();
		RecuperaPasswordResponseTO passwordResponseTO = new RecuperaPasswordResponseTO();
		
		recuperaPasswordTO.setTarjetaCuenta(forma.getTarjeta_cuenta());
		
		
		passwordResponseTO=(RecuperaPasswordResponseTO)getDelegate().findAccountOrCreditCardNumber(recuperaPasswordTO);
		
		session.addAttribute(DATOS_CUENTA,passwordResponseTO);
		session.addAttribute(TARJETA_CUENTA, forma.getTarjeta_cuenta());
		
		return response;
	} 
	
	
	
	
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		
		RecuperaPasswordForm forma = (RecuperaPasswordForm)getFormBean();
		ResourceFacadeSL facadeSL = getDelegate();
		
		session=SessionManagerUtil.recuperaSession(forma);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		
		
		RecuperaPasswordRequestTO recuperaPasswordTO =new RecuperaPasswordRequestTO();
		RecuperaPasswordResponseTO passwordResponseTO =new RecuperaPasswordResponseTO();
		String tarjetaNumero=(String)session.getAttribute(TARJETA_CUENTA);
		
		
		try
		{
			
			if(forma.getFecha_nacimiento() != null )
			{	
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
		        try {
		        	Date fecha_nac = new Date();
					fecha_nac = formatoFecha.parse(forma.getFecha_nacimiento());
			        String fechaFormateada = formatoFecha.format(fecha_nac);
			        
			        StringTokenizer token=new StringTokenizer(fechaFormateada,"-");
			        
			        int dia=Integer.parseInt(token.nextToken());
			        forma.setDia_nacimiento(String.valueOf(dia));
			        
			        int mes = Integer.parseInt(token.nextToken());
			        forma.setMes_nacimiento(String.valueOf(mes - 1));
			        
			        forma.setAnio_nacimiento(token.nextToken());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			recuperaPasswordTO.setNip(forma.getNip().toString());
			recuperaPasswordTO.setConfirmacionNip(forma.getConfirmacion_nip().toString());
			recuperaPasswordTO.setDiaNacimiento(forma.getDia_nacimiento());
			recuperaPasswordTO.setMesNacimiento(forma.getMes_nacimiento());
			recuperaPasswordTO.setAnioNacimiento(forma.getAnio_nacimiento());
			recuperaPasswordTO.setNombre(forma.getNombres());
			recuperaPasswordTO.setPaterno(forma.getApellido_paterno());
			recuperaPasswordTO.setMaterno(forma.getApellido_materno());
			recuperaPasswordTO.setTarjetaCuenta(tarjetaNumero);
			
			
			passwordResponseTO = facadeSL.findPersonalData(recuperaPasswordTO);			
			
			Confirmacion_datos_clienteTO confirmacion_datosTO = new Confirmacion_datos_clienteTO();
			confirmacion_datosTO.setUsuario(passwordResponseTO.getAlias());
			confirmacion_datosTO.setCorreo_electronico(passwordResponseTO.getCorreo());
			confirmacion_datosTO.setNumero_celular(passwordResponseTO.getCelular());
			confirmacion_datosTO.setCompania_celular(passwordResponseTO.getCompania());
			confirmacion_datosTO.setColeccion_companias(passwordResponseTO.getListaCompanias());
			
			session.addAttribute(DATOS_CUENTA,confirmacion_datosTO);
			
			
			response.addAttribute(confirmacion_datosTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}	
		
		return SessionManagerUtil.regeneraIdSession(forma, response,tarjetaNumero);
	} 
	
	public Response actualizacion(Session session) throws Exception {
		Response response = new Response();
		
		RecuperaPasswordForm forma = (RecuperaPasswordForm)getFormBean();
		ResourceFacadeSL facadeSL = getDelegate();
		RecuperaPasswordRequestTO recuperaPasswordTO = new RecuperaPasswordRequestTO();

		session=SessionManagerUtil.recuperaSession(forma);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}		
		
		Confirmacion_datos_clienteTO confirmacion_datosTO=(Confirmacion_datos_clienteTO)session.getAttribute(DATOS_CUENTA);
		ArrayList<String> carriers=(ArrayList<String>)confirmacion_datosTO.getColeccion_companias();
		String carrieSeleccionada=forma.getCompania_celular();
		boolean carrieValida=false;
		for (String carrie: carriers) {
			if(carrieSeleccionada.equalsIgnoreCase(carrie)){
				carrieValida=true;
				break;
			}
		}
		if(!carrieValida){
			response = new Response();
			response.setStatus(Errors.VALIDATION_CODE, "Debe seleccionar una compania de telefonia valida.", null);
			return response;
		}
		
		String cuenta=(String)session.getAttribute(TARJETA_CUENTA);
		
		try
		{
						
			recuperaPasswordTO.setCorreoElectronico(forma.getCorreo_electronico());
			recuperaPasswordTO.setNumeroCelular(forma.getNumero_celular());
			recuperaPasswordTO.setCompaniaCelular(forma.getCompania_celular());
			recuperaPasswordTO.setTarjetaCuenta(cuenta);
			
			
			facadeSL.findChangePersonalData(recuperaPasswordTO);
							
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}	
		
		return SessionManagerUtil.regeneraIdSession(forma, response,cuenta);
	} 
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		RecuperaPasswordForm forma = (RecuperaPasswordForm)getFormBean();
		ResourceFacadeSL facadeSL = getDelegate();
		RecuperaPasswordRequestTO recuperaPasswordTO = new RecuperaPasswordRequestTO();
		
		session=SessionManagerUtil.recuperaSession(forma);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		
		String cuenta=(String)session.getAttribute(TARJETA_CUENTA);
		
		try{
			recuperaPasswordTO.setPassword(forma.getNuevo_password().toString());
			recuperaPasswordTO.setConfirmacionPassword(forma.getConfirmacion_nuevo_password().toString());
			recuperaPasswordTO.setTarjetaCuenta(cuenta);
			facadeSL.updateToNewPassword(recuperaPasswordTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}	
		
		SessionManagerUtil.invalidaSessionUsuario(forma);
		
		return response;
	} 
	
	
	public MensajesTO FormateoContrasenia(Response response, RecuperaPasswordForm recuperaPasswordForm ) throws Exception {
		RecuperaPasswordRequestTO recuperaUsuarioPasswordTO = new RecuperaPasswordRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		MensajesTO mensajesTO = new MensajesTO();
		try {
			recuperaUsuarioPasswordTO.setPassword(recuperaPasswordForm.getNuevo_password().toString());
			facadeSL.updateToNewPassword(recuperaUsuarioPasswordTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return mensajesTO;
	}
}
