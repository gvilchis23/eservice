package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosResumenOperacionesRequestTO extends ReportosRequest {

	private static String[] keysResponse={"IORDEN","FLIQ","FVENCE","MBRUTO","PLAZO","TASA","INTERES","RETENCION","TOTIMPORTE","ESTOPER"};
//	private static String[] keysRequest={"TRANSAC","PORTAL","ICONTRATO","FINICIO","FFIN","NUM_CUS"};
	private static String[] keysRequest={"TRANSAC","PORTAL","ICONTRATO","NUM_CUS"};
	
	public ReportosResumenOperacionesRequestTO(String codigoTransaccion) {
		super(keysResponse, keysRequest, codigoTransaccion);
	}
	
	public void setPortal(String value){
		addParameter("PORTAL", value);
	}
	
	public void setNumeroContrato(String value){
		addParameter("ICONTRATO", value);
	}
	
	public void setFechaInicioResumen(String value){
		addParameter("FINICIO", value);
	}
	
	public void setFechaFinResumen(String value){
		addParameter("FFIN", value);
	}
	
	public void setNumeroIdentificacionAlnova(String value){
		addParameter("NUM_CUS", value);
	}
	
}
