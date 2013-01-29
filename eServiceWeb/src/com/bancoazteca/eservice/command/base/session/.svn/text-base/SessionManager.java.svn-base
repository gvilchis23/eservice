package com.bancoazteca.eservice.command.base.session;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandConstantes;

public class SessionManager implements CommandConstantes{
	
	private static final Logger log = Logger.getLogger(SessionManager.class);
	
	private final String SEPARATOR = "@@";
	private final String SEPARATOR_APPLICATIONSESSION ="_";
	public static SessionManager instance;
	private Map <String, Session> sessions;	
	private HashMap<String, String> users;
	private Map<String, Object> applicationSession;
	
	private Random random;
	
	private final int TIME_IDAPPLICATIONSESSION = 1;
	private final String PARAMETER_TIME_IDAPPLICATIONSESSION = "DATE"; //Posibles valores: DATE, HOUR_OF_DAY, MINUTE. 
	
	
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}
	
	private SessionManager(){
		random = new Random();
		sessions = new HashMap<String, Session>();
		applicationSession = new HashMap<String, Object>();
		users=new HashMap<String, String>();
	}
	
	synchronized Session  createSession(String idApplicationSession,String usuario){
		Session session = new Session();					
		String key = String.valueOf(getRandom(GENERATE_ID_SESSION));
		
		if (users.containsKey(usuario)){			
			synchronized (sessions) {
				String oldKey=users.remove(usuario);
				sessions.remove(oldKey);
			}
		}
		
		String idSession = idApplicationSession+SEPARATOR+key;
		sessions.put(key, session);
		users.put(usuario, key);
		session.setIdSession(idSession);
		return session;
	}
	
	public synchronized Session getSession(String key){
		int index = key.indexOf(SEPARATOR);
		String idApplication = null;
		String idSession = null;
		if(index > 0){
			idApplication = key.substring(0,index);
			idSession = key.substring(index+SEPARATOR.length());
		}
		Session session = new Session();
		if(applicationSession.containsValue(idApplication)){
			if(timeValidateApplicationSession(idApplication)){
				if(sessions.containsKey(idSession)){
					session = sessions.get(idSession);
				}			
			}else{
				invalidate(key, INVALIDATE_IDAPPLICATION_IDSESSION);
				session = new Session();
				session.setTimeValidateApplicationSession(false);
			}
		}else{
			session = new Session();
			session.setFoundApplicationSession(false);
		}
		return session;
	}
	
	public synchronized String getIdSessionByUser(String user){
		return users.get(user);
	}
	
	public synchronized void invalidate(String key, String invalidateSession){
		int index = key.indexOf(SEPARATOR);
		String idSession=null;
		String keyApplication=null;
		if(index!=-1){
			String idApplication = key.substring(0,index);
			idSession = key.substring(index+SEPARATOR.length());
			keyApplication = getKeyApplication(idApplication);
		}else{
			idSession=key;
		}
		
		if(invalidateSession.equals(INVALIDATE_IDAPPLICATION_IDSESSION)){
			sessions.remove(idSession);
			eliminaUsuario(idSession);
			applicationSession.remove(keyApplication);
		}
		else if(invalidateSession.equals(INVALIDATE_IDSESSION)){
			log.info("terminando la session: "+key);
			sessions.remove(idSession);
			eliminaUsuario(idSession);
		}
		else if(invalidateSession.equals(INVALIDATE_IDAPPLICATION)){
			applicationSession.remove(keyApplication);
		}
	}
	
	private void eliminaUsuario(String idSession){
		Set<Entry<String, String>> entrySet = users.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		ciclo:while (iterator.hasNext()) {
			Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
			if(entry.getValue().equals(idSession)){
				synchronized (users) {
					users.remove(entry.getKey());
				}
				break ciclo;
			}
		}
	}
	
	
	public synchronized Session generateNewSessionId(Session session,String usuario){
		String key = session.getIdSession();
		int index = key.indexOf(SEPARATOR);
		String idApplication = key.substring(0,index);
		String idSession = key.substring(index+SEPARATOR.length());
		
		String id = String.valueOf(getRandom(GENERATE_ID_SESSION));
		String newIdSession = idApplication+SEPARATOR+id;
		
		
		
		if (!sessions.containsKey(id)){			
			synchronized (sessions) {
				sessions.remove(idSession);
				eliminaUsuario(idSession);
			}
			session.setIdSession(newIdSession);
			sessions.put(id, session);
			users.put(usuario, id);
		}else{
			generateNewSessionId(session,usuario);
		}
		return session;
		
	}
	
	public synchronized String createApplicationSession(String aplicacion){
		String value = getRandom(GENERATE_ID_APPLICATION);
		applicationSession.put(aplicacion, value);
		return value;
	}
	
	
	private String cifraCesarInvertido(String usuario){
		char[] usuarioCifrado=new char[usuario.length()];
		int k=0;
		for (int i = usuario.length()-1; i >= 0; i--) {
			int dijit=usuario.codePointAt(i)+1;
			
			if(dijit==127){
				dijit=33;
			}
			
			char cifrado=(Character.toChars(dijit))[0];
			usuarioCifrado[k]=cifrado;
			k++;
		}
		return String.valueOf(usuarioCifrado);
	}
	
	
	private String decifraCesarInvertido(String usuarioCifrado){
		char[] usuarioDecifrado=new char[usuarioCifrado.length()];
		int k=usuarioCifrado.length()-1;
		for (int i = 0; i <usuarioCifrado.length(); i++) {
			int dijit=usuarioCifrado.codePointAt(i)-1;

			if(dijit==32){
				dijit=126;
			}
			
			char decifrado=(Character.toChars(dijit))[0];
			
			usuarioDecifrado[k]= decifrado;
			k--;
		}
		return String.valueOf(usuarioDecifrado);
	}
	
	
	private String getRandom(String tipoId){
		String numRandom = "";
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();		
		random.setSeed(time);	
		int randomCompleto = Math.abs(random.nextInt());
		if(tipoId.equals(GENERATE_ID_APPLICATION)){						
			numRandom = String.valueOf(randomCompleto).substring(0, 5) + SEPARATOR_APPLICATIONSESSION + time;
		}else if (tipoId.equals(GENERATE_ID_SESSION)){
			numRandom = String.valueOf(randomCompleto).substring(0, 5);
		}				
		return numRandom;
	}
	
	
	private boolean timeValidateApplicationSession(String idApplicationSession){
		boolean validate = false;
		//Calendario que contendrá la fecha en que fué creada el idApplicationSession
		Calendar calendar = Calendar.getInstance();
		int index = idApplicationSession.indexOf(SEPARATOR_APPLICATIONSESSION);
		String beginTimeSessionApp = idApplicationSession.substring(index+1, idApplicationSession.length());
		calendar.setTimeInMillis(Long.parseLong(beginTimeSessionApp));
		log.info("La sesión de aplicación fué creada: " + calendar.getTime());
		
		if(PARAMETER_TIME_IDAPPLICATIONSESSION.equals("DATE"))
			calendar.add(Calendar.DATE, TIME_IDAPPLICATIONSESSION);
		else if(PARAMETER_TIME_IDAPPLICATIONSESSION.equals("HOUR_OF_DAY"))
			calendar.add(Calendar.HOUR_OF_DAY, TIME_IDAPPLICATIONSESSION);
		else if(PARAMETER_TIME_IDAPPLICATIONSESSION.equals("MINUTE"))	
			calendar.add(Calendar.MINUTE, TIME_IDAPPLICATIONSESSION);
		
		log.info("La sesión de aplicación debe expirar: " + calendar.getTime());
		//Calendario Actual
		Calendar calendarActual = Calendar.getInstance();
		log.info("La fecha y hora actual es: " + calendarActual.getTime());
		if(calendarActual.before(calendar)){
			validate = true;
			log.info("La sesión de aplicación se encuentra en el rango de tiempo permitido");
		}
		else{
			log.info("La sesión de aplicación ha expirado");
		}
		
		return validate;
	}
	
	public String getSessionApplication(String idApplication){
		String validationIdApplication;
		if(applicationSession.containsValue(idApplication)){
			if(timeValidateApplicationSession(idApplication)){
				validationIdApplication = ID_APPLICATION_VALIDA;				
			}
			else{
				validationIdApplication = ID_APPLICATION_EXPIRADA;
				String keyApplication = getKeyApplication(idApplication);
				applicationSession.remove(keyApplication);
			}
		}else{ 
			validationIdApplication = ID_APPLICATION_INCORRECTA;
		}
		return validationIdApplication;
	}
	
	public String getValueSessionApplication(String idApplication){
		String valueIdApplication = null;
		
		if(applicationSession.containsKey(idApplication)){
			valueIdApplication = (String) applicationSession.get(idApplication);
		}
		return valueIdApplication;
	}
	
	public String getKeyApplication(String valueApplication){
		String id = null;
		for(String key : applicationSession.keySet()){
			String value = (String) applicationSession.get(key);
			if(value.equalsIgnoreCase(valueApplication)){
				id = key;
				break;
			}
		}
		return id;
	}
	
	public String getAplicationName(String session){
		int index = session.indexOf(SEPARATOR);
		String idApplication = null;
		String applicationName = null;
		if(index > 0){
			idApplication = session.substring(0,index);
			applicationName = getKeyApplication(idApplication);
		}		
		return applicationName;
	}
	
}
