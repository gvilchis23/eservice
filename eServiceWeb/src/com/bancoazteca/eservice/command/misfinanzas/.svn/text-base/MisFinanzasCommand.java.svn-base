package com.bancoazteca.eservice.command.misfinanzas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaMisFinanzasTO;
import com.bancoazteca.elite.beans.MisFinanzasResponseTO;
import com.bancoazteca.elite.beans.MovimientosMisFinanzasTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.CuentaTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_clienteTO;
import com.bancoazteca.eservice.command.base.beans.Mis_FinanzasTO;
import com.bancoazteca.eservice.command.base.beans.Mis_Finanzas_ColeccionesTO;
import com.bancoazteca.eservice.command.base.beans.Mis_Finanzas_TotalesTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_Mis_FinanzasTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.elite.util.Validator;


public class MisFinanzasCommand  extends CommandBase {
	private static final Logger log = Logger.getLogger(MisFinanzasCommand.class);
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL;
		try{
			resourceFacadeSL = getDelegate();
			ClienteTO clienteTO =(ClienteTO)session.getAttribute(CLIENTE_TO);
			BalanceRequestTO balanceRequestTO = new BalanceRequestTO();

			balanceRequestTO.setUser(clienteTO.getUserName());
			MisFinanzasResponseTO misFinanzasResponse = resourceFacadeSL.invokeMisFinanzas(balanceRequestTO);
						
			if(misFinanzasResponse.getCuentas()!=null){
				CuentaTO cuentaTO = null;
				Collection<CuentaTO> cuentasMisFinanzas = new ArrayList<CuentaTO>(); 
				Cuentas_clienteTO cuentasClienteTO = new Cuentas_clienteTO();
				
				/*
				 * Obtenemos las cuentas a mostrar para el servicio "Mis Finanzas"
				 * 
				 * */
				for(CuentaMisFinanzasTO cuentas: misFinanzasResponse.getCuentas()){
					if(!Validator.isEmptyData(cuentas.getSubproducto()) && 
							!(cuentas.getSubproducto().equals("00"))){
						
						cuentaTO = new CuentaTO();						
						cuentaTO.setNumero_cuenta(Formatter.formatCuenta(cuentas.getNumero()));
						cuentaTO.setProducto(cuentas.getDescripcion());
						cuentaTO.setCuenta_clabe(cuentas.getClabe());						
						cuentaTO.setSaldo_retenido(cuentas.getRetenido());
						cuentaTO.setSaldo_disponible(cuentas.getDisponible());
						
						double retenido = getCantidad(cuentas.getRetenido());
						double disponible = getCantidad(cuentas.getDisponible());
						double saldoTotal = retenido + disponible;
						
						cuentaTO.setSaldo_total(String.valueOf(saldoTotal));							
						cuentasMisFinanzas.add(cuentaTO);					
					}
				}
				cuentasClienteTO.setColeccion_cuentas(cuentasMisFinanzas);
				response.addAttribute(cuentasClienteTO);
				session.addAttribute(COLLECTION_CUENTAS_MISFINANZAS_TO, cuentasMisFinanzas);
			}			
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		int contador = 0;

		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			BalanceRequestTO balanceRequestTO = new BalanceRequestTO();
			MisFinanzasResponseTO misFinanzasResponse = null;
			MisFinanzasForm misFinanzasForm = (MisFinanzasForm) getFormBean();
			Map<String,String> errors= null;
			
			log.info(misFinanzasForm);

			balanceRequestTO.setUser(clienteTO.getUserName());
			balanceRequestTO.setFechaIni(misFinanzasForm.getFecha_inicial());
			balanceRequestTO.setFechaFin(misFinanzasForm.getFecha_final());
			balanceRequestTO.setRango(misFinanzasForm.getRango());	
		
			Collection<CuentaTO> cuentas = 
				(Collection<CuentaTO>) session.getAttribute(COLLECTION_CUENTAS_MISFINANZAS_TO);
			
			balanceRequestTO.setIndexAcount("");
			for(CuentaTO cuenta: cuentas){
				if(cuenta.getNumero_cuenta().equals(misFinanzasForm.getNumero_cuenta())){
					balanceRequestTO.setIndexAcount(contador + "");
				}
				contador++;
			}
			if(balanceRequestTO.getIndexAcount().equals("")){
				errors = new HashMap<String, String>();
				errors.put("numero_cuenta","El número de cuenta cargo es incorrecto para esta operación, por favor intente nuevamente." );
				return returnErrorMap(response, errors);
			}
			
			session.addAttribute(DATOS_REQUEST_MIS_FINANZAS_TO, balanceRequestTO);

			misFinanzasResponse = resourceFacadeSL.consultarMisFinanzas(balanceRequestTO);

			if(misFinanzasResponse!= null && misFinanzasResponse.getCuentas() != null){
				
				Mis_FinanzasTO misFinanzasTO = new Mis_FinanzasTO();
				
				Movimiento_Mis_FinanzasTO movimientoMisFinanzasTO = null;						
				Mis_Finanzas_ColeccionesTO misFinanzasColeccionesTO = new Mis_Finanzas_ColeccionesTO();
				Mis_Finanzas_TotalesTO misFinanzasTotalesTO = new Mis_Finanzas_TotalesTO();
				
				for (CuentaMisFinanzasTO cuentaMisFinanzas : misFinanzasResponse.getCuentas()){	
					if(cuentaMisFinanzas != null && cuentaMisFinanzas.getMovimientos() != null){
						
						cuentaMisFinanzas.getRetenido();
						
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Traspasos_Transferencias_Entradas = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Depositos  = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Intereses_ganados  = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Otros_Conceptos_Entrada  = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Compras_Pagos = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Retiro_Ventanilla_Cajeros = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Traspasos_Transferencias_Salidas = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Comisiones = null;
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Otros_Conceptos_Salida = null;  
						Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Generales = new ArrayList<Movimiento_Mis_FinanzasTO>();
						
						/*
						 * Obtenemos los movimientos realizados por el cliente
						 * 
						 * */
						
						for(MovimientosMisFinanzasTO movimientosMisFinanzas: cuentaMisFinanzas.getMovimientos()){							
							
							movimientoMisFinanzasTO = new Movimiento_Mis_FinanzasTO();
							
							movimientoMisFinanzasTO.setConcepto(movimientosMisFinanzas.getConcepto());
							movimientoMisFinanzasTO.setFecha(movimientosMisFinanzas.getFechaTexto());
							movimientoMisFinanzasTO.setImporte(movimientosMisFinanzas.getImporteTexto());
							movimientoMisFinanzasTO.setSaldo(movimientosMisFinanzas.getBalanceTexto());
							movimientoMisFinanzasTO.setTipo_movimiento(movimientosMisFinanzas.getTipo());
							/*
							 * Lista todos los movimientos en el rango de fecha consultada por el cliente
							 * */
							coleccion_Movimientos_Generales.add(movimientoMisFinanzasTO);
							
							int tipoMovimiento = Integer.parseInt(movimientosMisFinanzas.getTipo());
							switch(tipoMovimiento){
								case 1:{

									if(coleccion_Movimientos_Traspasos_Transferencias_Entradas == null){
										coleccion_Movimientos_Traspasos_Transferencias_Entradas = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto  
									 * traspasos transferencias de tipo entrada
									 * */
									coleccion_Movimientos_Traspasos_Transferencias_Entradas.add(movimientoMisFinanzasTO);
	
									break;
								}
								case 2:{
									
									if(coleccion_Movimientos_Depositos == null){
										coleccion_Movimientos_Depositos = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a depositos realizados
									 * */
									coleccion_Movimientos_Depositos.add(movimientoMisFinanzasTO);
					
									break;
								}
								case 3:{
									
									if(coleccion_Movimientos_Intereses_ganados == null){
										coleccion_Movimientos_Intereses_ganados = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a los intereses ganados
									 * */
									coleccion_Movimientos_Intereses_ganados.add(movimientoMisFinanzasTO);
						
									break;
								}
								case 4:{
									
									if(coleccion_Movimientos_Otros_Conceptos_Entrada == null){
										coleccion_Movimientos_Otros_Conceptos_Entrada = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Listamos otros movientos de tipo entrada
									 * */
									coleccion_Movimientos_Otros_Conceptos_Entrada.add(movimientoMisFinanzasTO);
							
									break;
								}
								case 5:{
									
									if(coleccion_Movimientos_Compras_Pagos == null){
										coleccion_Movimientos_Compras_Pagos = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a compras y pagos
									 * */
									coleccion_Movimientos_Compras_Pagos.add(movimientoMisFinanzasTO);
							
									break;
								}
								case 6:{
									
									if(coleccion_Movimientos_Retiro_Ventanilla_Cajeros == null){
										coleccion_Movimientos_Retiro_Ventanilla_Cajeros = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a la cantidad retirada en 
									 * ventanilla y cajeros
									 * */
									coleccion_Movimientos_Retiro_Ventanilla_Cajeros.add(movimientoMisFinanzasTO);
							
									break;
								}
								case 7:{
									
									if(coleccion_Movimientos_Traspasos_Transferencias_Salidas == null){
										coleccion_Movimientos_Traspasos_Transferencias_Salidas = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a traspasos y transferencias 
									 * de tipo Salida
									 * */
									coleccion_Movimientos_Traspasos_Transferencias_Salidas.add(movimientoMisFinanzasTO);
							
									break;
								}
								case 8:{
									
									if(coleccion_Movimientos_Comisiones == null){
										coleccion_Movimientos_Comisiones = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Lista los movimientos con respecto a las comisiones
									 * */
									coleccion_Movimientos_Comisiones.add(movimientoMisFinanzasTO);
							
									break;
								}	
								case 9:{
									
									if(coleccion_Movimientos_Otros_Conceptos_Salida == null){
										coleccion_Movimientos_Otros_Conceptos_Salida = new ArrayList<Movimiento_Mis_FinanzasTO>();
									}
									/*
									 * Listamos otros movientos de tipo Salida
									 * */
									coleccion_Movimientos_Otros_Conceptos_Salida.add(movimientoMisFinanzasTO);
						
									break;
								}
							}
						}
						
						misFinanzasColeccionesTO.setColeccion_movimientos_traspasos_transferencias_entradas(coleccion_Movimientos_Traspasos_Transferencias_Entradas);
						misFinanzasColeccionesTO.setColeccion_movimientos_depositos(coleccion_Movimientos_Depositos);
						misFinanzasColeccionesTO.setColeccion_movimientos_intereses_ganados(coleccion_Movimientos_Intereses_ganados);
						misFinanzasColeccionesTO.setColeccion_movimientos_otros_conceptos_entrada(coleccion_Movimientos_Otros_Conceptos_Entrada);
						misFinanzasColeccionesTO.setColeccion_movimientos_compras_pagos(coleccion_Movimientos_Compras_Pagos);
						misFinanzasColeccionesTO.setColeccion_movimientos_retiro_ventanilla_cajeros(coleccion_Movimientos_Retiro_Ventanilla_Cajeros);
						misFinanzasColeccionesTO.setColeccion_movimientos_traspasos_transferencias_salidas(coleccion_Movimientos_Traspasos_Transferencias_Salidas);
						misFinanzasColeccionesTO.setColeccion_movimientos_comisiones(coleccion_Movimientos_Comisiones);
						misFinanzasColeccionesTO.setColeccion_movimientos_otros_conceptos_salida(coleccion_Movimientos_Otros_Conceptos_Salida);
						
						misFinanzasTotalesTO.setTotal_retenido(cuentaMisFinanzas.getRetenido());
						misFinanzasTotalesTO.setNumero_cuenta(Formatter.formatCuenta(cuentaMisFinanzas.getNumero()));
						misFinanzasTotalesTO.setProducto(cuentaMisFinanzas.getDescripcion());
						misFinanzasTotalesTO.setSaldo_disponible(cuentaMisFinanzas.getDisponible());
						
						misFinanzasTO.setColeccion_movimientos_general(coleccion_Movimientos_Generales);
						misFinanzasTO.setMis_finanzas_colecciones(misFinanzasColeccionesTO);
					}
				}
				
				if(misFinanzasResponse.getDetalleMovimientosMisFinanzasTO() != null){

					/*
					 * Obtenemos los totales de cada tipo de moviemiento realizado por el cliente
					 * */
					misFinanzasTotalesTO.setTotal_mis_entradas(
							misFinanzasResponse.getDetalleMovimientosMisFinanzasTO().getAbonos());
					misFinanzasTotalesTO.setTotal_mis_salidas(
							misFinanzasResponse.getDetalleMovimientosMisFinanzasTO().getCargos());					
					misFinanzasTotalesTO.setFecha_inicio(
							Formatter.formatDate(
									misFinanzasResponse.getDetalleMovimientosMisFinanzasTO().getInicio()));
					misFinanzasTotalesTO.setFecha_final(
							Formatter.formatDate(
							misFinanzasResponse.getDetalleMovimientosMisFinanzasTO().getTermino()));
					
					ArrayList <String> totales = (ArrayList<String>) misFinanzasResponse.getDetalleMovimientosMisFinanzasTO().getTotales();
					
					if(totales.size()==9){
						for(int i=0; i<totales.size(); i++){
							switch(i){
								case 0:{
									misFinanzasTotalesTO.setTotal_traspasos_transferencias_entradas((String)totales.get(i));
									break;
								}
								case 1:{
									misFinanzasTotalesTO.setTotal_depositos((String)totales.get(i));
									break;
								}
								case 2:{
									misFinanzasTotalesTO.setTotal_intereses_ganados((String)totales.get(i));
									break;
								}
								case 3:{
									misFinanzasTotalesTO.setTotal_otros_conceptos_entradas((String)totales.get(i));
									break;
								}
								case 4:{
									misFinanzasTotalesTO.setTotal_compras_pagos((String)totales.get(i));
									break;
								}
								case 5:{
									misFinanzasTotalesTO.setTotal_retiro_ventanilla_cajeros((String)totales.get(i));
									break;
								}
								case 6:{
									misFinanzasTotalesTO.setTotal_traspasos_transferencias_salidas((String)totales.get(i));
									break;
								}
								case 7:{
									misFinanzasTotalesTO.setTotal_comisiones((String)totales.get(i));
									break;
								}
								case 8:{
									misFinanzasTotalesTO.setTotal_otros_conceptos_salida((String)totales.get(i));
									break;
								}
								default:{
									break;
								}
							}
						}
					}					
				}				
				misFinanzasTO.setMis_finanzas_totales(misFinanzasTotalesTO);				
				response.addAttribute(misFinanzasTO);	
			}			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response grafica(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		BalanceRequestTO balanceRequestTO = null;
		Mis_FinanzasTO misFinanzasTO = new Mis_FinanzasTO();
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);			
			balanceRequestTO = (BalanceRequestTO)session.getAttribute(DATOS_REQUEST_MIS_FINANZAS_TO);
			
				balanceRequestTO.setUser(clienteTO.getUserName());
				MisFinanzasResponseTO misFinanzasResponse = resourceFacadeSL.invokeMisFinanzasGrafica(balanceRequestTO);
				
				if(misFinanzasResponse != null){
					if(misFinanzasResponse.getCuentas()!=null && misFinanzasResponse.getCuentas().size() > 0){
						for (CuentaMisFinanzasTO cuentaMisFinanzas : misFinanzasResponse.getCuentas()){
							if(cuentaMisFinanzas != null && cuentaMisFinanzas.getMovimientos() != null){
								Movimiento_Mis_FinanzasTO movimientoMisFinanzasTO = null;
								Collection<Movimiento_Mis_FinanzasTO> coleccion_Movimientos_Generales = new ArrayList<Movimiento_Mis_FinanzasTO>();
								for(MovimientosMisFinanzasTO movimientosMisFinanzas: cuentaMisFinanzas.getMovimientos()){
									movimientoMisFinanzasTO = new Movimiento_Mis_FinanzasTO();
									
									movimientoMisFinanzasTO.setConcepto(movimientosMisFinanzas.getConcepto());
									movimientoMisFinanzasTO.setFecha(movimientosMisFinanzas.getFechaTexto());
									movimientoMisFinanzasTO.setImporte(movimientosMisFinanzas.getImporteTexto());
									movimientoMisFinanzasTO.setSaldo(movimientosMisFinanzas.getBalanceTexto());
									movimientoMisFinanzasTO.setTipo_movimiento(movimientosMisFinanzas.getTipo());
									
									coleccion_Movimientos_Generales.add(movimientoMisFinanzasTO);
								}
								misFinanzasTO.setColeccion_movimientos_general(coleccion_Movimientos_Generales);							
							}						
						}
					}
				}				
			response.addAttribute(misFinanzasTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public double getCantidad(String dato){
		double numero = 0.0;
		if(dato!=null){
			numero = Double.parseDouble(dato);
		}
		return numero;
	}
}
