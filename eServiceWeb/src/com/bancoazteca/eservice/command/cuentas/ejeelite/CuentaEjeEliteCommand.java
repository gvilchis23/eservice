package com.bancoazteca.eservice.command.cuentas.ejeelite;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaEjeEliteEntradaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaEjeEliteRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Apertura_cuenta_eje_eliteTO;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_eje_eliteTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
/**
 * La clase CuentaEjeEliteCommand es la implementación del comando de aperturas de cuenta 
 * eje elite, la apertura se realiza invocando al los métodos del ResourceFacadeSegundoSL. <br/>
 * Así como también esta clase es la encargada de subir al mapa de respuesta los datos 
 * necesarios para que el serializador los transforme en xml.
 */
public class CuentaEjeEliteCommand extends CommandBase{
	
	Logger log=Logger.getLogger(CuentaEjeEliteCommand.class);
	
	private static final String CUENTAS_INVERSION="CuentasInversion.properties";
	private static final DecimalFormat df = new DecimalFormat("$###,###,###,###.00");
	private static final int NUMERO_MAXIMO_BENEFICIARIOS=4;
	private double montoMinimo;
	private String TIPO_MONEDA="MXP";
	private String BENEFICIARIOS="BENEFICIARIOS";
	
	
	public Response solicitud(Session session) throws Exception{
		Response response = new Response();
		Locale.setDefault(Locale.ENGLISH);
		DecimalFormat df = new DecimalFormat("$###,###,###,###.00");
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Collection<CuentaTO> cuentas=clienteTO.getCuentas();		
		Iterator<CuentaTO> iterador = cuentas.iterator();
		CuentaTO cuentaTemp=null;
		Cuenta_CargoTO cuentaTO=null;
		Collection<Cuenta_CargoTO> cuentasColeccion = new ArrayList<Cuenta_CargoTO>();
		PropertiesService propertiesService=PropertiesService.getInstance();
		Cuenta_eje_eliteTO cuenta_eje_elite= new Cuenta_eje_eliteTO();
		montoMinimo=Integer.parseInt(propertiesService.getPropertie(CUENTAS_INVERSION, "montominimo.cuenta.mercadodinero"));
		
		while(iterador.hasNext()){
			cuentaTemp = iterador.next();	
			cuentaTO= new Cuenta_CargoTO();
			String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaTemp.getNumero()));
			cuentaTO.setNumero_cuenta(cuentaFormateada);
			cuentaTO.setNumero_cuenta(cuentaTemp.getCuentaFormateada());
			cuentaTO.setProducto(cuentaTemp.getDescripcion());
			cuentaTO.setSaldo_disponible(df.format(cuentaTemp.getBalance()));
			cuentasColeccion.add(cuentaTO);
		}
		
		cuenta_eje_elite.setColeccion_cuentas(cuentasColeccion);
		cuenta_eje_elite.setMonto_minimo(df.format(montoMinimo));
		response.addAttribute(cuenta_eje_elite);
		return response;			
	}
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();
		Map<String,String> errors= new HashMap<String,String>();
		
		CuentaEjeEliteForm forma = (CuentaEjeEliteForm)getFormBean();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		String monto=forma.getMonto();
		String cuenta=forma.getCuenta_origen();
		
		double disponible;
		double inversion=Double.parseDouble(monto);
	
		CuentaTO cuentaTO = getAccountsPrdicate(clienteTO.getCuentas(), cuenta);
		cuentaTO.setDisponible(new Double("4000000"));
		if(cuentaTO!=null){
			disponible=cuentaTO.getDisponible();
			if(inversion>disponible){
				errors.put("monto", "Su saldo en esta cuenta es de "+disponible+", debes tener "+df.format(montoMinimo) +"pesos ó más");
				return returnErrorMap(response, errors);
			}
		}else{
			errors.put("cuenta", "El número de cuenta " + cuenta + " proporcionado no es valido.");
			return returnErrorMap(response, errors);
		}
		double porcentaje=0;
		List<BeneficiarioTO> beneficiarios= new ArrayList<BeneficiarioTO>();
		int count=0;
		
		for(BeneficiarioTO obj:forma.getColeccion_beneficiarios()){
			porcentaje=porcentaje+Double.parseDouble(obj.getPorcentaje());
			if(porcentaje>100){
				errors.put("beneficiarios", "El porcentaje de los beneficiarios rebasa el 100% verifique sus porcentajes");
				return returnErrorMap(response, errors);
			}
			if(beneficiarios.add(obj)&& count++>NUMERO_MAXIMO_BENEFICIARIOS){
				errors.put("beneficiarios", "El maximo número de beneficiarios es "+NUMERO_MAXIMO_BENEFICIARIOS);
				return returnErrorMap(response, errors);
			}
		}
		
		session.addAttribute(APERTURA_EJE_ELITE, forma);
		session.addAttribute(BENEFICIARIOS, beneficiarios);
		obtenLlavePublica(response, clienteTO);
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception, XmlDecodeException{
		Response response = new Response();
		Map<String, String> errors =new HashMap<String, String>();
		Apertura_cuenta_eje_eliteTO apertura_cuenta_eje_elite= new Apertura_cuenta_eje_eliteTO();
		
		ClienteTO cliente=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		ResourceFacadeSegundoSL delegate = getDelegateSegundo();
				
		CuentaEjeEliteForm forma=(CuentaEjeEliteForm)getFormBean();
		CuentaEjeEliteForm datos=(CuentaEjeEliteForm)session.getAttribute(APERTURA_EJE_ELITE);
		List<BeneficiarioTO> beneficiarios=(ArrayList<BeneficiarioTO>)session.getAttribute(BENEFICIARIOS);
		if(1==2)
		if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_TOKEN)){
			String tokenCode=forma.getClave_seguridad().string;
			TokenTO tokenTO=new TokenTO();
			tokenTO.setTokenCode(tokenCode);
			tokenTO.setUser(cliente.getUserName());
			
			if(!getDelegate().validaToken(tokenTO)){
				obtenLlavePublica(response,cliente);
				errors.put("clave_seguridad", "Lo sentimos su firma azteca no coincide, intente nuevamente");
				return returnErrorMap(response, errors);
			}
		}else if(forma.getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_HUELLA)){
			String huellaClave=forma.getHuella_seguridad();
			HuellaDigitalTO huellaDigitalTO=new HuellaDigitalTO();
			huellaDigitalTO.setHuella(huellaClave);
			huellaDigitalTO.setUser(cliente.getUserName());

			if(!getDelegate().validaHuella(huellaDigitalTO)){
				obtenLlavePublica(response,cliente);
				errors.put("huella_seguridad", "Lo sentimos su huella no coincide, intente nuevamente");
				return returnErrorMap(response, errors);
			}
		}
		AperturaCuentaEjeEliteEntradaTO aperturaCuentasEntradaTO=new AperturaCuentaEjeEliteEntradaTO();
		
		aperturaCuentasEntradaTO.setMoneda(TIPO_MONEDA);
		aperturaCuentasEntradaTO.setMonto(datos.getMonto());
		aperturaCuentasEntradaTO.setNumeroCliente(cliente.getNumero());
		com.bancoazteca.elite.beans.BeneficiarioTO beneficiario= null; 
		for(BeneficiarioTO temp:beneficiarios){
			beneficiario= new com.bancoazteca.elite.beans.BeneficiarioTO(); 
			beneficiario.setApMaterno(temp.getSegundo_apellido());
			beneficiario.setApPaterno(temp.getPrimer_apellido());
			beneficiario.setNomBeneficiario(temp.getNombre_beneficiario());
			beneficiario.setPorcentaje(temp.getPorcentaje());
			beneficiario.setParentesco(temp.getParentesco());
			beneficiario.setNombreCompleto(temp.getPrimer_apellido().trim()+" "+temp.getSegundo_apellido().trim()+" "+ temp.getNombre_beneficiario().trim() );
			aperturaCuentasEntradaTO.addBeneficiario(beneficiario);
		}
		
		String cuentaCargo=datos.getCuenta_origen();
		CuentaTO cuentaTO = getAccountsPrdicate(cliente.getCuentas(), cuentaCargo);
		
		AperturaCuentaEjeEliteRequestTO aperturaCuentaEjeEliteRequestTO =new AperturaCuentaEjeEliteRequestTO();
		aperturaCuentaEjeEliteRequestTO.setCuentaCargo(cuentaTO.getNumero());
		aperturaCuentaEjeEliteRequestTO.setEntrada(aperturaCuentasEntradaTO);
		aperturaCuentaEjeEliteRequestTO.setIdAlnova(cliente.getNumero());
		aperturaCuentaEjeEliteRequestTO.setMontoAperturaCuenta(datos.getMonto());
		aperturaCuentaEjeEliteRequestTO.setOperation(InversionesRequestTO.OPERACION_APERTURA_CUENTA_EJE);
		aperturaCuentaEjeEliteRequestTO.setType(InversionesRequestTO.EMPRESA_BANCA_ELITE);
		aperturaCuentaEjeEliteRequestTO.setPortalSolicitante(forma.getPortal());
		if(errors.isEmpty()){
			AperturaCuentasResponseTO respuesta=(AperturaCuentasResponseTO)delegate.dispatchManager(aperturaCuentaEjeEliteRequestTO);
			DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);

			apertura_cuenta_eje_elite.setClabe(Formatter.formatSplittedCuentaClabe(respuesta.getClabe()));
			apertura_cuenta_eje_elite.setNombre_titular(cliente.getNombreCompleto());
			apertura_cuenta_eje_elite.setSaldo(respuesta.getSaldo());
			apertura_cuenta_eje_elite.setNumero_cuenta(formatearCuenta(Formatter.formatCuenta(respuesta.getCuentaEje().replace(" ", "").trim())));
			apertura_cuenta_eje_elite.setFolio_operacion(respuesta.getFolioOperacion());
			apertura_cuenta_eje_elite.setFecha_operacion(dateFormat.format(new Date()));
			apertura_cuenta_eje_elite.setSaldo(respuesta.getSaldo());
			response.addAttribute(apertura_cuenta_eje_elite);
		}
		return response;
	}
	
}
