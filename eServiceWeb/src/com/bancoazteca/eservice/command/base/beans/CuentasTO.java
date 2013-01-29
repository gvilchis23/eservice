package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class CuentasTO implements Serializable {
	
	private static final long serialVersionUID = 408568945076937783L;
	
	private Collection <CuentaTO> coleccion_cuentas;
	private Collection <CuentaTO> coleccion_inversiones;
	private Collection<CreditoTO> coleccion_creditos;
	private Collection<TarjetaTO> coleccion_tarjetas;
	private Inversion_mercado_dineroTO inversion_mercado_dinero;
	private String alias;
	private String nombre_completo;
	private String email;
	
	public Collection<CuentaTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}

	public void setColeccion_cuentas(Collection<CuentaTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}

	public Collection<CreditoTO> getColeccion_creditos() {
		return coleccion_creditos;
	}

	public void setColeccion_creditos(Collection<CreditoTO> coleccion_creditos) {
		this.coleccion_creditos = coleccion_creditos;
	}

	public Collection<TarjetaTO> getColeccion_tarjetas() {
		return coleccion_tarjetas;
	}

	public void setColeccion_tarjetas(Collection<TarjetaTO> coleccion_tarjetas) {
		this.coleccion_tarjetas = coleccion_tarjetas;
	}

	public Inversion_mercado_dineroTO getInversion_mercado_dinero() {
		return inversion_mercado_dinero;
	}

	public void setInversion_mercado_dinero(
			Inversion_mercado_dineroTO inversion_mercado_dinero) {
		this.inversion_mercado_dinero = inversion_mercado_dinero;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<CuentaTO> getColeccion_inversiones() {
		return coleccion_inversiones;
	}

	public void setColeccion_inversiones(Collection<CuentaTO> coleccion_inversiones) {
		this.coleccion_inversiones = coleccion_inversiones;
	}
	
}