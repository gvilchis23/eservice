package com.bancoazteca.eservice.command.mediospago.cambiosucursal;

import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.utils.eserviceemail.utils.Validator;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CambioSucursalForm extends FormBean {
	
	private String huella_seguridad;
	private String clave_seguridad;
	private String numero_tarjeta;
	private String titular_cuenta;
	private String numero_cuenta;
	private String tipo_tarjeta;
	private String vigenca;
	private String estatus;
	private String nombre_suc_actual;
	private String direccion_suc_actual;
	private String tipo_busqueda;
	private String codigo_postal;
	private String estado;
	private String municipio;
	private String indice_resultado;
	private String codigo_token;
	private String operacion;
	private String numero_sucursal_nueva;
	private String opcion_seguridad;
	
	
	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}
	public void setOpcion_seguridad(String opcionSeguridad) {
		opcion_seguridad = opcionSeguridad;
	}
	public String getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(String claveSeguridad) {
		clave_seguridad = claveSeguridad;
	}
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huellaSeguridad) {
		huella_seguridad = huellaSeguridad;
	}
	public String getNumero_sucursal_nueva() {
		return numero_sucursal_nueva;
	}
	public void setNumero_sucursal_nueva(String numeroSucursalNueva) {
		numero_sucursal_nueva = numeroSucursalNueva;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numeroTarjeta) {
		numero_tarjeta = numeroTarjeta;
	}
	public String getTitular_cuenta() {
		return titular_cuenta;
	}
	public void setTitular_cuenta(String titularCuenta) {
		titular_cuenta = titularCuenta;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numeroCuenta) {
		numero_cuenta = numeroCuenta;
	}
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipoTarjeta) {
		tipo_tarjeta = tipoTarjeta;
	}
	public String getVigenca() {
		return vigenca;
	}
	public void setVigenca(String vigenca) {
		this.vigenca = vigenca;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getNombre_suc_actual() {
		return nombre_suc_actual;
	}
	public void setNombre_suc_actual(String nombreSucActual) {
		nombre_suc_actual = nombreSucActual;
	}
	public String getDireccion_suc_actual() {
		return direccion_suc_actual;
	}
	public void setDireccion_suc_actual(String direccionSucActual) {
		direccion_suc_actual = direccionSucActual;
	}
	public String getTipo_busqueda() {
		return tipo_busqueda;
	}
	public void setTipo_busqueda(String tipoBusqueda) {
		tipo_busqueda = tipoBusqueda;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigoPostal) {
		codigo_postal = codigoPostal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getIndice_resultado() {
		return indice_resultado;
	}
	public void setIndice_resultado(String indiceResultado) {
		indice_resultado = indiceResultado;
	}
	public String getCodigo_token() {
		return codigo_token;
	}
	public void setCodigo_token(String codigoToken) {
		codigo_token = codigoToken;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@Override
	public MessageErrors validate() {
		
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("solicitud")){
			if(Validator.isEmpty(numero_tarjeta)){
				errors.add("","");
			}
		}
		
		
		
		return errors;
	}
}
