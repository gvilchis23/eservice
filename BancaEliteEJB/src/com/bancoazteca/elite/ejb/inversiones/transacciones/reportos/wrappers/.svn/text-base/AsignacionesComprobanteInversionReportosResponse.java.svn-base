package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class AsignacionesComprobanteInversionReportosResponse extends ReportosResponse {

	public AsignacionesComprobanteInversionReportosResponse(Map<String, String> resumenes) {
		super(resumenes);
	}

	public String getTipoOperacion(){
		return getAttribute("ITIPO_ORD");
	}

	public String getMoneda(){
		return getAttribute("ITAJUS");
	}

	public String getTipoValor(){
		return getAttribute("EMISBMV");
	}

	public String getEmisora(){
		return getAttribute("IEMISION");
	}

	public String getSerie(){
		return getAttribute("ISERIE");
	}

	public String getTasaOperativa(){
		return getAttribute("TPRECIO");
	}

	public String getTitulos(){
		return getAttribute("NTITULOS");
	}

	public String getPrecio(){
		return getAttribute("WPRECIO");
	}

	public String getTipoCambio(){
		return getAttribute("TCAMBIO");
	}

	public String getMonto(){
		return getAttribute("WIMPORTE");
	}
	
	public String getEmisoraIndeval(){
		return getAttribute("EINDEVAL");
	}
	
	public String getValorNominal(){
		return getAttribute("VN");
	}
}