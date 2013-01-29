package com.bancoazteca.eservice.command.reportos.consultaPlazoTasa;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ConsultaPlazoTasaForm extends FormBean{
	
	private String fecha_operacion;
	private String hora_operacion;
	private String plazo_inversion;
	private String tasa_inversion;
	
	public String getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public String getHora_operacion() {
		return hora_operacion;
	}
	public void setHora_operacion(String hora_operacion) {
		this.hora_operacion = hora_operacion;
	}
	public String getPlazo_inversion() {
		return plazo_inversion;
	}
	public void setPlazo_inversion(String plazo_inversion) {
		this.plazo_inversion = plazo_inversion;
	}
	public String getTasa_inversion() {
		return tasa_inversion;
	}
	public void setTasa_inversion(String tasa_inversion) {
		this.tasa_inversion = tasa_inversion;
	}
	
	
	public MessageErrors validate (){
		
		MessageErrors error = new MessageErrors();
					
			if(Validator.isEmptyData(plazo_inversion)){
				error.add("plazo_inversion", ERROR_PLAZO_INVERSION_EMPTY);
			}
			if(Validator.isEmptyData(tasa_inversion)){
				error.add("tasa_inversion", ERROR_TASA_INVERSION_EMPTY);
			}
			
			if(Validator.isEmptyData(fecha_operacion)){
				error.add("fecha_operacion", ERROR_FECHA_OPERACION_EMPTY);
			}else if(!Validator.checkFecha(fecha_operacion)){
				error.add("fecha_operacion", ERROR_FECHA_OPERACION_INVALID);
			}
			
			if(Validator.isEmptyData(hora_operacion)){
				error.add("hora_operacion", ERROR_HORA_OPERACION_EMPTY);
			}else if(!Validator.formatHour(hora_operacion)){
				error.add("fecha_operacion", ERROR_HORA_OPERACION_INVALID_FORMAT);
			}else if(!Validator.isValidHour(hora_operacion)){
				error.add("fecha_operacion", ERROR_HORA_OPERACION_INVALID_DATA);
			}

		return error;
	}
	
	
}
