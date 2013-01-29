package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosAperturaRequestTO extends ReportosRequest {
	
	//El orden de los arreglos de keys debe corresponder con el orden requerido por el contrato de TAS
	private static String[] keysResponse={"ICONTRATO","IORDEN","NUM_CUS"};
	private static String[] keysRequest={"TRANSAC","PORTAL","NUM_CUS","CTACHEQ","FOPER","MORDEN","TASA","PLAZO","FOLIO_RET"};

	private String idRetencion;
	
	public ReportosAperturaRequestTO(){
		super(keysResponse,keysRequest,"TAI01");
	}
	
	public void setPortal(String value){
		addParameter("PORTAL", value);
	}
	
	public void setNumeroIdentificacionAlnova(String value){
		addParameter("NUM_CUS", value);
	}
	
	public void setCuentaCargo(String value){
		addParameter("CTACHEQ", value);
	}
	
	public void setFechaOperacion(String value){
		addParameter("FOPER", value);
	}
	
	public void setMontoInversion(String value){
		addParameter("MORDEN", value);
	}
	
	public void setTasaBruta(String value){
		addParameter("TASA", value);
	}
	
	public void setPlazo(String value){
		addParameter("PLAZO", value);
	}
	
	public void setFolioRetencionAlnova(String value){
		addParameter("FOLIO_RET", value);
	}
	
	public String getPortal(){
		return getAttribute("PORTAL");
	}


	public String getNumeroIdentificacionAlnova(){
		return getAttribute("NUM_CUS");
	}


	public String getCuentaCargo(){
		return getAttribute("CTACHEQ");
	}


	public String getFechaOperacion(){
		return getAttribute("FOPER");
	}


	public String getMontoInversion(){
		return getAttribute("MORDEN");
	}


	public String getTasa(){
		return getAttribute("TASA");
	}


	public String getPlazo(){
		return getAttribute("PLAZO");
	}


	public String getFolioRetencion(){
		return getAttribute("FOLIO_RET");
	}

	public String getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(String idRetencion) {
		this.idRetencion = idRetencion;
	}
	
}
