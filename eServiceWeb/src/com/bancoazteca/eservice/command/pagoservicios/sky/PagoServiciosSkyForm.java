package com.bancoazteca.eservice.command.pagoservicios.sky;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;


/**
 * @author 
 *
 */
public class PagoServiciosSkyForm extends FormBean {

	private static final long serialVersionUID = -603519651185468935L;
	
	private String numero_referencia;
	private String cuenta_cargo;
	private String concepto_pago;
	private String importe;
	private String comision;
	private String iva;
	private String total_pago;
	private String agregarReferenciaSky;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	
	public void reset(){
		this.numero_referencia = null;
		this.cuenta_cargo = "-1";
		this.concepto_pago = null;
		this.importe = null;
		this.comision = null;
		this.iva = null;
		this.total_pago = null;
	}
	
	

	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){

			if(cuenta_cargo.equals("-1") || Validator.isEmptyData(cuenta_cargo)){
				errors.add("cuentaCargo", PAGOSERVICIOS_SKY_CUENTA_ERROR);
			}
			if(Validator.isEmptyData(numero_referencia)){
				errors.add("cuentaReferencia", PAGOSERVICIOS_SKY_CUENTA_REF_ERROR);
			}else if(!Validator.checkNumeric(numero_referencia)){
				errors.add("cuentaReferencia", PAGOSERVICIOS_SKY_CUENTA_REF_CARACTER_ERROR);
			}else if(numero_referencia.length() != 12){
				errors.add("cuentaReferencia", PAGOSERVICIOS_SKY_CUENTA_REF_SIZE_ERROR);
			}			
			if(Validator.isEmptyData(importe)){
				errors.add("importe",PAGOSERVICIOS_SKY_MONTO_ERROR);
			}else if (!Validator.checkDecimal(importe)) {
				errors.add("importe",PAGOSERVICIOS_SKY_FORMATOMONTO_ERROR);
			}else if (Double.parseDouble(importe)<=0) {
				errors.add("importe", PAGOSERVICIOS_SKY_MONTO_ERROR);
			}
			
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					errors.add(CommandBase.TAG_TOKEN, PAGOSERVICIOS_SKY_CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					errors.add(CommandBase.TAG_HUELLA, PAGOSERVICIOS_SKY_CLAVE_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad", PAGOSERVICIOS_SKY_OPCION_SEGURIDAD);				
			}		
		  }
		}
		return errors;
	}



	


	public String getCuenta_cargo() {
		return cuenta_cargo;
	}



	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}



	public String getConcepto_pago() {
		return concepto_pago;
	}



	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}



	public String getImporte() {
		return importe;
	}



	public void setImporte(String importe) {
		this.importe = importe;
	}



	public String getComision() {
		return comision;
	}



	public void setComision(String comision) {
		this.comision = comision;
	}



	public String getIva() {
		return iva;
	}



	public void setIva(String iva) {
		this.iva = iva;
	}



	public String getTotal_pago() {
		return total_pago;
	}



	public void setTotal_pago(String total_pago) {
		this.total_pago = total_pago;
	}



	public String getAgregarReferenciaSky() {
		return agregarReferenciaSky;
	}



	public void setAgregarReferenciaSky(String agregarReferenciaSky) {
		this.agregarReferenciaSky = agregarReferenciaSky;
	}



	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}



	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}



	public String getNumero_referencia() {
		return numero_referencia;
	}



	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
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
	
	
	

}
