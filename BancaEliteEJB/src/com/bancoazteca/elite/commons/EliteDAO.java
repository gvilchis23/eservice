package com.bancoazteca.elite.commons;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.XmlRulesRequestTO;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

public abstract class EliteDAO {
	
	private static final Logger log = Logger.getLogger(EliteDAO.class);
	
	protected final String PATH_UPLOAD_VAR_SESSION = "/seguridad/middleValue.elite";
	protected final String PATH_XML_BEAN_RULES = "/contrasena/xmlBeanRules.elite";
	
	public static final int TOKEN_DISPOSITIVE = 1;
	public static final int FINGER_DISPOSITIVE = 2;
	public static final int NIP_DISPOSITIVE = 3;
	
	public  void setXmlBeanRules(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("xmlEncodeBeans", XmlRulesRequestTO.BEAN_RULES);
		params.put("xmlNotEncodeBeans", XmlRulesRequestTO.NOT_LOAD_BEAN_RULES);		
		log.info("Beans a codificar "+XmlRulesRequestTO.BEAN_RULES);
		log.info("Beans a no codificar "+XmlRulesRequestTO.NOT_LOAD_BEAN_RULES);
		try {
			Connector connector = ConnectorManager.registerConnector(ConnectorManager.GENERAL_USER);
			connector.sendRequest(PATH_XML_BEAN_RULES, params);
		} catch (SessionExpiredException e) {		
			
		} catch (URISyntaxException e) {			
			
		} catch (HttpException e) {
			
		} catch (IOException e) {
			
		} catch (InvalidCertificateException e) {			
			
		}
		
	}
}
