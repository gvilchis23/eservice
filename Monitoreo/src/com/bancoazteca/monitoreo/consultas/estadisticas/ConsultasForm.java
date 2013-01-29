package com.bancoazteca.monitoreo.consultas.estadisticas;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bancoazteca.monitoreo.MonitoreoForm;
import com.bancoazteca.monitoreo.utileria.Validator;

public class ConsultasForm extends MonitoreoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String consulta;
	private String diaInicial;
	private String mesInicial;
	private String anioInicial;
	private String diaFinal;
	private String mesFinal;
	private String anioFinal;
	private boolean conFechas;
	private String fechaInicial;
	private String fechaFinal;
	
	
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public String getDiaInicial() {
		return diaInicial;
	}
	public void setDiaInicial(String diaInicial) {
		this.diaInicial = diaInicial;
	}
	public String getMesInicial() {
		return mesInicial;
	}
	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}
	public String getAnioInicial() {
		return anioInicial;
	}
	public void setAnioInicial(String anioInicial) {
		this.anioInicial = anioInicial;
	}
	public String getDiaFinal() {
		return diaFinal;
	}
	public void setDiaFinal(String diaFinal) {
		this.diaFinal = diaFinal;
	}
	public String getMesFinal() {
		return mesFinal;
	}
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}
	public String getAnioFinal() {
		return anioFinal;
	}
	public void setAnioFinal(String anioFinal) {
		this.anioFinal = anioFinal;
	}
	public boolean isConFechas() {
		return conFechas;
	}
	public void setConFechas(boolean conFechas) {
		this.conFechas = conFechas;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		setConFechas( false );
		if( !Validator.isEmptyData( fechaInicial ) && !Validator.isEmptyData( fechaFinal ) ) {
			setConFechas( true );
		}else if( !Validator.isEmptyData( fechaInicial ) || !Validator.isEmptyData( fechaFinal ) ) {
			errors.add( "fechas", new ActionMessage("error.monitoreo.rangofechas") );
		}else if(consulta.equals("6")){
			 if( fechaInicial.equals("") || fechaFinal.equals("") ) {
					errors.add( "fechas", new ActionMessage("error.monitoreo.fechaobligatoria") );
				}
		}else{
			errors.clear();
		}	
		
		return errors; 
	}
	
		
}
