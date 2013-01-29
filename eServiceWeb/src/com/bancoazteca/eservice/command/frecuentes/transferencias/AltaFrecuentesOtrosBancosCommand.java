package com.bancoazteca.eservice.command.frecuentes.transferencias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CodeTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class AltaFrecuentesOtrosBancosCommand extends CommandBase{
	private static final Logger log = Logger.getLogger(AltaFrecuentesOtrosBancosCommand.class);

	
	
	public Response validacion(Session session) throws Exception{
		log.info("AltaFrecuentesOtrosBancos validacion");
		Response response = new Response();
		try{			
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			AltaFrecuentesOtrosBancosForm forma = (AltaFrecuentesOtrosBancosForm)getFormBean();
			ResourceFacadeSL facadeSL = getDelegate();
			
			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferencias = new ConsultaFrecuentesTransferenciasCommand();
			String tipoFrecuente=forma.getTipo_frecuente();
			
			if(validaTipoFrecuente(tipoFrecuente)){
				tipoFrecuente=CuentasFrecuentesRequestTO.SPEI;
			}
			
			consultaFrecuentesTransferencias.listaFrecuentes(session, tipoFrecuente);
			
//			TransferenciaSpeiCommand transferneciaSpei = new TransferenciaSpeiCommand();
//			transferneciaSpei.solicitud(session);
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO();
			CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
//			cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.SPEI);
			cuentasFrecuentesRequestTO.setType(tipoFrecuente);
			cuentasFrecuentesRequestTO.setUser(clienteTO.getUserName());
			cuentasFrecuentesResponseTO=facadeSL.setOtrosBancosPreparacionAgregarCuenta(cuentasFrecuentesRequestTO);
			Collection<CodeTO> bancos= cuentasFrecuentesResponseTO.getBancos();
			String indiceBanco = getIndiceBanco(bancos,forma.getBanco().toUpperCase());
			if (indiceBanco.equals("")){
					Throwable e= new Throwable();
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("bancosSpei ", "Por favor ingrese un Banco valido");
					Object errorData = map;
					int level=1;
					throw  new EliteDataException(e,errorData,level);
			}
			cuentasFrecuentesRequestTO.setAlias(forma.getAlias());
		    cuentasFrecuentesRequestTO.setBeneficiario(forma.getBeneficiario());
			cuentasFrecuentesRequestTO.setCuenta(forma.getNumero_cuenta());
			cuentasFrecuentesRequestTO.setBanco(indiceBanco);
			cuentasFrecuentesRequestTO.setEmail(forma.getCorreo_electronico());
			String companiaCelular = forma.getCompania_telefonica();
			if(!Validator.isEmptyData(companiaCelular)){
				cuentasFrecuentesRequestTO.setCompania(companiaCelular);
			}else {
				cuentasFrecuentesRequestTO.setCompania("-1");
			}
			cuentasFrecuentesRequestTO.setCelular(forma.getNumero_celular());
			cuentasFrecuentesRequestTO.setTelCasa(forma.getNumero_casa());
			cuentasFrecuentesRequestTO.setTelOficina(forma.getNumero_oficina());
			DispositivoHuellaTO tdispositivoHuellaTO=facadeSL.setOtrosBancosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			session.addAttribute("AltaFrecuenteSpeiRequest", cuentasFrecuentesRequestTO);
			session.addAttribute("tipoFrecuente", tipoFrecuente);
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());
			response.addAttribute(huellaTO);


		}catch(EliteDataException e){
			super.buildErrorResponse(e, response);			

		}

		return response;
	}



	public Response ejecucion(Session session) throws Exception{
		log.info("AltaFrecuentesOtrosBancos ejecucion");
		Response response = new Response();

		String tipoFrecuente=(String)session.getAttribute("tipoFrecuente");
		
		try {

			CuentasFrecuentesRequestTO ctaFrecuentesRequestSession = (CuentasFrecuentesRequestTO) session.getAttribute("AltaFrecuenteSpeiRequest");
			AltaFrecuentesOtrosBancosForm forma = (AltaFrecuentesOtrosBancosForm)getFormBean();
			ResourceFacadeSL bean = getDelegate();
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO(); 
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			cuentasFrecuentesRequestTO.setUser(clienteTO.getUserName());
			cuentasFrecuentesRequestTO.setCuenta(ctaFrecuentesRequestSession.getCuenta());
           
			if(forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN) ){
				cuentasFrecuentesRequestTO.setTokencode(forma.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
				}
			else if(forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
                log.info(forma.getHuella_seguridad().toString());
				cuentasFrecuentesRequestTO.setClave(forma.getHuella_seguridad().toString());


			}

			cuentasFrecuentesRequestTO.setType(tipoFrecuente);
			cuentasFrecuentesRequestTO.setCuenta(ctaFrecuentesRequestSession.getCuenta());
			cuentasFrecuentesRequestTO.setUser(clienteTO.getUserName());
			bean.setOtrosBancosAgregarCuenta(cuentasFrecuentesRequestTO);

			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferenciasCommand = new ConsultaFrecuentesTransferenciasCommand();
			response.addAttribute(consultaFrecuentesTransferenciasCommand.listaFrecuentes(session, tipoFrecuente));

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	private boolean validaTipoFrecuente(String tipoFrecuente) {
		if(tipoFrecuente==null){
			return true;
		}
		String tmp=tipoFrecuente.trim();
		if(tmp.equalsIgnoreCase("")){
			return true;
		}
		
		return false;
	}



	public String getIndiceBanco(Collection<CodeTO> coleccionBancos, String banco){
		String indice= "";
		Iterator<CodeTO> bancos = coleccionBancos.iterator();
		while (bancos.hasNext()){
			CodeTO bancoSpeiTO = bancos.next();
			if (bancoSpeiTO.getDescripcion().equals(banco)){
				indice= bancoSpeiTO.getIndex();
				break;
			}
		}
		return indice;
	}
	

}
