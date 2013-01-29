package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class AperturaCuentaPlataResponseTO implements Serializable{

	private static final long serialVersionUID = 7631701075892447910L;

	private String referencia;		
	private String minimoApertura;
	private Collection<CuentaLoTO> cuentas;
	private Collection<CotizacionOnzaPlataLibertadTO> cotizaciones;
	private ConfirmacionAperturaCuentaPlataTO aperturaCuentaPlataTO;
	private String precioMoneda;
	private String montoNPesos;
	private String cuentaCargoFormateada;
	private String ctaPlata;
	private String folioPlata; 
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getMinimoApertura() {
		return minimoApertura;
	}
	public void setMinimoApertura(String minimoApertura) {
		this.minimoApertura = minimoApertura;
	}
	public Collection<CuentaLoTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaLoTO> cuentas) {
		this.cuentas = cuentas;
	}
	public Collection<CotizacionOnzaPlataLibertadTO> getCotizaciones() {
		return cotizaciones;
	}
	public void setCotizaciones(Collection<CotizacionOnzaPlataLibertadTO> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}
	public ConfirmacionAperturaCuentaPlataTO getAperturaCuentaPlataTO() {
		return aperturaCuentaPlataTO;
	}
	public void setAperturaCuentaPlataTO(ConfirmacionAperturaCuentaPlataTO aperturaCuentaPlataTO) {
		this.aperturaCuentaPlataTO = aperturaCuentaPlataTO;
	}
	public String getPrecioMoneda() {
		return precioMoneda;
	}
	public void setPrecioMoneda(String precioMoneda) {
		this.precioMoneda = precioMoneda;
	}
	public String getMontoNPesos() {
		return montoNPesos;
	}
	public void setMontoNPesos(String montoNPesos) {
		this.montoNPesos = montoNPesos;
	}
	public String getCuentaCargoFormateada() {
		return cuentaCargoFormateada;
	}
	public void setCuentaCargoFormateada(String cuentaCargoFormateada) {
		this.cuentaCargoFormateada = cuentaCargoFormateada;
	}
	public String getCtaPlata() {
		return ctaPlata;
	}
	public void setCtaPlata(String ctaPlata) {
		this.ctaPlata = ctaPlata;
	}
	public String getFolioPlata() {
		return folioPlata;
	}
	public void setFolioPlata(String folioPlata) {
		this.folioPlata = folioPlata;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	} 
	
}
