package com.bancoazteca.eservice.command.dispositivos.rastreopedido;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.RastreoPedidoRequestTO;
import com.bancoazteca.elite.beans.RastreoPedidoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Rastreo_Pedido_DispositivoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class RastreoPedidoCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(RastreoPedidoCommand.class);

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		RastreoPedidoForm forma = (RastreoPedidoForm) getFormBean();
		RastreoPedidoRequestTO rastreoPedidoRequestTO = new RastreoPedidoRequestTO();
		rastreoPedidoRequestTO.setUser(clienteTO.getUserName());
		rastreoPedidoRequestTO.setNumeroPedido(forma.getNumero_pedido());

		try {
			ResourceFacadeSL facadeSL = getDelegate();
			RastreoPedidoResponseTO rastreoPedidoResponseTO = facadeSL.rastreoPedido(rastreoPedidoRequestTO);
			
			Rastreo_Pedido_DispositivoTO rastreoPedidoTO = new Rastreo_Pedido_DispositivoTO();
			rastreoPedidoTO.setNumero_pedido(rastreoPedidoResponseTO.getNumPedido());
			rastreoPedidoTO.setConcepto(rastreoPedidoResponseTO.getConcepto());
			rastreoPedidoTO.setDireccion(rastreoPedidoResponseTO.getDireccion());
			rastreoPedidoTO.setEstatus_pedido(rastreoPedidoResponseTO.getEstatus());
			rastreoPedidoTO.setFecha_solicitud(rastreoPedidoResponseTO.getFecha());
			rastreoPedidoTO.setNumero_guia_dhl(rastreoPedidoResponseTO.getNumeroGuiaDHL());
			rastreoPedidoTO.setNombre_primera_persona_autorizada(rastreoPedidoResponseTO.getPersona1());
			rastreoPedidoTO.setNombre_segunda_persona_autorizada(rastreoPedidoResponseTO.getPersona2());
			
			response.addAttribute(rastreoPedidoTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}	

		return response;
	}
}
