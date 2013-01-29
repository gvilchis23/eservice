package com.bancoazteca.eservice.command.usuarios.altas;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;
import com.bancoazteca.eservice.beans.Cipher;

public class AltasUsuariosForm extends FormBean  {
	
	private String cuenta_cargo;
	private Cipher nip;
	private Cipher confirma_nip;
	private String dia;
	private String mes;
	private String anio;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String alias;
	private String correo_electronico;
	private String pregunta;
	private String respuesta;
	private String contrasenia;
	private String confirmar_contrasenia;
	

	public String getCuenta_cargo() {
		return cuenta_cargo;
	}


	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}

	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public String getAnio() {
		return anio;
	}


	public void setAnio(String anio) {
		this.anio = anio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public String getConfirmar_contrasenia() {
		return confirmar_contrasenia;
	}


	public void setConfirmar_contrasenia(String confirmar_contrasenia) {
		this.confirmar_contrasenia = confirmar_contrasenia;
	}
	
	public MessageErrors validate() {
		MessageErrors error = new MessageErrors();	
		
		if(getComando().equalsIgnoreCase("SOLICITUD")){
			
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuentaCargo", ALTAS_USUARIOS_CUENTA_CARGO_ERROR);
			}else{	
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuentaCargo", ALTAS_USUARIOS_CUENTA_CARGO_NUMERICA_ERROR);
				}
			}
		}if(getComando().equalsIgnoreCase("VALIDACION")){
			
			if(nip==null || confirma_nip==null){
				error.add("nip", ALTAS_USUARIOS_NIP_ERROR);
			}else if(Validator.isEmptyData(nip.toString()) || Validator.isEmptyData(confirma_nip.toString())){
				error.add("nip", ALTAS_USUARIOS_NIP_ERROR);
			}else if(!Validator.checkNumeric(nip.toString()) || !Validator.checkNumeric(confirma_nip.toString())){
					error.add("nip", ALTAS_USUARIOS_NIP_NUMERICA_ERROR);
			}else if(Validator.isEmptyData(dia) || Validator.isEmptyData(mes)|| Validator.isEmptyData(anio)){
					error.add("fecha", ALTAS_USUARIOS_FECHA_ERROR);
			}else if(!Validator.checkNumeric(dia)){
					error.add("dia", ALTAS_USUARIOS_DIA_NUMERICA_ERROR);
			}else if(!Validator.checkNumeric(mes)){
					error.add("mes", ALTAS_USUARIOS_MES_NUMERICA_ERROR);
			}else if(!Validator.checkNumeric(anio)){
					error.add("año", ALTAS_USUARIOS_ANO_NUMERICA_ERROR);
			}else if(Validator.isEmptyData(nombre) || Validator.checkNumeric(nombre)){
					error.add("nombre", ALTAS_USUARIOS_NOMBRE_ERROR);
			}else if(Validator.isEmptyData(apellido_paterno) || Validator.checkNumeric(apellido_paterno)){
					error.add("apellido_paterno", ALTAS_USUARIOS_APEPATERNO_ERROR);
			}else if(Validator.isEmptyData(apellido_materno) || Validator.checkNumeric(apellido_materno)){
					error.add("apellido_materno", ALTAS_USUARIOS_APEMATERNO_ERROR);
					
			}
			
		}if(getComando().equalsIgnoreCase("REGISTRAR")){
			
			if(Validator.isEmptyData(alias)){
				error.add("alias", ALTAS_USUARIOS_ALIAS_ERROR);
			}if(Validator.isEmptyData(correo_electronico)){
				error.add("correo_electronico", ALTAS_USUARIOS_EMAIL_ERROR);
			}if(Validator.isEmptyData(pregunta)){
				error.add("pregunta", ALTAS_USUARIOS_PREGUNTA_ERROR);
			}if(Validator.isEmptyData(respuesta)){
				error.add("respuesta", ALTAS_USUARIOS_RESPUESTA_ERROR);
			}
		}if(getComando().equalsIgnoreCase("EJECUCION")){
			
			if(Validator.isEmptyData(contrasenia)){
				error.add("contrasena", ALTAS_USUARIOS_CONTRASENA_ERROR);
			}if(Validator.isEmptyData(confirmar_contrasenia)){
				error.add("contrasena", ALTAS_USUARIOS_CONFIRMAR_ERROR);
			}
		}
		return error;
	}


	public Cipher getConfirma_nip() {
		return confirma_nip;
	}


	public void setConfirma_nip(Cipher confirma_nip) {
		this.confirma_nip = confirma_nip;
	}


	public Cipher getNip() {
		return nip;
	}


	public void setNip(Cipher nip) {
		this.nip = nip;
	}


	public String getCorreo_electronico() {
		return correo_electronico;
	}


	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}


	
}
