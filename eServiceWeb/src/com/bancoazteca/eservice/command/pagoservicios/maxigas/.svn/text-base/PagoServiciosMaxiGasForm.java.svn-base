package com.bancoazteca.eservice.command.pagoservicios.maxigas;


import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class PagoServiciosMaxiGasForm extends FormBean
{
	
	private static final long serialVersionUID = -6443747532846141116L;

	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private String huella_seguridad;
	
	public void reset(){
		
		this.importe = null;
		this.clave_seguridad=null;
		this.concepto_pago=null;
		this.cuenta_cargo=null;
		this.opcion_seguridad=null;
		this.numero_referencia=null;
	}
	
	@Override	
	public MessageErrors validate(){
		
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
					
					if (Validator.isEmptyData(numero_referencia)){
						errors.add("numero_referencia",REFERENCIA_DE_RECIBO_ERROR);	
					}else if (!Validator.checkNumeric(numero_referencia)){			
						errors.add("numero_referencia", REFERENCIA_NUMERICA_ERROR);			
					}else if (numero_referencia.length()!=19){
						errors.add("numero_referencia",LONGITUD_DE_REFERENCIA_ERROR);				
					}
					
					if (cuenta_cargo.equals("-1")){
						errors.add("cuenta_cargo",SELECCCION_DE_CUENTA_DE_CARGO_ERROR);
					}
					
					if (Validator.isEmptyData(importe)){
						errors.add("importe",IMPORTE_PARA_OPERACION_ERROR);
					}else if (!Validator.checkDecimal(importe)){
						errors.add("importe",IMPORTE_TIPO_NUMERICO_ERROR);
					}else if (Double.parseDouble(importe)<=0) {
						errors.add("importe", IMPORTE_CERO_ERROR);
					}
					
					if(Validator.isEmptyData(concepto_pago))
					{
						errors.add("concepto_pago",CONCEPTO_PAGO_ERROR);
					}
			}else if(getComando().equalsIgnoreCase("EJECUCION")){			
				if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
					if(Validator.isEmptyData(clave_seguridad.toString())){
						errors.add("clave_seguridad",CLAVE_SEGURIDAD_ERROR);
					}
				}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
					if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
						errors.add("clave_seguridad",CLAVE_SEGURIDAD_ERROR);
					}
				}else{
					errors.add("clave_seguridad",OPCION_SEGURIDAD_ERROR);
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
