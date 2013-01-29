package com.bancoazteca.eservice.command.pagoservicios.pagotarjetaazteca;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.Confirmacion_Envio_MailTO;
import com.bancoazteca.elite.beans.Confirmacion_Tarjeta_AztecaTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaResponseTO;
import com.bancoazteca.elite.beans.TarjetaCreditoAztecaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Envio_Mail_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_tarjeta_aztecaTO;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PagoTarjetaAztecaCommand extends CommandBase {

	
	public Response solicitud(Session session) throws Exception {
		
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO = new PagoTarjetaAztecaRequestTO();
			Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
			pagoTarjetaAztecaRequestTO.setUser(clienteTO.getUserName());			
			PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = resourceFacadeSL.solicitudPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			Collection<CuentaVO>  cuentas  = pagoTarjetaAztecaResponseTO.getCuentas();		
			Collection<Cuenta_CargoTO> coleccionCuentas = new ArrayList<Cuenta_CargoTO>();
	
			Cuenta_CargoTO cuenta_CargoTO=null;
					
			for(CuentaVO cuentaVO:cuentas)
			{	
				
				String subProduct = cuentaVO.getSubproducto();
				if(!Validator.isEmptyData(subProduct)&& !subProduct.equals("00") ){
					
					cuenta_CargoTO=new Cuenta_CargoTO();
					String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaVO.getNumero()));
					cuenta_CargoTO.setNumero_cuenta(cuentaFormateada);
					cuenta_CargoTO.setProducto(cuentaVO.getDescripcion());
					cuenta_CargoTO.setSaldo_disponible(cuentaVO.getDisponible());
					coleccionCuentas.add(cuenta_CargoTO);
					
				}			
			}
			
			Cuentas_CargoTO cuentas_CargoTO=new Cuentas_CargoTO();
			cuentas_CargoTO.setColeccion_cuentas(coleccionCuentas);
			response.addAttribute(cuentas_CargoTO);
			response.addAttribute(tarjetas_FrecuentesTO);
			session.addAttribute(CUENTAS_CARGO, coleccionCuentas);		
			return response;
	}
	
	
	@SuppressWarnings("unchecked")
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		PagoTarjetaAztecaForm forma = (PagoTarjetaAztecaForm)getFormBean();
		Pago_tarjeta_aztecaTO pago_tarjeta_aztecaTO=new Pago_tarjeta_aztecaTO();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		Collection<PagoTarjetaAztecaResponseTO> cuenta_cargo = null;
		synchronized (session) {
			cuenta_cargo = (Collection<PagoTarjetaAztecaResponseTO>)session.getAttribute(CUENTAS_CARGO);
		}
		Collection<Tarjeta_FrecuenteTO> tarjeta_frecuente = null;
		tarjeta_frecuente = (Collection<Tarjeta_FrecuenteTO>)session.getAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA);
		
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO = new PagoTarjetaAztecaRequestTO();
			pagoTarjetaAztecaRequestTO.setUser(clienteTO.getUserName());
			
			//Buscamos el Origen, que será el índice del número de cuenta
			
			pagoTarjetaAztecaRequestTO.setOrigen(""+getTarjetaIndex(cuenta_cargo, forma.getCuenta_cargo()));
			
			pagoTarjetaAztecaRequestTO.setTarjeta(forma.getNumero_tarjeta());
			pagoTarjetaAztecaRequestTO.setImporte(forma.getImporte());
			PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = resourceFacadeSL.validacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			
			
			CuentaTO cuentaTO= getAccountsPrdicate(clienteTO.getCuentas(), forma.getCuenta_cargo());
			
			Collection<TarjetaCreditoAztecaTO> tarjetasCredito = (Collection<TarjetaCreditoAztecaTO>)pagoTarjetaAztecaResponseTO.getTarjetasCredito();
			
			
			TarjetaCreditoAztecaTO tarjetaCreditoAztecaTO=null;
			for(TarjetaCreditoAztecaTO TarjetaCreditoAztecaTO:tarjetasCredito)
			{	
				tarjetaCreditoAztecaTO=new TarjetaCreditoAztecaTO();
				tarjetaCreditoAztecaTO.setNumero(TarjetaCreditoAztecaTO.getNumero());
				tarjetaCreditoAztecaTO.setBanco(TarjetaCreditoAztecaTO.getBanco());
				tarjetaCreditoAztecaTO.setTipoTarjeta(TarjetaCreditoAztecaTO.getTipoTarjeta());
				tarjetaCreditoAztecaTO.setTitular(TarjetaCreditoAztecaTO.getTitular());
				
			}
			
			
			//Datos del ordenante
			
			pago_tarjeta_aztecaTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			pago_tarjeta_aztecaTO.setCuenta_cargo(cuentaTO.getNumero());
			
			//Datos de la tarjeta
			
			pago_tarjeta_aztecaTO.setNumero_tarjeta(tarjetaCreditoAztecaTO.getNumero());
			pago_tarjeta_aztecaTO.setBanco_emisor(tarjetaCreditoAztecaTO.getBanco());
			pago_tarjeta_aztecaTO.setTipo_tarjeta(tarjetaCreditoAztecaTO.getTipoTarjeta());
			pago_tarjeta_aztecaTO.setTitular_tarjeta(tarjetaCreditoAztecaTO.getTitular());
			
			//Datos de la operacion
			
			pago_tarjeta_aztecaTO.setImporte(forma.getImporte());
			pago_tarjeta_aztecaTO.setComision("0.00");
			pago_tarjeta_aztecaTO.setIva_comision("0.00");
			pago_tarjeta_aztecaTO.setImporte_total(forma.getImporte());
			
			
			response.addAttribute(pago_tarjeta_aztecaTO);
			synchronized(session){
				session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS, forma);
				session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS_FORMATEADOS, pago_tarjeta_aztecaTO);
			}
			DispositivoHuellaTO tdispositivoHuellaTO=pagoTarjetaAztecaResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO=new HuellaTO();
			if(tdispositivoHuellaTO!=null){
			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());
			response.addAttribute(huellaTO);
			}
			session.addAttribute(Index,pagoTarjetaAztecaRequestTO.getOrigen());		
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {	
		Response response = new Response();
		PagoTarjetaAztecaForm forma = (PagoTarjetaAztecaForm)getFormBean();
		PagoTarjetaAztecaForm datos = (PagoTarjetaAztecaForm)session.getAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS);
		String indiceOrigen = (String) session.getAttribute(Index);
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO = new PagoTarjetaAztecaRequestTO();
			pagoTarjetaAztecaRequestTO.setUser(clienteTO.getUserName());
			pagoTarjetaAztecaRequestTO.setOrigen(indiceOrigen);
			pagoTarjetaAztecaRequestTO.setImporte(datos.getImporte());
			pagoTarjetaAztecaRequestTO.setTarjeta(datos.getNumero_tarjeta());
			pagoTarjetaAztecaRequestTO.setCuenta(datos.getCuenta_cargo());
			pagoTarjetaAztecaRequestTO.setComision(datos.getComision());
			

			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
				pagoTarjetaAztecaRequestTO.setOptionDispositive(OPCION_TOKEN);
				pagoTarjetaAztecaRequestTO.setTokenCode(forma.getClave_seguridad().toString());
			} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
				pagoTarjetaAztecaRequestTO.setOptionDispositive(OPCION_HUELLA);
				pagoTarjetaAztecaRequestTO.setClave(forma.getHuella_seguridad().toString());
			}
			
			
			PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO  = resourceFacadeSL.confirmacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);	
				
			Cuenta_CargoTO cuenta_CargoTO=null;
			for(CuentaTransaccionesTO cuentas:pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getCuentas())
				{	
					
					String indexCuentaCargo = cuentas.getIndex();
					if(indexCuentaCargo.equals(indiceOrigen)){		
						Collection<CuentaVO>  saldoDisponible  = pagoTarjetaAztecaResponseTO.getCuentas();		
						Collection<Cuenta_CargoTO> coleccionCuentas = new ArrayList<Cuenta_CargoTO>();
				
						for(CuentaVO cuentaVO:saldoDisponible)
						{	
						
							String subProduct = cuentaVO.getSubproducto();
							if(!Validator.isEmptyData(subProduct)&& !subProduct.equals("00") ){
								
								cuenta_CargoTO=new Cuenta_CargoTO();
								cuenta_CargoTO.setSaldo_disponible(cuentaVO.getDisponible());
								coleccionCuentas.add(cuenta_CargoTO);
								
							}			
						}
						String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentas.getNumero()));
						cuenta_CargoTO.setNumero_cuenta(cuentaFormateada);
						
					}			
				}
			
			Confirmacion_Tarjeta_AztecaTO confirmacion_Tarjeta_AztecaTO=new Confirmacion_Tarjeta_AztecaTO();
			
			//Datos del cargo
			
			confirmacion_Tarjeta_AztecaTO.setCuenta_cargo(cuenta_CargoTO.getNumero_cuenta());
			confirmacion_Tarjeta_AztecaTO.setDisponible(cuenta_CargoTO.getSaldo_disponible());
			confirmacion_Tarjeta_AztecaTO.setReferencia(pagoTarjetaAztecaResponseTO.getMovimientoTarjetaTO().getNumero());
			confirmacion_Tarjeta_AztecaTO.setImporte_cargo(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporte().toString());
			confirmacion_Tarjeta_AztecaTO.setComision(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getComision().toString(),2));
			confirmacion_Tarjeta_AztecaTO.setIva_comision(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString().equals("0E-55")? "0.00":Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString(),2));
			confirmacion_Tarjeta_AztecaTO.setImporte_total(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString(),2));
			confirmacion_Tarjeta_AztecaTO.setFecha_operacion(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFechaAplicacion().toString());
			confirmacion_Tarjeta_AztecaTO.setFolio_operacion(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFolio().toString());
			
			//Datos del abono
			
			confirmacion_Tarjeta_AztecaTO.setNumero_tarjeta_credito(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getNumero().toString());
			confirmacion_Tarjeta_AztecaTO.setBanco_emisor(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getBanco().toString());
			confirmacion_Tarjeta_AztecaTO.setTipo_tarjeta(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getTipoTarjeta().toString());
			confirmacion_Tarjeta_AztecaTO.setImporte_pago(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString(),2));
			confirmacion_Tarjeta_AztecaTO.setTitular(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getTitular().toString());
		
			
			synchronized(session){
				response.addAttribute(confirmacion_Tarjeta_AztecaTO);
			}
			super.updateBalance(session);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	private int getTarjetaIndex(Collection<PagoTarjetaAztecaResponseTO> tarjetas, String cuentaCargo){
		Iterator<?> iterator = tarjetas.iterator();
		int indice = 0;
		while(iterator.hasNext()){
			Cuenta_CargoTO to = (Cuenta_CargoTO)iterator.next();
			if(to.getNumero_cuenta().equalsIgnoreCase(cuentaCargo)){
				return indice;
			}
			indice++;
		}
		return -1;
	}
	

	
	public Response enviomail(Session session) throws Exception {	
		Response response = new Response();

		try {
			ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
			PagoTarjetaAztecaForm forma = (PagoTarjetaAztecaForm)getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO = new PagoTarjetaAztecaRequestTO();
			Confirmacion_Envio_MailTO confirmacion_Envio_MailTO=new Confirmacion_Envio_MailTO();	
			pagoTarjetaAztecaRequestTO.setMensaje(forma.getMensaje());
			pagoTarjetaAztecaRequestTO.setEmail(forma.getEmail());
			pagoTarjetaAztecaRequestTO.setUser(clienteTO.getUserName());
			 
			PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO=resourceFacadeSL.envioMail(pagoTarjetaAztecaRequestTO);
		
			if(pagoTarjetaAztecaResponseTO!=null){
			confirmacion_Envio_MailTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			confirmacion_Envio_MailTO.setNumero_tarjeta_credito(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getNumero().toString());
			confirmacion_Envio_MailTO.setBanco_emisor(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getBanco().toString());
			confirmacion_Envio_MailTO.setTipo_tarjeta(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getTipoTarjeta().toString());
			
			confirmacion_Envio_MailTO.setTitular_tarjeta_credito(pagoTarjetaAztecaResponseTO.getConfirmacionTarjetaCredito().getTitular().toString());
			confirmacion_Envio_MailTO.setImporte_operacion(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString(),2));
			confirmacion_Envio_MailTO.setComision_operacion(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getComision().toString(),2));
			
			confirmacion_Envio_MailTO.setIva_comision(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString().equals("0E-55")? "0.00":Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString(),2));
			confirmacion_Envio_MailTO.setImporte_total_operacion(Formatter.getMontoTruncado(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString(),2));
			confirmacion_Envio_MailTO.setFecha_operacion(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFechaAplicacion().toString());
			confirmacion_Envio_MailTO.setFolio(pagoTarjetaAztecaResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFolio().toString());
			confirmacion_Envio_MailTO.setMensaje(pagoTarjetaAztecaResponseTO.getMensaje());
			
			response.addAttribute(confirmacion_Envio_MailTO);
			
			}
			
			
			
			
		} catch (EliteDataException e) {					
			buildErrorResponse(e, response);
		}	
		return response;
	}
	
	
}
