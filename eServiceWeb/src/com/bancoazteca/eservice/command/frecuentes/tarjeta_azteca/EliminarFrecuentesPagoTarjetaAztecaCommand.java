package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaRequestTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EliminarFrecuentesPagoTarjetaAztecaCommand extends CommandBase{
	
	@SuppressWarnings("unchecked")
	public Response ejecucion (Session session) throws Exception{
		final String ELIMINAR = "eliminar";
		
		Response response = new Response();
		EliminarFrecuentesPagoTarjetaAztecaForm eliminarFrecuentesPagoTarjetaAztecaForm = (EliminarFrecuentesPagoTarjetaAztecaForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			FrecuentesAztecaRequestTO frecuentesRequestTO = new FrecuentesAztecaRequestTO();		
			frecuentesRequestTO.setUser(clienteTO.getUserName());

			//Consultamos para obtener la colleción de tarjetas y a partir de ella tener el index
			
			FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);				

			frecuentesRequestTO = getTarjetaFrecuente(frecuentesResponseTO.getTarjetasFrecuentes(), 
					frecuentesRequestTO, eliminarFrecuentesPagoTarjetaAztecaForm.getNumero_tarjeta());
			frecuentesRequestTO.setMethod(ELIMINAR);
			if(frecuentesRequestTO.getIndex()!= null){
			
						frecuentesRequestTO.setAlias(frecuentesRequestTO.getAlias());
						frecuentesRequestTO.setTarjeta(frecuentesRequestTO.getTarjeta());
						frecuentesRequestTO.setBanco(frecuentesRequestTO.getBanco());
						frecuentesRequestTO.setTipoTarjeta(frecuentesRequestTO.getTipoTarjeta());

				
				//Inicializamos editar ó eliminar
						
				frecuentesResponseTO = resourceFacadeSL.iniciarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			
				//Vemos la opcion del token
				
				frecuentesResponseTO = resourceFacadeSL.ejecutarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);

				Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = createCollectionFromMap(frecuentesResponseTO.getTarjetasFrecuentes());

				Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
				tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

				response.addAttribute(tarjetas_FrecuentesTO);
			}else{			
				Map <String, String>map  = new HashMap();
				map.put("numero_tarjeta", "El numero de tarjeta no existe, favor de verificar.");
				throw new EliteDataException("Error", map, EliteRules.LEVEL_ACTION_ERRORS);
			}
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private Collection <Tarjeta_FrecuenteTO> createCollectionFromMap(Collection<Map> mapaTarjetasFrecuentes){
		Iterator<Map> iterator = mapaTarjetasFrecuentes.iterator();
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
		return coleccion_tarjetas_frecuentesTO;
	}
	
	@SuppressWarnings("unchecked")
	private FrecuentesAztecaRequestTO getTarjetaFrecuente(Collection<Map> mapaTarjetasFrecuentes, FrecuentesAztecaRequestTO frecuentesRequestTO, 
			String numTarjeta){
		
		Iterator<Map> iterator = mapaTarjetasFrecuentes.iterator();		
		int contador = 0;
		while(iterator.hasNext()){
			Map tarjeta = (Map)iterator.next();
			if(tarjeta.get("destino").equals(numTarjeta)){
				frecuentesRequestTO.setTarjeta((String)tarjeta.get("destino"));
				frecuentesRequestTO.setBanco((String)tarjeta.get("bank"));
				frecuentesRequestTO.setAlias((String)tarjeta.get("alias"));
				frecuentesRequestTO.setTipoTarjeta((String)tarjeta.get("tipoTDC"));
				frecuentesRequestTO.setIndex(String.valueOf(contador));
				break;
			}
			contador++;
		}
		return frecuentesRequestTO;
	}
		
}
