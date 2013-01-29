package com.bancoazteca.eservice.command.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaInversionTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.LabelValueBeanTO;
import com.bancoazteca.elite.beans.LlaveSeguridadRequestTO;
import com.bancoazteca.elite.beans.LlaveSeguridadResponseTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajeTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.TokenTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.logout.LogoutCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class CommandBase extends Command implements CommandConstantes  {

	private static final Logger log=Logger.getLogger(CommandBase.class);
	
	public static final String TAG_HUELLA="huella";
	public static final String OPCION_HUELLA="2";
	public static final String TAG_TOKEN="token";
	public static final String OPCION_TOKEN="1";	
	public static final String TAG_NIP="nip";	
	public static final String OPCION_NIP="3";
	public static final String CUENTA_ESTATUS_HISTORICO="0";

	public Response execute(Session session)throws Exception{		
		Response response = new Response();
		
		Locale LOCALE_MX = new Locale("es","mx");
		Locale.setDefault(LOCALE_MX);
		
		FormBean formBean = getFormBean();

		String method = null;
		if (formBean!=null){
			method = formBean.getComando();
		}

		try {
			if (method!=null){
				method = method.toLowerCase();
				Method m = this.getClass().getMethod(method, new Class[]{Session.class});
				response = (Response) m.invoke(this, new Object[]{session});			
			}else{
				response.setStatus(Errors.VALIDATION_CODE, "Command not found", null);
			}
		}catch(InvocationTargetException ex){
			ex.printStackTrace();
			if(ex.getCause().getClass().equals(SessionExpiredException.class)){
				throw new SessionExpiredException (ex); 
			}else{
				throw ex;
			}
			
		}catch(Exception e){
			throw e;
		}	
		return response;
	}

	protected boolean isContrato(String cuenta){
		PropertiesService propertiesService=PropertiesService.getInstance();
		try {
			String listaDigitos=propertiesService.getPropertie("ContratosCuentas.properties", "contratos.cuentas.digitos");
			int dezplazamiento=Integer.parseInt(propertiesService.getPropertie("ContratosCuentas.properties", "contratos.cuentas.offset"));
			StringTokenizer stringTokenizer=new StringTokenizer(listaDigitos,",");
			while(stringTokenizer.hasMoreElements()){
				String parDigitos=stringTokenizer.nextToken();
				String pattern="\\d{"+(dezplazamiento-1)+"}"+parDigitos+"\\d*";
				if(cuenta.matches(pattern)){
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			
		}
		return false;
	}
	
	protected ResourceFacadeSL getDelegate() throws ServiceLocatorException{
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		ResourceFacadeSL facade;
		try {
			facade = serviceLocator.getFacadeEJBHome().create();
		} catch (EJBException e) {
			throw new ServiceLocatorException(e);
		} catch (CreateException e) {
			throw new ServiceLocatorException(e);
		}
		return facade;
	}

	public ResourceFacadeSegundoSL getDelegateSegundo() throws ServiceLocatorException{
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		ResourceFacadeSegundoSL facade;
		try {
			facade = serviceLocator.getFacadeSegundoEJBHome().create();
		} catch (EJBException e) {
			throw new ServiceLocatorException(e);
		} catch (CreateException e) {
			throw new ServiceLocatorException(e);
		}
		return facade;
	}
	
	protected MensajesTO buildMessages(Map<String, String> map){
		MensajesTO mensajesTO = new MensajesTO();		
		if (map!=null && !map.isEmpty()){
			Collection<MensajeTO> col = new ArrayList<MensajeTO>();
			for (String key : map.keySet()){
				String value = map.get(key);
				MensajeTO error = new MensajeTO();
				error.setPropiedad(key);
				error.setDescripcion(value);
				col.add(error);
			}
			mensajesTO.setColeccion_mensajes(col);
		}
		return mensajesTO;
	}
	
	public MensajesTO validationSecurityLevel(){
		HashMap<String, String> errors = new HashMap<String, String>();
		errors.put("seguridad", "A partir del 1 de marzo de 2007, por tu seguridad, todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los siguientes dispositivos: Firma Azteca ó Huella Azteca.");
		MensajesTO mensajesTO = buildMessages(errors);
		return mensajesTO;
	}

	private void logout(Session session) throws Exception{
		LogoutCommand logoutCommand = new LogoutCommand();
		logoutCommand.execute(session);
	}

	private String stackTraceToString(Throwable e){
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		StringBuffer buffer = new StringBuffer();
		for (int j=0;j<stackTraceElements.length;j++){
			StackTraceElement stackTraceElement = stackTraceElements[j];
			buffer.append(stackTraceElement.toString()+";");
		} 
		return buffer.toString();
	}

	protected void updateBalance(Session session,String cuentaCargo) throws ServiceLocatorException, EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String userName = clienteTO.getUserName();

		BalanceRequestTO balanceRequestTO = new BalanceRequestTO();
		balanceRequestTO.setUser(userName);
		balanceRequestTO.setAcountNumber( cuentaCargo );
		ResourceFacadeSL facade = getDelegate();		
		LoginResponseTO loginResponseTO = null;
		try{
			loginResponseTO = facade.getActualizarCuentaUsuario( balanceRequestTO );		
			clienteTO = loginResponseTO.getClienteTO();
			if( !clienteTO.isActualizacionCuenta() ){
				throw new Exception( "No se pudo actualizar el saldo de la cuenta del usuario." );
			}
			else{
				clienteTO.setUserName(userName);
				Collection<LabelValueBeanTO> allAccounts = setUnifyAllAccounts(clienteTO);
				clienteTO.setUnifyAllAccounts(allAccounts);
				session.addAttribute(CLIENTE_TO, clienteTO);
			}
		}catch (Exception e) {
			updateBalanceDual( session );
		}	
		
	}
	
 	private void updateBalanceDual(Session session) throws ServiceLocatorException, EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String userName = clienteTO.getUserName();

		LoginRequestTO loginRequestTO = new LoginRequestTO();
		loginRequestTO.setUser(userName);
		try{
			ResourceFacadeSL facade = getDelegate();		
			LoginResponseTO loginResponseCompletoTO = facade.getCuentasUsuario(loginRequestTO);	
			LoginResponseTO loginResponseParaleloTO = facade.getCuentasUsuarioParalelo(loginRequestTO);	

			clienteTO = loginResponseCompletoTO.getClienteTO();
			ClienteTO clienteParaleloTO = loginResponseParaleloTO.getClienteTO();

			Collection<CuentaTO> cuentasParalelo = clienteParaleloTO.getCuentas();
			
			Collection<CuentaTO>cuentasActualizadas = new ArrayList<CuentaTO>();
			
			for (CuentaTO cuenta : clienteTO.getCuentas()){
				CuentaTO cuentaParalelo = getAccountsPrdicate(cuentasParalelo, Formatter.removeSpaces(formatearCuenta(cuenta.getNumero())));
				if(cuentaParalelo!=null){
					cuenta.setRetenido(cuentaParalelo.getRetenido());
					cuenta.setDisponible(cuentaParalelo.getDisponible());
				}
				cuentasActualizadas.add(cuenta);
			}
			
			clienteTO.setCuentas(cuentasActualizadas);
			
			clienteTO.setUserName(userName);
			Collection<LabelValueBeanTO> allAccounts = setUnifyAllAccounts(clienteTO);
			clienteTO.setUnifyAllAccounts(allAccounts);
			session.addAttribute(CLIENTE_TO, clienteTO);
		} catch( Exception e ){
			throw new SessionExpiredException( e );
		}
	}

	protected void updateBalance(Session session) throws ServiceLocatorException, EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String userName = clienteTO.getUserName();

		LoginRequestTO loginRequestTO = new LoginRequestTO();
		loginRequestTO.setUser(userName);
		loginRequestTO.setReload(true);
		ResourceFacadeSL facade = getDelegate();		

		LoginResponseTO loginResponseTO = facade.getCuentasUsuario(loginRequestTO);		
//		clienteTO = loginResponseTO.getClienteTO();
//		Collection<CuentaTO>cuentasActualizadas=new ArrayList<CuentaTO>();
//		for (CuentaTO cuenta : clienteTO.getCuentas()){
//			double saldoTotal=cuenta.getRetenido()+cuenta.getDisponible();
//			cuenta.setBalance(saldoTotal);
//			cuentasActualizadas.add(cuenta);
//		}
//		clienteTO.setCuentas(cuentasActualizadas);
		
		clienteTO.setUserName(userName);
		Collection<LabelValueBeanTO> allAccounts = setUnifyAllAccounts(clienteTO);
		clienteTO.setUnifyAllAccounts(allAccounts);
		session.addAttribute(CLIENTE_TO, clienteTO);
	}

	protected Collection<LabelValueBeanTO> setUnifyAllAccounts(ClienteTO clienteTO){
		Collection<LabelValueBeanTO> allAccounts = new ArrayList<LabelValueBeanTO>();
		Collection<CuentaTO> cuentasClienteTO = clienteTO.getCuentas();
		int j = 0;

		for(CuentaTO cuentaTO : cuentasClienteTO){
			if(!Validator.isEmptyData(cuentaTO.getSubproducto())){
				LabelValueBeanTO valueBeanTO = new LabelValueBeanTO();
				valueBeanTO.setLabel(cuentaTO.getCuentaFormateada() + " " + cuentaTO.getDescripcion() + " " + Formatter.formatMontoPesos(cuentaTO.getDisponible().doubleValue()));
				valueBeanTO.setCuentaFormateada(cuentaTO.getCuentaFormateada());
				valueBeanTO.setNumeroCuenta(cuentaTO.getNumero());
				valueBeanTO.setDescripcion(cuentaTO.getDescripcion());
				if(cuentaTO.getIndex()!=null){
					valueBeanTO.setValue(Integer.parseInt(cuentaTO.getIndex()));
				}else{
					valueBeanTO.setValue(j++);
				}

				allAccounts.add(valueBeanTO);
			}
		}
		return allAccounts;
	}

	protected CuentaTO getAccountsPrdicate(Collection<CuentaTO> collectionCuentas, String param) {
		CuentaTO cuenta = new CuentaTO();
		CuentaPredicate cuentaPredicate = new CuentaPredicate();

		cuentaPredicate.setCuenta(param);
		cuenta = (CuentaTO) CollectionUtils.find(collectionCuentas, cuentaPredicate);
		return cuenta;
	}

	private class CuentaPredicate implements Predicate {
		private String cuenta;

		public boolean evaluate(Object obj) {
			CuentaTO cuentaTO = (CuentaTO) obj;
			String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaTO.getNumero()));
			if (cuentaFormateada.equals(Formatter.removeSpaces(cuenta))) {
				return true;
			} else {
				return false;
			}
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}


	protected CuentaInversionTO getAccountsInversionPrdicate(Collection<CuentaInversionTO> collectionCuentas, String param) {
		CuentaInversionTO cuentaInversión = new CuentaInversionTO();
		CuentaInversionPredicate cuentaPredicateInversion = new CuentaInversionPredicate();

		cuentaPredicateInversion.setCuenta(param);
		cuentaInversión = (CuentaInversionTO) CollectionUtils.find(collectionCuentas, cuentaPredicateInversion);
		return cuentaInversión;
	}

	private class CuentaInversionPredicate implements Predicate {
		private String cuenta;

		public boolean evaluate(Object obj) {
			CuentaInversionTO cuentaInversionTO = (CuentaInversionTO) obj;
			if ((cuentaInversionTO!=null)&&(cuenta.equals(Formatter.removeSpaces(cuentaInversionTO.getNumeroCuenta())))) {
				return true;
			} else {
				return false;
			}
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}

	public void buildErrorResponse(EliteDataException e, Response response){
		e.printStackTrace();
		if(e.getLevel()==EliteRules.LEVEL_ACTION_ERRORS){
			MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
			response.addAttribute(mensajesTO);
			Collection<MensajeTO> coleccionMensajesTO=mensajesTO.getColeccion_mensajes();
			if(coleccionMensajesTO!=null && !coleccionMensajesTO.isEmpty()){
				StringBuilder stringBPropiedades=new StringBuilder();
				Iterator<MensajeTO> mensajesIterator=coleccionMensajesTO.iterator();
				MensajeTO mensajeTO;
				String propiedad="";
				while(mensajesIterator.hasNext()){
					mensajeTO=mensajesIterator.next();
					propiedad=mensajeTO.getPropiedad();
					stringBPropiedades.append(propiedad+", ");
				}
				int ultimoIndiceDeComma;
				String errores="";
				if(!stringBPropiedades.toString().equals("")){
					ultimoIndiceDeComma=stringBPropiedades.lastIndexOf(",");
					stringBPropiedades.replace(ultimoIndiceDeComma,ultimoIndiceDeComma+2,".");
					errores=" en las propiedades: "+stringBPropiedades.toString();
				}
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+errores, null);
			}else{
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}else if(e.getLevel()==EliteRules.LEVEL_PATHS){
			if(e.getErrorData() instanceof Map){
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				Collection<MensajeTO> coleccionMensajesTO=mensajesTO.getColeccion_mensajes();
				if(coleccionMensajesTO!=null && !coleccionMensajesTO.isEmpty()){
					StringBuilder stringBPropiedades=new StringBuilder();
					Iterator<MensajeTO> mensajesIterator=coleccionMensajesTO.iterator();
					MensajeTO mensajeTO;
					String propiedad="";
					while(mensajesIterator.hasNext()){
						mensajeTO=mensajesIterator.next();
						propiedad=mensajeTO.getPropiedad();
						stringBPropiedades.append(propiedad+", ");
					}
					int ultimoIndiceDeComma;
					String errores="";
					if(!stringBPropiedades.toString().equals("")){
						ultimoIndiceDeComma=stringBPropiedades.lastIndexOf(",");
						stringBPropiedades.replace(ultimoIndiceDeComma,ultimoIndiceDeComma+2,".");
						errores=" en las propiedades: "+stringBPropiedades.toString();
					}
					response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION+errores, null);
				}else{
					response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
				}
			}else {
				response.setStatus(Errors.EXCEPTION_CODE, Errors.ERROR_GENERAL,null);
			}
		}
		
		DispositivoHuellaTO dispositivoHuellaTO=e.getDispositivoHuellaTO();
		if(dispositivoHuellaTO!=null){
			HuellaTO huellaTO=new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			response.addAttribute(huellaTO);
		}
	}
	
	protected void obtenLlavePublica(Response response,ClienteTO clienteTO) throws Exception{
		String nivelSeguridad=clienteTO.getSecurityData().getSecLevelVO().getNivelMovimientos();
		if(nivelSeguridad.equals("4")||nivelSeguridad.equals("6")){
			ResourceFacadeSL delegate;
			delegate = getDelegate();
			String user = clienteTO.getUserName();

			LlaveSeguridadRequestTO llaveSeguridadRequestTO=new LlaveSeguridadRequestTO();
			llaveSeguridadRequestTO.setUser(user);
			LlaveSeguridadResponseTO llaveResponse = delegate.obtenerLlaveSeguridad(llaveSeguridadRequestTO);
			
			HuellaTO huellaTO=new HuellaTO();
			huellaTO.setLlave_publica(llaveResponse.getLlave());
			huellaTO.setLongitud_huella(llaveResponse.getLogitud());
			response.addAttribute(huellaTO);
		}
	}
	protected Response returnErrorMap(Response response,Map<String, String> errors){
		MensajesTO mensajesTO = buildMessages(errors);
		response.addAttribute(mensajesTO);
		response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
		return response;
	}
	
	protected Response returnErrorMap(Response response,Map<String, String> errors,String mensaje){
		if(errors!=null){
			return returnErrorMap(response, errors);
		}else{
			response.setStatus(Errors.VALIDATION_CODE, mensaje, null);
			return response;
		}
		
	}
	
	protected CuentaTO getProductAccountsPredicate(Collection<CuentaTO> collectionCuentas, String producto,String supProducto) {
		CuentaTO cuenta = new CuentaTO();
		CuentaProductoPredicate cuentaPredicate = new CuentaProductoPredicate();

		cuentaPredicate.setProducto(producto);
		cuentaPredicate.setSubProducto(supProducto);
		cuenta = (CuentaTO) CollectionUtils.find(collectionCuentas, cuentaPredicate);
		return cuenta;
	}


	protected class CuentaProductoPredicate implements Predicate {
		private String producto;
		private String subProducto;

		public String getSubProducto() {
			return subProducto;
		}

		public void setSubProducto(String subProducto) {
			this.subProducto = subProducto;
		}

		public boolean evaluate(Object obj) {
			if (obj instanceof CuentaTO) {
				CuentaTO cuentaTO = (CuentaTO) obj;
				if (Formatter.removeSpaces(cuentaTO.getProducto()).equals(producto) && Formatter.removeSpaces(cuentaTO.getSubproducto()).equals(subProducto)) {
					return true;
				} else {
					return false;
				}
			}else{
				return false;
			}
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}
	}
	
	protected String formatearCuenta(String cuenta) {
		String cuentaFormateada = Formatter.formatSplittedCuenta(cuenta);
		return cuentaFormateada;	
	}
	
	protected Double getCantidad(Double cantidad){
		Double cantidadTotal = 0.0;
		if(cantidad != null){
			cantidadTotal = cantidad;
		}
		return cantidadTotal;
	}
	
	protected static String getException(Exception e ){
		StringWriter w = new StringWriter();
		e.printStackTrace(new PrintWriter(w));
		return w.toString();
	}
	
	public int getIndexTokenByNumeroSerie(Collection<TokenTO> tokenCollectionTO,
			String numeroSerie) {
		int index = 0;
		int indexTmp = -1;
		for (TokenTO token : tokenCollectionTO) {
			if (token.getNumero_serie().equalsIgnoreCase(numeroSerie)) {
				indexTmp = index;
				break;
			}
			index++;
		}
		return indexTmp;
	}
	
	public int getIndexByCuentaCargo(Collection<CuentaLoTO> listaCuentas,
			String cuentaBuscar, ClienteTO clienteTO) {
		CuentaTO cuentaTO;
		int index = 0;
		int indexTmp = -1;
		for (CuentaLoTO cuenta: listaCuentas) {
			cuentaTO = getAccountsPrdicate( clienteTO.getCuentas(), Formatter.formatCuenta(cuenta.getNumero()) );
			if (cuentaTO.getCuentaFormateada().replace(" ", "").equalsIgnoreCase(cuentaBuscar)) {
				indexTmp = index;
				break;
			}
			index++;
		}
		return indexTmp;
	}
}