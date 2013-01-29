package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LoginRequestTO implements Serializable{

	private static final long serialVersionUID = -4198676336876356574L;
	
	private String user;
	private String password;
	private String action;
	private String accountOnDemand;
	private boolean parallel;
	private boolean reload;
	private String aplicacion;

	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public boolean isParallel() {
		return parallel;
	}
	public void setParallel(boolean parallel) {
		this.parallel = parallel;
	}
	public String getAccountOnDemand() {
		return accountOnDemand;
	}
	public void setAccountOnDemand(String accountOnDemand) {
		this.accountOnDemand = accountOnDemand;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isReload() {
		return reload;
	}
	public void setReload(boolean reload) {
		this.reload = reload;
	}
	
	

}
