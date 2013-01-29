package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class P011_DatosClienteRequest extends AlnovaRequest {


	private static final String[] keys={"OPECOD","CUSNUM"};
		
	
	public P011_DatosClienteRequest() {
		super("P011",keys);
		addParameter("OPECOD","C");
	}


	public void setNumeroCliente(String value){
		addParameter("CUSNUM",value);
	}
	
}
