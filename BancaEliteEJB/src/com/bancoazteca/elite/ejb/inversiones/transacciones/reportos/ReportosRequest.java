package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos;

import java.util.HashMap;
import java.util.Map;

public abstract class ReportosRequest
{
	private String[] keysResponse;
	private String[] keysRequest;
	private Map<String,String> parametros;
	private String transaccion;
	
	public ReportosRequest(String[] keysResponse,String[] keysRequest,String transaccion){
		this.keysResponse=keysResponse;
		this.transaccion=transaccion;
		this.keysRequest=keysRequest;
		this.parametros=new HashMap<String, String>();
	}
	
	public void addParameter(String key,String value){
		parametros.put(key, value);
	}
	
	public String getAttribute(String key){ 
		return parametros.get(key);
	} 
	
	public Map<String,String> map(){
		return parametros;
	}

	public String[] getKeysResponse() {
		return keysResponse;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public String[] getKeysRequest() {
		return keysRequest;
	}
}