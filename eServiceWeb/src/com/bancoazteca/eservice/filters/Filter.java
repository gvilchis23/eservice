package com.bancoazteca.eservice.filters;

import java.util.HashMap;


public interface Filter extends ConstantesFiltro{
	
	public void ejecutar( HashMap<String, Object> sessionFilter )throws CuentaFilterException, AlnovaRedMovilFilterException, Exception;

}
