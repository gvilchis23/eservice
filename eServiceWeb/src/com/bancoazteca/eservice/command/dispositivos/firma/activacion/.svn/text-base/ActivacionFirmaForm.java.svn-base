package com.bancoazteca.eservice.command.dispositivos.firma.activacion;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;
import com.bancoazteca.elite.ejb.dispositivos.util.DispositivosTipoValidacionEnum;

public class ActivacionFirmaForm extends FormBean{

	private String numero_serie;
	private Cipher clave_seguridad;
	private String numero_celular;
	private String compania_celular;
	private String correo_electronico;
	private String numero_telefono;
	private String contrato_aceptado;	
	private String cuenta_cargo;
	private Cipher nip;
	private String tipo_validacion;
	private String opcion_seguridad;
	private String huella_seguridad;
	
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
	public String getTipo_validacion() {
		return tipo_validacion;
	}
	public void setTipo_validacion(String tipo_validacion) {
		this.tipo_validacion = tipo_validacion;
	}
	public String getNumero_serie() {
		return numero_serie;
	}
	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public Cipher getNip() {
		return nip;
	}
	public void setNip(Cipher nip) {
		this.nip = nip;
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
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getNumero_telefono() {
		return numero_telefono;
	}
	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}
	public String getContrato_aceptado() {
		return contrato_aceptado;
	}
	public void setContrato_aceptado(String contrato_aceptado) {
		this.contrato_aceptado = contrato_aceptado;
	}
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if (Validator.isEmptyData(tipo_validacion)) {
				errores.add("tipo_validacion", ACTIVACION_FIRMA_TIPO_VALIDACION);
			}
			else {
				try {
					DispositivosTipoValidacionEnum tipoValidacion = DispositivosTipoValidacionEnum.valueOf( tipo_validacion.toUpperCase() );

					switch (tipoValidacion) {
						case NUMERO_DE_SERIE : 	if(Validator.isEmptyData(numero_serie)){
													errores.add("numero_serie", ACTIVACION_FIRMA_NUMERO_SERIE);
												}
												else if (!Validator.checkNumeric(numero_serie)) {
													errores.add("numero_serie", ACTIVACION_FIRMA_NUMERO_SERIE_DECIMAL);					
												}
												else if (numero_serie.length()>12){ 
													errores.add("numero_serie", ACTIVACION_FIRMA_NUMERO_SERIE_LONGITUD);					
												}
												break;
						case TOKEN1:
						case TOKEN2:			if(clave_seguridad == null ||Validator.isEmptyData(clave_seguridad.toString())){
													errores.add("clave_seguridad", ACTIVACION_FIRMA_CLAVE_SEGURIDAD, "Firma Azteca");
												}
					}
				} catch (IllegalArgumentException e) {
					errores.add("tipo_validacion", ACTIVACION_FIRMA_TIPO_VALIDACION_INVALID);
				}
			}
		}
//		else 
//		if(getComando().equalsIgnoreCase("EJECUCION")){
//			if (Validator.isEmptyData(correo_electronico)) {
//				errores.add("correo_electronico", ACTIVACION_CORREO_ELECTRONICO_VACIO, "El correo electrónico");
//			}
//			else if (!Validator.checkEMail(correo_electronico)) {
//					errores.add("correo_electronico", ACTIVACION_CORREO_INVALIDO);
//			}
//			if (Validator.isEmptyData(numero_telefono)) {
//				if (Validator.isEmptyData(numero_celular)) 
//					errores.add("numero_telefonico", ACTIVACION_NUMEROS_TELEFONICOS);
//				else if (!Validator.checkNumeric(numero_celular) || numero_celular.length()!=10) {
//					errores.add("numero_celular", ACTIVACION_NUMERO_CELULAR_INVALID);
//				}
//			}
//			else if (!Validator.checkNumeric(numero_telefono) || numero_telefono.length()!=10) {
//				errores.add("numero_telefono", ACTIVACION_NUMERO_TELEFONO_INVALID);			
//			}
//			if (Validator.isEmptyData(contrato_aceptado)) {
//				errores.add("contrato_aceptado", ACTIVACION_CONTRATO_ACEPTO);
//			}
//			else if (!contrato_aceptado.equalsIgnoreCase("SI") && !contrato_aceptado.equalsIgnoreCase("NO")) {
//					errores.add("contrato_aceptado", ACTIVACION_CONTRATO_ACEPTO_INVALID);
//			}
//			if(CommandBase.TAG_NIP.equalsIgnoreCase(opcion_seguridad)){
//				if(Validator.isEmptyData(cuenta_cargo)){
//					errores.add("cuenta_cargo", ACTIVACION_CUENTA_CARGO);
//				}
//				else if (!Validator.checkNumeric(cuenta_cargo)) {
//					errores.add("cuenta_cargo", ACTIVACION_CUENTA_CARGO_DECIMAL);
//				}
//				if(nip == null || Validator.isEmptyData(nip.toString())){
//					errores.add("nip", ACTIVACION_NIP);
//				}
//			}else if(CommandBase.TAG_HUELLA.equalsIgnoreCase(opcion_seguridad)){
//				if(Validator.isEmptyData(huella_seguridad) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad)){
//					errores.add(CommandBase.TAG_HUELLA, HUELLA_SEGURIDAD, "huella");
//				}
//			}else{
//				errores.add("opcion_seguridad",OPCION_SEGURIDAD);
//			}	
//		}
		return  errores;
	}

	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}
}
