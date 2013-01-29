package com.bancoazteca.elite.connector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;

import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

public interface Connector extends Cloneable{
	
	String UTF8 = "UTF-8";
	String ISO_8859_1 = "ISO-8859-1";
	
	public String sendRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public String sendRequestPost(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public String sendMultiKeyRequest(String uri, Collection<ConnectorDataTO> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public String sendServletRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public String sendServletRequestPost(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public void sendJSPRequest(String uri, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	public void setActiveUser(String user);
	public void setHttpClient(HttpClient httpClient);
	public HttpClient getHttpClient();
	public Object clone() throws  CloneNotSupportedException;
}
