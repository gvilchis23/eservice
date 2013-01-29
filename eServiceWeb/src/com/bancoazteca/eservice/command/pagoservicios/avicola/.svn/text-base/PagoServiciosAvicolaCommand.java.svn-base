package com.bancoazteca.eservice.command.pagoservicios.avicola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosAvicolaRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosAvicolaResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Pago_Servicio_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.ServiciosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class PagoServiciosAvicolaCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(PagoServiciosAvicolaCommand.class);
	
	public Response solicitud(Session session)throws Exception{
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Response response = new Response();
		
		ResourceFacadeSL facadeSL = getDelegate();
		PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO =  new PagoServiciosAvicolaRequestTO();
		pagoServiciosAvicolaRequestTO.setUser(clienteTO.getUserName());
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = facadeSL.setInitialAvicolaPayment(pagoServiciosAvicolaRequestTO);
		
		Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
		
		Map<String, String> cuentas = pagoServiciosAvicolaResponseTO.getMapCuentas();
		
		for(String key : cuentas.keySet()){
			Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
			CuentaTO cuentaTO = (CuentaTO) super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0,14));
			cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
			cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
			cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
			cuentaCollectionTO.add(cuentaCargoTO);
		}
		
		Map<String, String> servicios = pagoServiciosAvicolaResponseTO.getMapServicios();
		Collection <ServiciosTO> collectionServiciosTO = new ArrayList<ServiciosTO>();
		for(String key : servicios.keySet()){
			ServiciosTO serviciosTO = new ServiciosTO();
			serviciosTO.setConcepto_pago(key);
			collectionServiciosTO.add(serviciosTO);
		}
		
		Pago_ServicioTO solicitudPagoServicioTO = new Pago_ServicioTO();		
		solicitudPagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
		solicitudPagoServicioTO.setColeccion_servicios(collectionServiciosTO);		
			
		session.addAttribute(PAGO_SERVICIO_AVICOLA_RESPONSE, pagoServiciosAvicolaResponseTO);
		
		response.addAttribute(solicitudPagoServicioTO);
		
		return response;
	}

	
	public Response validacion(Session session)throws Exception{
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = (PagoServiciosAvicolaResponseTO)session.getAttribute(PAGO_SERVICIO_AVICOLA_RESPONSE);
		PagoServiciosAvicolaForm AvicolaForm = (PagoServiciosAvicolaForm)getFormBean();
		Response response = new Response();
		
		PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO =  new PagoServiciosAvicolaRequestTO();
		pagoServiciosAvicolaRequestTO.setCuentaReferencia(AvicolaForm.getNumero_referencia());
		pagoServiciosAvicolaRequestTO.setDigitoVerificador("0");
		pagoServiciosAvicolaRequestTO.setCuentaCargo(AvicolaForm.getCuenta_cargo());
		pagoServiciosAvicolaRequestTO.setTipoServicio(AvicolaForm.getConcepto_pago());
		pagoServiciosAvicolaRequestTO.setImporte(AvicolaForm.getImporte());
		pagoServiciosAvicolaRequestTO.setUser(clienteTO.getUserName());
		try{
			ResourceFacadeSL facadeSL = getDelegate();
			pagoServiciosAvicolaResponseTO = facadeSL.setDataAvicolaPayment(pagoServiciosAvicolaRequestTO);
			pagoServiciosAvicolaResponseTO.setTipoServicio(pagoServiciosAvicolaRequestTO.getTipoServicio());
			
			DispositivoHuellaTO dispositivoHuellaTO = pagoServiciosAvicolaResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			
			session.addAttribute(PAGO_SERVICIO_AVICOLA_RESPONSE, pagoServiciosAvicolaResponseTO);
			
			response.addAttribute(huellaTO);
			
		} catch (EliteDataException e) {					
			buildErrorResponse(e, response);
		}		
		
		return response;
	}
	
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		PagoServiciosAvicolaForm AvicolaForm = (PagoServiciosAvicolaForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = (PagoServiciosAvicolaResponseTO)session.getAttribute(PAGO_SERVICIO_AVICOLA_RESPONSE);		
		PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO = new PagoServiciosAvicolaRequestTO();
		
		pagoServiciosAvicolaRequestTO.setCuentaReferencia(pagoServiciosAvicolaResponseTO.getCuentaReferencia());
		
		pagoServiciosAvicolaRequestTO.setCuentaCargo(Formatter.removeSpaces(pagoServiciosAvicolaResponseTO.getCuentaCargo()));
		pagoServiciosAvicolaRequestTO.setTipoServicio(pagoServiciosAvicolaResponseTO.getTipoServicio());
		pagoServiciosAvicolaRequestTO.setFechaAplicacion(pagoServiciosAvicolaResponseTO.getFechaAplicacion());
		pagoServiciosAvicolaRequestTO.setImporte(String.valueOf(pagoServiciosAvicolaResponseTO.getImporte()));
		pagoServiciosAvicolaRequestTO.setTotal(pagoServiciosAvicolaResponseTO.getTotal().toString());
		pagoServiciosAvicolaRequestTO.setUser(clienteTO.getUserName());
		pagoServiciosAvicolaRequestTO.setDigitoVerificador("0");
		

		if(AvicolaForm.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_TOKEN)){
			
			pagoServiciosAvicolaRequestTO.setOptionDispositive(CommandBase.OPCION_TOKEN);
			pagoServiciosAvicolaRequestTO.setTokenCode(AvicolaForm.getClave_seguridad().toString());
			
		}
		else if(AvicolaForm.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_HUELLA)){
			
			pagoServiciosAvicolaRequestTO.setOptionDispositive(CommandBase.OPCION_HUELLA);
			pagoServiciosAvicolaRequestTO.setClave(AvicolaForm.getHuella_seguridad().toString());
			
		}
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate(); 
			pagoServiciosAvicolaResponseTO = resourceFacadeSL.setConfirmAvicolaPayment(pagoServiciosAvicolaRequestTO);
		
			Pago_Servicio_EjecucionTO pagoServicioAvicolaTO = new Pago_Servicio_EjecucionTO();
		
			pagoServicioAvicolaTO.setComision(String.valueOf(pagoServiciosAvicolaResponseTO.getComision()));
			pagoServicioAvicolaTO.setConcepto_pago(pagoServiciosAvicolaRequestTO.getTipoServicio());
			pagoServicioAvicolaTO.setCuenta_cargo(pagoServiciosAvicolaResponseTO.getCuentaCargo().substring(0, 14));
			pagoServicioAvicolaTO.setImporte(String.valueOf(pagoServiciosAvicolaResponseTO.getImporte()));
			pagoServicioAvicolaTO.setIva(String.valueOf(pagoServiciosAvicolaResponseTO.getIva()));
			pagoServicioAvicolaTO.setNumero_referencia(pagoServiciosAvicolaResponseTO.getCuentaReferencia());
			pagoServicioAvicolaTO.setTotal_pago(String.valueOf(pagoServiciosAvicolaResponseTO.getTotal()));
			pagoServicioAvicolaTO.setFolio_aclaraciones(pagoServiciosAvicolaResponseTO.getFolioAclaracion());
			
				
				
			super.updateBalance(session);
			
			response.addAttribute(pagoServicioAvicolaTO);
		
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
}
