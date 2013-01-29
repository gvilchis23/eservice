package com.bancoazteca.eservice.command.chequera.liberacion;

import java.util.regex.Pattern;

import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class LiberacionChequesForm extends FormBean{

	private String numero_cuenta;
//	private String tipo_chequera;
	private String tipo_solicitud;
//	private String numero_chequera;
	private String chequera_id;
	private String monto_cheque;
	private String numero_cheque;
	private String opcion_seguridad;
	private String huella_seguridad;
	private Cipher clave_seguridad;
	
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
//	public String getTipo_chequera() {
//		return tipo_chequera;
//	}
//	public void setTipo_chequera(String tipo_chequera) {
//		this.tipo_chequera = tipo_chequera;
//	}
	public String getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
//	public String getNumero_chequera() {
//		return numero_chequera;
//	}
//	public void setNumero_chequera(String numero_chequera) {
//		this.numero_chequera = numero_chequera;
//	}
	public String getMonto_cheque() {
		return monto_cheque;
	}
	public void setMonto_cheque(String monto_cheque) {
		this.monto_cheque = monto_cheque;
	}
	public String getNumero_cheque() {
		return numero_cheque;
	}
	public void setNumero_cheque(String numero_cheque) {
		this.numero_cheque = numero_cheque;
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
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}	
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("SOLICITUD")){
			if (Validator.isEmptyData(tipo_solicitud)) {
				errores.add("tipo_solicitud", LIBERACION_CHEQUE_TIPO_SOLICITUD);
			}
			else {
				try {
					ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( tipo_solicitud.toUpperCase() );

					switch (tipoSolicitud) {
						case CHEQUERAS : 	
											if(Validator.isEmptyData(numero_cuenta)){
												errores.add("numero_cuenta", LIBERACION_CHEQUE_NUMERO_CUENTA_EMPTY);
											}
											if(!Validator.checkNumeric(numero_cuenta)){
												errores.add("numero_cuenta", LIBERACION_CHEQUE_NUMERO_CUENTA_DECIMAL, "numero de cuenta");
											}
											break;
						case CHEQUE:	
											if( !Validator.isEmptyData( chequera_id ) ){
												if( !Validator.checkNumeric( chequera_id ) ){
													errores.add( "chequera_id", LIBERACION_CHEQUERA_ID_CHEQUERA_NUMERICA );
												}
											}
											else{
												errores.add( "chequera_id", LIBERACION_CHEQUERA_ID_CHEQUERA );
											}
											break;
						}	
				}catch (IllegalArgumentException e) {
					errores.add("tipo_solicitud", LIBERACION_CHEQUE_TIPO_SOLICITUD_INVALID);
				}
			}
		}
		else
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(numero_cheque)){
				errores.add("numero_cheque", LIBERACION_CHEQUE_NUMERO_CHEQUE);
			}
			if(Validator.isEmptyData(monto_cheque)){
				errores.add("monto_cheque", LIBERACION_CHEQUE_MONTO_CHEQUE);
			}
		}
		else
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					errores.add(CommandBase.TAG_TOKEN, LIBERACION_CHEQUE_OPCION_SEGURIDAD_ERROR, "clave");
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					errores.add(CommandBase.TAG_HUELLA, LIBERACION_CHEQUE_OPCION_SEGURIDAD_ERROR, "huella");
				}
			}else{
				errores.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}
		return  errores;
	}
	public String getChequera_id() {
		return chequera_id;
	}
	public void setChequera_id(String chequera_id) {
		this.chequera_id = chequera_id;
	}
}
