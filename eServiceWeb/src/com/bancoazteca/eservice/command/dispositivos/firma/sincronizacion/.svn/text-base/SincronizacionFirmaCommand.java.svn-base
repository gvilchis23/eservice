package com.bancoazteca.eservice.command.dispositivos.firma.sincronizacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ListaTokenVO;
import com.bancoazteca.elite.beans.SincronizacionFirmaRequestTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Sincronizacion_Firma_AztecaTO;
import com.bancoazteca.eservice.command.base.beans.Confirmacion_Firma_AztecaTO;
import com.bancoazteca.eservice.command.base.beans.TokenTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class SincronizacionFirmaCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(SincronizacionFirmaCommand.class);

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO = new SincronizacionFirmaRequestTO();
		sincronizacionFirmaRequestTO.setUser(clienteTO.getUserName());

		try {
			ResourceFacadeSL facadeSL = getDelegate();
			SincronizacionFirmaResponseTO sincronizacionFirmaResponseTO = facadeSL.solicitaSincronizaFirma(sincronizacionFirmaRequestTO);
			Collection<TokenTO> tokenCollectionTO = new ArrayList<TokenTO>();
			for (ListaTokenVO tokenVO : sincronizacionFirmaResponseTO.getListaTokenVO()) {
				TokenTO tokenTO = new TokenTO();
				tokenTO.setEstatus(tokenVO.getStatusToken());
				tokenTO.setFecha_activacion(tokenVO.getFechaActi());
				tokenTO.setNumero_serie(tokenVO.getNumSerie());
				tokenCollectionTO.add(tokenTO);
			}
			Sincronizacion_Firma_AztecaTO sincronizacionFirmaTO = new Sincronizacion_Firma_AztecaTO();
			sincronizacionFirmaTO.setColeccion_token(tokenCollectionTO);
			response.addAttribute(sincronizacionFirmaTO);
			session.addAttribute(LISTA_TOKEN, tokenCollectionTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		Collection<TokenTO> tokenCollectionTO = (Collection<TokenTO>) session.getAttribute(LISTA_TOKEN);
		SincronizacionFirmaForm forma = (SincronizacionFirmaForm)getFormBean();
		SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO = new SincronizacionFirmaRequestTO();

		int indexToken = getIndexTokenByNumeroSerie(tokenCollectionTO, forma.getNumero_serie()); 
		if(indexToken == -1) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ListaToken", "El Token elegido no se encuentra en la lista de Token del usuario");
			throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
		}
		sincronizacionFirmaRequestTO.setNumeroSerie(""+ indexToken);
		sincronizacionFirmaRequestTO.setUser(clienteTO.getUserName());
		sincronizacionFirmaRequestTO.setPreToken(forma.getClave_seguridad().toString());

		try {
			ResourceFacadeSL facadeSL = getDelegate();
			SincronizacionFirmaResponseTO sincronizacionFirmaResponseTO = facadeSL.validaSincronizaFirma(sincronizacionFirmaRequestTO);
			
			Confirmacion_Firma_AztecaTO sincronizacionFirmaTO = new Confirmacion_Firma_AztecaTO();
			sincronizacionFirmaTO.setNumero_serie(sincronizacionFirmaResponseTO.getNumeroSerie());
			sincronizacionFirmaTO.setClave_seguridad(sincronizacionFirmaResponseTO.getPreToken());
			
			response.addAttribute(sincronizacionFirmaTO);
			session.addAttribute(CONFIRMACION_NUMERO_SERIE, forma.getNumero_serie());
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		SincronizacionFirmaForm forma = (SincronizacionFirmaForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);		
		Collection<TokenTO> tokenCollectionTO = (Collection<TokenTO>) session.getAttribute(LISTA_TOKEN);
		String numeroSerie = (String)session.getAttribute(CONFIRMACION_NUMERO_SERIE);
		if (Validator.isEmptyData(numeroSerie)) {
			numeroSerie = forma.getNumero_serie();
		}

		SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO = new SincronizacionFirmaRequestTO();
		int indexToken = getIndexTokenByNumeroSerie(tokenCollectionTO, numeroSerie);
		if(indexToken == -1) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ListaToken", "El Token elegido no se encuentra en la lista de Token del usuario");
			throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
		}
		sincronizacionFirmaRequestTO.setNumeroSerie(""+ indexToken);
		sincronizacionFirmaRequestTO.setUser(clienteTO.getUserName());
		sincronizacionFirmaRequestTO.setActToken(forma.getClave_seguridad().toString());
		
		try {
			ResourceFacadeSL facadeSL = getDelegate();
			facadeSL.ejecutaSincronizaFirma(sincronizacionFirmaRequestTO);
			session.deleteAttribute(CONFIRMACION_NUMERO_SERIE);
						
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
}
