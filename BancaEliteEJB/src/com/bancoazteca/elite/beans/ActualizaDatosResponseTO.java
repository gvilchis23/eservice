package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

import com.bancoazteca.eservice.beans.Cipher;

public class ActualizaDatosResponseTO implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4807067898673618312L;
	
	private String pregunta;
	private String respuesta;
	private String respuestaControl;
	private String confirmacionRespuesta;
	private String email;
	private String celular;
	private String compania;
	private boolean recibirMail;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private Collection<CompaniaViewTO> companiasCelulares;
	private String mensaje;
	
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getRespuestaControl() {
		return respuestaControl;
	}
	public void setRespuestaControl(String respuestaControl) {
		this.respuestaControl = respuestaControl;
	}
	public String getConfirmacionRespuesta() {
		return confirmacionRespuesta;
	}
	public void setConfirmacionRespuesta(String confirmacionRespuesta) {
		this.confirmacionRespuesta = confirmacionRespuesta;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public boolean isRecibirMail() {
		return recibirMail;
	}
	public void setRecibirMail(boolean recibirMail) {
		this.recibirMail = recibirMail;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public Collection<CompaniaViewTO> getCompaniasCelulares() {
		return companiasCelulares;
	}
	public void setCompaniasCelulares(Collection<CompaniaViewTO> companiasCelulares) {
		this.companiasCelulares = companiasCelulares;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	

}
