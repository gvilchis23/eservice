package com.bancoazteca.eservice.command.mediospago.cambionip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bancoazteca.elite.beans.CambioNipRequestTO;
import com.bancoazteca.elite.beans.CambioNipResponseTO;
import com.bancoazteca.elite.beans.CancelacionElementTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_NipTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_Cambio_NipTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class CambioNipCommand extends CommandBase{
	private ResourceFacadeSL facade;
	
	public Response solicitud(Session session) throws Exception {
		Response response= new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		CambioNipRequestTO request = new CambioNipRequestTO();
		CambioNipResponseTO cambioNipResponseTO=new CambioNipResponseTO();
		request.setUser(clienteTO.getUserName());
		request.setTarjeta("0");
		try {
			facade = getDelegate();
			cambioNipResponseTO  = facade.getNipChangeInvocation(request);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
	
		Collection<Cuenta_NipTO> coleccionCuentas = new ArrayList<Cuenta_NipTO>();
		Collection<CancelacionElementTO> cuentas = cambioNipResponseTO.getTarjetasCambioNip();	
		Cuentas_Cambio_NipTO coleccion=new Cuentas_Cambio_NipTO();
		Cuenta_NipTO cuentas_NipTO=null;
		for(CancelacionElementTO CancelacionElement:cuentas)
		{	
			cuentas_NipTO=new Cuenta_NipTO();
			cuentas_NipTO.setCuenta(CancelacionElement.getCuenta());
			cuentas_NipTO.setNumero_tarjeta(CancelacionElement.getTarjeta());
			cuentas_NipTO.setTipo_cuenta(CancelacionElement.getTipo());
			cuentas_NipTO.setNombre_titular(clienteTO.getNombreCompleto());
			coleccionCuentas.add(cuentas_NipTO);
			
		}
		coleccion.setColeccion_cuentas(coleccionCuentas);
		session.addAttribute(MEDIOS_PAGO_TARJETAS, coleccion);
		session.addAttribute(CAMBIO_NIP_REQUEST, request);
		response.addAttribute(coleccion);
		return response;
	}
	
	public Response validacion(Session session) throws Exception {
		Response response= new Response();
		CambioNipForm forma = (CambioNipForm) getFormBean();
		CambioNipRequestTO request = (CambioNipRequestTO)session.getAttribute(CAMBIO_NIP_REQUEST);
		//Medios_pagoTO medios_pagoTO= (Medios_pagoTO)session.getAttribute(MEDIOS_PAGO_TARJETAS);
		Cuentas_Cambio_NipTO cuentas_Cambio_NipTO= (Cuentas_Cambio_NipTO)session.getAttribute(MEDIOS_PAGO_TARJETAS);
		System.out.println("CUENTAS>>>>>>>>>>" +cuentas_Cambio_NipTO.getColeccion_cuentas());
		request.setTarjeta(getTarjetaIndex(cuentas_Cambio_NipTO.getColeccion_cuentas(), forma.getNumero_tarjeta()));	
		Cuenta_NipTO cuentas_NipTO=null;
		
		for(Cuenta_NipTO Cuenta_NipTO:cuentas_Cambio_NipTO.getColeccion_cuentas())
		{	
			cuentas_NipTO=new Cuenta_NipTO();
			cuentas_NipTO.setCuenta(Cuenta_NipTO.getCuenta());
			cuentas_NipTO.setNumero_tarjeta(Cuenta_NipTO.getNumero_tarjeta());
			cuentas_NipTO.setTipo_cuenta(Cuenta_NipTO.getTipo_cuenta());
			cuentas_NipTO.setNombre_titular(Cuenta_NipTO.getNombre_titular());
			
		}
		
		request.setNewNip1(forma.getNuevo_nip().string);
		request.setNewNip2(forma.getConfirma_nuevo_nip().string);
		request.setActualNip(forma.getActual_nip().string);
		request.setUser(request.getUser());
		
		try {
			facade = getDelegate();
			CambioNipResponseTO cambioNipResponseTO  = facade.setNipChangeExecution(request);
			HuellaTO  huellaTO= new HuellaTO();	
			huellaTO.setLlave_publica(cambioNipResponseTO.getDispositivoHuellaTO().getLlavePublica());
			huellaTO.setLongitud_huella(cambioNipResponseTO.getDispositivoHuellaTO().getLongitudHuella());
			response.addAttribute(huellaTO);
			session.addAttribute(CAMBIO_NIP_REQUEST, request);
			Cuenta_NipTO cambio_nip= new Cuenta_NipTO();
			cambio_nip.setCuenta(cuentas_NipTO.getCuenta());
			cambio_nip.setNumero_tarjeta(forma.getNumero_tarjeta());
			cambio_nip.setMensaje("Advertencia: Esta a punto de cambiar el NIP de su tarjeta. Para confirmar esta operación.");
			cambio_nip.setTipo_cuenta(cuentas_NipTO.getTipo_cuenta());
			cambio_nip.setNombre_titular(cuentas_NipTO.getNombre_titular());
			response.addAttribute(cambio_nip);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response= new Response();
		CambioNipForm forma = (CambioNipForm) getFormBean();
		Cuenta_NipTO cuentas_NipTO=null;
		CambioNipRequestTO request = (CambioNipRequestTO)session.getAttribute(CAMBIO_NIP_REQUEST);
		Cuentas_Cambio_NipTO cuentas_Cambio_NipTO= (Cuentas_Cambio_NipTO)session.getAttribute(MEDIOS_PAGO_TARJETAS);
		
		for(Cuenta_NipTO Cuenta_NipTO:cuentas_Cambio_NipTO.getColeccion_cuentas())
		{	
			cuentas_NipTO=new Cuenta_NipTO();
			cuentas_NipTO.setCuenta(Cuenta_NipTO.getCuenta());
			cuentas_NipTO.setNumero_tarjeta(Cuenta_NipTO.getNumero_tarjeta());
			cuentas_NipTO.setTipo_cuenta(Cuenta_NipTO.getTipo_cuenta());
			cuentas_NipTO.setNombre_titular(Cuenta_NipTO.getNombre_titular());
			
		}
		
		
		request.setTarjeta(getNumeroTarjetaByIndex(cuentas_Cambio_NipTO.getColeccion_cuentas(),Integer.parseInt( request.getTarjeta())));
		
		if(forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
			request.setOptionDispositive(OPCION_HUELLA);
			request.setClave(forma.getHuella_seguridad().toString());
		}else{
			request.setOptionDispositive(OPCION_TOKEN);
			request.setTokencode(forma.getClave_seguridad().string);
		}
		
		try {
			facade = getDelegate();
			CambioNipResponseTO cambioNipResponseTO = facade.setNipChangeConfirmation(request);
			//Obtenemos el mensaje de la transaccion
			cambioNipResponseTO.getMensajeTransaccion();
			Cuenta_NipTO cambio_nip= new Cuenta_NipTO();
			cambio_nip.setCuenta(cuentas_NipTO.getCuenta());
			cambio_nip.setNumero_tarjeta(request.getTarjeta());
			cambio_nip.setMensaje("El NIP de su tarjeta ha sido cambiado con éxito. Gracias por utilizar los servicios de Banca Electrónica de Banco Azteca.");
			cambio_nip.setTipo_cuenta(cuentas_NipTO.getTipo_cuenta());
			cambio_nip.setNombre_titular(cuentas_NipTO.getNombre_titular());
			response.addAttribute(cambio_nip);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	private String getTarjetaIndex(Collection<Cuenta_NipTO> tarjetas, String numeroTarjeta){
		Iterator<?> iterator = tarjetas.iterator();
		int indice = 0;
		while(iterator.hasNext()){
			Cuenta_NipTO to = (Cuenta_NipTO)iterator.next();
			if(to.getNumero_tarjeta().equalsIgnoreCase(Formatter.removeSpaces(numeroTarjeta))){
				return String.valueOf(indice);
			}
			indice++;
		}
		return "-1";
	}
	private String getNumeroTarjetaByIndex(Collection<Cuenta_NipTO> lista,int index){
		try{
			List<Cuenta_NipTO> list= (List<Cuenta_NipTO>)lista;
			Cuenta_NipTO mediosPago= list.get(index);
			return mediosPago.getNumero_tarjeta();
		}catch(Exception e){
			return null;
		}
	}
}
