package com.bancoazteca.eservice.command.base.beans;

public class ResponseTO {
	
	
	private StatusTO status;
	private Object data_service;
	
	
	
	
	public StatusTO getStatus() {
		return status;
	}
	public void setStatus(StatusTO status) {
		this.status = status;
	}
	public Object getData_service() {
		return data_service;
	}
	public void setData_service(Object data_service) {
		this.data_service = data_service;
	}
	
	
	

}
