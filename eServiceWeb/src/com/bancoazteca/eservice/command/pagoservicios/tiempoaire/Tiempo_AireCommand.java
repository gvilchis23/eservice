package com.bancoazteca.eservice.command.pagoservicios.tiempoaire;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Tiempo_Aire_CompaniaTO;
import com.bancoazteca.eservice.command.base.beans.Tiempo_Aire_ejecutarTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class Tiempo_AireCommand extends CommandBase{

	private static final Logger log = Logger.getLogger(Tiempo_AireCommand.class);

	public Response solicitud(Session session) throws Exception{
		log.info("metodo solicitud tiempo aire");
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();
		try{
			PagoServiciosTiempoAireRequestTO tiempoAireRequestTO = new PagoServiciosTiempoAireRequestTO();
			PagoServiciosTiempoAireResponseTO tiempoAireResponseTO = new PagoServiciosTiempoAireResponseTO();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			tiempoAireRequestTO.setUser(clienteTO.getUserName());

			bean.setMenuTiempoAirePayment(tiempoAireRequestTO);		
			Collection<Tiempo_Aire_CompaniaTO> montos = new ArrayList<Tiempo_Aire_CompaniaTO>();
			Collection<String> carriers = new ArrayList<String>();
			carriers.add("telcel");
			carriers.add("iusacell");
			carriers.add("movistar");
			carriers.add("unefon");

			for(String carrier : carriers){
				tiempoAireRequestTO.setCarrier(carrier);
				tiempoAireResponseTO = bean.setInitialTiempoAirePayment(tiempoAireRequestTO);
				Collection<String> montosResponseTO = tiempoAireResponseTO.getMontos();
				String montosLigados = "";

				for(String monto : montosResponseTO){
					montosLigados += monto + ",";
				}
				montosLigados = montosLigados.substring(0, montosLigados.length()-1);
				Tiempo_Aire_CompaniaTO aire_CompaniaTO = new Tiempo_Aire_CompaniaTO();
				aire_CompaniaTO.setCompania(carrier);
				aire_CompaniaTO.setMontos(montosLigados);
				montos.add(aire_CompaniaTO);
			}

			Collection<Cuenta_CargoTO> cuentaCollectionTO = new ArrayList<Cuenta_CargoTO>();
			Map<String, String> cuentas = tiempoAireResponseTO.getMapCuentas();

			for (String key : cuentas.keySet()) {
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
				key = Formatter.removeSpaces(key);
				CuentaTO cuentaTO = super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0, 14));
				if(cuentaTO!=null){				
					cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
					cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			Pago_ServicioTO pagoServicioTO = new Pago_ServicioTO();
			pagoServicioTO.setColeccion_cuentas(cuentaCollectionTO);
			response.addAttribute(pagoServicioTO);
			response.addAttribute(montos);
			session.addAttribute(TIEMPO_AIRE_TO, tiempoAireResponseTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}


	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		Tiempo_AireForm forma = (Tiempo_AireForm) getFormBean();
		log.info("metodo validacion tiempo aire");
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		PagoServiciosTiempoAireResponseTO tiempoAireResponseTO = (PagoServiciosTiempoAireResponseTO)session.getAttribute(TIEMPO_AIRE_TO);
		Map<String, String> mapCuentas = tiempoAireResponseTO.getMapCuentas();
		String index = null;
		for(String key : mapCuentas.keySet()){
			String valor = mapCuentas.get(key);
			String cuentaMapa = key.substring(0, 17);
			cuentaMapa = Formatter.removeSpaces(cuentaMapa);
			if(cuentaMapa.equals(forma.getCuenta_cargo())){
				index = valor;
				break;
			}
		}
		try {
			
			float monto=Float.parseFloat(forma.getMonto());
			DecimalFormat montoFormat=new DecimalFormat("##");
			
			
			ResourceFacadeSL bean = getDelegate();
			String numeroCuenta = forma.getNumeroCuenta();
			PagoServiciosTiempoAireRequestTO pagoServiciosTiempoAireRequestTO = new PagoServiciosTiempoAireRequestTO();
			pagoServiciosTiempoAireRequestTO.setUser(clienteTO.getUserName());
			pagoServiciosTiempoAireRequestTO.setIdCuenta(index);
			pagoServiciosTiempoAireRequestTO.setCarrier(forma.getCarrier().toLowerCase());
			pagoServiciosTiempoAireRequestTO.setTelefono(forma.getNumero_telefonico());
			pagoServiciosTiempoAireRequestTO.setMonto(montoFormat.format(monto));
			tiempoAireResponseTO = bean.setDataTiempoAirePayment(pagoServiciosTiempoAireRequestTO);
			tiempoAireResponseTO.setMapCuentas(mapCuentas);
			tiempoAireResponseTO.setCuentaCargo(index);
			DispositivoHuellaTO dispositivoHuellaTO = tiempoAireResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			session.addAttribute(TIEMPO_AIRE_TO, tiempoAireResponseTO);
			
			
			Tiempo_Aire_ejecutarTO aire_ejecutarTO = new Tiempo_Aire_ejecutarTO();

			aire_ejecutarTO.setCarrier(tiempoAireResponseTO.getCarrier());
			aire_ejecutarTO.setCuenta_cargo(forma.getCuenta_cargo());
			aire_ejecutarTO.setNumero_telefonico(tiempoAireResponseTO.getTelefono());
			aire_ejecutarTO.setMonto(tiempoAireResponseTO.getMonto());
			aire_ejecutarTO.setIva("0.00");
			aire_ejecutarTO.setComision("0.00");
			aire_ejecutarTO.setFolio_aclaracion(tiempoAireResponseTO.getFolio());
			
			response.addAttribute(aire_ejecutarTO);
			response.addAttribute(huellaTO);

		} catch (EliteDataException e) {
			super.buildErrorResponse(e, response);

		}
		return response;
	}

	public Response ejecutar(Session session) throws Exception {
		Response response = new Response();
		Tiempo_AireForm tiempo_AireForm = (Tiempo_AireForm) getFormBean();
		log.info("metodo ejecucion tiempo aire");
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);

		PagoServiciosTiempoAireRequestTO tiempoAireRequestTO = new PagoServiciosTiempoAireRequestTO();
		PagoServiciosTiempoAireResponseTO tiempoAireResponseTO = (PagoServiciosTiempoAireResponseTO)session.getAttribute(TIEMPO_AIRE_TO);

		tiempoAireRequestTO.setUser(clienteTO.getUserName());
		tiempoAireRequestTO.setOptionDispositive(tiempo_AireForm.getOpcion_seguridad());
		tiempoAireRequestTO.setCarrier(tiempoAireResponseTO.getCarrier());
		tiempoAireRequestTO.setIdCuenta(tiempoAireResponseTO.getCuentaCargo());
		tiempoAireRequestTO.setMonto(tiempoAireResponseTO.getMonto());
		tiempoAireRequestTO.setTelefono(tiempoAireResponseTO.getTelefono());
		
		if (tiempo_AireForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
			tiempoAireRequestTO.setClave(tiempo_AireForm.getHuella_seguridad());
			tiempoAireRequestTO.setOptionDispositive(OPCION_HUELLA);
		}
		else if (tiempo_AireForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
			tiempoAireRequestTO.setTokencode(tiempo_AireForm.getClave_seguridad().toString());
			tiempoAireRequestTO.setOptionDispositive(OPCION_TOKEN);
		}

		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			tiempoAireResponseTO = resourceFacadeSL.setConfirmTiempoAirePayment(tiempoAireRequestTO);

			Tiempo_Aire_ejecutarTO aire_ejecutarTO = new Tiempo_Aire_ejecutarTO();

			aire_ejecutarTO.setCarrier(tiempoAireResponseTO.getCarrier());
			aire_ejecutarTO.setCuenta_cargo(tiempoAireResponseTO.getCuentaCargoFormateada());
			aire_ejecutarTO.setNumero_telefonico(tiempoAireResponseTO.getTelefono());
			aire_ejecutarTO.setMonto(tiempoAireResponseTO.getMonto());
			aire_ejecutarTO.setIva("0.00");
			aire_ejecutarTO.setComision("0.00");
			aire_ejecutarTO.setFolio_aclaracion(tiempoAireResponseTO.getFolio());

			super.updateBalance(session);
			response.addAttribute(aire_ejecutarTO);

		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

}
