package com.bancoazteca.eservice.command.frecuentes.transferencias;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ModificarFrecuentesTransferenciasForm extends FormBean {

	private String tipo_transferencia;
	private String numero_cuenta_actual;
	private String numero_cuenta_nuevo;
	
	private String codigo_swift_aba;
	private String nombre_beneficiario;
	
	private String alias;
	private String banco;
	private String beneficiario;
	
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	
	private String numero_celular;
	private String correo_electronico;
	private String numero_casa;
	private String numero_oficina;
	private String compania_celular;
	
	public String getCompania_celular() {
		return compania_celular;
	}


	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}


	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			
			if(Validator.isEmptyData(numero_cuenta_actual)){
				error.add("numero_cuenta_actual", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_ERROR, "cuenta actual");
			}else if(!Validator.checkNumeric(numero_cuenta_actual)){
				error.add("numero_cuenta_actual", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_NUMERICO_ERROR, "cuenta actual");
			}else if(tipo_transferencia.equalsIgnoreCase("SPEI")){ 
				
				if(numero_cuenta_actual.length() != 16 && numero_cuenta_actual.length() != 18){
					error.add("numero_cuenta_actual", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR, "cuenta actual", "16 ó 18");
				}
			}else if(tipo_transferencia.equalsIgnoreCase("TERCEROS")){ 
				
				if(numero_cuenta_actual.length() != 14){
					error.add("numero_cuenta_actual", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR, "cuenta actual", "14");
				}
			}
			
			if(!Validator.isEmptyData(numero_cuenta_nuevo)){
				if(!Validator.checkNumeric(numero_cuenta_nuevo)){
					error.add("numero_cuenta_nuevo", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_NUMERICO_ERROR, "cuenta nueva");
				}else if(tipo_transferencia.equalsIgnoreCase("SPEI")){ 
					
					if(numero_cuenta_nuevo.length() != 16 && numero_cuenta_nuevo.length() != 18){
						error.add("numero_cuenta_nuevo", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR, "cuenta nueva", "16 ó 18");
					}
				}else if(tipo_transferencia.equalsIgnoreCase("TERCEROS")){ 
					
					if(numero_cuenta_nuevo.length() != 14){
						error.add("numero_cuenta_nuevo", FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR, "cuenta nueva", "14");
					}
				}
			}
			
			if(!tipo_transferencia.equalsIgnoreCase("TERCEROS")){
				if(Validator.isEmptyData(nombre_beneficiario)){
					error.add("nombre_beneficiario", FRECUENTES_TRANSFERENCIA_NUMERO_BENEFICIARIO_ERROR);
				}else if(!Validator.checkOnlyText(nombre_beneficiario)){
					error.add("nombre_beneficiario", FRECUENTES_TRANSFERENCIA_NOMBRE_LETRAS, "nombre del beneficiario");
				}
			}
			
			if(tipo_transferencia.equalsIgnoreCase("TERCEROS"))
			{
				if(Validator.isEmptyData(alias)){
					error.add("alias", FRECUENTES_TRANSFERENCIA_ALIAS, "alias");
				}
			}
			
			if(!Validator.isEmptyData(alias)){
				if(!Validator.checkAlphanumeric(alias)){
					error.add("alias", FRECUENTES_TRANSFERENCIA_ALIAS_ALFANUMERICO, "alias");
				}
			}
			
			
			if(tipo_transferencia.equalsIgnoreCase("INTERNACIONAL"))
			{
				if(Validator.isEmptyData(codigo_swift_aba)){
					error.add("codigo_swift_aba", FRECUENTES_TRANSFERENCIA_NUMERO_SWIFT_ERROR);
				}else if(!Validator.checkAlphanumeric(codigo_swift_aba)){
					error.add("codigo_swift_aba", FRECUENTES_TRANSFERENCIA_ALIAS_ALFANUMERICO, "Swift");
				}
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
	
	
	public String getNumero_cuenta_nuevo() {
		return numero_cuenta_nuevo;
	}
	public void setNumero_cuenta_nuevo(String numero_cuenta_nuevo) {
		this.numero_cuenta_nuevo = numero_cuenta_nuevo;
	}
	public String getCodigo_swift_aba() {
		return codigo_swift_aba;
	}
	public void setCodigo_swift_aba(String codigo_swift_aba) {
		this.codigo_swift_aba = codigo_swift_aba;
	}
	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
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
	public String getTipo_transferencia() {
		return tipo_transferencia;
	}
	public void setTipo_transferencia(String tipo_transferencia) {
		this.tipo_transferencia = tipo_transferencia;
	}
	public String getNumero_cuenta_actual() {
		return numero_cuenta_actual;
	}
	public void setNumero_cuenta_actual(String numero_cuenta_actual) {
		this.numero_cuenta_actual = numero_cuenta_actual;
	}

	
	/* atributos de las nuevas modificaciones */
	public String getNumero_celular() {
		return numero_celular;
	}
	public void setNumero_celular(String numero_celular) {
		this.numero_celular = numero_celular;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
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


	public String getBeneficiario() {
		return beneficiario;
	}


	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
}

