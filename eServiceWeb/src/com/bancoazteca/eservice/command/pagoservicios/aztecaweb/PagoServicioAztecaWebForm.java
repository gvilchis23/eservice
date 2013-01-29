package com.bancoazteca.eservice.command.pagoservicios.aztecaweb;


import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoServicioAztecaWebForm extends FormBean
{
	
	private static final long serialVersionUID = -6443747532846141116L;

	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private String huella_seguridad;
	
	@Override	
	public MessageErrors validate(){
		
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
					
					if (Validator.isEmptyData(numero_referencia)){
						errors.add("numero_referencia",PAGO_SERVICIOS_AZTECAWEB_REFERENCIA_DE_RECIBO_VALIDATOR);	
					}else if (!Validator.checkNumeric(numero_referencia)){			
						errors.add("numero_referencia", PAGO_SERVICIOS_AZTECAWEB_REFERENCIA_NUMERICA_VALIDATOR);			
					}else if (numero_referencia.length()!=12){
						errors.add("numero_referencia",PAGO_SERVICIOS_AZTECAWEB_LONGITUD_DE_REFERENCIA_VALIDATOR);				
					}
					
					if (cuenta_cargo.equals("-1")){
						errors.add("cuenta_cargo",PAGO_SERVICIOS_AZTECAWEB_SELECCCION_DE_CUENTA_DE_CARGO_VALIDATOR);
					}
					
					if (Validator.isEmptyData(importe)){
						errors.add("importe",PAGO_SERVICIOS_AZTECAWEB_IMPORTE_PARA_OPERACION_VALIDATOR);
					}else if (!Validator.checkDecimal(importe)){
						errors.add("importe",PAGO_SERVICIOS_AZTECAWEB_IMPORTE_TIPO_NUMERICO_VALIDATOR);
					}else if (Double.parseDouble(importe)<=0) {
						errors.add("importe", PAGO_SERVICIOS_AZTECAWEB_IMPORTE_CERO_VALIDATOR);
					}
					
					if(Validator.isEmptyData(concepto_pago))
					{
						errors.add("concepto_pago",PAGO_SERVICIOS_AZTECAWEB_CONCEPTO_PAGO_VALIDATOR);
					}
			}else if(getComando().equalsIgnoreCase("EJECUCION")){
				if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
					// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
					if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
						errors.add(CommandBase.TAG_TOKEN,PAGO_SERVICIOS_AZTECAWEB_CLAVE_SEGURIDAD_VALIDATOR);
					}
				}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
					if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
						errors.add(CommandBase.TAG_HUELLA,HUELLA_SEGURIDAD);
					}
				}else{
					errors.add("opcion_seguridad",PAGO_SERVICIOS_AZTECAWEB_OPCION_SEGURIDAD_VALIDATOR);
				}
			}
			

		return errors;
	}
	
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
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

	public String getConcepto_pago() {
		return concepto_pago;
	}

	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
}
