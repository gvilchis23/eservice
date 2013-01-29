package com.bancoazteca.elite.connector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.axis.message.MessageElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.beans.XmlRulesRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioDAO;
import com.bancoazteca.elite.util.Enviroment;
import com.bancoazteca.elite.util.PropertiesService;

public class ConnectorImp implements Connector {

	private static final Logger log = Logger.getLogger(ConnectorImp.class);

	private final String SOCKET_TIMEOUT = "http.socket.timeout";
	private final String CERTIFICATE_PASORD_KEY = "elite.certificate.password";
	private final String IP = "ebanking.ip";
	private final String IP_PORT = "ebanking.ip.port";
	private final String PROTOCOL = "ebanking.ip.protocol";

	private final String STRUTS_TOKEN_1 = "org.apache.struts.action.TOKEN";
	private final String STRUTS_TOKEN_2 = "org.apache.struts.taglib.html.TOKEN";
	private final String ENCODER_MANAGER = "rulesSize";
	
	private final String J_PASSWORD= "j_password";

	private HttpClient httpclient;
	private String strutsToken;
	private String baseUri;
	private String activeUser;
	
	
	public ConnectorImp() throws InvalidCertificateException {	
		buildConnector();
	}
	
	public HttpClient getHttpClient(){
		return this.httpclient;
	}
	
	public void setHttpClient(HttpClient httpClient){
		this.httpclient = httpClient;
	}
	
	public void setActiveUser(String user) {
		this.activeUser = user;
	}

	private void buildConnector() throws InvalidCertificateException {
		PropertiesService propertiesService = PropertiesService.getInstance();
		strutsToken = "";

		try {

			Properties properties = propertiesService.getProperties(PropertiesService.EBANKING);
			String keyStorePassword = properties.getProperty(CERTIFICATE_PASORD_KEY);
			int port = Integer.parseInt(properties.getProperty(IP_PORT));
			String scheme = properties.getProperty(PROTOCOL);
			this.baseUri = properties.getProperty(IP);

			String timeOut = properties.getProperty(SOCKET_TIMEOUT);

			httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(SOCKET_TIMEOUT, new Integer(timeOut));
			
			

			// Configuraciòn para Desarrollo
			if (Enviroment.isDesarrollo()) {

				String keystore = KeyStore.getDefaultType();
				KeyStore trustStore = KeyStore.getInstance(keystore);
				InputStream inputStream = PropertiesService.getInstance().getCertificateStream();
				try {
					trustStore.load(inputStream, keyStorePassword.toCharArray());
				} finally {
					inputStream.close();
				}
				SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
				Scheme sch = new Scheme(scheme, socketFactory, port);
				httpclient.getConnectionManager().getSchemeRegistry().register(sch);

			}

			// Configuracion para Producción
			if (Enviroment.isProduccion()  || Enviroment.isPruebas()) {

				KeyStore ksTrust = KeyStore.getInstance("JKS");
				KeyStore ksKeys = KeyStore.getInstance("JKS");

				char[] passphrase = keyStorePassword.toCharArray();
				InputStream inputStream = PropertiesService.getInstance().getCertificateStream();

				try {
					ksKeys.load(inputStream, passphrase);
				} finally {
					inputStream.close();
				}

				// KeyManager's decide which key material to use.
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				kmf.init(ksKeys, passphrase);

				// TrustManager's decide whether to allow connections.
				TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
				tmf.init(ksTrust);

				SSLContext sslContext = SSLContext.getInstance("SSLv3");
				sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

				javax.net.ssl.SSLSocketFactory socketFactory = sslContext.getSocketFactory();

			}

		} catch (KeyManagementException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (KeyStoreException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (CertificateException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvalidCertificateException(e);
		}

	}

	public synchronized String sendRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {	
		if (params == null) {
			params = new HashMap<String, String>();
		}
		String xml = null;
		String query = "";
					
		putStrutsToken(params);		
		query = buildQueryString(uri,params);	
		xml = executeConnection(query);		
		updateStrutsToken(xml);
		return xml;
	}	

	public String sendRequestPost(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {	
		if (params == null) {
			params = new HashMap<String, String>();
		}
		String xml = null;
		putStrutsToken(params);		
		HttpPost httpPost = buildHttpPost(uri,params);
		xml = executeConnectionPost(httpPost);		
		updateStrutsToken(xml);
		return xml;
	}

	public String sendMultiKeyRequest(String uri, Collection<ConnectorDataTO> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		if (params == null) {
			params = new ArrayList<ConnectorDataTO>();
		}
		String xml = null;
		String query = "";	
		
		putStrutsToken(params);		
		query = buildMultiKeyQueryString(uri, params);								
		xml = executeConnection(query);
		updateStrutsToken(xml);
		return xml;		
	}

	public String sendServletRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		if (params == null) {
			params = new HashMap<String, String>();
		}		
		String html = null;
		String query = "";
		
		query = buildQueryString(uri, params);			
		html = executeConnection(query);				
		return html;
	}
	
	public String sendServletRequestPost(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		if (params == null) {
			params = new HashMap<String, String>();
		}		
		String html = null;
		String query = "";
		
		HttpPost httpPost = buildHttpPost(uri,params);
		html = executeConnectionPost(httpPost);	
						
		return html;
	}
		
	public void sendJSPRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		if (params == null) {
			params = new HashMap<String, String>();
		}
		String query = "";		
		query = buildQueryString(uri, params);		
		executeConnection(query);	
	}
	
	
	public Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}

	private  String buildQueryString(String uri,Map<String, String> params) throws UnsupportedEncodingException, SessionExpiredException, URISyntaxException, HttpException, IOException{
		String query = "";
		uri = baseUri + uri;
		int j = 0;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value != null) {
				if (!containDigitalFinger(key, params)) {
					query += URLEncoder.encode(key.trim(), Connector.UTF8) + "=" + URLEncoder.encode(value.trim(), Connector.UTF8);
				}
			}
			if (j < params.size() - 1 && value != null) {
				query += "&";
			}
			j++;
		}
		query = uri+"?"+query; 
		return query;
	}
	

	private HttpPost buildHttpPost(String uri,Map<String, String> params) throws UnsupportedEncodingException{
		uri = baseUri + uri;
		HttpPost httpPost = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value != null) {				
				parameters.add(new BasicNameValuePair( key, value  ));
			}			
		}
		httpPost.setEntity(new UrlEncodedFormEntity(parameters));
		return httpPost;
	}
	
	private  String buildMultiKeyQueryString(String uri,Collection<ConnectorDataTO> params) throws UnsupportedEncodingException, SessionExpiredException, URISyntaxException, HttpException, IOException{
		String query = "";
		int j = 0;
		uri = baseUri + uri;
		for (ConnectorDataTO connectorDataTO : params) {
			String key = connectorDataTO.getKey();
			String value = connectorDataTO.getValue();
			if (value != null) {
				if (!containDigitalFinger(key, params)) {
					query += URLEncoder.encode(key.trim(), Connector.UTF8) + "=" + URLEncoder.encode(value.trim(), Connector.UTF8);
				}
			}
			if (j < params.size() - 1 && value != null) {
				query += "&";
			}
			j++;
		}	
		query = uri+"?"+query; 
		return query;
	}
	
	private synchronized String executeConnection(String query) throws HttpException, SessionExpiredException, IOException{
		String xml = "";
		if (!isLogBackground()){
			if (query.indexOf(J_PASSWORD)!=-1){
				log.info(query.substring(0,query.indexOf(J_PASSWORD)));
			}else{
				log.info(query);
			}
		}		
		try{					
			HttpPost httpPost = new HttpPost(query);			
			httpPost.setHeader("Content-Encoding", "gzip");
//			log.info("*********************** CONNECTOR ELITE HEADER ****************************");
//			for(Header obj: httpPost.getAllHeaders()){
//				log.info(obj.getName()+":"+obj.getValue());
//			}
			
			HttpResponse response = httpclient.execute(httpPost);
			geteBankingInstance(response);
			xml = getResponse(response);
			
			if (query.indexOf(J_PASSWORD)!=-1){
				log.info("[query] " + query.substring(0,query.indexOf(J_PASSWORD)));
			}else{
				log.info("[query] " + query);
			}			
			log.info("[xml] " + xml);
		}finally{			
			DummyManager.getInstance().commit(this.activeUser, query, xml);
		}		
		return xml;
	}
	
	private String executeConnectionPost(HttpPost httpPost) throws HttpException, SessionExpiredException, IOException{
		String xml = "";
		try{					
			httpPost.setHeader("Content-Encoding", "gzip");
			HttpResponse response = httpclient.execute(httpPost);
			geteBankingInstance(response);
			xml = getResponse(response);
			log.info("xml "+xml);
		}finally{			
			//DummyManager.getInstance().commit(this.activeUser, query, xml);
		}		
		return xml;
	}
	
	private boolean isLogBackground() {
		String logBackground = null;
		try {
			logBackground = PropertiesService.getInstance().getPropertie(PropertiesService.DUMMY, DummyManager.LOGS_BACKGORUND);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		if (logBackground!=null && logBackground.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	}

	private String getResponse(HttpResponse response) throws HttpException, SessionExpiredException, IOException {
		String xml = "";
		HttpEntity entity = response.getEntity();
		int ststusCode = response.getStatusLine().getStatusCode();	
			
		if (ststusCode == HttpStatus.SC_OK) {
			byte[] bytes = EntityUtils.toByteArray(entity);		
			xml = new String(bytes);		
			
//			log.info("4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~4~~");
//			log.info("[xml]:   "+ xml);

			//xml = EntityUtils.toString(entity);			
			if (!isLogBackground()){
				log.info(xml);
			}
//			xmlEncodeAnalizer(xml);
			if (entity != null) {
				log.info("Response content length: " + entity.getContentLength());
				log.info("Chunked?: " + entity.isChunked());
			}
			if (entity != null) {
				entity.consumeContent();
			}
			if (xml.indexOf("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">") != -1 && xml.indexOf("/servicios/servicios.jsp")==-1) {
				throw new SessionExpiredException("Session Expired");
			}
			if (xml.indexOf("logout.jsp")!=-1  || xml.indexOf("cerrada.jsp") != -1){
				throw new SessionExpiredException("Session Expired");
			}
		}else if(ststusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR){
			
			Header[] headers=response.getAllHeaders();
			boolean isCerradaJSP=false;
			
			for (Header header : headers) {
				String headerName=header.getName();
				if("Location".equalsIgnoreCase(headerName)){
					String headerValue=header.getValue();
					if(headerValue.indexOf("cerrada.jsp") != -1 ){
						isCerradaJSP=true;
					}
				}
			}
			
			if(isCerradaJSP){
				throw new SessionExpiredException("Session Expired");
			}else{
				throw new HttpException(response.getStatusLine().getReasonPhrase());
			}
			
		} else {
			throw new HttpException(response.getStatusLine().getReasonPhrase());
		}
		
		return xml;
	}

	
	private void xmlEncodeAnalizer(String xml){
		try {
			if (xml!=null && xml.length()>0){
				XMLDecode decode = new XMLDecode();
				MessageElement messageElement = XMLDecode.buildXMLMessageElement(xml);
				String status  = decode.getString(messageElement, ENCODER_MANAGER);				
				if (status==null || status.indexOf(XmlRulesRequestTO.BEAN_RULES.length())!=-1){
					EliteDAO eliteDAO = new UsuarioDAO();
					eliteDAO.setXmlBeanRules();
				}
			}
		} catch (Throwable e) {}
	}
	
	private void updateStrutsToken(String xml) {
		try {
			if (xml!=null && xml.length()>0){
				XMLDecode decode = new XMLDecode();
				MessageElement messageElement = XMLDecode.buildXMLMessageElement(xml);
				strutsToken = decode.getString(messageElement, STRUTS_TOKEN_1);
			}
		} catch (Throwable e) {
			log.error("Ocurrio un error al actualizar el struts token "+e.getMessage());
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

	private boolean containDigitalFinger(String key, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		boolean flag = false;
		String option = params.get(DigitalFinger.DIGITAL_FINGET_VALUE);
		if (key.equals(option)) {
			String value = params.get(key);
			if (value != null && value.length() > 0) {
				DigitalFinger digitalFinger = new DigitalFinger(value);
				String user = params.get(DigitalFinger.DIGITAL_FINGET_USER);
				digitalFinger.sendDigitalFingerParts(this, user);
				flag = true;
			}
		}
		return flag;
	}
	
	private boolean containDigitalFinger(String key, Collection<ConnectorDataTO> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		boolean flag = false;
		ConnectorDataTO connectorDataTO = (ConnectorDataTO) CollectionUtils.find(params, new DifitalFingerPredicate(DigitalFinger.DIGITAL_FINGET_VALUE));		
		if (connectorDataTO!=null && key.equals(connectorDataTO.getKey())) {
			String value = connectorDataTO.getValue();
			if (value != null && value.length() > 0) {
				DigitalFinger digitalFinger = new DigitalFinger(value);
				ConnectorDataTO connectorDataUserTO = (ConnectorDataTO) CollectionUtils.find(params, new DifitalFingerPredicate(DigitalFinger.DIGITAL_FINGET_USER));
				String user = connectorDataUserTO.getValue();
				digitalFinger.sendDigitalFingerParts(this, user);
				flag = true;
			}
		}
		return flag;
	}

	/* Mètodo para obtener la instancia real donde corre el proceso */
	private void geteBankingInstance(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		String headerName = null;
		String headerValue = null;
		log.info("******************** << headers >> ***********************");
		for (int i = 0; i < headers.length; i++) {
			headerName = headers[i].getName();
			headerValue = headers[i].getValue();
			
			log.info(headerName + " : " + headerValue);
			
			if (headerName != null && headerName.equals("Set-Cookie")) {
				try {
					log.info("\n##### La instancia de e-Banking donde se ejecuta el proceso es << " + headerValue.substring(headerValue.indexOf(".") + 1, headerValue.indexOf(";")) + " >> #####\n");
					break;
				} catch (Exception e) {
				}
			}
		}
		log.info("**********************************************************");
	}
	
	private  class DifitalFingerPredicate implements Predicate{
		
		private String key;
		
		public DifitalFingerPredicate(String key) {
			this.key = key;
		}

		public boolean evaluate(Object obj) {
			ConnectorDataTO connectorDataTO = (ConnectorDataTO) obj;
			String option = connectorDataTO.getKey();
			if (option.equals(this.key)){
				return true;
			}else{
				return false;
			}
		}
		
	}

	

}
