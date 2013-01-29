package com.bancoazteca.eservice.command.estadocuenta.mercadodinero;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.FechaAperturaReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.FechaAperturaReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosMovimientosEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosOperacionesDirectoEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosOperacionesEstadoCuentaResponseTO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Estado_cuenta_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.Estado_cuenta_mercado_dinero_solicitudTO;
import com.bancoazteca.eservice.command.base.beans.Estructura_carteraTO;
import com.bancoazteca.eservice.command.base.beans.Estructura_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_directoTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_inversion_reportoTO;
import com.bancoazteca.eservice.command.base.beans.Movimientos_inversion_directoTO;
import com.bancoazteca.eservice.command.base.beans.Regimen_operativo_fiscalTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EstadoCuentaMercadoDineroCommand extends CommandBase {
	
	public static final Logger log = Logger.getLogger(EstadoCuentaMercadoDineroCommand.class);
	enum meses { ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE };
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();	
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		EstadoCuentaMercadoDineroForm forma=(EstadoCuentaMercadoDineroForm)getFormBean();
		Estado_cuenta_mercado_dinero_solicitudTO respuestaSolicitud = new Estado_cuenta_mercado_dinero_solicitudTO();
		
		try{
			ResourceFacadeSegundoSL delegate = getDelegateSegundo();
			
			String numeroContrato = cliente.getNumContratoInvReportos();
			
			if ( !Validator.isEmptyData( numeroContrato ) ) {
				
				InversionesRequestTO requestCuenta=new InversionesRequestTO();
				requestCuenta.setIdAlnova(cliente.getNumero());
				requestCuenta.setOperation("busqueda_cuenta");
				requestCuenta.setType("inversion_cliente");
				InversionesResponseTO respuestaCuenta = getDelegateSegundo().dispatchManager(requestCuenta);
				String cuentaAsociada=respuestaCuenta.getMessage();
				CuentaTO cuentaAsociadaTO = new CuentaTO();			
				if(cuentaAsociada!=null && !cuentaAsociada.equals("0")){
					cuentaAsociada=Formatter.removeSpaces(formatearCuenta(cuentaAsociada));				
					cuentaAsociadaTO = getAccountsPrdicate(cliente.getCuentas(), cuentaAsociada);			
				}
				
				FechaAperturaReportosRequestTO reportosRequest = new FechaAperturaReportosRequestTO();
				reportosRequest.setOperation(InversionesRequestTO.OBTENCION_ANIOS_PERIODOS_REPORTOS);
				reportosRequest.setType(InversionesRequestTO.EMPRESA_REPORTOS);
				reportosRequest.setPortalSolicitante(forma.getPortal());
				reportosRequest.setIdAlnova(cliente.getNumero());
				reportosRequest.setNumeroContrato(cliente.getNumContratoInvReportos());
				
				FechaAperturaReportosResponseTO reportosResponse = (FechaAperturaReportosResponseTO) delegate.dispatchManager(reportosRequest);
				
				if( reportosResponse != null ) {
					
					respuestaSolicitud.setNumero_contrato( numeroContrato );
					respuestaSolicitud.setColeccion_anios( reportosResponse.getAnios() );
					Cuenta_CargoTO cuenta_eje = new Cuenta_CargoTO();
					cuenta_eje.setNumero_cuenta( Formatter.removeSpaces( formatearCuenta( cuentaAsociadaTO.getNumero() ) ) );
					cuenta_eje.setProducto( cuentaAsociadaTO.getDescripcion() );
					cuenta_eje.setSaldo_disponible( String.valueOf( cuentaAsociadaTO.getDisponible() ) );
					respuestaSolicitud.setCuenta_eje( cuenta_eje );
								
					session.addAttribute( ESTADO_CUENTA_REPORTOS, respuestaSolicitud );
					
					response.addAttribute(respuestaSolicitud);
					
				}else{
					Map<String, String> errors = new HashMap<String, String>();
					errors.put( "periodo", "Lo sentimos, por el momento no se puede gerenar el Estado de Cuenta" );
					throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
				}
				
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "numero_contrato", "Lo sentimos, usted no cuenta con un contrato de Mercado de Dinero" );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
			}
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}catch(InversionesGenericException e){
			return returnErrorMap(response, e.getInversionesExceptionTO().getErrorMap(),e.getInversionesExceptionTO().getMessage());
		}		
		return response;
	}
	
	public Response validacion(Session session) throws Exception {
		Response response = new Response();	
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		EstadoCuentaMercadoDineroForm forma=(EstadoCuentaMercadoDineroForm)getFormBean();
		Estado_cuenta_mercado_dinero_solicitudTO respuestaSolicitud = (Estado_cuenta_mercado_dinero_solicitudTO)session.getAttribute(ESTADO_CUENTA_REPORTOS);
		
		try{
			ResourceFacadeSegundoSL delegate = getDelegateSegundo();
			String numeroContrato = cliente.getNumContratoInvReportos();
			
			if( respuestaSolicitud == null ){
				solicitud(session);
				respuestaSolicitud = (Estado_cuenta_mercado_dinero_solicitudTO)session.getAttribute(ESTADO_CUENTA_REPORTOS);
			}
			
			String anio = Formatter.removeSpaces( forma.getAnio_periodo() );
			if(!respuestaSolicitud.getColeccion_anios().contains(anio)){
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "anio_periodo", "Lo sentimos, el anio seleccionado es incorrecto." );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
			}
			
			if ( !Validator.isEmptyData( numeroContrato ) ) {
				
				FechaAperturaReportosRequestTO reportosRequest = new FechaAperturaReportosRequestTO();
				reportosRequest.setOperation(InversionesRequestTO.OBTENCION_MESES_PERIODOS_REPORTOS);
				reportosRequest.setType(InversionesRequestTO.EMPRESA_REPORTOS);
				reportosRequest.setAnioPeriodo(anio);
				
				FechaAperturaReportosResponseTO reportosResponse = (FechaAperturaReportosResponseTO) delegate.dispatchManager(reportosRequest);
				
				if( reportosResponse != null ) {
					Collection<String> anioSeleccionado = new ArrayList<String>();
					anioSeleccionado.add(anio);
					respuestaSolicitud.setColeccion_anios(anioSeleccionado);
					respuestaSolicitud.setColeccion_meses(reportosResponse.getMeses());								
					response.addAttribute(respuestaSolicitud);
					
					session.addAttribute( ESTADO_CUENTA_REPORTOS, respuestaSolicitud );
					
					
				}else{
					Map<String, String> errors = new HashMap<String, String>();
					errors.put( "periodo", "Lo sentimos, por el momento no se puede gerenar el Estado de Cuenta" );
					throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
				}
				
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "numero_contrato", "Lo sentimos, usted no cuenta con un contrato de Mercado de Dinero" );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
			}
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}catch(InversionesGenericException e){
			return returnErrorMap(response, e.getInversionesExceptionTO().getErrorMap(),e.getInversionesExceptionTO().getMessage());
		}		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Estado_cuenta_mercado_dinero_solicitudTO respuestaSolicitud = (Estado_cuenta_mercado_dinero_solicitudTO)session.getAttribute(ESTADO_CUENTA_REPORTOS);
		
		EstadoCuentaMercadoDineroForm forma = (EstadoCuentaMercadoDineroForm)getFormBean();		
		ReportosEstadoCuentaRequestTO requestTO = new ReportosEstadoCuentaRequestTO();
				
		try{
			String anio = Formatter.removeSpaces( forma.getAnio_periodo() );
			String mes = Formatter.removeSpaces( forma.getMes_periodo() ).toUpperCase();
			
			if( respuestaSolicitud != null ){	
				if(!respuestaSolicitud.getColeccion_anios().contains(anio)){
					Map<String, String> errors = new HashMap<String, String>();
					errors.put( "anio_periodo", "Lo sentimos, el anio seleccionado es incorrecto." );
					throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
				}
				if(!respuestaSolicitud.getColeccion_meses().contains(mes)){
					Map<String, String> errors = new HashMap<String, String>();
					errors.put( "mes_periodo", "Lo sentimos, el mes seleccionado es incorrecto." );
					throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
				}
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "periodo", "Lo sentimos, por el momento no se puede gerenar el Estado de Cuenta" );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
			}
			
			String fechaPeriodo = getFechaPeriodo( forma.getMes_periodo(), forma.getAnio_periodo() );
			requestTO.setFechaEstadoCuenta(fechaPeriodo);
			requestTO.setNumeroContrato(cliente.getNumContratoInvReportos());
			requestTO.setNumeroIdentificacionAlnova(cliente.getNumero());
			requestTO.setPortal(forma.getPortal());
			
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			ReportosEstadoCuentaResponseTO estadoCuentaResponseTO = resourceFacadeSL.estadoCuentaMercadoDineroEjecucion(requestTO);
			
			Estado_cuenta_mercado_dineroTO estado_cuentaTO = new Estado_cuenta_mercado_dineroTO();
			
			if( estadoCuentaResponseTO != null ) {
				
				estado_cuentaTO.setNombre_cliente(estadoCuentaResponseTO.getNombreCliente());
				estado_cuentaTO.setFecha_inicio_periodo(estadoCuentaResponseTO.getPeriodoInicial());
				estado_cuentaTO.setFecha_fin_periodo(estadoCuentaResponseTO.getPeriodoFina());
				estado_cuentaTO.setTipo_moneda(estadoCuentaResponseTO.getDivisa());
				estado_cuentaTO.setNombre_asesor(estadoCuentaResponseTO.getPromotor());
				estado_cuentaTO.setRfc_cliente(estadoCuentaResponseTO.getRFC());
				estado_cuentaTO.setNumero_contrato(estadoCuentaResponseTO.getNumeroContrato());
				estado_cuentaTO.setCalle(estadoCuentaResponseTO.getDireccion());
				estado_cuentaTO.setColonia(estadoCuentaResponseTO.getColonia());
				estado_cuentaTO.setCodigo_postal(estadoCuentaResponseTO.getCodigoPostal());
				estado_cuentaTO.setCiudad(estadoCuentaResponseTO.getCiudad());
				
				Estructura_carteraTO estructura_cartera = new Estructura_carteraTO();
				Estructura_mercado_dineroTO estructuraMesActual = new Estructura_mercado_dineroTO();
				Estructura_mercado_dineroTO estructuraMesAnterior = new Estructura_mercado_dineroTO();
				
				estructuraMesAnterior.setInversion_directo(estadoCuentaResponseTO.getCarteraAnteriorDirecto());
				estructuraMesAnterior.setPorcentaje_inversion_directo(estadoCuentaResponseTO.getPorcentajeCarteraAnteriorDirecto());
				estructuraMesAnterior.setInversion_reporto(estadoCuentaResponseTO.getCarteraAnteriorReporto());
				estructuraMesAnterior.setPorcentaje_inversion_reporto(estadoCuentaResponseTO.getPorcentajeCarteraAnteriorReporto());
				estructuraMesAnterior.setInversion_efectivo(estadoCuentaResponseTO.getCarteraAnteriorEfectivo());
				estructuraMesAnterior.setPorcentaje_inversion_efectivo(estadoCuentaResponseTO.getPorcentajeCarteraAnteriorEfectivo());
				
				estructuraMesActual.setInversion_directo(estadoCuentaResponseTO.getCarteraActualDirecto());
				estructuraMesActual.setPorcentaje_inversion_directo(estadoCuentaResponseTO.getPorcentajeCarteraActualDirecto());
				estructuraMesActual.setInversion_reporto(estadoCuentaResponseTO.getCarteraActualReporto());
				estructuraMesActual.setPorcentaje_inversion_reporto(estadoCuentaResponseTO.getPorcentajeCarteraActualReporto());
				estructuraMesActual.setInversion_efectivo(estadoCuentaResponseTO.getCarteraActualEfectivo());
				estructuraMesActual.setPorcentaje_inversion_efectivo(estadoCuentaResponseTO.getPorcentajeCarteraActualEfectivo());
				
				estructura_cartera.setMes_actual(estructuraMesActual);
				estructura_cartera.setMes_anterior(estructuraMesAnterior);
				
				
				Regimen_operativo_fiscalTO regimen_operativo_fiscal = new Regimen_operativo_fiscalTO();
				regimen_operativo_fiscal.setCartera_mes_actual(estadoCuentaResponseTO.getCarteraMesActual());
				regimen_operativo_fiscal.setMas_entradas(estadoCuentaResponseTO.getMasEntradas());
				regimen_operativo_fiscal.setMenos_salidas(estadoCuentaResponseTO.getMenosSalidas());
				regimen_operativo_fiscal.setCartera_mes_anterior(estadoCuentaResponseTO.getCarteraMesAnterior());
				regimen_operativo_fiscal.setVariacion_mesual(estadoCuentaResponseTO.getVariacionMensual());
				regimen_operativo_fiscal.setIsr_retenido(estadoCuentaResponseTO.getIsrRetenido());
				regimen_operativo_fiscal.setIva_acreditable(estadoCuentaResponseTO.getIvaAcreditable());
				
				Collection<Movimientos_inversion_directoTO> colecionMovimientosDirecto = new ArrayList<Movimientos_inversion_directoTO>();
				Movimientos_inversion_directoTO movimiento_directo;
				Inversion_directoTO inversionMesActual;
				Inversion_directoTO inversionMesAnterior;
				String total_general_mes_actual = null;
				String total_general_mes_anterior = null;
				
				if ( estadoCuentaResponseTO.getOperacionesDirecto() != null ) {
					for ( ReportosOperacionesDirectoEstadoCuentaResponseTO mercadoDirecto : estadoCuentaResponseTO.getOperacionesDirecto()){
						movimiento_directo = new Movimientos_inversion_directoTO();
						inversionMesActual = new Inversion_directoTO();
						inversionMesAnterior = new Inversion_directoTO();
						
						movimiento_directo.setEmisora(mercadoDirecto.getNombreEmisora());
						movimiento_directo.setSerie(mercadoDirecto.getSerie());
						
						total_general_mes_actual = mercadoDirecto.getTotalGeneralMesActual();
						total_general_mes_anterior = mercadoDirecto.getTotalGeneralMesAnterior();
												
						inversionMesActual.setNumero_titulos(mercadoDirecto.getTasaMesAnterior());
						inversionMesActual.setPrecio(mercadoDirecto.getPrecioMesAnterior());
						inversionMesActual.setIntereses(mercadoDirecto.getInteresesMesAnterior());
						inversionMesActual.setTasa(mercadoDirecto.getTasaMesAnterior());
						inversionMesActual.setTotal(mercadoDirecto.getTotalMesAnterior());
						
						inversionMesAnterior.setNumero_titulos(mercadoDirecto.getTitulosMesActual());
						inversionMesAnterior.setPrecio(mercadoDirecto.getPrecioMesActual());
						inversionMesAnterior.setIntereses(mercadoDirecto.getInteresesMesActual());
						inversionMesAnterior.setTasa(mercadoDirecto.getTasaMesActual());
						inversionMesAnterior.setTotal(mercadoDirecto.getTotalMesActual());
						
						movimiento_directo.setMes_actual(inversionMesActual);
						movimiento_directo.setMes_anterior(inversionMesAnterior);
						
						colecionMovimientosDirecto.add(movimiento_directo);
					}
					estado_cuentaTO.setTotal_invertido_directo_mes_actual(total_general_mes_actual);
					estado_cuentaTO.setTotal_invertido_directo_mes_anterior(total_general_mes_anterior);
				}
				
				Collection<Movimiento_inversion_reportoTO> colecionMovimientosReporto = new ArrayList<Movimiento_inversion_reportoTO>();
				if ( estadoCuentaResponseTO.getOperacionesReportos() != null ) {
					for ( ReportosOperacionesEstadoCuentaResponseTO mercadoReporto : estadoCuentaResponseTO.getOperacionesReportos()){
						
						String totalInvertido = mercadoReporto.getTotalInvertido();
						String totalValorCorte = mercadoReporto.getTotalValorCorte();
						
						if( ( !Validator.isEmptyData( totalInvertido ) ) || ( !Validator.isEmptyData( totalValorCorte ) ) ){
							estado_cuentaTO.setTotal_invertido_reporto(totalInvertido);
							estado_cuentaTO.setTotal_valor_corte_reporto(totalValorCorte);
						}else{
							Movimiento_inversion_reportoTO movimiento_reporto = new Movimiento_inversion_reportoTO();
							movimiento_reporto.setFecha_inicio(mercadoReporto.getFechaInicio());
							movimiento_reporto.setFecha_vencimiento(mercadoReporto.getFechaVencimiento());
							movimiento_reporto.setPlazo(mercadoReporto.getPlazo());
							movimiento_reporto.setTasa_premio(mercadoReporto.getTasaPremio());
							movimiento_reporto.setMonto_invertido(mercadoReporto.getMontoInvertido());
							movimiento_reporto.setEmisora(mercadoReporto.getEmisora());
							movimiento_reporto.setSerie(mercadoReporto.getSerie());
							movimiento_reporto.setNumero_titulos(mercadoReporto.getTitulos());
							movimiento_reporto.setPremio_devengado(mercadoReporto.getPremioDevengado());
							movimiento_reporto.setValor_corte(mercadoReporto.getValorCorte());
							colecionMovimientosReporto.add(movimiento_reporto);							
						}
					}
				}
				
				Collection<Movimiento_Mercado_DineroTO> colecionDetalleMovimientos = new ArrayList<Movimiento_Mercado_DineroTO>();
				if ( estadoCuentaResponseTO.getMovimientos() != null ) {
					for ( ReportosMovimientosEstadoCuentaResponseTO movimiento : estadoCuentaResponseTO.getMovimientos()){
						Movimiento_Mercado_DineroTO detalle_movimiento = new Movimiento_Mercado_DineroTO();
						detalle_movimiento.setFecha_liquidacion(movimiento.getFechaLiquidacion());
						detalle_movimiento.setFecha_operacion(movimiento.getFechaOperacion());
						detalle_movimiento.setPlazo(movimiento.getPlazo());
						detalle_movimiento.setConcepto(movimiento.getConcepto());
						detalle_movimiento.setEmision(movimiento.getEmisionSerie());
						detalle_movimiento.setNumero_titulos(movimiento.getTitulos());
						detalle_movimiento.setPrecio(movimiento.getPrecio());
						detalle_movimiento.setMonto_bruto(movimiento.getImporteBruto());
						detalle_movimiento.setIva_isr(movimiento.getIvaIsr());
						detalle_movimiento.setTasa(movimiento.getTasa());
						detalle_movimiento.setMonto_neto(movimiento.getImporteNeto());
						detalle_movimiento.setSaldo(movimiento.getSaldo());
						colecionDetalleMovimientos.add(detalle_movimiento);
					}
					
				}
				
				estado_cuentaTO.setEstructura_cartera(estructura_cartera);
				estado_cuentaTO.setRegimen_operativo_fiscal(regimen_operativo_fiscal);
				estado_cuentaTO.setColeccion_inversion_directo(colecionMovimientosDirecto);
				estado_cuentaTO.setColeccion_inversion_reporto(colecionMovimientosReporto);
				estado_cuentaTO.setColeccion_movimientos(colecionDetalleMovimientos);
				
				response.addAttribute(estado_cuentaTO);	
				
//				estado_cuentaTO.setNombre_cliente(estadoCuentaResponseTO.getNombreCliente());
//				estado_cuentaTO.setPeriodo_inicial(estadoCuentaResponseTO.getPeriodoInicial());
////				estado_cuentaTO.setPeriodo_final(estadoCuentaResponseTO.getPeriodoFinal());
//				estado_cuentaTO.setMoneda(estadoCuentaResponseTO.getDivisa());
//				estado_cuentaTO.setAsesor(estadoCuentaResponseTO.getPromotor());
//				estado_cuentaTO.setRfc(estadoCuentaResponseTO.getRFC());
//				estado_cuentaTO.setContrato(estadoCuentaResponseTO.getNumeroContrato());
//				estado_cuentaTO.setDireccion(estadoCuentaResponseTO.getDireccion());
//				estado_cuentaTO.setColonia(estadoCuentaResponseTO.getColonia());
//				estado_cuentaTO.setCiudad(estadoCuentaResponseTO.getCiudad());
//				estado_cuentaTO.setCodigo_postal(estadoCuentaResponseTO.getCodigoPostal());
//				
//				estado_cuentaTO.setCartera_mes_anterior(estadoCuentaResponseTO.getCarteraMesAnterior());
////				estado_cuentaTO.setEntradas(estadoCuentaResponseTO.getEntradas());
////				estado_cuentaTO.setSalidas(estadoCuentaResponseTO.getSalidas());
//				estado_cuentaTO.setCartera_mes_actual(estadoCuentaResponseTO.getCarteraMesActual());
////				estado_cuentaTO.setVariacion(estadoCuentaResponseTO.getVariacion());
//				
////				estado_cuentaTO.setIsr(estadoCuentaResponseTO.getIsr());
////				estado_cuentaTO.setIva(estadoCuentaResponseTO.getIva());
//				
//				Collection<Movimiento_Mercado_DineroTO> coleccionMovimientos=new ArrayList<Movimiento_Mercado_DineroTO>();
//
//				for ( ReportosMovimientosEstadoCuentaResponseTO movimiento : estadoCuentaResponseTO.getMovimientos()) {
//					Movimiento_Mercado_DineroTO mov = new Movimiento_Mercado_DineroTO();
//					mov.setFecha_liquidacion(movimiento.getFechaLiquidacion());
//					mov.setFecha_operacion(movimiento.getFechaOperacion());
//					mov.setPlazo(movimiento.getPlazo());
//					mov.setConcepto(movimiento.getConcepto());
//					mov.setEmision(movimiento.getSerieEmision());
//					mov.setTitulos(movimiento.getTitulos());
//					mov.setPrecio(movimiento.getPrecio());
//					mov.setMonto_bruto(movimiento.getImporteBruto());
//					mov.setIva_isr(movimiento.getIvaIsr());
//					mov.setTasa(movimiento.getTasa());
//					mov.setMonto_neto(movimiento.getImporteNeto());
//					mov.setSaldo(movimiento.getSaldo());
//					
//					coleccionMovimientos.add(mov);
//				}
//				
//				estado_cuentaTO.setColeccion_movimientos(coleccionMovimientos);
//				
//				response.addAttribute(estado_cuentaTO);	
//				response.addAttribute(resumen_Operaciones_Mercado_DineroTO);
						
			}			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}catch(InversionesGenericException e){
			return returnErrorMap(response, e.getInversionesExceptionTO().getErrorMap(),e.getInversionesExceptionTO().getMessage());
		}		

		return response;
	}
	
	public static String getFechaPeriodo(String mes, String anio){
		Locale LOCALE_MX = new Locale("es","mx");
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd", LOCALE_MX );
		
		mes = Formatter.removeSpaces( mes );
		anio = Formatter.removeSpaces( anio );
		
		int mesNumerico = 0;
		int ultimoDiaMes = 0;
		String fechaPeriodo = "";
		try{
			Calendar fecha = GregorianCalendar.getInstance( LOCALE_MX );
			mesNumerico = getNumeroMes(mes);
			fecha.set(Integer.valueOf( anio ), mesNumerico, 1);
			ultimoDiaMes = fecha.getActualMaximum(Calendar.DATE);
			fecha.set(Integer.valueOf( anio ), mesNumerico, ultimoDiaMes);
			fechaPeriodo = dateFormat.format( fecha.getTime() );
			log.info("Fecha solicitada: " + fechaPeriodo);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return fechaPeriodo;
	}
	
	
	public static int getNumeroMes(String mes) throws Exception{
		int mesNumerico = 0;
		try {
			mesNumerico = meses.valueOf( mes.toUpperCase() ).ordinal();
		} catch (Exception e) {
			log.info("El mes " + mes + " no existe");
			throw e;
		}
		return mesNumerico;
	}
	
}