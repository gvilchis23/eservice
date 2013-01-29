package com.bancoazteca.eservice.command.mediospago.dispocicionefectivocajero;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroRequestTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroResponseTO;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Disposicion_EfectivoTO;
import com.bancoazteca.eservice.command.base.beans.Disposicion_Efectivo_ResponseTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.mediospago.Medios_PagoCommand;
import com.bancoazteca.eservice.command.response.Response;

public class DispocicionEfectivoCajeroCommand extends CommandBase {

	public Response solicitud(Session session)throws SessionExpiredException,CuentasException,ServiceLocatorException,Exception{
		Response response=new Response();
		
		DisposicionEfectivoCajeroRequestTO cajeroRequestTO=new DisposicionEfectivoCajeroRequestTO();
		
		try{
			
			DisposicionEfectivoCajeroForm form=(DisposicionEfectivoCajeroForm)getFormBean();			
			
			
			Medios_pagoTO mediosPagoTO=(Medios_pagoTO)session.getAttribute(Medios_PagoCommand.MEDIOS_PAGO_TARJETAS);
			
			if(mediosPagoTO==null){
				Medios_PagoCommand mediosPagoCommand=new Medios_PagoCommand();
				mediosPagoCommand.solicitud(session);
				mediosPagoTO=(Medios_pagoTO)session.getAttribute(Medios_PagoCommand.MEDIOS_PAGO_TARJETAS);
			}
			
			int i=0;
			for(Detalle_TarjetaTO tarjetaTO :mediosPagoTO.getColeccion_medios_pago()){
				if(tarjetaTO.getNumero_tarjeta().equals(form.getNumero_tarjeta())){
					cajeroRequestTO.setIndiceTarjeta(i);
					break;
				}
				i++;
			}
			ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
			cajeroRequestTO.setUserName(clienteTO.getUserName());
			DisposicionEfectivoCajeroResponseTO cajeroResponseTO = getDelegate().getSolicitudDisposicionEfectivoCajero(cajeroRequestTO);
		
			Disposicion_EfectivoTO disposicionEfectivoTO=new Disposicion_EfectivoTO();
			disposicionEfectivoTO.setNumero_tarjeta(cajeroResponseTO.getNumeroTarjeta());
			response.addAttribute(disposicionEfectivoTO);
			
			
			
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException, response);
		}
		
		return response;
	}
	
	public Response validacion(Session session)throws Throwable{
		
		Response response=new Response();
		
		DisposicionEfectivoCajeroForm form=(DisposicionEfectivoCajeroForm)getFormBean();
		DisposicionEfectivoCajeroRequestTO cajeroRequestTO=new DisposicionEfectivoCajeroRequestTO();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		cajeroRequestTO.setMonto(form.getMonto());
		cajeroRequestTO.setUserName(clienteTO.getUserName());
		DisposicionEfectivoCajeroResponseTO cajeroResponseTO=null;
		
		try{
			
			cajeroResponseTO=getDelegate().getValidacionDisposicionEfectivoCajero(cajeroRequestTO);
			
			Disposicion_Efectivo_ResponseTO validacionTO=new Disposicion_Efectivo_ResponseTO();
			
			validacionTO.setMonto_actual(cajeroResponseTO.getActual());
			validacionTO.setMonto_anteriror(cajeroResponseTO.getMontoAnterior());
			validacionTO.setNumero_tarjeta(cajeroResponseTO.getNumeroTarjeta());
			
			HuellaTO huella = new HuellaTO();
	   	    huella.setLlave_publica(cajeroResponseTO.getLlavePublica());
	   		huella.setLongitud_huella(cajeroResponseTO.getLongitudHuella());
	   		response.addAttribute(huella);
	   		response.addAttribute(validacionTO);
	   		
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session)throws Throwable{
		Response response=new Response();
		
		
		DisposicionEfectivoCajeroRequestTO cajeroRequestTO=new DisposicionEfectivoCajeroRequestTO();
		
		DisposicionEfectivoCajeroForm cajeroForm=(DisposicionEfectivoCajeroForm)getFormBean();
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		if(cajeroForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
			cajeroRequestTO.setOptionDispositive(OPCION_TOKEN);
			cajeroRequestTO.setTokenCode(cajeroForm.getClave_seguridad().toString());
		}else{
			cajeroRequestTO.setOptionDispositive(OPCION_TOKEN);
			cajeroRequestTO.setClave(cajeroForm.getHuella_seguridad());
		}
		cajeroRequestTO.setUserName(clienteTO.getUserName());
		
		try{
			
			Disposicion_Efectivo_ResponseTO efectivoResponseTO=new Disposicion_Efectivo_ResponseTO();
			
			DisposicionEfectivoCajeroResponseTO responseTO = getDelegate().getEjecucionDisposicionEfectivoCajero(cajeroRequestTO);
			
			efectivoResponseTO.setMonto_actual(responseTO.getActual());
			efectivoResponseTO.setMonto_anteriror(responseTO.getMontoAnterior());
			efectivoResponseTO.setNumero_tarjeta(responseTO.getNumeroTarjeta());
			
			response.addAttribute(efectivoResponseTO);
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException, response);
		}
		
		return response;
	}
	
	
}