package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class EnvioMailRequestTO implements Serializable {

	private static final long serialVersionUID = -4393821995755262061L;
	
	private String user;
	private String correo_electronico_destino;
	private String mensaje;
	private String tipo_envio;
	private SpeiProgramadoRequestTO speiRequest30TO;
	
	public SpeiProgramadoRequestTO getSpeiRequest30TO() {
		return speiRequest30TO;
	}
	public void setSpeiRequest30TO(SpeiProgramadoRequestTO speiRequest30TO) {
		this.speiRequest30TO = speiRequest30TO;
	}
	public String getTipo_envio() {
		return tipo_envio;
	}
	public void setTipo_envio(String tipo_envio) {
		this.tipo_envio = tipo_envio;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCorreo_electronico_destino() {
		return correo_electronico_destino;
	}
	public void setCorreo_electronico_destino(String correo_electronico_destino) {
		this.correo_electronico_destino = correo_electronico_destino;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
