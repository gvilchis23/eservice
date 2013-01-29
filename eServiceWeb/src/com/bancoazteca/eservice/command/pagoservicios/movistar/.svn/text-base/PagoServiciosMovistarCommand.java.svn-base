package com.bancoazteca.eservice.command.pagoservicios.movistar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosMovistarRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMovistarResponseTO;
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

public class PagoServiciosMovistarCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(PagoServiciosMovistarCommand.class);	

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);

		PagoServiciosMovistarRequestTO pagoServiciosMovistarRequestTO = new PagoServiciosMovistarRequestTO();
		pagoServiciosMovistarRequestTO.setUser(clienteTO.getUserName());

		ResourceFacadeSL facadeSL = getDelegate();
		PagoServiciosMovistarResponseTO pagoServiciosMovistarResponseTO = facadeSL.setInitialMovistarPayment(pagoServiciosMovistarRequestTO);

		Collection<Cuenta_CargoTO> cuentaCollectionTO = new ArrayList<Cuenta_CargoTO>();
		
		Map<String, String> cuentas = pagoServiciosMovistarResponseTO.getMapCuentas();

		for (String key : cuentas.keySet()) {
			Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
			CuentaTO cuentaTO = super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0, 14));
			if(cuentaTO != null){
				cuentaCargoTO.setNumero_cuenta(cuentaTO.getCuentaFormateada().replace(" ", ""));
				cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
				cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
				cuentaCollectionTO.add(cuentaCargoTO);
			}
		}
		Map<String, String> servicios = pagoServiciosMovistarResponseTO.getMapServicios();
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

		session.addAttribute(PAGO_SERVICIOS_MOVISTAR_RESPONSE,pagoServiciosMovistarResponseTO);

		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		PagoServiciosMovistarForm forma = (PagoServiciosMovistarForm)getFormBean();

		String cuentaReferencia = forma.getNumero_referencia();
		while (cuentaReferencia.length() < 18) {
			cuentaReferencia = "0" + cuentaReferencia;
		}		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		PagoServiciosMovistarResponseTO pagoServiciosMovistarResponseTO = (PagoServiciosMovistarResponseTO) session.getAttribute(PAGO_SERVICIOS_MOVISTAR_RESPONSE);
		Map<String, String> cuentasMap = pagoServiciosMovistarResponseTO.getMapCuentas();
		Map<String, String> serviciosMap = pagoServiciosMovistarResponseTO.getMapServicios();
		String cuentaCargoCompleta = "";
		for (String key : cuentasMap.keySet()) {
			if (key.startsWith(forma.getCuenta_cargo())) {
				cuentaCargoCompleta = key;
				break;
			}
		}

		PagoServiciosMovistarRequestTO pagoServiciosMovistarRequestTO = new PagoServiciosMovistarRequestTO();		
		pagoServiciosMovistarRequestTO.setDigitoVerificador("0");
		pagoServiciosMovistarRequestTO.setCuentaCargo(Formatter.removeSpaces(cuentaCargoCompleta));		
		pagoServiciosMovistarRequestTO.setTipoServicio(forma.getConcepto_pago());
		pagoServiciosMovistarRequestTO.setImporte(forma.getImporte());
		pagoServiciosMovistarRequestTO.setUser(clienteTO.getUserName());
		pagoServiciosMovistarRequestTO.setCuentaReferencia(forma.getNumero_referencia());
		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			pagoServiciosMovistarResponseTO = resourceFacadeSL.setDataMovistarPayment(pagoServiciosMovistarRequestTO);
			pagoServiciosMovistarResponseTO.setMapCuentas(cuentasMap);
			pagoServiciosMovistarResponseTO.setMapServicios(serviciosMap);			
			
			DispositivoHuellaTO dispositivoHuellaTO = pagoServiciosMovistarResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			
			session.addAttribute(PAGO_SERVICIOS_MOVISTAR_RESPONSE,pagoServiciosMovistarResponseTO);
			response.addAttribute(huellaTO);

		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		PagoServiciosMovistarForm forma = (PagoServiciosMovistarForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		PagoServiciosMovistarResponseTO pagoServiciosMovistarResponseTO =(PagoServiciosMovistarResponseTO) session.getAttribute(PAGO_SERVICIOS_MOVISTAR_RESPONSE);
		PagoServiciosMovistarRequestTO pagoServiciosMovistarRequestTO = new PagoServiciosMovistarRequestTO();

		pagoServiciosMovistarRequestTO.setCuentaReferencia(pagoServiciosMovistarResponseTO.getCuentaReferencia());
		pagoServiciosMovistarRequestTO.setCuentaCargo(Formatter.removeSpaces(pagoServiciosMovistarResponseTO.getCuentaCargo()));
		pagoServiciosMovistarRequestTO.setTipoServicio(pagoServiciosMovistarResponseTO.getTipoServicio());
		pagoServiciosMovistarRequestTO.setFechaAplicacion(pagoServiciosMovistarResponseTO.getFechaAplicacion());
		pagoServiciosMovistarRequestTO.setImporte(pagoServiciosMovistarResponseTO.getImporte().toString());
		pagoServiciosMovistarRequestTO.setTotal(pagoServiciosMovistarResponseTO.getTotal().toString());
		pagoServiciosMovistarRequestTO.setUser(clienteTO.getUserName());
		pagoServiciosMovistarRequestTO.setDigitoVerificador("0");

		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
			pagoServiciosMovistarRequestTO.setOptionDispositive(OPCION_TOKEN);
			pagoServiciosMovistarRequestTO.setTokencode(forma.getClave_seguridad().toString());
		} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
			pagoServiciosMovistarRequestTO.setOptionDispositive(OPCION_HUELLA);
			pagoServiciosMovistarRequestTO.setClave(forma.getHuella_seguridad().toString());
		}

		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			pagoServiciosMovistarResponseTO = resourceFacadeSL.setConfirmMovistarPayment(pagoServiciosMovistarRequestTO);
			Pago_Servicio_EjecucionTO pagoServicioEjecucionTO = new Pago_Servicio_EjecucionTO();

			pagoServicioEjecucionTO.setCuenta_cargo(pagoServiciosMovistarResponseTO.getCuentaCargo().substring(0, 14));
			pagoServicioEjecucionTO.setNumero_referencia(pagoServiciosMovistarResponseTO.getCuentaReferencia());
			pagoServicioEjecucionTO.setImporte(pagoServiciosMovistarResponseTO.getImporte().toString());
			pagoServicioEjecucionTO.setComision(pagoServiciosMovistarResponseTO.getComision().toString());
			pagoServicioEjecucionTO.setIva(pagoServiciosMovistarResponseTO.getIva().toString());
			pagoServicioEjecucionTO.setTotal_pago(pagoServiciosMovistarResponseTO.getTotal().toString());
			pagoServicioEjecucionTO.setConcepto_pago(pagoServiciosMovistarResponseTO.getTipoServicio());
			pagoServicioEjecucionTO.setFolio_aclaraciones(pagoServiciosMovistarResponseTO.getFolio());
			pagoServicioEjecucionTO.setFecha_aplicacion(pagoServiciosMovistarResponseTO.getFechaAplicacion());
			super.updateBalance(session);

			response.addAttribute(pagoServicioEjecucionTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

}