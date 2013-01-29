package com.bancoazteca.elite.connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.Enviroment;
import com.bancoazteca.elite.util.PropertiesService;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class ConnectorDummy implements Connector {
	
	private static final Logger log = Logger.getLogger(ConnectorDummy.class);

	private final String STRUTS_TOKEN_1 = "org.apache.struts.action.TOKEN";
	private final String STRUTS_TOKEN_2 = "org.apache.struts.taglib.html.TOKEN";
	
	private final String DUMMY_LOAD_DIRECTORY = "dummy.directory.load";	
	private final String IP = "ebanking.ip";
	private final String J_PASSWORD= "j_password";

	private String user; 
	private String strutsToken; 
	private Map<String, String> params;
	private String baseUri;
	
	public ConnectorDummy(String user) throws InvalidCertificateException {
		try {
			init(user);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (ClassNotFoundException e) {
			throw new InvalidCertificateException(e);		
		}
	}
	
	protected void init(String user) throws IOException, ClassNotFoundException{
		if (!Enviroment.isProduccion()){
			loadInitialData(user);			
		}
	}
	
	void loadInitialData(String user) throws IOException, ClassNotFoundException{
		PropertiesService propertiesService = PropertiesService.getInstance();
		Properties properties = propertiesService.getProperties(PropertiesService.EBANKING);
		String path = propertiesService.getPropertie(PropertiesService.DUMMY, DUMMY_LOAD_DIRECTORY);
		this.baseUri = properties.getProperty(IP);	
		this.user = user.substring(0, user.indexOf("@"));			
		if (!path.endsWith(File.separator)){
			path += File.separator;
		}
		path += this.user+DummyManager.NAMES_FILE;			
		this.params = getDummyFile(path);		
		
		for (String key : params.keySet()){
			log.info(key);
		}
	}
	
	public HttpClient getHttpClient() {		
		return new DefaultHttpClient();
	}
	
	public void setHttpClient(HttpClient httpClient) {		
	}
	
	public Object clone() throws CloneNotSupportedException {	
		return super.clone();
	}
	
	public String sendRequest(String uri, Map<String, String> queryParams) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		putStrutsToken(params);
		String query = buildQueryString(uri, queryParams);				
		String param = params.get(query);
		updateStrutsToken(param);	
		log.info(query);
		log.info(param);
		return param;
	}
	
	public String sendRequestPost(String uri, Map<String, String> queryParams) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		putStrutsToken(params);
		String query = buildQueryString(uri, queryParams);				
		String param = params.get(query);
		updateStrutsToken(param);	
		log.info(query);
		log.info(param);
		return param;
	}
	
	public String sendMultiKeyRequest(String uri, Collection<ConnectorDataTO> collectionData) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		putStrutsToken(params);
		String query = buildMultiKeyQueryString(uri, collectionData);		
		String param = params.get(query);
		updateStrutsToken(param);
		log.info(query);
		log.info(param);
		return param;
	}

	public void sendJSPRequest(String uri, Map<String, String> queryParams) throws SessionExpiredException, URISyntaxException, HttpException, IOException {		
		String query = buildQueryString(uri, queryParams);				
		@SuppressWarnings("unused")		
		String param = params.get(query);
		log.info(query);
		log.info(param);
	}

	public String sendServletRequest(String uri, Map<String, String> queryParams) throws SessionExpiredException, URISyntaxException, HttpException, IOException {		
		if (queryParams!=null){
			String j_password = queryParams.get(J_PASSWORD);
			if (j_password!=null){
				String value = queryParams.get(J_PASSWORD);			
				queryParams.put(J_PASSWORD, String.valueOf(value.hashCode()));
			}
		}
		String query = buildQueryString(uri, queryParams);
		String param = params.get(query);
		log.info(query);
		log.info(param);
		return param;
	}

	public void setActiveUser(String user) {
		this.user = user;
	}
	
	protected String buildQueryString(String uri, Map<String, String> params) throws UnsupportedEncodingException{
		uri = baseUri + uri;
		String query = "";
		int j = 0;
		
		if (params == null) {
			params = new HashMap<String, String>();
		}
						
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value != null) {
				query += URLEncoder.encode(key, Connector.UTF8) + "=" + URLEncoder.encode(value, Connector.UTF8);
			}
			if (j < params.size() - 1 && value != null) {
				query += "&";
			}
			j++;
		}
					
		return uri+"?"+query;
	}
	
	private String buildMultiKeyQueryString(String uri, Collection<ConnectorDataTO> params) throws UnsupportedEncodingException{
		uri = baseUri + uri;
		String query = "";
		
		
		int j = 0;
		putStrutsToken(params);
		
		if (params!=null){
			for (ConnectorDataTO connectorDataTO : params) {
				String key = connectorDataTO.getKey();
				String value = connectorDataTO.getValue();
				if (value != null) {				
					query += URLEncoder.encode(key.trim(), Connector.UTF8) + "=" + URLEncoder.encode(value.trim(), Connector.UTF8);				
				}
				if (j < params.size() - 1 && value != null) {
					query += "&";
				}
				j++;
			}
		}
		return uri+"?"+query;
	}
	
	
	private void updateStrutsToken(String xml) {
		try {						
			if (xml!=null && xml.length()>0){
				XMLDecode decode = new XMLDecode();
				MessageElement messageElement = XMLDecode.buildXMLMessageElement(xml);
				strutsToken = decode.getString(messageElement, STRUTS_TOKEN_1);	
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}		
	}

	private void putStrutsToken(Map<String, String> params) {
		if (strutsToken != null && strutsToken.trim().length() > 0) {
			params.put(STRUTS_TOKEN_2, strutsToken);
		}
	}
	
	private void putStrutsToken(Collection<ConnectorDataTO> params) {
		if (strutsToken != null && strutsToken.trim().length() > 0) {
			ConnectorDataTO connectorDataTO = new ConnectorDataTO(STRUTS_TOKEN_2,strutsToken);		
			params.add(connectorDataTO);
		}
	}
	
	@SuppressWarnings("unchecked")
	private  Map<String, String> getDummyFile(String sessionFile) throws IOException, ClassNotFoundException{			
		String path = sessionFile;
		Map<String, String> map = null;
		File file = new File(path);		
		if (file.exists()){
			FileInputStream fileInputStream = null;
			ObjectInputStream objectInputStream = null;
			try{
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				map = (Map<String, String>) objectInputStream.readObject();				
			}finally{
				if(objectInputStream!=null){
					objectInputStream.close();
				}
				if(fileInputStream!=null){
					fileInputStream.close();
				}
			}
		}else{
			throw new IOException("File not found "+sessionFile);
		}
		return map;
		
	}

	public String sendServletRequestPost(String uri, Map<String, String> params)
			throws SessionExpiredException, URISyntaxException, HttpException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
