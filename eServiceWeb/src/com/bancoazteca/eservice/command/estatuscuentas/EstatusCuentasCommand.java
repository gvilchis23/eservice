package com.bancoazteca.eservice.command.estatuscuentas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EstatusCuentaRequestTO;
import com.bancoazteca.elite.beans.EstatusCuentaResponseTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Estatus_CuentaTO;
import com.bancoazteca.eservice.command.base.beans.Estatus_CuentasTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Token_ValidacionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.command.validatoken.ValidaTokenCommand;
import com.bancoazteca.eservice.command.validatoken.ValidaTokenForm;

public class EstatusCuentasCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(EstatusCuentasCommand.class);
	
	public Response solicitud(Session session)throws Exception, XmlDecodeException{
		log.info("Método solicitud Estatus_cuentas");
		Response response = new Response();	
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		EstatusCuentasForm forma=(EstatusCuentasForm)getFormBean();
		
		if(forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
			ValidaTokenForm tokenForm = new ValidaTokenForm();
			tokenForm.setToken_code(forma.getClave_seguridad());
			tokenForm.setUser(clienteTO.getUserName());
			
			ValidaTokenCommand validaToken = new ValidaTokenCommand();
			validaToken.setFormBean(tokenForm);
			
			Response responseToken = new Response();
			responseToken = validaToken.ejecutar(session);
			
			Token_ValidacionTO tokenResponseTO = new Token_ValidacionTO(); 
			tokenResponseTO = (Token_ValidacionTO)responseToken.getDataService().iterator().next();
			try{
				if (!new Boolean(tokenResponseTO.getValido()).booleanValue()) {
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("Token", "Token es inválido");
					throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
				}
			}catch(EliteDataException e){
				buildErrorResponse(e, response);
				return response;
			}
			
		}
		ResourceFacadeSL bean = getDelegate();
		
		LoginResponseTO loginResponseTO = null;			
		LoginRequestTO loginRequestTO = new LoginRequestTO();
		EstatusCuentaResponseTO statusCuentaResponseTO = null;
		EstatusCuentaRequestTO statusCuentaRequestTO = new EstatusCuentaRequestTO();

		
		
		String userName = clienteTO.getUserName();
		loginRequestTO.setUser(userName);		
		
		loginResponseTO = bean.getCuentasUsuario(loginRequestTO);
		clienteTO = loginResponseTO.getClienteTO();
		clienteTO.setUserName(userName);
		
		statusCuentaRequestTO.setIdusuario(clienteTO.getNumero());
		statusCuentaRequestTO.setCuentas(clienteTO.getCuentas());
		statusCuentaResponseTO = bean.getCuentasUsuarioStatus(statusCuentaRequestTO);		
		
		Estatus_CuentasTO cuentasTO = new Estatus_CuentasTO();				
		Collection<Estatus_CuentaTO> estatus_cuentasCollectionTO = new ArrayList<Estatus_CuentaTO>();

		for(com.bancoazteca.elite.beans.EstatusCuentaTO cuenta: statusCuentaResponseTO.getEstatusCuentas()){
			Estatus_CuentaTO cuentaTO = new Estatus_CuentaTO();
			cuentaTO.setCuenta_cargo(cuenta.getCuenta_cargo());
			cuentaTO.setProducto(cuenta.getProducto());
			cuentaTO.setSaldo_total(cuenta.getSaldo_total());
			cuentaTO.setEstatus(cuenta.getEstatus());
			
			estatus_cuentasCollectionTO.add(cuentaTO);
		}		
		cuentasTO.setColeccion_estatus_cuentas(estatus_cuentasCollectionTO);		
		session.addAttribute(CommandConstantes.ESTATUS_CUENTAS_TO, statusCuentaResponseTO);
		response.addAttribute(cuentasTO);
		return response;
	}

	public Response validacion(Session session) throws Exception{
		Response response = new Response();		
		EstatusCuentasForm forma = (EstatusCuentasForm) getFormBean();
		EstatusCuentaResponseTO statusCuentaResponseTO = (EstatusCuentaResponseTO)session.getAttribute(ESTATUS_CUENTAS_TO);
 
		Estatus_CuentasTO cuentasTO = new Estatus_CuentasTO();
		String cuenta_cargo = forma.getCuenta_cargo();
			
		Collection<Estatus_CuentaTO> estatus_cuentasCollectionTO = new ArrayList<Estatus_CuentaTO>();
		Collection<com.bancoazteca.elite.beans.EstatusCuentaTO> estatusCuentasCollectionTO = new ArrayList<com.bancoazteca.elite.beans.EstatusCuentaTO>();

		for(com.bancoazteca.elite.beans.EstatusCuentaTO cuenta: statusCuentaResponseTO.getEstatusCuentas()){
			if (cuenta.getCuenta_cargo().equals(cuenta_cargo)) {
				Estatus_CuentaTO cuentaTO = new Estatus_CuentaTO();
				cuentaTO.setCuenta_cargo(cuenta.getCuenta_cargo());
				cuentaTO.setProducto(cuenta.getProducto());
				cuentaTO.setSaldo_total(cuenta.getSaldo_total());
				cuentaTO.setEstatus(cuenta.getEstatus());
				estatus_cuentasCollectionTO.add(cuentaTO);
				estatusCuentasCollectionTO.add(cuenta);
				break;
			}
		}
		
		if (estatus_cuentasCollectionTO.isEmpty()) {
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("numero de cuenta", "El número de cuenta enviado no corresponde a las cuentas del usuario.");
			buildErrorResponse(new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS), response);
		}
		
		cuentasTO.setColeccion_estatus_cuentas(estatus_cuentasCollectionTO);	
		statusCuentaResponseTO.setEstatusCuentas(estatusCuentasCollectionTO);
		HuellaTO huella=new HuellaTO();
//		huella.setLlave_publica(statusCuentaResponseTO.getDispositivoHuellaTO().getLlavePublica());
//		huella.setLongitud_huella(statusCuentaResponseTO.getDispositivoHuellaTO().getLongitudHuella());
		
		session.addAttribute(CommandConstantes.ESTATUS_CUENTAS_TO, statusCuentaResponseTO);
		response.addAttribute(cuentasTO);
		response.addAttribute(huella);
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception, XmlDecodeException{
		Response response = new Response();		
		Response responseToken = new Response();
		EstatusCuentasForm forma = (EstatusCuentasForm) getFormBean();			

		ResourceFacadeSL bean = getDelegate();		
		EstatusCuentaResponseTO statusCuentaResponseTO = (EstatusCuentaResponseTO)session.getAttribute(ESTATUS_CUENTAS_TO);
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		EstatusCuentaRequestTO statusCuentaRequestTO = new EstatusCuentaRequestTO();
		String cuenta_cargo = statusCuentaResponseTO.getEstatusCuentas().iterator().next().getCuenta_cargo();
		String estatus = statusCuentaResponseTO.getEstatusCuentas().iterator().next().getEstatus();
		
		Estatus_CuentasTO cuentasTO = new Estatus_CuentasTO();	
		Collection<Estatus_CuentaTO> estatus_cuentasCollectionTO = new ArrayList<Estatus_CuentaTO>();
		
		statusCuentaRequestTO.setIdusuario(clienteTO.getNumero());
		statusCuentaRequestTO.setNumero_cuenta(cuenta_cargo);
		statusCuentaRequestTO.setEstatus(estatus);
		statusCuentaRequestTO.setCuentas(clienteTO.getCuentas());
		
		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
			statusCuentaRequestTO.setOptionDispositive(OPCION_HUELLA);
			statusCuentaRequestTO.setClave(forma.getHuella_seguridad().toString());
		}
		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
			statusCuentaRequestTO.setOptionDispositive(OPCION_TOKEN);
			statusCuentaRequestTO.setClave(forma.getClave_seguridad().toString());
		}
		
		ValidaTokenForm tokenForm = new ValidaTokenForm();
		Token_ValidacionTO tokenResponseTO = new Token_ValidacionTO(); 
		tokenForm.setToken_code(forma.getClave_seguridad());
		tokenForm.setUser(clienteTO.getUserName());
		
		ValidaTokenCommand validaToken = new ValidaTokenCommand();
		validaToken.setFormBean(tokenForm);
		responseToken = validaToken.ejecutar(session);
		tokenResponseTO = (Token_ValidacionTO)(responseToken.getDataService().iterator().next());
		try {
			
			if (new Boolean(tokenResponseTO.getValido()).booleanValue()) {	
				statusCuentaResponseTO = bean.getCambiarEstatus(statusCuentaRequestTO);	
		
				for(com.bancoazteca.elite.beans.EstatusCuentaTO cuenta: statusCuentaResponseTO.getEstatusCuentas()){
					if (cuenta.getCuenta_cargo().equals(cuenta_cargo)) {
						Estatus_CuentaTO cuentaTO = new Estatus_CuentaTO();
						cuentaTO.setCuenta_cargo(cuenta.getCuenta_cargo());
						cuentaTO.setProducto(cuenta.getProducto());
						cuentaTO.setSaldo_total(cuenta.getSaldo_total());
						cuentaTO.setEstatus(cuenta.getEstatus());
						estatus_cuentasCollectionTO.add(cuentaTO);
						break;
					}
				}
		
				cuentasTO.setColeccion_estatus_cuentas(estatus_cuentasCollectionTO);		
				response.addAttribute(cuentasTO);
			}
			else {								
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Token", "Token es inválido");
				throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
		}catch(EliteDataException e)
		{
			buildErrorResponse(e, response);
		}
		session.addAttribute(CommandConstantes.ESTATUS_CUENTAS_TO, statusCuentaResponseTO);
		return response;
	}
}
