package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosComprobanteInversionRequest extends ReportosRequest{
	
	private static String[] keysrequest={"PORTAL","ICONTRATO","IORDEN","NUM_CUS"};
	
	private static String[] keysresponse={
		"FOPER",
		"REPORTADO","NOMLARGO","RFC","DIREC","COLONIA","CIUDAD","CODIGOP","ICONTRATO","NOMBRE",
		"CONCEPTO",
		"IORDEN","FCONCER","FLIQ","WPLAZO","FVENCE","MORDEN","NVALOR","TASA","WPREMIO","WMTOVTO","WISRM",
		"TOTTITULOS","TOTIMPORTE",
		"ITIPO_ORD","ITAJUS","EINDEVAL","EMISBMV","IEMISION","ISERIE","TPRECIO","VN","NTITULOS","WPRECIO","TCAMBIO","WIMPORTE"
	};
		
	public ReportosComprobanteInversionRequest() {
		super(keysresponse,keysrequest,"TCP01");
	}
	
	public void setPortal(String portal){
		addParameter("PORTAL", portal);
	}
	public void setNumeroContrato(String numcontrato){
		addParameter("ICONTRATO", numcontrato);
	}
	public void setFolioOperacion(String folioOperacion){
		addParameter("IORDEN", folioOperacion);
	}
	public void setIdAlnova(String idAlnova){
		addParameter("NUM_CUS", idAlnova);
	}

}
