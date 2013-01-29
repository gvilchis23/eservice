package com.bancoazteca.elite.connector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;

public class ConnectorEServiceDummy extends ConnectorDummy{

	public ConnectorEServiceDummy(String user) throws InvalidCertificateException {		
		super(user.replace("#", "@"));
	}

	@Override
	protected void init(String user) throws IOException, ClassNotFoundException {
		loadInitialData(user);
	}
	
	@Override
	public void setActiveUser(String user) {
		super.setActiveUser(user.replace("#", "@"));
	}
	
	@Override
	protected String buildQueryString(String uri, Map<String, String> params) throws UnsupportedEncodingException{
		Map<String, String> parametros = new HashMap<String, String>();
		if (params!=null){
			for (String key :params.keySet()){
				String value = params.get(key);
				if ((value!=null) && (key.indexOf("#")!=-1 ||value.indexOf("#")!=-1)){
					String arg0 = key.replace("#", "@");
					String arg1 = value.replace("#", "@");
					//params.remove(key);
					parametros.put(arg0, arg1);
				}else{
					parametros.put(key, value); 
				}
			}
		}
		return super.buildQueryString(uri, parametros);
	}
	
}
