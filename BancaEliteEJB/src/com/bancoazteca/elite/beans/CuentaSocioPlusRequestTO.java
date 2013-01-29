package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class CuentaSocioPlusRequestTO implements Serializable{

	private static final long serialVersionUID = 2141417938609981139L;
	
	private String user;
	private String montoInvertir;
	private String cuentaOrigen;
	private String aceptar;
	private String[] primerNombre;
	private String[] segundoNombre;
	private String[] primerApellido;
	private String[] segundoApellido;
	private String[] parentesco;
	private String[] edad;
	private String[] porcentaje;
	private Collection<ConnectorDataTO> cadena;
	private String tokenCode;
	private String clave;
	private int optionDispositive;


	public int getOptionDispositive() {
		return optionDispositive;
	}

	public void setOptionDispositive(int optionDispositive) {
		this.optionDispositive = optionDispositive;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Collection<ConnectorDataTO> getCadena() {
		return cadena;
	}

	public void setCadena(Collection<ConnectorDataTO> cadena) {
		this.cadena = cadena;
	}

	public String[] getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String[] primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String[] getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String[] segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String[] getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String[] primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String[] getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String[] segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String[] getParentesco() {
		return parentesco;
	}

	public void setParentesco(String[] parentesco) {
		this.parentesco = parentesco;
	}

	public String[] getEdad() {
		return edad;
	}

	public void setEdad(String[] edad) {
		this.edad = edad;
	}

	public String[] getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String[] porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getAceptar() {
		return aceptar;
	}

	public void setAceptar(String aceptar) {
		this.aceptar = aceptar;
	}

	public String getMontoInvertir() {
		return montoInvertir;
	}

	public void setMontoInvertir(String montoInvertir) {
		this.montoInvertir = montoInvertir;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	

}
