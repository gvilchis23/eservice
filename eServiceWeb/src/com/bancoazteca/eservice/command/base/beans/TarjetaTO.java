package com.bancoazteca.eservice.command.base.beans;

import java.math.BigDecimal;


public class TarjetaTO {	
	private static final long serialVersionUID = 8083389520829938600L;		

	private String fecha_inicial_de_corte;
	private String fecha_proximo_pago;
	private String titular_contrato;
	private String fecha_limite_de_pago;
	private String tarjeta_nueva;
	private String credito_usado;
	private String pago_minimo;
	private String periodos;
	private String tipo;
	private String despues_del_corte;
	private String retenido;	
	private String fecha_de_corte_nueva;
	private String numero_de_periodos;
	private String nombre_titular;
	private String fecha_de_corte;
	private String tarjetas_adicionales;
	private String credito_disponible;
	private String credito_utilizado_en_tarjetas_adicionales;
	private String titularidad;
	private String fecha_activacion;
	private String secuencia;
	private String fecha_proximo_corte;
	private String fecha_final_de_corte;
	private String estatus;
	private String tiene_tarjetas_adicionales;
	private String credito_utilizado_al_corte;
	private String mostrar_nuevo_estado;
	private String contrato;
	private String tarjeta_invalida;
	private String pago_para_no_generar_intereses;
	private String tarjeta_adicional;
	private String corte_septiembre;
	private String numero_tarjeta;
	private String saldo_al_corte;
	private String limite_credito;
	private String fecha_limite_de_pago_nueva;
	
	public String getFecha_inicial_de_corte() {
		return fecha_inicial_de_corte;
	}
	public void setFecha_inicial_de_corte(String fecha_inicial_de_corte) {
		this.fecha_inicial_de_corte = fecha_inicial_de_corte;
	}
	public String getFecha_proximo_pago() {
		return fecha_proximo_pago;
	}
	public void setFecha_proximo_pago(String fecha_proximo_pago) {
		this.fecha_proximo_pago = fecha_proximo_pago;
	}
	public String getTitular_contrato() {
		return titular_contrato;
	}
	public void setTitular_contrato(String titular_contrato) {
		this.titular_contrato = titular_contrato;
	}
	public String getFecha_limite_de_pago() {
		return fecha_limite_de_pago;
	}
	public void setFecha_limite_de_pago(String fecha_limite_de_pago) {
		this.fecha_limite_de_pago = fecha_limite_de_pago;
	}
	public String getPeriodos() {
		return periodos;
	}
	public void setPeriodos(String periodos) {
		this.periodos = periodos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDespues_del_corte() {
		return despues_del_corte;
	}
	public void setDespues_del_corte(String despues_del_corte) {
		this.despues_del_corte = despues_del_corte;
	}
	public String getFecha_de_corte_nueva() {
		return fecha_de_corte_nueva;
	}
	public void setFecha_de_corte_nueva(String fecha_de_corte_nueva) {
		this.fecha_de_corte_nueva = fecha_de_corte_nueva;
	}
	public String getNumero_de_periodos() {
		return numero_de_periodos;
	}
	public void setNumero_de_periodos(String numero_de_periodos) {
		this.numero_de_periodos = numero_de_periodos;
	}
	public String getNombre_titular() {
		return nombre_titular;
	}
	public void setNombre_titular(String nombre_titular) {
		this.nombre_titular = nombre_titular;
	}
	public String getFecha_de_corte() {
		return fecha_de_corte;
	}
	public void setFecha_de_corte(String fecha_de_corte) {
		this.fecha_de_corte = fecha_de_corte;
	}
	public String getTarjetas_adicionales() {
		return tarjetas_adicionales;
	}
	public void setTarjetas_adicionales(String tarjetas_adicionales) {
		this.tarjetas_adicionales = tarjetas_adicionales;
	}
	public String getCredito_utilizado_en_tarjetas_adicionales() {
		return credito_utilizado_en_tarjetas_adicionales;
	}
	public void setCredito_utilizado_en_tarjetas_adicionales(
			String credito_utilizado_en_tarjetas_adicionales) {
		this.credito_utilizado_en_tarjetas_adicionales = credito_utilizado_en_tarjetas_adicionales;
	}
	public String getTitularidad() {
		return titularidad;
	}
	public void setTitularidad(String titularidad) {
		this.titularidad = titularidad;
	}
	public String getFecha_activacion() {
		return fecha_activacion;
	}
	public void setFecha_activacion(String fecha_activacion) {
		this.fecha_activacion = fecha_activacion;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getFecha_proximo_corte() {
		return fecha_proximo_corte;
	}
	public void setFecha_proximo_corte(String fecha_proximo_corte) {
		this.fecha_proximo_corte = fecha_proximo_corte;
	}
	public String getFecha_final_de_corte() {
		return fecha_final_de_corte;
	}
	public void setFecha_final_de_corte(String fecha_final_de_corte) {
		this.fecha_final_de_corte = fecha_final_de_corte;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getTiene_tarjetas_adicionales() {
		return tiene_tarjetas_adicionales;
	}
	public void setTiene_tarjetas_adicionales(String tiene_tarjetas_adicionales) {
		this.tiene_tarjetas_adicionales = tiene_tarjetas_adicionales;
	}
	public String getMostrar_nuevo_estado() {
		return mostrar_nuevo_estado;
	}
	public void setMostrar_nuevo_estado(String mostrar_nuevo_estado) {
		this.mostrar_nuevo_estado = mostrar_nuevo_estado;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getTarjeta_invalida() {
		return tarjeta_invalida;
	}
	public void setTarjeta_invalida(String tarjeta_invalida) {
		this.tarjeta_invalida = tarjeta_invalida;
	}
	public String getTarjeta_adicional() {
		return tarjeta_adicional;
	}
	public void setTarjeta_adicional(String tarjeta_adicional) {
		this.tarjeta_adicional = tarjeta_adicional;
	}
	public String getCorte_septiembre() {
		return corte_septiembre;
	}
	public void setCorte_septiembre(String corte_septiembre) {
		this.corte_septiembre = corte_septiembre;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getFecha_limite_de_pago_nueva() {
		return fecha_limite_de_pago_nueva;
	}
	public void setFecha_limite_de_pago_nueva(String fecha_limite_de_pago_nueva) {
		this.fecha_limite_de_pago_nueva = fecha_limite_de_pago_nueva;
	}
	public String getTarjeta_nueva() {
		return tarjeta_nueva;
	}
	public void setTarjeta_nueva(String tarjeta_nueva) {
		this.tarjeta_nueva = tarjeta_nueva;
	}
	public String getCredito_usado() {
		return credito_usado;
	}
	public void setCredito_usado(String credito_usado) {
		this.credito_usado = credito_usado;
	}
	public String getPago_minimo() {
		return pago_minimo;
	}
	public void setPago_minimo(String pago_minimo) {
		this.pago_minimo = pago_minimo;
	}
	public String getRetenido() {
		return retenido;
	}
	public void setRetenido(String retenido) {
		this.retenido = retenido;
	}
	public String getCredito_disponible() {
		return credito_disponible;
	}
	public void setCredito_disponible(String credito_disponible) {
		this.credito_disponible = credito_disponible;
	}
	public String getCredito_utilizado_al_corte() {
		return credito_utilizado_al_corte;
	}
	public void setCredito_utilizado_al_corte(String credito_utilizado_al_corte) {
		this.credito_utilizado_al_corte = credito_utilizado_al_corte;
	}
	public String getPago_para_no_generar_intereses() {
		return pago_para_no_generar_intereses;
	}
	public void setPago_para_no_generar_intereses(
			String pago_para_no_generar_intereses) {
		this.pago_para_no_generar_intereses = pago_para_no_generar_intereses;
	}
	public String getSaldo_al_corte() {
		return saldo_al_corte;
	}
	public void setSaldo_al_corte(String saldo_al_corte) {
		this.saldo_al_corte = saldo_al_corte;
	}
	public String getLimite_credito() {
		return limite_credito;
	}
	public void setLimite_credito(String limite_credito) {
		this.limite_credito = limite_credito;
	}
	
}
