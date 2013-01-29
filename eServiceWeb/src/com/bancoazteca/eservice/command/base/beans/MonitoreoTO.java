package com.bancoazteca.eservice.command.base.beans;

import java.sql.Date;

public class MonitoreoTO {

	private String usuario;
	private Date fecha;
	private String descripcionLarga;
	private String xmlResponse;
	private String xmlRequest;
	private Integer idSistema;
	private Integer idServicio;
	private String status;
	private Integer idSessionApp;
	private Integer idSessionCliente;
	private Integer idTracking;

	/**
	 * Crea una instancia de la clase asignando el valor por defecto a
	 * los atributos.
	 */
	public MonitoreoTO() {
	}

	public MonitoreoTO(String usuario, Date fecha, String descripcionLarga, String xmlResponse, String xmlRequest, Integer idSistema, Integer idServicio, Integer idTracking, String status, Integer idSessionApp, Integer idSessionCliente) {
		init(usuario, fecha, descripcionLarga, xmlResponse, xmlRequest, idSistema, idServicio, idTracking, status, idSessionApp, idSessionCliente);
	}

	public MonitoreoTO(MonitoreoTO inSearchSeguimientoTO) {
		init(inSearchSeguimientoTO.usuario, inSearchSeguimientoTO.fecha, inSearchSeguimientoTO.descripcionLarga, inSearchSeguimientoTO.xmlResponse, inSearchSeguimientoTO.xmlRequest, inSearchSeguimientoTO.idSistema, inSearchSeguimientoTO.idServicio, inSearchSeguimientoTO.idTracking, inSearchSeguimientoTO.status, inSearchSeguimientoTO.idSessionApp, inSearchSeguimientoTO.idSessionCliente);
	}
	
	public void init(String usuario, Date fecha, String descripcionLarga, String xmlResponse, String xmlRequest, Integer idSistema, Integer idServicio, Integer idTracking, String status, Integer idSessionApp, Integer idSessionCliente) {
		setUsuario(usuario);
		setFecha(fecha);
		setDescripcionLarga(descripcionLarga);
		setXmlResponse(xmlResponse);
		setXmlRequest(xmlRequest);
		setIdSistema(idSistema);
		setIdServicio(idServicio);
		setIdTracking(idTracking);
		setStatus(status);
		setIdSessionApp(idSessionApp);
		setIdSessionCliente(idSessionCliente);
	}

	/**
	 * Regresa una copia del objeto.
	 * 
	 * @return Copia del objeto.
	 */
	public MonitoreoTO getData() {
		return new MonitoreoTO(this);
	}

	public String getUsuario() {
		return usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	
	public String getXmlResponse() {
		return xmlResponse;
	}

	public String getXmlRequest() {
		return xmlRequest;
	}

	public Integer getIdSistema() {
		return idSistema;
	}
	
	public Integer getIdServicio() {
		return idServicio;
	}
	
	public Integer getIdTracking() {
		return idTracking;
	}

	public String getStatus() {
		return status;
	}

	public Integer getIdSessionApp() {
		return idSessionApp;
	}

	public Integer getIdSesionCliente() {
		return idSessionCliente;
	}

	public void setUsuario(String anUsuario) {
		if (anUsuario == null) {
			usuario = null;
		} else {
			usuario = anUsuario;
		}
	}

	public void setFecha(Date aFecha) {
		if (aFecha == null) {
			fecha = null;
		} else {
			fecha = aFecha;
		}
	}

	public void setXmlResponse(String anXmlResponse) {
		if (anXmlResponse == null) {
			xmlResponse = null;
		} else {
			xmlResponse = anXmlResponse;
		}
	}

	public void setXmlRequest(String aXmlRequest) {
		if (aXmlRequest == null) {
			xmlRequest = null;
		} else {
			xmlRequest = aXmlRequest;
		}
	}

	public void setIdSistema(Integer aIdSistema) {
		if (aIdSistema == null) {
			idSistema = null;
		} else {
			idSistema = aIdSistema;
		}
	}

	public void setIdServicio(Integer aIdServicio) {
		if (aIdServicio == null) {
			idServicio = null;
		} else {
			idServicio = aIdServicio;
		}
	}

	public void setIdTracking(Integer aIdTracking) {
		if (aIdTracking == null) {
			idTracking = null;
		} else {
			idTracking = aIdTracking;
		}
	}

	public void setStatus(String aStatus) {
		if (aStatus == null) {
			status = null;
		} else {
			status = aStatus;
		}
	}

	public void setIdSessionApp(Integer aIdSessionApp) {
		if (aIdSessionApp == null) {
			idSessionApp = null;
		} else {
			idSessionApp = aIdSessionApp;
		}
	}

	public void setIdSessionCliente(Integer anIdSesionCliente) {
		if (anIdSesionCliente == null) {
			idSessionCliente = null;
		} else {
			idSessionCliente = anIdSesionCliente;
		}
	}

	public void setDescripcionLarga(String andescripcionLarga) {
		if (andescripcionLarga == null) {
			descripcionLarga = null;
		} else {
			descripcionLarga= andescripcionLarga;
		}
	}
	
	public String toString() {
	return "{ Usuario=" + usuario + ";  Fecha=" + fecha + "; XmlResponse=" + xmlResponse + "; XmlRequest=" + xmlRequest + "; IdSistema=" + idSistema + "; IdServicio=" + idServicio + "; IdTracking=" + idTracking + "; Status=" + status + "; IdSessionApp=" + idSessionApp + "; IdSesionCliente=" + idSessionCliente + " }";
	}
}
