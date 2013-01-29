package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class PagoTarjetaCreditoResponseTO implements Serializable {

	private static final long serialVersionUID = -25544796688751L;
	private String numeroTarjeta;
	private String tipo;
	private Collection<PagoTarjetaCreditoResponseTO> tarjetasCredito;
	private String nombreUsuario;
	private String cuenta;
	private String comision;
	private String fecha;
	private Collection<PagoTarjetaCreditoResponseTO> cuentas;
	private String importeTotal;
	private String tarjeta;
	private String nombreTitular;
	private String numero;
	private String tarjetaUnica;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	
	public String getTarjetaUnica() {
		return tarjetaUnica;
	}
	public void setTarjetaUnica(String tarjetaUnica) {
		this.tarjetaUnica = tarjetaUnica;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	public Collection<PagoTarjetaCreditoResponseTO> getTarjetasCredito() {
		return tarjetasCredito;
	}
	public void setTarjetasCredito(
			Collection<PagoTarjetaCreditoResponseTO> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Collection<PagoTarjetaCreditoResponseTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<PagoTarjetaCreditoResponseTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}

}
