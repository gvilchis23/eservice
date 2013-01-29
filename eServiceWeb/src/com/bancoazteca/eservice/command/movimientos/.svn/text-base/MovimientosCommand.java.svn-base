package com.bancoazteca.eservice.command.movimientos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BalanceResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.MovimientosTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Detalle_CuentaTO;
import com.bancoazteca.eservice.command.base.beans.MovimientoTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_socio_plusTO;
import com.bancoazteca.eservice.command.base.beans.Movimientos_socio_plusTO;
import com.bancoazteca.eservice.command.base.beans.Posicion_AnteriorTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_MovimientosTO;
import com.bancoazteca.eservice.command.base.beans.Posicion_ActualTO;
import com.bancoazteca.eservice.command.base.beans.rendimientosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class MovimientosCommand  extends CommandBase{


public Response ejecucion(Session session) throws Exception{
		

		MovimientosForm movimientosForm = (MovimientosForm)getFormBean();
		BalanceRequestTO balanceRequestTO = new BalanceRequestTO();
		BalanceResponseTO balanceResponseTO = null;
		Response response = new Response();		 

		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Collection <CuentaTO> cuentasTO = clienteTO.getCuentas();
		String userName = clienteTO.getUserName();
		
		String cuenta=movimientosForm.getNumero_cuenta();
		
		CuentaTO cuentaTO = existeCuenta(cuentasTO,cuenta);
		if(cuentaTO!=null){

			String cuentaProducto=cuentaTO.getProducto();

			balanceRequestTO.setAcountNumber(cuentaTO.getNumero());
			balanceRequestTO.setUser(userName);
			balanceRequestTO.setIndexAcount(cuentaTO.getIndex());

			Collection<MovimientosTO> movimientosTO = null;
			ResourceFacadeSL facadeSL = getDelegate();
			

			Iterator<MovimientosTO> movimientosTOIterator;

			MovimientoTO movimientoTO = null;

			try{

				if(cuentaProducto.equals("97")){
					balanceResponseTO = facadeSL.getPartnerPlusBalanceAccount(balanceRequestTO);

					movimientosTO = balanceResponseTO.getMovimientos();
					movimientosTOIterator = (Iterator<MovimientosTO>)movimientosTO.iterator();

				
					
					Movimiento_socio_plusTO movimiento_socio_plusTO=new Movimiento_socio_plusTO();
					ArrayList<Movimiento_socio_plusTO> coleccionMovimiento_socio_plus=new ArrayList<Movimiento_socio_plusTO>();
					while(movimientosTOIterator.hasNext()){
						MovimientosTO movTO = (MovimientosTO)movimientosTOIterator.next();
						movimiento_socio_plusTO=new Movimiento_socio_plusTO();

						movimiento_socio_plusTO.setFecha_movimiento(movTO.getFecha());
						movimiento_socio_plusTO.setImporte(String.valueOf(movTO.getImporte()));
						movimiento_socio_plusTO.setFolio(movTO.getFolio());
						movimiento_socio_plusTO.setUnidades(movTO.getUnidades());
						movimiento_socio_plusTO.setConcepto(movTO.getConcepto());
						movimiento_socio_plusTO.setPrecio(movTO.getValorUnidad());
						movimiento_socio_plusTO.setInteres(movTO.getInteres());
						movimiento_socio_plusTO.setSaldo(movTO.getSaldo().toString());
						movimiento_socio_plusTO.setMovimiento(movTO.getMovimiento().toString());
						
						coleccionMovimiento_socio_plus.add(movimiento_socio_plusTO);
					}
					Movimientos_socio_plusTO movimientos_socio_plusTO=new Movimientos_socio_plusTO();
					movimientos_socio_plusTO.setColeccion_movimientos(coleccionMovimiento_socio_plus);
					//movimientos_socio_plusTO.setPlusvalia(balanceResponseTO.getPlusvalia());
					//movimientos_socio_plusTO.setPosicion_actual(balanceResponseTO.getPosicionActual());
					
					/*Para rendimientos*/
					
					String rendimientos="";
//					
					if(balanceResponseTO.getRendimientos() != null){
					rendimientos=balanceResponseTO.getRendimientos();
					System.out.println("LOS RENDIMIENTOS SON>>" +rendimientos);
					}

							String rendimiento_dia_anterior="";
							String rendimiento_semanal="";
							String rendimiento_mensual="";
							String rendimiento_anual="";
							rendimientosTO rendimientosTO=new rendimientosTO();
					
						             int eliminar = rendimientos.indexOf(",");
						             rendimiento_dia_anterior = rendimientos.substring(0, eliminar);
						             rendimientosTO.setRendimiento_dia_anterior(Formatter.removeSpacesLeftRight(rendimiento_dia_anterior!=null?rendimiento_dia_anterior:""));
								     
						             rendimientos = rendimientos.substring(eliminar+1);
								     eliminar = rendimientos.indexOf(",");
								     rendimiento_semanal = rendimientos.substring(0, eliminar);
								     rendimientosTO.setRendimiento_semanal(Formatter.removeSpacesLeftRight(rendimiento_semanal!=null?rendimiento_semanal:""));
								     
								     rendimientos = rendimientos.substring(eliminar+1);
								     eliminar = rendimientos.indexOf(",");
								     rendimiento_mensual = rendimientos.substring(0, eliminar);
								     rendimientosTO.setRendimiento_mensual(Formatter.removeSpacesLeftRight(rendimiento_mensual!=null?rendimiento_mensual:""));
								   
								     
								     rendimientos = rendimientos.substring(eliminar+1);
								     rendimientosTO.setRendimiento_anual(Formatter.removeSpacesLeftRight(rendimientos!=null?rendimientos:""));
								   
								     movimientos_socio_plusTO.setRendimientos(rendimientosTO);   
								     //response.addAttribute(movimientos_socio_plusTO);
								     
								     ///Para plusvalia
								     
								     String plusvalias="";
								     
								     if(balanceResponseTO.getPlusvalia() != null){
								    	 plusvalias=balanceResponseTO.getPlusvalia();
											System.out.println("IMPRESION PLUSVALIA>>" + plusvalias);
											}
								     
									 String total_unidades;
									 String costo_promedio_unidad;
									 String importe_al_costo;
									 String precio_unidad_cierre;
									 
									 String importe_total;
									 String plusvalia="";
									 	
									 Posicion_AnteriorTO posicion_anteriorTO=new Posicion_AnteriorTO();
								 	
								 	 eliminar = plusvalias.indexOf(",");
								 	 costo_promedio_unidad = plusvalias.substring(0, eliminar);
								 	 posicion_anteriorTO.setCosto_promedio_unidad(Formatter.removeSpacesLeftRight(costo_promedio_unidad!=null?costo_promedio_unidad:""));
								     
								 	 plusvalias = plusvalias.substring(eliminar+1);
								     eliminar = plusvalias.indexOf(",");
								     importe_al_costo = plusvalias.substring(0, eliminar);
								     posicion_anteriorTO.setImporte_al_costo(Formatter.removeSpacesLeftRight(importe_al_costo!=null?importe_al_costo:""));
								     
								     plusvalias = plusvalias.substring(eliminar+1);
								     eliminar = plusvalias.indexOf(",");
								     importe_total = plusvalias.substring(0, eliminar);
								     posicion_anteriorTO.setImporte_total(Formatter.removeSpacesLeftRight(importe_total!=null?importe_total:""));
								   
								     
								     plusvalias = plusvalias.substring(eliminar+1);
								     eliminar = plusvalias.indexOf(",");
								     plusvalia = plusvalias.substring(0, eliminar);
								     posicion_anteriorTO.setPlusvalia(Formatter.removeSpacesLeftRight(plusvalia!=null?plusvalia:""));
								   
								     
								     plusvalias = plusvalias.substring(eliminar+1);
								     eliminar = plusvalias.indexOf(",");
								     total_unidades = plusvalias.substring(0, eliminar);
								     posicion_anteriorTO.setTotal_unidades(Formatter.removeSpacesLeftRight(total_unidades!=null?total_unidades:""));
								   
								     
								     plusvalias = plusvalias.substring(eliminar+1);
								     posicion_anteriorTO.setPrecio_unidad_cierre(Formatter.removeSpacesLeftRight(plusvalias!=null?plusvalias:""));
								   
								     movimientos_socio_plusTO.setPosicion_anterior(posicion_anteriorTO);   
								     
								     
								     
								 	//Para posicion Actual
								     Posicion_ActualTO posicion_actualTO=new Posicion_ActualTO();
								     posicion_actualTO.setImporte_total(cuentaTO.getDisponible().toString());
								     posicion_actualTO.setPrecio_actual_unidad(balanceResponseTO.getValorUni());
								     posicion_actualTO.setTotal_de_unidades(total_unidades);
								     
								     movimientos_socio_plusTO.setPosicion_actual(posicion_actualTO);  
								     
								 	/**Colocamos los datos del cliente*/
										
										Detalle_CuentaTO detalle_CuentaTO=new Detalle_CuentaTO();
										
										detalle_CuentaTO.setCliente(clienteTO.getNombreCompleto());
										detalle_CuentaTO.setCuenta(cuenta);
										detalle_CuentaTO.setTipo_cuenta(cuentaTO.getDescripcion());
										detalle_CuentaTO.setFecha_inicio(balanceResponseTO.getFecha_inicio());
										detalle_CuentaTO.setFecha_final(balanceResponseTO.getFecha_final());
										detalle_CuentaTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
								     
										response.addAttribute(detalle_CuentaTO);
										response.addAttribute(movimientos_socio_plusTO);
								     



				}else{

					com.bancoazteca.eservice.command.base.beans.MovimientosTO movto = new com.bancoazteca.eservice.command.base.beans.MovimientosTO();		
					Collection<MovimientoTO> collectionMov = new ArrayList<MovimientoTO>();

					balanceResponseTO = facadeSL.getMovimientosByIndex(balanceRequestTO);
									


					movimientosTO = balanceResponseTO.getMovimientos();
					movimientosTOIterator = (Iterator<MovimientosTO>)movimientosTO.iterator();

					while(movimientosTOIterator.hasNext()){

						MovimientosTO movsTO = (MovimientosTO)movimientosTOIterator.next();
						movimientoTO = new MovimientoTO();

						movimientoTO.setConcepto(movsTO.getConcepto());
						movimientoTO.setFecha_movimiento(movsTO.getFecha());
						movimientoTO.setImporte(String.valueOf(movsTO.getImporte()));
						movimientoTO.setNum_movimiento(movsTO.getNumero());
						movimientoTO.setSaldo_total(String.valueOf(movsTO.getBalance()));

						collectionMov.add(movimientoTO);
						
					}
									
					
					Resumen_MovimientosTO resumen_Movimientos = new Resumen_MovimientosTO();
					
					resumen_Movimientos.setNombre_cliente(clienteTO.getNombreCompleto());
					resumen_Movimientos.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
					resumen_Movimientos.setCuenta_clabe(Formatter.removeSpaces(cuentaTO.getClabe()));
					resumen_Movimientos.setDescripcion_cuenta(cuentaTO.getDescripcion());
					resumen_Movimientos.setFecha_inicio(balanceResponseTO.getDetalleMovimientosCuentas()==null?"":balanceResponseTO.getDetalleMovimientosCuentas().getInicio());
					resumen_Movimientos.setFecha_final(balanceResponseTO.getDetalleMovimientosCuentas()==null?"":balanceResponseTO.getDetalleMovimientosCuentas().getTermino());
					resumen_Movimientos.setSaldo_total_actual(cuentaTO.getBalance().toString());
					resumen_Movimientos.setSaldo_retenido(cuentaTO.getRetenido().toString());
					resumen_Movimientos.setSaldo_disponible(cuentaTO.getDisponible().toString());
					resumen_Movimientos.setTotal_cargos(String.valueOf(balanceResponseTO.getDetalleMovimientosCuentas()==null?"0.00":balanceResponseTO.getDetalleMovimientosCuentas().getCargos()));
					resumen_Movimientos.setTotal_abonos(String.valueOf(balanceResponseTO.getDetalleMovimientosCuentas()==null?"0.00":balanceResponseTO.getDetalleMovimientosCuentas().getAbonos()));
					
					resumen_Movimientos.setRetenido_negativo(cuentaTO.getRetenido().toString());
					resumen_Movimientos.setRetenido_positivo(cuentaTO.getRetenido().toString());					
					
					movto.setResumen_movimientos(resumen_Movimientos);	
					movto.setColeccion_movimientos(collectionMov);
					response.addAttribute(movto); 
					
				}

			}catch(EliteDataException e)
			{
				buildErrorResponse(e, response);
			}
		}else{
			response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+": la cuenta "+cuenta+" no existe para su usuario.", null);
		}
		
		return response;
	}


	
//	public Response ejecucion(Session session) throws Exception{
//			
//	
//			MovimientosForm movimientosForm = (MovimientosForm)getFormBean();
//			BalanceRequestTO balanceRequestTO = new BalanceRequestTO();
//			BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
//			Response response = new Response();		 
//	
//			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
//			
//			Collection <CuentaTO> cuentasTO = clienteTO.getCuentas();
//			String userName = clienteTO.getUserName();
//			
//			String cuenta=movimientosForm.getNumero_cuenta();
//			
//			CuentaTO cuentaTO = existeCuenta(cuentasTO,cuenta);
//			if(cuentaTO!=null){
//	
//				String cuentaProducto=cuentaTO.getProducto();
//				balanceRequestTO.setUser(userName);
//				balanceRequestTO.setFechaIni(movimientosForm.getFecha_inicial());
//				balanceRequestTO.setFechaFin(movimientosForm.getFecha_final());
//				balanceRequestTO.setRango(movimientosForm.getRango());	
//				
//				Collection<MovimientosTO> movimientosTO = null;
//				ResourceFacadeSL facadeSL = getDelegate();
//				
//	
//				Iterator<MovimientosTO> movimientosTOIterator;
//	
//				MovimientoTO movimientoTO = null;
//	
//				try{
//	
//					if(cuentaProducto.equals("97")){
//						balanceResponseTO = facadeSL.consultarMovimientosFecha(balanceRequestTO);
//	
//						movimientosTO = balanceResponseTO.getMovimientos();
//						movimientosTOIterator = (Iterator<MovimientosTO>)movimientosTO.iterator();
//	
//					
//						
//						Movimiento_socio_plusTO movimiento_socio_plusTO=new Movimiento_socio_plusTO();
//						ArrayList<Movimiento_socio_plusTO> coleccionMovimiento_socio_plus=new ArrayList<Movimiento_socio_plusTO>();
//						while(movimientosTOIterator.hasNext()){
//							MovimientosTO movTO = (MovimientosTO)movimientosTOIterator.next();
//							movimiento_socio_plusTO=new Movimiento_socio_plusTO();
//	
//							movimiento_socio_plusTO.setFecha_movimiento(movTO.getFecha());
//							movimiento_socio_plusTO.setImporte(String.valueOf(movTO.getImporte()));
//							movimiento_socio_plusTO.setFolio(movTO.getFolio());
//							movimiento_socio_plusTO.setUnidades(movTO.getUnidades());
//							movimiento_socio_plusTO.setConcepto(movTO.getConcepto());
//							movimiento_socio_plusTO.setPrecio(movTO.getValorUnidad());
//							movimiento_socio_plusTO.setInteres(movTO.getInteres());
//							movimiento_socio_plusTO.setSaldo(movTO.getSaldo().toString());				
//							
//							coleccionMovimiento_socio_plus.add(movimiento_socio_plusTO);
//						}
//						Movimientos_socio_plusTO movimientos_socio_plusTO=new Movimientos_socio_plusTO();
//						movimientos_socio_plusTO.setColeccion_movimientos(coleccionMovimiento_socio_plus);
//	
//						/*Para rendimientos*/
//						
//						String rendimientos="";
//	//					
//						if(balanceResponseTO.getRendimientos() != null){
//						rendimientos=balanceResponseTO.getRendimientos();
//						System.out.println("LOS RENDIMIENTOS SON>>" +rendimientos);
//						}
//	
//								String rendimiento_dia_anterior="";
//								String rendimiento_semanal="";
//								String rendimiento_mensual="";
//								rendimientosTO rendimientosTO=new rendimientosTO();
//						
//							             int eliminar = rendimientos.indexOf(",");
//							             rendimiento_dia_anterior = rendimientos.substring(0, eliminar);
//							             rendimientosTO.setRendimiento_dia_anterior(Formatter.removeSpacesLeftRight(rendimiento_dia_anterior!=null?rendimiento_dia_anterior:""));
//									     
//							             rendimientos = rendimientos.substring(eliminar+1);
//									     eliminar = rendimientos.indexOf(",");
//									     rendimiento_semanal = rendimientos.substring(0, eliminar);
//									     rendimientosTO.setRendimiento_semanal(Formatter.removeSpacesLeftRight(rendimiento_semanal!=null?rendimiento_semanal:""));
//									     
//									     rendimientos = rendimientos.substring(eliminar+1);
//									     eliminar = rendimientos.indexOf(",");
//									     rendimiento_mensual = rendimientos.substring(0, eliminar);
//									     rendimientosTO.setRendimiento_mensual(Formatter.removeSpacesLeftRight(rendimiento_mensual!=null?rendimiento_mensual:""));
//									   
//									     
//									     rendimientos = rendimientos.substring(eliminar+1);
//									     rendimientosTO.setRendimiento_anual(Formatter.removeSpacesLeftRight(rendimientos!=null?rendimientos:""));
//									   
//									     movimientos_socio_plusTO.setRendimientos(rendimientosTO);
//									     
//									     ///Para plusvalia
//									     
//									     String plusvalias="";
//									     
//									     if(balanceResponseTO.getPlusvalia() != null){
//									    	 plusvalias=balanceResponseTO.getPlusvalia();
//												System.out.println("IMPRESION PLUSVALIA>>" + plusvalias);
//												}
//									     
//										 String total_unidades;
//										 String costo_promedio_unidad;
//										 String importe_al_costo;
//										 String importe_total;
//										 String plusvalia="";
//										 	
//										 Posicion_AnteriorTO posicion_anteriorTO=new Posicion_AnteriorTO();
//									 	
//									 	 eliminar = plusvalias.indexOf(",");
//									 	 costo_promedio_unidad = plusvalias.substring(0, eliminar);
//									 	 posicion_anteriorTO.setCosto_promedio_unidad(Formatter.removeSpacesLeftRight(costo_promedio_unidad!=null?costo_promedio_unidad:""));
//									     
//									 	 plusvalias = plusvalias.substring(eliminar+1);
//									     eliminar = plusvalias.indexOf(",");
//									     importe_al_costo = plusvalias.substring(0, eliminar);
//									     posicion_anteriorTO.setImporte_al_costo(Formatter.removeSpacesLeftRight(importe_al_costo!=null?importe_al_costo:""));
//									     
//									     plusvalias = plusvalias.substring(eliminar+1);
//									     eliminar = plusvalias.indexOf(",");
//									     importe_total = plusvalias.substring(0, eliminar);
//									     posicion_anteriorTO.setImporte_total(Formatter.removeSpacesLeftRight(importe_total!=null?importe_total:""));
//									   
//									     
//									     plusvalias = plusvalias.substring(eliminar+1);
//									     eliminar = plusvalias.indexOf(",");
//									     plusvalia = plusvalias.substring(0, eliminar);
//									     posicion_anteriorTO.setPlusvalia(Formatter.removeSpacesLeftRight(plusvalia!=null?plusvalia:""));
//									   
//									     
//									     plusvalias = plusvalias.substring(eliminar+1);
//									     eliminar = plusvalias.indexOf(",");
//									     total_unidades = plusvalias.substring(0, eliminar);
//									     posicion_anteriorTO.setTotal_unidades(Formatter.removeSpacesLeftRight(total_unidades!=null?total_unidades:""));
//									   
//									     
//									     plusvalias = plusvalias.substring(eliminar+1);
//									     posicion_anteriorTO.setPrecio_unidad_cierre(Formatter.removeSpacesLeftRight(plusvalias!=null?plusvalias:""));
//									   
//									     movimientos_socio_plusTO.setPosicion_anterior(posicion_anteriorTO);   
//									     
//									 	//Para posicion Actual
//									     
//									     Posicion_ActualTO posicion_actualTO=new Posicion_ActualTO();
//									     posicion_actualTO.setImporte_total(cuentaTO.getDisponible().toString());
//									     posicion_actualTO.setPrecio_actual_unidad(balanceResponseTO.getValorUni());
//									     posicion_actualTO.setTotal_de_unidades(total_unidades);
//									     
//									     movimientos_socio_plusTO.setPosicion_actual(posicion_actualTO);  
//									     
//									 	/**Colocamos los datos del cliente*/
//											
//											Detalle_CuentaTO detalle_CuentaTO=new Detalle_CuentaTO();
//											
//											detalle_CuentaTO.setCliente(clienteTO.getNombreCompleto());
//											detalle_CuentaTO.setCuenta(cuenta);
//											detalle_CuentaTO.setTipo_cuenta(cuentaTO.getDescripcion());
//											detalle_CuentaTO.setFecha_inicio(balanceResponseTO.getFecha_inicio());
//											detalle_CuentaTO.setFecha_final(balanceResponseTO.getFecha_final());
//											detalle_CuentaTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
//									     
//											response.addAttribute(detalle_CuentaTO);
//											response.addAttribute(movimientos_socio_plusTO);
//									     
//	
//	
//	
//					}else{
//	
//						com.bancoazteca.eservice.command.base.beans.MovimientosTO movto = new com.bancoazteca.eservice.command.base.beans.MovimientosTO();		
//						Collection<MovimientoTO> collectionMov = new ArrayList<MovimientoTO>();
//	
//						balanceResponseTO = facadeSL.consultarMovimientosFechaOtrasCuentas(balanceRequestTO);
//					
//	
//						movimientosTO = balanceResponseTO.getMovimientos();
//						movimientosTOIterator = (Iterator<MovimientosTO>)movimientosTO.iterator();
//	
//						while(movimientosTOIterator.hasNext()){
//	
//							MovimientosTO movsTO = (MovimientosTO)movimientosTOIterator.next();
//							movimientoTO = new MovimientoTO();
//	
//							movimientoTO.setConcepto(movsTO.getConcepto());
//							movimientoTO.setFecha_movimiento(movsTO.getFecha());
//							movimientoTO.setImporte(String.valueOf(movsTO.getImporte()));
//							movimientoTO.setNum_movimiento(movsTO.getNumero());
//							movimientoTO.setSaldo_total(String.valueOf(movsTO.getBalance()));
//	
//							collectionMov.add(movimientoTO);
//							
//						}
//										
//						
//						Resumen_MovimientosTO resumen_Movimientos = new Resumen_MovimientosTO();
//						
//						resumen_Movimientos.setNombre_cliente(clienteTO.getNombreCompleto());
//						resumen_Movimientos.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
//						resumen_Movimientos.setCuenta_clabe(Formatter.removeSpaces(cuentaTO.getClabe()));
//						resumen_Movimientos.setDescripcion_cuenta(cuentaTO.getDescripcion());
//						resumen_Movimientos.setFecha_inicio(balanceResponseTO.getDetalleMovimientosCuentas().getInicio());
//						resumen_Movimientos.setFecha_final(balanceResponseTO.getDetalleMovimientosCuentas().getTermino());
//						resumen_Movimientos.setSaldo_total_actual(cuentaTO.getBalance().toString());
//						resumen_Movimientos.setSaldo_retenido(cuentaTO.getRetenido().toString());
//						resumen_Movimientos.setSaldo_disponible(cuentaTO.getDisponible().toString());
//						resumen_Movimientos.setTotal_cargos(String.valueOf(balanceResponseTO.getDetalleMovimientosCuentas().getCargos()));
//						resumen_Movimientos.setTotal_abonos(String.valueOf(balanceResponseTO.getDetalleMovimientosCuentas().getAbonos()));
//						resumen_Movimientos.setRetenido_negativo("0.00");
//						resumen_Movimientos.setRetenido_positivo("0.00");					
//						movto.setResumen_movimientos(resumen_Movimientos);	
//						movto.setColeccion_movimientos(collectionMov);
//						response.addAttribute(movto); 
//						
//					}
//	
//				}catch(EliteDataException e)
//				{
//					buildErrorResponse(e, response);
//				}
//			}else{
//				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+": la cuenta "+cuenta+" no existe para su usuario.", null);
//			}
//			
//			return response;
//		}
	
		private CuentaTO existeCuenta(Collection<CuentaTO> cuentasTO,String numero_cuenta) {
			CuentaTO cuentaTO = super.getAccountsPrdicate(cuentasTO, numero_cuenta);
			
			return cuentaTO;
		}

}