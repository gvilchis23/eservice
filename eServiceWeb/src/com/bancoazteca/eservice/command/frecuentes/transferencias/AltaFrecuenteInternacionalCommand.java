package com.bancoazteca.eservice.command.frecuentes.transferencias;

import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class AltaFrecuenteInternacionalCommand extends CommandBase{

private static final Logger log = Logger.getLogger(AltaFrecuenteInternacionalCommand.class);
	
	public Response validacion(Session session) throws Exception{
		log.info("AltaFrecuenteInternacionalCommand.validacion");
		
		Response response = new Response();
		AltaFrecuenteInternacionalForm altaFrecuenteInternacionalForm = (AltaFrecuenteInternacionalForm)getFormBean();
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		DispositivoHuellaTO tdispositivoHuellaTO;
		HuellaTO huellaTO = new HuellaTO();
		try{
			cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
			cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.INTERNACIONAL);
			cuentasFrecuentesRequestTO.setBeneficiario(altaFrecuenteInternacionalForm.getNombre_beneficiario());
			cuentasFrecuentesRequestTO.setCuenta(altaFrecuenteInternacionalForm.getNumero_cuenta());
			cuentasFrecuentesRequestTO.setClaveBanco(altaFrecuenteInternacionalForm.getCodigo_swift_aba());
		
			facadeSL.setOtrosBancosPreparacionAgregarCuenta(cuentasFrecuentesRequestTO);
			
			tdispositivoHuellaTO=facadeSL.setIntenationalesDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());
			
		}catch(EliteDataException e){
				super.buildErrorResponse(e, response);
		}
		
		session.addAttribute("AltaFrecuenteInternacionalRequest", cuentasFrecuentesRequestTO);
		response.addAttribute(huellaTO);
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception{
		log.info("AltaFrecuenteInternacionalCommand.ejecucion");
		
		Response response = new Response();
		AltaFrecuenteInternacionalForm altaFrecuenteInternacionalForm = (AltaFrecuenteInternacionalForm)getFormBean();
		CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
		ResourceFacadeSL facadeSL = getDelegate();
		
		try{
			
			CuentasFrecuentesRequestTO ctaFrecuentesRequestSession = (CuentasFrecuentesRequestTO) session.getAttribute("AltaFrecuenteInternacionalRequest");
			
			cuentasFrecuentesRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
			cuentasFrecuentesRequestTO.setCuenta(ctaFrecuentesRequestSession.getCuenta());
			cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.INTERNACIONAL);
			
			if (altaFrecuenteInternacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				cuentasFrecuentesRequestTO.setTokencode(altaFrecuenteInternacionalForm.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
			}else if (altaFrecuenteInternacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				cuentasFrecuentesRequestTO.setClave(altaFrecuenteInternacionalForm.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
			}
			
			facadeSL.setOtrosBancosAgregarCuenta(cuentasFrecuentesRequestTO);
			log.info(":: Ejecuto el alta");
			
			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferenciasCommand = new ConsultaFrecuentesTransferenciasCommand();
			response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, "INTERNACIONAL"));
				
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
}
