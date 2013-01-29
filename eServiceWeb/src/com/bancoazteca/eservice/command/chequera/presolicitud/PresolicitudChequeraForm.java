package com.bancoazteca.eservice.command.chequera.presolicitud;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PresolicitudChequeraForm extends FormBean{
	
	private String tipo_solicitud;
	
	private String numero_cuenta;
	private String regimen;
	private String tipo_chequera;
	private String proteccion_chequera;
	private String monto_proteccion;
	
	private String opcion_seguridad;
	private String huella_seguridad;
	private Cipher clave_seguridad;
	
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getTipo_chequera() {
		return tipo_chequera;
	}
	public void setTipo_chequera(String tipo_chequera) {
		this.tipo_chequera = tipo_chequera;
	}
	public String getProteccion_chequera() {
		return proteccion_chequera;
	}
	public void setProteccion_chequera(String proteccion_chequera) {
		this.proteccion_chequera = proteccion_chequera;
	}
	public String getMonto_proteccion() {
		return monto_proteccion;
	}
	public void setMonto_proteccion(String monto_proteccion) {
		this.monto_proteccion = monto_proteccion;
	}
	public String getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
	public MessageErrors validate(){
		return null;
	}
}
