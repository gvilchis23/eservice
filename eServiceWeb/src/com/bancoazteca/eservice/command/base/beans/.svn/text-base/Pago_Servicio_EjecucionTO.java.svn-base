package com.bancoazteca.eservice.command.base.beans;

public class Pago_Servicio_EjecucionTO {
	private String folio_aclaraciones;
	private String cuenta_cargo;
	private String numero_referencia;
	private String importe;
	private String comision;
	private String iva;
	private String total_pago;
	private String concepto_pago;
	private String fecha_aplicacion;
	private String titular_cuenta;
	private String movimiento;
	private String referencia;
		
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getTitular_cuenta() {
		return titular_cuenta;
	}
	public void setTitular_cuenta(String titular_cuenta) {
		this.titular_cuenta = titular_cuenta;
	}
	public String getFolio_aclaraciones() {
		return folio_aclaraciones;
	}
	public void setFolio_aclaraciones(String folio_aclaraciones) {
		this.folio_aclaraciones = folio_aclaraciones;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getTotal_pago() {
		return total_pago;
	}
	public void setTotal_pago(String total_pago) {
		this.total_pago = total_pago;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		if(concepto_pago!=null && concepto_pago.matches(".*IUSACELL SECSA")){
			concepto_pago="Pago del Servicio a IUSACELL";
		}
		this.concepto_pago = concepto_pago;
	}
	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}
	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}

}
