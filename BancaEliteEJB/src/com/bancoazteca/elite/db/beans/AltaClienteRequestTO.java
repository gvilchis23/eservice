package com.bancoazteca.elite.db.beans;

public class AltaClienteRequestTO {

	
	private String idAlnova="*";
	private String nombre="*";
	private String apellidoPaterno="*";
	private String apellidoMaterno="*";
	private String statusActivo="*";
	private String curp="*****";
	private String usuarioModifico="*";
	public String getIdAlnova() {
		return idAlnova;
	}
	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getStatusActivo() {
		return statusActivo;
	}
	public void setStatusActivo(String statusActivo) {
		this.statusActivo = statusActivo;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getUsuarioModifico() {
		return usuarioModifico;
	}
	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}	
}