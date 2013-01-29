package com.bancoazteca.elite.ejb.alnova.beans;


public class MM31AsignacionTarjetaRequestTO extends EjbAlnovaRequestTO {

	
	private static final long serialVersionUID = -7578682399357653994L;
	private static final String[] parameters = {"TARJETA"
												,"NUMCTE"												
												,"ENTCTA"
												,"BRNCTA"
												,"NUMCTA"
												,"PARTICI"
												,"PRODUCT"
												,"SUBPROD"};
	
	
	public MM31AsignacionTarjetaRequestTO() {
		super("MM31",parameters);
	}
	
	public void setNumeroCuenta(String cta){
		super.addParameter("NUMCTA", cta);
	}
	
	public void setSucursal(String sucursal){
		super.addParameter("BRNCTA", sucursal);
	}
	
	public void setEntidad(String entidad){
		super.addParameter("ENTCTA", entidad);
	}
	
	public void setTarjeta(String tarjeta){
		super.addParameter("TARJETA", tarjeta);
	}
	
	public void setNumeroCliente(String numCliente){
		super.addParameter("NUMCTE", numCliente);
	}
	
	public void setTitularOAdicional(String titularOAdicional){
		super.addParameter("PARTICI", titularOAdicional);
	}
	
	public void setProducto(String producto){
		super.addParameter("PRODUCT", producto);
	}
	
	public void setSubProducto(String supProducto){
		super.addParameter("SUBPROD", supProducto);
	}
}