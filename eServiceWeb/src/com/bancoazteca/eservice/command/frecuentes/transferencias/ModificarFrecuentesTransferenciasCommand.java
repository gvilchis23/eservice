package com.bancoazteca.eservice.command.frecuentes.transferencias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.BancoSpeiTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class ModificarFrecuentesTransferenciasCommand extends CommandBase {
	
	private static final Logger log = Logger.getLogger(ModificarFrecuentesTransferenciasCommand.class);
	
	public Response validacion(Session session) throws Exception{
		log.info("ModificarFrecuentesTransferenciasCommand.validacion");
		
		Response response = new Response();
		ModificarFrecuentesTransferenciasForm modificarFrecuentesTransferenciasForm = (ModificarFrecuentesTransferenciasForm)getFormBean();
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		HuellaTO huellaTO = new HuellaTO();
		
		cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
		String tipoFrecuente = modificarFrecuentesTransferenciasForm.getTipo_transferencia();
		
		try{
			if(tipoFrecuente.equalsIgnoreCase("TERCEROS")){
				
				if(Validator.isEmptyData(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo())){
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_actual());
				}else{
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo());
				}
				
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.TERCEROS);
				cuentasFrecuentesRequestTO.setAlias(modificarFrecuentesTransferenciasForm.getAlias());
				cuentasFrecuentesRequestTO.setEmail(modificarFrecuentesTransferenciasForm.getCorreo_electronico());
				cuentasFrecuentesRequestTO.setCelular(modificarFrecuentesTransferenciasForm.getNumero_celular());
				cuentasFrecuentesRequestTO.setCompania(modificarFrecuentesTransferenciasForm.getCompania_celular());
				cuentasFrecuentesRequestTO.setTelCasa(modificarFrecuentesTransferenciasForm.getNumero_casa());
				cuentasFrecuentesRequestTO.setTelOficina(modificarFrecuentesTransferenciasForm.getNumero_oficina());
				
				
			}else if(tipoFrecuente.equalsIgnoreCase("SPEI")){
												   
				if(Validator.isEmptyData(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo())){
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_actual());
				}else{
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo());
				}
				
				TransferenciasSpeiRequestTO speiRequestTO = new TransferenciasSpeiRequestTO();
				TransferenciasSpeiResponseTO speiResponseTO = new TransferenciasSpeiResponseTO();
								
				speiRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
				speiResponseTO = facadeSL.getTransferenciaSpeiInvocacion(speiRequestTO);
				Collection <BancoSpeiTO> bancTO =  speiResponseTO.getBancos();
				
				BancoSpeiTO bSpeiTO = null;
				BancosSpeiPredicate bancosSpeiPredicate = new BancosSpeiPredicate();
				bancosSpeiPredicate.setBanco(modificarFrecuentesTransferenciasForm.getBanco());
				bSpeiTO = (BancoSpeiTO) CollectionUtils.find(bancTO, bancosSpeiPredicate);
				
				if(bSpeiTO != null){
					log.info("Indice Bnaco..........."+bSpeiTO.getIndex());
					cuentasFrecuentesRequestTO.setBanco(bSpeiTO.getIndex());
				}else{
					Map<String, String> error = new HashMap<String, String>();
					error.put("Error ", "Nombre de banco incorrecto. Favor de verificar");
	
					throw new EliteDataException("ERROR!!" ,error, EliteRules.LEVEL_ACTION_ERRORS);
				}
							
				/*aqui*/
				
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.SPEI);
				cuentasFrecuentesRequestTO.setBeneficiario(modificarFrecuentesTransferenciasForm.getNombre_beneficiario());
				cuentasFrecuentesRequestTO.setAlias(modificarFrecuentesTransferenciasForm.getAlias());
				cuentasFrecuentesRequestTO.setTelCasa(modificarFrecuentesTransferenciasForm.getNumero_casa());
				cuentasFrecuentesRequestTO.setTelOficina(modificarFrecuentesTransferenciasForm.getNumero_oficina());
				cuentasFrecuentesRequestTO.setCelular(modificarFrecuentesTransferenciasForm.getNumero_celular());
				cuentasFrecuentesRequestTO.setBeneficiario(modificarFrecuentesTransferenciasForm.getBeneficiario());
				cuentasFrecuentesRequestTO.setEmail(modificarFrecuentesTransferenciasForm.getCorreo_electronico());
				cuentasFrecuentesRequestTO.setCompania(modificarFrecuentesTransferenciasForm.getCompania_celular());
				
				
			}else if(tipoFrecuente.equalsIgnoreCase("INTERNACIONAL")){
				
				if(Validator.isEmptyData(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo())){
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_actual());
				}else{
					cuentasFrecuentesRequestTO.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_nuevo());
				}
				
				cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.INTERNACIONAL);
				cuentasFrecuentesRequestTO.setBeneficiario(modificarFrecuentesTransferenciasForm.getNombre_beneficiario());								   
				cuentasFrecuentesRequestTO.setClaveBanco(modificarFrecuentesTransferenciasForm.getCodigo_swift_aba());
			}
	
			CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = facadeSL.getCuentasFrecuentes(cuentasFrecuentesRequestTO);
			Collection<CuentasFrecuentesTO> cuentasFrecuentesColl =  cuentasFrecuentesResponseTO.getCuentasFrecuentes();
			
			CuentasFrecuentesTO cuentaFrecuente = null;
			CuentasFrecuentesPredicate cuentaFrecuentePredicate = new CuentasFrecuentesPredicate();
			cuentaFrecuentePredicate.setCuenta(modificarFrecuentesTransferenciasForm.getNumero_cuenta_actual());
			cuentaFrecuente = (CuentasFrecuentesTO) CollectionUtils.find(cuentasFrecuentesColl, cuentaFrecuentePredicate);
			
			if(cuentaFrecuente != null){
				log.info("Indice de cuenta frecuente: "+cuentaFrecuente.getIndice());
				
				cuentasFrecuentesRequestTO.setIndex(cuentaFrecuente.getIndice());
				facadeSL.getModificaDatosCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);
				CuentasFrecuentesResponseTO frecuentesResponseTO = facadeSL.setModificaDatosCuentaFrecuentesTraspasosConf(cuentasFrecuentesRequestTO);
				
				DispositivoHuellaTO dispositivoHuellaTO = frecuentesResponseTO.getDispositivoHuellaTO();
				huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
				huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
				response.addAttribute(huellaTO);
				
			}else{
				Map<String, String> error = new HashMap<String, String>();
				error.put("Error ", "Numero de cuenta incorrecto. Favor de verificar");
				
				throw new EliteDataException("ERROR!!" ,error, EliteRules.LEVEL_ACTION_ERRORS);
			}
			
			
			session.addAttribute("ModificaFrecuentesRequest", cuentasFrecuentesRequestTO);
			
			
		}catch(EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {	
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}	
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception{
		log.info("ModificarFrecuentesTransferenciasCommand.ejecucion");
		
		Response response = new Response();
		ModificarFrecuentesTransferenciasForm altaFrecuenteInternacionalForm = (ModificarFrecuentesTransferenciasForm)getFormBean();
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		
		try{
			
			CuentasFrecuentesRequestTO ctaFrecuentesRequestSession = (CuentasFrecuentesRequestTO) session.getAttribute("ModificaFrecuentesRequest");
			
			cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
			cuentasFrecuentesRequestTO.setCuenta(ctaFrecuentesRequestSession.getCuenta());
			cuentasFrecuentesRequestTO.setType(ctaFrecuentesRequestSession.getType());
			
			if (altaFrecuenteInternacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				cuentasFrecuentesRequestTO.setTokencode(altaFrecuenteInternacionalForm.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
			}else if (altaFrecuenteInternacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				cuentasFrecuentesRequestTO.setClave(altaFrecuenteInternacionalForm.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
			}
			
			facadeSL.setGuardaModificaCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);
			log.info(":: Ejecuto la modificacion");
			
			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferenciasCommand = new ConsultaFrecuentesTransferenciasCommand();
			
			if(ctaFrecuentesRequestSession.getType().equalsIgnoreCase(CuentasFrecuentesRequestTO.TERCEROS)){
				response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, "TERCEROS"));
			}else if(ctaFrecuentesRequestSession.getType().equalsIgnoreCase(CuentasFrecuentesRequestTO.SPEI)){
				response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, "SPEI"));
			}else{
				response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, "INTERNACIONAL"));
			}
			
				
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
	
	private class BancosSpeiPredicate implements Predicate{
		private String banco;
		
		public boolean evaluate(Object obj){
			BancoSpeiTO banco_SpeiTO = (BancoSpeiTO)obj;
			if(banco_SpeiTO.getDescription().equalsIgnoreCase(banco)){
				return true;
			}else{
				return false;
			}
			
		}

		public String getBanco() {
			return banco;
		}

		public void setBanco(String banco) {
			this.banco = banco;
		}
	}
	
}
