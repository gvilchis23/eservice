package com.bancoazteca.eservice.command.operacionesFrecuentes;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class OperacionesFrecuentesForm extends FormBean{

	private String numero_tarjeta;
	private String cuenta_cargo;
	private String importe;
	private String cuenta_destino;
	private String banco_destino;
	private String rfc_curp;
	private String beneficiario;
	private String concepto;
	private String referencia;
	private String fecha_aplicacion;
	private String rfc_beneficiario;
	private String iva_deducir;
	private String deducir_impuestos;
	private String tipo_cuenta_destino;
	private String numero_referencia;
	private String concepto_pago;
	private String digito_verificador;
	private String monto;
	private String iva;
	private String folio_aclaracion;
	private String comision;
	private String numero_telefonico;
	private String carrier;
	private String nombre_banco;
	
	private String operacion_frecuente;
	private String alias;
	private String clave_operacion;
	
	public MessageErrors validate(){		
		MessageErrors error = new MessageErrors();	

		
		if(getTipo_operacion().equalsIgnoreCase("AGREGAR")){
			if(Validator.isEmptyData(getOperacion_frecuente())){
				error.add("operacion_frecuente", VALIDATOR_OPERACIONES_FRECUENTES_OPERACION_FRECUENTE_EMPTY);
			}
		}else if(getTipo_operacion().equalsIgnoreCase("ELIMINAR")){
			if(Validator.isEmptyData(getClave_operacion())){
				error.add("clave_operacion", VALIDATOR_OPERACIONES_FRECUENTES_CLAVE_OPERACION_EMPTY);
			}else if(!Validator.checkNumeric(getClave_operacion())){
				error.add("clave_operacion", VALIDATOR_OPERACIONES_FRECUENTES_CLAVE_OPERACION_ERROR);
			}
		}
		
		if(Validator.isEmptyData(getAlias())){
			error.add("alias", VALIDATOR_OPERACIONES_FRECUENTES_ALIAS_EMPTY);
		}
		
	
	   return error;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
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
	public String getTipo_cuenta_destino() {
		return tipo_cuenta_destino;
	}
	public void setTipo_cuenta_destino(String tipo_cuenta_destino) {
		this.tipo_cuenta_destino = tipo_cuenta_destino;
	}
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}
	public String getDigito_verificador() {
		return digito_verificador;
	}
	public void setDigito_verificador(String digito_verificador) {
		this.digito_verificador = digito_verificador;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getFolio_aclaracion() {
		return folio_aclaracion;
	}
	public void setFolio_aclaracion(String folio_aclaracion) {
		this.folio_aclaracion = folio_aclaracion;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getNumero_telefonico() {
		return numero_telefonico;
	}
	public void setNumero_telefonico(String numero_telefonico) {
		this.numero_telefonico = numero_telefonico;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getOperacion_frecuente() {
		return operacion_frecuente;
	}
	public void setOperacion_frecuente(String operacion_frecuente) {
		this.operacion_frecuente = operacion_frecuente;
	}

	public String getClave_operacion() {
		return clave_operacion;
	}

	public void setClave_operacion(String clave_operacion) {
		this.clave_operacion = clave_operacion;
	}

	public String getNombre_banco() {
		return nombre_banco;
	}

	public void setNombre_banco(String nombre_banco) {
		this.nombre_banco = nombre_banco;
	}
}
