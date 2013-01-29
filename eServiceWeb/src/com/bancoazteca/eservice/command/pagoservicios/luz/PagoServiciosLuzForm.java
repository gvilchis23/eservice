package com.bancoazteca.eservice.command.pagoservicios.luz;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;





public class PagoServiciosLuzForm extends FormBean{
	// Poner todos los datos de entrada que faltan
	private static final long serialVersionUID = -7344281584110034655L;
	
	
	
	private String fecha_limite;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private String numero_referencia;
	private String opcion_seguridad;
	private Cipher clave_seguridad;
	private String huella_seguridad;
	

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




	public String getFecha_limite() {
		return fecha_limite;
	}




	public void setFecha_limite(String fecha_limite) {
		this.fecha_limite = fecha_limite;
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




	public String getNumero_referencia() {
		return numero_referencia;
	}




	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}




	public MessageErrors validate(){
		
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("VALIDACION")){
			if (Validator.isEmptyData(numero_referencia)){
				errors.add("referencia",PAGO_LUZ_CUENTA_VACIA);	
			}else if (!Validator.checkNumeric(numero_referencia)){			
				errors.add("referencia",PAGO_LUZ_CUENTA_ERROR_NO_NUMERICO);			
			}else if (numero_referencia.length()!=14){
				errors.add("referencia", PAGO_LUZ_CUENTA_ERROR_LONGUITUD);				
			}		
			if (Validator.isEmptyData(fecha_limite)){
				errors.add("fechaPago",PAGO_LUZ_FECHA_REQUERIDA);		
			}		
			if (cuenta_cargo.equals("-1")){
				errors.add("cuentaCargo", PAGO_LUZ_FALTA_CUENTA_A_CARGO);
			}
			if (Validator.isEmptyData(importe.toString())){
				errors.add("importe", PAGO_LUZ_FALTA_IMPORTE);
			}else if (!Validator.checkDecimal(importe.toString())){
				errors.add("importe", PAGO_LUZ_IMPORTE_NUMERICO);
			}else if(Double.parseDouble(importe)<=0){
				errors.add("importe", PAGO_LUZ_IMPORTE_MAYOR_A_CERO );
			}
		}
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				if(Validator.isEmptyData(clave_seguridad.toString())){
					errors.add(CommandBase.TAG_TOKEN, PAGO_LUZ_CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", huella_seguridad.toString())){
					errors.add(CommandBase.TAG_HUELLA, PAGO_LUZ_CLAVE_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad", PAGO_LUZ_OPCION_SEGURIDAD);
			}	
		}
		return errors;	
	}




	public String getHuella_seguridad() {
		return huella_seguridad;
	}




	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}


	

}
