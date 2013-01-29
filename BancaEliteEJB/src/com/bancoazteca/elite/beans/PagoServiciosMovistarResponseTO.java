package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class PagoServiciosMovistarResponseTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	private String cuentaReferencia;
	private String digitoVerificador;
	private String cuentaCargo;
	private String tipoServicio;
	private String fechaAplicacion;
	private BigDecimal importe;
	private BigDecimal comision;
	private BigDecimal iva;
	private BigDecimal total;
	private String tokencode;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private Map<String, String> mapCuentas;
	private Map<String, String> mapServicios;
	private String cuentaCargoFormateada;
	private String operacionMovistar;
	private String folio;
	
	
	public void setCuentaReferencia(String cuentaReferencia) {
		this.cuentaReferencia = cuentaReferencia;
	}
	public String getCuentaReferencia() {
		return cuentaReferencia;
	}
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	public BigDecimal getComision() {
		return comision;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getTokencode() {
		return tokencode;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setMapCuentas(Map<String, String> mapCuentas) {
		this.mapCuentas = mapCuentas;
	}
	public Map<String, String> getMapCuentas() {
		return mapCuentas;
	}
	public Map<String, String> getMapServicios() {
		return mapServicios;
	}
	public void setMapServicios(Map<String, String> mapServicios) {
		this.mapServicios = mapServicios;
	}
	public String getCuentaCargoFormateada() {
		return cuentaCargoFormateada;
	}
	public void setCuentaCargoFormateada(String cuentaCargoFormateada) {
		this.cuentaCargoFormateada = cuentaCargoFormateada;
	}
	public void setOperacionMovistar(String operacionMovistar) {
		this.operacionMovistar = operacionMovistar;
	}
	public String getOperacionMovistar() {
		return operacionMovistar;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
}
