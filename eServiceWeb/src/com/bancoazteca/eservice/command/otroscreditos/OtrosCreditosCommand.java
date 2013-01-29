package com.bancoazteca.eservice.command.otroscreditos;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CreditosRequestTO;
import com.bancoazteca.elite.beans.CreditosResponseTO;
import com.bancoazteca.elite.beans.DetallePagoCreditoTO;
import com.bancoazteca.elite.beans.DetallePagoOtrosCreditosTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Detalle_Historal_CreditoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_Otros_CreditosTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_pago_creditoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class OtrosCreditosCommand extends CommandBase{
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		OtrosCreditosForm forma = (OtrosCreditosForm) getFormBean();
		Detalle_Otros_CreditosTO otros_Creditos = new Detalle_Otros_CreditosTO();
		DecimalFormat df = new DecimalFormat("$###,###,###,###.00");
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			CreditosRequestTO creditosRequestTO = new CreditosRequestTO();
			creditosRequestTO.setUser(clienteTO.getUserName());
			creditosRequestTO.setMethod(forma.getMethod_credito());
			creditosRequestTO.setCredito(forma.getNumero_credito());

			CreditosResponseTO creditosResponse = resourceFacadeSL.detailOtherCredits(creditosRequestTO);

			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMM yyyy");
			
			if(forma.getMethod_credito().equalsIgnoreCase("proyeccion")){
				Detalle_Historal_CreditoTO detalle;
				List<Detalle_Historal_CreditoTO> listaDetalles= new ArrayList<Detalle_Historal_CreditoTO>();
				for(DetallePagoCreditoTO obj: creditosResponse.getDetallesCredito() ){
					detalle= new Detalle_Historal_CreditoTO();

					detalle.setAmortizacion_capital(df.format(obj.getAmortizacionCapital()));
					detalle.setDias_calculo(obj.getDiasCalculo());
					
					Date d=simpleDateFormat.parse(obj.getFechaFinCalculo());
					detalle.setFecha_fin_calculo(dateFormat.format(d));
					
					d=simpleDateFormat.parse(obj.getFechaInicioCalculo());
					detalle.setFecha_inicio_calculo(dateFormat.format(d));
					
					detalle.setIntereses(df.format(obj.getIntereses()));
					detalle.setIva_intereses(df.format(obj.getIvaIntereses()));
					detalle.setNum_pago(obj.getNumPago());
					detalle.setPago_a_realizar(df.format(obj.getPagoARealizar()));
					detalle.setSaldo_insoluto(df.format(obj.getSaldoInsoluto()));
					detalle.setSeguro_danios(df.format(obj.getSeguroDanios()));
					detalle.setSeguro_vida(df.format(obj.getSeguroVida()));
					detalle.setTasa(obj.getTasa()+"%");

					listaDetalles.add(detalle);
				}
				otros_Creditos.setColleccion_historial(listaDetalles);
				session.addAttribute(DETALLE_OTROS_CREDITOS, otros_Creditos );
				response.addAttribute(otros_Creditos);
			}else if(forma.getMethod_credito().equalsIgnoreCase("pagosRealizados")){
				
				
				Detalle_pago_creditoTO detalle_pago_creditoTO=null;
				Collection<DetallePagoOtrosCreditosTO> collectionPagos=creditosResponse.getDetallePagosCredito();
				Collection<Detalle_pago_creditoTO>coleccionPagos=new ArrayList<Detalle_pago_creditoTO>();
				
				for (DetallePagoOtrosCreditosTO detallePagoOtrosCreditosTO : collectionPagos) {
					
					detalle_pago_creditoTO=new Detalle_pago_creditoTO();
					detalle_pago_creditoTO.setAmortizacion_capital(detallePagoOtrosCreditosTO.getAmortizacionCapital());
					
					Date d=simpleDateFormat.parse(detallePagoOtrosCreditosTO.getFechaPago());
					detalle_pago_creditoTO.setFecha_pago(dateFormat.format(d));
					
					detalle_pago_creditoTO.setId_tipo_pago(detallePagoOtrosCreditosTO.getIdTipoPago());
					detalle_pago_creditoTO.setIntereses(detallePagoOtrosCreditosTO.getIntereses());
					detalle_pago_creditoTO.setIntereses_moratorios(detallePagoOtrosCreditosTO.getInteresesMoratorios());
					detalle_pago_creditoTO.setIva_intereses(detallePagoOtrosCreditosTO.getIvaIntereses());
					detalle_pago_creditoTO.setIva_moratorio(detallePagoOtrosCreditosTO.getIvaMoratorios());
					detalle_pago_creditoTO.setNum_pago(detallePagoOtrosCreditosTO.getNumPago());
					detalle_pago_creditoTO.setPago(detallePagoOtrosCreditosTO.getPago());
					detalle_pago_creditoTO.setSaldo_insoluto(detallePagoOtrosCreditosTO.getSaldoInsoluto());
					detalle_pago_creditoTO.setSeguro_danios(detallePagoOtrosCreditosTO.getSeguroDanios());
					detalle_pago_creditoTO.setSeguro_vida(detallePagoOtrosCreditosTO.getSeguroVida());
					detalle_pago_creditoTO.setTasa(detallePagoOtrosCreditosTO.getTasa());
					detalle_pago_creditoTO.setTasa_moratoria(detallePagoOtrosCreditosTO.getTasaMoratoria());
					coleccionPagos.add(detalle_pago_creditoTO);
					
				}
				otros_Creditos.setColleccion_pagos(coleccionPagos);
				response.addAttribute(otros_Creditos);				
			}
			
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
}
