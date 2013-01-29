package com.bancoazteca.eservice.command.frecuentes.traspasos;


import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuentas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.frecuentes.transferencias.ConsultaFrecuentesTransferenciasCommand;
import com.bancoazteca.eservice.command.response.Response;

public class AltaFrecuenteTraspasosTercerosCommand extends CommandBase {
	
	private static final String TERCEROS = "terceros";
	
	public Response validacion(Session session)throws Exception  {
		Response response = new Response();
		AltaFrecuenteTraspasosTercerosForm forma = (AltaFrecuenteTraspasosTercerosForm)getFormBean();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		HuellaTO huellaTO = new HuellaTO();
		DispositivoHuellaTO tdispositivoHuellaTO;
		
		try {
			
			ConsultaFrecuentesTransferenciasCommand consultaFrecuentesTransferencias = new ConsultaFrecuentesTransferenciasCommand();
			consultaFrecuentesTransferencias.listaFrecuentes(session, "TERCEROS");
			
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = new CuentasFrecuentesRequestTO(); 
			cuentasFrecuentesRequestTO.setUser(clienteTO.getUserName());
			cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.TERCEROS);
			cuentasFrecuentesRequestTO.setAlias(forma.getAlias_beneficiario());
			cuentasFrecuentesRequestTO.setCuenta(forma.getNumero_cuenta());			
			String companiaCelular = forma.getCompania_celular();
			if(!Validator.isEmptyData(companiaCelular)){
				cuentasFrecuentesRequestTO.setCompania(companiaCelular);
			}else {
				cuentasFrecuentesRequestTO.setCompania("-1");
			}
			cuentasFrecuentesRequestTO.setCelular(forma.getNumero_celular());
			cuentasFrecuentesRequestTO.setTelOficina(forma.getNumero_oficina());
			cuentasFrecuentesRequestTO.setTelCasa(forma.getNumero_casa());
			
			
			tdispositivoHuellaTO = resourceFacadeSL.setTercerosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());
			
			synchronized(session){
				session.addAttribute(FRECUENTES_TRASPASOS_TERCEROS_RESPONSE, cuentasFrecuentesRequestTO);
			}
			
			response.addAttribute(huellaTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();			
		try {
			AltaFrecuenteTraspasosTercerosForm forma = (AltaFrecuenteTraspasosTercerosForm)getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO = (CuentasFrecuentesRequestTO)session.getAttribute(FRECUENTES_TRASPASOS_TERCEROS_RESPONSE); 
			Cuentas_FrecuentesTO cuentasFrecuentesTO = new Cuentas_FrecuentesTO();
			ConsultaFrecuentesTransferenciasCommand consultaFrecuntes = new ConsultaFrecuentesTransferenciasCommand();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			cuentasFrecuentesRequestTO.setUser(clienteTO.getUserName());
			
//			cuentasFrecuentesRequestTO.setTokencode(forma.getClave_seguridad().toString());					
		    
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				cuentasFrecuentesRequestTO.setTokencode(forma.getClave_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				cuentasFrecuentesRequestTO.setClave(forma.getHuella_seguridad().toString());
				cuentasFrecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
			}
			
			cuentasFrecuentesRequestTO.setType(CuentasFrecuentesRequestTO.TERCEROS);			
			resourceFacadeSL.setTercerosConfirmarAltaFrecuente(cuentasFrecuentesRequestTO);
			cuentasFrecuentesTO = consultaFrecuntes.listaFrecuentes(session,TERCEROS);
			response.addAttribute(cuentasFrecuentesTO);
								
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
}
