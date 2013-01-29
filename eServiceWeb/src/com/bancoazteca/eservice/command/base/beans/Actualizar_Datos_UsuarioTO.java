package com.bancoazteca.eservice.command.base.beans;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.eservice.beans.Cipher;

public class Actualizar_Datos_UsuarioTO{
	
	private String pregunta_secreta;
	private Cipher respuesta_pregunta_secreta;
	private Cipher confirmar_respuesta_pregunta_secreta;
	private String email;
	private String celular;
	private String compania_celular;
	private boolean recibir_correos_informativos;
	
	private Collection<Compania_celularTO> coleccion_companias_celulares;

	public String getPregunta_secreta() {
		return pregunta_secreta;
	}

	public void setPregunta_secreta(String pregunta_secreta) {
		this.pregunta_secreta = pregunta_secreta;
	}

	public Cipher getRespuesta_pregunta_secreta() {
		return respuesta_pregunta_secreta;
	}

	public void setRespuesta_pregunta_secreta(Cipher respuesta_pregunta_secreta) {
		this.respuesta_pregunta_secreta = respuesta_pregunta_secreta;
	}

	public Cipher getConfirmar_respuesta_pregunta_secreta() {
		return confirmar_respuesta_pregunta_secreta;
	}

	public void setConfirmar_respuesta_pregunta_secreta(
			Cipher confirmar_respuesta_pregunta_secreta) {
		this.confirmar_respuesta_pregunta_secreta = confirmar_respuesta_pregunta_secreta;
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

	public String getCompania_celular() {
		return compania_celular;
	}

	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}

	public boolean isRecibir_correos_informativos() {
		return recibir_correos_informativos;
	}

	public void setRecibir_correos_informativos(boolean recibir_correos_informativos) {
		this.recibir_correos_informativos = recibir_correos_informativos;
	}

	public Collection<Compania_celularTO> getColeccion_companias_celulares() {
		return coleccion_companias_celulares;
	}

	public void setColeccion_companias_celulares(
			Collection<Compania_celularTO> coleccion_companias_celulares) {
		this.coleccion_companias_celulares = coleccion_companias_celulares;
	}
		

}
