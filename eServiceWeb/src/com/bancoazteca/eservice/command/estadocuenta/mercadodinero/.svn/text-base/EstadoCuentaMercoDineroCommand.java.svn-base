package com.bancoazteca.eservice.command.estadocuenta.mercadodinero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
//import com.bancoazteca.elite.beans.ReportosEstadoCuentaSolicitudRequestTO;
//import com.bancoazteca.elite.beans.ReportosEstadoCuentaSolicitudResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
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
import com.bancoazteca.eservice.command.base.beans.Estado_cuenta_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.Estado_cuenta_mercado_dinero_solicitudTO;
import com.bancoazteca.eservice.command.base.beans.Estructura_carteraTO;
import com.bancoazteca.eservice.command.base.beans.Estructura_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_directoTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.Movimientos_inversion_directoTO;
import com.bancoazteca.eservice.command.base.beans.Movimiento_inversion_reportoTO;
import com.bancoazteca.eservice.command.base.beans.Regimen_operativo_fiscalTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.cuentas.mercadodinero.CuentaMercadoDineroCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class EstadoCuentaMercoDineroCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(EstadoCuentaMercoDineroCommand.class);
	private static final String CUENTA_ASOCIADA="CUENTA_ASOCIADA";
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();	
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
//		ReportosEstadoCuentaSolicitudRequestTO requestTO = new ReportosEstadoCuentaSolicitudRequestTO();
		
		//TODO: obtenerlo del cliente
//		requestTO.setIdAlnova("05513697");
		
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
//			ReportosEstadoCuentaSolicitudResponseTO estadoCuentaResponseTO = resourceFacadeSL.estadoCuentaMercadoDineroSolicitud(requestTO);
			
			Estado_cuenta_mercado_dinero_solicitudTO solicitudTO = new Estado_cuenta_mercado_dinero_solicitudTO();
			
//			Collection<String> anios = estadoCuentaResponseTO.getAnios();
//			solicitudTO.setColeccion_anios(anios);
			
			InversionesRequestTO requestCuenta=new InversionesRequestTO();
			//TODO: cmc obtenerlo de cliente
			requestCuenta.setIdAlnova("05513697");
			requestCuenta.setOperation("busqueda_cuenta");
			requestCuenta.setType("inversion_cliente");
			
			Collection<CuentaTO> cuentas = cliente.getCuentas();
			InversionesResponseTO respuestaCuenta = getDelegateSegundo().dispatchManager(requestCuenta);
			String cuentaAsociada=respuestaCuenta.getMessage();

			if(cuentaAsociada!=null && !cuentaAsociada.equals("0")){
				
				cuentaAsociada=Formatter.removeSpaces(formatearCuenta(cuentaAsociada));				
				
				CuentaTO cuentaAsociadaTO = getAccountsPrdicate(cuentas, cuentaAsociada);
				cuentas.clear();
				cuentas.add(cuentaAsociadaTO);
				
//				solicitudTO.setColeccion_cuentas(cuentas);
				session.addAttribute(CUENTA_ASOCIADA,cuentaAsociadaTO);
			}
			
			response.addAttribute(solicitudTO);	
//		}catch (EliteDataException e) {
//			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
//				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
//				response.addAttribute(mensajesTO);
//				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
//			}
		}catch(InversionesGenericException exception){
			response = returnErrorMap(response, exception.getInversionesExceptionTO().getErrorMap());
			response.setStatus(Errors.VALIDATION_CODE, exception.getMessage(), null);
		}		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		EstadoCuentaMercadoDineroForm forma = (EstadoCuentaMercadoDineroForm)getFormBean();		
		ReportosEstadoCuentaRequestTO requestTO = new ReportosEstadoCuentaRequestTO();
		
//		requestTO.setFechaEstadoCuenta(forma.getFecha());
//		requestTO.setNumeroContrato(forma.getNumero_contrato());
//		requestTO.setNumeroIdentificacionAlnova(forma.getId_alnova());
//		requestTO.setPortal(forma.getPortal());
		
		
		requestTO.setFechaEstadoCuenta("2010/05/31");
		requestTO.setNumeroContrato("30000000");
		requestTO.setNumeroIdentificacionAlnova("05513697");
		requestTO.setPortal("Elite");
		
		try{
			CuentaMercadoDineroCommand mercadoDinero = new CuentaMercadoDineroCommand();
//			Resumen_Operaciones_Mercado_DineroTO resumen_Operaciones_Mercado_DineroTO = mercadoDinero.ejecutaResumenOperacines(cliente, InversionesRequestTO.OPERACION_RESUMEN_OPERACIONES_VIGENTES,forma.getPortal());

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
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}catch(InversionesGenericException exception){
			response = returnErrorMap(response, exception.getInversionesExceptionTO().getErrorMap());
			response.setStatus(Errors.VALIDATION_CODE, exception.getInversionesExceptionTO().getMessage(), null);
		}
		return response;
	}
}