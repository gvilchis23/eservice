package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosMovimientosEstadoCuentaResponseTO extends ReportosResponse {

	public ReportosMovimientosEstadoCuentaResponseTO(Map<String, String> mapa) {
		super(mapa);		
	}
	
	public String getFechaLiquidacion(){
		return getAttribute( "FLIQ" );
	}

	public String getFechaOperacion(){
		return getAttribute( "FOPER" );
	}

	public String getPlazo(){
		return getAttribute( "WPLAZO" );
	}
	public String getConcepto(){
		return getAttribute( "CONCEPTO" );
	}

	public String getEmisionSerie(){
		return getAttribute( "EMISERIE" );
	}

	public String getTitulos(){
		return getAttribute( "NTITULOS" );
	}

	public String getPrecio(){
		return getAttribute( "PRECIO" );
	}

	public String getImporteBruto(){
		return getAttribute( "MBRUTO" );
	}

	public String getIvaIsr(){
		return getAttribute( "ISRIVA" );
	}

	public String getTasa(){
		return getAttribute( "TASA" );
	}

	public String getImporteNeto(){
		return getAttribute( "MNETO" );
	}

	public String getSaldo(){
		return getAttribute( "SALDO" );
	}
}
