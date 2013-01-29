package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class CuentaTO implements Serializable {

	private static final long serialVersionUID = 747295134524945663L;

	private String index;
	private Double balance;
	private String clabe;
	private String descripcion;
	private Double disponible;
	private String moneda;
	private String cotitulares;
	private String numero;
	private String producto;
	private Double retenido;
	private String subproducto;
	private String sucursal;
	private String movimientos;
	private Double totalNPesos;
	private String comisionServChequera;
	private String tipoCambioCompra;
	private String tipoCambioVenta;
	private String nombreMoneda;

	public String cuentaFormateada;
	public String cuenctaClabeFormateada;
	public String cuentaFormateadaCombos;

	private Collection<MovimientosTO> movimientosTO;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getDisponible() {
		return disponible;
	}

	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCotitulares() {
		return cotitulares;
	}

	public void setCotitulares(String cotitulares) {
		this.cotitulares = cotitulares;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Double getRetenido() {
		return retenido;
	}

	public void setRetenido(Double retenido) {
		this.retenido = retenido;
	}

	public String getSubproducto() {
		return subproducto;
	}

	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(String movimientos) {
		this.movimientos = movimientos;
	}
     
	public Double getTotalNPesos() {
		return totalNPesos;
	}

	public void setTotalNPesos(Double totalNPesos) {
		this.totalNPesos = totalNPesos;
	}

	public String getComisionServChequera() {
		return comisionServChequera;
	}

	public void setComisionServChequera(String comisionServChequera) {
		this.comisionServChequera = comisionServChequera;
	}

	public String getTipoCambioCompra() {
		return tipoCambioCompra;
	}

	public void setTipoCambioCompra(String tipoCambioCompra) {
		this.tipoCambioCompra = tipoCambioCompra;
	}

	public String getTipoCambioVenta() {
		return tipoCambioVenta;
	}

	public void setTipoCambioVenta(String tipoCambioVenta) {
		this.tipoCambioVenta = tipoCambioVenta;
	}

	public String getNombreMoneda() {
		return nombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}

	public Collection<MovimientosTO> getMovimientosTO() {
		return movimientosTO;
	}

	public void setMovimientosTO(Collection<MovimientosTO> movimientosTO) {
		this.movimientosTO = movimientosTO;
	}

	public String getCuentaFormateada() {
		return cuentaFormateada;
	}

	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}

	public String getCuenctaClabeFormateada() {
		return cuenctaClabeFormateada;
	}

	public void setCuenctaClabeFormateada(String cuenctaClabeFormateada) {
		this.cuenctaClabeFormateada = cuenctaClabeFormateada;
	}

	public String getCuentaFormateadaCombos() {
		return cuentaFormateadaCombos;
	}

	public void setCuentaFormateadaCombos(String cuentaFormateadaCombos) {
		this.cuentaFormateadaCombos = cuentaFormateadaCombos;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getNumeroCuenta14(){
		String cuenta = getNumero().replaceAll("\\s", "");
		if( cuenta != null ){
			if( cuenta.length() == 20 )
				return cuenta.substring( 4, 8 ).concat( cuenta.substring( 10, 20 ) );
			else 
			if ( cuenta.length() < 14 ) {
				StringBuffer temp = new StringBuffer();
				for( int i = 0; i < 14 - cuenta.length(); i++)
					temp.append( "0" );
				temp.append( cuenta );
				cuenta = temp.toString();
			}
		}
		return cuenta;
	}

}
