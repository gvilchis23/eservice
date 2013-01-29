package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class CompaniaViewTO implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8795613190381870248L;

	private String desc;
	
	private String id;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
