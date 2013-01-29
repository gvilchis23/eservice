package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class PagoServiciosTiempoAireResponseTO implements Serializable {
	public static final long serialVersionUID = -54789412123l;
	private String cuentaCargo;
	private String telefono;
	private String monto;
	private Collection<String> montos;
	private String carrier;
	private String comision;
	private String iva;
	private String cuenta;
	private String folio;
	private String mensaje;
	private String nombreCliente;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private Map<String, String> mapCuentas;
	private String cuentaCargoFormateada;
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
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
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public Collection<String> getMontos() {
		return montos;
	}
	public void setMontos(Collection<String> montos) {
		this.montos = montos;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
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
	public String getCuentaCargoFormateada() {
		return cuentaCargoFormateada;
	}
	public void setCuentaCargoFormateada(String cuentaCargoFormateada) {
		this.cuentaCargoFormateada = cuentaCargoFormateada;
	}
	
}
