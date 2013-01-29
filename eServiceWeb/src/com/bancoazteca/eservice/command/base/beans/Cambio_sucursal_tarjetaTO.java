package com.bancoazteca.eservice.command.base.beans;

public class Cambio_sucursal_tarjetaTO {

	
	private String tipo_tarjeta;
	private String centro_actual_nombre;
	private String centro_actual;
	private String centro_actual_direccion;

	private String centro_nuevo_nombre;
	private String centro_nuevo;
	private String centro_nuevo_direccion;

	private String numero_tarjeta;
	private String numero_cuenta;
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipoTarjeta) {
		tipo_tarjeta = tipoTarjeta;
	}
	public String getCentro_actual_nombre() {
		return centro_actual_nombre;
	}
	public void setCentro_actual_nombre(String centroActualNombre) {
		centro_actual_nombre = centroActualNombre;
	}
	public String getCentro_actual() {
		return centro_actual;
	}
	public void setCentro_actual(String centroActual) {
		centro_actual = centroActual;
	}
	public String getCentro_actual_direccion() {
		return centro_actual_direccion;
	}
	public void setCentro_actual_direccion(String centroActualDireccion) {
		centro_actual_direccion = centroActualDireccion;
	}
	public String getCentro_nuevo_nombre() {
		return centro_nuevo_nombre;
	}
	public void setCentro_nuevo_nombre(String centroNuevoNombre) {
		centro_nuevo_nombre = centroNuevoNombre;
	}
	public String getCentro_nuevo() {
		return centro_nuevo;
	}
	public void setCentro_nuevo(String centroNuevo) {
		centro_nuevo = centroNuevo;
	}
	public String getCentro_nuevo_direccion() {
		return centro_nuevo_direccion;
	}
	public void setCentro_nuevo_direccion(String centroNuevoDireccion) {
		centro_nuevo_direccion = centroNuevoDireccion;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numeroTarjeta) {
		numero_tarjeta = numeroTarjeta;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numeroCuenta) {
		numero_cuenta = numeroCuenta;
	}
	
	
}
