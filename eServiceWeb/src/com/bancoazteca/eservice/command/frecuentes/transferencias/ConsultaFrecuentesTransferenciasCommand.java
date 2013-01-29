package com.bancoazteca.eservice.command.frecuentes.transferencias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class ConsultaFrecuentesTransferenciasCommand extends CommandBase{

private static final Logger log = Logger.getLogger(ConsultaFrecuentesTransferenciasCommand.class);
	
	private final String ESTADO_DESBLOQUEADA="0";


	public Response ejecucion(Session session) throws Exception{
		log.info("CuentasFrecuentesCommand");
		
		ConsultaFrecuentesTransferenciasForm cuentasFrecuentesForm = (ConsultaFrecuentesTransferenciasForm)getFormBean();
		Response response = new Response();	
		
		try{
		
			response.addAttribute(listaFrecuentes(session, cuentasFrecuentesForm.getTipo_transferencia()));


		}catch (EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {				
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}	
		
		return response;
	}
	
	public Cuentas_FrecuentesTO listaFrecuentes(Session session, String tipoFrecuente)throws EliteDataException, ServiceLocatorException, EJBException, CuentasException, SessionExpiredException
	{
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
		 
		Collection<Cuenta_FrecuenteTO> cuentasFrecuentesCollection;
		if(tipoFrecuente.equalsIgnoreCase("TERCEROS")){
			cuentasFrecuentesCollection=lista(session,CuentasFrecuentesRequestTO.TERCEROS,"TERCEROS");
		}else if(tipoFrecuente.equalsIgnoreCase("SPEI")){
			cuentasFrecuentesCollection=lista(session,CuentasFrecuentesRequestTO.SPEI,"SPEI");
		}else if(tipoFrecuente.equalsIgnoreCase("TEF")){
			cuentasFrecuentesCollection=lista(session,CuentasFrecuentesRequestTO.TEF,"TEF");
		}else if(tipoFrecuente.equalsIgnoreCase("INTERNACIONAL")){
			cuentasFrecuentesCollection=lista(session,CuentasFrecuentesRequestTO.INTERNACIONAL,"INTERNACIONAL");
		}else{
			cuentasFrecuentesCollection=lista(session,CuentasFrecuentesRequestTO.INTERNACIONAL,"INTERNACIONAL");
			cuentasFrecuentesCollection.addAll(lista(session,CuentasFrecuentesRequestTO.SPEI,"SPEI"));
			cuentasFrecuentesCollection.addAll(lista(session,CuentasFrecuentesRequestTO.TERCEROS,"TERCEROS"));
		}
		Cuentas_FrecuentesTO cuentasFrecuentesTOEservice = new Cuentas_FrecuentesTO();
		cuentasFrecuentesTOEservice.setColeccion_cuentas_frecuentes(cuentasFrecuentesCollection);
		
		return cuentasFrecuentesTOEservice;
	}
	
	private Collection<Cuenta_FrecuenteTO> lista(Session session,String tipo,String tipo_en_form) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException, EliteDataException
	{
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
		
		cuentasFrecuentesRequestTO.setType(tipo);
		
		ResourceFacadeSL facadeSL = getDelegate();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = facadeSL.getCuentasFrecuentes(cuentasFrecuentesRequestTO);
		
		Collection<Cuenta_FrecuenteTO> cuentasFrecuentesCollection = new ArrayList<Cuenta_FrecuenteTO>();
		Cuenta_FrecuenteTO cuentaFrecuenteTO = null;
		Collection<CuentasFrecuentesTO> cuentasFrecuentes =  cuentasFrecuentesResponseTO.getCuentasFrecuentes();
		Iterator<CuentasFrecuentesTO> iterator = cuentasFrecuentes.iterator();
		CuentasFrecuentesTO cuentasFrecuentesTO = null;
		log.info("tipo"+  tipo+"tipo en form"+ tipo_en_form);
		while(iterator.hasNext()){
			cuentasFrecuentesTO = (CuentasFrecuentesTO) iterator.next();
			if(!cuentasFrecuentesTO.getStatus().equals(CUENTA_ESTATUS_HISTORICO)){
				cuentaFrecuenteTO = new Cuenta_FrecuenteTO();
				if(cuentasFrecuentesTO.getTipoTransferenciaStr().equalsIgnoreCase("7")){
					cuentaFrecuenteTO.setBanco(cuentasFrecuentesTO.getClaveSwift());
				}else{
					cuentaFrecuenteTO.setBanco(cuentasFrecuentesTO.getNomBanco());
					cuentaFrecuenteTO.setAlias(cuentasFrecuentesTO.getApodo());
				}
				cuentaFrecuenteTO.setNombre_beneficiario(cuentasFrecuentesTO.getNombreDestino());
				cuentaFrecuenteTO.setNumero_cuenta(cuentasFrecuentesTO.getCuentaDestino());
				cuentaFrecuenteTO.setTipo_transferencia(tipo_en_form);
				cuentaFrecuenteTO.setTipo_numero_cuenta(cuentasFrecuentesTO.getTipoCuentaDestino());
				cuentaFrecuenteTO.setFecha_modificacion(cuentasFrecuentesTO.getFechaModificacionFormat());
				
				
				cuentaFrecuenteTO.setNumero_celular(cuentasFrecuentesTO.getNumCelularDestino());
				
				String companiaCelular = cuentasFrecuentesTO.getCarrier();
				cuentaFrecuenteTO.setCompania_telefonica(( companiaCelular != null && companiaCelular.trim().equals("-1"))?null:companiaCelular);
				
				String estado=null;
				switch(Integer.parseInt(cuentasFrecuentesTO.getStatus())){
					case 0:
					case 1: estado="desbloqueada";
						break;
					case 2:	estado="bloqueada";
						break;
				}
				cuentaFrecuenteTO.setEstado(estado.toUpperCase());
				
				
				cuentasFrecuentesCollection.add(cuentaFrecuenteTO);
			}
			
		}	
		return cuentasFrecuentesCollection;
	}
}

