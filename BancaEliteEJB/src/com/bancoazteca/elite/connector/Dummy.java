package com.bancoazteca.elite.connector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.PropertiesService;
/**
 * 
 * @author Jorge Bringas
 *
 */
public class Dummy implements Runnable{
	
	private static final Logger log = Logger.getLogger(Dummy.class);

	private final String DUMMY_ENABLED = "dummy.record.enable";
	private final String DUMMY_DIRECTORY = "dummy.directory.save";
	private final String DUMMY_LOGIN_PASSWORD = "dummy.login.password";
	private final String DUMMY_USERS = "dummy.record.users";
	
	private final String J_USER_NAME = "j_username";
	private final String J_PASSWORD= "j_password";
	private final String ELITE_USER_NAME ="user";
	private final String ELITE_PASSWORD = "password";	
	private String path;	
	private boolean enabledRecord;
	private String sessionDirectory;
	private String user;	
	private String loginPassword;
	private Map<String, String> parameters;
	private Collection<String> recorsUsers;
	private Collection<ValueBean> requests;
	
	public Dummy(String user){
		try {
			this.parameters = new HashMap<String, String>();
			this.requests = new ArrayList<ValueBean>();
			this.user = user;
			String enabled = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DUMMY_ENABLED);
			loadAllRecordUsers();
			
			if (enabled!=null && enabled.equals("true") && (isRecordAllUsers() || isRecordUser(user))){
				String dummyPath = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DUMMY_DIRECTORY);
				String passwordDummy = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DUMMY_LOGIN_PASSWORD);
				
				this.enabledRecord = true;
				this.loginPassword = passwordDummy;
				this.path = dummyPath;
				initSessionDirectory();
				
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void run() {						
		commit();				
	}
	
	public synchronized void commit(){		
		try {		
			if (this.enabledRecord){
				log.info("Dummy.commit()");
				saveDummyFile();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} 
	}		
	
	private void buildFrontRequest(){
		String query = "";
		for(ValueBean valueBean : requests){
			query += valueBean.getUri();
			query += "?";
			Map<?, ?> map = valueBean.getParams();				
			for(Object obj : map.keySet()){
				String key = ConvertUtils.convert(obj);				
				Object objValue = map.get(obj);				
				String value = ConvertUtils.convert(objValue) ;		
				
				if (key.indexOf(ELITE_USER_NAME)!=-1){
					if (value.indexOf("@")==-1){
						value = value+"@dummy";
					}
				}else if (key.indexOf(ELITE_PASSWORD)!=-1){
					value = loginPassword;
				}				
				
				query += key+"="+value+"&";				
			}
			query+="<->";
		}
		if (query.length()>0){
			//log.info("EliteRequest "+query);
			parameters.put("EliteRequest", query);
		}		
	}
	
	private synchronized void saveDummyFile() throws IOException{	
		buildFrontRequest();
		String path = this.sessionDirectory;		
		File newFile = new File(path);
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(newFile);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);					
			objectOutputStream.writeObject(parameters);
		}finally{
			if (objectOutputStream!=null){
				objectOutputStream.close();
			}
			if (fileOutputStream!=null){
				fileOutputStream.close();
			}
		}		
	}
	
	private void initSessionDirectory() throws IOException{
		if (this.sessionDirectory==null){		
			String path = this.path;
			if (!path.endsWith(File.separator)){
				path += File.separator;
			}			
			this.sessionDirectory = path;			
			createDirectory(path);
			path += this.user+DummyManager.NAMES_FILE;
			this.sessionDirectory = path;
		}
	}
	
	private boolean createDirectory(String path){
		boolean flag = false;
		File directory = new File(path);	
		if(!directory.exists()){
			flag = directory.mkdirs();
			log.info("create directory "+path);
		}
		return flag;
	}

	public  void setParameters(String request,String response){
		if (request.indexOf(J_PASSWORD)!=-1){
			try {
				String j_username = URLEncoder.encode(J_USER_NAME, Connector.UTF8) + "=" + URLEncoder.encode(this.user+"@dummy", Connector.UTF8);
				String j_password = URLEncoder.encode(J_PASSWORD, Connector.UTF8) + "=" + URLEncoder.encode(this.loginPassword, Connector.UTF8);
				request = request.substring(0, request.indexOf("?")+1)+j_username+"&"+j_password;
			} catch (Throwable e) {		
			}			
		}						
		this.parameters.put(request, response);
//		if (isLogBackground()){
//			if (request.indexOf(J_PASSWORD)!=-1){
//				log.info(request.substring(0,request.indexOf(J_PASSWORD)));
//			}else{
//				log.info(request);
//			}
//			log.info(response);
//		}
	}
	
	public  void setParameters(String uri,Map<?, ?> params){
		ValueBean valueBean = new ValueBean();
		valueBean.setUri(uri);
		valueBean.setParams(params);
		this.requests.add(valueBean);
	}
	
	private boolean isRecordAllUsers(){
		boolean flag = false;
		try {
			String users = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DUMMY_USERS);
			if (users.indexOf("*")!=-1){
				flag = true;
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean isRecordUser(String user){
		if (recorsUsers.contains(user)){
			return true;
		}else{
			return false;
		}
	}

	private void loadAllRecordUsers(){
		Collection<String> users = null;
		try {
			if (this.recorsUsers==null){
				users = new ArrayList<String>();
				String tokens = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DUMMY_USERS);
				StringTokenizer stringTokenizer = new StringTokenizer(tokens,",");
				while (stringTokenizer.hasMoreElements()){
					String token = stringTokenizer.nextToken();
					users.add(token);
				}
				this.recorsUsers = users;
			}
		} catch (IOException e) {		
			e.printStackTrace();
		}	
	}
	
	private boolean isLogBackground() {
		String logBackground = null;
		try {
			logBackground = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DummyManager.LOGS_BACKGORUND);
		} catch (IOException e) {			
		}
		if (logBackground!=null && logBackground.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}	
	
	private class ValueBean{
		private String uri;
		private Map<?, ?> params;
		
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public Map<?, ?> getParams() {
			return params;
		}
		public void setParams(Map<?, ?> params) {
			this.params = params;
		}				
	}
		
}
