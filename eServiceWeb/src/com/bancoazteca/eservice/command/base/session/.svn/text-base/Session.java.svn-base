package com.bancoazteca.eservice.command.base.session;

import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.eservice.command.base.CommandConstantes;

public class Session  implements CommandConstantes{
	private final Map <String, Object> session;	
	private String idSession;
	private boolean timeValidateApplicationSession = true;
	private boolean isFoundApplicationSession = true;
	
	public boolean isTimeValidateApplicationSession() {
		return timeValidateApplicationSession;
	}

	public void setTimeValidateApplicationSession(
			boolean timeValidateApplicationSession) {
		this.timeValidateApplicationSession = timeValidateApplicationSession;
	}

	public Session(){
		session = new HashMap<String,Object>();
	}
	
	void setIdSession(String idSession) {
		this.idSession = idSession;
	}
	
	public void invalidate(){
		SessionManager.getInstance().invalidate(this.idSession, INVALIDATE_IDSESSION);
	}
	
	public void addAttribute(String key, Object value){
		deleteAttribute(key);
		session.put(key, value);		
	}
	
	public void deleteAttribute(String key){
		session.remove(key);		
	}
	
	public Object getAttribute(String key){
		return session.get(key);
	}
	
	public String getIdSession() {
		return idSession;
	}
	
	public static final Session create(String idSession,String usuario){
		return SessionManager.getInstance().createSession(idSession,usuario);
	}

	public static String getSessionApplication(String idApplication){
		return SessionManager.getInstance().getSessionApplication(idApplication);
	}

	public boolean isFoundApplicationSession() {
		return isFoundApplicationSession;
	}

	public void setFoundApplicationSession(boolean isFoundApplicationSession) {
		this.isFoundApplicationSession = isFoundApplicationSession;
	}

}
