package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class Pregunta_FrecuenteTO implements Serializable {

	private static final long serialVersionUID = 6323577997556549300L;

	private String pregunta;
	private String respuesta;
	private String id_pregunta;
	
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
	public String getId_pregunta() {
		return id_pregunta;
	}
	public void setId_pregunta(String id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
}
