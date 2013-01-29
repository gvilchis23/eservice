package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosConciliacionResponseTO extends ReportosResponse {

	public ReportosConciliacionResponseTO(Map<String, String> mapa) {
		super(mapa);		
	}
	
	public String getFolio(){
		return getAttribute("IORDEN");
	}
	
	public String getFechaOperacion(){
		return getAttribute("FOPER");
	}
	
	public String getMonto(){
		return getAttribute("MBRUTO");
	}
	
	public String getEstatus(){
		return getAttribute("ESTATUS");
	}
	
	public String getFolioRetencion(){
		return getAttribute("FRETENCION");
	}

	public String getMontoRetencion(){
		return getAttribute("MRETENCION");
	}
	
	public String getFolioCargo(){
		return getAttribute("FCARGO");
	}
	
	public String getMontoCargo(){
		return getAttribute("MCARGO");
	}
	
	public String getCodigo(){
		return getAttribute("CODIGO");
	}
	
	public String getDescripcion(){
		return getAttribute("DESCRIPCION");
	}
}
