package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosEstadoCuentaResponseTO extends ReportosResponse {
	
	public ReportosEstadoCuentaResponseTO(Map<String, String> mapa) {
		super(mapa);		
	}

	private Collection<ReportosMovimientosEstadoCuentaResponseTO> movimientos;
	private Collection<ReportosOperacionesEstadoCuentaResponseTO> operacionesReportos;
	private Collection<ReportosOperacionesDirectoEstadoCuentaResponseTO> operacionesDirecto;

	
	public String getNombreCliente() {
		return getAttribute( "NOMLARGO" );
	}


	public String getPeriodoInicial() {
		return getAttribute( "Periodo_del" );
	}


	public String getPeriodoFina() {
		return getAttribute( "Periodo_al" );
	}


	public String getNumeroContrato() {
		return getAttribute( "ICONTRATO" );
	}


	public String getRFC() {
		return getAttribute( "RFC" );
	}


	public String getDivisa() {
		return getAttribute( "DIVISA" );
	}


	public String getDireccion() {
		return getAttribute( "DIREC" );
	}


	public String getColonia() {
		return getAttribute( "COLONIA" );
	}


	public String getPromotor() {
		return getAttribute( "NOMBRE" );
	}


	public String getCiudad() {
		return getAttribute( "CIUDAD" );
	}


	public String getCodigoPostal() {
		return getAttribute( "CODIGOP" );
	}


	public String getCarteraMesAnterior() {
		return getAttribute( "MESANT" );
	}


	public String getIsrRetenido() {
		return getAttribute( "ISR" );
	}


	public String getMasEntradas() {
		return getAttribute( "ENTRADAS" );
	}


	public String getIvaAcreditable() {
		return getAttribute( "IVA" );
	}


	public String getMenosSalidas() {
		return getAttribute( "SALIDAS" );
	}


	public String getCarteraMesActual() {
		return getAttribute( "MESACT" );
	}


	public String getVariacionMensual() {
		return getAttribute( "VARMESAN" );
	}


	public String getCarteraAnteriorDirecto() {
		return getAttribute( "CPAND" );
	}


	public String getPorcentajeCarteraAnteriorDirecto() {
		return getAttribute( "PCPAND" );
	}


	public String getCarteraAnteriorReporto() {
		return getAttribute( "CPANR" );
	}


	public String getPorcentajeCarteraAnteriorReporto() {
		return getAttribute( "PCPANR" );
	}


	public String getCarteraAnteriorEfectivo() {
		return getAttribute( "CPANE" );
	}


	public String getPorcentajeCarteraAnteriorEfectivo() {
		return getAttribute( "PCPANE" );
	}


	public String getCarteraActualDirecto() {
		return getAttribute( "CPACD" );
	}


	public String getPorcentajeCarteraActualDirecto() {
		return getAttribute( "PCPACD" );
	}


	public String getCarteraActualReporto() {
		return getAttribute( "CPACR" );
	}


	public String getPorcentajeCarteraActualReporto() {
		return getAttribute( "PCPACR" );
	}


	public String getCarteraActualEfectivo() {
		return getAttribute( "CPACE" );
	}


	public String getPorcentajeCarteraActualEfectivo() {
		return getAttribute( "PCPACE" );
	}

	
//	public ReportosEstadoCuentaResponseTO( List<Map<String,String>> resumenes ) {
//		super( resumenes );
//		movimientos = new ArrayList<ReportosMovimientosEstadoCuentaResponseTO>();
//		ReportosMovimientosEstadoCuentaResponseTO detalle;
//		for( Map<String, String> mapa : resumenes ){
//			detalle = new ReportosMovimientosEstadoCuentaResponseTO( mapa );
//			movimientos.add( detalle );
//		}
//	}

	public Collection<ReportosMovimientosEstadoCuentaResponseTO> getMovimientos() {
		return movimientos;
	}

	
	public void setMovimientos(List<Map<String,String>> listaMovimientos) {
		movimientos = new ArrayList<ReportosMovimientosEstadoCuentaResponseTO>();
		ReportosMovimientosEstadoCuentaResponseTO detalle;
		for( Map<String, String> mapa : listaMovimientos ){
			detalle = new ReportosMovimientosEstadoCuentaResponseTO( mapa );
			movimientos.add( detalle );
		}
		this.movimientos = movimientos;
	}


	public Collection<ReportosOperacionesEstadoCuentaResponseTO> getOperacionesReportos() {
		return operacionesReportos;
	}


	public void setOperacionesReportos(List<Map<String,String>> listaOperacionesReportos) {
		operacionesReportos = new ArrayList<ReportosOperacionesEstadoCuentaResponseTO>();
		ReportosOperacionesEstadoCuentaResponseTO movimientosReportos;
		for( Map<String, String> mapa : listaOperacionesReportos ){
			movimientosReportos = new ReportosOperacionesEstadoCuentaResponseTO(mapa);
			operacionesReportos.add( movimientosReportos );
		}
		this.operacionesReportos = operacionesReportos;
	}


	public Collection<ReportosOperacionesDirectoEstadoCuentaResponseTO> getOperacionesDirecto() {
		return operacionesDirecto;
	}


	public void setOperacionesDirecto(List<Map<String,String>> listaOperacionesDirecto) {
		operacionesDirecto = new ArrayList<ReportosOperacionesDirectoEstadoCuentaResponseTO>();
		ReportosOperacionesDirectoEstadoCuentaResponseTO movimientosDirecto;
		for( Map<String, String> mapa : listaOperacionesDirecto ){
			movimientosDirecto = new ReportosOperacionesDirectoEstadoCuentaResponseTO(mapa);
			operacionesDirecto.add( movimientosDirecto );
		}
		this.movimientos = movimientos;
		this.operacionesDirecto = operacionesDirecto;
	}

}
