package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class FrecuenteServiciosConsultaForm extends FormBean{
	private String tipo_servicio;

	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();		

		if(Validator.isEmptyData(tipo_servicio)){
			errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_CONSULTA_SERVICIO_ERROR);
		}
//		else if(!tipo_servicio.equalsIgnoreCase("telmex") && !tipo_servicio.equalsIgnoreCase("movistar") &&
//				!tipo_servicio.equalsIgnoreCase("iusacell") && !tipo_servicio.equalsIgnoreCase("sky")){
//				errores.add("tipo_servicio", PAGO_SERVICIOS_FRECUENTES_CONSULTA_SERVICIO_INEXISTENTE_ERROR, tipo_servicio);
//		}
		return  errores;
	}
	
	public String getTipo_servicio() {
		return tipo_servicio;
	}

	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}	
}
