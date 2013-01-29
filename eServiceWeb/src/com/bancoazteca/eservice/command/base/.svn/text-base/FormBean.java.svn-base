package com.bancoazteca.eservice.command.base;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.eservice.validator.MessageErrors;

/**
 * @author Jorge Bringas
 *
 */
public abstract class FormBean implements Errors{
	
	private Cipher idaplicacion;
	private Cipher idsesion;
	private String tipo_operacion;
	private String idservicio;
	private String comando;
	private String idportal;
	
	public MessageErrors validate(){
		return null;
	}	
	public Cipher getIdsesion() {
		return idsesion;
	}
	public void setIdsesion(Cipher idsesion) {
		this.idsesion = idsesion;
	}
	public String getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(String idservicio) {
		this.idservicio = idservicio;
	}
	public String getComando() {
		return comando;
	}
	
	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getTipo_operacion() {
		return tipo_operacion;
	}

	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	public String getIdportal() {
		return idportal;
	}
	public void setIdportal(String idportal) {
		this.idportal = idportal;
	}
	public Cipher getIdaplicacion() {
		return idaplicacion;
	}
	public void setIdaplicacion(Cipher idaplicacion) {
		this.idaplicacion = idaplicacion;
	}
	
}