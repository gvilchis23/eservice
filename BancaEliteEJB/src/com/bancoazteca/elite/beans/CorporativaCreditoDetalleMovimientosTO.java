package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CorporativaCreditoDetalleMovimientosTO implements Serializable{

	private static final long serialVersionUID = -7224216887178772399L;
	
	private BigDecimal abonoCargo;
	private String fechaAplicacion;
	private String fechaOperacion;
	private String lugar;
	private String moneda;
	private String importeScript;
	private BigDecimal importe;
	private String clasificacion;
	private String status;
	private BigDecimal abono;
	public BigDecimal getAbonoCargo() {
		return abonoCargo;
	}
	public void setAbonoCargo(BigDecimal abonoCargo) {
		this.abonoCargo = abonoCargo;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getImporteScript() {
		return importeScript;
	}
	public void setImporteScript(String importeScript) {
		this.importeScript = importeScript;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAbono() {
		return abono;
	}
	public void setAbono(BigDecimal abono) {
		this.abono = abono;
	}

}
