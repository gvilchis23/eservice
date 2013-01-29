package com.bancoazteca.eservice.command.frecuentes.enviodineroexpress;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AltaFrecuenteDineroExpressForm extends FormBean {
	
	private String nombre_beneficiario;
	private String apellido_paterno;
	private String apellido_materno;
	private String nombre_corto;
	private String fecha_nacimiento;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String cliente_id;
	
	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();	

		
	   if (getComando().equalsIgnoreCase("validacion")) {

		if (Validator.isEmptyData(nombre_beneficiario)){
			error.add("nombre_beneficiario", VALIDATOR_DINERO_EXPRESS_NOMBRE_BENEFICIARIO);
		}
		if (Validator.isEmptyData(apellido_paterno)){
			error.add("apellido_paterno", VALIDATOR_DINERO_EXPRESS_APELLIDO_PATERNO);
		}
//		if (Validator.isEmptyData(apellido_materno)){
//			error.add("apellido_materno", VALIDATOR_DINERO_EXPRESS_APELLIDO_MATERNO);
//		}
		if (Validator.isEmptyData(fecha_nacimiento)){
			fecha_nacimiento="01/01/1900";
			//error.add("fecha_nacimiento", VALIDATOR_DINERO_EXPRESS_FECHA_NACIMIENTO);
		}
		if(Validator.isEmptyData(nombre_corto)){	
			error.add("nombre_corto",VALIDATOR_DINERO_EXPRESS_NOMBRE_CORTO);
		}else{
			if(nombre_corto.length()>10){
				error.add("nombre_corto", VALIDATOR_DINERO_EXPRESS_NOMBRE_CORTO);
			}
		}
	  }
		return error;
	}
	
	public String getNombre_corto() {
		return nombre_corto;
	}

	public void setNombre_corto(String nombreCorto) {
		nombre_corto = nombreCorto;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}

	public String getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}

}
