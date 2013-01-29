package com.bancoazteca.eservice.command.frecuentes.transferencias;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AltaFrecuenteInternacionalForm extends FormBean{

	private String nombre_beneficiario;
	private String numero_cuenta;
	private String codigo_swift_aba;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(numero_cuenta)){
				error.add("numero_cuenta", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_ERROR, "cuenta");
			}else if(!Validator.checkNumeric(numero_cuenta)){
				error.add("numero_cuenta", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_NUMERICO_ERROR, "cuenta");
			}/*else if(numero_cuenta.length() != 16 || numero_cuenta.length() != 14){
				error.add("numero_cuenta", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR, "cuenta");
			}*/
			
			if(Validator.isEmptyData(nombre_beneficiario)){
				error.add("nombre_beneficiario", FRECUENTES_TRANSFERENCIA_NUMERO_BENEFICIARIO_ERROR);
			}
			
			if(Validator.isEmptyData(codigo_swift_aba)){
				error.add("codigo_swift_aba", FRECUENTES_TRANSFERENCIA_NUMERO_SWIFT_ERROR);
			}
			
		}else if(getComando().equalsIgnoreCase("EJECUCION")){
			if(Validator.isEmptyData(clave_seguridad.toString())){
				error.add("clave_seguridad", FRECUENTES_TRANSFERENCIA_NUMERO_CLAVE_SEGURIDAD_ERROR);
			}
			
			if(Validator.isEmptyData(opcion_seguridad.toString())){
				error.add("opcion_seguridad", FRECUENTES_TRANSFERENCIA_NUMERO_OPCION_SEGURIDAD_ERROR);
			}
		}
	
		return error;
	}


	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}


	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}


	public String getNumero_cuenta() {
		return numero_cuenta;
	}


	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}


	public String getCodigo_swift_aba() {
		return codigo_swift_aba;
	}


	public void setCodigo_swift_aba(String codigo_swift_aba) {
		this.codigo_swift_aba = codigo_swift_aba;
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
		
}
