package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosConciliacionRequestTO extends ReportosRequest {	
	private static String[] keysResponse={"IORDEN","FOPER","MBRUTO","ESTATUS","FRETENCION","MRETENCION","FCARGO","MCARGO"};
	private static String[] keysRequest={"PORTAL","FOPER"};
	
	public ReportosConciliacionRequestTO() {
		super(keysResponse, keysRequest, "TCC01");		
	}
	
	public void setPortal(String portal){
		super.addParameter("PORTAL",portal);
	}
	
	public void setFechaOperacion(String portal){
		super.addParameter("FOPER",portal);
	}
}
