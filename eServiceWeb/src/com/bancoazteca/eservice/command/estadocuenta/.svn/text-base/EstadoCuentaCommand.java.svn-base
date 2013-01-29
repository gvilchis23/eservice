package com.bancoazteca.eservice.command.estadocuenta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DetalleMovimientosTO;
import com.bancoazteca.elite.beans.EstadoCuentaRequestTO;
import com.bancoazteca.elite.beans.EstadoCuentaResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.EliteException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Comportamiento_Estado_CuentaSocioplusTO;
import com.bancoazteca.eservice.command.base.beans.Comportamiento_Estado_CuentaTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_MovimientoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_Movimiento_SocioplusTO;
import com.bancoazteca.eservice.command.base.beans.Estado_cuentaSocioPlusTO;
import com.bancoazteca.eservice.command.base.beans.Estado_cuentaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.Recibo_ElectronicoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.pagoservicios.movistar.PagoServiciosMovistarCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class EstadoCuentaCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(PagoServiciosMovistarCommand.class);
	
	private final String SOCIOPLUS = "SOCIO PLUS";
	private static final String TIPO_SOCIOPLUS="TIPO_SOCIOPLUS";
	
	
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		
		EstadoCuentaRequestTO estadoCuentaRequestTO = new EstadoCuentaRequestTO();
		estadoCuentaRequestTO.setUser(clienteTO.getUserName());
		
		ResourceFacadeSL facadeSL = getDelegate();
		
		EstadoCuentaResponseTO estadoCuentaResponseTO = facadeSL.estadoCuentaGetCuentas(estadoCuentaRequestTO); 
		
		Map<String, String> cuentas = estadoCuentaResponseTO.getMapCuentas();
		
		Estado_cuentaTO estado_cuentaTO = new Estado_cuentaTO();
		
		Collection<Cuenta_CargoTO> collection=new ArrayList<Cuenta_CargoTO>();
		
		String[] descripcionSaldo=null;
		
		Map<String,String> cuentaIndex=new HashMap<String,String>();
		String  cuenta=null;
		if (cuentas != null){
			for (String index : cuentas.keySet()){
				
				Cuenta_CargoTO cuentaTO=new Cuenta_CargoTO();
				cuenta=Formatter.removeSpaces(cuentas.get(index).substring(0,19));
				cuentaTO.setNumero_cuenta(cuenta);
				descripcionSaldo=cuentas.get(index).substring(19).split("\\$");
				cuentaTO.setSaldo_disponible(Formatter.removeSpacesLeftRight("$ "+descripcionSaldo[1]));
				cuentaTO.setProducto(Formatter.removeSpacesLeftRight(descripcionSaldo[0]));
//				if(!cuentaTO.getProducto().toUpperCase().equals(SOCIOPLUS)){
					collection.add(cuentaTO);
//				}
				log.info("index  "+index+" cuenta:"+cuenta);
				cuentaIndex.put(cuenta,index);
			}
		}
		estado_cuentaTO.setColeccion_cuentas(collection);
		
		response.addAttribute(estado_cuentaTO);
		
		session.addAttribute(CUENTAS_INDEX, cuentaIndex);
		
		
		return response;
	}
	
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		EstadoCuentaForm forma = (EstadoCuentaForm)getFormBean();
		HashMap<String, String> errores = null;
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		EstadoCuentaResponseTO estadoCuentaResponseTO=null;
		
		EstadoCuentaRequestTO estadoCuentaRequestTO = new EstadoCuentaRequestTO();
		estadoCuentaRequestTO.setUser(clienteTO.getUserName());
		String cuentaFormateada=Formatter.removeSpaces(forma.getCuenta());
		Map<String,String> cuentasIndex=(Map<String, String>)session.getAttribute(CUENTAS_INDEX);
		
		CuentaTO cuentaTO = (CuentaTO) getAccountsPrdicate(clienteTO.getCuentas(), cuentaFormateada);
		
		if(forma.getComando().equalsIgnoreCase("validacion")&& !cuentaTO.getProducto().equals(PRODUCTO_SOCIO_PLUS) ){
			session.deleteAttribute(TIPO_ESTADO_CUENTA);
		}
		
		
		estadoCuentaRequestTO.setCuenta(cuentasIndex.get(cuentaFormateada));
		
		
		
		if(forma.getComando().equalsIgnoreCase("validacion")&& cuentaTO.getProducto().equals(PRODUCTO_SOCIO_PLUS) ){
		
			estadoCuentaRequestTO.setAnio( Validator.isEmptyData( forma.getAnio_periodo())? "0" : forma.getAnio_periodo() );
			estadoCuentaRequestTO.setMes(  Validator.isEmptyData( forma.getAnio_periodo())? "0" : forma.getMes_periodo() );
	
			//Obtenemos la lista de los a;os
			
			ArrayList<String> listaAnios=(ArrayList<String>) session.getAttribute("listaAnios");
			boolean bandera=false;
			
			
			String nextMetodo= (String)session.getAttribute(FLAG_SOCIO_PLUS_VALIDATION_NEXT_METHOD);
				
			//Verificamos si la lista contiene el annio que se le manda de la forma
			
			if(listaAnios!=null){
				Iterator<String> itr = listaAnios.iterator();
				    while (itr.hasNext()) {
				      String annio = itr.next();
				      if(annio.equalsIgnoreCase(forma.getAnio_periodo())){
				    	  bandera=true;
				      }		      
				    }
				}
				
			
			if(estadoCuentaRequestTO.getAnio()==null || estadoCuentaRequestTO.getAnio().equals("0")){
			nextMetodo="dates";
			}else{
				
				/*forma.getAnio_periodo()==true*/
				if(forma.getAnio_periodo()!=null && bandera==true) {
					nextMetodo="getMeses";
					
					}else{
						//regresamos mensaje si no coincide annio
						errores = new HashMap<String, String>();
						errores.put("annio erroneo",Errors.ESTADO_DE_CUENTA_ANIO_REQUERIDO );

				}
				
		}
	
			session.addAttribute(FLAG_SOCIO_PLUS_VALIDATION_NEXT_METHOD, nextMetodo);
			estadoCuentaRequestTO.setMethod(nextMetodo);
			session.addAttribute(TIPO_ESTADO_CUENTA,TIPO_SOCIOPLUS);
		}else{
			estadoCuentaRequestTO.setAnio("0");
			estadoCuentaRequestTO.setMes("0");
			estadoCuentaRequestTO.setMethod("dates");
		}
		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			estadoCuentaResponseTO = resourceFacadeSL.estadoCuentaSeleccionaCuenta(estadoCuentaRequestTO);
			
			Collection<String> periodos = estadoCuentaResponseTO.getPeriodos();
			Collection<String> meses = estadoCuentaResponseTO.getMeses();
			Collection<String> anios = estadoCuentaResponseTO.getAnios();
			String mesAtual=new SimpleDateFormat("MMMM").format(new Date());
			//Subir a session	
			session.addAttribute("listaAnios", anios);
			log.info("mesAtual: " + mesAtual);
			if(periodos != null && periodos.size() > 0)
			for (String periodo : periodos) {
				log.info("periodo Atual: " + periodo);
				if(periodo.toLowerCase().contains(mesAtual)){
					periodos.remove(periodo);
					break;
				}
			}
			
			if(errores!=null && errores.size()>0){
				returnErrorMap(response, errores);	
			}else{
			if( cuentaTO.getProducto().equals(PRODUCTO_SOCIO_PLUS) ){
				log.info("meses: " + meses);
				log.info("anios: " + anios);
				Estado_cuentaSocioPlusTO estado_cuentaTO=new Estado_cuentaSocioPlusTO();
				estado_cuentaTO.setColeccion_periodos(null);
				estado_cuentaTO.setColeccion_meses(meses);
				estado_cuentaTO.setColeccion_anios(anios);
				
				response.addAttribute(estado_cuentaTO);
			}else{
				log.info("periodos: " + periodos);
				Estado_cuentaTO estado_cuentaTO=new Estado_cuentaTO();
				estado_cuentaTO.setColeccion_periodos(periodos);
				estado_cuentaTO.setColeccion_meses(null);
				estado_cuentaTO.setColeccion_anios(null);
				response.addAttribute(estado_cuentaTO);
			}
		
		}
			
		}catch (EliteDataException e) {
			
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		EstadoCuentaForm forma = (EstadoCuentaForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		Map<String, String> cuentaIndex = (Map<String,String>)session.getAttribute(CUENTAS_INDEX);
		session.deleteAttribute(FLAG_SOCIO_PLUS_VALIDATION_NEXT_METHOD);
		
		String tipoEstadoCuenta = (String)session.getAttribute(TIPO_ESTADO_CUENTA);
		EstadoCuentaRequestTO estadoCuentaRequestTO = new EstadoCuentaRequestTO();
		
		String cuenta=cuentaIndex.get(Formatter.removeSpaces(forma.getCuenta()));
		
		if(cuenta==null){
			response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+": la tarjeta "+forma.getCuenta()+ " no existe.", null);
			return response;
		}
		
	
		estadoCuentaRequestTO.setCuenta(cuenta);
		estadoCuentaRequestTO.setPeriodo(forma.getPeriodo());
		estadoCuentaRequestTO.setMes( Validator.isEmptyData( forma.getMes_periodo())? "0" : forma.getMes_periodo()+"          _");
		estadoCuentaRequestTO.setAnio( Validator.isEmptyData( forma.getAnio_periodo())? "0" : forma.getAnio_periodo());
		estadoCuentaRequestTO.setUser(clienteTO.getUserName());
		
		
		
		estadoCuentaRequestTO.setUser(clienteTO.getUserName());
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			EstadoCuentaResponseTO estadoCuentaResponseTO = resourceFacadeSL.estadoCuentaEjecutar(estadoCuentaRequestTO);
			
			if( tipoEstadoCuenta != null && tipoEstadoCuenta.equals(TIPO_SOCIOPLUS) ){
				Map estadoCuenta = estadoCuentaResponseTO.getEstadoCuenta();
				
				ArrayList<DetalleMovimientosTO> movimientosSocPlus= (ArrayList<DetalleMovimientosTO>)estadoCuenta.get("movimientos");
				
				Detalle_MovimientoTO detalle_MovimientoTO=null;
				Collection<Detalle_Movimiento_SocioplusTO>coleccionMovimientos=new ArrayList<Detalle_Movimiento_SocioplusTO>();
				
				Collection<Detalle_Movimiento_SocioplusTO>coleccionMovimientosSocioPlus =new ArrayList<Detalle_Movimiento_SocioplusTO>();
				Detalle_Movimiento_SocioplusTO detalle_Movimiento_SocioplusTO=null;
				
				if(movimientosSocPlus != null)
				for (DetalleMovimientosTO movimiento : movimientosSocPlus) {
					detalle_Movimiento_SocioplusTO=new Detalle_Movimiento_SocioplusTO();
					detalle_Movimiento_SocioplusTO.setInteres(movimiento.getInteres()==null?new Double("0"):movimiento.getInteres());
					detalle_Movimiento_SocioplusTO.setSaldo(movimiento.getSaldo()==null?new Double("0"):movimiento.getSaldo());
					detalle_Movimiento_SocioplusTO.setUnidades(movimiento.getUnidades()==null?"":movimiento.getUnidades());
					detalle_Movimiento_SocioplusTO.setMovimiento(movimiento.getMovimiento()==null?"":movimiento.getMovimiento());
					detalle_Movimiento_SocioplusTO.setFecha(movimiento.getFecha()==null?"":movimiento.getFecha());
					detalle_Movimiento_SocioplusTO.setValor_unidad(movimiento.getValorUnidad()==null?"":movimiento.getValorUnidad());
					detalle_Movimiento_SocioplusTO.setSaldo_nidades(movimiento.getSaldoUnidades()==null?"":movimiento.getSaldoUnidades());
					detalle_Movimiento_SocioplusTO.setImporte(movimiento.getImporte()==null?new Double("0"):movimiento.getImporte());
					detalle_Movimiento_SocioplusTO.setFolio(movimiento.getFolio()==null?"":movimiento.getFolio());
					coleccionMovimientos.add(detalle_Movimiento_SocioplusTO);
				}
				
				Comportamiento_Estado_CuentaSocioplusTO comportamiento_Estado_CuentaTO=new Comportamiento_Estado_CuentaSocioplusTO();
				
				
				
				comportamiento_Estado_CuentaTO.setColeccion_movimientos(coleccionMovimientos);
				
				comportamiento_Estado_CuentaTO.setNombre_cliente((String)estadoCuenta.get("name")==null?"":(String)estadoCuenta.get("name"));
				comportamiento_Estado_CuentaTO.setCalle_cliente((String)estadoCuenta.get("street")==null?"":(String)estadoCuenta.get("street"));
				comportamiento_Estado_CuentaTO.setColonia((String)estadoCuenta.get("neighborhood")==null?"":(String)estadoCuenta.get("neighborhood"));
				comportamiento_Estado_CuentaTO.setCodigo_postal((String)estadoCuenta.get("zipcode")==null?"":(String)estadoCuenta.get("zipcode"));
				comportamiento_Estado_CuentaTO.setRfc((String)estadoCuenta.get("rfc")==null?"":(String)estadoCuenta.get("rfc"));
				
				comportamiento_Estado_CuentaTO.setPeriodo_cuenta((String)estadoCuenta.get("period")==null?"":(String)estadoCuenta.get("period"));
				comportamiento_Estado_CuentaTO.setFecha_emision_cuenta((String)estadoCuenta.get("datetime")==null?"":(String)estadoCuenta.get("datetime"));
				comportamiento_Estado_CuentaTO.setNumero_cuenta((String)estadoCuenta.get("account")==null?"":(String)estadoCuenta.get("account"));
				comportamiento_Estado_CuentaTO.setSucursal_cuenta("Banca por Internet");
				
				comportamiento_Estado_CuentaTO.setSaldo_unidades_mes_anterior((String)estadoCuenta.get("P_Nm_SalUniMesAnt_1")==null?"":(String)estadoCuenta.get("P_Nm_SalUniMesAnt_1"));
				comportamiento_Estado_CuentaTO.setCompra_unidades_mes_actual((String)estadoCuenta.get("P_Nm_ComUniMesAct_2")==null?"":(String)estadoCuenta.get("P_Nm_ComUniMesAct_2"));
				comportamiento_Estado_CuentaTO.setVenta_unidades_mes_actual((String)estadoCuenta.get("P_Nm_VenUniMesAct_3")==null?"":(String)estadoCuenta.get("P_Nm_VenUniMesAct_3"));
				comportamiento_Estado_CuentaTO.setSaldo_unidades_mes_actual((String)estadoCuenta.get("P_Nm_SalUniMesAct_4")==null?"":(String)estadoCuenta.get("P_Nm_SalUniMesAct_4"));
				comportamiento_Estado_CuentaTO.setPrecio_unidad_dia_corte((String)estadoCuenta.get("P_Nm_PreUniDiaCor_5")==null?"":(String)estadoCuenta.get("P_Nm_PreUniDiaCor_5"));
				comportamiento_Estado_CuentaTO.setImporte_actual_moneda_nacional((String)estadoCuenta.get("P_Nm_ImpMesAct_6")==null?"":(String)estadoCuenta.get("P_Nm_ImpMesAct_6"));
				
				
				comportamiento_Estado_CuentaTO.setPrecio_unidad_mes_anterior((String)estadoCuenta.get("P_Nm_PreUniMesAnt_7")==null?"":(String)estadoCuenta.get("P_Nm_PreUniMesAnt_7"));
				comportamiento_Estado_CuentaTO.setPrecio_unidad_dia_corriente((String)estadoCuenta.get("P_Nm_PreUniDiaCor_5")==null?"":(String)estadoCuenta.get("P_Nm_PreUniDiaCor_5"));
				
				comportamiento_Estado_CuentaTO.setSaldo_anterior((String)estadoCuenta.get("P_Nm_SalMesAnt_9")==null?"":(String)estadoCuenta.get("P_Nm_SalMesAnt_9"));
				comportamiento_Estado_CuentaTO.setTotal_depositos((String)estadoCuenta.get("P_Nm_ImpDepMesAct_10")==null?"":(String)estadoCuenta.get("P_Nm_ImpDepMesAct_10"));
				comportamiento_Estado_CuentaTO.setTotal_retiros((String)estadoCuenta.get("P_Nm_ImpRetMesAct_11_PDF")==null?"":(String)estadoCuenta.get("P_Nm_ImpRetMesAct_11_PDF"));
				comportamiento_Estado_CuentaTO.setInteres_dia_corte((String)estadoCuenta.get("P_Nm_ImpIntMesAct_12")==null?"":(String)estadoCuenta.get("P_Nm_ImpIntMesAct_12"));
				comportamiento_Estado_CuentaTO.setSaldo_actual((String)estadoCuenta.get("P_Nm_ImpMesAct_6")==null?"":(String)estadoCuenta.get("P_Nm_ImpMesAct_6"));
				
				comportamiento_Estado_CuentaTO.setISR_retenido((String)estadoCuenta.get("P_Nm_IsrRetMesAct_13")==null?"":(String)estadoCuenta.get("P_Nm_IsrRetMesAct_13"));
				comportamiento_Estado_CuentaTO.setRendimiento_ultimos_dias((String)estadoCuenta.get("P_Nm_TasRenPer_15")==null?"":(String)estadoCuenta.get("P_Nm_TasRenPer_15") +"%" );
				
				Estado_cuentaTO estado_cuentaTO=new Estado_cuentaTO();
				estado_cuentaTO.setComportamiento_estado_cuenta_socio_plus(comportamiento_Estado_CuentaTO);
				
				response.addAttribute(estado_cuentaTO);
				
				session.addAttribute(ESTADO_CUENTA_RESPONSE, estado_cuentaTO);
			}else{
				Map estadoCuenta = estadoCuentaResponseTO.getEstadoCuenta();
				
				ArrayList<String[]>movimientos=(ArrayList<String[]>)estadoCuenta.get("movements");
				
				Detalle_MovimientoTO detalle_MovimientoTO=null;
				Collection<Detalle_MovimientoTO>coleccionMovimientos=new ArrayList<Detalle_MovimientoTO>();
				
				if(movimientos != null){
				for (String[] movimiento : movimientos) {
					detalle_MovimientoTO=new Detalle_MovimientoTO();
					String concepto= "";
					String fecha= "";
					String cargo = "";
				    String abono = "";
				    String saldoFinal = "";
				    String movimiento_final = "";
				
					 for(int i=0; i<movimiento.length; i++){
						 movimiento_final += movimiento[i];
				            if(i+1!=movimiento.length){
				            	movimiento_final+=",";
				            }
				     }
				             int eliminar = movimiento_final.indexOf(",");
						     fecha = movimiento_final.substring(0, eliminar);
						     detalle_MovimientoTO.setFecha_aplicacion(Formatter.removeSpacesLeftRight(fecha!=null?fecha:""));
						     
						     movimiento_final = movimiento_final.substring(eliminar+1);
						     eliminar = movimiento_final.indexOf(",");
						     concepto = movimiento_final.substring(0, eliminar);
						     detalle_MovimientoTO.setConcepto_movimiento(Formatter.removeSpacesLeftRight(concepto!=null?concepto:""));
						     
						     movimiento_final = movimiento_final.substring(eliminar);
						     movimiento_final = movimiento_final.replaceAll(",", "");
						     eliminar = movimiento_final.indexOf(".");
						     cargo = movimiento_final.substring(0, (eliminar+3));
						     detalle_MovimientoTO.setCargo(Formatter.removeSpacesLeftRight(cargo!=null?cargo:""));
						     
							    
						     movimiento_final = movimiento_final.substring(eliminar+3);
						     eliminar = movimiento_final.indexOf(".");
						     abono = movimiento_final.substring(0, (eliminar+3));
						     detalle_MovimientoTO.setAbono(Formatter.removeSpacesLeftRight(abono!=null?abono:""));
							    
						     
						     movimiento_final = movimiento_final.substring(eliminar+3);
						     saldoFinal = movimiento_final;
						     detalle_MovimientoTO.setSaldo_disponible(Formatter.removeSpacesLeftRight(saldoFinal!=null?saldoFinal:""));
						     
						     coleccionMovimientos.add(detalle_MovimientoTO);
				   }
				}
					
			
				Comportamiento_Estado_CuentaTO comportamiento_Estado_CuentaTO=new Comportamiento_Estado_CuentaTO();
				
				if(estadoCuenta.get("street")!=null){
					comportamiento_Estado_CuentaTO.setCalle(estadoCuenta.get("street").toString());
				}
				if(estadoCuenta.get("charge")!=null){
					comportamiento_Estado_CuentaTO.setTotal_cargos_retiros(estadoCuenta.get("charge").toString());
				}
				if(estadoCuenta.get("clabe")!=null){
					comportamiento_Estado_CuentaTO.setCuenta_clabe(estadoCuenta.get("clabe").toString());
				}
				if(estadoCuenta.get("zipcode")!=null){
				comportamiento_Estado_CuentaTO.setCodigo_postal(estadoCuenta.get("zipcode").toString());
				}
				if(estadoCuenta.get("neighborhood")!=null){
					comportamiento_Estado_CuentaTO.setColonia(estadoCuenta.get("neighborhood").toString());
				}
				if(estadoCuenta.get("account")!=null){
					comportamiento_Estado_CuentaTO.setCuenta(estadoCuenta.get("account").toString());
				}
				if(estadoCuenta.get("deposits")!=null){
					comportamiento_Estado_CuentaTO.setTotal_abonos_depositos(estadoCuenta.get("deposits").toString());
				}
				if(estadoCuenta.get("days")!=null){
					comportamiento_Estado_CuentaTO.setDias_transcurridos(estadoCuenta.get("days").toString());
				}
				if(estadoCuenta.get("datetime")!=null){
				comportamiento_Estado_CuentaTO.setFecha_hora(estadoCuenta.get("datetime").toString());
				}
				if(estadoCuenta.get("folio")!=null){
					comportamiento_Estado_CuentaTO.setFolio(estadoCuenta.get("folio").toString());
				}
				if(estadoCuenta.get("name")!=null){
					comportamiento_Estado_CuentaTO.setNombre_cliente(estadoCuenta.get("name").toString());
				}
				if(estadoCuenta.get("client")!=null){
					comportamiento_Estado_CuentaTO.setNumero_cliente(estadoCuenta.get("client").toString());
				}
				if(estadoCuenta.get("period")!=null){
					comportamiento_Estado_CuentaTO.setPeriodo(estadoCuenta.get("period").toString());
				}
				if(estadoCuenta.get("finalsaldo")!=null){
					comportamiento_Estado_CuentaTO.setSaldo_final(estadoCuenta.get("finalsaldo").toString());
				}
				if(estadoCuenta.get("prevsaldo")!=null){
					comportamiento_Estado_CuentaTO.setSaldo_previo(estadoCuenta.get("prevsaldo").toString());
				}
				if(estadoCuenta.get("avrgsaldo")!=null){
					comportamiento_Estado_CuentaTO.setSaldo_promedio(estadoCuenta.get("avrgsaldo").toString());
				}
				if(estadoCuenta.get("suc")!=null){
					comportamiento_Estado_CuentaTO.setSucursal(estadoCuenta.get("suc").toString());
				}
				if(estadoCuenta.get("tasabruta")!=null){
					comportamiento_Estado_CuentaTO.setTasa_bruta(estadoCuenta.get("tasabruta").toString());
				}
				
				comportamiento_Estado_CuentaTO.setColeccion_movimientos(coleccionMovimientos);
				
				
				Estado_cuentaTO estado_cuentaTO=new Estado_cuentaTO();
				estado_cuentaTO.setComportamiento_estado_cuenta(comportamiento_Estado_CuentaTO);
				
				response.addAttribute(estado_cuentaTO);
				
				session.addAttribute(ESTADO_CUENTA_RESPONSE, estado_cuentaTO);
			}
		}catch (EliteDataException e) {
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		return response;
	}
}