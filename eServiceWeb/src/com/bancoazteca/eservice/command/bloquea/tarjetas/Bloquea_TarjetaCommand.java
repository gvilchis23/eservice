package com.bancoazteca.eservice.command.bloquea.tarjetas;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.CancelacionElementTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.LockUnlockCardsRequestTO;
import com.bancoazteca.elite.beans.LockUnlockCardsResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Bloquea_cuentaTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.mediospago.Medios_PagoCommand;
import com.bancoazteca.eservice.command.response.Response;

public class Bloquea_TarjetaCommand extends CommandBase{

	private ResourceFacadeSL facade = null;
	
	private static Logger log = Logger.getLogger(Bloquea_TarjetaCommand.class);
	
	public Response solicitud(Session session) throws Exception{
		Response response = new Response();
		List<Detalle_TarjetaTO> colleccion_medios_pago = new ArrayList<Detalle_TarjetaTO>();
		ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
		 try{
			 Medios_PagoCommand medios_PagoCommand = new Medios_PagoCommand();
			 Medios_pagoTO medios_pagoTO = medios_PagoCommand.medios(clienteTO);
			 colleccion_medios_pago = (List<Detalle_TarjetaTO>) medios_pagoTO.getColeccion_medios_pago();
			 session.addAttribute(MEDIOS_PAGO_TARJETAS, medios_pagoTO);
			 
			 response.addAttribute(medios_pagoTO);
		 }catch (EliteDataException e) {
			 buildErrorResponse(e, response);
		 }
		 
		return response;
	}
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();
		ResourceFacadeSL facade = null;
		Bloquea_TarjetaForm forma=(Bloquea_TarjetaForm)getFormBean();
		boolean tarjetaValida=false;
		
		
		LockUnlockCardsRequestTO lockUnlockRequestTO = new LockUnlockCardsRequestTO(); 
		LockUnlockCardsResponseTO  lockUnlockResponseTO = new LockUnlockCardsResponseTO();
		
		HuellaTO huellaTO = new HuellaTO();
		
		Medios_pagoTO medios_pagoTO = new Medios_pagoTO();
		medios_pagoTO = (Medios_pagoTO) session.getAttribute(MEDIOS_PAGO_TARJETAS);
		
		ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
		 try{
				facade = getDelegate();	
				String numero_cuenta = Formatter.removeSpaces( forma.getNumero_cuenta() );
				String numero_tarjeta = null;
				String estatusTarjeta = null;
				int indexTarjeta = 0;
				Detalle_TarjetaTO detalle_tarjetaTO=new Detalle_TarjetaTO();
				
				for ( Detalle_TarjetaTO aux: medios_pagoTO.getColeccion_medios_pago() ){
					String aux_Cuenta=aux.getNumero_cuenta();
				   if ( Formatter.removeSpaces(aux_Cuenta).equalsIgnoreCase(numero_cuenta)){
					   detalle_tarjetaTO=aux;
					   estatusTarjeta = aux.getEstatus_tarjeta();
					   indexTarjeta =((ArrayList<Detalle_TarjetaTO>) medios_pagoTO.getColeccion_medios_pago()).indexOf(aux);
					   numero_tarjeta = aux.getNumero_tarjeta();
					   tarjetaValida=true;
				   }
				}
				
				if(!tarjetaValida){
					HashMap<String, String> errores=new HashMap<String, String>();
					errores.put("numero_cuenta", "Verifique el numero de la cuenta seleccionada.");
					return returnErrorMap(response, errores);
				}
				
				String operacion = "";
				 
				if(estatusTarjeta.equalsIgnoreCase("BLOQUEADA")){
					operacion = "desbloquear";
				}else{
					operacion = "bloquear";
				}
				
				lockUnlockRequestTO.setNombre(clienteTO.getNombreCompleto());
				lockUnlockRequestTO.setIndexCard(indexTarjeta);
				lockUnlockRequestTO.setOperacion(operacion);
				lockUnlockRequestTO.setUser(clienteTO.getAlias());
				lockUnlockRequestTO.setNumTarjeta(numero_tarjeta);
				lockUnlockRequestTO.setEstadoTarjeta(estatusTarjeta);
				
				lockUnlockResponseTO = facade.informacionBloquearDesbloquear(lockUnlockRequestTO);
				
				if(lockUnlockResponseTO.getDispositivoHuellaTO()!=null){
					huellaTO.setLlave_publica(lockUnlockResponseTO.getDispositivoHuellaTO().getLlavePublica());
					huellaTO.setLongitud_huella(lockUnlockResponseTO.getDispositivoHuellaTO().getLongitudHuella());
				}
				response.addAttribute(huellaTO);
				
				Calendar calendar=Calendar.getInstance();
				Locale local=Locale.getDefault();
				Date date=calendar.getTime();
				Format f=new SimpleDateFormat("dd-MMM-yyyy",local);
				String fecha=f.format(date);
				
				detalle_tarjetaTO.setFecha(fecha);
				
				response.addAttribute(detalle_tarjetaTO);
				
				session.addAttribute(LOCKUNLOCKRESPONSETO, lockUnlockResponseTO);
				
		 }catch (EliteDataException e) {
			 buildErrorResponse(e, response);
		 }
		 
		return response;
	}
	
	
	 public Response ejecutar(Session session) throws Exception {
		 Response response = new Response();
		 ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		 
		 try{
			 
		 LockUnlockCardsRequestTO lockUnlockRequestTO = new LockUnlockCardsRequestTO();
		 LockUnlockCardsResponseTO  lockUnlockResponseTO = new LockUnlockCardsResponseTO();
		 lockUnlockResponseTO = (LockUnlockCardsResponseTO) session.getAttribute(LOCKUNLOCKRESPONSETO);
		 
		 Bloquea_TarjetaForm form =(Bloquea_TarjetaForm) getFormBean();		 
		 
		 lockUnlockRequestTO.setNombre(lockUnlockResponseTO.getTarjeta().getNombre());
		 lockUnlockRequestTO.setEstadoTarjeta(lockUnlockResponseTO.getTarjeta().getEstado());
		 lockUnlockRequestTO.setClave(lockUnlockResponseTO.getTarjeta().getTokencode());
		 lockUnlockRequestTO.setOperacion(lockUnlockResponseTO.getTarjeta().getOpcion());
		 lockUnlockRequestTO.setUser(clienteTO.getAlias());
		 lockUnlockRequestTO.setNumTarjeta(lockUnlockResponseTO.getTarjeta().getTarjeta());
 
		 if(form.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				lockUnlockRequestTO.setOptionDispositive(String.valueOf(OPCION_HUELLA));
				lockUnlockRequestTO.setClave(form.getHuella_seguridad().toString());
		}else{
			lockUnlockRequestTO.setOptionDispositive(String.valueOf(OPCION_TOKEN));
			lockUnlockRequestTO.setTokenCode(form.getClave_seguridad().toString());
		}	 	
		 facade = getDelegate();
		 LockUnlockCardsResponseTO lockUnlockCardsResponseTO = facade.aceptarCardBlocking(lockUnlockRequestTO);
		 CancelacionElementTO aux_LockUnlockResponse=lockUnlockCardsResponseTO.getTarjeta();
		 
	 	Bloquea_cuentaTO bloquea_cuentaTO = new Bloquea_cuentaTO();
	 	bloquea_cuentaTO.setEstado_actual(aux_LockUnlockResponse.getEstado());
	 	bloquea_cuentaTO.setEstado_anterior(aux_LockUnlockResponse.getEstadoAnterior());
	 	bloquea_cuentaTO.setFecha_movimiento(aux_LockUnlockResponse.getFechaUltimoMovimiento());
	 	bloquea_cuentaTO.setFolio_operacion(aux_LockUnlockResponse.getFolio());
	 	bloquea_cuentaTO.setNombre_cliente(aux_LockUnlockResponse.getNombre());
	 	bloquea_cuentaTO.setNumero_tarjeta(aux_LockUnlockResponse.getTarjeta());

		 
		 response.addAttribute(bloquea_cuentaTO);
		 
		 }catch (EliteDataException e) {
			 buildErrorResponse(e, response);
		 }
		 
		 return response;
	 }
	 
	 
	 
	 
}
