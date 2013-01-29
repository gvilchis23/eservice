package com.bancoazteca.eservice.command.base.beans;

public class Actualizar_Datos_Usuario_ConfirmacionTO {
	
	private Actualizar_Datos_UsuarioTO actualizar_Datos_UsuarioTO;
	
	private String mensaje_Confirmacion;

	public Actualizar_Datos_UsuarioTO getActualizar_Datos_UsuarioTO() {
		return actualizar_Datos_UsuarioTO;
	}

	public void setActualizar_Datos_UsuarioTO(
			Actualizar_Datos_UsuarioTO actualizar_Datos_UsuarioTO) {
		this.actualizar_Datos_UsuarioTO = actualizar_Datos_UsuarioTO;
	}

	public String getMensaje_Confirmacion() {
		return mensaje_Confirmacion;
	}

	public void setMensaje_Confirmacion(String mensaje_Confirmacion) {
		this.mensaje_Confirmacion = mensaje_Confirmacion;
	}
}
