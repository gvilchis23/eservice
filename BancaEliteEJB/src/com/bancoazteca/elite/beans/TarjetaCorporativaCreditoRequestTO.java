package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class TarjetaCorporativaCreditoRequestTO implements Serializable{

	private static final long serialVersionUID = -2496324040197991079L;
	
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
