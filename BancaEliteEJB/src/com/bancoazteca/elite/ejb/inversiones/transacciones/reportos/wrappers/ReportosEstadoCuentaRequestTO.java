package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosEstadoCuentaRequestTO extends ReportosRequest {

//	private static String[] keysResponse={ "NOMLARGO", "Periodo_del", "Periodo_al", "ICONTRATO", "RFC", "DIVISA", 
//			"DIREC", "COLONIA", "NOMBRE", "CIUDAD", "CODIGOP", "FLIQ", "FOPER", "WPLAZO", "CONCEPTO", "CONCEPTO2",
//			"NTITULOS", "PRECIO", "MBRUTO", "ISRIVA", "TASA", "MNETO", "SALDO", "MESANT", "ISR", "ENTRADAS", "IVA",
//			"SALIDAS", "MESACT", "VARMESAN" };

	private static String[] keysResponse={ 
		// Encabezado
		"NOMLARGO","Periodo_del","Periodo_al","ICONTRATO","RFC","DIVISA","DIREC","COLONIA","NOMBRE","CIUDAD","CODIGOP",
		// Movimientos
		"FLIQ","FOPER","WPLAZO","CONCEPTO","EMISERIE","NTITULOS","PRECIO","MBRUTO","ISRIVA","TASA","MNETO","SALDO",
		//Resumenes
		"MESANT","ISR","ENTRADAS","IVA","SALIDAS","MESACT","VARMESAN",
		//Estructura
		"CPAND","PCPAND","CPANR","PCPANR","CPANE","PCPANE","CPACD","PCPACD","CPACR","PCPACR","CPACE","PCPACE",
		//directo
		"PGDMANEMI","PGDMANSER","PGDMANTIT","PGDMANPRE","PGDMANINT","PGDMANTAS","PGDMANTOT","PGDMANTOTG","PGDMACTIT","PGDMACPRE","PGDMACINT","PGDMACTAS","PGDMACTOT","PGDMACTOTG",
		//Vigentes
		"PDRFI","PDRFV","PDRPZO","PDRTP","PDRMI","PDREMI","PDRSER","PDRTIT","PDRPD","PDRVC","PDRTMI","PDRTVC"
	};
	
	
	
	private static String[] keysRequest={ "TRANSAC", "PORTAL", "FEDOCTA", "ICONTRATO", "NUM_CUS" };
	
	public ReportosEstadoCuentaRequestTO() {
		super(keysResponse, keysRequest, "TCE01");		
	}
	
	public void setPortal(String value){
		addParameter("PORTAL", value);
	}
	
	public void setNumeroContrato(String value){
		addParameter("ICONTRATO", value);
	}
	
	public void setFechaEstadoCuenta(String value){
		addParameter("FEDOCTA", value);
	}
	
	public void setNumeroIdentificacionAlnova(String value){
		addParameter("NUM_CUS", value);
	}
	
}
