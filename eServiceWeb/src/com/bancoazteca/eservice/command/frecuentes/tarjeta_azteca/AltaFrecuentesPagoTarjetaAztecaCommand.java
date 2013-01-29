package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaRequestTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AltaFrecuentesPagoTarjetaAztecaCommand extends CommandBase{
	
	
	

	 public Response validacion(Session session) throws Exception{	 
			Response response = new Response();
			HuellaTO huellaTO = new HuellaTO();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			FrecuentesAztecaRequestTO frecuentesRequestTO=new FrecuentesAztecaRequestTO();
			frecuentesRequestTO.setUser(clienteTO.getUserName());
			FrecuentesAztecaResponseTO frecuentesAztecaResponseTO=new FrecuentesAztecaResponseTO();
			Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
			Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Tarjeta_FrecuenteTO>();
			try{
				ResourceFacadeSL resourceFacadeSL = getDelegate();
				AltaFrecuentesPagoTarjetaAztecaForm frecuentesForm = (AltaFrecuentesPagoTarjetaAztecaForm) getFormBean();
				
				if(session.getAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA) == null){				
					
					frecuentesAztecaResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);				

					session.addAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA, frecuentesAztecaResponseTO);
				}
				
				//Nuevo metodo de validacion que realiza el paso de verificacion
				
				frecuentesRequestTO.setTarjeta(frecuentesForm.getNumero_tarjeta());
				frecuentesRequestTO.setAlias(frecuentesForm.getNumero_tarjeta());
				
				//Nuevo metodo de validacion que realiza el paso de verificacion
				
				FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.validarAltaCuentasFrecuentes(frecuentesRequestTO);
				
		
				if(frecuentesResponseTO.getDispositivoHuellaTO() != null) {
					huellaTO.setLlave_publica( frecuentesResponseTO.getDispositivoHuellaTO().getLlavePublica() );
					huellaTO.setLongitud_huella( frecuentesResponseTO.getDispositivoHuellaTO().getLongitudHuella() );
				}
		
				//Alta de cuenta frecuente agregada
				
				Tarjeta_FrecuenteTO tarjeta_FrecuenteTO = new Tarjeta_FrecuenteTO();
				tarjeta_FrecuenteTO.setNumero_tarjeta(frecuentesResponseTO.getAltaTarjetaFrecuenteTO().getDestino());
				tarjeta_FrecuenteTO.setAlias_beneficiario(frecuentesForm.getAlias());
				coleccion_tarjetas_frecuentesTO.add(tarjeta_FrecuenteTO);
				
//				tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

				response.addAttribute(coleccion_tarjetas_frecuentesTO);
				response.addAttribute(huellaTO);

			} catch (EliteDataException e) {					
				buildErrorResponse(e, response);
			}		
			 return response;
}
	
	
	
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		AltaFrecuentesPagoTarjetaAztecaForm frecuentesPagoTarjetaAztecaForm = (AltaFrecuentesPagoTarjetaAztecaForm)getFormBean();
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			FrecuentesAztecaRequestTO frecuentesRequestTO=new FrecuentesAztecaRequestTO();
			frecuentesRequestTO.setUser(clienteTO.getUserName());
			
			if(session.getAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA) == null){				
				
				FrecuentesAztecaResponseTO consultaFrecuentesResponseTO = resourceFacadeSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);				

				session.addAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA, consultaFrecuentesResponseTO);
			}
						
			frecuentesRequestTO.setTarjeta(frecuentesPagoTarjetaAztecaForm.getNumero_tarjeta());
			frecuentesRequestTO.setAlias(frecuentesPagoTarjetaAztecaForm.getAlias());
			
			if (frecuentesPagoTarjetaAztecaForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
				frecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
				frecuentesRequestTO.setTokenCode(frecuentesPagoTarjetaAztecaForm.getClave_seguridad().toString());
			} else if (frecuentesPagoTarjetaAztecaForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
				frecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
				frecuentesRequestTO.setClave(frecuentesPagoTarjetaAztecaForm.getHuella_seguridad().toString());
			}
			
			
			FrecuentesAztecaResponseTO frecuentesResponseTO = resourceFacadeSL.AgregaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			Collection <Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Tarjeta_FrecuenteTO>();		
			
			//Alta de cuenta frecuente agregada
			
			Tarjeta_FrecuenteTO tarjeta_FrecuenteTO = new Tarjeta_FrecuenteTO();
			tarjeta_FrecuenteTO.setNumero_tarjeta(frecuentesResponseTO.getAltaTarjetaFrecuenteTO().getDestino());
			tarjeta_FrecuenteTO.setNombre_banco(frecuentesResponseTO.getAltaTarjetaFrecuenteTO().getBank());
			tarjeta_FrecuenteTO.setAlias_beneficiario(frecuentesResponseTO.getAltaTarjetaFrecuenteTO().getAlias());
			tarjeta_FrecuenteTO.setTipo_tarjeta(frecuentesResponseTO.getAltaTarjetaFrecuenteTO().getTipoTDC());
			tarjeta_FrecuenteTO.setEstado("La cuenta dada de alta será activada después de 30 minutos");
			coleccion_tarjetas_frecuentesTO.add(tarjeta_FrecuenteTO);
			
			Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
			tarjetas_FrecuentesTO.setColeccion_tarjetas_frecuentes(coleccion_tarjetas_frecuentesTO);

			response.addAttribute(tarjetas_FrecuentesTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	
}
