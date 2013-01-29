package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class PagoServiciosAvicolaResponseTO implements Serializable {

	private static final long serialVersionUID = -6241581546895368577L;
	private String cuentaReferencia;
	private String digitoVerificador;
	private String cuentaCargo;
	private String tipoServicio;
	private String fechaAplicacion;
	private String folioAclaracion;
	private BigDecimal importe;
	private BigDecimal comision;
	private BigDecimal iva;
	private BigDecimal total;
	private String tokencode;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private Map<String, String> mapCuentas;
	private Map<String, String> mapServicios;
	
	
	
	public String getFolioAclaracion() {
		return folioAclaracion;
	}
	public void setFolioAclaracion(String folioAclaracion) {
		this.folioAclaracion = folioAclaracion;
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
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public Map<String, String> getMapCuentas() {
		return mapCuentas;
	}
	public void setMapCuentas(Map<String, String> mapCuentas) {
		this.mapCuentas = mapCuentas;
	}
	public Map<String, String> getMapServicios() {
		return mapServicios;
	}
	public void setMapServicios(Map<String, String> mapServicios) {
		this.mapServicios = mapServicios;
	}
	
	
	
	
	
	
	
	
	

}
