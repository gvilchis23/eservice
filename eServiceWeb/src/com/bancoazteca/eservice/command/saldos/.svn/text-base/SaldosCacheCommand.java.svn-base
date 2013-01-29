package com.bancoazteca.eservice.command.saldos;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.beans.TarjetaBaseAlnova;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.CuentaTO;
import com.bancoazteca.eservice.command.base.beans.CuentasTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_OperacionTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_Operaciones_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.TarjetasTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.cuentas.mercadodinero.CuentaMercadoDineroCommand;
import com.bancoazteca.eservice.command.response.Response;

public class SaldosCacheCommand  extends CommandBase {
	
	public static final Logger log = Logger.getLogger(SaldosCacheCommand.class);
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		ResourceFacadeSL facadeSL = getDelegate();
		LoginResponseTO loginResponseTO = null;	
					
		LoginRequestTO loginRequestTO = new LoginRequestTO();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		String userName = clienteTO.getUserName();
		loginRequestTO.setUser(userName);		
		
		loginResponseTO = facadeSL.getCuentasUsuarioParalelo(loginRequestTO);
		
		clienteTO = loginResponseTO.getClienteTO();
		clienteTO.setUserName(userName);
		
		CuentasTO cuentasTO = new CuentasTO();				
		Collection <CuentaTO> cuentaCollectionTO = new ArrayList <CuentaTO>();
		
//		Collection<com.bancoazteca.elite.beans.CuentaTO> cuentasActuales=new ArrayList<com.bancoazteca.elite.beans.CuentaTO>();
		if( clienteTO.getCuentas() != null ){
			for(com.bancoazteca.elite.beans.CuentaTO cuentaEliteTO: clienteTO.getCuentas()){
				CuentaTO cuentaTO = new CuentaTO();
				
//				double retenido = getCantidad(cuentaEliteTO.getRetenido());
//				double disponible = getCantidad(cuentaEliteTO.getDisponible());
//				double saldoTotal = retenido + disponible;
				
				cuentaTO.setNumero_cuenta(cuentaEliteTO.getCuentaFormateada().replace(" ", ""));
				
				cuentaTO.setCuenta_clabe(cuentaEliteTO.getClabe());
//				cuentaTO.setSaldo_retenido(String.valueOf(retenido));
//				cuentaTO.setSaldo_disponible(String.valueOf(disponible));
//				cuentaTO.setSaldo_total(String.valueOf(saldoTotal));
				cuentaTO.setSaldo_total(String.valueOf(cuentaEliteTO.getBalance()));
				cuentaTO.setSaldo_retenido(String.valueOf(cuentaEliteTO.getRetenido()));
				cuentaTO.setSaldo_disponible(String.valueOf(cuentaEliteTO.getDisponible()));
				cuentaTO.setProducto(cuentaEliteTO.getDescripcion());
				
				
				cuentaCollectionTO.add(cuentaTO);
				
				
				
//				cuentaEliteTO.setBalance(saldoTotal);
//				cuentasActuales.add(cuentaEliteTO);
				
			}
		}
//		clienteTO.setCuentas(cuentasActuales);
		
		
		Collection<com.bancoazteca.elite.beans.CreditoTO> creditos= clienteTO.getCreditos();
		if (creditos != null) {	
			Collection<com.bancoazteca.eservice.command.base.beans.CreditoTO> creditosColeccion= new ArrayList<com.bancoazteca.eservice.command.base.beans.CreditoTO>();			
			for(com.bancoazteca.elite.beans.CreditoTO credit : clienteTO.getCreditos()){
				com.bancoazteca.eservice.command.base.beans.CreditoTO credito_TO = new com.bancoazteca.eservice.command.base.beans.CreditoTO();
				credito_TO.setEstatus(credit.getEstatus());
				credito_TO.setFecha_proximo_pago(Formatter.formatoFecha(credit.getFechaProxPago()));
				credito_TO.setIndex(credit.getIndex());
				credito_TO.setNumero_credito(credit.getNumCredito());
				credito_TO.setNumero_cuenta(credit.getNumCuenta());
				credito_TO.setPagos_pendientes(credit.getPagosPendientes());
				credito_TO.setPagos_realizados(credit.getPagosRealizados());
				credito_TO.setSaldo_actual(credit.getSaldoActual());
				credito_TO.setTipo_credito(credit.getTipo());
				credito_TO.setTotal_pagos(credit.getTotalPagos());
				creditosColeccion.add(credito_TO);
			}
			
			cuentasTO.setColeccion_creditos(creditosColeccion);
		}				
		
		cuentasTO.setColeccion_cuentas(cuentaCollectionTO);
		cuentasTO.setColeccion_tarjetas(obtenerTarjetas(clienteTO));
		
		try {
			Inversion_mercado_dineroTO inversion_mercado_dinero = getMercadoDinero(clienteTO, session);		
			cuentasTO.setInversion_mercado_dinero(inversion_mercado_dinero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cuentasTO.setAlias(clienteTO.getUserName());
		cuentasTO.setEmail(clienteTO.getEmail());
		cuentasTO.setNombre_completo(clienteTO.getNombreCompleto());
		
		CuentasTO clasificacionCuentas = filtrarCuentas(clienteTO);
		if(clasificacionCuentas != null){
			cuentasTO.setColeccion_cuentas(clasificacionCuentas.getColeccion_cuentas());
			cuentasTO.setColeccion_inversiones(clasificacionCuentas.getColeccion_inversiones());
		}
		
		session.addAttribute(CLIENTE_TO, clienteTO);
		response.addAttribute(cuentasTO);
		
		return response;
	}
	

	private Collection<TarjetaTO> obtenerTarjetas(ClienteTO clienteTO){
		
		Collection<TarjetaBaseAlnova> coleccionTarjetas=new ArrayList<TarjetaBaseAlnova>();
		
		if(clienteTO.getTarjetasBaseAlnova()!=null){
			coleccionTarjetas.addAll(clienteTO.getTarjetasBaseAlnova());
		}
		if(clienteTO.getGoldenCards()!=null){
			coleccionTarjetas.addAll(clienteTO.getGoldenCards());
		}
		if(clienteTO.getTarjetasInifite()!=null){
			coleccionTarjetas.addAll(clienteTO.getTarjetasInifite());
		}
		
		TarjetasTO tarjetas= new TarjetasTO();
		Collection<TarjetaTO> cards = new ArrayList<TarjetaTO>();		
		if(coleccionTarjetas!=null){
			for (TarjetaBaseAlnova tarjeta : coleccionTarjetas){
				TarjetaTO tarjetaTO = new TarjetaTO();	
				tarjetaTO.setNumero_tarjeta(tarjeta.getNumeroTarjeta());
				tarjetaTO.setCredito_disponible(Formatter.formatMonto(tarjeta.getCreditoDisponible()));
				tarjetaTO.setCredito_usado(Formatter.formatMonto(tarjeta.getCreditoUsado()));
				tarjetaTO.setCredito_utilizado_al_corte(Formatter.formatMonto(tarjeta.getCreditoUtilizadoAlCorte()));
				tarjetaTO.setCorte_septiembre(Validator.isEmptyData(tarjeta.getCorteSeptiembre())?"":tarjeta.getCorteSeptiembre());
				tarjetaTO.setFecha_de_corte(Validator.isEmptyData(tarjeta.getFechaDeCorte())?"":tarjeta.getFechaDeCorte());
				tarjetaTO.setFecha_de_corte_nueva(Validator.isEmptyData(tarjeta.getFechaDeCorte_Nueva())?"":tarjeta.getFechaDeCorte_Nueva());
				tarjetaTO.setPago_minimo(Formatter.formatMonto(tarjeta.getPagoMinimo()));
				tarjetaTO.setPago_para_no_generar_intereses(Formatter.formatMonto(tarjeta.getPagoParaNoGenerarIntereses()));
				tarjetaTO.setContrato(Validator.isEmptyData(tarjeta.getContrato())?"":tarjeta.getContrato());
				tarjetaTO.setCredito_utilizado_en_tarjetas_adicionales(Formatter.formatMonto(tarjeta.getCredUtilizadoTarjAdicionales()));
				tarjetaTO.setDespues_del_corte(Validator.isEmptyData(tarjeta.getDespuesCorte())?"":tarjeta.getDespuesCorte());
				tarjetaTO.setEstatus(Validator.isEmptyData(tarjeta.getEstatus())?"":tarjeta.getEstatus());
				tarjetaTO.setFecha_activacion(Validator.isEmptyData(tarjeta.getFechaActivacion())?"":tarjeta.getFechaActivacion());
				tarjetaTO.setFecha_final_de_corte(Validator.isEmptyData(tarjeta.getFechaFinalDeCorte())?"":tarjeta.getFechaFinalDeCorte());
				tarjetaTO.setFecha_limite_de_pago(Validator.isEmptyData(tarjeta.getFechaLimiteDePago())?"":tarjeta.getFechaLimiteDePago());
				tarjetaTO.setFecha_limite_de_pago_nueva(Validator.isEmptyData(tarjeta.getFechaLimiteDePago_Nueva())?"":tarjeta.getFechaLimiteDePago_Nueva());
				tarjetaTO.setFecha_proximo_corte(Validator.isEmptyData(tarjeta.getFechaProximoCorte())?"":tarjeta.getFechaProximoCorte());
				tarjetaTO.setFecha_proximo_pago(Validator.isEmptyData(tarjeta.getFechaProximoPago())?"":tarjeta.getFechaProximoPago());
				tarjetaTO.setLimite_credito(Formatter.formatMonto(tarjeta.getLimiteCredito()));
				tarjetaTO.setMostrar_nuevo_estado(Validator.isEmptyData(tarjeta.getMostrarNuevoEstado())?"":tarjeta.getMostrarNuevoEstado());
				tarjetaTO.setNombre_titular(Validator.isEmptyData(tarjeta.getTitularContrato())?"":tarjeta.getTitularContrato());
				tarjetaTO.setNumero_de_periodos(Validator.isEmptyData(tarjeta.getNumDePeriodos())?"":tarjeta.getNumDePeriodos());
				tarjetaTO.setPeriodos(Validator.isEmptyData(tarjeta.getPeriodos())?"":tarjeta.getPeriodos());
				tarjetaTO.setRetenido(Formatter.formatMonto(tarjeta.getRetenido()));
				tarjetaTO.setSaldo_al_corte(Formatter.formatMonto(tarjeta.getSaldoAlCorte()));
				tarjetaTO.setSecuencia(Validator.isEmptyData(tarjeta.getSecuencia())?"":tarjeta.getSecuencia());
				tarjetaTO.setTarjeta_adicional(Validator.isEmptyData(tarjeta.getTarjetaAdicional())?"":tarjeta.getTarjetaAdicional());
				tarjetaTO.setTarjeta_invalida(Validator.isEmptyData(tarjeta.getTarjetaInvalida())?"":tarjeta.getTarjetaInvalida());
				tarjetaTO.setTarjeta_nueva(Validator.isEmptyData(tarjeta.getTarjetaNueva())?"":tarjeta.getTarjetaNueva());
				tarjetaTO.setTarjetas_adicionales(Validator.isEmptyData(tarjeta.getTarjetasAdicionales())?"":tarjeta.getTarjetasAdicionales());
				tarjetaTO.setTiene_tarjetas_adicionales(Validator.isEmptyData(tarjeta.getTieneTarjetasAdicionales())?"":tarjeta.getTieneTarjetasAdicionales());
				tarjetaTO.setTipo(Validator.isEmptyData(tarjeta.getTipo())?"":tarjeta.getTipo());
				tarjetaTO.setTitular_contrato(Validator.isEmptyData(tarjeta.getTitularContrato())?"":tarjeta.getTitularContrato());
				tarjetaTO.setTitularidad(Validator.isEmptyData(tarjeta.getTitularidad())?"":tarjeta.getTitularidad());
				cards.add(tarjetaTO);
			}
		}
		return cards;
	}
	
	private Inversion_mercado_dineroTO getMercadoDinero(ClienteTO clienteTO, Session session) throws Exception{
		Inversion_mercado_dineroTO inversion_mercado_dineroTO = null;
		long numContrato = Long.valueOf(clienteTO.getNumContratoInvReportos());
		Double saldo = 0.0;
		if(numContrato > 0){
			inversion_mercado_dineroTO = new Inversion_mercado_dineroTO();
			CuentaMercadoDineroCommand command = new CuentaMercadoDineroCommand();
			Resumen_Operaciones_Mercado_DineroTO responseCommand;
			
			try {
				responseCommand = command.ejecutaResumenOperacines(clienteTO, InversionesRequestTO.OPERACION_RESUMEN_OPERACIONES_VIGENTES, "ELITE");
				Collection<Detalle_OperacionTO> coleccion_operaciones = responseCommand.getColeccion_operaciones();
				if(coleccion_operaciones.size() > 0){
					for(Detalle_OperacionTO operacion : coleccion_operaciones){
						saldo += operacion.getMonto();					
					}
				}
			} catch (InversionesGenericException e) {
				e.printStackTrace();
			} 
			log.info("saldo antes: " + saldo);
			saldo = Double.valueOf(Formatter.getMontoTruncado(saldo.toString(), 2));
			log.info("saldo despues: " + saldo);
			inversion_mercado_dineroTO.setNumero_contrato(String.valueOf(numContrato));
			inversion_mercado_dineroTO.setSaldo_actual(String.valueOf(saldo));
		}
		return inversion_mercado_dineroTO;
		
	}
	
	private CuentasTO filtrarCuentas(ClienteTO clienteTO){
		CuentasTO cuentasTO  = new CuentasTO();
		Collection<com.bancoazteca.elite.beans.CuentaTO> cuentas = clienteTO.getCuentas();
		Collection<com.bancoazteca.elite.beans.CuentaTO> cuentasCaptacion = new ArrayList<com.bancoazteca.elite.beans.CuentaTO>();
		Collection<com.bancoazteca.elite.beans.CuentaTO> cuentasInversion = new ArrayList<com.bancoazteca.elite.beans.CuentaTO>();
		
		if(cuentas!=null){
			for(com.bancoazteca.elite.beans.CuentaTO cuentaTO : cuentas){
				String producto = cuentaTO.getProducto();
				String subProducto = cuentaTO.getSubproducto();
				if(producto == null){
					producto = "";
				}
				if(subProducto == null){
					subProducto = "";
				}
				
				//DescripcionAbreviada = "CPE CLIENTES"	
				if( producto.equals("01") && subProducto.equals("0020")){
					cuentasCaptacion.add(cuentaTO);
				}	
				//DescripcionAbreviada = "NOMINA GPO SALI"	
				else if( producto.equals("01") && subProducto.equals("0050")){	
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "TARJETA AZTECA"	
				else if( producto.equals("01") && subProducto.equals("0099")){
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "EJE PLATA"	
				else if( producto.equals("01") && subProducto.equals("0003")){	
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "MI PLATA EN BOVEDA"	
				else if( producto.equals("04") && subProducto.equals("0001")){	
					cuentasInversion.add(cuentaTO);
				}
				//DescripcionAbreviada = "SOCIO EMPLEADO"	
				else if( producto.equals("01") && subProducto.equals("0010")){	
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "CUENTA SOCIO"	
				else if( producto.equals("02") && subProducto.equals("0003")){	
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "GUARDADITO VIST"	
				else if( producto.equals("13") && subProducto.equals("0017")){	
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "CTA EVOLUCION"	
				else if( producto.equals("13") && subProducto.equals("0025")){
					cuentasCaptacion.add(cuentaTO);
				}
				//DescripcionAbreviada = "SOCIO PLUS"	
				else if( producto.equals("97") && subProducto.equals("00")){	
					cuentasInversion.add(cuentaTO);
				}
				//DescripcionAbreviada = "PAGARE 364 DIAS"	
				else if( producto.equals("07") && subProducto.equals("0060")){	
					cuentasInversion.add(cuentaTO);
				}
				//DescripcionAbreviada = "INV AZTECA 360"	
				else if( producto.equals("14") && subProducto.equals("0016")){
					cuentasInversion.add(cuentaTO);
				}
				//DescripcionAbreviada = "CUENTA DE MEDIOS DE"	
				else if( producto.equals("70") && subProducto.equals("")){
					cuentasCaptacion.add(cuentaTO);
				}else{
					cuentasCaptacion.add(cuentaTO);
				}	
				
			}
		}
		
		cuentasTO.setColeccion_inversiones(wrapperCuenta(cuentasInversion));
		cuentasTO.setColeccion_cuentas(wrapperCuenta(cuentasCaptacion));
		
		return cuentasTO;
	}
	
	private Collection<CuentaTO> wrapperCuenta(Collection<com.bancoazteca.elite.beans.CuentaTO> cuentasOrigen){
		Collection<CuentaTO> cuentasDestino = new ArrayList<CuentaTO>();
		for (com.bancoazteca.elite.beans.CuentaTO cuenta : cuentasOrigen){				
			CuentaTO cuentaTO = new CuentaTO();
			String subProduct = cuenta.getSubproducto();
			if(!Validator.isEmptyData(subProduct)){
				cuentaTO.setNumero_cuenta(cuenta.getCuentaFormateada().replaceAll(" ", ""));
				cuentaTO.setCuenta_clabe(cuenta.getClabe());
						
				double retenido = getCantidad(cuenta.getRetenido());
				double disponible = getCantidad(cuenta.getDisponible());
				double saldoTotal = retenido + disponible;
			
				cuentaTO.setSaldo_retenido(String.valueOf(retenido));
				cuentaTO.setSaldo_disponible(String.valueOf(disponible));
				cuentaTO.setSaldo_total(String.valueOf(saldoTotal));
				
				cuentaTO.setProducto(cuenta.getDescripcion());
				
				cuentasDestino.add(cuentaTO);
			}
		}
		return cuentasDestino;
	}
	
}
