package com.bancoazteca.eservice.command.transferencias.spei;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class TransferenciaSpeiForm extends FormBean {
	
	private String cuenta_destino;
	private String banco_destino;
	private String rfc_curp;
	private String beneficiario;
	private String cuenta_cargo;
	private String importe;
	private String concepto;
	private String referencia;
	private String tipo_cuenta_destino;
	private String fecha_aplicacion;
	
	private String rfc_beneficiario;
	private String iva_deducir;
	private String deducir_impuestos;
	
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	private String ip_cliente;
	
	public String getTipo_cuenta_destino() {
		return tipo_cuenta_destino;
	}

	public void setTipo_cuenta_destino(String tipo_cuenta_destino) {
		this.tipo_cuenta_destino = tipo_cuenta_destino;
	}

	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){			
			if(Validator.isEmptyData(cuenta_destino)){
				errores.add("cuenta_destino", TRANSFERENCIAS_SPEI_CUENTAS_ERROR, "destino");
			}else{	
				if(!Validator.checkNumeric(cuenta_destino)){
					errores.add("cuenta_destino", TRANSFERENCIAS_SPEI_CUENTAS_NUMERICAS_ERROR, "destino");
				}
			}
	
			if(Validator.isEmptyData(cuenta_cargo)){
				errores.add("cuenta_cargo", TRANSFERENCIAS_SPEI_CUENTAS_ERROR, "cargo");
			}else if(!Validator.checkNumeric(cuenta_cargo)){
					errores.add("cuenta_cargo", TRANSFERENCIAS_SPEI_CUENTAS_NUMERICAS_ERROR, "cargo");
			}else if(cuenta_cargo.length() != 14){
				errores.add("cuenta_cargo", TRANSFERENCIAS_SPEI_CUENTA_TAMANIO_ERROR);
			}
			
			if(Validator.isEmptyData(rfc_curp)){
				errores.add("rfc_curp", TRANSFERENCIAS_SPEI_RFC_CURP_ERROR, "rfccurp");
			}
			
			if(Validator.isEmptyData(referencia)){
				errores.add("referencia", TRANSFERENCIAS_SPEI_REFERENCIA_ERROR);
			}
			
			if(Validator.isEmptyData(importe)){
				errores.add("importe", TRANSFERENCIAS_SPEI_IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					errores.add("importe", TRANSFERENCIAS_SPEI_IMPORTE_DECIMAL_ERROR);
				}
			}
			
		}else if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(clave_seguridad==null || Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errores.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errores.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				errores.add("opcion_seguridad",TRANSFERENCIAS_SPEI_SEGURIDAD_ERROR);
			}
		}
		return  errores;
	}
	
	public String getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public String getBanco_destino() {
		return banco_destino;
	}
	public void setBanco_destino(String banco_destino) {
		this.banco_destino = banco_destino;
	}
	public String getRfc_curp() {
		return rfc_curp;
	}
	public void setRfc_curp(String rfc_curp) {
		this.rfc_curp = rfc_curp;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}

	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}

	public String getRfc_beneficiario() {
		return rfc_beneficiario;
	}

	public void setRfc_beneficiario(String rfc_beneficiario) {
		this.rfc_beneficiario = rfc_beneficiario;
	}

	public String getIva_deducir() {
		return iva_deducir;
	}

	public void setIva_deducir(String iva_deducir) {
		this.iva_deducir = iva_deducir;
	}

	public String getDeducir_impuestos() {
		return deducir_impuestos;
	}

	public void setDeducir_impuestos(String deducir_impuestos) {
		this.deducir_impuestos = deducir_impuestos;
	}

	public String getIp_cliente() {
		return ip_cliente;
	}

	public void setIp_cliente(String ip_cliente) {
		this.ip_cliente = ip_cliente;
	}
}