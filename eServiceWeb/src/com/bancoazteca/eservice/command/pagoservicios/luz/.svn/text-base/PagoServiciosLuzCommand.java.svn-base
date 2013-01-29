package com.bancoazteca.eservice.command.pagoservicios.luz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServicioLuzRequestTO;
import com.bancoazteca.elite.beans.PagoServicioLuzResponsetTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Pago_Servicios_LuzTO;
import com.bancoazteca.eservice.command.base.beans.ServiciosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class PagoServiciosLuzCommand extends CommandBase {
	private static final Logger log = Logger.getLogger(PagoServiciosLuzCommand.class);
	
	public Response solicitud(Session session) throws Exception{
		log.info("PagoServiciosLuz solicitud");
		Response response = new Response();
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			PagoServicioLuzRequestTO pagoServicioLuzRequestTO = new PagoServicioLuzRequestTO();
			ResourceFacadeSL bean = getDelegate();		
			pagoServicioLuzRequestTO.setUser(clienteTO.getUserName());
			
				Collection<Cuenta_CargoTO> cuentaCollectionTO = new ArrayList<Cuenta_CargoTO>();

				PagoServicioLuzResponsetTO servicioLuzResponsetTO = bean.setInitialLuzPayment(pagoServicioLuzRequestTO);
				Map<String, String> cuentas = servicioLuzResponsetTO.getMapCuentas();

				for (String key : cuentas.keySet()) {
					
					Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
					CuentaTO cuentaTO = super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0, 14));
					cuentaCargoTO.setNumero_cuenta(cuentaTO.getCuentaFormateada().replace(" ", ""));
					cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
						
				Map<String, String> servicios = servicioLuzResponsetTO.getMapServicios();
				Collection <ServiciosTO> collectionServiciosTO = new ArrayList<ServiciosTO>();
				
				for(String key : servicios.keySet()){
					ServiciosTO serviciosTO = new ServiciosTO();
					serviciosTO.setConcepto_pago(key);
					collectionServiciosTO.add(serviciosTO);
				}
				
				Pago_ServicioTO pagoServicioTO = new Pago_ServicioTO();
				pagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
				pagoServicioTO.setColeccion_servicios(collectionServiciosTO);
				response.addAttribute(pagoServicioTO);

		
				synchronized (session) {
					
					session.addAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_RESPONSE, servicioLuzResponsetTO);
					
				}
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;	
	}
	
	
	public Response validacion(Session session) throws Exception{
		log.info("PagoServiciosLuz validacion");

		PagoServiciosLuzForm forma = (PagoServiciosLuzForm)getFormBean();
		ResourceFacadeSL bean = getDelegate();	
		Response response = new Response();	
		
		try{
			
			if(forma.getCuenta_cargo().length()>14){			
				forma.setCuenta_cargo(Formatter.removeSpaces(forma.getCuenta_cargo()).substring(0,14));
			}
			
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			PagoServicioLuzRequestTO pagoServicioLuzRequestTO = new PagoServicioLuzRequestTO();
			PagoServicioLuzResponsetTO pagoServicioLuzResponsetTO = (PagoServicioLuzResponsetTO) session.getAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_RESPONSE);	
			Map<String, String> cuentasMap = pagoServicioLuzResponsetTO.getMapCuentas();
			pagoServicioLuzRequestTO.setCuentaReferencia(forma.getNumero_referencia());
			pagoServicioLuzRequestTO.setFechaVencimiento(forma.getFecha_limite());
			pagoServicioLuzRequestTO.setDigitoVerificador("0");
			pagoServicioLuzRequestTO.setCuentaCargo(forma.getCuenta_cargo());
			pagoServicioLuzRequestTO.setImporte(forma.getImporte().toString());
			pagoServicioLuzRequestTO.setUser(clienteTO.getUserName());
			pagoServicioLuzResponsetTO = bean.setDataLuzPayment(pagoServicioLuzRequestTO);
			pagoServicioLuzResponsetTO.setMapCuentas(cuentasMap);
			
			Pago_Servicios_LuzTO pagoServiciosLuzTO = new Pago_Servicios_LuzTO();
			
			pagoServiciosLuzTO.setNumeroReferencia(pagoServicioLuzResponsetTO.getCuentaReferencia());
			pagoServiciosLuzTO.setFechaLimite(pagoServicioLuzResponsetTO.getFechaVencimiento());
			pagoServiciosLuzTO.setImporte(pagoServicioLuzResponsetTO.getTotal().toString());
			pagoServiciosLuzTO.setComision(pagoServicioLuzResponsetTO.getComision().toString());
			pagoServiciosLuzTO.setIva(pagoServicioLuzResponsetTO.getIva().toString());
			pagoServiciosLuzTO.setTotalPago(pagoServicioLuzResponsetTO.getTotal().toString());
			pagoServiciosLuzTO.setConceptoPago("Luz y Fuerza Centro");
			pagoServiciosLuzTO.setFechaAplicacion(pagoServicioLuzResponsetTO.getFechaAplicacion());
			pagoServiciosLuzTO.setFolioAclaraciones(pagoServicioLuzResponsetTO.getNumeroOperacion());
			response.addAttribute(pagoServiciosLuzTO);
			
			DispositivoHuellaTO dispositivoHuellaTO = pagoServicioLuzResponsetTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			response.addAttribute(huellaTO);
						
			synchronized(session){
				session.addAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_REQUEST, pagoServicioLuzRequestTO);
				session.addAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_RESPONSE, pagoServicioLuzResponsetTO);
			}
			if(pagoServicioLuzResponsetTO.getDispositivoHuellaTO() != null) {
				session.addAttribute(CommandConstantes.DISPOSITIVO_HUELLA, pagoServicioLuzResponsetTO.getDispositivoHuellaTO());
			}
		}catch(EliteDataException e){
			buildErrorResponse(e, response);	
		}
		
		return response;
		
	}
	
	public Response ejecucion(Session session) throws Exception{
		log.info("PagoServiciosLuz ejecucion");

		PagoServicioLuzResponsetTO pagoServicioLuzResponsetTO = null;
		PagoServicioLuzRequestTO pagoServicioLuzRequestTOValidacion = null;
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		PagoServiciosLuzForm forma = (PagoServiciosLuzForm)getFormBean();			
		ResourceFacadeSL bean = getDelegate();
		Response response = new Response();	
		
		try{
			synchronized(session){
				
				pagoServicioLuzResponsetTO = (PagoServicioLuzResponsetTO) session.getAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_RESPONSE);
				pagoServicioLuzRequestTOValidacion = (PagoServicioLuzRequestTO) session.getAttribute(CommandConstantes.PAGO_SERVICIOS_LUZ_REQUEST);
				
			}
			
			PagoServicioLuzRequestTO pagoServicioLuzRequestTO = new PagoServicioLuzRequestTO();
			pagoServicioLuzRequestTO.setCuentaReferencia(pagoServicioLuzResponsetTO.getCuentaReferencia());
			pagoServicioLuzRequestTO.setFechaVencimiento(pagoServicioLuzRequestTOValidacion.getFechaVencimiento());
			pagoServicioLuzRequestTO.setDigitoVerificador("0");
			pagoServicioLuzRequestTO.setCuentaCargo(pagoServicioLuzRequestTOValidacion.getCuentaCargo());
			pagoServicioLuzRequestTO.setImporte(pagoServicioLuzRequestTOValidacion.getImporte());
			pagoServicioLuzRequestTO.setUser(clienteTO.getUserName());
			pagoServicioLuzRequestTO.setFechaAplicacion(pagoServicioLuzResponsetTO.getFechaAplicacion());
			pagoServicioLuzRequestTO.setComision(pagoServicioLuzResponsetTO.getComision().toString());
			pagoServicioLuzRequestTO.setIva(pagoServicioLuzResponsetTO.getIva().toString());
			pagoServicioLuzRequestTO.setTotal(pagoServicioLuzResponsetTO.getTotal().toString());

			if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				
				pagoServicioLuzRequestTO.setOptionDispositive(CommandBase.OPCION_TOKEN);
				pagoServicioLuzRequestTO.setToken(forma.getClave_seguridad().toString());
				
			}
			else if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				
				pagoServicioLuzRequestTO.setOptionDispositive(CommandBase.OPCION_HUELLA);
				pagoServicioLuzRequestTO.setClave(forma.getHuella_seguridad().toString());
				
			}
			
			pagoServicioLuzResponsetTO = bean.setConfirmLuzPayment(pagoServicioLuzRequestTO);
			log.info("numero de operacion de luz: "+pagoServicioLuzResponsetTO.getNumeroOperacion());
			Pago_Servicios_LuzTO pagoServiciosLuzTO = new Pago_Servicios_LuzTO();
			pagoServiciosLuzTO.setCuentaCargo(pagoServicioLuzRequestTOValidacion.getCuentaCargo());
			pagoServiciosLuzTO.setNumeroReferencia(pagoServicioLuzResponsetTO.getCuentaReferencia());
			pagoServiciosLuzTO.setFechaLimite(pagoServicioLuzRequestTOValidacion.getFechaVencimiento());
			pagoServiciosLuzTO.setImporte(pagoServicioLuzResponsetTO.getTotal().toString());
			pagoServiciosLuzTO.setComision(pagoServicioLuzResponsetTO.getComision().toString());
			pagoServiciosLuzTO.setIva(pagoServicioLuzResponsetTO.getIva().toString());
			pagoServiciosLuzTO.setTotalPago(pagoServicioLuzResponsetTO.getTotal().toString());
			pagoServiciosLuzTO.setConceptoPago("Luz y Fuerza Centro");
			pagoServiciosLuzTO.setFechaAplicacion(pagoServicioLuzResponsetTO.getFechaAplicacion());
			pagoServiciosLuzTO.setFolioAclaraciones(pagoServicioLuzResponsetTO.getNumeroOperacion());
			response.addAttribute(pagoServiciosLuzTO);
			bean.setEndLuzPayment(pagoServicioLuzRequestTO);
			super.updateBalance(session);
	
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	

}
