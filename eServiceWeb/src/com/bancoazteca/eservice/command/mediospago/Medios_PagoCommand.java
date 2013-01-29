package com.bancoazteca.eservice.command.mediospago;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.CambioNipRequestTO;
import com.bancoazteca.elite.beans.CambioNipResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.MediosPagoTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Medios_Pago_RequestTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class Medios_PagoCommand extends CommandBase {
	private static final Logger log = Logger.getLogger(Medios_PagoCommand.class);
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		try{
			Medios_pagoTO medios_pago = medios((ClienteTO)session.getAttribute(CLIENTE_TO)) ;
			session.addAttribute(MEDIOS_PAGO_TARJETAS, medios_pago);
			response.addAttribute(medios_pago);
		}catch (EliteDataException e) {
			 buildErrorResponse(e, response);
		 }
		
		return response;
		
	}
	
	public Medios_pagoTO medios(ClienteTO clienteTO) throws Exception{		
		ResourceFacadeSL facade = null ;
		Medios_pagoTO medios_pago= new Medios_pagoTO();
		Collection<Detalle_TarjetaTO> auxiliar = new ArrayList<Detalle_TarjetaTO>(); 
		Medios_Pago_RequestTO medios_Pago_RequestTO = new Medios_Pago_RequestTO();
		Detalle_TarjetaTO medios_Pago_ResponseTO = null;
		medios_Pago_RequestTO.setUser(clienteTO.getUserName());
		
		facade = getDelegate();
		CambioNipRequestTO cambioNipRequestTO= new CambioNipRequestTO(); 
		cambioNipRequestTO.setUser(clienteTO.getUserName());
		CambioNipResponseTO cambioNipResponseTO= facade.getMediosPagoInvocation(cambioNipRequestTO);
		
		for(MediosPagoTO obj:cambioNipResponseTO.getTarjetasMediosPago()){
			medios_Pago_ResponseTO = new Detalle_TarjetaTO();
			medios_Pago_ResponseTO.setAlerta_celular(obj.getAlertaCelular());
			medios_Pago_ResponseTO.setNumero_cuenta(obj.getCuenta());
			medios_Pago_ResponseTO.setEstatus_tarjeta(obj.getEstatus());
			medios_Pago_ResponseTO.setLimite_disposicion_cajero(obj.getLimiteDisposicionCajero());
			medios_Pago_ResponseTO.setNumero_nip(obj.getNip());
			medios_Pago_ResponseTO.setNombre_cliente(obj.getNombreCliente());
			medios_Pago_ResponseTO.setNumero_celular(obj.getNumeroCelular());
			medios_Pago_ResponseTO.setNumero_tarjeta(obj.getNumeroTarjeta());
			medios_Pago_ResponseTO.setNombre_sucursal(obj.getSucursal());
			medios_Pago_ResponseTO.setTipo_cuenta(obj.getTipoCuenta());
			medios_Pago_ResponseTO.setPago_internet(obj.getPagoInternetEstatus());
			auxiliar.add(medios_Pago_ResponseTO);
		}
		medios_pago.setColeccion_medios_pago(auxiliar);
		
		return medios_pago;
	}
	

}
