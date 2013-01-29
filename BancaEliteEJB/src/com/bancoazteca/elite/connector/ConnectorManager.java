package com.bancoazteca.elite.connector;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;

public class ConnectorManager {
	
	private static final Logger log = Logger.getLogger(ConnectorManager.class);
	
	public static final String GENERAL_USER = "ConnectorManager.GENERAL_USER";
	
	private static final String DUMMY_USER = "@dummy";
	private static final String E_SERVICE_DUMMY_USER = "#dummy";
	
	private static Map<String, Connector> connectors = new HashMap<String, Connector>();	
	
	private static Map<String, Long>mapaTiempos = new HashMap<String,Long>();	
	
	

	public static synchronized HashMap<String, Long> getTiempos(){
		return (HashMap<String, Long>)mapaTiempos;
	}
	
		
	public static synchronized Connector  registerConnector(String user) throws InvalidCertificateException{		
		Connector connector = null;
		
		if (user.endsWith(DUMMY_USER)){
			log.info("#########################################");
			log.info("USUARIO DUMMY");
			log.info("#########################################");
			connector = new ConnectorDummy(user);
		}else if(user.endsWith(E_SERVICE_DUMMY_USER)){
			log.info("#########################################");
			log.info("USUARIO E SERVICE DUMMY");
			log.info("#########################################");
			connector = new ConnectorEServiceDummy(user);
		}else{
			connector = new ConnectorImp();
			DummyManager dummyManager = DummyManager.getInstance();		
			dummyManager.registerDummy(user);		
		}
		
		user = getUser(user);
		removeConnector(user);
		connector.setActiveUser(user);
		connectors.put(user, connector);
		mapaTiempos.put(user,System.currentTimeMillis());
		log.info(connectors);
		return connector;		
	}
	
	public static synchronized Connector getConnector(String user){
		user = getUser(user);
		log.info(connectors);
		log.info("Reset tiempo connector...");
		mapaTiempos.put(user,System.currentTimeMillis());
		return connectors.get(user);
	}
	
	public static synchronized void removeConnector(String user){
		user = getUser(user);
		log.info("ConnectorManager.removeConnector()");
		if(connectors.containsKey(user)){			
			connectors.remove(user);
			mapaTiempos.remove(user);
		}
		log.info(connectors);
	}
	
	public static boolean containsConnector(String user){
		user = getUser(user);
		return connectors.containsKey(user);
		
	}
	
	public static synchronized boolean isDummyMode(String user){	
		boolean flag = false;
		Connector connector = getConnector(user);
		if (connector!=null && connector instanceof ConnectorDummy){
			flag = true;
		}
		return flag;
	}
	
	private static synchronized String getUser(String user) {
		int index = 0;
		if (user.indexOf("@")!=-1){
			index = user.indexOf("@");
		}else{
			index = user.indexOf("#");
		}
		
		if (index!=-1){
			user = user.substring(0, index);
		}
		user = user.toLowerCase();
		return user;
	}

}
