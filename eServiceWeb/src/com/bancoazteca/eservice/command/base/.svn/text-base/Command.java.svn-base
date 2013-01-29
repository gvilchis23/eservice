package com.bancoazteca.eservice.command.base;

import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public abstract class Command {
	
	private FormBean formBean;
	private boolean validate;
	private boolean notSession;
	
	public boolean isNotSession() {
		return notSession;
	}
	public void setNotSession(boolean notSession) {
		this.notSession = notSession;
	}
	
	public Response execute(Session session) throws Exception{
		return null;
	}
	public  Response undefined (Session session) throws Exception{
		return null;
	}
		
	public FormBean getFormBean() {
		return formBean;
	}
	
	public void setFormBean(FormBean formBean) {
		this.formBean = formBean;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
}
