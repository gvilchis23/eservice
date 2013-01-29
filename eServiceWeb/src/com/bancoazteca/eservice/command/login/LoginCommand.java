package com.bancoazteca.eservice.command.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.beans.ParameterOnSessionTO;
import com.bancoazteca.elite.beans.TarjetaBaseAlnova;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Detalle_OperacionTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.LoginTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_Operaciones_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.TarjetaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;
import com.bancoazteca.eservice.command.cuentas.mercadodinero.CuentaMercadoDineroCommand;
import com.bancoazteca.eservice.command.logout.LogoutCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.filters.AlnovaRedMovilFilterException;
import com.bancoazteca.eservice.filters.ConstantesFiltro;
import com.bancoazteca.eservice.filters.CuentaFilterException;
import com.bancoazteca.eservice.filters.Filter;
import com.bancoazteca.eservice.filters.FilterManager;
import com.bancoazteca.eservice.validator.Errors;

public class LoginCommand extends CommandBase {

	private static final Logger log = Logger.getLogger(LoginCommand.class);

	public Response execute(Session session) throws Exception {
		
		log.info("loginCommand");
		String nombreCompleto="";
		ClienteTO clienteTO=new ClienteTO();
		HashMap<String, Object> configuracionFiltro = new HashMap<String, Object>();	//Configuración del filtro
		LoginForm loginForm = (LoginForm) getFormBean();
		boolean isCache=!loginForm.getIdservicio().equalsIgnoreCase("login");
		LoginRequestTO loginRequestTO = new LoginRequestTO();	
		LoginResponseTO loginResponseTO = null;
		Response response = new Response();
		String user = loginForm.getUsuario();
		loginRequestTO.setUser(user);
		loginRequestTO.setPassword(loginForm.getPassword().toString());
		String validationIdApplication = Session.getSessionApplication(loginForm.getIdaplicacion().toString());
		String aplicacion = SessionManager.getInstance().getKeyApplication( loginForm.getIdaplicacion().toString() );
		loginRequestTO.setAplicacion(aplicacion);
		//Se agrega a la configuración del filtro el id de aplicación que se loguea a EsErvice
		configuracionFiltro.put(ConstantesFiltro.ID_APLICACION, aplicacion);
		configuracionFiltro.put("path", ConstantesFiltro.FILTROS_PATH);
		FilterManager filterManager = new FilterManager( configuracionFiltro );

		if(validationIdApplication.equals(ID_APPLICATION_VALIDA)){
			ResourceFacadeSL facadeSL = getDelegate();

			try {
				if(isCache)
				{
					loginRequestTO.setReload(true);
					loginResponseTO=facadeSL.login(loginRequestTO);
//					response.addAttribute(loginResponseTO);
				}else{
					loginResponseTO = facadeSL.login(loginRequestTO);
				}
			} catch (EliteDataException e) {
				if (e.getMessage() != null && e.getErrorData().equals("1")) {
					if(isCache ){
						if( loginForm.getCerrar_sesion_anterior()== null || loginForm.getCerrar_sesion_anterior().equalsIgnoreCase("no")){
							
							/*Funcionalidad para validar si la aplicación debe de filtrarse*/
							if( filterManager.filtrarAplicacion(aplicacion) ){
								/*Si se filtra entonces se abre sesión para el usuario y si hay una sesión abierta se cierra.*/
								loginRequestTO.setAction("abrir");
								loginResponseTO=facadeSL.sessionManagment(loginRequestTO);
								configuracionFiltro.put(ConstantesFiltro.CLIENTE_TO, loginResponseTO.getClienteTO());
								try {
									filterManager.ejecutaFiltros( );
									clienteTO=new ClienteTO();
									clienteTO = loginResponseTO.getClienteTO();		
									clienteTO.setUserName(loginForm.getUsuario());
									session = Session.create(loginForm.getIdaplicacion().toString(),loginForm.getUsuario());
									session.addAttribute(CLIENTE_TO, clienteTO);
									response = new LogoutCommand().execute(session);
								} catch (Exception ex ) {
									/*Si ocurrio una excepción en el proceso de filtro entonces se lanza la excepción correspondiente*/
									ex.printStackTrace();
									if(ex instanceof AlnovaRedMovilFilterException){
										response.setStatus(Errors.EXCEPTION_USER_NO_PAYMENT_RMA, 
												PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																							Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																							+ Filter.ALNOVA_ERROR_NO_PAGO ), null );
									}else if(ex instanceof CuentaFilterException){
										response.setStatus(Errors.EXCEPTION_USER_NO_ACCOUNT_RMA, 
												PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																							Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																							+ Filter.ALNOVA_ERROR_USUARIO_NUEVO), null );
									}else if(ex instanceof AlnovaException){
										response.setStatus(Errors.EXCEPTION_USER_NO_PAYMENT_RMA, 
												PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																							Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																							+ Filter.ALNOVA_ERROR), null);
									}else{
										throw ex;
									}
								}
							}	
							/*Si la aplicación no debe de filtrarse entonces se realiza el manejo de sesión para una Sesión que ya existe*/
							
							response.setStatus(Errors.EXCEPTION_USER_OPEN_SESSION,e.getMessage(),null);
							return response;
						}else{
							loginRequestTO.setAction("abrir");
							loginResponseTO=facadeSL.sessionManagment(loginRequestTO);
//							response.addAttribute(loginResponseTO);
						}
					}else{
						loginRequestTO.setAction("abrir");
						loginResponseTO = facadeSL.sessionManagment(loginRequestTO);
					}
				}
				if (e.getMessage() != null && e.getMessage().equalsIgnoreCase("Maximo Intentos")){
					throw new UsuarioException(e.getMessage());
				}
			}
			session = Session.create(loginForm.getIdaplicacion().toString(),loginForm.getUsuario());
			session.addAttribute(CommandConstantes.NOMBRE_USUARIO, loginForm.getUsuario());
			
			response.setIdSession(session);
			
			LoginTO loginTO = new LoginTO();
			log.info( "ID Aplicación " + loginForm.getIdaplicacion() );
			
			if(!isCache){
				clienteTO = loginResponseTO.getClienteTO();		
				clienteTO.setUserName(loginForm.getUsuario());
				loginTO =(LoginTO)establecerLoginTO(clienteTO, session);
			}else{
				clienteTO = loginResponseTO.getClienteTO();		
				clienteTO.setUserName(loginForm.getUsuario());
				loginTO =(LoginTO)establecerLoginCache(clienteTO, session);
			}
			
			try {
				Inversion_mercado_dineroTO inversion_mercado_dinero = getMercadoDinero(clienteTO, session);
				loginTO.setInversion_mercado_dinero(inversion_mercado_dinero);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			loginTO.setNivel_seguridad(setNivelSeguridad(clienteTO));
			
			loginTO.setNumero_cliente(Cipher.valueOf( clienteTO.getNumero() ));
			
			response.addAttribute(loginTO);
			nombreCompleto=clienteTO.getNombreCompleto();
			session.addAttribute(CLIENTE_TO, clienteTO);
			
			/*Lógica para cerrar la sesión que se genera para validar si el usuario ya esta logueado*/
			try {
//				filterManager = new FilterManager( configuracionFiltro );
				if(filterManager.filtrarAplicacion(aplicacion)){
					configuracionFiltro.put(ConstantesFiltro.CLIENTE_TO, loginResponseTO.getClienteTO());
					filterManager.ejecutaFiltros( );
				}
			} catch (Exception ex) {
				/*Si no se pudieron aplicar todos los filtros entonces se lanza la excepción correspondiente y se cierra la sesión*/
				response = new LogoutCommand().execute(session);	
				/*Si ocurrio una excepción en el proceso de filtro entonces se lanza la excepción correspondiente*/
				ex.printStackTrace();
				if(ex instanceof AlnovaRedMovilFilterException){
					response.setStatus(Errors.EXCEPTION_USER_NO_PAYMENT_RMA, 
							PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																		Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																		+ Filter.ALNOVA_ERROR_NO_PAGO ), null );
				}else if(ex instanceof CuentaFilterException){
					response.setStatus(Errors.EXCEPTION_USER_NO_ACCOUNT_RMA, 
							PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																		Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																		+ Filter.ALNOVA_ERROR_USUARIO_NUEVO), null );
				}else if(ex instanceof AlnovaException){
					response.setStatus(Errors.EXCEPTION_USER_NO_PAYMENT_RMA, 
							PropertiesService.getInstance().getPropertie(Filter.FILTRO_PROPERTIES , 
																		Filter.PREFIJO_FILTROS_PROPERTIES  + aplicacion 
																		+ Filter.ALNOVA_ERROR), null);
				}else{
					throw ex;
				}
			}
			/*Lógica para cerrar la sesión que se genera para validar si el usuario ya esta logueado*/
		}else if(validationIdApplication.equals(ID_APPLICATION_INCORRECTA)){
			response.setStatus(Errors.SESSION_APPLICATION_NOT_FOUND_CODE, "Sesión de aplicación incorrecta", null);
		}else if(validationIdApplication.equals(ID_APPLICATION_EXPIRADA)){
			response.setStatus(Errors.SESSION_APPLICATION_EXPIRED, "Sesión de aplicación expirada", null);
		}		
		
		/*ParameterOnSessionTO onSessionTO=new ParameterOnSessionTO();
		onSessionTO.setNombreValor("typeName");
		onSessionTO.setValor(loginRequestTO.getAplicacion());
		onSessionTO.setUsuario(user);		
		getDelegate().setParameterOnEBankSession(onSessionTO);*/

		
		return response;
	}

	private String setNivelSeguridad(ClienteTO clienteTO) {
		String nivelSeguridad="";
		if(	clienteTO != null &&
			clienteTO.getSecurityData() != null &&
			clienteTO.getSecurityData().getSecLevelVO() != null &&
			clienteTO.getSecurityData().getSecLevelVO().getSecurityLevel() != null)
		switch( Integer.parseInt(clienteTO.getSecurityData().getSecLevelVO().getSecurityLevel()) ){
			case 1 : nivelSeguridad="SIN_DISPOSITIVOS"; break;
			case 5 : nivelSeguridad="BAJO"; break;
			case 6 : nivelSeguridad="MEDIO"; break;
			case 4 : nivelSeguridad="ALTO"; break;
			default : nivelSeguridad="NO_DEFINIDO"; break;
		}
		return nivelSeguridad;
	}

	private com.bancoazteca.eservice.command.base.beans.LoginTO establecerLoginTO(ClienteTO clienteTO, Session session) {
		LoginTO loginTO = new LoginTO();
		loginTO.setAlias(clienteTO.getAlias());
		loginTO.setNombre(clienteTO.getNombre());
		loginTO.setApellido_paterno(clienteTO.getPaterno());
		loginTO.setApellido_materno(clienteTO.getMaterno());
		loginTO.setNombre_completo(clienteTO.getNombreCompleto());
		loginTO.setEmail(clienteTO.getEmail());
		loginTO.setFecha_nacimiento(clienteTO.getNacimiento());		
		Collection<com.bancoazteca.eservice.command.base.beans.CuentaTO> cuentas = new ArrayList<com.bancoazteca.eservice.command.base.beans.CuentaTO>();		
		
		Collection<com.bancoazteca.elite.beans.CuentaTO> cuentasActuales=new ArrayList<com.bancoazteca.elite.beans.CuentaTO>();
		
		cicloCuentas:for (com.bancoazteca.elite.beans.CuentaTO cuenta : clienteTO.getCuentas()){				
			com.bancoazteca.eservice.command.base.beans.CuentaTO cuentaTO = new com.bancoazteca.eservice.command.base.beans.CuentaTO();
			String subProduct = cuenta.getSubproducto();
			if(!Validator.isEmptyData(subProduct)){
				
				String numeroCuenta=cuenta.getCuentaFormateada().replaceAll(" ", "");
				
				if(isContrato(numeroCuenta)){
					continue cicloCuentas;
				}
				
				cuentaTO.setNumero_cuenta(numeroCuenta);
				cuentaTO.setCuenta_clabe(cuenta.getClabe());
						
				double retenido = getCantidad(cuenta.getRetenido());
				double disponible = getCantidad(cuenta.getDisponible());
				double saldoTotal = retenido + disponible;
			
				cuentaTO.setSaldo_retenido(String.valueOf(retenido));
				cuentaTO.setSaldo_disponible(String.valueOf(disponible));
				cuentaTO.setSaldo_total(String.valueOf(saldoTotal));
				
				cuentaTO.setProducto(cuenta.getDescripcion());
				cuentas.add(cuentaTO);
				
				cuenta.setBalance(saldoTotal);
				
				cuentasActuales.add(cuenta);
				
			}
		}
		clienteTO.setCuentas(cuentasActuales);
		
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
			
			loginTO.setColeccion_creditos(creditosColeccion);
		}				
		loginTO.setCuentas(cuentas);			
		
		loginTO.setColeccion_tarjetas(obtenerTarjetas(clienteTO));
		
		LoginTO clasificacionCuentas = filtrarCuentas(clienteTO);
		if(clasificacionCuentas != null){
			loginTO.setCuentas(clasificacionCuentas.getCuentas());
			loginTO.setColeccion_inversiones(clasificacionCuentas.getColeccion_inversiones());
		}
		
		return loginTO;
	}
	
	private com.bancoazteca.eservice.command.base.beans.LoginTO establecerLoginCache(ClienteTO clienteTO, Session session) {
		LoginTO loginTO = new LoginTO();
		loginTO.setAlias(clienteTO.getAlias());
		loginTO.setNombre(clienteTO.getNombre());
		loginTO.setApellido_paterno(clienteTO.getPaterno());
		loginTO.setApellido_materno(clienteTO.getMaterno());
		loginTO.setNombre_completo(clienteTO.getNombreCompleto());
		loginTO.setEmail(clienteTO.getEmail());
		loginTO.setFecha_nacimiento(clienteTO.getNacimiento());		
		Collection<com.bancoazteca.eservice.command.base.beans.CuentaTO> cuentas = new ArrayList<com.bancoazteca.eservice.command.base.beans.CuentaTO>();		
		if(clienteTO.getCuentas() != null){
			for (com.bancoazteca.elite.beans.CuentaTO cuenta : clienteTO.getCuentas()){				
				com.bancoazteca.eservice.command.base.beans.CuentaTO cuentaTO = new com.bancoazteca.eservice.command.base.beans.CuentaTO();
				String subProduct = cuenta.getSubproducto();
				if(!Validator.isEmptyData(subProduct)){
					cuentaTO.setNumero_cuenta(cuenta.getCuentaFormateada().replaceAll(" ", ""));
					cuentaTO.setCuenta_clabe(cuenta.getClabe());
					cuentaTO.setProducto(cuenta.getDescripcion());
					cuentas.add(cuentaTO);
				}
			}	
		}
		
		Collection<com.bancoazteca.elite.beans.CreditoTO> creditos= clienteTO.getCreditos();
		if (creditos != null) {	
			Collection<com.bancoazteca.eservice.command.base.beans.CreditoTO> creditosColeccion= new ArrayList<com.bancoazteca.eservice.command.base.beans.CreditoTO>();			
			for(com.bancoazteca.elite.beans.CreditoTO credit : clienteTO.getCreditos()){
				com.bancoazteca.eservice.command.base.beans.CreditoTO credito_TO = new com.bancoazteca.eservice.command.base.beans.CreditoTO();
				credito_TO.setIndex(credit.getIndex());
				credito_TO.setNumero_credito(credit.getNumCredito());
				credito_TO.setNumero_cuenta(credit.getNumCuenta());
				credito_TO.setTipo_credito(credit.getTipo());
				creditosColeccion.add(credito_TO);
			}
			
			loginTO.setColeccion_creditos(creditosColeccion);
		}				
		loginTO.setCuentas(cuentas);			
				
		Collection<TarjetaBaseAlnova> coleccionTarjetas=new ArrayList<TarjetaBaseAlnova>();
		Collection<TarjetaTO> cards = new ArrayList<TarjetaTO>();	
		
		if(clienteTO.getTarjetasBaseAlnova()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getTarjetasBaseAlnova());
		}
		
		if(clienteTO.getGoldenCards()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getGoldenCards());
		}
		
		if(clienteTO.getTarjetasInifite()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getTarjetasInifite());
		}
		
		if(coleccionTarjetas!=null){
			for (TarjetaBaseAlnova tarjeta : coleccionTarjetas){
				TarjetaTO tarjetaTO = new TarjetaTO();	
				tarjetaTO.setNumero_tarjeta(tarjeta.getNumeroTarjeta());
				tarjetaTO.setContrato(tarjeta.getContrato());
				tarjetaTO.setNombre_titular(tarjeta.getTitularContrato());
				tarjetaTO.setTarjeta_adicional(tarjeta.getTarjetaAdicional());
				tarjetaTO.setTarjetas_adicionales(tarjeta.getTarjetasAdicionales());
				tarjetaTO.setTiene_tarjetas_adicionales(tarjeta.getTieneTarjetasAdicionales());
				tarjetaTO.setTipo(tarjeta.getTipo());
				tarjetaTO.setTitular_contrato(tarjeta.getTitularContrato());
				tarjetaTO.setTitularidad(tarjeta.getTitularidad());
				cards.add(tarjetaTO);
			}
		}		
		
		loginTO.setColeccion_tarjetas(cards);
		
		LoginTO clasificacionCuentas = filtrarCuentas(clienteTO);
		if(clasificacionCuentas != null){
			loginTO.setCuentas(clasificacionCuentas.getCuentas());
			loginTO.setColeccion_inversiones(clasificacionCuentas.getColeccion_inversiones());
		}
		
		return loginTO;
	}
	
	private Collection<TarjetaTO> obtenerTarjetas(ClienteTO clienteTO){
		
		Collection<TarjetaBaseAlnova> coleccionTarjetas=new ArrayList<TarjetaBaseAlnova>();
		Collection<TarjetaTO> cards = new ArrayList<TarjetaTO>();	
		
		if(clienteTO.getTarjetasBaseAlnova()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getTarjetasBaseAlnova());
		}
		
		if(clienteTO.getGoldenCards()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getGoldenCards());
		}
		
		if(clienteTO.getTarjetasInifite()!=null)
		{
			coleccionTarjetas.addAll(clienteTO.getTarjetasInifite());
		}
		
		if(coleccionTarjetas!=null){
			for (TarjetaBaseAlnova tarjeta : coleccionTarjetas){
				TarjetaTO tarjetaTO = new TarjetaTO();	
				tarjetaTO.setNumero_tarjeta(tarjeta.getNumeroTarjeta());
				tarjetaTO.setCredito_disponible(Formatter.formatMonto(tarjeta.getCreditoDisponible()));
				tarjetaTO.setCredito_usado(Formatter.formatMonto(tarjeta.getCreditoUsado()));
				tarjetaTO.setCredito_utilizado_al_corte(Formatter.formatMonto(tarjeta.getCreditoUtilizadoAlCorte()));
				tarjetaTO.setCorte_septiembre(tarjeta.getCorteSeptiembre());
				tarjetaTO.setFecha_de_corte(tarjeta.getFechaDeCorte());
				tarjetaTO.setFecha_de_corte_nueva(tarjeta.getFechaDeCorte_Nueva());
				tarjetaTO.setPago_minimo(Formatter.formatMonto(tarjeta.getPagoMinimo()));
				tarjetaTO.setPago_para_no_generar_intereses(Formatter.formatMonto(tarjeta.getPagoParaNoGenerarIntereses()));
				tarjetaTO.setContrato(tarjeta.getContrato());
				tarjetaTO.setCredito_utilizado_en_tarjetas_adicionales(tarjeta.getCredUtilizadoTarjAdicionales());
				tarjetaTO.setDespues_del_corte(tarjeta.getDespuesCorte());
				tarjetaTO.setEstatus(tarjeta.getEstatus());
				tarjetaTO.setFecha_activacion(tarjeta.getFechaActivacion());
				tarjetaTO.setFecha_final_de_corte(tarjeta.getFechaFinalDeCorte());
				tarjetaTO.setFecha_limite_de_pago(Validator.isEmptyData(tarjeta.getFechaLimiteDePago())?"":tarjeta.getFechaLimiteDePago());
				tarjetaTO.setFecha_limite_de_pago_nueva(tarjeta.getFechaLimiteDePago_Nueva());
				tarjetaTO.setFecha_proximo_corte(tarjeta.getFechaProximoCorte());
				tarjetaTO.setFecha_proximo_pago(Validator.isEmptyData(tarjeta.getFechaProximoPago())?"":tarjeta.getFechaProximoPago());
				tarjetaTO.setLimite_credito(Formatter.formatMonto(tarjeta.getLimiteCredito()));
				tarjetaTO.setMostrar_nuevo_estado(tarjeta.getMostrarNuevoEstado());
				tarjetaTO.setNombre_titular(tarjeta.getTitularContrato());
				tarjetaTO.setNumero_de_periodos(tarjeta.getNumDePeriodos());
				tarjetaTO.setPeriodos(tarjeta.getPeriodos());
				tarjetaTO.setRetenido(Formatter.formatMonto(tarjeta.getRetenido()));
				tarjetaTO.setSaldo_al_corte(Formatter.formatMonto(tarjeta.getSaldoAlCorte()));
				tarjetaTO.setSecuencia(tarjeta.getSecuencia());
				tarjetaTO.setTarjeta_adicional(tarjeta.getTarjetaAdicional());
				tarjetaTO.setTarjeta_invalida(tarjeta.getTarjetaInvalida());
				tarjetaTO.setTarjeta_nueva(tarjeta.getTarjetaNueva());
				tarjetaTO.setTarjetas_adicionales(tarjeta.getTarjetasAdicionales());
				tarjetaTO.setTiene_tarjetas_adicionales(tarjeta.getTieneTarjetasAdicionales());
				tarjetaTO.setTipo(Validator.isEmptyData(tarjeta.getTipo())?"":tarjeta.getTipo());
				tarjetaTO.setTitular_contrato(tarjeta.getTitularContrato());
				tarjetaTO.setTitularidad(tarjeta.getTitularidad());
				cards.add(tarjetaTO);
			}
		}
		return cards;
	}

	private Inversion_mercado_dineroTO getMercadoDinero(ClienteTO clienteTO, Session session)throws Exception{
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
	
	private LoginTO filtrarCuentas(ClienteTO clienteTO){
		LoginTO loginTO = new LoginTO();
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();
		Collection<CuentaTO> cuentasCaptacion = new ArrayList<CuentaTO>();
		Collection<CuentaTO> cuentasInversion = new ArrayList<CuentaTO>();
		
		if(cuentas!=null){
			for(CuentaTO cuentaTO : cuentas){
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
		
		loginTO.setColeccion_inversiones(wrapperCuenta(cuentasInversion));
		loginTO.setCuentas(wrapperCuenta(cuentasCaptacion));
		
		return loginTO;
	}
	
	private Collection<com.bancoazteca.eservice.command.base.beans.CuentaTO> wrapperCuenta(Collection<CuentaTO> cuentasOrigen){
		Collection<com.bancoazteca.eservice.command.base.beans.CuentaTO> cuentasDestino = new ArrayList<com.bancoazteca.eservice.command.base.beans.CuentaTO>();
		for (CuentaTO cuenta : cuentasOrigen){				
			com.bancoazteca.eservice.command.base.beans.CuentaTO cuentaTO = new com.bancoazteca.eservice.command.base.beans.CuentaTO();
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