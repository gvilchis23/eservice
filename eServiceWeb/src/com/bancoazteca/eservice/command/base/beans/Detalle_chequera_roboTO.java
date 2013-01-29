package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;

public class Detalle_chequera_roboTO implements Serializable{
	
	private static final long serialVersionUID = 1466707646744496191L;
	
	private String nombre_cliente;
	private String numero_cuenta;
	private String tipo_cuenta;
	private String cheque_inicial;
	private String cheque_final;
	private String descripcion_chequera;
	private String estado_chequera;
	private ChequesEnum.TipoSolicitudEnum tipo_cancelacion;
	private String motivo_cancelacion;
	private String folios_cheques_boquear;
	
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getTipo_cuenta() {
		return tipo_cuenta;
	}
	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}
	public String getCheque_inicial() {
		return cheque_inicial;
	}
	public void setCheque_inicial(String cheque_inicial) {
		this.cheque_inicial = cheque_inicial;
	}
	public String getCheque_final() {
		return cheque_final;
	}
	public void setCheque_final(String cheque_final) {
		this.cheque_final = cheque_final;
	}
	public String getDescripcion_chequera() {
		return descripcion_chequera;
	}
	public void setDescripcion_chequera(String descripcion_chequera) {
		this.descripcion_chequera = descripcion_chequera;
	}
	public String getEstado_chequera() {
		return estado_chequera;
	}
	public void setEstado_chequera(String estado_chequera) {
		this.estado_chequera = estado_chequera;
	}
	public ChequesEnum.TipoSolicitudEnum getTipo_cancelacion() {
		return tipo_cancelacion;
	}
	public void setTipo_cancelacion(ChequesEnum.TipoSolicitudEnum tipo_cancelacion) {
		this.tipo_cancelacion = tipo_cancelacion;
	}
	public String getMotivo_cancelacion() {
		return motivo_cancelacion;
	}
	public void setMotivo_cancelacion(String motivo_cancelacion) {
		this.motivo_cancelacion = motivo_cancelacion;
	}
	public String getFolios_cheques_boquear() {
		return folios_cheques_boquear;
	}
	public void setFolios_cheques_boquear(String folios_cheques_boquear) {
		this.folios_cheques_boquear = folios_cheques_boquear;
	}
	
	
}
