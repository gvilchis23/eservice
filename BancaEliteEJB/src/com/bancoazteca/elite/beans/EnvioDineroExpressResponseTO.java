package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class EnvioDineroExpressResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Collection<String> cuentas;
	private Collection<CuentaVisibleDineroExpressTO> cuentaVisibleDineroExpress;
	private Collection<LocalidadDEXTO>paisesDEX;
	private Collection<LocalidadDEXTO>estadosDEX;
	private Collection<LocalidadDEXTO>ciudadesDEX;
	private Collection<LocalidadDEXTO>sucursalesDEX;
	private Collection<AgenteDEXTO>agentesDEX;
	
	//TODO no sirven cambiar por collecciones de arriba
	
	private Map<String, String> mapCiudades;
	
	private String user;
	private String cuentaCargo;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String pais;
	private String estado;
	private String ciudad;
	
	private String agenteName;
	private String sucursalName;
	
	private String monto;
	private String comision;
	private String descuento;
	private String subtotal;
	private String impuestos;
	private String totalPagar;
	private String paisNombre;
	private String estadoNombre;
	private String ciudadNombre;
	private String folioTransferencia;
	private String llavePublica;
	private String longitudHuella;
	private String tipoCambio;
	private String divisa;
	private String nomCortoDivisa;
	private String montoRecibir;
	
	private String mensajeCorreo;		
	
	
	
	public String getAgenteName() {
		return agenteName;
	}
	public void setAgenteName(String agenteName) {
		this.agenteName = agenteName;
	}
	public String getSucursalName() {
		return sucursalName;
	}
	public void setSucursalName(String sucursalName) {
		this.sucursalName = sucursalName;
	}
	public String getMensajeCorreo() {
		return mensajeCorreo;
	}
	public void setMensajeCorreo(String mensajeCorreo) {
		this.mensajeCorreo = mensajeCorreo;
	}
	public String getMontoRecibir() {
		return montoRecibir;
	}
	public void setMontoRecibir(String montoRecibir) {
		this.montoRecibir = montoRecibir;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getDivisa() {
		return divisa;
	}
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}
	public String getNomCortoDivisa() {
		return nomCortoDivisa;
	}
	public void setNomCortoDivisa(String nomCortoDivisa) {
		this.nomCortoDivisa = nomCortoDivisa;
	}
	public Collection<String> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<String> cuentas) {
		this.cuentas = cuentas;
	}
	public Collection<CuentaVisibleDineroExpressTO> getCuentaVisibleDineroExpress() {
		return cuentaVisibleDineroExpress;
	}
	public void setCuentaVisibleDineroExpress(
			Collection<CuentaVisibleDineroExpressTO> cuentaVisibleDineroExpress) {
		this.cuentaVisibleDineroExpress = cuentaVisibleDineroExpress;
	}
	public Collection<LocalidadDEXTO> getPaisesDEX() {
		return paisesDEX;
	}
	public void setPaisesDEX(Collection<LocalidadDEXTO> paisesDEX) {
		this.paisesDEX = paisesDEX;
	}
	public Collection<LocalidadDEXTO> getEstadosDEX() {
		return estadosDEX;
	}
	public void setEstadosDEX(Collection<LocalidadDEXTO> estadosDEX) {
		this.estadosDEX = estadosDEX;
	}
	public Collection<LocalidadDEXTO> getCiudadesDEX() {
		return ciudadesDEX;
	}
	public void setCiudadesDEX(Collection<LocalidadDEXTO> ciudadesDEX) {
		this.ciudadesDEX = ciudadesDEX;
	}
	public Collection<LocalidadDEXTO> getSucursalesDEX() {
		return sucursalesDEX;
	}
	public void setSucursalesDEX(Collection<LocalidadDEXTO> sucursalesDEX) {
		this.sucursalesDEX = sucursalesDEX;
	}
	public Collection<AgenteDEXTO> getAgentesDEX() {
		return agentesDEX;
	}
	public void setAgentesDEX(Collection<AgenteDEXTO> agentesDEX) {
		this.agentesDEX = agentesDEX;
	}
	
	public Map<String, String> getMapCiudades() {
		return mapCiudades;
	}
	public void setMapCiudades(Map<String, String> mapCiudades) {
		this.mapCiudades = mapCiudades;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	public String getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(String totalPagar) {
		this.totalPagar = totalPagar;
	}
	public String getPaisNombre() {
		return paisNombre;
	}
	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	public String getCiudadNombre() {
		return ciudadNombre;
	}
	public void setCiudadNombre(String ciudadNombre) {
		this.ciudadNombre = ciudadNombre;
	}
	public String getFolioTransferencia() {
		return folioTransferencia;
	}
	public void setFolioTransferencia(String folioTransferencia) {
		this.folioTransferencia = folioTransferencia;
	}
	public String getLlavePublica() {
		return llavePublica;
	}
	public void setLlavePublica(String llavePublica) {
		this.llavePublica = llavePublica;
	}
	public String getLongitudHuella() {
		return longitudHuella;
	}
	public void setLongitudHuella(String longitudHuella) {
		this.longitudHuella = longitudHuella;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
