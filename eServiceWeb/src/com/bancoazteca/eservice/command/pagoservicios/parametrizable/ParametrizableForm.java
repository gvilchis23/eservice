package com.bancoazteca.eservice.command.pagoservicios.parametrizable;

import java.io.IOException;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ParametrizableForm extends FormBean {
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	private String digito_verificador;
	
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
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
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
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
	public String getDigito_verificador() {
		return digito_verificador;
	}
	public void setDigito_verificador(String digito_verificador) {
		this.digito_verificador = digito_verificador;
	}
	
	public MessageErrors validate(){
		MessageErrors error = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if(Validator.isEmptyData(cuenta_cargo)){
				error.add("cuenta_cargo", PAGO_SERVICIOS_PARAMETRIZABLE_CUENTAS_ERROR, "cargo");
			}else{
				if(!Validator.checkNumeric(cuenta_cargo)){
					error.add("cuenta_cargo", PAGO_SERVICIOS_PARAMETRIZABLE_CUENTAS_DECIMAL_ERROR, "cargo");
				}
			}

			if(getTipo_operacion().equalsIgnoreCase("TELMEX")){
				if(Validator.isEmptyData(getDigito_verificador())){
					error.add("digito_verificador", PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_EMPTY);
				}else if(!Validator.checkNumeric(getDigito_verificador())){
					error.add("digito_verificador", PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_ERROR);
				}else if(getDigito_verificador().length()!=1){
					error.add("digito_verificador", PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_TAMANIO);
				}
			}
			
			if(Validator.isEmptyData(numero_referencia)){
				error.add("numero_referencia", PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_ERROR, "referencia");
			}else{	
				if(!Validator.checkNumeric(numero_referencia)){
					error.add("numero_referencia", PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_DECIMAL_ERROR, "referencia");
				}	
				else {
					String tipo_operacion = getTipo_operacion();
					PropertiesService propertiesService = PropertiesService.getInstance();
					try{
						String valorData = propertiesService.getPropertie(CommandConstantes.PAGO_SERVICIO_PARAMETRIZABLE, PAGO_SERVICIO_REFERENCIA_LONGITUD + tipo_operacion.toLowerCase());
						String [] arrayLongitudes = valorData.split(",");
						boolean flag = false;	
						for(int i=0; i<arrayLongitudes.length; i++){
							int data = Integer.parseInt(arrayLongitudes[i]);
							if( numero_referencia.length() == data ){
								flag = true;
							}
						}
						if(!flag){
							error.add("numero_referencia", PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_LONGITUD_VARIABLE, arrayLongitudes);
						}
					
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}						
				}
			}
			if(Validator.isEmptyData(concepto_pago)){
				error.add("concepto", PAGO_SERVICIOS_PARAMETRIZABLE_CONCEPTO_PAGO_ERROR);
			}
			if(Validator.isEmptyData(importe)){
				error.add("importe", PAGO_SERVICIOS_PARAMETRIZABLE_IMPORTE_ERROR);
			}else{
				if(!Validator.checkDecimal(importe)){
					error.add("importe", PAGO_SERVICIOS_PARAMETRIZABLE_IMPORTE_DECIMAL_ERROR);
				}
			}
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					error.add(CommandBase.TAG_TOKEN, PAGO_SERVICIOS_PARAMETRIZABLE_OPCION_SEGURIDAD_ERROR, "clave");
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					error.add(CommandBase.TAG_HUELLA, PAGO_SERVICIOS_PARAMETRIZABLE_OPCION_SEGURIDAD_ERROR, "huella");
				}
			}else{
				error.add("opcion_seguridad",OPCION_SEGURIDAD);
			}	
		}
		return error;
	}
}
