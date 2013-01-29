package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

import com.bancoazteca.eservice.beans.Cipher;

public class LoginTO {
	private Cipher numero_cliente; 
	private String alias;
	private String nombre;
	private String apellido_paterno ;
	private String apellido_materno;
	private String nombre_completo;
	private String email;
	private String fecha_nacimiento;	
	private Collection<CuentaTO> cuentas;
	private Collection<CuentaTO> coleccion_inversiones;
	private Collection<CreditoTO> coleccion_creditos;
	private Collection<TarjetaTO> coleccion_tarjetas;
	private Inversion_mercado_dineroTO inversion_mercado_dinero;
	private String nivel_seguridad;
	
	//private TarjetasTO infiniteCards;
	//private TarjetasTO goldenCards;
	//private TarjetasTO tarjetasBase;	
	
	public String getNivel_seguridad() {
		return nivel_seguridad;
	}
	public void setNivel_seguridad(String nivel_seguridad) {
		this.nivel_seguridad = nivel_seguridad;
	}
	public Cipher getNumero_cliente() {
		return numero_cliente;
	}
	public void setNumero_cliente(Cipher numero_cliente) {
		this.numero_cliente = numero_cliente;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<CuentaTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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
	public Collection<CuentaTO> getColeccion_inversiones() {
		return coleccion_inversiones;
	}
	public void setColeccion_inversiones(Collection<CuentaTO> coleccion_inversiones) {
		this.coleccion_inversiones = coleccion_inversiones;
	}
	
}
