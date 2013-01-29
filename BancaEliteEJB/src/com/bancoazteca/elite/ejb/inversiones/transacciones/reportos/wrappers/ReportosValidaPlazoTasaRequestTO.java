package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosValidaPlazoTasaRequestTO extends ReportosRequest {
	
	private static String[] keysResponse={"RESPUESTA"};
	private static String[] keysRequest={"PORTAL","FOPER","MORDEN","TASA","PLAZO"};
	
	public ReportosValidaPlazoTasaRequestTO() {
		super(keysResponse, keysRequest, "TV001");
	}
	
	public void setPortal(String portal){
		super.addParameter("PORTAL",portal);
	}
	
	public void setFechaOperacion(String portal){
		super.addParameter("FOPER",portal);
	}
	
	public void setMontoOperacion(String portal){
		super.addParameter("MORDEN",portal);
	}
	
	public void setTasa(String portal){
		super.addParameter("TASA",portal);
	}
	
	public void setPlazo(String portal){
		super.addParameter("PLAZO",portal);
	}
}