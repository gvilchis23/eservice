package com.bancoazteca.eservice.command.usuarios.altas;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.AltasUsuariosRequestTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.SessionManagerUtil;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AltasUsuariosCommand extends CommandBase {
	
	private static final Logger log = Logger.getLogger(AltasUsuariosCommand.class);
	public Response solicitud(Session session) throws Exception{
		log.info("metodo solicitud Altas Usuarios");
		Response response = new Response();
		AltasUsuariosForm form = (AltasUsuariosForm) getFormBean();
		AltasUsuariosRequestTO request = new AltasUsuariosRequestTO();
		request.setCuenta_cargo(form.getCuenta_cargo());
		if(SessionManagerUtil.isAplicacionInvalida(form)){
			log.info("El id de aplicacion no es valido, realize el login de aplicacion");
			return SessionManagerUtil.generaErrorAppInvalida(form);
		}
		session=SessionManagerUtil.crearSession(form,response,form.getCuenta_cargo());
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.consultarCuentaActivar(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		session.addAttribute("altas",request);
		return response;
		
		
	}
	
	public Response validacion(Session session) throws Exception{
		log.info("metodo validacion Altas Usuarios");
		Response response = new Response();
		AltasUsuariosForm form = (AltasUsuariosForm) getFormBean();
		AltasUsuariosRequestTO request = new AltasUsuariosRequestTO();
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		
		int mes=Integer.parseInt(form.getMes())-1;
		int dia=Integer.parseInt(form.getDia());
		
		request= (AltasUsuariosRequestTO) session.getAttribute("altas");
		//request.setCuenta_cargo(form.getCuenta_cargo());
		request.setNip(form.getNip().toString());
		request.setConfirm_nip(form.getConfirma_nip().toString());
		request.setDia(Integer.toString(dia));
		request.setMes(Integer.toString(mes));
		request.setAño(form.getAnio());
		request.setNombre(form.getNombre());
		request.setApellido_paterno(form.getApellido_paterno());
		request.setApellido_materno(form.getApellido_materno());		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.validarDatosActivar(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		//session.addAttribute("Altas",responseTO);
		
		return response;
		
		
	}
	
	public Response contrato(Session session) throws Exception{
		log.info("metodo contrato Altas Usuarios");
		Response response = new Response();
		AltasUsuariosForm form = (AltasUsuariosForm) getFormBean();
		AltasUsuariosRequestTO request = new AltasUsuariosRequestTO();
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		request= (AltasUsuariosRequestTO) session.getAttribute("altas");
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.validarContrato(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		//session.addAttribute("Altas",responseTO);
		
		return response;
		
		
	}
	
	public Response registrar(Session session) throws Exception{
		log.info("metodo registrar Altas Usuarios");
		Response response = new Response();
		AltasUsuariosForm form = (AltasUsuariosForm) getFormBean();
		AltasUsuariosRequestTO request = new AltasUsuariosRequestTO();
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		request= (AltasUsuariosRequestTO) session.getAttribute("altas");
		request.setAlias(form.getAlias());
		request.setEmail(form.getCorreo_electronico());
		request.setPregunta(form.getPregunta());
		request.setRespuesta(form.getRespuesta());
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.consultarUsuarioDisponible(request);
			resourceFacadeSL.registrarUsuario(request);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		//session.addAttribute("Altas",responseTO);
		
		return response;
		
		
	}
	
	public Response ejecucion(Session session) throws Exception{
		log.info("metodo ejecutar Altas Usuarios");
		Response response = new Response();
		AltasUsuariosForm form = (AltasUsuariosForm) getFormBean();
		AltasUsuariosRequestTO request = new AltasUsuariosRequestTO();
		
		session=SessionManagerUtil.recuperaSession(form);
		if(SessionManagerUtil.isInvalidSession(session)){
			Response error=SessionManagerUtil.generaErrorSessionInvalida(session);
			return error;
		}
		request= (AltasUsuariosRequestTO) session.getAttribute("altas");
		request.setContrasena(form.getContrasenia());
		request.setConfirmarContrasena(form.getConfirmar_contrasenia());
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			resourceFacadeSL.activarUsuario(request);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		//session.addAttribute("Altas",responseTO);
		
		return response;
		
		
	}
	
	
	
	
	
	

}
