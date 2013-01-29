package com.bancoazteca.eservice.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.util.PropertiesService;

public class CuentaFilter implements Filter {

	public void ejecutar(HashMap<String , Object> sessionFilter )throws CuentaFilterException {
		ClienteTO cliente = (ClienteTO) sessionFilter.get(CLIENTE_TO);
		String idAplicacion = (String) sessionFilter.get(ConstantesFiltro.ID_APLICACION);
		CuentaTO cuentaTO = getCuenta( cliente.getCuentas() , idAplicacion);
		if(cuentaTO == null){
			throw new CuentaFilterException("No se encontro la cuenta de Red Móvil Azteca");
		}else{
			sessionFilter.put(CUENTA_ALNOVA, cuentaTO.getNumero());
		}
	}

	private CuentaTO getCuenta(Collection<CuentaTO> cuentas, String idAplicacion){
		String producto = "";
		String subProducto = "";
		try {
			 producto = PropertiesService.getInstance().getPropertie( FILTRO_PROPERTIES, PREFIJO_FILTROS_PROPERTIES + idAplicacion + ALNOVA_PRODUCTO );
			 subProducto = PropertiesService.getInstance().getPropertie(FILTRO_PROPERTIES, PREFIJO_FILTROS_PROPERTIES + idAplicacion + ALNOVA_SUBPRODUCTO );
			 producto = producto.trim();
			 subProducto = subProducto.trim();
		} catch (IOException e) {
			System.out.println("No se encontro filtro.properties o la definición de un atributo");
		}
		for(CuentaTO cuenta: cuentas){
			if(cuenta.getProducto().equalsIgnoreCase(producto) && cuenta.getSubproducto().equalsIgnoreCase(subProducto)){
				return cuenta;
			}
		}
		return null;
	}

}
