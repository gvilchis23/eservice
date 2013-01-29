package com.bancoazteca.eservice.command.chequera.presolicitud;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.PresolicitudChequesRequestTO;
import com.bancoazteca.elite.beans.PresolicitudChequesResponseTO;
import com.bancoazteca.elite.beans.RegistroFirmasVO;
import com.bancoazteca.elite.beans.TalonarioVO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Presolicitud_ChequesTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_ChequeTO;
import com.bancoazteca.eservice.command.base.beans.Presolicitud_cheques_ejecucionTO;
import com.bancoazteca.eservice.command.base.beans.Presolicitud_cheques_validacionTO;
import com.bancoazteca.eservice.command.base.beans.Tipo_ChequeTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PresolicitudChequeraCommand extends CommandBase {
	
	public Response solicitud(Session session)throws Exception{
		PresolicitudChequeraForm form=(PresolicitudChequeraForm)getFormBean();
		Response response=new Response();
		try {
			Class<?> clazz=this.getClass();
			Class<?>[] types=new Class[]{Session.class};
			Method metodo=clazz.getMethod(form.getTipo_solicitud().toLowerCase(), types);
			Object[] args = new Object[]{session};
			response=(Response)metodo.invoke(this, args);
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof SessionExpiredException){
				throw new SessionExpiredException(e);
			}else{
				throw e;				
			}
			
		}
		return response;
	}
	
	public Response solicitud_cuentas(Session session)throws Exception{
		System.out.println("entro a chequera");
		Response response=new Response();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PresolicitudChequesRequestTO requestTO=new PresolicitudChequesRequestTO();
			requestTO.setUsuario(clienteTO.getAlias());
			PresolicitudChequesResponseTO responseTO=getDelegateSegundo().presolicitudChequeraInicio(requestTO);
			Presolicitud_ChequesTO presolicitud_chequesTO = llenaTO(responseTO);
			response.addAttribute(presolicitud_chequesTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
		
	
	public Response solicitud_detalle_cuenta(Session session)throws Exception{
		Response response=new Response();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PresolicitudChequesRequestTO requestTO=new PresolicitudChequesRequestTO();
			PresolicitudChequeraForm form=(PresolicitudChequeraForm)getFormBean();
			requestTO.setUsuario(clienteTO.getAlias());
			requestTO.setCuenta(form.getNumero_cuenta());
			PresolicitudChequesResponseTO responseTO=getDelegateSegundo().presolicitudChequeraInicioDetalleCuenta(requestTO);
			Presolicitud_ChequesTO presolicitud_chequesTO = llenaTO(responseTO);
			
			ArrayList<TalonarioVO> tiposChequeras=responseTO.getTiposChequeras();
			ArrayList<Tipo_ChequeTO> coleccion_tipos_cheques=new ArrayList<Tipo_ChequeTO>();
			for (TalonarioVO talonarioVO : tiposChequeras) {
				Tipo_ChequeTO tipo_chequeTO=new Tipo_ChequeTO();
				tipo_chequeTO.setCheque_final(talonarioVO.getChequeFinal());
				tipo_chequeTO.setCheque_inicial(talonarioVO.getChequeInicial());
				tipo_chequeTO.setComision(talonarioVO.getComision());
				tipo_chequeTO.setNumero_cheques(talonarioVO.getNumeroCheques());
				tipo_chequeTO.setTipo_cheque(talonarioVO.getCodigoImpresion());
				tipo_chequeTO.setDescripcion_chequera(talonarioVO.getDescripcionChequera());
				coleccion_tipos_cheques.add(tipo_chequeTO);
			}
			presolicitud_chequesTO.setColeccion_tipos_cheques(coleccion_tipos_cheques);
			presolicitud_chequesTO.setRenta_mensual(responseTO.getComision());
			response.addAttribute(presolicitud_chequesTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	

	
	public Response validacion(Session session)throws Exception{
		Response response=new Response();
		PresolicitudChequesRequestTO requestTO=new PresolicitudChequesRequestTO();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		HuellaTO huellaTO=new HuellaTO();
		try {
			
			PresolicitudChequeraForm form=(PresolicitudChequeraForm)getFormBean();
			requestTO.setCuenta(form.getNumero_cuenta());
			requestTO.setRegimen(form.getRegimen());
			requestTO.setTipoChequera(form.getTipo_chequera());
			requestTO.setProteccion(form.getProteccion_chequera());
			requestTO.setMontoPiso(form.getMonto_proteccion());
			requestTO.setUsuario(clienteTO.getAlias());
			
			PresolicitudChequesResponseTO responseTO=getDelegateSegundo().presolicitudChequeraValidacion(requestTO);
			RegistroFirmasVO registroFirmasVO=responseTO.getRegistroFirmasVO();
			Presolicitud_cheques_validacionTO presolicitud_cheques_validacionTO=new Presolicitud_cheques_validacionTO();
			
			presolicitud_cheques_validacionTO.setAutorizacion_firma_1(registroFirmasVO.getAutorizado1());
			presolicitud_cheques_validacionTO.setAutorizacion_firma_2(registroFirmasVO.getAutorizado2());
			presolicitud_cheques_validacionTO.setCosto_cheque_liberado_protegido(registroFirmasVO.getCostoChequeLiberado());
			presolicitud_cheques_validacionTO.setMonto_minimo_proteccion(registroFirmasVO.getMontoPiso());
			presolicitud_cheques_validacionTO.setNumero_cheques(registroFirmasVO.getNumeroCheques());
			presolicitud_cheques_validacionTO.setNumero_cuenta(registroFirmasVO.getNumeroCuenta());
			presolicitud_cheques_validacionTO.setProteccion_chequera(registroFirmasVO.getProteccion());
			presolicitud_cheques_validacionTO.setRenta_mensual(registroFirmasVO.getComision());
			presolicitud_cheques_validacionTO.setTipo_chequera(registroFirmasVO.getTipoChequera());
			presolicitud_cheques_validacionTO.setTipo_cuenta(registroFirmasVO.getTipoCuenta());
			presolicitud_cheques_validacionTO.setTipo_regimen(registroFirmasVO.getRegimen());
			presolicitud_cheques_validacionTO.setTitular_cuenta(registroFirmasVO.getTitular());
			
			huellaTO.setLlave_publica(responseTO.getDispositivoHuellaTO().getLlavePublica());
			huellaTO.setLongitud_huella(responseTO.getDispositivoHuellaTO().getLongitudHuella());
			
			response.addAttribute(presolicitud_cheques_validacionTO);
			response.addAttribute(huellaTO);
			
		}  catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		PresolicitudChequesRequestTO requestTO=new PresolicitudChequesRequestTO();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		PresolicitudChequeraForm forma=(PresolicitudChequeraForm)getFormBean();
			try{
				requestTO.setUsuario(clienteTO.getAlias());
				forma=(PresolicitudChequeraForm)getFormBean();
				System.out.println("Seteando dispositivo de seguridad");
				if (forma.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ){
					requestTO.setClave( forma.getHuella_seguridad().toString() );
					requestTO.setOptionDispositive( OPCION_HUELLA );
				}
				else if (forma.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ){
					requestTO.setTokenCode( forma.getClave_seguridad().toString() );
					requestTO.setOptionDispositive( OPCION_TOKEN );
				}
			
			
			
				PresolicitudChequesResponseTO responseTO=getDelegateSegundo().presolicitudChequeraEjecucion(requestTO);
				RegistroFirmasVO registroFirmasVO=responseTO.getRegistroFirmasVO();
				Presolicitud_cheques_ejecucionTO presolicitud_cheques_ejecucionTO=new Presolicitud_cheques_ejecucionTO();
				
				presolicitud_cheques_ejecucionTO.setAutorizacion_firma_1(registroFirmasVO.getAutorizado1());
				presolicitud_cheques_ejecucionTO.setAutorizacion_firma_2(registroFirmasVO.getAutorizado2());
				presolicitud_cheques_ejecucionTO.setCosto_cheque_liberado_protegido(registroFirmasVO.getCostoChequeLiberado());
				presolicitud_cheques_ejecucionTO.setMonto_minimo_proteccion(registroFirmasVO.getMontoPiso());
				presolicitud_cheques_ejecucionTO.setNumero_cheques(registroFirmasVO.getNumeroCheques());
				presolicitud_cheques_ejecucionTO.setNumero_cuenta(registroFirmasVO.getNumeroCuenta());
				presolicitud_cheques_ejecucionTO.setProteccion_chequera(registroFirmasVO.getProteccion());
				presolicitud_cheques_ejecucionTO.setRenta_mensual(registroFirmasVO.getComision());
				presolicitud_cheques_ejecucionTO.setTipo_chequera(registroFirmasVO.getTipoChequera());
				presolicitud_cheques_ejecucionTO.setTipo_cuenta(registroFirmasVO.getTipoCuenta());
				presolicitud_cheques_ejecucionTO.setTipo_regimen(registroFirmasVO.getRegimen());
				presolicitud_cheques_ejecucionTO.setTitular_cuenta(registroFirmasVO.getTitular());
				presolicitud_cheques_ejecucionTO.setFolio_presolicitud(registroFirmasVO.getFolioPreSolicitud());
			
			response.addAttribute(presolicitud_cheques_ejecucionTO);
		
		}  catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	private Presolicitud_ChequesTO llenaTO(PresolicitudChequesResponseTO responseTO) throws EliteDataException {
		ArrayList<Cuenta_ChequeTO> coleccion_presolicitud_cheques=new ArrayList<Cuenta_ChequeTO>();
		ArrayList<String> coleccion_tipo_regimen =new ArrayList<String>();
		ArrayList<CuentaVO> cuentas = responseTO.getCuentas();
		Presolicitud_ChequesTO presolicitud_chequesTO=new Presolicitud_ChequesTO();
		
		if( cuentas != null ){
			for (CuentaVO cuentaVO : cuentas) {
				Cuenta_ChequeTO cuenta_chequeTO=new Cuenta_ChequeTO();
				cuenta_chequeTO.setChequera(cuentaVO.getChequera());
				cuenta_chequeTO.setNumero_cuenta(cuentaVO.getNumero());
				cuenta_chequeTO.setClabe(cuentaVO.getClabe());
				cuenta_chequeTO.setProducto(cuentaVO.getDescripcion());
				cuenta_chequeTO.setSaldo_disponible(cuentaVO.getDisponible());
				cuenta_chequeTO.setSaldo_retenido(cuentaVO.getRetenido());
				coleccion_presolicitud_cheques.add(cuenta_chequeTO);
			}			
			
			presolicitud_chequesTO.setColeccion_presolicitud_cheques(coleccion_presolicitud_cheques);
			coleccion_tipo_regimen.add("Individual");
			coleccion_tipo_regimen.add("Indistinto");
			presolicitud_chequesTO.setColeccion_tipo_regimen(coleccion_tipo_regimen);
		}else{
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("numero_cuenta", "Lo sentimos, los productos de sus cuentas no cuentan con chequeras.");
			throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
		}
		
		
		return presolicitud_chequesTO;
	}
	
}
