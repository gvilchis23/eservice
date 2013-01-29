package com.bancoazteca.eservice.command.movimientos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.CargosPorAplicarInfiniteTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CreditosMovimientosTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosRequestTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosResponseTO;
import com.bancoazteca.elite.beans.TarjetaBaseAlnova;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Detalle_Movimiento_InfiniteTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_cargos_aplicarTO;
import com.bancoazteca.eservice.command.base.beans.GrupoMovimientosTO;
import com.bancoazteca.eservice.command.base.beans.Movimientos_InfiniteTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class MovimientosInfiniteCommand extends CommandBase{
	
	Logger log=Logger.getLogger(MovimientosCommand.class);
	
	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);			
			
			InfiniteMovimientosRequestTO infiniteMovimientosRequestTO = new InfiniteMovimientosRequestTO();
			MovimientosForm movimientosForm = (MovimientosForm)getFormBean();
			
			TarjetaBaseAlnova titular = getIndiceTarjeta(movimientosForm.getNumero_tarjeta(), clienteTO);
			if(titular == null)
			{
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+": la tarjeta "+ movimientosForm.getNumero_tarjeta() + " no existe.", null);
				return response;
			}
			infiniteMovimientosRequestTO.setUser(clienteTO.getUserName());
			infiniteMovimientosRequestTO.setIndexTarjeta("0");
			ResourceFacadeSL facadeSL = getDelegate();	
			InfiniteMovimientosResponseTO infiniteMovimientosResponseTO = facadeSL.getInfiniteDetalleMovimientos(infiniteMovimientosRequestTO);
			
			Movimientos_InfiniteTO respuesta=new Movimientos_InfiniteTO();
			
			BigDecimal cargosPorAplicar=new BigDecimal("0");
			Collection<Detalle_cargos_aplicarTO>coleccionCargosAplicar=new ArrayList<Detalle_cargos_aplicarTO>();
			if(infiniteMovimientosResponseTO.getCargosPorAplicar()!=null && !infiniteMovimientosResponseTO.getCargosPorAplicar().isEmpty()){
				Collection<CargosPorAplicarInfiniteTO> lista=infiniteMovimientosResponseTO.getCargosPorAplicar();	
				for (CargosPorAplicarInfiniteTO cargosPorAplicarInfiniteTO : lista) {
					cargosPorAplicar.add(cargosPorAplicarInfiniteTO.getMonto());
					Detalle_cargos_aplicarTO detalle_cargos_aplicar=new Detalle_cargos_aplicarTO();
					detalle_cargos_aplicar.setDescripcion(cargosPorAplicarInfiniteTO.getDescrpcion());
					detalle_cargos_aplicar.setFecha(cargosPorAplicarInfiniteTO.getFechaOperacion());
					detalle_cargos_aplicar.setMonto(cargosPorAplicarInfiniteTO.getMonto().doubleValue()+"");
					coleccionCargosAplicar.add(detalle_cargos_aplicar);
				}
			}
			respuesta.setColeccion_cargos_aplicar(coleccionCargosAplicar);
			respuesta.setCredito_autorizado(titular.getLimiteCredito().doubleValue()+"");
			respuesta.setCredito_disponible(titular.getCreditoDisponible().doubleValue()+"");
			respuesta.setCredito_usado(titular.getCreditoUsado().doubleValue()+"");
			if(titular.getFechaDeCorte()!=null){
				respuesta.setFecha_corte(titular.getFechaDeCorte());
			}else{
				respuesta.setFecha_corte(infiniteMovimientosResponseTO.getFechaFinal());
			}
			respuesta.setFecha_inicial(infiniteMovimientosResponseTO.getFechaInicial());
			respuesta.setFecha_pago(titular.getFechaLimiteDePago_Nueva());
			respuesta.setNumero_tarjeta(titular.getNumeroTarjeta());
			respuesta.setPago_no_intereses(titular.getPagoParaNoGenerarIntereses().doubleValue()+"");
			respuesta.setSaldo_corte(titular.getSaldoAlCorte().doubleValue()+"");
			respuesta.setSaldo_actual(titular.getSaldoAlCorte().doubleValue()+"");
			respuesta.setSaldo_fecha_inicial(infiniteMovimientosResponseTO.getSaldoAnterior().doubleValue()+"");
			respuesta.setFecha_fin_corte(infiniteMovimientosResponseTO.getFechaFinalMas());
			respuesta.setCargos_por_aplicar(titular.getRetenido().doubleValue()+"");
			respuesta.setCredito_utilizado(titular.getRetenido().add(titular.getCreditoUsado()).doubleValue()+"");
			
			GrupoMovimientosTO mas_compras_disposiciones_actual=new GrupoMovimientosTO();
			GrupoMovimientosTO menos_pagos_depositos_actual=new GrupoMovimientosTO();
			
			mas_compras_disposiciones_actual.setFecha_final(infiniteMovimientosResponseTO.getFechaHoy());
			mas_compras_disposiciones_actual.setFecha_inicial(infiniteMovimientosResponseTO.getFechaFinalMas());
			Detalle_Movimiento_InfiniteTO detalle_Movimiento_InfiniteTO=new Detalle_Movimiento_InfiniteTO();
			if(infiniteMovimientosResponseTO.getSaldoActualTO()!=null){
				mas_compras_disposiciones_actual.setTotal_movimientos(infiniteMovimientosResponseTO.getSaldoActualTO().getTitular().getCargos().doubleValue()+"");

				menos_pagos_depositos_actual.setFecha_final(infiniteMovimientosResponseTO.getFechaFinal());
				menos_pagos_depositos_actual.setFecha_inicial(infiniteMovimientosResponseTO.getFechaInicial());
				menos_pagos_depositos_actual.setTotal_movimientos(infiniteMovimientosResponseTO.getSaldoActualTO().getTitular().getAbonos().doubleValue()+"");
				Collection<CreditosMovimientosTO>listaCreditos=infiniteMovimientosResponseTO.getSaldoActualTO().getTitular().getTitular();
				
				Collection<Detalle_Movimiento_InfiniteTO>masGastosActual=new ArrayList<Detalle_Movimiento_InfiniteTO>();
				Collection<Detalle_Movimiento_InfiniteTO>menosPagosActual=new ArrayList<Detalle_Movimiento_InfiniteTO>();
								
				for (CreditosMovimientosTO creditosMovimientosTO : listaCreditos) {
					
					detalle_Movimiento_InfiniteTO=new Detalle_Movimiento_InfiniteTO();
					detalle_Movimiento_InfiniteTO.setEstablecimiento(creditosMovimientosTO.getNegocio());
					detalle_Movimiento_InfiniteTO.setFecha_aplicacion(creditosMovimientosTO.getFechaDeAplicacion());
					detalle_Movimiento_InfiniteTO.setFecha_operacion(creditosMovimientosTO.getFechaDeOperacion());
					if(creditosMovimientosTO.getMonedaOriginal()!=null){
						detalle_Movimiento_InfiniteTO.setMonto_moneda_original(creditosMovimientosTO.getMonedaOriginal().doubleValue()+"");
					}
					if(creditosMovimientosTO.getCargos()!=null ){
						detalle_Movimiento_InfiniteTO.setImporte(creditosMovimientosTO.getCargos().doubleValue()+"");
						masGastosActual.add(detalle_Movimiento_InfiniteTO);
					}else{
						detalle_Movimiento_InfiniteTO.setImporte(creditosMovimientosTO.getAbonos().doubleValue()+"");
						menosPagosActual.add(detalle_Movimiento_InfiniteTO);
					}
					
				}
				mas_compras_disposiciones_actual.setColeccion_movimientos(masGastosActual);
				menos_pagos_depositos_actual.setColeccion_movimientos(menosPagosActual);
				
				respuesta.setMas_compras_disposiciones_actual(mas_compras_disposiciones_actual);
				respuesta.setMenos_pagos_depositos_actual(menos_pagos_depositos_actual);
				
			}
			
			
			GrupoMovimientosTO mas_compras_disposiciones_corte=new GrupoMovimientosTO();
			GrupoMovimientosTO menos_pagos_depositos_corte=new GrupoMovimientosTO();
			
			mas_compras_disposiciones_corte.setFecha_final(infiniteMovimientosResponseTO.getFechaFinal());
			mas_compras_disposiciones_corte.setFecha_inicial(infiniteMovimientosResponseTO.getFechaInicial());
			
			if(infiniteMovimientosResponseTO.getSaldoCorteTO()!=null){
				mas_compras_disposiciones_corte.setTotal_movimientos(infiniteMovimientosResponseTO.getSaldoCorteTO().getTitular().getCargos().doubleValue()+"");
				menos_pagos_depositos_actual.setFecha_final("antes de la fecha limite");
				menos_pagos_depositos_actual.setFecha_inicial("antes de la fecha limite");
				menos_pagos_depositos_corte.setTotal_movimientos(infiniteMovimientosResponseTO.getSaldoCorteTO().getTitular().getAbonos().doubleValue()+"");
				
				Collection<CreditosMovimientosTO> listaCreditosAlCorte = infiniteMovimientosResponseTO.getSaldoCorteTO().getTitular().getTitular();
				
				Collection<Detalle_Movimiento_InfiniteTO>masGastosCorte=new ArrayList<Detalle_Movimiento_InfiniteTO>();
				Collection<Detalle_Movimiento_InfiniteTO>menosPagosCorte=new ArrayList<Detalle_Movimiento_InfiniteTO>();
				
				for (CreditosMovimientosTO creditosMovimientosTO : listaCreditosAlCorte) {
					
					detalle_Movimiento_InfiniteTO=new Detalle_Movimiento_InfiniteTO();
					detalle_Movimiento_InfiniteTO.setEstablecimiento(creditosMovimientosTO.getNegocio());
					detalle_Movimiento_InfiniteTO.setFecha_aplicacion(creditosMovimientosTO.getFechaDeAplicacion());
					detalle_Movimiento_InfiniteTO.setFecha_operacion(creditosMovimientosTO.getFechaDeOperacion());
					if(creditosMovimientosTO.getMonedaOriginal()!=null){
						detalle_Movimiento_InfiniteTO.setMonto_moneda_original(creditosMovimientosTO.getMonedaOriginal().doubleValue()+"");
					}
					if(creditosMovimientosTO.getCargos()!=null ){
						detalle_Movimiento_InfiniteTO.setImporte(creditosMovimientosTO.getCargos().doubleValue()+"");
						masGastosCorte.add(detalle_Movimiento_InfiniteTO);
					}else{
						detalle_Movimiento_InfiniteTO.setImporte(creditosMovimientosTO.getAbonos().doubleValue()+"");
						menosPagosCorte.add(detalle_Movimiento_InfiniteTO);
					}
				}
				
				mas_compras_disposiciones_corte.setColeccion_movimientos(masGastosCorte);
				menos_pagos_depositos_corte.setColeccion_movimientos(menosPagosCorte);
				
				respuesta.setMas_compras_disposiciones_corte(mas_compras_disposiciones_corte);
				respuesta.setMenos_pagos_depositos_corte(menos_pagos_depositos_corte);
			}
						
			response.addAttribute(respuesta);
			
			
		}catch(EliteDataException e)
		{
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	private TarjetaBaseAlnova getIndiceTarjeta(String numeroTarjeta, ClienteTO tarjetasInfinite){
		int indice = 0;
		for(TarjetaBaseAlnova tarjetaBaseAlnova: tarjetasInfinite.getTarjetasInifite()){
			if(tarjetaBaseAlnova.getNumeroTarjeta().equals(numeroTarjeta)){
				tarjetaBaseAlnova.setIndiceTarjeta(String.valueOf(indice));
				return tarjetaBaseAlnova;
			}			
			indice++;
		}		
		for(TarjetaBaseAlnova tarjetaBaseAlnova: tarjetasInfinite.getTarjetasBaseAlnova()){
			if(tarjetaBaseAlnova.getNumeroTarjeta().equals(numeroTarjeta)){
				tarjetaBaseAlnova.setIndiceTarjeta(String.valueOf(indice));
				return tarjetaBaseAlnova;
			}			
			indice++;
		}
		for(TarjetaBaseAlnova tarjetaBaseAlnova: tarjetasInfinite.getGoldenCards()){
			if(tarjetaBaseAlnova.getNumeroTarjeta().equals(numeroTarjeta)){
				tarjetaBaseAlnova.setIndiceTarjeta(String.valueOf(indice));
				return tarjetaBaseAlnova;
			}			
			indice++;
		}
		return null;
	}
}