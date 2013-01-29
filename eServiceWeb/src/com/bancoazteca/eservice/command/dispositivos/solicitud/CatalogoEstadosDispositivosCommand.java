package com.bancoazteca.eservice.command.dispositivos.solicitud;

import java.util.Collection;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoRequestTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Catalogos_DispositivosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class CatalogoEstadosDispositivosCommand extends CommandBase {

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		try{
			Catalogos_DispositivosTO catalogoTO = ( Catalogos_DispositivosTO ) session.getAttribute( LISTA_ESTADOS_DISPOSITIVO );
			if( catalogoTO == null ){
				Collection<String> listaEstados;
				Collection<String> listaMunicipios;
				ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
				SolicitudDispositivoRequestTO solicitudDispositivoRequestTO = new SolicitudDispositivoRequestTO();
				solicitudDispositivoRequestTO.setUser( clienteTO.getUserName() );
				solicitudDispositivoRequestTO.setModificarDireccion(true);
				solicitudDispositivoRequestTO.setSubmith( "MODIFICA_DIRECCION" );
				ResourceFacadeSL facadeSL = getDelegate();
				SolicitudDispositivoResponseTO solicitudResponseTO = facadeSL.getCatalogoEstadosDispositivos( solicitudDispositivoRequestTO );
				listaEstados= solicitudResponseTO.getListaEstados();
				listaMunicipios = solicitudResponseTO.getListaMunicipios();

				catalogoTO = new Catalogos_DispositivosTO();
				if( listaEstados != null && listaMunicipios != null ){
					catalogoTO.setColeccion_estados( listaEstados );
					catalogoTO.setColeccion_municipios( listaMunicipios );
					session.addAttribute( LISTA_ESTADOS_DISPOSITIVO, catalogoTO );
				}
			}
			response.addAttribute( catalogoTO );
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
}