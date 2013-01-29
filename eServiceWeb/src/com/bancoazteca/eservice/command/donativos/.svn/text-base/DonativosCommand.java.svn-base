package com.bancoazteca.eservice.command.donativos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.DonativosRequestTO;
import com.bancoazteca.elite.beans.DonativosResponseTO;
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

import org.apache.log4j.Logger;

public class DonativosCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(DonativosCommand.class);
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		DonativosRequestTO donativosRequestTO = new DonativosRequestTO();
		
		donativosRequestTO.setUser(clienteTO.getUserName());
		ResourceFacadeSL facadeSL = getDelegate();
		DonativosResponseTO donativosResponseTO = facadeSL.setInitialDonativo(donativosRequestTO);
		Collection<Cuenta_CargoTO> cuentaCollectionTO = new ArrayList<Cuenta_CargoTO>();
		Collection<ServiciosTO> serviciosCollectionTO = new ArrayList<ServiciosTO>();
		Collection<CuentaLoTO> cuentas = donativosResponseTO.getCuentas();					
		String[] descripcionSaldo=null;
		String  cuenta=null;
		
		Map<String,String> cuentaIndex=new HashMap<String,String>();
		for(CuentaLoTO cuentaLo:cuentas)
		{	
			Cuenta_CargoTO cuentaTO=new Cuenta_CargoTO();
			cuenta=Formatter.removeSpaces(cuentaLo.getCuenta().substring(0,18));
			cuentaTO.setNumero_cuenta(cuenta);
			descripcionSaldo=cuentaLo.getCuenta().substring(18).split("\\$");
			cuentaTO.setSaldo_disponible(Formatter.removeSpacesLeftRight(descripcionSaldo[1]));
			cuentaTO.setProducto(Formatter.removeSpacesLeftRight(descripcionSaldo[0]));						
			cuentaCollectionTO.add(cuentaTO);		
			cuentaIndex.put(cuenta,cuentaLo.getIndex());
		}		
		ServiciosTO donativo = new ServiciosTO();
		donativo.setConcepto_pago(donativosResponseTO.getReferencia());
		serviciosCollectionTO.add(donativo);
		
		Pago_ServicioTO pagoServicioTO = new Pago_ServicioTO();
		pagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);	
		pagoServicioTO.setColeccion_servicios(serviciosCollectionTO);
		response.addAttribute(pagoServicioTO);
		
		session.addAttribute(DONATIVOS_CUENTA_RESPONSE,cuentaIndex);
		session.addAttribute(DONATIVOS_CLIENTE, donativosResponseTO.getNombreCompleto());
		
		return response;		
	}
	
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		DonativosForm forma = (DonativosForm)getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		DonativosResponseTO donativosResponseTO = null;
		//Detalle_DonativosTO detalle_DonativosTO = new Detalle_DonativosTO();
		Pago_Servicio_EjecucionTO pagoServicioEjecucionTO = new Pago_Servicio_EjecucionTO();

		
		Map<String,String> cuentasIndex=(Map<String, String>)session.getAttribute(DONATIVOS_CUENTA_RESPONSE);		
		String nombreCompleto =  (String) session.getAttribute(DONATIVOS_CLIENTE);
		
		
		DonativosRequestTO donativosRequestTO = new DonativosRequestTO();
		donativosRequestTO.setDigitoVerificador("0");
		donativosRequestTO.setCuentaCargo(cuentasIndex.get(Formatter.removeSpaces(forma.getCuenta_cargo())));		
		donativosRequestTO.setTipoServicio("Donativo Fundación Azteca");
		donativosRequestTO.setImporte(forma.getImporte().toString());
		donativosRequestTO.setUser(clienteTO.getUserName());
		
		
		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			donativosResponseTO = resourceFacadeSL.setDataDonativo(donativosRequestTO);		

			
			pagoServicioEjecucionTO.setTitular_cuenta(nombreCompleto);
			pagoServicioEjecucionTO.setCuenta_cargo(Formatter.removeSpaces(forma.getCuenta_cargo()));
			pagoServicioEjecucionTO.setConcepto_pago(donativosResponseTO.getReferencia());
			pagoServicioEjecucionTO.setTotal_pago(donativosResponseTO.getImporte().toString());
			pagoServicioEjecucionTO.setReferencia(donativosResponseTO.getReferencia());
			pagoServicioEjecucionTO.setImporte(forma.getImporte().toString());
			pagoServicioEjecucionTO.setComision("0.0");
			pagoServicioEjecucionTO.setIva("0.0");
	
			HuellaTO huellaTO = new HuellaTO();
			if(donativosResponseTO.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica(donativosResponseTO.getDispositivoHuellaTO().getLlavePublica());
				huellaTO.setLongitud_huella(donativosResponseTO.getDispositivoHuellaTO().getLongitudHuella());
			}
			
			session.addAttribute(DETALLE_DONATIVOS, pagoServicioEjecucionTO);
			session.addAttribute(DONATIVOS_RESPONSE, donativosResponseTO);
			session.addAttribute(DONATIVOS_CLIENTE, nombreCompleto);
			
			response.addAttribute(pagoServicioEjecucionTO);
			response.addAttribute(huellaTO);

		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		DonativosForm forma = (DonativosForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String nombreCompleto =  (String) session.getAttribute(DONATIVOS_CLIENTE);
		DonativosResponseTO donativosResponseTO = (DonativosResponseTO)session.getAttribute(DONATIVOS_RESPONSE);
		Pago_Servicio_EjecucionTO detalle_DonativosTO =(Pago_Servicio_EjecucionTO)session.getAttribute(DETALLE_DONATIVOS);
		DonativosRequestTO donativosRequestTO = new DonativosRequestTO();
				
		donativosRequestTO.setCuentaCargo(Formatter.removeSpaces(detalle_DonativosTO.getCuenta_cargo()));
		donativosRequestTO.setTipoServicio(donativosResponseTO.getTipoServicio());
		donativosRequestTO.setImporte(detalle_DonativosTO.getImporte().toString());
		donativosRequestTO.setTotal(detalle_DonativosTO.getImporte().toString());
		donativosRequestTO.setUser(clienteTO.getUserName());
		donativosRequestTO.setDigitoVerificador("0");
		

		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
			donativosRequestTO.setOptionDispositive(OPCION_TOKEN);
			donativosRequestTO.setTokenCode(forma.getClave_seguridad().toString());
		} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
			donativosRequestTO.setOptionDispositive(OPCION_HUELLA);
			donativosRequestTO.setClave(forma.getHuella_seguridad().toString());
		}
		
		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			donativosResponseTO = resourceFacadeSL.setConfirmDonativo(donativosRequestTO);
			Pago_Servicio_EjecucionTO pagoServicioEjecucionTO = new Pago_Servicio_EjecucionTO();

			pagoServicioEjecucionTO.setTitular_cuenta(nombreCompleto);
			pagoServicioEjecucionTO.setCuenta_cargo(donativosResponseTO.getCuentaCargo().substring(0, 14));
			pagoServicioEjecucionTO.setReferencia(detalle_DonativosTO.getReferencia());
			pagoServicioEjecucionTO.setImporte(donativosResponseTO.getImporte().toString());
			pagoServicioEjecucionTO.setComision("0.0");
			pagoServicioEjecucionTO.setIva("0.0");
			pagoServicioEjecucionTO.setFecha_aplicacion(donativosResponseTO.getFechaOperacion());	
			pagoServicioEjecucionTO.setFolio_aclaraciones(donativosResponseTO.getFolio());
			pagoServicioEjecucionTO.setTotal_pago(donativosResponseTO.getImporte().toString());

			super.updateBalance(session);
			response.addAttribute(pagoServicioEjecucionTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}		
		return response;
	}
}