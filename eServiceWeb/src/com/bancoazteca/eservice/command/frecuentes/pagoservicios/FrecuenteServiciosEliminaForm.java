package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class FrecuenteServiciosEliminaForm extends FormBean{
	
	private String numero_referencia;
	private String tipo_servicio;
	private String estatus;
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();	
		String commando = getComando();
		
		if( commando.equalsIgnoreCase("VALIDACION") ){
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
			
			if(Validator.isEmptyData(estatus)){
				errores.add("estatus", PAGO_SERVICIOS_FRECUENTES_AGREGA_VERIFICADOR_ESTATUS_EMPTY);
			}
			
		}else if(commando.equalsIgnoreCase("EJECUCION")){
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
		}
		
		
		return  errores;
	}

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

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
