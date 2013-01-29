package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class PagoServicioLuzResponsetTO implements Serializable{
	
	private static final long serialVersionUID = -6465233451040995230L;
	
	private BigDecimal comision;
	private BigDecimal iva;
	private BigDecimal total;
	private String cuentaReferencia;
	private String digitoVerificador;
	private String fechaAplicacion;
	private String fechaVencimiento;
	private String numeroOperacion;
	private DispositivoHuellaTO dispositivoHuellaTO;	
	private Map<String, String> mapCuentas;
	private Map<String, String> mapServicios;
	
	public Map<String, String> getMapServicios() {
		return mapServicios;
	}
	public void setMapServicios(Map<String, String> mapServicios) {
		this.mapServicios = mapServicios;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
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
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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
	

}
