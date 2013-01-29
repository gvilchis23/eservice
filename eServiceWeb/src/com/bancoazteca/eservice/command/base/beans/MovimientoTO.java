package com.bancoazteca.eservice.command.base.beans;

public class MovimientoTO {

	private String fecha_movimiento;
	private String concepto;
	private String importe;
	private String saldo_total;
	private String num_movimiento;
	

	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getSaldo_total() {
		return saldo_total;
	}
	public void setSaldo_total(String saldo_total) {
		this.saldo_total = saldo_total;
	}
	public String getNum_movimiento() {
		return num_movimiento;
	}
	public void setNum_movimiento(String num_movimiento) {
		this.num_movimiento = num_movimiento;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}

}
