package com.bancoazteca.eservice.command.usuarios.fotounica;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FotoUnicaRequestTO;
import com.bancoazteca.elite.beans.FotoUnicaResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.command.base.beans.Foto_UnicaTO;
import com.bancoazteca.eservice.command.base.session.Session;

public class FotoUnicaCommand extends CommandBase {

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		FotoUnicaRequestTO fotoUnicaRequestTO = new FotoUnicaRequestTO();
		FotoUnicaResponseTO fotoUnicaResponseTO = new FotoUnicaResponseTO();
		Foto_UnicaTO foto_unicaTO = new Foto_UnicaTO();
		try{
			FotoUnicaForm fotoUnicaForm = (FotoUnicaForm) getFormBean();		
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			fotoUnicaRequestTO.setUser(clienteTO.getUserName());
			fotoUnicaRequestTO.setCuenta(fotoUnicaForm.getCuenta_usuario());
			fotoUnicaRequestTO.setSucursal(fotoUnicaForm.getSucursal_cuenta_usuario());
			
			//traer properties
			PropertiesService propertiesService = PropertiesService.getInstance();
			String pstrIp = propertiesService.getPropertie( FOTO_UNICA_DATOS, FOTO_UNICA_IP );
			String pstrServerPort = propertiesService.getPropertie( FOTO_UNICA_DATOS , FOTO_UNICA_PORT );
			fotoUnicaRequestTO.setPstrIP(pstrIp);
			fotoUnicaRequestTO.setPstrServerPort(pstrServerPort);
			
			fotoUnicaResponseTO = bean.consultaFotoUnica(fotoUnicaRequestTO);
			foto_unicaTO.setFoto(fotoUnicaResponseTO.getFotoUsuario());	
			foto_unicaTO.setEstatus_wsfotounica(fotoUnicaResponseTO.getStatus());
			response.addAttribute(foto_unicaTO);
		} catch (EliteDataException e) {
			buildErrorResponse( e, response );
		}
		return response;
	}
}
