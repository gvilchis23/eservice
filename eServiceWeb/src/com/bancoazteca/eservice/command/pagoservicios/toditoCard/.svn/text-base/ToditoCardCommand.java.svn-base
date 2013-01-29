package com.bancoazteca.eservice.command.pagoservicios.toditoCard;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.PagoServiciosToditoCardRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosToditoCardResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.CuentaTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_ChequeTO;
import com.bancoazteca.eservice.command.base.beans.Todito_CardTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ToditoCardCommand extends CommandBase{
	
	public Response solicitud(Session session) throws Exception{
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();
		Todito_CardTO todito_CardTO = new Todito_CardTO();
		PagoServiciosToditoCardRequestTO toditoCardRequestTO = new PagoServiciosToditoCardRequestTO();  
		
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			toditoCardRequestTO.setUser(clienteTO.getUserName());
			PagoServiciosToditoCardResponseTO responseTO = bean.getInitialToditoCard(toditoCardRequestTO);
			
			
			CuentaTO cuentaTO = null;
			ArrayList<CuentaTO> coleccion_cuentas_cargo=new ArrayList<CuentaTO>();
			for(CuentaVO cuenta: responseTO.getCuentas()){
				cuentaTO = new CuentaTO();
				cuentaTO.setNumero_cuenta(Formatter.formatCuenta(cuenta.getNumero()));
				cuentaTO.setProducto(cuenta.getDescripcion());
				cuentaTO.setSaldo_disponible("$  " + cuenta.getDisponible());
				coleccion_cuentas_cargo.add(cuentaTO);
			}
			todito_CardTO.setColeccion_cuentas_cargo(coleccion_cuentas_cargo);
			
			
			response.addAttribute(todito_CardTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();
		return response;
	}
	
}
