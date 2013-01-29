package com.bancoazteca.eservice.command.frecuentes.transferencias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class EliminarFrecuentesTransferenciasCommand  extends CommandBase{
	
	private static final Logger log = Logger.getLogger(EliminarFrecuentesTransferenciasCommand.class);
	
	public Response ejecucion(Session session) throws Exception{
		log.info("EliminarFrecuentesTransferenciasCommand.ejecucion");
		
		Response response = new Response();
		EliminarFrecuentesTransferenciasForm eliminarFrecuentesTransferenciasForm = (EliminarFrecuentesTransferenciasForm) getFormBean();
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		
		try{
			
			String tipoFrecuente = eliminarFrecuentesTransferenciasForm.getTipo_transferencia(); 
			
			if(tipoFrecuente.equalsIgnoreCase("TERCEROS")){			
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.TERCEROS);
			}else if(tipoFrecuente.equalsIgnoreCase("SPEI")){
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.SPEI);
			}else if(tipoFrecuente.equalsIgnoreCase("INTERNACIONAL")){
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.INTERNACIONAL);
			}else if(tipoFrecuente.equalsIgnoreCase(CuentasFrecuentesRequestTO.TEF)){
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.TEF);
			}
			cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
			CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = facadeSL.getCuentasFrecuentes(cuentasFrecuentesRequestTO);
			Collection<CuentasFrecuentesTO> cuentasFrecuentesColl =  cuentasFrecuentesResponseTO.getCuentasFrecuentes();
			
			CuentasFrecuentesTO cuentaFrecuente = null;
			CuentasFrecuentesPredicate cuentaFrecuentePredicate = new CuentasFrecuentesPredicate();
			cuentaFrecuentePredicate.setCuenta(eliminarFrecuentesTransferenciasForm.getCuenta());
			cuentaFrecuente = (CuentasFrecuentesTO) CollectionUtils.find(cuentasFrecuentesColl, cuentaFrecuentePredicate);
			
			if(cuentaFrecuente != null){
				log.info("Indice ..........."+cuentaFrecuente.getIndice());
				
				cuentasFrecuentesRequestTO.setIndex(cuentaFrecuente.getIndice());
				
			}else{
				Map<String, String> error = new HashMap<String, String>();
				error.put("Error ", "Numero de cuenta incorrecto. Favor de verificar");
				
				throw new EliteDataException("ERROR!!" ,error, EliteRules.LEVEL_ACTION_ERRORS);
			}

			facadeSL.getIntenationalesDatosEliminaCuenta(cuentasFrecuentesRequestTO);
			facadeSL.setIntenationalesEliminaCuenta(cuentasFrecuentesRequestTO);
			
			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferenciasCommand = new ConsultaFrecuentesTransferenciasCommand();
			response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, tipoFrecuente));
				
		}catch(EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {	
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		return response;
	}
	
	private class CuentasFrecuentesPredicate implements Predicate{
		private String cuenta;
		
		public boolean evaluate(Object obj) {
			CuentasFrecuentesTO cuenta_FrecuenteTO = (CuentasFrecuentesTO) obj;
			if (cuenta_FrecuenteTO.getCuentaDestino().equals(cuenta)) {
				return true;
			} else {
				return false;
			}
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}

}
