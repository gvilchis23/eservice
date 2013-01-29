package com.bancoazteca.eservice.command.pagoservicios.telmex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.PagoServiciosTelmexRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTelmexResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.PagoserviciosTelmexEjecucionTO;
import com.bancoazteca.eservice.command.base.beans.ServiciosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PagoServiciosTelmexCommand  extends CommandBase{
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();		
		ResourceFacadeSL bean = getDelegate();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);			
			PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO = new PagoServiciosTelmexRequestTO();			
			pagoServiciosTelmexRequestTO.setUser(clienteTO.getUserName());			
			PagoServiciosTelmexResponseTO telmexResponseTO =  bean.setInitialTelmexPayment(pagoServiciosTelmexRequestTO);
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
	
			Map<String, String> cuentas = telmexResponseTO.getMapCuentas();

			for (String key : cuentas.keySet()) {
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
				CuentaTO cuentaTO= new CuentaTO(); 
				if (key.length()>=14){
					cuentaTO= super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0, 14));
				}
				if(cuentaTO != null){
					cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
					cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			
			Map<String, String> servicios = telmexResponseTO.getMapServicios();
			Collection <ServiciosTO> collectionServiciosTO = new ArrayList<ServiciosTO>();
			for(String key : servicios.keySet()){
				ServiciosTO serviciosTO = new ServiciosTO();
				serviciosTO.setConcepto_pago(servicios.get(key));
				collectionServiciosTO.add(serviciosTO);
			}
			Pago_ServicioTO solicitudPagoServicioTO = new Pago_ServicioTO();		
			solicitudPagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
			solicitudPagoServicioTO.setColeccion_servicios(collectionServiciosTO);		
	
			session.addAttribute(CommandConstantes.PAGOSERVICIOS_TELMEX_SOLICITUD_RESPONSE, telmexResponseTO);
			response.addAttribute(solicitudPagoServicioTO);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		PagoServiciosTelmexForm forma = (PagoServiciosTelmexForm) getFormBean();
		ResourceFacadeSL bean = getDelegate();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = (PagoServiciosTelmexResponseTO) session.getAttribute(CommandConstantes.PAGOSERVICIOS_TELMEX_SOLICITUD_RESPONSE);
			Map<String, String> cuentasMap = pagoServiciosTelmexResponseTO.getMapCuentas();
			String cuenta=forma.getCuenta_cargo();
			PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO = new PagoServiciosTelmexRequestTO();
			for(String key : cuentasMap.keySet()){
				if (cuenta.equals(cuentasMap.get(key).substring(0,14)))	pagoServiciosTelmexRequestTO.setCuentaCargo(forma.getCuenta_cargo());
			}
			
			pagoServiciosTelmexRequestTO.setNumeroTelefonico(forma.getNumero_telefonico());//.getTelefono());

			pagoServiciosTelmexRequestTO.setDigitoVerificador(forma.getDigito_verificador());//.getDigitoVerificador());
			pagoServiciosTelmexRequestTO.setTipoServicio(forma.getConcepto_pago());
			pagoServiciosTelmexRequestTO.setImporte(forma.getImporte());
			pagoServiciosTelmexRequestTO.setUser(clienteTO.getUserName());
			//concepto pago???			

			pagoServiciosTelmexResponseTO = bean.setDataTelmexPayment(pagoServiciosTelmexRequestTO);
			HuellaTO huella=new HuellaTO();
			huella.setLlave_publica(pagoServiciosTelmexResponseTO.getDispositivoHuellaTO().getLlavePublica());
			huella.setLongitud_huella(pagoServiciosTelmexResponseTO.getDispositivoHuellaTO().getLongitudHuella());

			session.addAttribute(CommandConstantes.PAGOSERVICIOS_TELMEX_VALIDACION_RESPONSE, pagoServiciosTelmexResponseTO);
			
			PagoserviciosTelmexEjecucionTO pagoserviciosTelmexEjecucionTO= new PagoserviciosTelmexEjecucionTO();
			pagoserviciosTelmexEjecucionTO.setComision(pagoServiciosTelmexResponseTO.getComision().toString());
			//pagoserviciosTelmexEjecucionTO.setConcepto_pago(pagoServiciosTelmexResponseTO.get);?????
			pagoserviciosTelmexEjecucionTO.setConcepto_pago(forma.getConcepto_pago());
			pagoserviciosTelmexEjecucionTO.setCuenta_cargo(pagoServiciosTelmexResponseTO.getCuentaCargo());
			pagoserviciosTelmexEjecucionTO.setDigito_verificacdor(pagoServiciosTelmexResponseTO.getDigitoVerificador());
			pagoserviciosTelmexEjecucionTO.setFecha_aplicacion(pagoServiciosTelmexResponseTO.getFechaAplicacion());
			pagoserviciosTelmexEjecucionTO.setFolio_aclaracion(pagoServiciosTelmexResponseTO.getFolio());
			pagoserviciosTelmexEjecucionTO.setImporte(pagoServiciosTelmexResponseTO.getImporte().toString());
			pagoserviciosTelmexEjecucionTO.setIva(pagoServiciosTelmexResponseTO.getIva().toString());
			pagoserviciosTelmexEjecucionTO.setNumero_telefonico(pagoServiciosTelmexResponseTO.getCuentaReferencia());
			pagoserviciosTelmexEjecucionTO.setTotal_pago(pagoServiciosTelmexResponseTO.getTotal().toString());	
			
			response.addAttribute(pagoserviciosTelmexEjecucionTO);
			response.addAttribute(huella);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		PagoServiciosTelmexForm forma = (PagoServiciosTelmexForm) getFormBean();
		PagoserviciosTelmexEjecucionTO pagoserviciosTelmexEjecucionTO= new PagoserviciosTelmexEjecucionTO();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = (PagoServiciosTelmexResponseTO) session.getAttribute(PAGOSERVICIOS_TELMEX_VALIDACION_RESPONSE);

			PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO = new PagoServiciosTelmexRequestTO();

			pagoServiciosTelmexRequestTO.setNumeroTelefonico(pagoServiciosTelmexResponseTO.getCuentaReferencia());
			pagoServiciosTelmexRequestTO.setCuentaCargo(pagoServiciosTelmexResponseTO.getCuentaCargo());
			pagoServiciosTelmexRequestTO.setDigitoVerificador(pagoServiciosTelmexResponseTO.getDigitoVerificador());
			pagoServiciosTelmexRequestTO.setTipoServicio("TELMEX     Telefonia Local");
			pagoServiciosTelmexRequestTO.setImporte(pagoServiciosTelmexResponseTO.getImporte().toString());
			pagoServiciosTelmexRequestTO.setTotal(pagoServiciosTelmexResponseTO.getTotal().toString());
			pagoServiciosTelmexRequestTO.setFechaAplicacion(pagoServiciosTelmexResponseTO.getFechaAplicacion());
			pagoServiciosTelmexRequestTO.setUser(clienteTO.getUserName());

			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				pagoServiciosTelmexRequestTO.setClave(forma.getHuella_seguridad().toString());
				pagoServiciosTelmexRequestTO.setOptionDispositive(OPCION_HUELLA);
			}
			else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				pagoServiciosTelmexRequestTO.setTokenCode(forma.getClave_seguridad().toString());
				pagoServiciosTelmexRequestTO.setOptionDispositive(OPCION_TOKEN);
			}
			ResourceFacadeSL bean = getDelegate();
			pagoServiciosTelmexResponseTO = bean.setConfimTelmexPayment(pagoServiciosTelmexRequestTO);
			super.updateBalance(session);
			
			pagoserviciosTelmexEjecucionTO.setComision(pagoServiciosTelmexResponseTO.getComision().toString());
	
			//pagoserviciosTelmexEjecucionTO.setConcepto_pago(pagoServiciosTelmexResponseTO.get);?????
			pagoserviciosTelmexEjecucionTO.setCuenta_cargo(pagoServiciosTelmexResponseTO.getCuentaCargo());
			pagoserviciosTelmexEjecucionTO.setDigito_verificacdor(pagoServiciosTelmexResponseTO.getDigitoVerificador());
			pagoserviciosTelmexEjecucionTO.setFecha_aplicacion(pagoServiciosTelmexResponseTO.getFechaAplicacion());
			pagoserviciosTelmexEjecucionTO.setFolio_aclaracion(pagoServiciosTelmexResponseTO.getFolio());
			pagoserviciosTelmexEjecucionTO.setImporte(pagoServiciosTelmexResponseTO.getImporte().toString());
			pagoserviciosTelmexEjecucionTO.setIva(pagoServiciosTelmexResponseTO.getIva().toString());
			pagoserviciosTelmexEjecucionTO.setNumero_telefonico(pagoServiciosTelmexResponseTO.getCuentaReferencia());
			pagoserviciosTelmexEjecucionTO.setTotal_pago(pagoServiciosTelmexResponseTO.getTotal().toString());	
			response.addAttribute(pagoserviciosTelmexEjecucionTO);
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	 }
}