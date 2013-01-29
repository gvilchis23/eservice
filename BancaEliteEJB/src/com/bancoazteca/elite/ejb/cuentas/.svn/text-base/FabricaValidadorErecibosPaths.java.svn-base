package com.bancoazteca.elite.ejb.cuentas;

import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class FabricaValidadorErecibosPaths {
	private static final String PASO_SOLICITUD="solicitud";
	private static final String PASO_LIBERACION="liberacion";
	
	private FabricaValidadorErecibosPaths(){}
	
	public static ValidaPathsERecibos getInstance(String paso,MessageElement element) throws EliteDataException{
		if(paso.equalsIgnoreCase(PASO_LIBERACION)){
			ValidaPathsLiberacion liberacion=new ValidaPathsLiberacion(element);
			return liberacion;
		}else{
			if(paso.equalsIgnoreCase(PASO_SOLICITUD)){
				ValidaPathsConsulta consulta=new ValidaPathsConsulta(element);
				return consulta;
			}else{
				return null;
			}
		}
	}
	
}
