package com.bancoazteca.eservice.command.base;

import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public final class SessionManagerUtil  {

	private static final String MENSAJE_EXCEPCION="El response es una instancia nula. Inicialize el Response antes de invocar";
	
	public static boolean isAplicacionInvalida(FormBean forma) {
		String validationIdApplication = Session.getSessionApplication(forma.getIdaplicacion().toString());
		if(!validationIdApplication.equals(CommandBase.ID_APPLICATION_VALIDA)){
			return true;
		}
		return false;
	}
	
	public static Response generaErrorAppInvalida(FormBean forma){
		Response response=new Response();
		String validationIdApplication = Session.getSessionApplication(forma.getIdaplicacion().toString());
		if(validationIdApplication.equals(CommandBase.ID_APPLICATION_INCORRECTA)){
			response.setStatus(Errors.SESSION_APPLICATION_NOT_FOUND_CODE, "Sesión de aplicación incorrecta", null);
		}else if(validationIdApplication.equals(CommandBase.ID_APPLICATION_EXPIRADA)){
			response.setStatus(Errors.SESSION_APPLICATION_EXPIRED, "Sesión de aplicación expirada", null);
		}
		
		return response;
	}
	
	
	public static Response regeneraIdSession(String idSession, Response response,String usuario){
		Session session = SessionManager.getInstance().getSession(idSession);
		response.setIdSession(SessionManager.getInstance().generateNewSessionId(session,usuario));
		return response;
	}
	
	public static Session crearSession(FormBean forma, Response response,String usuario){
		if(response==null){
			throw new IllegalStateException(MENSAJE_EXCEPCION);
		}
		Session session = Session.create(forma.getIdaplicacion().toString(),usuario);
		response.setIdSession(session);
		return session;
	}
	
	public static Session recuperaSession(FormBean forma){
		Session session=null;
		try{
			session = SessionManager.getInstance().getSession(forma.getIdsesion().toString());
		}catch(Exception e){
			e.printStackTrace();
			session=null;
		}
		return session;
	}
	
	public static boolean isInvalidSession(Session session){
		
		if(session!=null){
			if(session.isTimeValidateApplicationSession() && session.isFoundApplicationSession() ){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	
	public static Response generaErrorSessionInvalida(Session session){
		Response response=new Response();
		if (session.getIdSession() != null) {
			if (!session.isFoundApplicationSession()) {
				response.setStatus(Errors.SESSION_APPLICATION_NOT_FOUND_CODE,"Sesión de aplicación incorrecta", null);
			} else{
				if (!session.isTimeValidateApplicationSession()) {
					response.setStatus( Errors.SESSION_APPLICATION_EXPIRED,"Sesión de aplicación expirada", null);
				}
			} 
		} else {
			response = new Response();
			response.setStatus(Errors.SESSION_USER_NOT_FOUND_CODE, "Sesión de cliente incorrecta", null);
		}
		return response;
	}
	
	
	public static String getIdSession(FormBean forma){
		String idSession=forma.getIdsesion().toString();
		return idSession;
	}
	
	
	public static Response regeneraIdSession(FormBean forma,Response response,String usuario){
		String idSession=getIdSession(forma);
		response=regeneraIdSession(idSession, response,usuario);
		return response;
	}
	
	public static Session invalidaSessionUsuario(FormBean forma){
		Session session=SessionManagerUtil.recuperaSession(forma);
		SessionManager.getInstance().invalidate(session.getIdSession(), SessionManager.INVALIDATE_IDSESSION);
		return session;
	}
	
}
