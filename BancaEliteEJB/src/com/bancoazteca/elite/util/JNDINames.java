package com.bancoazteca.elite.util;


/**
 * @author Jorge Bringas
 */
public interface JNDINames {

	// Resource Facade              
	
	
	
	final String RESOURCE_FACADE = "java:comp/env/ejb/ResourceFacadeSL";
	final String RESOURCE_FACADE2 = "java:comp/env/ejb/ResourceFacadeSegundoSL";
	// Usuario
	final String USUARIO = "java:comp/env/ejb/UsuarioSL";

	// Cuentas
	final String CUENTAS = "java:comp/env/ejb/CuentasSL";

	// Transferencias
	final String TRANSFERENCIAS = "java:comp/env/ejb/TransferenciasSL";

	// Traspasos
	final String TRASPASOS = "java:comp/env/ejb/TraspasosSL";
	
	//Pago de Servicios
	final String PAGO_SERVICIOS = "java:comp/env/ejb/PagoServiciosSL";
	
	//Inversiones
	final String INVERSIONES = "java:comp/env/ejb/InversionesSL";
	
	final String ALNOVA = "java:comp/env/ejb/AlnovaEjbSL";
	
	//Preferencias.
	final String PREFERENCIAS = "java:comp/env/ejb/PreferenciasSL";
	
	//dispositivos de seguridad.
	final String DISPOSITIVOS = "java:comp/env/ejb/DispositivoSL";
	
	//Chequera
	final String CHEQUERA = "java:comp/env/ejb/ChequeraSL";

}
