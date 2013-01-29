package com.bancoazteca.eservice.command.cuentas.socionomina;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.AperturaCuentaSocioRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConfirmarAperturaCuentaSocioTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.command.base.beans.CuentaSocioTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Ejecucion_Cuenta_SocioTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;



/**
 * La clase CuentaSocioCommand es la implementación del comando de aperturas de cuenta 
 * socio nomina, la apertura se realiza invocando al los métodos del ResourceFacadeSL. <br/>
 * Así como también esta clase es la encargada de subir al mapa de respuesta los datos 
 * necesarios para que el serializador los transforme en xml.
 */
public class CuentaSocioCommand extends CommandBase {

	Logger logger=Logger.getLogger(CuentaSocioCommand.class);
	
	public Response solicitud(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException{
		Response response=new Response();
		
		ClienteTO clienteTO =(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		AperturaCuentaSocioRequestTO socioRequestTO = new AperturaCuentaSocioRequestTO();
		AperturaCuentaSocioResponseTO socioResponseTO = new AperturaCuentaSocioResponseTO();

		try{
			ResourceFacadeSL bean = getDelegate();
			
			socioRequestTO.setUser(clienteTO.getUserName());
			socioResponseTO = bean.getCuentaSocioInvocacion(socioRequestTO);
			
			String tasaBruta = socioResponseTO.getAperturaCuentaSocioTO().getTasaBrutaAnual();

			String montoMinimo=socioResponseTO.getMontoMinimo();
			
			Collection<CuentaLoTO>cuentas = socioResponseTO.getAperturaCuentaSocioTO().getCuentasLO();
			Cuenta_CargoTO cuenta_cargoTO=null;
			
			
			
			Collection<Cuenta_CargoTO> collectionCuentasCargoTO=new ArrayList<Cuenta_CargoTO>();
			
			for(CuentaLoTO cuenta:cuentas)
			{
				CuentaTO cuentaClieteTO = super.getAccountsPrdicate(clienteTO.getCuentas(),Formatter.removeSpaces(cuenta.getCuentaFormateada()));
				if(cuentaClieteTO!=null){
					cuenta_cargoTO=new Cuenta_CargoTO();
					cuenta_cargoTO.setNumero_cuenta(Formatter.removeSpaces(cuenta.getCuentaFormateada()));
					cuenta_cargoTO.setProducto(cuentaClieteTO.getDescripcion());
					cuenta_cargoTO.setSaldo_disponible(cuentaClieteTO.getDisponible().toString());						
					collectionCuentasCargoTO.add(cuenta_cargoTO);
				}
			}
			
			CuentaSocioTO cuentaSocioTO=new CuentaSocioTO();
			
			cuentaSocioTO.setCollection_cuentas(collectionCuentasCargoTO);
			cuentaSocioTO.setMonto_minimo(montoMinimo);
			cuentaSocioTO.setTasa_bruta(tasaBruta);
			response.addAttribute(cuentaSocioTO);
			
			session.addAttribute(CommandConstantes.CUENTA_SOCIO_RESPONSE, socioResponseTO);
			
		}catch (EliteDataException e) {
			
			e.printStackTrace();
			super.buildErrorResponse(e, response);			
		}
		return response;
	}
	
	public Response validacion(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException{

		ClienteTO clienteTO =(ClienteTO)session.getAttribute(CLIENTE_TO);
		AperturaCuentaSocioResponseTO socioResponseTO=(AperturaCuentaSocioResponseTO)session.getAttribute(CommandConstantes.CUENTA_SOCIO_RESPONSE);
		
		CuentaSocioForm forma = (CuentaSocioForm)getFormBean();
		
		AperturaCuentaSocioRequestTO socioRequestTO = new AperturaCuentaSocioRequestTO();
		
		 Iterator<CuentaLoTO>  cuentas = socioResponseTO.getAperturaCuentaSocioTO().getCuentasLO().iterator();
		 Response response=new Response();
		String indice="-1";
		String cuenta;
		CuentaLoTO cuentaLoTO=null;
		String cuenta_origen=forma.getCuenta_origen();
		while(cuentas.hasNext()){
			cuentaLoTO=cuentas.next();
			cuenta=Formatter.removeSpaces(cuentaLoTO.getCuentaFormateada());
			logger.info("cuenta forma "+cuenta_origen);
			logger.info("cuenta to "+cuenta);
			if(cuenta.equals(cuenta_origen)){
				indice=cuentaLoTO.getIndex();
				break;
			}
		}
		if(indice.equals("-1")){
			response.setStatus(-1, "La cuenta proporcionada no existe para el cliente "+clienteTO.getUserName(), null);
			return response;
		}
		
		try{
			ResourceFacadeSL bean = getDelegate();
			socioRequestTO.setUser(clienteTO.getUserName());
			socioRequestTO.setCuentaOrigen(indice);
			socioRequestTO.setMonto(forma.getMonto());
			socioRequestTO.setTasaBrutaAnual(socioResponseTO.getAperturaCuentaSocioTO().getTasaBrutaAnual());
			socioRequestTO.setReferencia(socioResponseTO.getAperturaCuentaSocioTO().getReferencia());
			
			Iterator<BeneficiarioTO> beneficiarios = forma.getColeccion_beneficiarios().iterator();
			BeneficiarioTO beneficiario;
			
			if(beneficiarios.hasNext()){
				beneficiario = beneficiarios.next();
				socioRequestTO.setNombreBeneficiario1(beneficiario.getNombre_beneficiario());
				socioRequestTO.setApBeneficiario1(beneficiario.getPrimer_apellido());
				socioRequestTO.setAmBeneficiario1(beneficiario.getSegundo_apellido());
				socioRequestTO.setPorcentaje1(beneficiario.getPorcentaje());
			}
			if(beneficiarios.hasNext()){
				beneficiario = beneficiarios.next();
				socioRequestTO.setNombreBeneficiario2(beneficiario.getNombre_beneficiario());
				socioRequestTO.setApBeneficiario2(beneficiario.getPrimer_apellido());
				socioRequestTO.setAmBeneficiario2(beneficiario.getSegundo_apellido());
				socioRequestTO.setPorcentaje2(beneficiario.getPorcentaje());
			}
			if(beneficiarios.hasNext()){
				beneficiario = beneficiarios.next();
				socioRequestTO.setNombreBeneficiario3(beneficiario.getNombre_beneficiario());
				socioRequestTO.setApBeneficiario3(beneficiario.getPrimer_apellido());
				socioRequestTO.setAmBeneficiario3(beneficiario.getSegundo_apellido());
				socioRequestTO.setPorcentaje3(beneficiario.getPorcentaje());
			}
			if(beneficiarios.hasNext()){
				beneficiario = beneficiarios.next();
				socioRequestTO.setNombreBeneficiario4(beneficiario.getNombre_beneficiario());
				socioRequestTO.setApBeneficiario4(beneficiario.getPrimer_apellido());
				socioRequestTO.setAmBeneficiario4(beneficiario.getSegundo_apellido());
				socioRequestTO.setPorcentaje4(beneficiario.getPorcentaje());
			}
			
			socioRequestTO.setNip(forma.getNip());
			socioRequestTO.setNipc(forma.getNip_confirmar());
			
			socioResponseTO = bean.getCuentaSocioEnvioDatos(socioRequestTO);
			
			DispositivoHuellaTO dispositivoHuella=socioResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO=new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuella.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuella.getLongitudHuella());
			
			response.addAttribute(huellaTO);
				
		}catch (EliteDataException e) {
			logger.info("Error al invocar la Apertura de la Cuenta Socio por ServiceLocatorException");
			e.printStackTrace();
			super.buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException, LoginException, UsuarioException{
		                         
		CuentaSocioForm forma = (CuentaSocioForm)getFormBean();
		ClienteTO clienteTO =(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Response response=new Response();
		
		AperturaCuentaSocioRequestTO socioRequestTO = new AperturaCuentaSocioRequestTO();
		
		
		String opcionSeguridad=forma.getOpcion_seguridad();
		
		AperturaCuentaSocioResponseTO cuentaSocioResponse=null;
		try{
			ResourceFacadeSL bean = getDelegate();
			socioRequestTO.setUser(clienteTO.getUserName());
			
			if(opcionSeguridad.equalsIgnoreCase(TAG_HUELLA)){
				socioRequestTO.setOptionDispositive(OPCION_HUELLA);
				socioRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else{
				socioRequestTO.setOptionDispositive(OPCION_TOKEN);
				socioRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}

//			cuentaSocioResponse=bean.getCuentaSocioConfirmacion(socioRequestTO);
			cuentaSocioResponse = new AperturaCuentaSocioResponseTO();
			
			ConfirmarAperturaCuentaSocioTO aperturaCuentaSocioTO=new ConfirmarAperturaCuentaSocioTO();
			
			aperturaCuentaSocioTO.setBeneficiario1("juan reyes olvera");
			aperturaCuentaSocioTO.setPorcentaje1("20");
			aperturaCuentaSocioTO.setBeneficiario2("juan reyes olvera");
			aperturaCuentaSocioTO.setPorcentaje2("20");
			aperturaCuentaSocioTO.setBeneficiario3("juan reyes olvera");
			aperturaCuentaSocioTO.setPorcentaje3("20");
			aperturaCuentaSocioTO.setBeneficiario4("juan reyes olvera");
			aperturaCuentaSocioTO.setPorcentaje4("40");
			
			aperturaCuentaSocioTO.setTitular("diana berenice C.C.");
			aperturaCuentaSocioTO.setTasaBrutaAnual("8.5");
			aperturaCuentaSocioTO.setCuentaCargo("99994488338822");
			aperturaCuentaSocioTO.setMonto(new BigDecimal(50));
			cuentaSocioResponse.setAperturaCuentaSocioTO(aperturaCuentaSocioTO);
			
			updateBalance(session);
			
			Ejecucion_Cuenta_SocioTO cuenta_SocioTO=new Ejecucion_Cuenta_SocioTO();
			
			cuenta_SocioTO.setMonto(cuentaSocioResponse.getAperturaCuentaSocioTO().getMonto().toString());
			cuenta_SocioTO.setTitular(cuentaSocioResponse.getAperturaCuentaSocioTO().getTitular());
			cuenta_SocioTO.setTasa_bruta(cuentaSocioResponse.getAperturaCuentaSocioTO().getTasaBrutaAnual());
			cuenta_SocioTO.setCuenta_cargo(cuentaSocioResponse.getAperturaCuentaSocioTO().getCuentaCargo());
			
			Collection<BeneficiarioTO>collection=new ArrayList<BeneficiarioTO>();
			
			BeneficiarioTO beneficiario=new BeneficiarioTO();
			beneficiario.setNombre_beneficiario(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario1());
			beneficiario.setPorcentaje(cuentaSocioResponse.getAperturaCuentaSocioTO().getPorcentaje1());
			collection.add(beneficiario);
			
			if(!Validator.isEmptyData(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario2())){
				beneficiario=new BeneficiarioTO();
				beneficiario.setNombre_beneficiario(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario2());
				beneficiario.setPorcentaje(cuentaSocioResponse.getAperturaCuentaSocioTO().getPorcentaje2());
				collection.add(beneficiario);
			}
			
			if(!Validator.isEmptyData(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario3())){
				beneficiario=new BeneficiarioTO();
				beneficiario.setNombre_beneficiario(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario3());
				beneficiario.setPorcentaje(cuentaSocioResponse.getAperturaCuentaSocioTO().getPorcentaje3());
				collection.add(beneficiario);
			}

			if(!Validator.isEmptyData(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario4())){
				beneficiario=new BeneficiarioTO();
				beneficiario.setNombre_beneficiario(cuentaSocioResponse.getAperturaCuentaSocioTO().getBeneficiario4());
				beneficiario.setPorcentaje(cuentaSocioResponse.getAperturaCuentaSocioTO().getPorcentaje4());
				collection.add(beneficiario);
			}
			
			cuenta_SocioTO.setColeccion_beneficiarios(collection);
			response.addAttribute(cuenta_SocioTO);
			
		}catch (EliteDataException e) {
			logger.info("Error al invocar la Apertura de la Cuenta Socio por ServiceLocatorException");
			e.printStackTrace();
			
			response.addAttribute(cuentaSocioResponse.getDispositivoHuellaTO());
			super.buildErrorResponse(e, response);
		}
		return response;		
	}
}