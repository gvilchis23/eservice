package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Operaciones_FrecuentesTO implements Serializable{
	
	private static final long serialVersionUID = 4216016336886354879L;
	
	private String mensaje_operacion_frecuente;
	
	private Collection <Operacion_Frecuente_Transferencias_TercerosTO> coleccion_transferencias_terceros;
	private Collection <Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_spei;
	private Collection <Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_tef;
	private Collection <Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_azteca;
	private Collection <Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_otros_bancos;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_telmex;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_axtel;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_cfe;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_iusacell;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_movistar;
	private Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_sky;
	private Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_telcel;
	private Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_unefon;
	private Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_iusacell;
	private Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_movistar;
	
	public Collection<Operacion_Frecuente_Transferencias_TercerosTO> getColeccion_transferencias_terceros() {
		return coleccion_transferencias_terceros;
	}
	public void setColeccion_transferencias_terceros(
			Collection<Operacion_Frecuente_Transferencias_TercerosTO> coleccion_transferencias_terceros) {
		this.coleccion_transferencias_terceros = coleccion_transferencias_terceros;
	}
	public Collection<Operacion_Frecuente_TransferenciasTO> getColeccion_transferencias_spei() {
		return coleccion_transferencias_spei;
	}
	public void setColeccion_transferencias_spei(
			Collection<Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_spei) {
		this.coleccion_transferencias_spei = coleccion_transferencias_spei;
	}
	public Collection<Operacion_Frecuente_TransferenciasTO> getColeccion_transferencias_tef() {
		return coleccion_transferencias_tef;
	}
	public void setColeccion_transferencias_tef(
			Collection<Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_tef) {
		this.coleccion_transferencias_tef = coleccion_transferencias_tef;
	}
	public Collection<Operacion_Frecuente_Pago_TarjetaTO> getColeccion_pago_tarjeta_azteca() {
		return coleccion_pago_tarjeta_azteca;
	}
	public void setColeccion_pago_tarjeta_azteca(
			Collection<Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_azteca) {
		this.coleccion_pago_tarjeta_azteca = coleccion_pago_tarjeta_azteca;
	}
	public Collection<Operacion_Frecuente_Pago_TarjetaTO> getColeccion_pago_tarjeta_otros_bancos() {
		return coleccion_pago_tarjeta_otros_bancos;
	}
	public void setColeccion_pago_tarjeta_otros_bancos(
			Collection<Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_otros_bancos) {
		this.coleccion_pago_tarjeta_otros_bancos = coleccion_pago_tarjeta_otros_bancos;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_telmex() {
		return coleccion_pago_servicio_telmex;
	}
	public void setColeccion_pago_servicio_telmex(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_telmex) {
		this.coleccion_pago_servicio_telmex = coleccion_pago_servicio_telmex;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_axtel() {
		return coleccion_pago_servicio_axtel;
	}
	public void setColeccion_pago_servicio_axtel(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_axtel) {
		this.coleccion_pago_servicio_axtel = coleccion_pago_servicio_axtel;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_cfe() {
		return coleccion_pago_servicio_cfe;
	}
	public void setColeccion_pago_servicio_cfe(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_cfe) {
		this.coleccion_pago_servicio_cfe = coleccion_pago_servicio_cfe;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_iusacell() {
		return coleccion_pago_servicio_iusacell;
	}
	public void setColeccion_pago_servicio_iusacell(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_iusacell) {
		this.coleccion_pago_servicio_iusacell = coleccion_pago_servicio_iusacell;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_movistar() {
		return coleccion_pago_servicio_movistar;
	}
	public void setColeccion_pago_servicio_movistar(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_movistar) {
		this.coleccion_pago_servicio_movistar = coleccion_pago_servicio_movistar;
	}
	public Collection<Operacion_Frecuente_Pago_ServiciosTO> getColeccion_pago_servicio_sky() {
		return coleccion_pago_servicio_sky;
	}
	public void setColeccion_pago_servicio_sky(
			Collection<Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_sky) {
		this.coleccion_pago_servicio_sky = coleccion_pago_servicio_sky;
	}
	public Collection<Operacion_Frecuente_Tiempo_AireTO> getColeccion_tiempo_aire_telcel() {
		return coleccion_tiempo_aire_telcel;
	}
	public void setColeccion_tiempo_aire_telcel(
			Collection<Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_telcel) {
		this.coleccion_tiempo_aire_telcel = coleccion_tiempo_aire_telcel;
	}
	public Collection<Operacion_Frecuente_Tiempo_AireTO> getColeccion_tiempo_aire_unefon() {
		return coleccion_tiempo_aire_unefon;
	}
	public void setColeccion_tiempo_aire_unefon(
			Collection<Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_unefon) {
		this.coleccion_tiempo_aire_unefon = coleccion_tiempo_aire_unefon;
	}
	public Collection<Operacion_Frecuente_Tiempo_AireTO> getColeccion_tiempo_aire_iusacell() {
		return coleccion_tiempo_aire_iusacell;
	}
	public void setColeccion_tiempo_aire_iusacell(
			Collection<Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_iusacell) {
		this.coleccion_tiempo_aire_iusacell = coleccion_tiempo_aire_iusacell;
	}
	public Collection<Operacion_Frecuente_Tiempo_AireTO> getColeccion_tiempo_aire_movistar() {
		return coleccion_tiempo_aire_movistar;
	}
	public void setColeccion_tiempo_aire_movistar(
			Collection<Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_movistar) {
		this.coleccion_tiempo_aire_movistar = coleccion_tiempo_aire_movistar;
	}
	public String getMensaje_operacion_frecuente() {
		return mensaje_operacion_frecuente;
	}
	public void setMensaje_operacion_frecuente(String mensaje_operacion_frecuente) {
		this.mensaje_operacion_frecuente = mensaje_operacion_frecuente;
	}
}
