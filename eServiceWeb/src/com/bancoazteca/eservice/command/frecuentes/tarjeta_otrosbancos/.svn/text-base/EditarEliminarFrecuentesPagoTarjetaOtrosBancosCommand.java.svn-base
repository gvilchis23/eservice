package com.bancoazteca.eservice.command.frecuentes.tarjeta_otrosbancos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

public class EditarEliminarFrecuentesPagoTarjetaOtrosBancosCommand extends CommandBase{
	
	@SuppressWarnings("unchecked")
	public Response ejecucion (Session session) throws Exception{
		final String ELIMINAR = "eliminar";
		final String EDITAR = "editar";
		
		Response response = new Response();
		EditarEliminarFrecuentesPagoTarjetaOtrosBancosForm editarFrecuentesPagoTarjetaOtrosBancosForm = (EditarEliminarFrecuentesPagoTarjetaOtrosBancosForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();		
			frecuentesRequestTO.setUser(clienteTO.getUserName());

			//Consultamos para obtener la colleción de tarjetas y a partir de ella tener el index
			FrecuentesResponseTO frecuentesResponseTO = resourceFacadeSL.setConsultaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);				

			//Buscamos el index de la tarjeta (actual) a editar ó eliminar
			frecuentesRequestTO = getTarjetaFrecuente(frecuentesResponseTO.getTarjetasFrecuentes(), 
					frecuentesRequestTO, editarFrecuentesPagoTarjetaOtrosBancosForm.getIdservicio().equalsIgnoreCase("modifica_frecuentes_tarjetas")?	
					editarFrecuentesPagoTarjetaOtrosBancosForm.getNumero_tarjeta_actual():
					editarFrecuentesPagoTarjetaOtrosBancosForm.getNumero_tarjeta());
			
			if(frecuentesRequestTO.getIndex()!= null){
				//Seteamos los dos datos posibles a editar:alias_beneficiario y/o tarjeta
				if(editarFrecuentesPagoTarjetaOtrosBancosForm.getIdservicio().equalsIgnoreCase("modifica_frecuentes_tarjetas")){

					if(editarFrecuentesPagoTarjetaOtrosBancosForm.getAlias_beneficiario_nuevo()!= null)
						frecuentesRequestTO.setAlias(editarFrecuentesPagoTarjetaOtrosBancosForm.getAlias_beneficiario_nuevo());
					else
						frecuentesRequestTO.setAlias(frecuentesRequestTO.getAlias());			

					if(editarFrecuentesPagoTarjetaOtrosBancosForm.getNumero_tarjeta_nuevo()!= null )				
						frecuentesRequestTO.setTarjeta(editarFrecuentesPagoTarjetaOtrosBancosForm.getNumero_tarjeta_nuevo());				
					else
						frecuentesRequestTO.setTarjeta(editarFrecuentesPagoTarjetaOtrosBancosForm.getNumero_tarjeta_actual());

					frecuentesRequestTO.setMethod(EDITAR);

				}else{
					frecuentesRequestTO.setMethod(ELIMINAR);	
				}		

				//Inicializamos editar ó eliminar
				frecuentesResponseTO = resourceFacadeSL.iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
				//Ejecutamos edita ó eliminar
				frecuentesResponseTO = resourceFacadeSL.ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);

				Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = createCollectionFromMap(frecuentesResponseTO.getTarjetasFrecuentes());

				Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
				tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

				response.addAttribute(tarjetas_FrecuentesTO);
			}else{			
				Map <String, String>map  = new HashMap();
				map.put("numero_tarjeta_actual", "El numero de tarjeta actual no existe, favor de verificar.");
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
	private FrecuentesRequestTO getTarjetaFrecuente(Collection<Map> mapaTarjetasFrecuentes, FrecuentesRequestTO frecuentesRequestTO, 
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
