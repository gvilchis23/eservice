package com.bancoazteca.eservice.command.cuentas.mercadodinero;

import java.io.IOException;

import com.bancoazteca.eservice.beans.Cipher;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CuentaMercadoDineroForm extends FormBean {
	private String contrato;
	private String cuenta_origen;
	private String monto="";
	private String tipo_inversion;
	private String plazo;
	private String tasa;
	private String portal;
	private String folio;
	private String numero_contrato;
	private String huella_seguridad;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	
	private double montoMinimo;
	
	public MessageErrors validate(){
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("validacion")){
			
			if(plazo.equals("0") || tasa.equals("0") ){
				errors.add("plazo_taza", VALIDATOR_TAS_PLAZO_TASA);
			}
			
			if(cuenta_origen.equalsIgnoreCase("-1")){
				errors.add("cuenta_origen", VALIDATOR_TAS_CUENTA_CARGO);
			}
			
			try {
				if(!validaMonto()){
					errors.add("monto",VALIDATOR_TAS_MONTO_MINIMO, "" + montoMinimo );
				}
			}catch (NumberFormatException e) {
				errors.add("monto",VALIDATOR_TAS_MONTO_INVALID_FORMAT);
			}
		}//else if(getComando().equalsIgnoreCase("ejecucion")){
//			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
//				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
//				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
//					errors.add("clave_seguridad",VALIDATOR_CUENTA_MERCADO_CLAVE_SEGURIDAD);
//				}
//			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
//				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
//					errors.add("huella_seguridad",HUELLA_SEGURIDAD);
//				}
//			}else{
//				errors.add("opcion_seguridad",VALIDATOR_CUENTA_MERCADO_OPCION_SEGURIDAD);
//			}
//		}
		
		return errors;
	}
	
	private boolean validaMonto() throws NumberFormatException{
		double montoDouble = 0;
		montoMinimo = 1;
		try{
			montoDouble=Double.parseDouble(monto);
			
			PropertiesService propertiesService=PropertiesService.getInstance();
			montoMinimo=Integer.parseInt(propertiesService.getPropertie("CuentasInversion.properties", "montominimo.cuenta.mercadodinero"));
		} catch (IOException e) {
			return false;
		}
		return montoDouble>=montoMinimo;
	}
	
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
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

	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}

	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}

	public String getCuenta_origen() {
		return cuenta_origen;
	}

	public void setCuenta_origen(String cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}

	public String getTipo_inversion() {
		return tipo_inversion;
	}

	public void setTipo_inversion(String tipo_inversion) {
		this.tipo_inversion = tipo_inversion;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getNumero_contrato() {
		return numero_contrato;
	}

	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}

}
