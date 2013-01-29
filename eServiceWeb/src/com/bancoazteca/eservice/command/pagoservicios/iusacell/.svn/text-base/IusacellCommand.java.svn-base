package com.bancoazteca.eservice.command.pagoservicios.iusacell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosIusacellRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosIusacellResponseTO;
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


public class IusacellCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(IusacellCommand.class);
	
	public Response solicitud(Session session)throws Exception{
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Response response = new Response();
		
		try{
			ResourceFacadeSL facadeSL = getDelegate();
			PagoServiciosIusacellRequestTO pagoServiciosIusacellRequestTO =  new PagoServiciosIusacellRequestTO();
			pagoServiciosIusacellRequestTO.setUser(clienteTO.getUserName());
			PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = facadeSL.setInitialIusacellPayment(pagoServiciosIusacellRequestTO);
			
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			
			Map<String, String> cuentas = pagoServiciosIusacellResponseTO.getMapCuentas();
			
			for(String key : cuentas.keySet()){
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				
				CuentaTO cuentaTO = (CuentaTO) super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0,14));
				
				cuentaCargoTO.setNumero_cuenta(cuentaTO.getCuentaFormateada().replace(" ", ""));			
				cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
				cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
				cuentaCollectionTO.add(cuentaCargoTO);
			}
			
			Map<String, String> servicios = pagoServiciosIusacellResponseTO.getMapServicios();
			Collection <ServiciosTO> collectionServiciosTO = new ArrayList<ServiciosTO>();
			for(String key : servicios.keySet()){
				ServiciosTO serviciosTO = new ServiciosTO();
				serviciosTO.setConcepto_pago(key);
				collectionServiciosTO.add(serviciosTO);
			}
			
			Pago_ServicioTO solicitudPagoServicioTO = new Pago_ServicioTO();		
			solicitudPagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
			solicitudPagoServicioTO.setColeccion_servicios(collectionServiciosTO);		
				
			session.addAttribute(PAGO_SERVICIO_IUSACELL_SOLICITUD_RESPONSE, pagoServiciosIusacellResponseTO);
			
			response.addAttribute(solicitudPagoServicioTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		
		return response;
	}
	
	public Response validacion(Session session)throws Exception{
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = (PagoServiciosIusacellResponseTO)session.getAttribute(PAGO_SERVICIO_IUSACELL_SOLICITUD_RESPONSE);
		IusacellForm iusacellForm = (IusacellForm)getFormBean();
		Response response = new Response();
		
		Map<String, String> cuentas = pagoServiciosIusacellResponseTO.getMapCuentas();
		String cuentaCargoCompleta = "";
		for(String key : cuentas.keySet()){
			if(key.startsWith(iusacellForm.getCuenta_cargo())){
				cuentaCargoCompleta = key; 
				break;
			}
		}
		
		String cuentaReferencia = iusacellForm.getNumero_referencia();
		while(cuentaReferencia.length()<17){
			cuentaReferencia = "0" + cuentaReferencia; 
		}
		
		PagoServiciosIusacellRequestTO pagoServiciosIusacellRequestTO =  new PagoServiciosIusacellRequestTO();
		pagoServiciosIusacellRequestTO.setCuentaReferencia(cuentaReferencia);
		pagoServiciosIusacellRequestTO.setDigitoVerificador("0");
		pagoServiciosIusacellRequestTO.setCuentaCargo(Formatter.removeSpaces(cuentaCargoCompleta));
		pagoServiciosIusacellRequestTO.setTipoServicio(iusacellForm.getConcepto_pago());
		pagoServiciosIusacellRequestTO.setImporte(iusacellForm.getImporte());
		pagoServiciosIusacellRequestTO.setUser(clienteTO.getUserName());
		try{
			ResourceFacadeSL facadeSL = getDelegate();
			pagoServiciosIusacellResponseTO = facadeSL.setDataIusacellPayment(pagoServiciosIusacellRequestTO);
			pagoServiciosIusacellResponseTO.setTipoServicio(pagoServiciosIusacellRequestTO.getTipoServicio());
			
			DispositivoHuellaTO dispositivoHuellaTO = pagoServiciosIusacellResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			
			session.addAttribute(PAGO_SERVICIO_IUSACELL_RESPONSE, pagoServiciosIusacellResponseTO);
			
			
			Pago_Servicio_EjecucionTO pagoServicioIusacellTO = new Pago_Servicio_EjecucionTO();
			
			pagoServicioIusacellTO.setComision(String.valueOf(pagoServiciosIusacellResponseTO.getComision()));
			pagoServicioIusacellTO.setConcepto_pago(pagoServiciosIusacellRequestTO.getTipoServicio());
			pagoServicioIusacellTO.setCuenta_cargo(pagoServiciosIusacellResponseTO.getCuentaCargo().substring(0, 14));
			pagoServicioIusacellTO.setFolio_aclaraciones(pagoServiciosIusacellResponseTO.getFolio());
			pagoServicioIusacellTO.setFecha_aplicacion(pagoServiciosIusacellResponseTO.getFechaAplicacion());
			pagoServicioIusacellTO.setImporte(String.valueOf(pagoServiciosIusacellResponseTO.getImporte()));
			pagoServicioIusacellTO.setIva(String.valueOf(pagoServiciosIusacellResponseTO.getIva()));
			pagoServicioIusacellTO.setNumero_referencia(pagoServiciosIusacellResponseTO.getCuentaReferencia());
			pagoServicioIusacellTO.setTotal_pago(String.valueOf(pagoServiciosIusacellResponseTO.getTotal()));
			
			response.addAttribute(pagoServicioIusacellTO);
			response.addAttribute(huellaTO);
			
		} catch (EliteDataException e) {					
			buildErrorResponse(e, response);
		}		
		
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		IusacellForm iusacellForm = (IusacellForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = (PagoServiciosIusacellResponseTO)session.getAttribute(PAGO_SERVICIO_IUSACELL_RESPONSE);		
		PagoServiciosIusacellRequestTO pagoServiciosIusacellRequestTO = new PagoServiciosIusacellRequestTO();
		
		pagoServiciosIusacellRequestTO.setCuentaReferencia(pagoServiciosIusacellResponseTO.getCuentaReferencia());
		
		pagoServiciosIusacellRequestTO.setCuentaCargo(Formatter.removeSpaces(pagoServiciosIusacellResponseTO.getCuentaCargo()));
		pagoServiciosIusacellRequestTO.setTipoServicio(pagoServiciosIusacellResponseTO.getTipoServicio());
		pagoServiciosIusacellRequestTO.setFechaAplicacion(pagoServiciosIusacellResponseTO.getFechaAplicacion());
		pagoServiciosIusacellRequestTO.setImporte(String.valueOf(pagoServiciosIusacellResponseTO.getImporte()));
		pagoServiciosIusacellRequestTO.setTotal(pagoServiciosIusacellResponseTO.getTotal().toString());
		pagoServiciosIusacellRequestTO.setUser(clienteTO.getUserName());
		pagoServiciosIusacellRequestTO.setDigitoVerificador("0");
		if(iusacellForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
			pagoServiciosIusacellRequestTO.setOptionDispositive(OPCION_TOKEN);
			pagoServiciosIusacellRequestTO.setTokenCode(iusacellForm.getClave_seguridad().toString());
		}
		else if(iusacellForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
			pagoServiciosIusacellRequestTO.setOptionDispositive(OPCION_HUELLA);
			pagoServiciosIusacellRequestTO.setClave(iusacellForm.getHuella_seguridad().toString());		
		}
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate(); 
			pagoServiciosIusacellResponseTO = resourceFacadeSL.setConfirmIusacellPayment(pagoServiciosIusacellRequestTO);
		
			Pago_Servicio_EjecucionTO pagoServicioIusacellTO = new Pago_Servicio_EjecucionTO();
		
			pagoServicioIusacellTO.setComision(String.valueOf(pagoServiciosIusacellResponseTO.getComision()));
			pagoServicioIusacellTO.setConcepto_pago(pagoServiciosIusacellRequestTO.getTipoServicio());
			pagoServicioIusacellTO.setCuenta_cargo(pagoServiciosIusacellResponseTO.getCuentaCargo().substring(0, 14));
			pagoServicioIusacellTO.setFolio_aclaraciones(pagoServiciosIusacellResponseTO.getFolio());
			pagoServicioIusacellTO.setFecha_aplicacion(pagoServiciosIusacellResponseTO.getFechaAplicacion());
			pagoServicioIusacellTO.setImporte(String.valueOf(pagoServiciosIusacellResponseTO.getImporte()));
			pagoServicioIusacellTO.setIva(String.valueOf(pagoServiciosIusacellResponseTO.getIva()));
			pagoServicioIusacellTO.setNumero_referencia(pagoServiciosIusacellResponseTO.getCuentaReferencia());
			pagoServicioIusacellTO.setTotal_pago(String.valueOf(pagoServiciosIusacellResponseTO.getTotal()));
				
			super.updateBalance(session);
			
			response.addAttribute(pagoServicioIusacellTO);
		
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
				
}
