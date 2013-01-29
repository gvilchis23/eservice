package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaRequestTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaResponseTO;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConsultaFrecuentesPagoTarjetaAztecaCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		FrecuentesAztecaRequestTO frecuentesRequestTO=new FrecuentesAztecaRequestTO();
		Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		
		/**Consulta para cuentas frecuentes*/
		
		frecuentesRequestTO.setUser(clienteTO.getUserName());
		FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = creaColeccionFrecuentes(frecuentesResponseTO.getTarjetasFrecuentes());
		tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);
		
		response.addAttribute(tarjetas_FrecuentesTO);
		session.addAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA, coleccion_tarjetas_frecuentesTO);		
		return response;
		
		
	} 
	
	
	
	private Collection <Tarjeta_FrecuenteTO> creaColeccionFrecuentes(Collection<Map> mapaTarjetasFrecuentes){
		Iterator<Map> iterator = mapaTarjetasFrecuentes.iterator();
		Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Tarjeta_FrecuenteTO>();		
		while(iterator.hasNext()){
			Map tarjeta = (Map)iterator.next();
			Tarjeta_FrecuenteTO tarjeta_FrecuenteTO = new Tarjeta_FrecuenteTO();
			tarjeta_FrecuenteTO.setNumero_tarjeta((String)tarjeta.get("destino"));
			tarjeta_FrecuenteTO.setNombre_banco((String)tarjeta.get("bank"));
			tarjeta_FrecuenteTO.setAlias_beneficiario((String)tarjeta.get("alias"));
			tarjeta_FrecuenteTO.setTipo_tarjeta((String)tarjeta.get("tipoTDC"));
			tarjeta_FrecuenteTO.setEstado((String)tarjeta.get("status"));
			
			 String estado="";
				switch(Integer.parseInt(tarjeta_FrecuenteTO.getEstado())){
					case 0:
					case 1: estado="DESBLOQUEADA";
						break;
					case 2:	estado="BLOQUEADA";
						break;
				}
			
				tarjeta_FrecuenteTO.setEstado(estado);
			
			
			
			coleccion_tarjetas_frecuentesTO.add(tarjeta_FrecuenteTO);
			
		}
		return coleccion_tarjetas_frecuentesTO;
	}
	
}
