package com.bancoazteca.eservice.command.base.beans;

public class Detalle_MovimientoTO {

	private String fecha_aplicacion;
	private String concepto_movimiento;
	private String cargo;
	private String abono;
	private String saldo_disponible;
	
	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}
	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}
	public String getConcepto_movimiento() {
		return concepto_movimiento;
	}
	public void setConcepto_movimiento(String concepto_movimiento) {
		this.concepto_movimiento = concepto_movimiento;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getAbono() {
		return abono;
	}
	public void setAbono(String abono) {
		this.abono = abono;
	}
	public String getSaldo_disponible() {
		return saldo_disponible;
	}
	public void setSaldo_disponible(String saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}	
	
}
