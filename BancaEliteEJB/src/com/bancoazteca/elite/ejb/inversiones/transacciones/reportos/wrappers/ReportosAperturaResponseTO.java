package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosAperturaResponseTO extends ReportosResponse {

	public ReportosAperturaResponseTO(Map<String,String> mapa) {
		super(mapa);
	}

	public String getNumeroContrato(){
		return getAttribute("ICONTRATO");
	}
	
	public String getFolioOperacion(){
		return getAttribute("IORDEN");
	}
	
	public String getIdAlnova(){
		return getAttribute("NUM_CUS");
	}
}