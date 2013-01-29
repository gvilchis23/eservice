package com.bancoazteca.elite.ejb.alnova.beans;


public class B706RegistroRetencionResponseTO extends EjbAlnovaResponseTO{
	private static final long serialVersionUID = 1991481577509935281L;
	private String idRetencion;
	private String idAlnova;
	private String portal;
	
	public String getCuentaCliente(){
		return super.alnovaResponse.getAttribute("ACC");
	}
	
	public String getNumeroRetencion(){
		return super.alnovaResponse.getAttribute("WITHHNM");
	}
	
	public String getImporteRetenido(){
		return super.alnovaResponse.getAttribute("AMTINP");
	}
	
	public String getFechaOperacion(){
		return super.alnovaResponse.getAttribute("OPERDAT");
	}
	
	public String getCodigoTransaccion(){
		return super.alnovaResponse.getAttribute("TRANSCO");
	}

	public String getIdAlnova() {
		return idAlnova;
	}

	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}
	
	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(String idRetencion) {
		this.idRetencion = idRetencion;
	}
}