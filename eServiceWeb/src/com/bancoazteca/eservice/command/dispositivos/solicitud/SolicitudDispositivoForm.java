package com.bancoazteca.eservice.command.dispositivos.solicitud;

import com.bancoazteca.elite.util.TipoDispositivoSeguridadEnum;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class SolicitudDispositivoForm extends FormBean{

	private String tipo_dispositivo;
	private String cuenta_cargo;
	// Direccion de entrega de dispositivos.
	private String direccion_entrega_dispositivos;
	private String estado_entrega_dispositivos;
	private String ciudad_entrega_dispositivos;
	private String colonia_entrega_dispositivos;
	private String codigo_postal_entrega_dispositivos;
	private String telefono_entrega_dispositivos;
	
	//Personas autoirizadas para recibir los biometricos.
	private String primer_nombre_persona_autorizada;
	private String primer_apellido_paterno_persona_autorizada;
	private String primer_apellido_materno_autorizada;
	
	private String segundo_nombre_persona_autorizada;
	private String segundo_apellido_paterno_persona_autorizada;
	private String segundo_apellido_materno_persona_autorizada;

	private String modificacion_direccion;
	
	private Cipher nip;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		if(getComando().equalsIgnoreCase("SELECCIONAR_CUENTA")){
			if(Validator.isEmptyData(tipo_dispositivo)){
				errores.add("tipo_dispositivo", VALIDA_SOLICITID_DISPOSITIVO_TIPO_DISPOSITIVO_EMPTY);
			}
			else{
				try { 
					TipoDispositivoSeguridadEnum.valueOf( tipo_dispositivo.toUpperCase() );
				} catch (IllegalArgumentException e) {
					errores.add("tipo_dispositivo", VALIDA_SOLICITID_DISPOSITIVO_TIPO_DISPOSITIVO_INVALIDO);
				}
			}
			if(Validator.isEmptyData(cuenta_cargo)){
				errores.add("cuenta_cargo", VALIDA_SOLICITID_DISPOSITIVO_CUENTA_CARGO_EMPTY);
			}
			else if (!Validator.checkNumeric(cuenta_cargo)) {
				errores.add("cuenta_cargo", VALIDA_SOLICITID_DISPOSITIVO_CUENTA_CARGO_DECIMAL);
			}
		}
		else if(getComando().equalsIgnoreCase("VALIDACION")){
			if (!Validator.isEmptyData(modificacion_direccion) && modificacion_direccion.equalsIgnoreCase("SI") ){
				if(Validator.isEmptyData(direccion_entrega_dispositivos)){
					errores.add("direccion_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_DIRECCION_EMPTY);
				}
				if(Validator.isEmptyData(estado_entrega_dispositivos)){
					errores.add("estado_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_ESTADO_EMPTY);
				}
				if(Validator.isEmptyData(ciudad_entrega_dispositivos)){
					errores.add("ciudad_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_CIUDAD_EMPTY);
				}
				if(Validator.isEmptyData(colonia_entrega_dispositivos)){
					errores.add("colonia_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_COLONIA_EMPTY);
				}
				if(Validator.isEmptyData(codigo_postal_entrega_dispositivos)){
					errores.add("codigo_postal_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_CP_EMPTY);
				}
				else if (!Validator.checkNumeric(codigo_postal_entrega_dispositivos)) {
					errores.add("codigo_postal_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_CP_DECIMAL);		
				}
				if(Validator.isEmptyData(telefono_entrega_dispositivos)){
					errores.add("telefono_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_EMPTY);
				}else if(!Validator.checkNumeric(telefono_entrega_dispositivos)){
					errores.add("telefono_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_NUMERIC);
				}else if(telefono_entrega_dispositivos.length()!= 10){
					errores.add("telefono_entrega_dispositivos", VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_LENGHT);
				}
			}
			if( !Validator.isEmptyData(primer_nombre_persona_autorizada )||
					!Validator.isEmptyData(primer_apellido_paterno_persona_autorizada ) ||
					!Validator.isEmptyData(primer_apellido_materno_autorizada )
				){
					if(Validator.isEmptyData(primer_nombre_persona_autorizada)){
						errores.add("primer_nombre_persona_autorizada", VALIDA_SOLICITID_DISPOSITIVO_NOMBRE_1_EMPTY);
					}
					if(Validator.isEmptyData(primer_apellido_paterno_persona_autorizada)){
						errores.add("primer_apellido_paterno_persona_autorizada", VALIDA_SOLICITID_DISPOSITIVO_PATERNO_1_EMPTY);
					}
					if(Validator.isEmptyData(primer_apellido_materno_autorizada)){
						errores.add("primer_apellido_materno_autorizada", VALIDA_SOLICITID_DISPOSITIVO_MATERNO_1_EMPTY);
					}
				}
				
			if( !Validator.isEmptyData(primer_nombre_persona_autorizada )||
					!Validator.isEmptyData(primer_apellido_paterno_persona_autorizada ) ||
					!Validator.isEmptyData(primer_apellido_materno_autorizada )
			){
				if(Validator.isEmptyData(segundo_nombre_persona_autorizada)){
					errores.add("segundo_nombre_persona_autorizada", VALIDA_SOLICITID_DISPOSITIVO_NOMBRE_2_EMPTY);
				}
				if(Validator.isEmptyData(segundo_apellido_paterno_persona_autorizada)){
					errores.add("segundo_apellido_paterno_persona_autorizada", VALIDA_SOLICITID_DISPOSITIVO_PATERNO_2_EMPTY);
				}
				if(Validator.isEmptyData(segundo_apellido_materno_persona_autorizada)){
					errores.add("segundo_apellido_materno_persona_autorizada", VALIDA_SOLICITID_DISPOSITIVO_MATERNO_2_EMPTY);
				}
			}
		}
		
//		else if(getComando().equalsIgnoreCase("EJECUCION")){	
//			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_NIP)){
//				if( nip == null || Validator.isEmptyData( nip.toString() ) ){
//					errores.add("nip", VALIDA_SOLICITID_DISPOSITIVO_NIP_EMPTY);
//				}
//			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
//				if(Validator.isEmptyData(huella_seguridad) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad)){
//					errores.add(CommandBase.TAG_HUELLA, HUELLA_SEGURIDAD, "huella");
//				}
//			}else{
//				errores.add("opcion_seguridad",OPCION_SEGURIDAD);
//			}	
//		}
		return  errores;
	}

	public String getDireccion_entrega_dispositivos() {
		return direccion_entrega_dispositivos;
	}

	public void setDireccion_entrega_dispositivos(
			String direccion_entrega_dispositivos) {
		this.direccion_entrega_dispositivos = direccion_entrega_dispositivos;
	}

	public String getEstado_entrega_dispositivos() {
		return estado_entrega_dispositivos;
	}

	public void setEstado_entrega_dispositivos(String estado_entrega_dispositivos) {
		this.estado_entrega_dispositivos = estado_entrega_dispositivos;
	}

	public String getCiudad_entrega_dispositivos() {
		return ciudad_entrega_dispositivos;
	}

	public void setCiudad_entrega_dispositivos(String ciudad_entrega_dispositivos) {
		this.ciudad_entrega_dispositivos = ciudad_entrega_dispositivos;
	}

	public String getColonia_entrega_dispositivos() {
		return colonia_entrega_dispositivos;
	}

	public void setColonia_entrega_dispositivos(String colonia_entrega_dispositivos) {
		this.colonia_entrega_dispositivos = colonia_entrega_dispositivos;
	}

	public String getCodigo_postal_entrega_dispositivos() {
		return codigo_postal_entrega_dispositivos;
	}

	public void setCodigo_postal_entrega_dispositivos(
			String codigo_postal_entrega_dispositivos) {
		this.codigo_postal_entrega_dispositivos = codigo_postal_entrega_dispositivos;
	}

	public String getTelefono_entrega_dispositivos() {
		return telefono_entrega_dispositivos;
	}

	public void setTelefono_entrega_dispositivos(
			String telefono_entrega_dispositivos) {
		this.telefono_entrega_dispositivos = telefono_entrega_dispositivos;
	}

	public String getModificacion_direccion() {
		return modificacion_direccion;
	}

	public void setModificacion_direccion(String modificacion_direccion) {
		this.modificacion_direccion = modificacion_direccion;
	}

	public String getPrimer_nombre_persona_autorizada() {
		return primer_nombre_persona_autorizada;
	}

	public void setPrimer_nombre_persona_autorizada(
			String primer_nombre_persona_autorizada) {
		this.primer_nombre_persona_autorizada = primer_nombre_persona_autorizada;
	}

	public String getPrimer_apellido_paterno_persona_autorizada() {
		return primer_apellido_paterno_persona_autorizada;
	}

	public void setPrimer_apellido_paterno_persona_autorizada(
			String primer_apellido_paterno_persona_autorizada) {
		this.primer_apellido_paterno_persona_autorizada = primer_apellido_paterno_persona_autorizada;
	}

	public String getPrimer_apellido_materno_autorizada() {
		return primer_apellido_materno_autorizada;
	}

	public void setPrimer_apellido_materno_autorizada(
			String primer_apellido_materno_autorizada) {
		this.primer_apellido_materno_autorizada = primer_apellido_materno_autorizada;
	}


	public String getSegundo_nombre_persona_autorizada() {
		return segundo_nombre_persona_autorizada;
	}

	public void setSegundo_nombre_persona_autorizada(
			String segundo_nombre_persona_autorizada) {
		this.segundo_nombre_persona_autorizada = segundo_nombre_persona_autorizada;
	}

	public String getSegundo_apellido_paterno_persona_autorizada() {
		return segundo_apellido_paterno_persona_autorizada;
	}

	public void setSegundo_apellido_paterno_persona_autorizada(
			String segundo_apellido_paterno_persona_autorizada) {
		this.segundo_apellido_paterno_persona_autorizada = segundo_apellido_paterno_persona_autorizada;
	}

	public Cipher getNip() {
		return nip;
	}

	public void setNip(Cipher nip) {
		this.nip = nip;
	}

	public String getSegundo_apellido_materno_persona_autorizada() {
		return segundo_apellido_materno_persona_autorizada;
	}

	public void setSegundo_apellido_materno_persona_autorizada(
			String segundo_apellido_materno_persona_autorizada) {
		this.segundo_apellido_materno_persona_autorizada = segundo_apellido_materno_persona_autorizada;
	}

	public String getTipo_dispositivo() {
		return tipo_dispositivo;
	}

	public void setTipo_dispositivo(String tipo_dispositivo) {
		this.tipo_dispositivo = tipo_dispositivo;
	}

	public String getCuenta_cargo() {
		return cuenta_cargo;
	}

	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
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

}
