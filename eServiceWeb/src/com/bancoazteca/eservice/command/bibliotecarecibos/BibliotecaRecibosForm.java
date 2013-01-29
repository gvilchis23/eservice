package com.bancoazteca.eservice.command.bibliotecarecibos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class BibliotecaRecibosForm extends FormBean {
	
	private String id_servicio;
	private String fecha_inicio;
	private String fecha_final;
	
	private String indice_recibo;
	
	
	
	public String getIndice_recibo() {
		return indice_recibo;
	}

	public void setIndice_recibo(String indiceRecibo) {
		indice_recibo = indiceRecibo;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public MessageErrors validate(){
		MessageErrors errors=new MessageErrors();
		
		if(getComando().equalsIgnoreCase("ejecucion")){
			if(Validator.isEmptyData(id_servicio)){
				errors.add("id_servicio","bibliotecarecibos.idServico");
			}else{
				PropertiesService service=PropertiesService.getInstance();
				try {
					String servicio=service.getPropertie("CatalogoBibliotecaRecibos.properties",id_servicio);
					if(servicio==null){
						errors.add("id_servicio","bibliotecarecibos.idservicio.invalido");
					}else{
						id_servicio=servicio;
					}
				} catch (IOException e) {
					errors.add("id_servicio","bibliotecarecibos.fileNotFound");
				}
			}
			if(Validator.isEmptyData(fecha_final)){
				errors.add("fecha_final","bibliotecarecibos.fecha.final.vacia");
			}else{
				try {
					checkDateFormat(fecha_final);
				} catch (ParseException e) {
					errors.add("fecha_final","bibliotecarecibos.fecha.formato");
				}
			}
			
			if(Validator.isEmptyData(fecha_inicio)){
				errors.add("fecha_inicial","bibliotecarecibos.fecha.inicial.vacia");
			}else{
				try {
					checkDateFormat(fecha_inicio);
				} catch (ParseException e) {
					errors.add("fecha_inicial","bibliotecarecibos.fecha.formato");
				}
			}
		}
		
		if(getComando().equalsIgnoreCase("datospdf")){
			if(Validator.isEmptyData(indice_recibo)){
				errors.add("indice_recibo","bibliotecarecibos.indice.recibo.requerido");				
			}
		}
		
		return errors;		
	}
	
	private void checkDateFormat(String fecha) throws ParseException{		
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		df.parse(fecha);								
	}
}
