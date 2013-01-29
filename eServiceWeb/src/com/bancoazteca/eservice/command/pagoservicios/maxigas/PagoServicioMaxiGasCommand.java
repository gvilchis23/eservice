package com.bancoazteca.eservice.command.pagoservicios.maxigas;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJBException;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosMaxiGasRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMaxiGasResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Pago_Servicio_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.ServiciosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PagoServicioMaxiGasCommand extends CommandBase {	


	final private static String  digito_verificador="0";


	public Response solicitud(Session session) throws Exception {	

		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();


		Pago_ServicioTO pago_servicioTO=new Pago_ServicioTO();

		String cuenta;


		try{
			ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO = new PagoServiciosMaxiGasRequestTO();			
			pagoServiciosMaxiGasRequestTO.setUser(clienteTO.getUserName());			
			PagoServiciosMaxiGasResponseTO maxiGasResponseTO =  resourceFacadeSL.setInitialMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			Map<String, String> cuentas = maxiGasResponseTO.getMapCuentas();

			Collection<Cuenta_CargoTO> collectionCuentasCargoTO=new ArrayList<Cuenta_CargoTO>();
			Cuenta_CargoTO cuenta_cargoTO;


			for(String cuentaNum:cuentas.keySet())
			{
				cuenta = cuentaNum.substring(0,14);
				CuentaTO cuentaClieteTO = super.getAccountsPrdicate(clienteTO.getCuentas(), cuenta);
				if(cuentaClieteTO!=null){
					cuenta_cargoTO=new Cuenta_CargoTO();
					cuenta_cargoTO.setNumero_cuenta(cuenta);
					cuenta_cargoTO.setProducto(cuentaClieteTO.getDescripcion());
					cuenta_cargoTO.setSaldo_disponible(cuentaClieteTO.getDisponible().toString());						
					collectionCuentasCargoTO.add(cuenta_cargoTO);
				}
			}


			Set<String> setServicios=maxiGasResponseTO.getMapServicios().keySet();

			Collection<ServiciosTO> collectionServiciosTO=new ArrayList<ServiciosTO>();
			ServiciosTO serviciosTO;


			for(String servicio:setServicios){
				serviciosTO=new ServiciosTO();
				serviciosTO.setConcepto_pago(servicio);
				collectionServiciosTO.add(serviciosTO);
			}


			pago_servicioTO.setColeccion_cuentas(collectionCuentasCargoTO);

			pago_servicioTO.setColeccion_servicios(collectionServiciosTO);




			synchronized(session){
				session.addAttribute(PAGO_SERVICIO_MAXIGAS_RESPONSE, maxiGasResponseTO);
			}
			response.addAttribute(pago_servicioTO);

		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}


	public Response validacion(Session session) throws Exception {		

		PagoServiciosMaxiGasForm forma = (PagoServiciosMaxiGasForm) getFormBean();
		ResourceFacadeSL resourceFacadeSL = getDelegate();

		Response response=new Response();

		try{
			ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = (PagoServiciosMaxiGasResponseTO) session.getAttribute(PAGO_SERVICIO_MAXIGAS_RESPONSE);

			Map<String, String> serviciosMap = pagoServiciosMaxiGasResponseTO.getMapServicios();
			Map<String, String> cuentasMap = pagoServiciosMaxiGasResponseTO.getMapCuentas();


			Set<String> setCuentas=cuentasMap.keySet();
			String cuentaTemp="";
			String cuentaForma=forma.getCuenta_cargo();
			for(String cuentaConcat:setCuentas)
			{
				cuentaTemp=cuentaConcat.substring(0,14);
				if(cuentaTemp.equals(cuentaForma))
				{
					cuentaTemp=cuentaConcat;
					break;
				}
			}

			PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO = new PagoServiciosMaxiGasRequestTO();
			pagoServiciosMaxiGasRequestTO.setCuentaCargo(Formatter.removeSpaces(cuentaTemp));
			pagoServiciosMaxiGasRequestTO.setCuentaReferencia(Formatter.removeSpaces(forma.getNumero_referencia()));
			pagoServiciosMaxiGasRequestTO.setDigitoVerificador(digito_verificador);
			pagoServiciosMaxiGasRequestTO.setTipoServicio(forma.getConcepto_pago());
			pagoServiciosMaxiGasRequestTO.setImporte(forma.getImporte());
			pagoServiciosMaxiGasRequestTO.setUser(clienteTO.getUserName());

			pagoServiciosMaxiGasResponseTO = resourceFacadeSL.setDataMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			pagoServiciosMaxiGasResponseTO.setMapCuentas(cuentasMap);
			pagoServiciosMaxiGasResponseTO.setMapServicios(serviciosMap);
			pagoServiciosMaxiGasResponseTO.setConcepto_pago(forma.getConcepto_pago());

			synchronized(session){
				session.addAttribute(PAGO_SERVICIO_MAXIGAS_RESPONSE, pagoServiciosMaxiGasResponseTO);
			}

			DispositivoHuellaTO tdispositivoHuellaTO=pagoServiciosMaxiGasResponseTO.getDispositivoHuellaTO();

			HuellaTO huellaTO=new HuellaTO();

			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());

			response.addAttribute(huellaTO);

		}catch (EliteDataException e){				
			buildErrorResponse(e,response);
		}

		return response;
	}

	public Response ejecucion(Session session) throws ServiceLocatorException, EJBException, LoginException, SessionExpiredException, UsuarioException, PagoServiciosException{	

		PagoServiciosMaxiGasForm forma = (PagoServiciosMaxiGasForm) getFormBean();


		Response response=new Response();


		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);

		ResourceFacadeSL resourceFacadeSL = getDelegate();
		try{

			PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = (PagoServiciosMaxiGasResponseTO) session.getAttribute(PAGO_SERVICIO_MAXIGAS_RESPONSE);

			PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO = new PagoServiciosMaxiGasRequestTO();

			pagoServiciosMaxiGasRequestTO.setCuentaReferencia(forma.getNumero_referencia());
			pagoServiciosMaxiGasRequestTO.setCuentaCargo(Formatter.removeSpaces(forma.getCuenta_cargo()));
			pagoServiciosMaxiGasRequestTO.setDigitoVerificador(digito_verificador);
			pagoServiciosMaxiGasRequestTO.setTipoServicio(forma.getConcepto_pago());
			pagoServiciosMaxiGasRequestTO.setImporte(forma.getImporte());
			pagoServiciosMaxiGasRequestTO.setTotal(pagoServiciosMaxiGasResponseTO.getTotal().toString());
			pagoServiciosMaxiGasRequestTO.setFechaAplicacion(pagoServiciosMaxiGasResponseTO.getFechaAplicacion());
			pagoServiciosMaxiGasRequestTO.setUser(clienteTO.getUserName());

			String opc_seg=forma.getOpcion_seguridad().toUpperCase();

			if(opc_seg.equalsIgnoreCase(TAG_HUELLA)){
				pagoServiciosMaxiGasRequestTO.setOptionDispositive(OPCION_HUELLA);
				pagoServiciosMaxiGasRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				pagoServiciosMaxiGasRequestTO.setOptionDispositive(OPCION_TOKEN);
				pagoServiciosMaxiGasRequestTO.setTokenCode(forma.getClave_seguridad().toString());
			}




			pagoServiciosMaxiGasResponseTO = resourceFacadeSL.setConfimMaxiGasPayment(pagoServiciosMaxiGasRequestTO);

			Pago_Servicio_EjecucionTO pagoServicioEjecucionTO=new Pago_Servicio_EjecucionTO();

			pagoServicioEjecucionTO.setComision(pagoServiciosMaxiGasResponseTO.getComision().toString());
			pagoServicioEjecucionTO.setCuenta_cargo(pagoServiciosMaxiGasResponseTO.getCuentaCargo());
			pagoServicioEjecucionTO.setTotal_pago(pagoServiciosMaxiGasResponseTO.getTotal().toString());
			pagoServicioEjecucionTO.setIva(pagoServiciosMaxiGasResponseTO.getIva().toString());
			pagoServicioEjecucionTO.setImporte(pagoServiciosMaxiGasResponseTO.getImporte().toString());
			pagoServicioEjecucionTO.setNumero_referencia(pagoServiciosMaxiGasResponseTO.getCuentaReferencia());

			pagoServicioEjecucionTO.setFolio_aclaraciones(null);
			pagoServicioEjecucionTO.setConcepto_pago(pagoServiciosMaxiGasResponseTO.getConcepto_pago());


			updateBalance(session);


			response.addAttribute(pagoServicioEjecucionTO);

		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}

		return response;
	}
}