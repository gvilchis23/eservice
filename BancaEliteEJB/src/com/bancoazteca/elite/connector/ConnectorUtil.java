package com.bancoazteca.elite.connector;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.util.Enviroment;
import com.bancoazteca.elite.util.PropertiesService;


/**
 * 
 * @author Jorge Bringas
 *
 */
public class ConnectorUtil {
		

	
	public static synchronized final Connector clone(Connector connector) throws CloneNotSupportedException {		
		Connector clon = (Connector) connector.clone();
		HttpClient httpClient = clone(connector.getHttpClient());
		clon.setHttpClient(httpClient);
		return clon;
	}
	
	private static synchronized final HttpClient clone(HttpClient httpClient){		
		DefaultHttpClient defultHttp = (DefaultHttpClient) httpClient;			
		
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		defaultHttpClient.setAuthSchemes(defultHttp.getAuthSchemes());
		defaultHttpClient.setCookieSpecs(defultHttp.getCookieSpecs());
		defaultHttpClient.setCookieStore(defultHttp.getCookieStore());
		defaultHttpClient.setCredentialsProvider(defultHttp.getCredentialsProvider());
		defaultHttpClient.setHttpRequestRetryHandler(defultHttp.getHttpRequestRetryHandler());
		defaultHttpClient.setKeepAliveStrategy(defultHttp.getConnectionKeepAliveStrategy());
		defaultHttpClient.setParams(defultHttp.getParams());
		defaultHttpClient.setProxyAuthenticationHandler(defultHttp.getProxyAuthenticationHandler());
		defaultHttpClient.setRedirectHandler(defultHttp.getRedirectHandler());			
		defaultHttpClient.setRoutePlanner(defultHttp.getRoutePlanner());
		defaultHttpClient.setReuseStrategy(defultHttp.getConnectionReuseStrategy());
		defaultHttpClient.setTargetAuthenticationHandler(defultHttp.getTargetAuthenticationHandler());
		defaultHttpClient.setUserTokenHandler(defultHttp.getUserTokenHandler());
		
		if (Enviroment.isDesarrollo()) {
			try{				
				final String CERTIFICATE_PASORD_KEY = "elite.certificate.password";			
				final String IP_PORT = "ebanking.ip.port";
				final String PROTOCOL = "ebanking.ip.protocol";
				
				Properties properties = PropertiesService.getInstance().getProperties(PropertiesService.EBANKING);
				String keyStorePassword = properties.getProperty(CERTIFICATE_PASORD_KEY);
				int port = Integer.parseInt(properties.getProperty(IP_PORT));
				String scheme = properties.getProperty(PROTOCOL);
				
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
				defaultHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
			}catch (Throwable e){
				e.printStackTrace();
			}
		}
		
		return defaultHttpClient;
	}
	

}
