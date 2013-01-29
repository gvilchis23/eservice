package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;


import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosPlazoTasaRequest extends ReportosRequest {
	
	private static String[] keys={"FREG","TASA","PLAZO"};
	
	
	
	public ReportosPlazoTasaRequest() {
		super(keys,new String[3],"");
	}
	
	public void setFechaRegistro(String fecha){
		addParameter("FREG", fecha);
	}
}
