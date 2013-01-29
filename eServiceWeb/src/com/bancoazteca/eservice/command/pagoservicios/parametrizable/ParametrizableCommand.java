package com.bancoazteca.eservice.command.pagoservicios.parametrizable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoServiciosParametrizableRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosParametrizableResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Pago_Servicio_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.ServiciosTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class ParametrizableCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(ParametrizableCommand.class);
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String tipo_operacion = "";
		
		if(session.getAttribute(REFERENCIA_FRECUENTE_SERVICIO)!=null){
			tipo_operacion = (String)session.getAttribute(REFERENCIA_FRECUENTE_SERVICIO);
		}else{
			tipo_operacion = getFormBean().getTipo_operacion().toLowerCase();
		}		
		
		PropertiesService propertiesService = PropertiesService.getInstance();
		
		String id_operacion = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO + tipo_operacion );	
		
		//Obtencion de la longitud de referencia
		String id_referencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA + tipo_operacion );	
		String [] referenciasLongitud = id_referencia.split(",");
		
		PagoServiciosParametrizableRequestTO pagoServiciosParametrizableRequestTO = new PagoServiciosParametrizableRequestTO();
		pagoServiciosParametrizableRequestTO.setUser(clienteTO.getUserName());
		pagoServiciosParametrizableRequestTO.setTipoServicio(id_operacion);
		

		try {
			ResourceFacadeSL facadeSL = getDelegate();
			PagoServiciosParametrizableResponseTO pagoServiciosParametrizableResponseTO = facadeSL.setInitialParametrizablePayment(pagoServiciosParametrizableRequestTO);
		
			Collection<Cuenta_CargoTO> cuentaCollectionTO = new ArrayList<Cuenta_CargoTO>();
			
			Map<String, String> cuentas = pagoServiciosParametrizableResponseTO.getMapCuentas();

			for (String key : cuentas.keySet()) {
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
				CuentaTO cuentaTO = super.getAccountsPrdicate(clienteTO.getCuentas(), key.substring(0, 14));
				if(cuentaTO != null){
					cuentaCargoTO.setNumero_cuenta(cuentaTO.getCuentaFormateada().replace(" ", ""));
					cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			
			Map<String, String> servicios = pagoServiciosParametrizableResponseTO.getMapServicios();
			Collection <ServiciosTO> collectionServiciosTO = new ArrayList<ServiciosTO>();
			for(String key : servicios.keySet()){
				ServiciosTO serviciosTO = new ServiciosTO();
				serviciosTO.setConcepto_pago(key);
				collectionServiciosTO.add(serviciosTO);
			}
			

			Pago_ServicioTO paramTO = new Pago_ServicioTO();
			
			StringBuilder cadena = new StringBuilder();
			for(int i=0; i<referenciasLongitud.length; i++){
				cadena.append(referenciasLongitud[i]);
				if(i+1 != referenciasLongitud.length){
					cadena.append(" o ");
				}
			}
			paramTO.setLongitud_referencia(cadena.toString());
			paramTO.setColeccion_cuentas(cuentaCollectionTO);
			paramTO.setColeccion_servicios( collectionServiciosTO );
			
			
			//Obtencion de la variable de referencia
			String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + tipo_operacion );
			if(tipoReferencia != null){
				paramTO.setServicio_referencia_frecuente("1");
			}else{
				paramTO.setServicio_referencia_frecuente("0");
			}
			
			session.addAttribute(PAGO_SERVICIOS_PARAMETRIZABLE_RESPONSE,pagoServiciosParametrizableResponseTO);
			session.addAttribute("id_servicio", id_operacion);
			response.addAttribute( paramTO );
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		ParametrizableForm forma = (ParametrizableForm)getFormBean();

		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		PagoServiciosParametrizableResponseTO responseTO =(PagoServiciosParametrizableResponseTO) session.getAttribute( PAGO_SERVICIOS_PARAMETRIZABLE_RESPONSE );

		PagoServiciosParametrizableRequestTO pagoServiciosParametrizableRequestTO = new PagoServiciosParametrizableRequestTO();		
		
		String tipo_operacion = getFormBean().getTipo_operacion().toLowerCase();
		pagoServiciosParametrizableRequestTO.setCuentaReferencia(forma.getNumero_referencia());
		if(tipo_operacion.equalsIgnoreCase("TELMEX")){
			pagoServiciosParametrizableRequestTO.setDigitoVerificador(forma.getDigito_verificador());
		}else{
			pagoServiciosParametrizableRequestTO.setDigitoVerificador("0");
		}
				
		pagoServiciosParametrizableRequestTO.setCuentaCargo(Formatter.removeSpaces( forma.getCuenta_cargo() ) );		
		pagoServiciosParametrizableRequestTO.setTipoServicio(forma.getConcepto_pago());
		pagoServiciosParametrizableRequestTO.setImporte(forma.getImporte());
		pagoServiciosParametrizableRequestTO.setUser(clienteTO.getUserName());	
		String id_servicio = (String)session.getAttribute("id_servicio");
		pagoServiciosParametrizableRequestTO.setId_servicio(id_servicio);
		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			responseTO = resourceFacadeSL.setDataParametrizablePayment(pagoServiciosParametrizableRequestTO);
			Pago_Servicio_EjecucionTO paramTO = generaRespuestaTO( responseTO );
			HuellaTO huellaTO = new HuellaTO();
			DispositivoHuellaTO dispositivoHuellaTO = responseTO.getDispositivoHuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}
			session.addAttribute( PAGO_SERVICIOS_PARAMETRIZABLE_RESPONSE, responseTO );

			response.addAttribute ( paramTO );
			response.addAttribute( huellaTO );

		} catch ( EliteDataException e ) {
			buildErrorResponse( e, response );
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ParametrizableForm forma = ( ParametrizableForm )getFormBean();
		ClienteTO clienteTO = ( ClienteTO ) session.getAttribute( CLIENTE_TO );
		PagoServiciosParametrizableResponseTO pagoServiciosParametrizableResponseTO =( PagoServiciosParametrizableResponseTO ) session.getAttribute( PAGO_SERVICIOS_PARAMETRIZABLE_RESPONSE );
		PagoServiciosParametrizableRequestTO pagoServiciosParametrizableRequestTO = new PagoServiciosParametrizableRequestTO();

		pagoServiciosParametrizableRequestTO.setCuentaReferencia( pagoServiciosParametrizableResponseTO.getCuentaReferencia() );
		pagoServiciosParametrizableRequestTO.setCuentaCargo(Formatter.removeSpaces( pagoServiciosParametrizableResponseTO.getCuentaCargo() ) );
		pagoServiciosParametrizableRequestTO.setTipoServicio( pagoServiciosParametrizableResponseTO.getTipoServicio() );
		pagoServiciosParametrizableRequestTO.setFechaAplicacion( pagoServiciosParametrizableResponseTO.getFechaAplicacion() );
		pagoServiciosParametrizableRequestTO.setComision( pagoServiciosParametrizableResponseTO.getComision().toString() );
		pagoServiciosParametrizableRequestTO.setIva( pagoServiciosParametrizableResponseTO.getIva().toString() );
		pagoServiciosParametrizableRequestTO.setImporte( pagoServiciosParametrizableResponseTO.getImporte().toString() );
		pagoServiciosParametrizableRequestTO.setTotal( pagoServiciosParametrizableResponseTO.getTotal().toString() );
		pagoServiciosParametrizableRequestTO.setUser( clienteTO.getUserName() );
		pagoServiciosParametrizableRequestTO.setDigitoVerificador( "0" );

		if( forma.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ) {
			pagoServiciosParametrizableRequestTO.setOptionDispositive( OPCION_TOKEN );
			pagoServiciosParametrizableRequestTO.setTokencode( forma.getClave_seguridad().toString() );
		} else if (forma.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ) {
			pagoServiciosParametrizableRequestTO.setOptionDispositive( OPCION_HUELLA );
			pagoServiciosParametrizableRequestTO.setClave( forma.getHuella_seguridad().toString() );
		}

		try {
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			pagoServiciosParametrizableResponseTO = resourceFacadeSL.setConfirmParametrizablePayment(pagoServiciosParametrizableRequestTO);
			Pago_Servicio_EjecucionTO paramTO = generaRespuestaTO( pagoServiciosParametrizableResponseTO );

			updateBalance( session );
			response.addAttribute( paramTO );
		} catch ( EliteDataException e ) {
			buildErrorResponse( e, response );
		}
		return response;
	}

	private Pago_Servicio_EjecucionTO generaRespuestaTO( PagoServiciosParametrizableResponseTO responseTO ){
		Pago_Servicio_EjecucionTO paramTO = new Pago_Servicio_EjecucionTO();
		
		paramTO.setCuenta_cargo( responseTO.getCuentaCargo().substring(0, 14) );
		paramTO.setNumero_referencia( responseTO.getCuentaReferencia() );
		paramTO.setImporte( String.valueOf(responseTO.getImporte()) );
		paramTO.setComision( String.valueOf(responseTO.getComision()) );
		paramTO.setIva( String.valueOf(responseTO.getIva()) );
		paramTO.setTotal_pago( String.valueOf(responseTO.getTotal()) );
		paramTO.setConcepto_pago( responseTO.getTipoServicio() );
		paramTO.setFolio_aclaraciones( responseTO.getNumeroOperacion() );
		paramTO.setFecha_aplicacion( responseTO.getFechaAplicacion() );
		return paramTO;
	}
}