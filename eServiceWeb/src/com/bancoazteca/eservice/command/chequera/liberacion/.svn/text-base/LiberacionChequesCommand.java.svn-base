package com.bancoazteca.eservice.command.chequera.liberacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ChequeTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.LiberacionChequesRequestTO;
import com.bancoazteca.elite.beans.LiberacionChequesResponseTO;
import com.bancoazteca.elite.beans.TalonarioTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.chequera.ChequeraException;
import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.ChequeraTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequerasTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequesTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Liberacion_chequesTO;
import com.bancoazteca.eservice.command.base.beans.Listado_detalle_chequeraTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class LiberacionChequesCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(LiberacionChequesCommand.class);

	public Response solicitud(Session session) throws Exception {
		log.info(">> ---Entra a Solicitud Liberacion Chequera --- <<");
		Response response = new Response();
		ClienteTO clienteTO = ( ClienteTO ) session.getAttribute( CLIENTE_TO );
			
		DateFormat dateFormat = new SimpleDateFormat( "d MMM yyyy", new Locale("es", "MX") );
		Date fecha = new Date();
		
		LiberacionChequesForm forma = (LiberacionChequesForm)getFormBean();
		LiberacionChequesRequestTO requestTO = new LiberacionChequesRequestTO();
		requestTO.setUser(clienteTO.getUserName());
		CuentaTO cuentaAux = null;

		try {
			ResourceFacadeSegundoSL facadeSL = getDelegateSegundo();
			String fechaStr = dateFormat.format( fecha );
			ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( forma.getTipo_solicitud().toUpperCase() );
			switch( tipoSolicitud ){
				case CUENTAS: {
					Iterator<CuentaTO> iterator = clienteTO.getCuentas().iterator();
					cuentaAux = iterator.next();
					requestTO.setCuenta( cuentaAux.getNumero() + "-" + cuentaAux.getDescripcion() );
					requestTO.setFechaSolicitud( fechaStr );
					getCuentasDisponibles( response, requestTO, facadeSL );
					break;
				}
				case CHEQUERAS: {					 
					String numeroCuenta = Formatter.removeSpaces(Formatter.formatCuenta(forma.getNumero_cuenta()));	
					CuentaTO cuentaTO = getAccountsPrdicate( clienteTO.getCuentas(), numeroCuenta );
					if (cuentaTO==null) {
						Map<String, String> errors = new HashMap<String, String>();
						errors.put("Cuenta", "Número de cuenta inválido");
						throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
					}
					requestTO.setCuenta( cuentaTO.getNumero() + "-" + cuentaTO.getDescripcion() );
					requestTO.setFechaSolicitud( dateFormat.format( fecha ) );
					
					session.addAttribute( FECHA_SOLICITUD, fechaStr); 
					getChequeraCuenta( response, requestTO, facadeSL, session );					
					break;
				}
				case CHEQUE: {
					requestTO.setFechaSolicitud( ( String ) session.getAttribute( FECHA_SOLICITUD ) );
					requestTO.setNumeroCheque( forma.getChequera_id() );
					getChequesChequera(response, requestTO, facadeSL, session);		
					session.addAttribute( NUMERO_CUENTA, requestTO.getActivar() );
					break;
				}
			}			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	private void getChequesChequera(Response response,
			LiberacionChequesRequestTO requestTO,
			ResourceFacadeSegundoSL facadeSL,
			Session session) throws ChequeraException,
			SessionExpiredException, EliteDataException {
		
		Detalle_chequerasTO chequeras = ( Detalle_chequerasTO ) session.getAttribute( CHEQUERA_LIBERACION_RESPONSE );
		
		String idChequera = requestTO.getNumeroCheque();
		int idChequeraInt = Integer.parseInt( idChequera );
		Collection<ChequeraTO> listaChequeras = chequeras.getColeccion_chequeras();
		
		if( listaChequeras == null || idChequeraInt < 0 || idChequeraInt > listaChequeras.size()-1 )
			throw new EliteDataException( "El identificador de chequera es inválido", null, EliteRules.LEVEL_ACTION_MESSAGES );
		
		ChequeraTO chequeraAux = null;
		for( ChequeraTO chequera : chequeras.getColeccion_chequeras() ){
			if( idChequera.equalsIgnoreCase( chequera.getChequera_id() ) ){
				chequeraAux = chequera;
				String chequeraStr = chequera.getCheque_inicial() + "-" + chequera.getCheque_final();
				chequeraStr += "," + chequera.getDescripcion();
				requestTO.setActivar( chequeraStr );
				break;
			}
		}

		LiberacionChequesResponseTO responseTO = facadeSL.liberacionChequesSolicitudCheque(requestTO);
		
		Cuenta_CargoTO cuenta = new Cuenta_CargoTO();
		cuenta.setNumero_cuenta( Formatter.formatCuenta( responseTO.getCuenta() ) );
		cuenta.setProducto( responseTO.getTipoCuenta() );
		response.addAttribute( cuenta );
		
		if( chequeraAux != null )
			response.addAttribute( chequeraAux );
		
		Listado_detalle_chequeraTO listado = new Listado_detalle_chequeraTO();
		List<Detalle_chequesTO> cheques = new ArrayList<Detalle_chequesTO>();
		Detalle_chequesTO chequeAux;
		for( ChequeTO cheque : responseTO.getCheques() ){
			chequeAux = new Detalle_chequesTO();
			chequeAux.setEstado( cheque.getStatus() );
			chequeAux.setNumero_cheque( "" + cheque.getNumeroCheque() );
			cheques.add( chequeAux );
		}
		listado.setColeccion_detalle_cheques( cheques );
		response.addAttribute( listado );
	}

	private void getChequeraCuenta(Response response,
			LiberacionChequesRequestTO requestTO,
			ResourceFacadeSegundoSL facadeSL, Session session ) throws ChequeraException,
			SessionExpiredException, EliteDataException {
		
		LiberacionChequesResponseTO responseTO = facadeSL.liberacionChequesSolicitudChequera( requestTO );
		
		Cuenta_CargoTO cuenta = new Cuenta_CargoTO();
		cuenta.setNumero_cuenta( Formatter.removeSpaces( responseTO.getCuenta() ) );
		cuenta.setProducto( responseTO.getTipoCuenta() );
		response.addAttribute( cuenta );
		
		Detalle_chequerasTO informacion = new Detalle_chequerasTO();
		Collection<ChequeraTO> coleccionCheques = new ArrayList<ChequeraTO>();

		int i = 0;
		for(TalonarioTO obj : responseTO.getChequerascuenta() ){
			ChequeraTO resumen_chequerasTO = new ChequeraTO();
			resumen_chequerasTO.setChequera_id( "" + i++ );
			resumen_chequerasTO.setCheque_final( "" + obj.getChequeFinal() );
			resumen_chequerasTO.setCheque_inicial( "" + obj.getChequeInicial() );
			resumen_chequerasTO.setDescripcion( obj.getDescripcionChequera().trim() );
			resumen_chequerasTO.setRegimen_firmas( obj.getRegimenFirmas().trim() );
			resumen_chequerasTO.setImporte_piso( obj.getImportePiso().toString() );
			resumen_chequerasTO.setProteccion_chequera( obj.getProteccion().trim() );
			resumen_chequerasTO.setEstado( obj.getDescStatus().trim() );
			coleccionCheques.add( resumen_chequerasTO );
		}
		informacion.setColeccion_chequeras(coleccionCheques);
		session.addAttribute( CHEQUERA_LIBERACION_RESPONSE, informacion );
		response.addAttribute(informacion);
	}

	private void getCuentasDisponibles(Response response,
			LiberacionChequesRequestTO requestTO,
			ResourceFacadeSegundoSL facadeSL) throws ChequeraException,
			SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = facadeSL.liberacionChequesSolicitudCuenta(requestTO);
		
		Cuentas_CargoTO cuentas = new Cuentas_CargoTO();
		Collection<Cuenta_CargoTO> coleccionCuentas = new ArrayList<Cuenta_CargoTO>();
		for( CuentaTO cuenta : responseTO.getCuentas() ){
			Cuenta_CargoTO cuentaTO = new Cuenta_CargoTO();
			cuentaTO.setNumero_cuenta( Formatter.formatCuenta( cuenta.getNumero() ) );
			cuentaTO.setSaldo_disponible( cuenta.getDisponible().toString() );
			cuentaTO.setProducto( cuenta.getDescripcion() );
			coleccionCuentas.add( cuentaTO );
		}
		cuentas.setColeccion_cuentas( coleccionCuentas );
		response.addAttribute( cuentas );
	}
	
	public Response validacion( Session session ) throws Exception {
		log.info(">> ---Entra a Validacion Liberacion Chequera --- <<");
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);				
		LiberacionChequesForm forma = ( LiberacionChequesForm ) getFormBean();
		
		String fecha = ( String ) session.getAttribute( FECHA_SOLICITUD );
		String numeroCuenta = ( String ) session.getAttribute( NUMERO_CUENTA );
		LiberacionChequesRequestTO requestTO = new LiberacionChequesRequestTO();
		requestTO.setUser( clienteTO.getUserName() );
		requestTO.setFechaSolicitud( fecha );
		requestTO.setNumeroCheque( forma.getNumero_cheque() );
		requestTO.setMontoCheque( forma.getMonto_cheque() );
		requestTO.setActivar( numeroCuenta );

		try {
			ResourceFacadeSegundoSL facadeSL = getDelegateSegundo();
			LiberacionChequesResponseTO responseTO = facadeSL.liberacionChequesValidacion(requestTO);
			Liberacion_chequesTO liberacionCheques = new Liberacion_chequesTO();
			
			liberacionCheques.setMonto_cheque( responseTO.getMontoCheque() );
			liberacionCheques.setNumero_cheque( responseTO.getNumeroCheque() );
			liberacionCheques.setNumero_chequera( responseTO.getActivar() );
			liberacionCheques.setNumero_cuenta( Formatter.formatCuenta( responseTO.getCuenta() ) );
			liberacionCheques.setDescripcion_chequera( responseTO.getTipoChequera() );
			liberacionCheques.setTipo_cuenta( responseTO.getTipoCuenta() );
			
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica( responseTO.getDispositivoHuellaTO().getLlavePublica() );
			huellaTO.setLongitud_huella( responseTO.getDispositivoHuellaTO().getLongitudHuella() );
			
			response.addAttribute( liberacionCheques );
			response.addAttribute(huellaTO);
			
			session.addAttribute( CHEQUERA_LIBERACION_RESPONSE, liberacionCheques);
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		log.info(">> ---Entra a Ejecucion Liberacion Chequera --- <<");
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		Liberacion_chequesTO liberacionCheques = (Liberacion_chequesTO) session.getAttribute(CHEQUERA_LIBERACION_RESPONSE);
				
		LiberacionChequesForm forma = (LiberacionChequesForm)getFormBean();
		
		LiberacionChequesRequestTO requestTO = new LiberacionChequesRequestTO();
		requestTO.setUser(clienteTO.getUserName());
		requestTO.setFechaSolicitud((String)session.getAttribute(FECHA_SOLICITUD));
		
		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)) {
			requestTO.setOpcionActivacion(OPCION_TOKEN);
			requestTO.setClave(forma.getClave_seguridad().toString());
		} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
			requestTO.setOpcionActivacion(OPCION_HUELLA);
			requestTO.setClave(forma.getHuella_seguridad());
		}

		try {
			ResourceFacadeSegundoSL facadeSL = getDelegateSegundo();
			LiberacionChequesResponseTO responseTO = facadeSL.liberacionChequesEjecucion(requestTO);

			liberacionCheques = new Liberacion_chequesTO();
			
			liberacionCheques.setMonto_cheque( responseTO.getMontoCheque() );
			liberacionCheques.setNumero_cheque( responseTO.getNumeroCheque() );
			liberacionCheques.setNumero_chequera( responseTO.getActivar() );
			liberacionCheques.setNumero_cuenta( Formatter.formatCuenta( responseTO.getCuenta() ) );
			liberacionCheques.setDescripcion_chequera( responseTO.getTipoChequera() );
			liberacionCheques.setTipo_cuenta( responseTO.getTipoCuenta() );
			liberacionCheques.setFecha_operacion( responseTO.getFechaActivacion() );
			
			response.addAttribute(liberacionCheques);		

		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
}
