package com.bancoazteca.eservice.command.base.beans;

public class Datos_TarjetaTO {

	private String titular;
	private String numero_cuenta;
	private String numero_tarjeta;
	private String tipo_tarjeta;
	private String estado_tarjeta;
	private String sucursal_actual;
	private String centro_actual_nombre;
	private String centro_actual_direccion;
	
	
	
	
	public String getCentro_actual_nombre() {
		return centro_actual_nombre;
	}
	public void setCentro_actual_nombre(String centroActualNombre) {
		centro_actual_nombre = centroActualNombre;
	}
	public String getCentro_actual_direccion() {
		return centro_actual_direccion;
	}
	public void setCentro_actual_direccion(String centroActualDireccion) {
		centro_actual_direccion = centroActualDireccion;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numeroCuenta) {
		numero_cuenta = numeroCuenta;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numeroTarjeta) {
		numero_tarjeta = numeroTarjeta;
	}
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipoTarjeta) {
		tipo_tarjeta = tipoTarjeta;
	}
	public String getEstado_tarjeta() {
		return estado_tarjeta;
	}
	public void setEstado_tarjeta(String estadoTarjeta) {
		estado_tarjeta = estadoTarjeta;
	}
	public String getSucursal_actual() {
		return sucursal_actual;
	}
	public void setSucursal_actual(String sucursalActual) {
		sucursal_actual = sucursalActual;
	}
	
	
	
	
}
