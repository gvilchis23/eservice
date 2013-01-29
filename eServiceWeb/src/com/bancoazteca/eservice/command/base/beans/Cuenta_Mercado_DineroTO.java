package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;






public class Cuenta_Mercado_DineroTO {
	private String bandera_operacion;
	private String monto_minimo;
	private Collection<Cuenta_CargoTO> coleccion_cuentas;
	private Collection<PlazoTasaTO> coleccion_plazos_tasas;
	
	public String getMonto_minimo() {
		return monto_minimo;
	}
	public void setMonto_minimo(String monto_minimo) {
		this.monto_minimo = monto_minimo;
	}
	public Collection<Cuenta_CargoTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}
	public void setColeccion_cuentas(Collection<Cuenta_CargoTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}
	public Collection<PlazoTasaTO> getColeccion_plazos_tasas() {
		return coleccion_plazos_tasas;
	}
	public void setColeccion_plazos_tasas(
			Collection<PlazoTasaTO> coleccion_plazos_tasas) {
		this.coleccion_plazos_tasas = coleccion_plazos_tasas;
	}
	public String getBandera_operacion() {
		return bandera_operacion;
	}
	public void setBandera_operacion(String bandera_operacion) {
		this.bandera_operacion = bandera_operacion;
	}
	
	
}