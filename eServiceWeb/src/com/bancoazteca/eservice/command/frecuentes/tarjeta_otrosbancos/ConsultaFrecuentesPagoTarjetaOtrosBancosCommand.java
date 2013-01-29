package com.bancoazteca.eservice.command.frecuentes.tarjeta_otrosbancos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class ConsultaFrecuentesPagoTarjetaOtrosBancosCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
			frecuentesRequestTO.setUser(clienteTO.getUserName());
			FrecuentesResponseTO consultaFrecuentesResponseTO = resourceFacadeSL.setConsultaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);

			Iterator<Map> iterator = consultaFrecuentesResponseTO.getTarjetasFrecuentes().iterator();
			Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Tarjeta_FrecuenteTO>();		
			while(iterator.hasNext()){
				Map tarjeta = (Map)iterator.next();
				Tarjeta_FrecuenteTO tarjeta_FrecuenteTO = new Tarjeta_FrecuenteTO();
				tarjeta_FrecuenteTO.setNumero_tarjeta((String)tarjeta.get("destino"));
				tarjeta_FrecuenteTO.setNombre_banco((String)tarjeta.get("bank"));
				tarjeta_FrecuenteTO.setAlias_beneficiario((String)tarjeta.get("alias"));
				tarjeta_FrecuenteTO.setTipo_tarjeta((String)tarjeta.get("tipoTDC"));;
				coleccion_tarjetas_frecuentesTO.add(tarjeta_FrecuenteTO);
			}

			Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
			tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

			session.addAttribute(CONSULTA_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS_RESPONSE, consultaFrecuentesResponseTO);
			
			response.addAttribute(tarjetas_FrecuentesTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	} 
}
