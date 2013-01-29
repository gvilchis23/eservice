package com.bancoazteca.elite.connector;

import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.ejb.usuario.UsuarioDAO;

public class ConnectorTest {
	
	



	
	
	public static void main(String[] args) {
		
	
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			LoginRequestTO loginRequestTO = new LoginRequestTO();
			loginRequestTO.setUser("maestrodelweb");
			loginRequestTO.setPassword("baz2005");
			usuarioDAO.login(loginRequestTO);
			
			Map<String, String> params = new HashMap<String, String>();

			Connector connector = ConnectorManager.getConnector("maestrodelweb");
			
			params.put("servicio", "00001");
			connector.sendServletRequest("/servicios/servicioDisponible.elite", params);
			
			params.clear();
			params.put("method", "confirmarSaldo");
			params.put("cuentaReferencia", "1234567890");
			params.put("digitoVerificador", "8");
			params.put("cuentaCargo", "00001");
			params.put("tipoServicio", "00001");
			params.put("importe ", "1");			
			connector.sendServletRequest("/servicios/confirmarSaldo.elite", params);
		
			/*
			params.clear();
			params.put("tokencode", "1");	
			connector.sendServletRequest("/servicios/pagoServicioPassword.elite", params);
			
			*/
			
			
			usuarioDAO.logout("maestrodelweb");
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		} 
	}

}
