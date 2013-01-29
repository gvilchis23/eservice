package com.bancoazteca.elite.ejb.alnova.beans;

public class P027LocalizacionClientesResponseTO extends EjbAlnovaResponseTO {

	private static final long serialVersionUID = 1L;
		
	public String getNombreCliente() {
		return super.alnovaResponse.getAttribute("CUSNM");
	}

	public String getApellidoPaterno() {
		return super.alnovaResponse.getAttribute("NMSURN1");
	}

	public String getDireccion() {
		return super.alnovaResponse.getAttribute("ADR1");
	}

	public String getCiudad() {
		return super.alnovaResponse.getAttribute("CITY1");
	}
	
	public String getCodigoIdentificacion() {
		return super.alnovaResponse.getAttribute("IDC1");
	}

	public String getLlaveIdentificacion() {
		return super.alnovaResponse.getAttribute("IDKEY1");
	}
	
	public String getDigitoIdentificacion() {
		return super.alnovaResponse.getAttribute("IDDIG1");
	}
	public String getDigitoSecuencia() {
		return super.alnovaResponse.getAttribute("IDSEQ1");
	}
	public String getDescripcionSegmento() {
		return super.alnovaResponse.getAttribute("SEGDES1");
	}
	public String getGap() {
		return super.alnovaResponse.getAttribute("GAP");
	}


	

	
}