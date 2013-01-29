package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class PagoTarjetaAztecaResponseTO implements Serializable {

	private static final long serialVersionUID = -25544796688751L;
	private String numeroTarjeta;
	private String tipo;
	private Collection<TarjetaCreditoAztecaTO> tarjetasCredito;
	private TarjetaCreditoAztecaTO confirmacionTarjetaCredito;
	private MovimientoTarjetaTO movimientoTarjetaTO;
	private ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaOtrosBancosTO;
	private String nombreUsuario;
	private String cuenta;
	private String comision;
	private String fecha;
	private Collection<CuentaVO> cuentas;
	private Map<String, String> mapCuentas;
	private String importeTotal;
	private String tarjeta;
	private String nombreTitular;
	private String numero;
	private String tarjetaUnica;
	private String tarjetaFormateada;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String mensaje;
	
	
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

	public Collection<TarjetaCreditoAztecaTO> getTarjetasCredito() {
		return tarjetasCredito;
	}
	public void setTarjetasCredito(
			Collection<TarjetaCreditoAztecaTO> tarjetasCredito) {
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

	public Collection<CuentaVO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaVO> cuentas) {
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
	public Map<String, String> getMapCuentas() {
		return mapCuentas;
	}
	public void setMapCuentas(Map<String, String> mapCuentas) {
		this.mapCuentas = mapCuentas;
	}
	public ConfirmacionPagoTarjetaOtrosBancosTO getConfirmacionPagoTarjetaOtrosBancosTO() {
		return confirmacionPagoTarjetaOtrosBancosTO;
	}
	public void setConfirmacionPagoTarjetaOtrosBancosTO(
			ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaOtrosBancosTO) {
		this.confirmacionPagoTarjetaOtrosBancosTO = confirmacionPagoTarjetaOtrosBancosTO;
	}
	public String getTarjetaFormateada() {
		return tarjetaFormateada;
	}
	public void setTarjetaFormateada(String tarjetaFormateada) {
		this.tarjetaFormateada = tarjetaFormateada;
	}
	public TarjetaCreditoAztecaTO getConfirmacionTarjetaCredito() {
		return confirmacionTarjetaCredito;
	}
	public void setConfirmacionTarjetaCredito(
			TarjetaCreditoAztecaTO confirmacionTarjetaCredito) {
		this.confirmacionTarjetaCredito = confirmacionTarjetaCredito;
	}
	public MovimientoTarjetaTO getMovimientoTarjetaTO() {
		return movimientoTarjetaTO;
	}
	public void setMovimientoTarjetaTO(MovimientoTarjetaTO movimientoTarjetaTO) {
		this.movimientoTarjetaTO = movimientoTarjetaTO;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
