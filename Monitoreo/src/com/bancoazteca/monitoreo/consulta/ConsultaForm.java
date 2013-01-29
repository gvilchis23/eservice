package com.bancoazteca.monitoreo.consulta;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bancoazteca.monitoreo.utileria.Validator;
import com.bancoazteca.monitoreo.MonitoreoForm;

public class ConsultaForm extends MonitoreoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String consulta;
	private String diaInicial;
	private String mesInicial;
	private String anioInicial;
	private String diaFinal;
	private String mesFinal;
	private String anioFinal;
	private boolean conFechas;
	
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
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		checkFields();
		
		if(!isConFechas()){						
			DecimalFormat diaFormat = new DecimalFormat("00");
			String dia = diaFormat.format(Integer.valueOf(getDiaInicial()));
			setDiaInicial(dia);
			String fechaInicial = getDiaInicial()+"/"+getMesInicial()+"/"+getAnioInicial();
			
			dia = diaFormat.format(Integer.valueOf(getDiaFinal()));
			setDiaFinal(dia);
			String fechaFinal = getDiaFinal()+"/"+getMesFinal()+"/"+getAnioFinal();
			
			if (!Validator.checkFecha(fechaInicial))
				errors.add("errorFechaInicial", new ActionMessage("error.monitoreo.fechainicial"));
			if (!Validator.checkFecha(fechaFinal))
				errors.add("errorFechaFinal", new ActionMessage("error.monitoreo.fechafinal"));
		}
		return errors; 
	}
	
	public void checkFields(){
		setConFechas(false);
		if ( ("".equals(getDiaInicial()))&&("Mes".equals(getMesInicial()))&&("".equals(getAnioInicial()))&&("".equals(getDiaFinal()))&&("Mes".equals(getMesFinal()))&&("".equals(getAnioFinal())))
			setConFechas(true);
		else{
			if("".equals( getDiaInicial() ))
				setDiaInicial("00");
			if("".equals( getAnioInicial() ))
				setAnioInicial("0000");
			if("".equals( getDiaFinal() ))
				setDiaFinal("00");
			if("".equals( getAnioFinal() ))
				setAnioFinal("0000");
		}
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		diaInicial = "";
		mesInicial = "Mes";
		anioInicial = "";
		diaFinal = "";
		mesFinal = "Mes";
		anioFinal = "";
	}
	
}
