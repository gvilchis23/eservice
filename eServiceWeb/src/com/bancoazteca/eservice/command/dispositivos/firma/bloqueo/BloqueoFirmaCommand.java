package com.bancoazteca.eservice.command.dispositivos.firma.bloqueo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.BloqueoFirmaRequestTO;
import com.bancoazteca.elite.beans.BloqueoFirmaResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ListaTokenVO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.dispositivos.util.TipoOperacionTokenEnum;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Operaciones_Firma_AztecaTO;
import com.bancoazteca.eservice.command.base.beans.TokenTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class BloqueoFirmaCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(BloqueoFirmaCommand.class);

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);		
		BloqueoFirmaRequestTO bloqueoFirmaRequestTO = new BloqueoFirmaRequestTO();
		bloqueoFirmaRequestTO.setUser(clienteTO.getUserName());
		
		try {
			ResourceFacadeSL facadeSL = getDelegate();
			BloqueoFirmaResponseTO bloqueoFirmaResponseTO = facadeSL.solicitaBloqueoToken(bloqueoFirmaRequestTO);
			Collection<TokenTO> tokenCollectionTO = new ArrayList<TokenTO>();
			for (ListaTokenVO tokenVO : bloqueoFirmaResponseTO.getListaTokenVO()) {
				TokenTO tokenTO = new TokenTO();
				tokenTO.setEstatus(tokenVO.getStatusToken());
				tokenTO.setFecha_activacion(tokenVO.getFechaActi());
				tokenTO.setNumero_serie(tokenVO.getNumSerie());
				tokenCollectionTO.add(tokenTO);
			}
			Operaciones_Firma_AztecaTO bloqueoFirmaTO = new Operaciones_Firma_AztecaTO();
			bloqueoFirmaTO.setColeccion_token(tokenCollectionTO);
			response.addAttribute(bloqueoFirmaTO);
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

		BloqueoFirmaForm forma = (BloqueoFirmaForm)getFormBean();
		BloqueoFirmaRequestTO bloqueoFirmaRequestTO = new BloqueoFirmaRequestTO();
		bloqueoFirmaRequestTO.setUser(clienteTO.getUserName());
		int indexToken = getIndexTokenByNumeroSerie(tokenCollectionTO, forma.getNumero_serie());
		if(indexToken == -1) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ListaToken", "El Token elegido no se encuentra en la lista de Token del usuario");
			throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
		}
		bloqueoFirmaRequestTO.setNumeroSerie(""+indexToken);
		bloqueoFirmaRequestTO.setTipoServicio(TipoOperacionTokenEnum.valueOf(forma.getTipo_operacion().toUpperCase()));
		
		try {
			ResourceFacadeSL facadeSL = getDelegate();
			BloqueoFirmaResponseTO bloqueoFirmaResponseTO = facadeSL.validaBloqueoToken(bloqueoFirmaRequestTO);
			ListaTokenVO tokenVO = bloqueoFirmaResponseTO.getTokenVO();
			
			TokenTO tokenTO = new TokenTO();
			tokenTO.setEstatus(tokenVO.getStatusToken());
			tokenTO.setFecha_activacion(tokenVO.getFechaActi());
			tokenTO.setNumero_serie(tokenVO.getNumSerie());
			response.addAttribute(tokenTO);
			session.addAttribute(BLOQUEO_FIRMA_TO , bloqueoFirmaRequestTO);
			session.addAttribute(OPERACIONES_TOKEN_TO, tokenTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}

		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		BloqueoFirmaRequestTO bloqueoFirmaRequestTO = (BloqueoFirmaRequestTO) session.getAttribute(BLOQUEO_FIRMA_TO);
		TokenTO tokenTO = (TokenTO)session.getAttribute(OPERACIONES_TOKEN_TO);
		BloqueoFirmaForm forma = (BloqueoFirmaForm)getFormBean();
		
		
		if ( bloqueoFirmaRequestTO.getTipoServicio() == TipoOperacionTokenEnum.DESBLOQUEAR ) {
			bloqueoFirmaRequestTO.setTokenCode(forma.getClave_seguridad().toString());
		}

		try {
			ResourceFacadeSL facadeSL = getDelegate();
			BloqueoFirmaResponseTO bloqueoFirmaResponseTO = facadeSL.ejecutaBloqueoToken(bloqueoFirmaRequestTO);	
			
			tokenTO.setEstatus(bloqueoFirmaResponseTO.getEstatusToken());
			response.addAttribute(tokenTO);

		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
}