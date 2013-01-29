package com.bancoazteca.eservice.command.chequera.activacion;

import java.util.regex.Pattern;

import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ActivacionChequeraForm extends FormBean{
	
	private String cuenta_cargo;
	private String regimen;
	private String chequera_id;
//	private String tipo_chequera;
//	private String proteccion_chequera;
//	private String monto_proteccion;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String tipo_solicitud;
	
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}

	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

//	public String getTipo_chequera() {
//		return tipo_chequera;
//	}
//
//	public void setTipo_chequera(String tipo_chequera) {
//		this.tipo_chequera = tipo_chequera;
//	}
//
//	public String getProteccion_chequera() {
//		return proteccion_chequera;
//	}
//
//	public void setProteccion_chequera(String proteccion_chequera) {
//		this.proteccion_chequera = proteccion_chequera;
//	}
//
//	public String getMonto_proteccion() {
//		return monto_proteccion;
//	}
//
//	public void setMonto_proteccion(String monto_proteccion) {
//		this.monto_proteccion = monto_proteccion;
//	}

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

	public String getTipo_solicitud() {
		return tipo_solicitud;
	}

	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}

	public MessageErrors validate(){
		String commando = getComando();
		MessageErrors error = new MessageErrors();
		if( "SOLICITUD".equalsIgnoreCase( commando ) ){
			if( Validator.isEmptyData( tipo_solicitud ) ){
				error.add( "tipo_validacion", ACTIVACION_CHEQUERA_TIPO_SOLICITUD );
			}
			else{
				try{
					ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( tipo_solicitud.toUpperCase() );
					switch (tipoSolicitud) {
						case CUENTAS : break;
						
						case CHEQUERAS: 
							if( Validator.isEmptyData( cuenta_cargo ) ) {
								error.add( "cuenta_cargo", ACTIVACION_CHEQUERA_CUENTA_CARGO );
							}
							else if( !Validator.checkNumeric( cuenta_cargo ) ){
								error.add( "cuenta_cargo", ACTIVACION_CHEQUERA_CUENTA_CARGO_NUMERICA );
							}
							break;
							
						default : error.add( "tipo_validacion", ACTIVACION_CHEQUERA_TIPO_SOLICITUD_INVALID );
					}
				} catch ( IllegalArgumentException e ) {
					error.add( "tipo_validacion", ACTIVACION_CHEQUERA_TIPO_SOLICITUD_INVALID );
				}
			}
		}
		else if( "VALIDACION".equalsIgnoreCase( commando ) ){
			if( !Validator.isEmptyData( chequera_id ) ){
				if( !Validator.checkNumeric( chequera_id ) ){
					error.add( "cuenta_cargo", ACTIVACION_CHEQUERA_ID_CHEQUERA_NUMERICA );
				}
			}
			else{
				error.add( "chequera_id", ACTIVACION_CHEQUERA_ID_CHEQUERA );
			}
		}
		else if( "EJECUCION".equalsIgnoreCase( commando ) ){
			if(opcion_seguridad.equalsIgnoreCase( CommandBase.TAG_TOKEN ) ){
				if( Validator.isEmptyData( clave_seguridad.toString() ) ){
					error.add( CommandBase.TAG_TOKEN, ACTIVACION_CHEQUERA_OPCION_SEGURIDAD_ERROR, "clave");
				}
			}else if( opcion_seguridad.equalsIgnoreCase( CommandBase.TAG_HUELLA ) ){
				if( Validator.isEmptyData( huella_seguridad ) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad ) ){
					error.add( CommandBase.TAG_HUELLA, ACTIVACION_CHEQUERA_OPCION_SEGURIDAD_ERROR, "huella" );
				}
			}else{
				error.add( "opcion_seguridad", OPCION_SEGURIDAD );
			}	
		}

		return error;
	}

	public String getChequera_id() {
		return chequera_id;
	}

	public void setChequera_id(String chequera_id) {
		this.chequera_id = chequera_id;
	}
}
