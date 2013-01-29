package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import java.io.IOException;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class FrecuenteServiciosModificaForm extends FormBean{

	private String numero_referencia;
	private String tipo_servicio;
	private String estatus;
	
	private String opcion_seguridad; 
	private Cipher clave_seguridad;
	private String huella_seguridad;
	
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getTipo_servicio() {
		return tipo_servicio;
	}
	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
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
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();		
		String commando = getComando();
		
		if( commando.equalsIgnoreCase("SOLICITUD") ){
			if(Validator.isEmptyData(numero_referencia)){
				errores.add("numero_referencia", PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_ERROR);
			}else if(!Validator.checkNumeric(numero_referencia)){
				errores.add("numero_referencia", PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_NUMERICAS_ERROR, numero_referencia);
			}
			
			if(Validator.isEmptyData(tipo_servicio)){
				errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_ERROR);
			}else if(!tipo_servicio.equalsIgnoreCase("telmex") && !tipo_servicio.equalsIgnoreCase("movistar") &&
					!tipo_servicio.equalsIgnoreCase("iusacell") && !tipo_servicio.equalsIgnoreCase("sky")){
					errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_INEXISTENTE_ERROR, tipo_servicio);
			}else{
				try{					
					//obtenemos del properties el idServicio.
					PropertiesService propertiesService = PropertiesService.getInstance();
					//Obtenemos el tipo de referencia 
					String tipoReferencia = propertiesService.getPropertie(CommandConstantes.PAGO_SERVICIO_PARAMETRIZABLE, PAGO_SERVICIO_REFERENCIA_LONGITUD + tipo_servicio.toLowerCase());
					if(tipoReferencia!=null){
						String [] arrayLongitudes = tipoReferencia.split(",");
						boolean flag = false;	
						for(int i=0; i<arrayLongitudes.length; i++){
							int data = Integer.parseInt(arrayLongitudes[i]);
							if( numero_referencia.length() == data ){
								flag = true;
							}
						}
						if(!flag){
							errores.add("numero_referencia", PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_LONGITUD_VARIABLE, arrayLongitudes);
						}
					}
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(Validator.isEmptyData(estatus)){
				errores.add("estatus", PAGO_SERVICIOS_FRECUENTES_AGREGA_VERIFICADOR_ESTATUS_EMPTY);
			}
		}else if( commando.equalsIgnoreCase("VALIDACION") ){
			if(Validator.isEmptyData(numero_referencia)){
				errores.add("numero_referencia", PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_ERROR);
			}else if(!Validator.checkNumeric(numero_referencia)){
				errores.add("numero_referencia", PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_NUMERICAS_ERROR, numero_referencia);
			}
			
			if(Validator.isEmptyData(tipo_servicio)){
				errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_ERROR);
			}else if(!tipo_servicio.equalsIgnoreCase("telmex") && !tipo_servicio.equalsIgnoreCase("movistar") &&
					!tipo_servicio.equalsIgnoreCase("iusacell") && !tipo_servicio.equalsIgnoreCase("sky")){
					errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_INEXISTENTE_ERROR, tipo_servicio);
			}
		}else if( commando.equalsIgnoreCase("EJECUCION") ){
			if(Validator.isEmptyData(tipo_servicio)){
				errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_ERROR);
			}else if(!tipo_servicio.equalsIgnoreCase("telmex") && !tipo_servicio.equalsIgnoreCase("movistar") &&
					!tipo_servicio.equalsIgnoreCase("iusacell") && !tipo_servicio.equalsIgnoreCase("sky")){
					errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_INEXISTENTE_ERROR, tipo_servicio);
			}
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
				errores.add("opcion_seguridad",VALIDATOR_ALERTACELULAR_SEGURIDAD_ERROR);
			}
		}
		
		return  errores;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}