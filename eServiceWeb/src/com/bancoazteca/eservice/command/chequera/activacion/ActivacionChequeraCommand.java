package com.bancoazteca.eservice.command.chequera.activacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.bancoazteca.elite.beans.ActivacionChequeraRequestTO;
import com.bancoazteca.elite.beans.ActivacionChequeraResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.TalonarioTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.ChequeraTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequerasTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_activacion_chequeraTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ActivacionChequeraCommand extends CommandBase {
	
	private DateFormat dateFormat = new SimpleDateFormat( "dd MMM yyyy", new Locale("es", "MX") );
	
	public Response solicitud(Session session) throws Exception{
		System.out.println("entro a solicitud activacion chequera");
		Response response = new Response();
		CuentaTO cuentaAux = null;
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute( CLIENTE_TO );
			ActivacionChequeraRequestTO requestTO = new ActivacionChequeraRequestTO();
			ResourceFacadeSegundoSL resource = getDelegateSegundo();
			ActivacionChequeraForm forma = ( ActivacionChequeraForm ) getFormBean();

			requestTO.setUser( clienteTO.getAlias() );
			requestTO.setNumeroCuenta( forma.getCuenta_cargo() );
			String fecha = dateFormat.format( new Date() );
			requestTO.setFechaSolicitud( fecha );
			
			ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( forma.getTipo_solicitud().toUpperCase() );
			switch (tipoSolicitud) {
				case CUENTAS : 
					Iterator<CuentaTO> iterator = clienteTO.getCuentas().iterator();
					cuentaAux = iterator.next();
					requestTO.setNumeroCuenta( cuentaAux.getNumero() );
					requestTO.setDescripcionCuenta( cuentaAux.getDescripcion() );
					getCuentasDisponibles( response, requestTO, resource );
					break;
				case CHEQUERAS : 
					cuentaAux = getAccountsPrdicate( clienteTO.getCuentas(), requestTO.getNumeroCuenta() );
					if (cuentaAux == null ){
						Map<String, String> errors = new HashMap<String, String>();
						errors.put( "Cuenta", "Número de cuenta inválido" );
						throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
					}
					requestTO.setNumeroCuenta( cuentaAux.getNumero() );
					requestTO.setDescripcionCuenta( cuentaAux.getDescripcion() );
					getChequeraCuenta( response, requestTO, resource, session );
					break;
			}
		} catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		return response;
	}
	
	private void getCuentasDisponibles( Response response, ActivacionChequeraRequestTO requestTO, ResourceFacadeSegundoSL facade ) throws Exception{
		ActivacionChequeraResponseTO responseDelegateTO = facade.setActivacionChequeSolicitud( requestTO );
		Cuentas_CargoTO cuentas = new Cuentas_CargoTO();
		Collection<Cuenta_CargoTO> coleccionCuentas = new ArrayList<Cuenta_CargoTO>();
		String numeroCuenta;
		for( com.bancoazteca.elite.beans.CuentaTO cuenta : responseDelegateTO.getCuentas() ){
			Cuenta_CargoTO cuentaTO = new Cuenta_CargoTO();
			numeroCuenta = Formatter.formatCuenta( cuenta.getNumero() );
			cuentaTO.setNumero_cuenta( numeroCuenta );
			cuentaTO.setSaldo_disponible( cuenta.getDisponible().toString() );
			cuentaTO.setProducto(cuenta.getDescripcion() );
			coleccionCuentas.add( cuentaTO );
		}
		cuentas.setColeccion_cuentas(coleccionCuentas);
		response.addAttribute(cuentas);
	}

	private void getChequeraCuenta( Response response, ActivacionChequeraRequestTO requestTO, ResourceFacadeSegundoSL facade, 
			Session session ) throws Exception{
		String numeroCuenta;
		ActivacionChequeraResponseTO responseDelegateTO = facade.setActivacionChequeSeleccionCuenta( requestTO );
		
		Collection<ChequeraTO> coleccionCheques = new ArrayList<ChequeraTO>();
		numeroCuenta = responseDelegateTO.getCuenta().substring( 0, 20 );
		numeroCuenta = Formatter.formatCuenta( numeroCuenta );

		Cuenta_CargoTO cuenta = new Cuenta_CargoTO();
		cuenta.setNumero_cuenta( Formatter.formatCuenta( numeroCuenta ) );
		cuenta.setProducto( responseDelegateTO.getTipoCuenta() );
		response.addAttribute( cuenta );

		Detalle_chequerasTO informacion = new Detalle_chequerasTO();
		ChequeraTO resumen_chequerasTO;
		int i = 0;
		for( TalonarioTO obj : responseDelegateTO.getChequerascuenta() ){
			resumen_chequerasTO = new ChequeraTO();
			resumen_chequerasTO.setChequera_id( "" + i );
			resumen_chequerasTO.setCheque_final( "" + obj.getChequeFinal() );
			resumen_chequerasTO.setCheque_inicial( "" + obj.getChequeInicial() );
			resumen_chequerasTO.setDescripcion( obj.getDescripcionChequera().trim() );
			resumen_chequerasTO.setRegimen_firmas( obj.getRegimenFirmas().trim() );
			resumen_chequerasTO.setImporte_piso( obj.getImportePiso().toString() );
			resumen_chequerasTO.setProteccion_chequera( obj.getProteccion().trim() );
			resumen_chequerasTO.setEstado( obj.getDescStatus().trim() );
			coleccionCheques.add( resumen_chequerasTO );
			i++;
		}
		informacion.setColeccion_chequeras( coleccionCheques );
		session.addAttribute( CHEQUERA_ACTIVACION_RESPONSE, informacion );
		response.addAttribute( informacion );
	}

	public Response validacion(Session session) throws Exception{
		System.out.println("entro a validacion activacion chequera");
		Response response = new Response();
		Detalle_chequerasTO chequeras = ( Detalle_chequerasTO ) session.getAttribute( CHEQUERA_ACTIVACION_RESPONSE );
		ActivacionChequeraForm forma = ( ActivacionChequeraForm ) getFormBean();
		try {
			ClienteTO clienteTO = ( ClienteTO ) session.getAttribute( CLIENTE_TO );
			ActivacionChequeraRequestTO requestTO = new ActivacionChequeraRequestTO();
			requestTO.setUser( clienteTO.getAlias() );
			
			String idChequera = forma.getChequera_id();
			int idChequeraInt = Integer.parseInt( idChequera );
			Collection<ChequeraTO> listaChequeras = chequeras.getColeccion_chequeras();
			
			if( idChequeraInt < 0 || idChequeraInt > listaChequeras.size()-1 )
				throw new EliteDataException( "El identificador de chequera es inválido", null, EliteRules.LEVEL_ACTION_MESSAGES );
			
			for( ChequeraTO chequera : chequeras.getColeccion_chequeras() ){
				if( idChequera.equalsIgnoreCase( chequera.getChequera_id() ) ){
					requestTO.setChequeFinal( chequera.getCheque_final() );
					requestTO.setChequeInicial( chequera.getCheque_inicial() );
					requestTO.setDescripcionChequera( chequera.getDescripcion() );
					break;
				}
			}
			
			ResourceFacadeSegundoSL resource = getDelegateSegundo();
			ActivacionChequeraResponseTO responseDelegateTO = resource.getActivacionChequeValidacion( requestTO );
			
			Resumen_activacion_chequeraTO resumen = new Resumen_activacion_chequeraTO();
			resumen.setFecha_activacion( responseDelegateTO.getFechaActivacion() );
			String numero = responseDelegateTO.getChequeInicial()+ "-" + responseDelegateTO.getChequeFinal();
			resumen.setNumero_chequera( numero );
			numero = responseDelegateTO.getCuenta().substring( 0, 20 );
			numero = Formatter.formatCuenta( numero );
			resumen.setNumero_cuenta( numero );
			resumen.setTipo_chequera( responseDelegateTO.getDescripcion() );
			resumen.setTipo_cuenta( responseDelegateTO.getTipoCuenta() );
			
			response.addAttribute( resumen );

			DispositivoHuellaTO dispositivoHuellaTO = responseDelegateTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}
			response.addAttribute( huellaTO );

		} catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception{
		System.out.println("entro a ejecucion activacion chequera");
		Response response = new Response();
		ActivacionChequeraForm forma = ( ActivacionChequeraForm ) getFormBean();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute( CLIENTE_TO );
			ActivacionChequeraRequestTO requestTO = new ActivacionChequeraRequestTO();
			requestTO.setUser( clienteTO.getAlias() );
			Calendar c = Calendar.getInstance();
			requestTO.setFechaActivacion( dateFormat.format( c.getTime() ) );
			ResourceFacadeSegundoSL resource = getDelegateSegundo();

			if( forma.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ) {
				requestTO.setOptionDispositive( OPCION_TOKEN );
				requestTO.setTokencode( forma.getClave_seguridad().toString() );
			} else if( forma.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ) {
				requestTO.setOptionDispositive( OPCION_HUELLA );
				requestTO.setClave( forma.getHuella_seguridad().toString() );
			}

			ActivacionChequeraResponseTO responseDelegateTO = resource.getActivacionChequeEjecucion( requestTO );

			Resumen_activacion_chequeraTO resumen = new Resumen_activacion_chequeraTO();
			resumen.setFecha_activacion( responseDelegateTO.getFechaActivacion() );
			String numero = responseDelegateTO.getChequeInicial()+ "-" + responseDelegateTO.getChequeFinal();
			resumen.setNumero_chequera( numero );
			numero = responseDelegateTO.getCuenta().substring( 0, 20 );
			numero = Formatter.formatCuenta( numero );
			resumen.setNumero_cuenta( numero );
			resumen.setTipo_chequera( responseDelegateTO.getDescripcion() );
			resumen.setTipo_cuenta( responseDelegateTO.getTipoCuenta() );
			
			response.addAttribute( resumen );
		} catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		return response;
	}
}
