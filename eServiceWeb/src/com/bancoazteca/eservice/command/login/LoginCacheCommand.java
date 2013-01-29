package com.bancoazteca.eservice.command.login;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.beans.ParameterOnSessionTO;
import com.bancoazteca.elite.beans.TarjetaBaseAlnova;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Detalle_OperacionTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_mercado_dineroTO;
import com.bancoazteca.eservice.command.base.beans.LoginTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_Operaciones_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.TarjetaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;
import com.bancoazteca.eservice.command.cuentas.mercadodinero.CuentaMercadoDineroCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;


public class LoginCacheCommand extends CommandBase {

	private static final Logger log = Logger.getLogger(LoginCacheCommand.class);

	public Response execute(Session session) throws Exception {
		Response response = new Response();
		log.info("LoginCacheCommand");
		ClienteTO clienteTO=new ClienteTO();
		LoginForm loginForm = (LoginForm) getFormBean();
		LoginRequestTO loginRequestTO = new LoginRequestTO();	
		LoginResponseTO loginResponseTO = null;
		
		String user = loginForm.getUsuario();
		loginRequestTO.setUser(user);
		loginRequestTO.setPassword(loginForm.getPassword().toString());
		String validationIdApplication = Session.getSessionApplication(loginForm.getIdaplicacion().toString());
		String aplicacion = SessionManager.getInstance().getKeyApplication( loginForm.getIdaplicacion().toString() );
		loginRequestTO.setAplicacion(aplicacion);
		if(validationIdApplication.equals(ID_APPLICATION_VALIDA)){
			ResourceFacadeSL facadeSL = getDelegate();

			try {
				//loginRequestTO.setReload(true);
				loginResponseTO=facadeSL.loginCliente(loginRequestTO);
			} catch (EliteDataException e) {
				if (e.getMessage() != null && e.getErrorData().equals("1")) {
					
						loginRequestTO.setAction("abrir");
						loginResponseTO=facadeSL.sessionManagment(loginRequestTO);
					
				}
				if (e.getMessage() != null && e.getMessage().equalsIgnoreCase("Maximo Intentos")){
					throw new UsuarioException(e.getMessage());
				}
			}
			session = Session.create(loginForm.getIdaplicacion().toString(),user);
			
			response.setIdSession(session);
			
			LoginTO loginTO = new LoginTO();
			log.info( "ID Aplicación " + loginForm.getIdaplicacion() );
			
			clienteTO = loginResponseTO.getClienteTO();		
			clienteTO.setUserName(loginForm.getUsuario());
			loginTO =(LoginTO)establecerLoginCache(clienteTO, session);
			
			try {
				Inversion_mercado_dineroTO inversion_mercado_dinero = getMercadoDinero(clienteTO, session);
				loginTO.setInversion_mercado_dinero(inversion_mercado_dinero);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			loginTO.setNivel_seguridad(setNivelSeguridad(clienteTO));
			
			response.addAttribute(loginTO);
			session.addAttribute(CLIENTE_TO, clienteTO);
			
		}else if(validationIdApplication.equals(ID_APPLICATION_INCORRECTA)){
			response.setStatus(Errors.SESSION_APPLICATION_NOT_FOUND_CODE, "Sesión de aplicación incorrecta", null);
		}else if(validationIdApplication.equals(ID_APPLICATION_EXPIRADA)){
			response.setStatus(Errors.SESSION_APPLICATION_EXPIRED, "Sesión de aplicación expirada", null);
		}		
		
//		ParameterOnSessionTO onSessionTO=new ParameterOnSessionTO();
//		onSessionTO.setNombreValor("typeName");
//		onSessionTO.setValor("crmovil");
//		onSessionTO.setUsuario(user);		
//		getDelegate().setParameterOnEBankSession(onSessionTO);
		
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