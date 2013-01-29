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
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EditarFrecuentesPagoTarjetaAztecaCommand extends CommandBase{
	
	
	public Response validacion(Session session) throws Exception{
		 
		Response response = new Response();
		HuellaTO huellaTO = new HuellaTO();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		FrecuentesAztecaRequestTO frecuentesRequestTO=new FrecuentesAztecaRequestTO();
		frecuentesRequestTO.setUser(clienteTO.getUserName());
		FrecuentesAztecaResponseTO frecuentesAztecaResponseTO=new FrecuentesAztecaResponseTO();
		Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			
	
			EditarFrecuentesPagoTarjetaAztecaForm frecuentesForm = (EditarFrecuentesPagoTarjetaAztecaForm) getFormBean();
			
			if(session.getAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA) == null){				
				
				frecuentesAztecaResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);				

				session.addAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA, frecuentesAztecaResponseTO);
			}
			
			
			frecuentesRequestTO = getTarjetaFrecuente(frecuentesAztecaResponseTO.getTarjetasFrecuentes(), frecuentesRequestTO,frecuentesForm.getNumero_tarjeta());
			frecuentesRequestTO.setIndex(frecuentesRequestTO.getIndex());
			//Nuevo metodo de validacion que realiza el paso de verificacion
			
			FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.validarEdicionCuentasFrecuentes(frecuentesRequestTO);
			
	
			if(frecuentesResponseTO.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica( frecuentesResponseTO.getDispositivoHuellaTO().getLlavePublica() );
				huellaTO.setLongitud_huella( frecuentesResponseTO.getDispositivoHuellaTO().getLongitudHuella() );
			}
	
			Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = createCollectionValidacion(frecuentesResponseTO.getTarjetasFrecuentes(),frecuentesForm);
			tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

			response.addAttribute(tarjetas_FrecuentesTO);
			response.addAttribute(huellaTO);

		} catch (EliteDataException e) {					
			buildErrorResponse(e, response);
		}		
		 return response;
	 }

	
	
	@SuppressWarnings("unchecked")
	public Response ejecucion (Session session) throws Exception{
		final String EDITAR = "editar";
		
		Response response = new Response();
		EditarFrecuentesPagoTarjetaAztecaForm editarFrecuentesPagoTarjetaAztecaForm = (EditarFrecuentesPagoTarjetaAztecaForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			FrecuentesAztecaRequestTO frecuentesRequestTO = new FrecuentesAztecaRequestTO();		
			frecuentesRequestTO.setUser(clienteTO.getUserName());

			//Consultamos para obtener la colleción de tarjetas y a partir de ella tener el index
			FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);				

			//Buscamos el index de la tarjeta (actual) a editar ó eliminar
			frecuentesRequestTO = getTarjetaFrecuente(frecuentesResponseTO.getTarjetasFrecuentes(), frecuentesRequestTO,editarFrecuentesPagoTarjetaAztecaForm.getNumero_tarjeta());
			frecuentesRequestTO.setMethod(EDITAR);
			if(frecuentesRequestTO.getIndex()!= null){
				//Seteamos los dos datos posibles a editar:alias_beneficiario y/o tarjeta
				
				
				if(editarFrecuentesPagoTarjetaAztecaForm.getAlias_nuevo()!= null)
						frecuentesRequestTO.setAlias(editarFrecuentesPagoTarjetaAztecaForm.getAlias_nuevo());
					else
						frecuentesRequestTO.setAlias(frecuentesRequestTO.getAlias());			

					if(editarFrecuentesPagoTarjetaAztecaForm.getNumero_tarjeta_nuevo()!= null )				
						frecuentesRequestTO.setTarjeta(editarFrecuentesPagoTarjetaAztecaForm.getNumero_tarjeta_nuevo());				
					else
						frecuentesRequestTO.setTarjeta(frecuentesRequestTO.getTarjeta());
	
				
				frecuentesResponseTO = resourceFacadeSL.iniciarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			
				//Vemos la opcion del token
				
				if (editarFrecuentesPagoTarjetaAztecaForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
					frecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
					frecuentesRequestTO.setTokenCode(editarFrecuentesPagoTarjetaAztecaForm.getClave_seguridad().toString());
				} else if (editarFrecuentesPagoTarjetaAztecaForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
					frecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
					frecuentesRequestTO.setClave(editarFrecuentesPagoTarjetaAztecaForm.getHuella_seguridad().toString());
				}
				
				frecuentesResponseTO = resourceFacadeSL.ejecutarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);

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
	
	
	
	@SuppressWarnings("unchecked")
	private Collection <Tarjeta_FrecuenteTO> createCollectionValidacion(Collection<Map> mapaTarjetasFrecuentes,EditarFrecuentesPagoTarjetaAztecaForm frecuentesForm){
		Iterator<Map> iterator = mapaTarjetasFrecuentes.iterator();
		Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Tarjeta_FrecuenteTO>();		
		while(iterator.hasNext()){
			Map tarjeta = (Map)iterator.next();
			Tarjeta_FrecuenteTO tarjeta_FrecuenteTO = new Tarjeta_FrecuenteTO();
			tarjeta_FrecuenteTO.setNumero_tarjeta(frecuentesForm.getNumero_tarjeta());
			tarjeta_FrecuenteTO.setAlias_beneficiario(frecuentesForm.getAlias());
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
