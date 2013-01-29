package com.bancoazteca.eservice.command.pagoservicios.sky;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.PagoServiciosSkyRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosSkyResponseTO;
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



public class PagoServiciosSkyCommand extends CommandBase {		
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();

	    ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
				
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();
		Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
		Collection <ServiciosTO> serviciosCollectionTO = new ArrayList <ServiciosTO>();
		Pago_ServicioTO pagoServicioTO = new Pago_ServicioTO();
		
		PagoServiciosSkyRequestTO skyRequestTO = new PagoServiciosSkyRequestTO();
		skyRequestTO.setUser(clienteTO.getUserName());
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		PagoServiciosSkyResponseTO skyResponseTO =  resourceFacadeSL.setInitialSkyPayment(skyRequestTO);
		Collection<String> cuentasResponse = skyResponseTO.getCuentas();		
		Iterator<CuentaTO> iter = cuentas.iterator();	
		while (iter.hasNext()){
			CuentaTO cuentaTO = iter.next();
			Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
			
			boolean valida = false;
			valida = getCuentaValida(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()), cuentasResponse); 
			if (valida){
				cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));				
				cuentaCargoTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
				cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
				cuentaCollectionTO.add(cuentaCargoTO);
			}
		}

		Collection<String> servicios = skyResponseTO.getServicios();		
		Iterator<String> ite = servicios.iterator();		
		while(ite.hasNext()){
			String servicio = ite.next();
			ServiciosTO serviciosTO = new ServiciosTO();
			serviciosTO.setConcepto_pago(servicio);
			serviciosCollectionTO.add(serviciosTO);
		}

		pagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
		pagoServicioTO.setColeccion_servicios(serviciosCollectionTO);
		
		response.addAttribute(pagoServicioTO);
		session.addAttribute(PAGO_SERVICIO_SKY_RESPONSE, skyResponseTO);

		return response;
	}
	
	
	
	
	public Response validacion(Session session) throws Exception {	

		Response response = new Response();
		PagoServiciosSkyForm forma = (PagoServiciosSkyForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		HuellaTO huellaTO = new HuellaTO();
		String cuentaCargo = new String();

		try{
	
			PagoServiciosSkyResponseTO skyResponseTO = (PagoServiciosSkyResponseTO) session.getAttribute(PAGO_SERVICIO_SKY_RESPONSE);
			PagoServiciosSkyRequestTO skyRequestTO = new PagoServiciosSkyRequestTO();	
			
			Collection<String> cuentasResponse = skyResponseTO.getCuentas();		
			Iterator<String> iter = cuentasResponse.iterator();
			
			while (iter.hasNext()){
				String cuentaValida = iter.next();
				if(forma.getCuenta_cargo().equals(cuentaValida.substring(0, 14))){
					cuentaCargo = cuentaValida;
					break;
				}
			}
						
			skyRequestTO.setUser(clienteTO.getUserName());	
			skyRequestTO.setCuentaReferencia(forma.getNumero_referencia());
			skyRequestTO.setDigitoVerificador("0");
			skyRequestTO.setCuentaCargo(cuentaCargo);	
			skyRequestTO.setTipoServicio(forma.getConcepto_pago());
			skyRequestTO.setImporte(forma.getImporte());			
			skyResponseTO = resourceFacadeSL.setDataSkyPayment(skyRequestTO);
			
			skyResponseTO.getConfirmacionPagoServicios().setCuentaCargo(cuentaCargo);
			skyResponseTO.getConfirmacionPagoServicios().setTipoServicio(forma.getConcepto_pago());

			
			if(skyResponseTO.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica(skyResponseTO.getDispositivoHuellaTO().getLlavePublica());
				huellaTO.setLongitud_huella(skyResponseTO.getDispositivoHuellaTO().getLongitudHuella());
			}
			
			synchronized(session){
				session.addAttribute(PAGO_SERVICIO_SKY_RESPONSE, skyResponseTO);
			}
			response.addAttribute(huellaTO);
		
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {	
		Response response = new Response();

		PagoServiciosSkyForm forma = (PagoServiciosSkyForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		Pago_Servicio_EjecucionTO pagoEjecucion = new Pago_Servicio_EjecucionTO();

		try{
			PagoServiciosSkyResponseTO skyResponseTO = (PagoServiciosSkyResponseTO) session.getAttribute(PAGO_SERVICIO_SKY_RESPONSE);
			
			String cuentaCargoFormateada = skyResponseTO.getCuentaCargoFormateada();
			Map<String, String> cuentasMap = skyResponseTO.getMapCuentas();
			PagoServiciosSkyRequestTO skyRequestTO = new PagoServiciosSkyRequestTO();		
			skyRequestTO.setUser(clienteTO.getUserName());	
			skyRequestTO.setCuentaReferencia(skyResponseTO.getConfirmacionPagoServicios().getCuentaReferencia());
			skyRequestTO.setDigitoVerificador(skyResponseTO.getConfirmacionPagoServicios().getDigitoVerificador());		
			skyRequestTO.setCuentaCargo(skyResponseTO.getConfirmacionPagoServicios().getCuentaCargo());
			skyRequestTO.setTipoServicio(skyResponseTO.getConfirmacionPagoServicios().getTipoServicio());
			skyRequestTO.setImporte(skyResponseTO.getConfirmacionPagoServicios().getImporte());
			skyRequestTO.setComision(skyResponseTO.getConfirmacionPagoServicios().getComision());
			skyRequestTO.setIva(skyResponseTO.getConfirmacionPagoServicios().getIva());
			skyRequestTO.setTotal(skyResponseTO.getConfirmacionPagoServicios().getTotal());
			
			if(forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				skyRequestTO.setOptionDispositive(OPCION_TOKEN);
				skyRequestTO.setTokenCode(forma.getClave_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
				skyRequestTO.setOptionDispositive(OPCION_HUELLA);
				skyRequestTO.setClave(forma.getHuella_seguridad().toString());
			}

			skyResponseTO = resourceFacadeSL.setConfirmSkyPayment(skyRequestTO);
			skyResponseTO.setMapCuentas(cuentasMap);
			skyResponseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
			super.updateBalance(session);
			pagoEjecucion.setCuenta_cargo(skyResponseTO.getConfirmacionPagoServicios().getCuentaCargo().substring(0, 14));
			pagoEjecucion.setNumero_referencia(skyResponseTO.getConfirmacionPagoServicios().getCuentaReferencia()); 
			pagoEjecucion.setImporte(skyResponseTO.getConfirmacionPagoServicios().getImporte());
			pagoEjecucion.setComision(skyResponseTO.getConfirmacionPagoServicios().getComision());
			pagoEjecucion.setIva(skyResponseTO.getConfirmacionPagoServicios().getIva());
			pagoEjecucion.setTotal_pago(skyResponseTO.getConfirmacionPagoServicios().getTotal());
			pagoEjecucion.setConcepto_pago(skyResponseTO.getConfirmacionPagoServicios().getTipoServicio());
			pagoEjecucion.setFolio_aclaraciones(skyResponseTO.getConfirmacionPagoServicios().getFolio());
			pagoEjecucion.setFecha_aplicacion(skyResponseTO.getConfirmacionPagoServicios().getFechaAplicacion());
			
			
			
			
			response.addAttribute(pagoEjecucion);
	
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public boolean getCuentaValida(String cuenta, Collection<String> cuentasValidas){
		boolean valida = false;
		Iterator<String> cuentas = cuentasValidas.iterator();
		while (cuentas.hasNext()){
			String cuentaValida = cuentas.next().substring(0, 14);			
			if(cuenta.equals(cuentaValida)){
				valida = true;
				break;
			}
			
		}
		return valida;
	}
		
}

