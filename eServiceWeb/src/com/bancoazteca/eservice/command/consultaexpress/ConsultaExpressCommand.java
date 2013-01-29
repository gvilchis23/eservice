package com.bancoazteca.eservice.command.consultaexpress;



import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConsultaExpressRequestTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.SessionManagerUtil;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConsultaExpressCommand extends CommandBase{
	
	private static final Logger log = Logger.getLogger(ConsultaExpressCommand.class);
	
	public Response solicitud(Session session) throws Exception{
		log.info("metodo solicitud Consulta Express");
		Response response = new Response();
		ConsultaExpressForm form = (ConsultaExpressForm) getFormBean();
		ConsultaExpressRequestTO request = new ConsultaExpressRequestTO();
		
		if(SessionManagerUtil.isAplicacionInvalida(form)){
			log.info("El id de aplicacion no es valido, realize el login de aplicacion");
			return SessionManagerUtil.generaErrorAppInvalida(form);
		}
		session=SessionManagerUtil.crearSession(form,response,form.getCuenta());
		request.setTarjetaCuenta(form.getCuenta());
		request.setOperacion(form.getAction());
		
		
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.consultarCuentaExpress(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		session.addAttribute("consultaExpress",request);
		return response;
		
		
	}
	
	public Response validacion(Session session) throws Exception{
		log.info("metodo validacion Consulta Express");
		Response response = new Response();
		ConsultaExpressForm form = (ConsultaExpressForm) getFormBean();
		
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		ConsultaExpressRequestTO request = (ConsultaExpressRequestTO) session.getAttribute("consultaExpress");
		
		
		if( form.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ) {
				request.setOptionDispositive( OPCION_TOKEN );
				request.setTokencode( form.getClave_seguridad().toString() );
			} else if (form.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ) {
				request.setOptionDispositive( OPCION_HUELLA );
				request.setClave( form.getHuella_seguridad().toString() );
			}else if (form.getOpcion_seguridad().equalsIgnoreCase( OPCION_NIP ) ) {
				request.setOptionDispositive( OPCION_NIP );
				request.setNip( form.getNip().toString() );
			}
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.validarCuentaExpress(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		session.addAttribute("consultaExpress",request);
		return response;
		
		
	}
	
	public Response ejecucion(Session session) throws Exception{
		log.info("metodo ejecucion Consulta Express");
		Response response = new Response();
		ConsultaExpressForm form = (ConsultaExpressForm) getFormBean();
		
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		ConsultaExpressRequestTO request = (ConsultaExpressRequestTO) session.getAttribute("consultaExpress");
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.getRecibosCuentaExpress(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		session.addAttribute("consultaExpress",request);
		return response;
		
		
	}
	
	
	
	
}
