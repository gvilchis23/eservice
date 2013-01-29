package com.bancoazteca.elite.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PedidoTO {
	private String dis;
	private String titular;
	private String titularCuentaDestino;
	private String cuentaCargo;
	private String cuentaDestino;
	private String[] dispositivo;
	private String[] importe;
	private String[] IVA;
	private String total;
	private String[] cantidad;
	private String formaEnvio;
	private String fechaEnvio;
	private String direccion;
	private String estado;
	private String ciudad;
	private String colonia;
	private String cp;
	private String telefono;
	private ArrayList personasAutorizadas;
	private String tiempoEntrega;
	private String numPedido;
	private String folioOperacion;
	private String concepto;
	private String FolioBD;
	private String NumeroCliente;
	private String NombreCliente;
	private String ApPatCliente;
	private String ApMatCliente;
	private String RFCCliente;
	private String TelefonoAdicional;
	private String FechaSolicitud;					
	private String StatusSolicitud;
	private String IP;
	private String FechaSolicitudPrevFraudes;					
	private String IdUsuario;					
	private String Email;					
	private String Alias;					
	private String TipoCuenta;					
	private String SKULector;					
	private String SKUToken;					
	private String CostoEnvio;					
	private String IdMensajeria;					
	private String DescripcionMensajeria;					
	private String Intentos;					
	private String Observaciones;					
	private String PrimerIntento;					
	private String UltimoIntento;	
	private String fondosSuficientes;	
	private String folioRetencion;			
	private String reqAlnova;
	private String importeCargoAlnova;
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getTitularCuentaDestino() {
		return titularCuentaDestino;
	}
	public void setTitularCuentaDestino(String titularCuentaDestino) {
		this.titularCuentaDestino = titularCuentaDestino;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String[] getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(String[] dispositivo) {
		this.dispositivo = dispositivo;
	}
	public String[] getImporte() {
		return importe;
	}
	public void setImporte(String[] importe) {
		this.importe = importe;
	}
	public String[] getIVA() {
		return IVA;
	}
	public void setIVA(String[] iva) {
		IVA = iva;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String[] getCantidad() {
		return cantidad;
	}
	public void setCantidad(String[] cantidad) {
		this.cantidad = cantidad;
	}
	public String getFormaEnvio() {
		return formaEnvio;
	}
	public void setFormaEnvio(String formaEnvio) {
		this.formaEnvio = formaEnvio;
	}
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public ArrayList getPersonasAutorizadas() {
		return personasAutorizadas;
	}
	public void setPersonasAutorizadas(ArrayList personasAutorizadas) {
		this.personasAutorizadas = personasAutorizadas;
	}
	public String getTiempoEntrega() {
		return tiempoEntrega;
	}
	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}
	public String getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}
	public String getFolioOperacion() {
		return folioOperacion;
	}
	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion = folioOperacion;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getFolioBD() {
		return FolioBD;
	}
	public void setFolioBD(String folioBD) {
		FolioBD = folioBD;
	}
	public String getNumeroCliente() {
		return NumeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		NumeroCliente = numeroCliente;
	}
	public String getNombreCliente() {
		return NombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		NombreCliente = nombreCliente;
	}
	public String getApPatCliente() {
		return ApPatCliente;
	}
	public void setApPatCliente(String apPatCliente) {
		ApPatCliente = apPatCliente;
	}
	public String getApMatCliente() {
		return ApMatCliente;
	}
	public void setApMatCliente(String apMatCliente) {
		ApMatCliente = apMatCliente;
	}
	public String getRFCCliente() {
		return RFCCliente;
	}
	public void setRFCCliente(String cliente) {
		RFCCliente = cliente;
	}
	public String getTelefonoAdicional() {
		return TelefonoAdicional;
	}
	public void setTelefonoAdicional(String telefonoAdicional) {
		TelefonoAdicional = telefonoAdicional;
	}
	public String getFechaSolicitud() {
		return FechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		FechaSolicitud = fechaSolicitud;
	}
	public String getStatusSolicitud() {
		return StatusSolicitud;
	}
	public void setStatusSolicitud(String statusSolicitud) {
		StatusSolicitud = statusSolicitud;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public String getFechaSolicitudPrevFraudes() {
		return FechaSolicitudPrevFraudes;
	}
	public void setFechaSolicitudPrevFraudes(String fechaSolicitudPrevFraudes) {
		FechaSolicitudPrevFraudes = fechaSolicitudPrevFraudes;
	}
	public String getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAlias() {
		return Alias;
	}
	public void setAlias(String alias) {
		Alias = alias;
	}
	public String getTipoCuenta() {
		return TipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}
	public String getSKULector() {
		return SKULector;
	}
	public void setSKULector(String lector) {
		SKULector = lector;
	}
	public String getSKUToken() {
		return SKUToken;
	}
	public void setSKUToken(String token) {
		SKUToken = token;
	}
	public String getCostoEnvio() {
		return CostoEnvio;
	}
	public void setCostoEnvio(String costoEnvio) {
		CostoEnvio = costoEnvio;
	}
	public String getIdMensajeria() {
		return IdMensajeria;
	}
	public void setIdMensajeria(String idMensajeria) {
		IdMensajeria = idMensajeria;
	}
	public String getDescripcionMensajeria() {
		return DescripcionMensajeria;
	}
	public void setDescripcionMensajeria(String descripcionMensajeria) {
		DescripcionMensajeria = descripcionMensajeria;
	}
	public String getIntentos() {
		return Intentos;
	}
	public void setIntentos(String intentos) {
		Intentos = intentos;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	public String getPrimerIntento() {
		return PrimerIntento;
	}
	public void setPrimerIntento(String primerIntento) {
		PrimerIntento = primerIntento;
	}
	public String getUltimoIntento() {
		return UltimoIntento;
	}
	public void setUltimoIntento(String ultimoIntento) {
		UltimoIntento = ultimoIntento;
	}
	public String getFondosSuficientes() {
		return fondosSuficientes;
	}
	public void setFondosSuficientes(String fondosSuficientes) {
		this.fondosSuficientes = fondosSuficientes;
	}
	public String getFolioRetencion() {
		return folioRetencion;
	}
	public void setFolioRetencion(String folioRetencion) {
		this.folioRetencion = folioRetencion;
	}
	public String getReqAlnova() {
		return reqAlnova;
	}
	public void setReqAlnova(String reqAlnova) {
		this.reqAlnova = reqAlnova;
	}
	public String getImporteCargoAlnova() {
		return importeCargoAlnova;
	}
	public void setImporteCargoAlnova(String importeCargoAlnova) {
		this.importeCargoAlnova = importeCargoAlnova;
	}
	
}
