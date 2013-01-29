package com.bancoazteca.elite.ejb.inversiones.beans;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class ComprobanteInversionReportosResponseTO extends InversionesResponseTO{
	
	private Collection<AsignacionesComprobanteTO> coleccionAsignaciones;
	
	private String fechaOperacion;
	private String nombre_cliente;
	private String rfc;
	private String direccion;
	private String colonia;
	private String ciudad;
	private String codigo_postal;
	private String numero_contrato;
	private String promotor;
	private String folio_orden;
	private String fecha_concentracion;
	private String fecha_liquidacion;
	private String importe_inversion;
	private String valor_letra;
	private String plazo;
	private String tasa;
	private String interes_premio;
	private String monto_al_vencimiento;
	private String isr;
	private String tipo_operacion;
	private String moneda;
	private String tipo_valor;
	private String emisora;
	private String serie;
	private String tasa_operativa;
	private String titulos;
	private String precio;
	private String tipo_cambio;
	private String monto;
	private String total_titulos;
	private String total_importe;
	private String fecha_vencimiento;
	private String empresa_reportado;
	private String concepto_inversion;
	
	
	private ByteArrayOutputStream outputStreamPDF;
	
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getNumero_contrato() {
		return numero_contrato;
	}
	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}
	public String getPromotor() {
		return promotor;
	}
	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}
	public String getFolio_orden() {
		return folio_orden;
	}
	public void setFolio_orden(String folio_orden) {
		this.folio_orden = folio_orden;
	}
	public String getFecha_concentracion() {
		return fecha_concentracion;
	}
	public void setFecha_concentracion(String fecha_concentracion) {
		this.fecha_concentracion = fecha_concentracion;
	}
	public String getFecha_liquidacion() {
		return fecha_liquidacion;
	}
	public void setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}
	public String getImporte_inversion() {
		return importe_inversion;
	}
	public void setImporte_inversion(String importe_inversion) {
		this.importe_inversion = importe_inversion;
	}
	public String getValor_letra() {
		return valor_letra;
	}
	public void setValor_letra(String valor_letra) {
		this.valor_letra = valor_letra;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getInteres_premio() {
		return interes_premio;
	}
	public void setInteres_premio(String interes_premio) {
		this.interes_premio = interes_premio;
	}
	public String getMonto_al_vencimiento() {
		return monto_al_vencimiento;
	}
	public void setMonto_al_vencimiento(String monto_al_vencimiento) {
		this.monto_al_vencimiento = monto_al_vencimiento;
	}
	public String getIsr() {
		return isr;
	}
	public void setIsr(String isr) {
		this.isr = isr;
	}
	public String getTipo_operacion() {
		return tipo_operacion;
	}
	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getTipo_valor() {
		return tipo_valor;
	}
	public void setTipo_valor(String tipo_valor) {
		this.tipo_valor = tipo_valor;
	}
	public String getEmisora() {
		return emisora;
	}
	public void setEmisora(String emisora) {
		this.emisora = emisora;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTasa_operativa() {
		return tasa_operativa;
	}
	public void setTasa_operativa(String tasa_operativa) {
		this.tasa_operativa = tasa_operativa;
	}
	public String getTitulos() {
		return titulos;
	}
	public void setTitulos(String titulos) {
		this.titulos = titulos;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(String tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getTotal_titulos() {
		return total_titulos;
	}
	public void setTotal_titulos(String total_titulos) {
		this.total_titulos = total_titulos;
	}
	public String getTotal_importe() {
		return total_importe;
	}
	public void setTotal_importe(String total_importe) {
		this.total_importe = total_importe;
	}
	public ByteArrayOutputStream getOutputStreamPDF() {
		return outputStreamPDF;
	}
	public void setOutputStreamPDF(ByteArrayOutputStream outputStreamPDF) {
		this.outputStreamPDF = outputStreamPDF;
	}
	public Collection<AsignacionesComprobanteTO> getColeccionAsignaciones() {
		return coleccionAsignaciones;
	}
	public void setColeccionAsignaciones(
			Collection<AsignacionesComprobanteTO> coleccionAsignaciones) {
		this.coleccionAsignaciones = coleccionAsignaciones;
	}
	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public String getEmpresa_reportado() {
		return empresa_reportado;
	}
	public void setEmpresa_reportado(String empresa_reportado) {
		this.empresa_reportado = empresa_reportado;
	}
	public String getConcepto_inversion() {
		return concepto_inversion;
	}
	public void setConcepto_inversion(String concepto_inversion) {
		this.concepto_inversion = concepto_inversion;
	}

}
