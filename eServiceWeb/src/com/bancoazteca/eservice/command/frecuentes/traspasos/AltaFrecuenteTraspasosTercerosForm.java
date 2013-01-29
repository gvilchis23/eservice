package com.bancoazteca.eservice.command.frecuentes.traspasos;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.validator.MessageErrors;
import com.bancoazteca.eservice.command.base.FormBean;

public class AltaFrecuenteTraspasosTercerosForm extends FormBean{

	private static Logger log = Logger.getLogger(AltaFrecuenteTraspasosTercerosForm.class);
	private String tipo_operacion;
	
	private String index;
	private String confirmarEliminacion;

	private String beneficiario;
	private String numero_cuenta;
	private String swift;
	private String opcionSwift;
	private String nameTxt;
	private String tokenCode;
	
	
	private String alias_beneficiario;
	private String email;
	private String numero_celular;
	private String compania_celular;
	private String telCasa;
	private String telOficina;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String numero_casa;
	private String numero_oficina;
	private String correo_electronico;
	
	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		AltaFrecuenteTraspasosTercerosForm.log = log;
	}

	public String getNumero_casa() {
		return numero_casa;
	}

	public void setNumero_casa(String numero_casa) {
		this.numero_casa = numero_casa;
	}

	public String getNumero_oficina() {
		return numero_oficina;
	}

	public void setNumero_oficina(String numero_oficina) {
		this.numero_oficina = numero_oficina;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}

	public MessageErrors validateDataSkyPayment() {
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			log.info("Entra a validar la forma");

		
			if(Validator.isEmptyData(alias_beneficiario)){
				errors.add("alias_beneficiario", FRECUENTES_TRASPASOS_ALIAS_ERROR);
			}		
			if(Validator.isEmptyData(numero_cuenta)){
				errors.add("numero_cuenta",FRECUENTES_TRASPASOS_CUENTA_ERROR);
			}
		}	
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if (clave_seguridad==null || clave_seguridad.toString().trim().length()==0){
				errors.add("clave_seguridad", FRECUENTES_TRASPASOS_CLAVE_SEGURIDAD);
			}
			if(Validator.isEmptyData(opcion_seguridad)){
				errors.add("opcion_seguridad", FRECUENTES_TRASPASOS_OPCION_SEGURIDAD);
			}
		}
		
		return errors;
	}
	
	public String getTipo_operacion() {
		return tipo_operacion;
	}

	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}

	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getConfirmarEliminacion() {
		return confirmarEliminacion;
	}
	public void setConfirmarEliminacion(String confirmarEliminacion) {
		this.confirmarEliminacion = confirmarEliminacion;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public String getOpcionSwift() {
		return opcionSwift;
	}
	public void setOpcionSwift(String opcionSwift) {
		this.opcionSwift = opcionSwift;
	}
	public String getNameTxt() {
		return nameTxt;
	}
	public void setNameTxt(String nameTxt) {
		this.nameTxt = nameTxt;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNumero_celular() {
		return numero_celular;
	}

	public void setNumero_celular(String numero_celular) {
		this.numero_celular = numero_celular;
	}

	public String getCompania_celular() {
		return compania_celular;
	}

	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}

	public String getTelCasa() {
		return telCasa;
	}
	public void setTelCasa(String telCasa) {
		this.telCasa = telCasa;
	}
	public String getTelOficina() {
		return telOficina;
	}
	public void setTelOficina(String telOficina) {
		this.telOficina = telOficina;
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
	public String getAlias_beneficiario() {
		return alias_beneficiario;
	}
	public void setAlias_beneficiario(String alias_beneficiario) {
		this.alias_beneficiario = alias_beneficiario;
	}
}