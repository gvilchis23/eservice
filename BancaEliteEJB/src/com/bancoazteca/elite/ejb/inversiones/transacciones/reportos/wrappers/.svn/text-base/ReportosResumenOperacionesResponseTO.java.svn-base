package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosResumenOperacionesResponseTO extends ReportosResponse {

	public ReportosResumenOperacionesResponseTO(Map<String, String> mapa) {
		super(mapa);
	}
		
	public String getFolio(){
		return getAttribute("IORDEN");
	}

	public String getFechaLiquidacion(){
		return getAttribute("FLIQ");
	}
	
	public String getFechaVencimiento(){
		return getAttribute("FVENCE");
	}
	
	public String getMonto(){
		return getAttribute("MBRUTO");
	}
	
	public String getPlazo(){
		return getAttribute("PLAZO");
	}
	
	public String getTasa(){
		return getAttribute("TASA");
	}
	
	public String getInteresGenerado(){
		return getAttribute("INTERES");
	}
	
	public String getRetencionISR(){
		return getAttribute("RETENCION");
	}
	
	public String getImporteTotal(){
		return getAttribute("TOTIMPORTE");
	}
	
	public String getEstatus(){
		return getAttribute("ESTOPER");
	}
	
}

