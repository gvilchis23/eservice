package com.bancoazteca.eservice.command.chequera.roboextravio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ChequeraRoboExtravioRequestTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.TalonarioTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Catalogo_tipo_cancelacionTO;
import com.bancoazteca.eservice.command.base.beans.ChequeraTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequera_roboTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequera_robo_validacionTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequerasTO;
import com.bancoazteca.eservice.command.base.beans.Robo_extravio_motivo_reporteTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class RoboExtravioCommand extends CommandBase {
	private static final String MOTIVO_REPORTE_ROBO="ROBO";
	private static final String MOTIVO_REPORTE_EXTRAVIO="EXTRAVIO";
	private static final String TIPO_CANCELACION_CHEQUE= "CHEQUE";
	private static final String TIPO_CANCELACION_CHEQUERA= "CHEQUERAS";
	private static final String TIPO_REPORTE_CHEQUE="INDIVIDUAL";
	private static final String TIPO_REPORTE_CHEQUES="BLOQUE";
	
		
	public static final Logger log = Logger.getLogger(RoboExtravioCommand.class);

	public Response solicitud(Session session) throws Exception{		
		Response response = new Response();
		RoboExtravioForm forma= (RoboExtravioForm) getFormBean();
		
		if(forma.getTipo_solicitud().equalsIgnoreCase("obtener_cuentas")){
			response = obtener_cuentas(session);
		}else if(forma.getTipo_solicitud().equalsIgnoreCase("obtener_resumen_chequeras")){
			response = obtener_resumen_chequeras(session);
		}else if(forma.getTipo_solicitud().equalsIgnoreCase("obtener_fecha_motivo_reporte")){
			response = obtener_fecha_motivo_reporte(session);
		}
		return response;
	}
	
	// solicitud obtiene cuentas
	public Response obtener_cuentas(Session session)throws Exception{
		Response response = new Response();
		CuentaTO cuentaAux = null;
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ChequeraRoboExtravioRequestTO requestTO=new ChequeraRoboExtravioRequestTO();
			requestTO.setUser( clienteTO.getAlias() );
			ResourceFacadeSegundoSL facade = getDelegateSegundo();
			
			Iterator<CuentaTO> iterator = clienteTO.getCuentas().iterator();
			cuentaAux = iterator.next();
			
			requestTO=new ChequeraRoboExtravioRequestTO();
			requestTO.setUser( clienteTO.getAlias() );
			requestTO.setNumeroCuenta( cuentaAux.getNumero() + "-" + cuentaAux.getDescripcion() );
			requestTO.setFechaSolicitud(Formatter.formatFecha("dd'-'MMMM'-'yyyy", new Date()));

			ChequeraRoboExtravioResponseTO responseTO = facade.getRoboExtravioInicio( requestTO );

			Cuentas_CargoTO cuentasResponse = new Cuentas_CargoTO();
			Cuenta_CargoTO cuentaCargo;
			Collection<Cuenta_CargoTO> listaCargos = new ArrayList<Cuenta_CargoTO>();
			
			if( (responseTO!=null) && (responseTO.getCuentas()!=null)){
				for( CuentaTO cuenta : responseTO.getCuentas() ){
					cuentaCargo = new Cuenta_CargoTO();
					cuentaCargo.setNumero_cuenta( Formatter.formatCuenta(cuenta.getNumero() ));
					cuentaCargo.setProducto( cuenta.getDescripcion() );
					cuentaCargo.setSaldo_disponible( cuenta.getDisponible().toString() );
					listaCargos.add( cuentaCargo );
				}
				cuentasResponse.setColeccion_cuentas( listaCargos );
			}		
			
			response.addAttribute( cuentasResponse );
			
			facade.getExtravioObtencionChequera( requestTO );

			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}  
		return response;
	}
	// solicitud de chequera (tipo de cancelacion cheque o chequera)
	public Response obtener_resumen_chequeras(Session session) throws Exception{		
		Response response = new Response();
		RoboExtravioForm forma = (RoboExtravioForm)getFormBean();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			String numeroCuenta = forma.getNumero_cuenta();	
			com.bancoazteca.elite.beans.CuentaTO cuentaTO = getAccountsPrdicate(clienteTO.getCuentas(), numeroCuenta);
			if(cuentaTO!=null){
				ChequeraRoboExtravioRequestTO requestTO=new ChequeraRoboExtravioRequestTO();
				requestTO.setUser( clienteTO.getAlias() );
				requestTO.setNumeroCuenta(cuentaTO.getNumero() + "-" + cuentaTO.getDescripcion());
				requestTO.setFechaSolicitud(Formatter.formatFecha("dd'-'MMMM'-'yyyy", new Date()));
				ResourceFacadeSegundoSL facade = getDelegateSegundo();
				
				ChequeraRoboExtravioResponseTO responseTO = facade.getExtravioObtencionChequera(requestTO );
				Detalle_chequerasTO resumen_chequerasTO = new Detalle_chequerasTO();
				
				ChequeraTO chequera = new ChequeraTO();
				Collection<ChequeraTO> lista_cheques = new ArrayList<ChequeraTO>();
				
				if( (responseTO!=null) && (responseTO.getChequerascuenta()!=null)){
					for( TalonarioTO talonario: responseTO.getChequerascuenta() ){
						chequera = new ChequeraTO();
						chequera.setCheque_final(String.valueOf(talonario.getChequeFinal()));
						chequera.setCheque_inicial(""+talonario.getChequeInicial());
						chequera.setDescripcion(talonario.getDescripcionChequera());
						chequera.setEstado(talonario.getDescStatus());
						chequera.setRegimen_firmas(talonario.getRegimenFirmas());
						chequera.setImporte_piso(""+talonario.getImportePiso());
						chequera.setProteccion_chequera(talonario.getProteccion());
						lista_cheques.add( chequera );
					}
					resumen_chequerasTO.setColeccion_chequeras(lista_cheques);

					Cuenta_CargoTO cuenta_CargoTO = new Cuenta_CargoTO();
					String[] cuenta = responseTO.getCuenta().split( "-" );
					cuenta_CargoTO.setNumero_cuenta( Formatter.formatCuenta( cuenta[0] ) );
					cuenta_CargoTO.setProducto(responseTO.getTipoCuenta());
								
					response.addAttribute( cuenta_CargoTO );
					response.addAttribute( resumen_chequerasTO );
					
					Collection<String> listaTipoCancelacion = new ArrayList<String>();
					listaTipoCancelacion.add(TIPO_CANCELACION_CHEQUERA );
					listaTipoCancelacion.add(TIPO_CANCELACION_CHEQUE );
					Catalogo_tipo_cancelacionTO catalogo_tipo_cancelacionTO = new Catalogo_tipo_cancelacionTO();
					catalogo_tipo_cancelacionTO.setColeccion_tipo_cancelacion(listaTipoCancelacion);
					
					response.addAttribute(catalogo_tipo_cancelacionTO);
				}else{
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("detalle_chequeras", "El número de cuenta proporcionado no tiene chequeras.");
					throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
				}				
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("numero_cuenta", "El número de cuenta proporcionado es incorrecto.");
				throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}  
		return response;
	}
	// SOLICITUD DE FECHA Y MOTIVO (y aqui me devuelve toda la informacion del cliente)
	public Response obtener_fecha_motivo_reporte(Session session) throws Exception{	
		Response response = new Response();
		RoboExtravioForm forma = (RoboExtravioForm)getFormBean();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			String numeroCuenta = forma.getNumero_cuenta();	
			com.bancoazteca.elite.beans.CuentaTO cuentaTO = getAccountsPrdicate(clienteTO.getCuentas(), numeroCuenta);
			if(cuentaTO!=null){
				ChequeraRoboExtravioRequestTO requestTO=new ChequeraRoboExtravioRequestTO();
				requestTO.setUser( clienteTO.getAlias() );
				requestTO.setNumeroCuenta(cuentaTO.getNumero() + "-" + cuentaTO.getDescripcion());
				requestTO.setFechaSolicitud(Formatter.formatFecha("dd'-'MMMM'-'yyyy", new Date()));
				requestTO.setIdChequera(forma.getCheque_inicial()+ "-" + forma.getCheque_final()+ ","+ forma.getDescripcion_chequera()+"?"+forma.getEstado_chequera());
			
				ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( forma.getTipo_cancelacion().toUpperCase() );
				session.addAttribute("reporteRoboTipoSolicitud", tipoSolicitud);
				requestTO.setTipoOperacion(tipoSolicitud);
				
				ResourceFacadeSegundoSL facade = getDelegateSegundo();
								
				ChequeraRoboExtravioResponseTO responseTO = facade.getExtravioTipoOperacion(requestTO );
				Detalle_chequera_roboTO detalle_chequera_roboTO = new Detalle_chequera_roboTO();
				
				detalle_chequera_roboTO.setNombre_cliente(clienteTO.getNombreCompleto());
				detalle_chequera_roboTO.setNumero_cuenta( Formatter.removeSpaces( responseTO.getCuentaFormat() ) );
				detalle_chequera_roboTO.setTipo_cuenta(responseTO.getCuentaDescripcion());
				detalle_chequera_roboTO.setCheque_inicial(responseTO.getChequeInicial());
				detalle_chequera_roboTO.setCheque_final(responseTO.getChequeFinal());
				detalle_chequera_roboTO.setEstado_chequera(responseTO.getEstatusChequera());
				detalle_chequera_roboTO.setDescripcion_chequera(responseTO.getDescChequera());
				detalle_chequera_roboTO.setTipo_cancelacion(tipoSolicitud);
				
				
				Collection<String> motivoReporte = new ArrayList<String>();
				motivoReporte.add(MOTIVO_REPORTE_ROBO);
				motivoReporte.add(MOTIVO_REPORTE_EXTRAVIO);
				Robo_extravio_motivo_reporteTO robo_extravio_motivo_reporte = new Robo_extravio_motivo_reporteTO();
				robo_extravio_motivo_reporte.setColeccion_motivos_reporte(motivoReporte);
				
				response.addAttribute(detalle_chequera_roboTO);
				response.addAttribute(robo_extravio_motivo_reporte);
				
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("numero_cuenta", "El número de cuenta proporcionado es incorrecto.");
				throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}  
		return response;
	}
	// VALIDACION 
	public Response validacion (Session session) throws Exception{		
		log.info(">> ---Entra a Validacion RoboExtravio Chequera --- <<");
		Response response = new Response();
		RoboExtravioForm forma = (RoboExtravioForm)getFormBean();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ChequeraRoboExtravioRequestTO requestTO=new ChequeraRoboExtravioRequestTO();
			requestTO.setUser( clienteTO.getAlias() );
			
			if(forma.getMotivo_reporte().equalsIgnoreCase(MOTIVO_REPORTE_ROBO)){
				requestTO.setMotivoBloqueoRobo("0");
			}else if(forma.getMotivo_reporte().equalsIgnoreCase(MOTIVO_REPORTE_EXTRAVIO)){
				requestTO.setMotivoBloqueoRobo("1");
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("motivo_reporte", "El motivo del reporte proporcionado no es correcto");
				throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
			
			
			
			ChequesEnum.TipoSolicitudEnum tipoSolicitud;  
			tipoSolicitud = (ChequesEnum.TipoSolicitudEnum) session.getAttribute("reporteRoboTipoSolicitud");
			requestTO.setTipoOperacion(tipoSolicitud);
			
			if(tipoSolicitud.toString().equalsIgnoreCase(TIPO_CANCELACION_CHEQUERA)){
				
				requestTO.setFechaSolicitud(Formatter.formatFecha("dd'-'MMMM'-'yyyy", new Date()));
				requestTO.setIdChequera(forma.getCheque_inicial()+ "-" + forma.getCheque_final()+ ","+ forma.getDescripcion_chequera()+"?"+forma.getEstado_chequera());

			} 
			else if(tipoSolicitud.toString().equalsIgnoreCase(TIPO_CANCELACION_CHEQUE)){
				
				ChequesEnum.TipoReporteRoboEnum tipoReporte = ChequesEnum.TipoReporteRoboEnum.valueOf(forma.getTipo_reporte().toUpperCase());
				requestTO.setTipoReporteCheque(tipoReporte);
				System.out.println(tipoReporte.toString());
				requestTO.setFechaSolicitud(forma.getFecha_solicitud());
				
				if(tipoReporte.toString().equalsIgnoreCase(TIPO_REPORTE_CHEQUE)){
					requestTO.setNumeroChequeInicial(null);
					requestTO.setNumeroChequeFinal(null);
					requestTO.setNumeroCheque(forma.getNumero_cheques_boquear());
				}else if (tipoReporte.toString().equalsIgnoreCase(TIPO_REPORTE_CHEQUES)){
					requestTO.setNumeroChequeInicial(forma.getCheque_inicial());
					requestTO.setNumeroChequeFinal(forma.getCheque_final());
					requestTO.setNumeroCheque(null);
					
					
				}else {
						Map<String, String> errors = new HashMap<String, String>();
						errors.put("tipo_reporte", "El tipo de opcion de cheque que proporciono es invalido ");
						throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
						
					}
			}
			//TODO individual o un rango de cheques #############################################################################
			
			ResourceFacadeSegundoSL facade = getDelegateSegundo();
			
			ChequeraRoboExtravioResponseTO responseTO = facade.getExtravioValidaInformacion(requestTO );
			
			Detalle_chequera_robo_validacionTO detalle_chequera_roboTO = new Detalle_chequera_robo_validacionTO();
			detalle_chequera_roboTO.setNombre_cliente(clienteTO.getNombreCompleto());
			detalle_chequera_roboTO.setNumero_cuenta(responseTO.getCuentaFormat());
			detalle_chequera_roboTO.setTipo_cuenta(responseTO.getCuentaDescripcion());
			detalle_chequera_roboTO.setDescripcion_chequera(responseTO.getDescChequera());
			detalle_chequera_roboTO.setCheque_inicial(responseTO.getChequeInicial());
			detalle_chequera_roboTO.setCheque_final(responseTO.getChequeFinal());
			detalle_chequera_roboTO.setEstado_chequera(responseTO.getEstatusChequera());
			
			detalle_chequera_roboTO.setCheque_bloquear(""+responseTO.getNumeroCheque());
			detalle_chequera_roboTO.setMotivo_cancelacion(forma.getMotivo_reporte());
			
			//TODO Validacion de (-) para el rango de cheques a bloquear #################################################################################333
			
			detalle_chequera_roboTO.setRango_cheques_bloquear(responseTO.getCheques());
			if(detalle_chequera_roboTO.getRango_cheques_bloquear().contains("-")){
				String[] rango= detalle_chequera_roboTO.getRango_cheques_bloquear().split("-");
				int chequeInicial = Integer.parseInt( rango[0] );
				int chequeFinal = Integer.parseInt( rango[1] );
				if(chequeFinal > chequeInicial){
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("rango_folios", "Error el valor incial del rango no puede ser mayor que el que el final ");
					throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
				}
			}
			
			response.addAttribute(detalle_chequera_roboTO);
			session.addAttribute(DETALLE_ROBO_CHEQUERA, detalle_chequera_roboTO);
				
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}  
		return response;
	}

	// ejecuta la cancelacion.
	public Response ejecucion (Session session) throws Exception{		
		Response response = new Response();
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Detalle_chequera_robo_validacionTO detalle_chequera_roboTO = (Detalle_chequera_robo_validacionTO) session.getAttribute(DETALLE_ROBO_CHEQUERA);
		ChequeraRoboExtravioRequestTO requestTO=new ChequeraRoboExtravioRequestTO();
		RoboExtravioForm forma = (RoboExtravioForm)getFormBean();
		
		try {
			String confirmacionBloqueo = forma.getConfirmacion_bloqueo();
			
			if(confirmacionBloqueo.equalsIgnoreCase("si")){
				ResourceFacadeSegundoSL facade = getDelegateSegundo();
				
				ChequesEnum.TipoSolicitudEnum tipoOperacion;
				tipoOperacion = (ChequesEnum.TipoSolicitudEnum) session.getAttribute("reporteRoboTipoSolicitud");
				requestTO.setTipoOperacion(tipoOperacion);
				requestTO.setUser( clienteTO.getAlias());
				
				
				ChequeraRoboExtravioResponseTO responseTO = facade.getExtravioEjecucion(requestTO);
				response.addAttribute(detalle_chequera_roboTO);
				
				
			}else{
				
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("confirmacion_bloqueo", "Error no se realizo la ejecucion debido a que no se confirmo correctamente ");
				throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
				
			}
									
			//TODO DESCOMENTAR PARA PRODUCCION
			
		} 
			catch (EliteDataException e) {
			buildErrorResponse(e, response);
	}  
		return response;
	
	}
}
