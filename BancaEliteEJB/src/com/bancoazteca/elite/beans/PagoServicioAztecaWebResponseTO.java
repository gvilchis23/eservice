package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class PagoServicioAztecaWebResponseTO implements Serializable {

	private static final long serialVersionUID = -937460808261953303L;
	
	private String fechaAplicacion;
	private String cuentaCargo;
	private String cuentaReferencia;
	private String digitoVerificador;
	private BigDecimal importe;
	private BigDecimal total;
	private BigDecimal comision;
	private BigDecimal iva;
	private DispositivoHuellaTO dispositivoHuellaTO;
	Map<String, String> mapCuentas;
	Map<String, String> mapServicios;
	private String concepto_pago;
	private String numeroOperacion;
	private String folio;
	
	
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public Map<String, String> getMapServicios() {
		return mapServicios;
	}
	public void setMapServicios(Map<String, String> mapServicios) {
		this.mapServicios = mapServicios;
	}
	public Map<String, String> getMapCuentas() {
		return mapCuentas;
	}
	public void setMapCuentas(Map<String, String> mapCuentas) {
		this.mapCuentas = mapCuentas;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getCuentaReferencia() {
		return cuentaReferencia;
	}
	public void setCuentaReferencia(String cuentaReferencia) {
		this.cuentaReferencia = cuentaReferencia;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public BigDecimal getComision() {
		return comision;
	}
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String toString (){
		StringBuffer obj= new StringBuffer();
		obj.append("PagoServicioAztecaWebResponseTO: ");
		obj.append("\nfechaAplicacion: "+fechaAplicacion);
		obj.append("\ncuentaCargo: "+cuentaCargo);
		obj.append("\ncuentaReferencia: "+cuentaReferencia);
		obj.append("\ndigitoVerificador: "+digitoVerificador);
		obj.append("\nimporte: "+importe);
		obj.append("\ntotal: "+total);
		obj.append("\ncomision: "+comision);
		obj.append("\niva: "+iva);
		obj.append("\ndispositivoHuellaTO: "+dispositivoHuellaTO);
		obj.append("\nmapCuentas: "+mapCuentas);
		obj.append("\nmapServicios: " +mapServicios);
		obj.append("\nconcepto_pago: "+concepto_pago);
		return obj.toString();
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
}
