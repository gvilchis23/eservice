package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class B706_RegistroRetencionResponseTO{
	
	AlnovaResponse response;
	
	private String idRetencion;
	private String idAlnova;
	private String portal;
	
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

	public B706_RegistroRetencionResponseTO(AlnovaResponse response) {
		this.response=response;
	}
	
	public String getCuentaCliente(){
		return response.getAttribute("ACC");
	}
	
	public String getNumeroRetencion(){
		return response.getAttribute("WITHHNM");
	}
	
	public String getImporteRetenido(){
		return response.getAttribute("AMTINP");
	}
	
	
	public String getFechaOperacion(){
		return response.getAttribute("OPERDAT");
	}
	
	public String getCodigoTransaccion(){
		return response.getAttribute("TRANSCO");
	}
	
	

	
	public String getMensaje()
	{
		return response.getMessage();
	}
	
	public String getCodigoMensaje()
	{
		return response.getMessageCode();
	}
	
	public String getMensajeReal(){
		return response.getMessageReal();
	}

	public String getIdAlnova() {
		return idAlnova;
	}

	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}
	
}