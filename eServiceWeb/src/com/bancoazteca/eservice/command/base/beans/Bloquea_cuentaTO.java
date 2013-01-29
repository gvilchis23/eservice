package com.bancoazteca.eservice.command.base.beans;

public class Bloquea_cuentaTO {

	private String estado_anterior;
	private String nombre_cliente;
	private String estado_actual;
	private String numero_tarjeta;  
	private String fecha_movimiento;	
	private String folio_operacion;
	
	public String getEstado_anterior() {
		return estado_anterior;
	}
	public void setEstado_anterior(String estado_anterior) {
		this.estado_anterior = estado_anterior;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getEstado_actual() {
		return estado_actual;
	}
	public void setEstado_actual(String estado_actual) {
		this.estado_actual = estado_actual;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(String fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public String getFolio_operacion() {
		return folio_operacion;
	}
	public void setFolio_operacion(String folio_operacion) {
		this.folio_operacion = folio_operacion;
	}

}
