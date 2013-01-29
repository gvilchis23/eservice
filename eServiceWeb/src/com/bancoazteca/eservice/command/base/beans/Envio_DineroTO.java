package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Envio_DineroTO implements Serializable{
	
	private static final long serialVersionUID = 6276175310093576098L;
	
	private String fecha_aplicacion;
	private String tipo_cambio;
	private String tipo_cambio_formateado;
	Collection <Cuenta_CargoTO> coleccion_cuentas;
	private String nota_aplicacion_spei;
	private String nota_restriccion_internacional;
	private String curp;
	private String operacion_programada;	
	
	public Collection<Cuenta_CargoTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}

	public void setColeccion_cuentas(Collection<Cuenta_CargoTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}

	public String getFecha_aplicacion() {
		return fecha_aplicacion;
	}

	public void setFecha_aplicacion(String fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}

	public String getNota_aplicacion_spei() {
		return nota_aplicacion_spei;
	}

	public void setNota_aplicacion_spei(String nota_aplicacion_spei) {
		this.nota_aplicacion_spei = nota_aplicacion_spei;
	}

	public String getTipo_cambio() {
		return tipo_cambio;
	}

	public void setTipo_cambio(String tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}

	public String getTipo_cambio_formateado() {
		return tipo_cambio_formateado;
	}

	public void setTipo_cambio_formateado(String tipo_cambio_formateado) {
		this.tipo_cambio_formateado = tipo_cambio_formateado;
	}

	public String getNota_restriccion_internacional() {
		return nota_restriccion_internacional;
	}

	public void setNota_restriccion_internacional(String nota_restriccion_internacional) {
		this.nota_restriccion_internacional = nota_restriccion_internacional;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getOperacion_programada() {
		return operacion_programada;
	}

	public void setOperacion_programada(String operacion_programada) {
		this.operacion_programada = operacion_programada;
	}
		
}
