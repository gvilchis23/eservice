package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Pago_ServicioTO {
	private Collection <Cuenta_CargoTO> coleccion_cuentas;
	private Collection <ServiciosTO> coleccion_servicios;
	private String longitud_referencia;
	private String servicio_referencia_frecuente;
	
	public Collection<Cuenta_CargoTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}
	public void setColeccion_cuentas(Collection<Cuenta_CargoTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}
	public Collection<ServiciosTO> getColeccion_servicios() {
		return coleccion_servicios;
	}
	public void setColeccion_servicios(Collection<ServiciosTO> coleccion_servicios) {
		this.coleccion_servicios = coleccion_servicios;
	}
	public String getLongitud_referencia() {
		return longitud_referencia;
	}
	public void setLongitud_referencia(String longitud_referencia) {
		this.longitud_referencia = longitud_referencia;
	}
	public String getServicio_referencia_frecuente() {
		return servicio_referencia_frecuente;
	}
	public void setServicio_referencia_frecuente(
			String servicio_referencia_frecuente) {
		this.servicio_referencia_frecuente = servicio_referencia_frecuente;
	}
	
	
}
