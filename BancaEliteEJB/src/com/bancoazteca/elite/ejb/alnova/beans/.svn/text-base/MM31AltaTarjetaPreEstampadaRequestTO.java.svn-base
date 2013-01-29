package com.bancoazteca.elite.ejb.alnova.beans;

public class MM31AltaTarjetaPreEstampadaRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"TARJETA","NUMCTE","ENTCTA","BRNCTA","NUMCTA","PARTICI","PRODUCT","SUBPROD"};

	public MM31AltaTarjetaPreEstampadaRequestTO() {
		super("MM31", keys);
	}
	
	public void setNumeroTarjeta(String value){
		super.addParameter("TARJETA",value);
	}
	
	public void setNumeroCliente(String value){
		super.addParameter("NUMCTE",value);
	}
	
	public void setEntidad(String value){
		super.addParameter("ENTCTA",value);
	}
	
	public void setSucursal(String value){
		super.addParameter("BRNCTA",value);
	}
	public void setCuenta(String value){
		super.addParameter("NUMCTA",value);
	}
	
	public void setTitularidad(String value){
		super.addParameter("PARTICI",value);
	}
	public void setProducto(String value){
		super.addParameter("PRODUCT",value);
	}
	
	public void setSubProducto(String value){
		super.addParameter("SUBPROD",value);
	}
}