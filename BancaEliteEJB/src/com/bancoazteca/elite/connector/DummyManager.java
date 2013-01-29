package com.bancoazteca.elite.connector;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class DummyManager {
	
	private static final Logger log = Logger.getLogger(DummyManager.class);
	
	public static final String NAMES_FILE = ".dummy";
	public static final String LOGS_BACKGORUND = "dummy.log.background";
	
	private static DummyManager instance;	
	private Map<String, Dummy> dummyInstances;
	
	
	public static DummyManager getInstance(){
		if(instance==null){
			instance = new DummyManager();
		}
		return instance;
	}
	
	private DummyManager(){
		dummyInstances = new HashMap<String, Dummy>();
	}
	
	public void registerDummy(String user){
		user = getUser(user);
		removeDummy(user);
		Dummy dummy = new Dummy(user);
		dummyInstances.put(user.toLowerCase(), dummy);		
	}
		
	public void commit(String user,String request,String response) {
		Dummy dummy = getDummy(user);
		dummy.setParameters(request, response);
		Thread thread = new Thread(dummy);		
		thread.start();
	}
	
	public void setFrontRequest(String user,String uri,Map<?, ?> params) {
		Dummy dummy = getDummy(user);
		if (dummy!=null){
			dummy.setParameters(uri,params);			
		}
	}
	
	public boolean existDummy(String user){
		Dummy dummy = getDummy(user);
		if (dummy!=null){
			return true;
		}else{
			return false;
		}
	}
	
	private void removeDummy(String user){
		this.dummyInstances.remove(user);
	}
	
	
	private Dummy getDummy(String user){
		//log.info(dummyInstances);
		Dummy dummy = this.dummyInstances.get(user.toLowerCase());		
		return dummy;
	}
	
	private String getUser(String user) {
		int index = user.indexOf("@");
		if (index!=-1){
			user = user.substring(0, index);
		}
		user = user.toLowerCase();
		return user;
	}

}
