package com.bancoazteca.eservice.command.transferencias.internacionales;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class TransferenciaInternacionalForm extends FormBean{

	private String cuenta_cargo;
	private String cuenta_destino;
	private String clave_banco;
	private String nombre_beneficiario;
	private String apellido_paterno_beneficiario;
	private String apellido_materno_beneficiario;
	private String nacionalidad_beneficiario;
	private String domicilio_beneficiario;
	private String dia_nacimiento_beneficiario;
	private String mes_nacimiento_beneficiario;
	private String anio_nacimiento_beneficiario;
	private String lugar_nacimiento_beneficiario;
	private String concepto_transferencia;
	private String importe_dolares;
	
	private String opcion_seguridad;
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public String getClave_banco() {
		return clave_banco;
	}
	public void setClave_banco(String clave_banco) {
		this.clave_banco = clave_banco;
	}
	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}
	public String getApellido_paterno_beneficiario() {
		return apellido_paterno_beneficiario;
	}
	public void setApellido_paterno_beneficiario(
			String apellido_paterno_beneficiario) {
		this.apellido_paterno_beneficiario = apellido_paterno_beneficiario;
	}
	public String getApellido_materno_beneficiario() {
		return apellido_materno_beneficiario;
	}
	public void setApellido_materno_beneficiario(
			String apellido_materno_beneficiario) {
		this.apellido_materno_beneficiario = apellido_materno_beneficiario;
	}
	public String getNacionalidad_beneficiario() {
		return nacionalidad_beneficiario;
	}
	public void setNacionalidad_beneficiario(String nacionalidad_beneficiario) {
		this.nacionalidad_beneficiario = nacionalidad_beneficiario;
	}
	public String getDomicilio_beneficiario() {
		return domicilio_beneficiario;
	}
	public void setDomicilio_beneficiario(String domicilio_beneficiario) {
		this.domicilio_beneficiario = domicilio_beneficiario;
	}
	public String getDia_nacimiento_beneficiario() {
		return dia_nacimiento_beneficiario;
	}
	public void setDia_nacimiento_beneficiario(String dia_nacimiento_beneficiario) {
		this.dia_nacimiento_beneficiario = dia_nacimiento_beneficiario;
	}
	public String getMes_nacimiento_beneficiario() {
		return mes_nacimiento_beneficiario;
	}
	public void setMes_nacimiento_beneficiario(String mes_nacimiento_beneficiario) {
		this.mes_nacimiento_beneficiario = mes_nacimiento_beneficiario;
	}
	public String getAnio_nacimiento_beneficiario() {
		return anio_nacimiento_beneficiario;
	}
	public void setAnio_nacimiento_beneficiario(String anio_nacimiento_beneficiario) {
		this.anio_nacimiento_beneficiario = anio_nacimiento_beneficiario;
	}
	public String getLugar_nacimiento_beneficiario() {
		return lugar_nacimiento_beneficiario;
	}
	public void setLugar_nacimiento_beneficiario(
			String lugar_nacimiento_beneficiario) {
		this.lugar_nacimiento_beneficiario = lugar_nacimiento_beneficiario;
	}
	public String getConcepto_transferencia() {
		return concepto_transferencia;
	}
	public void setConcepto_transferencia(String concepto_transferencia) {
		this.concepto_transferencia = concepto_transferencia;
	}
	public String getImporte_dolares() {
		return importe_dolares;
	}
	public void setImporte_dolares(String importe_dolares) {
		this.importe_dolares = importe_dolares;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	
	
	public MessageErrors validate(){
		MessageErrors errors= new MessageErrors();
		String commando = getComando();
		
		if(commando.equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(cuenta_destino)){
				errors.add("cuenta_destino", TRANSFERENCIAS_INTERNACIONALES_CUENTAS_ERROR, "destino");
			}else{	
				if(!Validator.checkNumeric(cuenta_destino)){
					errors.add("cuenta_destino", TRANSFERENCIAS_INTERNACIONALES_CUENTAS_NUMERICAS_ERROR, "destino");
				}
			}
			
			if(Validator.isEmptyData(cuenta_cargo)){
				errors.add("cuenta_cargo", TRANSFERENCIAS_INTERNACIONALES_CUENTAS_ERROR, "cargo");
			}else if(!Validator.checkNumeric(cuenta_cargo)){
					errors.add("cuenta_cargo", TRANSFERENCIAS_INTERNACIONALES_CUENTAS_NUMERICAS_ERROR, "cargo");
			}
			
			if(Validator.isEmptyData(clave_banco)){
				errors.add("clave_banco", TRANSFERENCIAS_INTERNACIONALES_CLAVE_BANCO_EMPTY);
			}
			
			if(Validator.isEmptyData(nombre_beneficiario)){
				errors.add("nombre_beneficiario", TRANSFERENCIAS_INTERNACIONALES_NOMBRE_BENEFICIARIO_EMPTY);
			}
			
			if(Validator.isEmptyData(apellido_paterno_beneficiario)){
				errors.add("apellido_paterno_beneficiario", TRANSFERENCIAS_INTERNACIONALES_APELLIDO_PATERNO_BENEFICIARIO_EMPTY);
			}
			
			if(Validator.isEmptyData(nacionalidad_beneficiario)){
				errors.add("nacionalidad_beneficiario", TRANSFERENCIAS_INTERNACIONALES_NACIONALIDAD_BENEFICIARIO_EMPTY);
			}
			
			if(Validator.isEmptyData(concepto_transferencia)){
				errors.add("concepto_transferencia", TRANSFERENCIAS_INTERNACIONALES_CONCEPTO_TRANSFERENCIA_EMTPY);
			}
			
			if(Validator.isEmptyData(importe_dolares)){
				errors.add("importe_dolares", TRANSFERENCIAS_INTERNACIONALES_IMPORTE_DOLARES_EMPTY);
			}else{
				if(!Validator.checkNumericBalance(importe_dolares)){
					errors.add("importe_dolares", TRANSFERENCIAS_INTERNACIONALES_IMPORTE_DOLARES_ERROR);
				}
			}
			
		}else if(commando.equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(clave_seguridad==null||Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errors.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errors.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad",OPCION_SEGURIDAD_ERROR);
			}
		}
		return errors;
	}
	
	
}
