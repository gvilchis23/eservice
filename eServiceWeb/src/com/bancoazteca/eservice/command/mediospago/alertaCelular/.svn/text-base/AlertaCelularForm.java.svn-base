package com.bancoazteca.eservice.command.mediospago.alertaCelular;

import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AlertaCelularForm extends FormBean{
	
	private String indice_cuenta;
	private String numero_cuenta;
	
	private String numero_celular;
	private String compania_celular;
	private String chkout_deposito;
	private String chkout_retiro;
	private String deposito_minimo;
	private String retiro_minimo;
	
	private String clave_activacion;
		
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	public MessageErrors validate(){
		String commando = getComando();
		MessageErrors error = new MessageErrors();
		System.out.println("comandoFormalertacelular" + commando);
		if( commando.equalsIgnoreCase("VALIDACION") ){
			
			if(Validator.isEmptyData(numero_celular)){
				error.add("numero_celular", Errors.VALIDATOR_ALERTACELULAR_NUMEROCEL_EMPTY);
			}else{
				if(!Validator.containsOnlyNumbers(numero_celular)){
					error.add("numero_celular", Errors.VALIDATOR_ALERTACELULAR_NUMEROCEL_NO_NUMEROS);
				}else if(numero_celular.length()!=10){
					error.add("numero_celular", Errors.VALIDATOR_ALERTACELULAR_NUMEROCEL_LENGTH_ERROR);
				}
			}
			
			if(Validator.isEmptyData(compania_celular)){
				error.add("compania_celular", Errors.VALIDATOR_ALERTACELULAR_COMPANIA_EMPTY);
			}
//			else{
//				if(!Validator.checkNumeric(compania_celular)){
//					error.add("compania_celular", Errors.VALIDATOR_ALERTACELULAR_COMPANIA_EMPTY);
//				}
//			}
			
			if(Validator.isEmptyData(deposito_minimo)){					
					error.add("deposito_minimo", Errors.VALIDATOR_ALERTACELULAR_MIN_EMPTY);
			}else{
				if(!Validator.checkNumericBalance(deposito_minimo)){
					error.add("deposito_minimo", Errors.VALIDATOR_ALERTACELULAR_MIN_NODIGIT);
				}
			}			
			
			if(Validator.isEmptyData(retiro_minimo)){
					error.add("retiro_minimo", Errors.VALIDATOR_ALERTACELULAR_MIN_EMPTY);
			}else{
				if(!Validator.checkNumericBalance(retiro_minimo)){
					error.add("retiro_minimo", Errors.VALIDATOR_ALERTACELULAR_MIN_NODIGIT);
				}
			}
				
			if(chkout_deposito != null && chkout_retiro != null){
				if((chkout_deposito.equals("") && chkout_retiro.equals(""))||
						(chkout_deposito.equalsIgnoreCase("false") && chkout_retiro.equalsIgnoreCase("false"))||
						(!chkout_deposito.equalsIgnoreCase("true") && !chkout_retiro.equalsIgnoreCase("true"))){
					error.add("chkout_deposito", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
					error.add("chkout_retiro", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
				}else{
					if(!chkout_deposito.equalsIgnoreCase("true")&&!chkout_deposito.equalsIgnoreCase("false")){
						error.add("chkout_deposito", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
					}
					if(!chkout_retiro.equalsIgnoreCase("true")&&!chkout_retiro.equalsIgnoreCase("false")){
						error.add("chkout_retiro", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
					}
				}
			}else{
				if(chkout_deposito == null){
					error.add("chkout_deposito", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
				}else if(chkout_retiro == null){
					error.add("chkout_retiro", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
				}else{
					error.add("chkout_deposito", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
					error.add("chkout_retiro", Errors.VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY);
				}
			}	
			
		}else if(getTipo_operacion().equalsIgnoreCase("ACTIVAR")){
			if(commando.equalsIgnoreCase("SOLICITUD_CLAVE_CONFIRMACION")){
				if(Validator.isEmptyData(clave_activacion)){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}else if(!Validator.containsOnlyNumbers(clave_activacion)){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}else if(clave_activacion.length()!=5){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}
			}else if(commando.equalsIgnoreCase("EJECUCION")){
				if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
					// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
					if(clave_seguridad==null || Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
						error.add("clave_seguridad",CLAVE_SEGURIDAD);
					}
				}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
					if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
						error.add("huella_seguridad",HUELLA_SEGURIDAD);
					}
				}else{
					error.add("opcion_seguridad",VALIDATOR_ALERTACELULAR_SEGURIDAD_ERROR);
				}
			}
		}else if(getTipo_operacion().equalsIgnoreCase("MODIFICAR") || getTipo_operacion().equalsIgnoreCase("ELIMINAR")){					
			if(commando.equalsIgnoreCase("EJECUCION")){
				if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
					// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
					if(clave_seguridad==null || Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
						error.add("clave_seguridad",CLAVE_SEGURIDAD);
					}
				}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
					if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
						error.add("huella_seguridad",HUELLA_SEGURIDAD);
					}
				}else{
					error.add("opcion_seguridad",VALIDATOR_ALERTACELULAR_SEGURIDAD_ERROR);
				}
				if(Validator.isEmptyData(clave_activacion)){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}else if(!Validator.containsOnlyNumbers(clave_activacion)){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}else if(clave_activacion.length()!=5){
					error.add("clave_activacion", Errors.VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY);
				}
			}	
		}
		return error;
	}
	
	public String getIndice_cuenta() {
		return indice_cuenta;
	}
	public void setIndice_cuenta(String indice_cuenta) {
		this.indice_cuenta = indice_cuenta;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getNumero_celular() {
		return numero_celular;
	}
	public void setNumero_celular(String numero_celular) {
		this.numero_celular = numero_celular;
	}
	public String getCompania_celular() {
		return compania_celular;
	}
	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}
	public String getChkout_deposito() {
		return chkout_deposito;
	}
	public void setChkout_deposito(String chkout_deposito) {
		this.chkout_deposito = chkout_deposito;
	}
	public String getChkout_retiro() {
		return chkout_retiro;
	}
	public void setChkout_retiro(String chkout_retiro) {
		this.chkout_retiro = chkout_retiro;
	}
	public String getDeposito_minimo() {
		return deposito_minimo;
	}
	public void setDeposito_minimo(String deposito_minimo) {
		this.deposito_minimo = deposito_minimo;
	}
	public String getRetiro_minimo() {
		return retiro_minimo;
	}
	public void setRetiro_minimo(String retiro_minimo) {
		this.retiro_minimo = retiro_minimo;
	}
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
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
	public String getClave_activacion() {
		return clave_activacion;
	}
	public void setClave_activacion(String clave_activacion) {
		this.clave_activacion = clave_activacion;
	}	
}
