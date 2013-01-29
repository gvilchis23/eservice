package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosFechaAperturaRequestTO extends ReportosRequest {

	private static String[] keysResponse={"FOPER"};
	private static String[] keysRequest={"PORTAL","ICONTRATO","NUM_CUS"};
	
	public ReportosFechaAperturaRequestTO() {
		super(keysResponse, keysRequest, "TPO01");
	}
	
	public void setPortalSolicitante(String value) {
		addParameter("PORTAL", value);
	}
	public void setNumeroContrato(String value) {
		addParameter("ICONTRATO", value);
	}
	public void setIdAlnova(String value) {
		addParameter("NUM_CUS", value);
	}
	
}


 


