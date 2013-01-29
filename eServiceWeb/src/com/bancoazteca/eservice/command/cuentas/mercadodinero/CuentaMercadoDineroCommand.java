package com.bancoazteca.eservice.command.cuentas.mercadodinero;

import java.io.IOException;
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

import com.bancoazteca.elite.beans.ActualizacionSaldoPorCuentaRequestTO;
import com.bancoazteca.elite.beans.ActualizacionSaldoPorCuentaResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AsignacionesComprobanteTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosValidaPlazoTasaReponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosValidaPlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenOperacionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenOperacionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenResponseTO;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Comprobante_Inversion_ReportosTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.beans.DestinoMailTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_AsignacionTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_OperacionTO;
import com.bancoazteca.eservice.command.base.beans.Inversion_ReportosTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_Operaciones_Mercado_DineroTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.utils.eserviceemail.EnvioMail;
import com.bancoazteca.eservice.utils.eserviceemail.plantillas.ConstructorContenidoMail;
import com.bancoazteca.eservice.validator.Errors;

public class CuentaMercadoDineroCommand extends CommandBase{

	private static final String CUENTA_ASOCIADA="CUENTA_ASOCIADA";
	private static final String APERTURA="APERTURA";
	private static final String CUENTAS_INVERSION="CuentasInversion.properties";
	private double montoMinimo;
	private static final String CUENTA_MERCADO_DINERO_FORM="CUENTA_MERCADO_DINERO_FORM";
	private static final String INVERSION="INVERSION";
	

	Logger logger=Logger.getLogger(CuentaMercadoDineroCommand.class);

	@SuppressWarnings("deprecation")
	public Response solicitud(Session session) throws Exception{
		Response response = new Response();
		
		Date fecha=new Date();	
		int hora=fecha.getHours();
		
		PropertiesService propertiesService=PropertiesService.getInstance();
		int horaFin=Integer.parseInt(propertiesService.getPropertie("CuentasInversion.properties","inversiones.reportos.horario.horafin"));
		int horaInicio=Integer.parseInt(propertiesService.getPropertie("CuentasInversion.properties","inversiones.reportos.horario.horainicio"));
		
		if(hora>=horaFin || hora<horaInicio){
			response.setStatus(Errors.VALIDATION_CODE,"Recuerda que el horario para realizar tus inversiones es de las "+horaInicio+" a las "+horaFin+" horas.",null);
			return response;
		}
		
		Map<String, String> errors= new HashMap<String, String>();
		Cuenta_Mercado_DineroTO cuenta_Mercado_Dinero = new Cuenta_Mercado_DineroTO();
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();
		
		String producto=propertiesService.getPropertie("Alnova.properties","alnova.transaccion.bb02.producto");
		String subProducto=propertiesService.getPropertie("Alnova.properties","alnova.transaccion.bb02.sub_producto");
		montoMinimo=Integer.parseInt(propertiesService.getPropertie(CUENTAS_INVERSION, "montominimo.cuenta.mercadodinero"));
		CuentaTO cuentaTO = getProductAccountsPredicate(cuentas,producto,subProducto);

//		if(cuentaTO==null){
//			errors.put("eje_elite","El usiario debe de tener una cuenta Eje elite" );
//			return returnErrorMap(response, errors);
//		}
		
		
		InversionesDAO inversiones=new InversionesDAO();

		int securityLevel = Integer.parseInt(clienteTO.getSecurityData().getSecLevelVO().getSecurityLevel());
		if(securityLevel > 3 && securityLevel < 7){	
			Collection<PlazoTasaTO> colleccionPlazosTasas=null;
			logger.info("---------> No hay datos en cache.... comienza busqueda en base de datos.........");
			colleccionPlazosTasas = getDelegateSegundo().getPlazoTasaBD();
			session.addAttribute("plazos", colleccionPlazosTasas);
			InversionesRequestTO requestCuenta=new InversionesRequestTO();

			requestCuenta.setIdAlnova(clienteTO.getNumero());

			requestCuenta.setOperation("busqueda_cuenta");
			requestCuenta.setType("inversion_cliente");
			
			InversionesResponseTO respuestaCuenta = getDelegateSegundo().dispatchManager(requestCuenta);
			
			Collection<Cuenta_CargoTO> cuentasColeccion = null;
			String cuentaAsociada=respuestaCuenta.getMessage();
			
			
			if(cuentaAsociada!=null && !cuentaAsociada.equals("0")){
				
				cuentaAsociada=Formatter.removeSpaces(formatearCuenta(cuentaAsociada));				
				
				CuentaTO cuentaAsociadaTO = getAccountsPrdicate(cuentas, cuentaAsociada);
				cuentas.clear();
				cuentas.add(cuentaAsociadaTO);
				session.addAttribute(CUENTA_ASOCIADA,cuentaAsociadaTO);
				
			}
			
			
			
			Iterator<CuentaTO> iterador = cuentas.iterator();
			CuentaTO cuentaTemp=null;
			cuentaTO=null;
			Cuenta_CargoTO cuentaCargo= null;
			cuentasColeccion = new ArrayList<Cuenta_CargoTO>();
			while(iterador.hasNext()){
				cuentaTemp = iterador.next();	
				cuentaCargo= new Cuenta_CargoTO();
				String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaTemp.getNumero()));
				cuentaCargo.setNumero_cuenta(cuentaFormateada);
				cuentaCargo.setProducto(cuentaTemp.getDescripcion());
				cuentaCargo.setSaldo_disponible(String.valueOf(cuentaTemp.getDisponible()));
				cuentasColeccion.add(cuentaCargo);
			}
			int numeroCliente = inversiones.busquedaCliente(clienteTO.getNumero());
			if(numeroCliente!=-1){
				session.addAttribute(APERTURA,false);
				cuenta_Mercado_Dinero.setBandera_operacion(INVERSION);
				cuenta_Mercado_Dinero.setMonto_minimo(String.valueOf(montoMinimo));
				cuenta_Mercado_Dinero.setColeccion_cuentas(cuentasColeccion);
				cuenta_Mercado_Dinero.setColeccion_plazos_tasas(colleccionPlazosTasas);
			}else{
				cuenta_Mercado_Dinero.setBandera_operacion(APERTURA);
				cuenta_Mercado_Dinero.setMonto_minimo(String.valueOf(montoMinimo));
				cuenta_Mercado_Dinero.setColeccion_cuentas(cuentasColeccion);
				cuenta_Mercado_Dinero.setColeccion_plazos_tasas(colleccionPlazosTasas);
				session.addAttribute(APERTURA,true);
			}
		}else {
			errors.put("nivel_seguridad",EliteRules.MESSAGE_ERROR_AUTORIZACION );
			return returnErrorMap(response, errors);
		}
		response.addAttribute(cuenta_Mercado_Dinero);
		return response;
	}

	public Response validacion(Session session) throws Exception{
		Response response = new Response();
		Map<String,String> errors= new HashMap<String,String>();
		CuentaMercadoDineroForm forma=(CuentaMercadoDineroForm)getFormBean();
		
		ResourceFacadeSegundoSL delegatedos = getDelegateSegundo();
		
		ReportosValidaPlazoTasaTO plazoTasaTO=new ReportosValidaPlazoTasaTO();
		plazoTasaTO.setOperation(InversionesRequestTO.VALIDACION_PLAZO_TASA);
		plazoTasaTO.setType(InversionesRequestTO.EMPRESA_REPORTOS);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		String fecha=dateFormat.format(new Date());
		plazoTasaTO.setFechaOperacion(fecha);
		plazoTasaTO.setMontoOrden(forma.getMonto());
		plazoTasaTO.setPlazo(forma.getPlazo());
		plazoTasaTO.setTasa(forma.getTasa());
		plazoTasaTO.setPortal("Elite");
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();

		String monto=forma.getMonto();
		String cuenta=forma.getCuenta_origen(); 
	
		CuentaTO cuentaTO = getAccountsPrdicate(cuentas, cuenta);
		if(cuentaTO!=null){
			try {
				validaSaldoDisponible(cuentaTO.getNumero(), monto, session);
			} catch(InversionesGenericException ex){
				return returnErrorMap(response, ex.getInversionesExceptionTO().getErrorMap(),ex.getInversionesExceptionTO().getMessage());
			}catch (EliteDataException e) {
				buildErrorResponse(e, response);
			}			
		}else{
			errors.put("cuenta", "El número de cuenta " + cuenta + " proporcionado no es valido.");
			return returnErrorMap(response, errors);
		}
		session.addAttribute(CUENTA_MERCADO_DINERO_FORM, forma );
		
		ReportosValidaPlazoTasaReponseTO validaResponse=(ReportosValidaPlazoTasaReponseTO)delegatedos.dispatchManager(plazoTasaTO);
		
		if(!validaResponse.isOk()){
			String code=validaResponse.getCodigoMensaje();
			try{
				if(code.equals("0105")){
					Collection<PlazoTasaTO> plazos=(Collection<PlazoTasaTO>)session.getAttribute("plazos");
					enviaAvisoPlazosMail(clienteTO,forma.getMonto(),forma.getPlazo(),plazos);
				}	
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(code.equals("0105")){
				response.setStatus(-105,"Estimado Usuario, el plazo que eligió no se encuentra disponible por el momento. Por favor elija otra opción o intente más tarde",null);
			}else{
				response.setStatus(-105,validaResponse.getMensaje(),null);
			}
			
			return response;						
		}
		
		CuentaTO cuentaAsociada = (CuentaTO)session.getAttribute(CUENTA_ASOCIADA);
		
		if(cuentaAsociada!=null){
			String cuentaAsoc=Formatter.removeSpaces(formatearCuenta(cuentaAsociada.getNumero()));
			if(!cuentaAsoc.equals(cuenta)){
				response.setStatus(Errors.VALIDATION_CODE,"La cuenta que ha elegido no es la asociada con su cuenta de inversion.", null);
				return response;
			}
			
		}
		
//		TODO Descomentar para produccion
		boolean isApertura = (Boolean) session.getAttribute(APERTURA);
		if(isApertura){		
			try {
				obtenLlavePublica(response,clienteTO);
			} catch (EliteDataException e) {
				buildErrorResponse(e, response);
			}
		}

		return response;
	}

	private void enviaAvisoPlazosMail(ClienteTO clienteTO,String monto,String plazo,Collection<PlazoTasaTO>plazos) throws IOException {
		
					 
		String destinos=PropertiesService.getInstance().getPropertie("eMailDestinatarios.properties","email.destinos.mercadodineroplazos");
		String[] destinosArray = destinos.split(",");
		
		EnvioMail envioMail=new EnvioMail();
		DestinoMailTO destinoMailTO=null;
		List<DestinoMailTO> listaDestino=new ArrayList<DestinoMailTO>();
		
		ConstructorContenidoMail  constructorContenidoMail= new ConstructorContenidoMail();
		Map<String, String> parametros=new HashMap<String, String>();
		parametros.put("[[idAlnova]]", clienteTO.getNumero());
		parametros.put("[[monto]]", monto);
		parametros.put("[[plazo]]", plazo);
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd 'de' MMMM 'del' yyyy, 'a las' HH:mm:ss");
		String fecha=dateFormat.format(new Date());
		parametros.put("[[fecha]]", fecha);
		
		String plazosString="";
		for(PlazoTasaTO plazoTasaTO : plazos) {
			plazosString+=plazoTasaTO.getPlazo()+", ";
		}
		parametros.put("[[plazos]]", plazosString);
		
		constructorContenidoMail.setBody(parametros, "prueba");
		
		for (String dest : destinosArray) {
			destinoMailTO=new DestinoMailTO();
			destinoMailTO.setEmail(dest);
			destinoMailTO.setNombreDestinatario(dest);
			listaDestino.add(destinoMailTO);
		}
		String mailContent=constructorContenidoMail.getFullMail();
		envioMail.sendMail("Actualizar plazos de Mercado de Dinero",mailContent,listaDestino,"prueba");
		
	}

	public Response ejecucion(Session session) throws Exception, XmlDecodeException{
		Response response = new Response();
		
		Map<String,String> errors= new HashMap<String,String>();
		boolean esApertura=false;
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		InversionesDAO inversiones=new InversionesDAO();
		int numeroCliente = inversiones.busquedaCliente(cliente.getNumero());
		if(numeroCliente<0){
			esApertura=true;
		}
		
		CuentaMercadoDineroForm datos=(CuentaMercadoDineroForm)session.getAttribute(CUENTA_MERCADO_DINERO_FORM);
		CuentaMercadoDineroForm forma=(CuentaMercadoDineroForm)getFormBean();
		boolean isApertura = (Boolean) session.getAttribute(APERTURA);
		if(isApertura){
			if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				String tokenCode=forma.getClave_seguridad().string;
				
				TokenTO tokenTO=new TokenTO();
				tokenTO.setTokenCode(tokenCode);
				tokenTO.setUser(cliente.getUserName());
	
	//			TODO Descomentar para produccion
				if(!getDelegate().validaToken(tokenTO)){				
					try {
						obtenLlavePublica(response,cliente);
					} catch (EliteDataException e){
						buildErrorResponse(e, response);
						return response;
					}
					errors.put("clave_seguridad", "Lo sentimos su firma azteca no coincide, intente nuevamente.");
					return returnErrorMap(response, errors);
				}
			}else if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				String huellaClave=forma.getHuella_seguridad();
				HuellaDigitalTO huellaDigitalTO=new HuellaDigitalTO();
				huellaDigitalTO.setHuella(huellaClave);
				huellaDigitalTO.setUser(cliente.getUserName());
	
	//			TODO Descomentar para produccion
				if(!getDelegate().validaHuella(huellaDigitalTO)){				
					try {
						obtenLlavePublica(response,cliente);
					} catch (EliteDataException e){
						buildErrorResponse(e, response);
						return response;
					}
					errors.put("clave_seguridad", "Lo sentimos su huella azteca no coincide, intente nuevamente.");
					return returnErrorMap(response, errors);
				}
			}
		}

		
		cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Collection<CuentaTO> cuentas = cliente.getCuentas();
		String cuentaCargo=datos.getCuenta_origen();
		CuentaTO cuentaTO = getAccountsPrdicate(cuentas, cuentaCargo);
		if(cuentaTO!=null){
			try {
				validaSaldoDisponible(cuentaTO.getNumero(), datos.getMonto(), session);
				
				ResourceFacadeSegundoSL delegate = getDelegateSegundo();
				
				if(esApertura){	
					AperturaCuentaReportosRequestTO aperturaCuentaReportosRequestTO=new AperturaCuentaReportosRequestTO();
					aperturaCuentaReportosRequestTO.setCuentaCargo(cuentaTO.getNumero());
					aperturaCuentaReportosRequestTO.setCuentaClabe(cuentaTO.getClabe());
					aperturaCuentaReportosRequestTO.setIdAlnova(cliente.getNumero());
					aperturaCuentaReportosRequestTO.setMonto(datos.getMonto());
					aperturaCuentaReportosRequestTO.setOperation(InversionesRequestTO.OPERACION_APERTURA_INVERSION);
					aperturaCuentaReportosRequestTO.setPlazo(datos.getPlazo());
					aperturaCuentaReportosRequestTO.setPortalSolicitante(forma.getPortal());
					aperturaCuentaReportosRequestTO.setTasaBruta(datos.getTasa());
					aperturaCuentaReportosRequestTO.setType(InversionesRequestTO.EMPRESA_REPORTOS);

					CompraInversionReportosResponseTO compraInversionReportosResponse = (CompraInversionReportosResponseTO) delegate.dispatchManager(aperturaCuentaReportosRequestTO);

					DateFormat dateFormat =DateFormat.getDateInstance(DateFormat.FULL);

					Inversion_ReportosTO inversion_Reportos= new Inversion_ReportosTO();
					inversion_Reportos.setCuenta_cargo(cuentaTO.getCuenctaClabeFormateada());
					inversion_Reportos.setFecha_operacion(dateFormat.format(new Date()));
					inversion_Reportos.setFolio(compraInversionReportosResponse.getFolioOperacion());
					inversion_Reportos.setImporte(datos.getMonto());
					inversion_Reportos.setNumero_contrato(compraInversionReportosResponse.getNumeroDeContrato());
					inversion_Reportos.setPlazo(datos.getPlazo());
					inversion_Reportos.setTasa(datos.getTasa());
					inversion_Reportos.setTitular(cliente.getNombreCompleto());

					response.addAttribute(inversion_Reportos);
				}else{
					CompraInversionReportosRequestTO compraInversionReportosRequestTO=new CompraInversionReportosRequestTO();
					compraInversionReportosRequestTO.setCuentaCargo(cuentaTO.getNumero());
					compraInversionReportosRequestTO.setIdAlnova(cliente.getNumero());
					compraInversionReportosRequestTO.setMonto(datos.getMonto());
					compraInversionReportosRequestTO.setOperation(InversionesRequestTO.OPERACION_COMPRA_INVERSION);
					compraInversionReportosRequestTO.setPlazo(datos.getPlazo());
					compraInversionReportosRequestTO.setPortalSolicitante(forma.getPortal());
					compraInversionReportosRequestTO.setTasaBruta(datos.getTasa());
					compraInversionReportosRequestTO.setType(InversionesRequestTO.EMPRESA_REPORTOS);
					CompraInversionReportosResponseTO compraInversionReportosResponseTO=(CompraInversionReportosResponseTO)delegate.dispatchManager(compraInversionReportosRequestTO);


					Locale LOCALE_MX = new Locale("es","mx");
					Locale.setDefault(LOCALE_MX);
					DateFormat d=DateFormat.getDateInstance(DateFormat.FULL);
					
					Inversion_ReportosTO inversion_Reportos= new Inversion_ReportosTO();
					inversion_Reportos.setCuenta_cargo(cuentaTO.getCuenctaClabeFormateada());
					inversion_Reportos.setFecha_operacion(d.format(new Date()));
					inversion_Reportos.setFolio(compraInversionReportosResponseTO.getFolioOperacion());
					inversion_Reportos.setImporte(datos.getMonto());
					inversion_Reportos.setNumero_contrato(compraInversionReportosResponseTO.getNumeroDeContrato());
					inversion_Reportos.setPlazo(datos.getPlazo());
					inversion_Reportos.setTasa(datos.getTasa());
					inversion_Reportos.setTitular(cliente.getNombreCompleto());

					response.addAttribute(inversion_Reportos);
				}
				
//				updateBalance(session,cuentaCargo);
				
			}catch(InversionesGenericException ex){
//				errors.put(Errors.VALIDATION,ex.getInversionesExceptionTO().getErrorMap());
				return returnErrorMap(response, ex.getInversionesExceptionTO().getErrorMap(),ex.getInversionesExceptionTO().getMessage());
			}catch (EliteDataException e) {
				buildErrorResponse(e, response);
			}	
		}else{
			errors.put("CUENTA_ORIGEN", "La cuenta origen seleccionada no fue encontrada.");
			return returnErrorMap(response, errors);
		}
		
		return response;
	}

	public Response comprobante_inversion(Session session) throws Exception {

		CuentaMercadoDineroForm forma=(CuentaMercadoDineroForm)getFormBean();
		Response response = new Response();
		String folio = forma.getFolio();
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		

		int numeroContrato = Integer.valueOf(cliente.getNumContratoInvReportos());

		ResourceFacadeSegundoSL resourceFacde = getDelegateSegundo();
		ComprobanteInversionReportosRequestTO comprobanteInversionRequestTO = new ComprobanteInversionReportosRequestTO();

		comprobanteInversionRequestTO.setType(InversionesRequestTO.EMPRESA_REPORTOS);
		comprobanteInversionRequestTO.setOperation(InversionesRequestTO.OPERACION_COMPROBANTE_INVERSION);


		comprobanteInversionRequestTO.setPortalSolicitante(forma.getPortal());
		comprobanteInversionRequestTO.setFolio_operacion(folio);
		comprobanteInversionRequestTO.setNumero_contrato(forma.getNumero_contrato());
		comprobanteInversionRequestTO.setIdAlnova(cliente.getNumero());
		
		comprobanteInversionRequestTO.setNumero_contrato(String.valueOf(numeroContrato));

		try{
			ComprobanteInversionReportosResponseTO comprobanteInversion = (ComprobanteInversionReportosResponseTO) resourceFacde.dispatchManager(comprobanteInversionRequestTO);
			
	
			Comprobante_Inversion_ReportosTO comprobante_Inversion_ReportosTO=new Comprobante_Inversion_ReportosTO();
	
			comprobante_Inversion_ReportosTO.setCiudad(comprobanteInversion.getCiudad());
			comprobante_Inversion_ReportosTO.setCodigo_postal(comprobanteInversion.getCodigo_postal());
			comprobante_Inversion_ReportosTO.setColonia(comprobanteInversion.getColonia());
			comprobante_Inversion_ReportosTO.setDireccion(comprobanteInversion.getDireccion());
			comprobante_Inversion_ReportosTO.setFecha_concertacion(comprobanteInversion.getFecha_concentracion());
			comprobante_Inversion_ReportosTO.setFecha_liquidacion(comprobanteInversion.getFecha_liquidacion());
			comprobante_Inversion_ReportosTO.setFecha_operacion(comprobanteInversion.getFechaOperacion());
			comprobante_Inversion_ReportosTO.setFolio_orden(comprobanteInversion.getFolio_orden());
			comprobante_Inversion_ReportosTO.setImporte_inversion(comprobanteInversion.getImporte_inversion());
			comprobante_Inversion_ReportosTO.setInteres_premio(comprobanteInversion.getInteres_premio());
			comprobante_Inversion_ReportosTO.setIsr(comprobanteInversion.getIsr());
			comprobante_Inversion_ReportosTO.setMonto_al_vencimiento(comprobanteInversion.getMonto_al_vencimiento());
			comprobante_Inversion_ReportosTO.setNombre_cliente(comprobanteInversion.getNombre_cliente());
			comprobante_Inversion_ReportosTO.setNumero_contrato(comprobanteInversion.getNumero_contrato());
			comprobante_Inversion_ReportosTO.setPlazo(comprobanteInversion.getPlazo());
			comprobante_Inversion_ReportosTO.setPromotor(comprobanteInversion.getPromotor());
			comprobante_Inversion_ReportosTO.setRfc(comprobanteInversion.getRfc());
			comprobante_Inversion_ReportosTO.setTasa(comprobanteInversion.getTasa());
			comprobante_Inversion_ReportosTO.setTotal_importe(comprobanteInversion.getTotal_importe());
			comprobante_Inversion_ReportosTO.setTotal_titulos(comprobanteInversion.getTotal_titulos());
			comprobante_Inversion_ReportosTO.setValor_letra(comprobanteInversion.getValor_letra());
			comprobante_Inversion_ReportosTO.setFecha_vencimiento(comprobanteInversion.getFecha_vencimiento());
			comprobante_Inversion_ReportosTO.setConcepto_inversion(comprobanteInversion.getConcepto_inversion());
			comprobante_Inversion_ReportosTO.setEmpresa_reportado(comprobanteInversion.getEmpresa_reportado());
	
			Collection<Detalle_AsignacionTO>collection=new ArrayList<Detalle_AsignacionTO>();
			Detalle_AsignacionTO detalle_AsignacionTO=null;
			for (AsignacionesComprobanteTO detalle : comprobanteInversion.getColeccionAsignaciones()) {
				detalle_AsignacionTO=new Detalle_AsignacionTO();
				detalle_AsignacionTO.setEmisora(detalle.getEmisora());
				detalle_AsignacionTO.setMoneda(detalle.getMoneda());
				detalle_AsignacionTO.setMonto(detalle.getMonto());
				detalle_AsignacionTO.setPrecio(detalle.getPrecio());
				detalle_AsignacionTO.setSerie(detalle.getSerie());
				detalle_AsignacionTO.setTasa_operativa(detalle.getTasaOperativa());
				detalle_AsignacionTO.setTipo_cambio(detalle.getTipoCambio());
				detalle_AsignacionTO.setTipo_operacion(detalle.getTipoOperacion());
				detalle_AsignacionTO.setTipo_valor(detalle.getTipoValor());
				detalle_AsignacionTO.setTitulos(detalle.getTitulos());
				detalle_AsignacionTO.setEmisora_indeval(detalle.getEmisoraIndeval());
				detalle_AsignacionTO.setValor_nominal(detalle.getValorNominal());
				collection.add(detalle_AsignacionTO);
			}
	
			comprobante_Inversion_ReportosTO.setColeccion_asiganciones(collection);
	
			response.addAttribute(comprobante_Inversion_ReportosTO);
		}catch(InversionesGenericException exception){
//			response.addAttribute(exception.getInversionesExceptionTO().getErrorMap());
//			response.setStatus(-1,exception.getInversionesExceptionTO().getMessage(),null);
			response = returnErrorMap(response, exception.getInversionesExceptionTO().getErrorMap());
			response.setStatus(Errors.VALIDATION_CODE, exception.getMessage(), null);
			return response;			
		}
		return response;
	}

	public Response resumen_operaciones_vigentes(Session session)throws Exception{
		Response response = new Response();
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaMercadoDineroForm cuentaMercadoDineroForm=(CuentaMercadoDineroForm)getFormBean();
		
		try{
			Resumen_Operaciones_Mercado_DineroTO resumen_Operaciones_Mercado_DineroTO = ejecutaResumenOperacines(cliente, InversionesRequestTO.OPERACION_RESUMEN_OPERACIONES_VIGENTES,cuentaMercadoDineroForm.getPortal());
			response.addAttribute(resumen_Operaciones_Mercado_DineroTO);
		}catch(InversionesGenericException exception){
			response = returnErrorMap(response, exception.getInversionesExceptionTO().getErrorMap());
			response.setStatus(Errors.VALIDATION_CODE, exception.getMessage(), null);
			return response;	
		}
		return response;		
	}

	public Response resumen_operaciones_historicas(Session session)throws Exception{
		Response response = new Response();
		ClienteTO cliente = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaMercadoDineroForm cuentaMercadoDineroForm=(CuentaMercadoDineroForm)getFormBean();
		
		try{
			Resumen_Operaciones_Mercado_DineroTO resumen_Operaciones_Mercado_DineroTO = ejecutaResumenOperacines(cliente, InversionesRequestTO.OPERACION_RESUMEN_OPERACIONES_HISTORICAS,cuentaMercadoDineroForm.getPortal());
			response.addAttribute(resumen_Operaciones_Mercado_DineroTO);
		}catch(InversionesGenericException exception){
			response = returnErrorMap(response, exception.getInversionesExceptionTO().getErrorMap());
			response.setStatus(Errors.VALIDATION_CODE, exception.getMessage(), null);
			return response;	
		}
		return response;
	}

	public Resumen_Operaciones_Mercado_DineroTO ejecutaResumenOperacines(ClienteTO cliente, String operacion,String portal) throws InversionesGenericException, ServiceLocatorException{
		String idAlnova = cliente.getNumero();

		InversionesDAO inversiones=new InversionesDAO();
		int numeroContrato = inversiones.busquedaNumeroContrato(idAlnova);

		ResumenOperacionesRequestTO resumenOperacionesRequestTO = new ResumenOperacionesRequestTO();
		resumenOperacionesRequestTO.setType(InversionesRequestTO.EMPRESA_REPORTOS);
		resumenOperacionesRequestTO.setOperation(operacion);
		resumenOperacionesRequestTO.setPortalSolicitante(portal);
		resumenOperacionesRequestTO.setNumeroContrato(String.valueOf(numeroContrato));
		resumenOperacionesRequestTO.setIdAlnova(cliente.getNumero());

		ResourceFacadeSegundoSL delegate=getDelegateSegundo();
		ResumenResponseTO resumenResponseTO = (ResumenResponseTO)delegate.dispatchManager(resumenOperacionesRequestTO);
		Collection<ResumenOperacionesResponseTO> resumenOperaciones =resumenResponseTO.getResumenOperaciones();

		Resumen_Operaciones_Mercado_DineroTO resumen_Operaciones_Mercado_DineroTO=new Resumen_Operaciones_Mercado_DineroTO();
		Collection<Detalle_OperacionTO> listaOperaciones=new ArrayList<Detalle_OperacionTO>();
		Detalle_OperacionTO operacionTO=null;
		for (ResumenOperacionesResponseTO resumenOperacionesResponseTO : resumenOperaciones) {
			operacionTO=new Detalle_OperacionTO();
			operacionTO.setEstatus(resumenOperacionesResponseTO.getEstatus());
			operacionTO.setFecha_liquidacion(resumenOperacionesResponseTO.getFechaLiquidacion());
			operacionTO.setFecha_vencimiento(resumenOperacionesResponseTO.getFechaVencimiento());
			operacionTO.setFolio(resumenOperacionesResponseTO.getFolio());
			operacionTO.setInteres_generados(resumenOperacionesResponseTO.getInteresGenerados());
			operacionTO.setMonto(resumenOperacionesResponseTO.getMonto());
			operacionTO.setMonto_total(resumenOperacionesResponseTO.getMontoTotal());
			operacionTO.setPlazo(resumenOperacionesResponseTO.getPlazo());
			operacionTO.setRetencion(resumenOperacionesResponseTO.getRetencion());
			operacionTO.setTasa(resumenOperacionesResponseTO.getTasa());
			listaOperaciones.add(operacionTO);
		}
		resumen_Operaciones_Mercado_DineroTO.setColeccion_operaciones(listaOperaciones);

		return resumen_Operaciones_Mercado_DineroTO;
	}

	private void validaSaldoDisponible(String numeroCuenta, String monto, Session session) throws EliteDataException, ServiceLocatorException, InversionesGenericException{
		ResourceFacadeSegundoSL delegate = getDelegateSegundo();
		
		ActualizacionSaldoPorCuentaRequestTO actualizacionSaldoRequestTO = new ActualizacionSaldoPorCuentaRequestTO();
		actualizacionSaldoRequestTO.setNumeroCuenta(numeroCuenta);
		actualizacionSaldoRequestTO.setOperation(InversionesRequestTO.ACTUALIZACION_SALDO_POR_CUENTA);
		actualizacionSaldoRequestTO.setType(InversionesRequestTO.EMPRESA_ALNOVA);
		
		ActualizacionSaldoPorCuentaResponseTO actualizacionSaldoResponseTO = (ActualizacionSaldoPorCuentaResponseTO) delegate.dispatchManager(actualizacionSaldoRequestTO);
		
		if(actualizacionSaldoResponseTO != null){
			double inversion=Double.parseDouble(monto);
			double disponible=actualizacionSaldoResponseTO.getSaldoDisponible();
			logger.info("Monto a invertir: " + inversion);
			logger.info("Monto disponible: " + disponible);
			if(inversion > disponible){
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("monto","El monto que desea invertir es mayor al saldo disponible de la cuenta. ");
				throw new EliteDataException("SaldoInsificiente", errors, 1);
			}							
		}
		
		session.addAttribute(B402_ACTUALIZACION_SALDO_CUENTA, actualizacionSaldoResponseTO);
		
	}

}