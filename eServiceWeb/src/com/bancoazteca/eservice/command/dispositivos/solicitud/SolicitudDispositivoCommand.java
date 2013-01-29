package com.bancoazteca.eservice.command.dispositivos.solicitud;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PedidoTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoRequestTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.TipoDispositivoSeguridadEnum;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Catalogos_DispositivosTO;
import com.bancoazteca.eservice.command.base.beans.Confirmacion_Entrega_DispositivosTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Direccion_Entrega_DispositivosTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Lugar_Entrega_DispositivosTO;
import com.bancoazteca.eservice.command.base.beans.Solicitud_DispositivoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class SolicitudDispositivoCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(SolicitudDispositivoCommand.class);

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL facadeSL = getDelegate();
			
			SolicitudDispositivoRequestTO solicitudDispositivoRequestTO = new SolicitudDispositivoRequestTO();
			solicitudDispositivoRequestTO.setUser( clienteTO.getUserName() );
			
			SolicitudDispositivoResponseTO solicitudDispositivoResponseTO = facadeSL.setInitialSolicitudDispositivo( solicitudDispositivoRequestTO );
			
			Solicitud_DispositivoTO solicitudDispositivoTO = new Solicitud_DispositivoTO();
			Cuenta_CargoTO cuentaCargo;
			CuentaTO cuentaTO;
			Cuentas_CargoTO cuentas = new Cuentas_CargoTO();
			cuentas.setColeccion_cuentas( new ArrayList<Cuenta_CargoTO>() );
			
			Collection<CuentaLoTO> coleccionCuentasSolicitudDispositivos = solicitudDispositivoResponseTO.getCuentas();
			for( CuentaLoTO cuenta : coleccionCuentasSolicitudDispositivos ){
				cuentaTO = super.getAccountsPrdicate( clienteTO.getCuentas(), Formatter.formatCuenta(cuenta.getNumero()) );
				cuentaCargo = new Cuenta_CargoTO();
				cuentaCargo.setNumero_cuenta( cuentaTO.getCuentaFormateada().replace(" ", "") );
				cuentaCargo.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
				cuentaCargo.setProducto(cuentaTO.getDescripcion());
				cuentas.getColeccion_cuentas().add( cuentaCargo );
			}

			solicitudDispositivoTO.setCosto_lector( solicitudDispositivoResponseTO.getCostoLector() );
			solicitudDispositivoTO.setCosto_token( solicitudDispositivoResponseTO.getCostoToken() );
			
			response.addAttribute(solicitudDispositivoTO);
			response.addAttribute( cuentas );
			
			session.addAttribute(SOLICITUD_DISPOSITIVO_CUENTAS,coleccionCuentasSolicitudDispositivos);
			boolean mostrarToken = "simon".equalsIgnoreCase( solicitudDispositivoResponseTO.getPresentarToken() );
			solicitudDispositivoTO.setMostrar_token( mostrarToken );
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response seleccionar_cuenta(Session session) throws Exception {
		Response response = new Response();
		try{
			SolicitudDispositivoForm forma=(SolicitudDispositivoForm)getFormBean();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			Collection <CuentaLoTO> coleccion_cuentas =(Collection <CuentaLoTO>)session.getAttribute(SOLICITUD_DISPOSITIVO_CUENTAS);
			SolicitudDispositivoRequestTO solicitudDispositivoRequestTO = new SolicitudDispositivoRequestTO();
			
			solicitudDispositivoRequestTO.setUser( clienteTO.getUserName() );
			
			if(forma.getTipo_dispositivo().equalsIgnoreCase(TipoDispositivoSeguridadEnum.TOKEN.toString())){
				solicitudDispositivoRequestTO.setTipoDispositivo("token");
			}else{
				solicitudDispositivoRequestTO.setTipoDispositivo("lector");
			}
				
			int cuentaCargo = getIndexByCuentaCargo(coleccion_cuentas, forma.getCuenta_cargo(), clienteTO);
			solicitudDispositivoRequestTO.setCuenta(""+cuentaCargo);
			
			ResourceFacadeSL facadeSL = getDelegate();
			SolicitudDispositivoResponseTO solicitudDispositivoResponseTO =	facadeSL.setCuentaSolicitudDispositivo( solicitudDispositivoRequestTO );
			session.addAttribute(SOLICITUD_DISPOSITIVO_DATOS_CUENTA, solicitudDispositivoRequestTO);
			
			Direccion_Entrega_DispositivosTO responseTO = new Direccion_Entrega_DispositivosTO();
			
			responseTO.setDireccion( solicitudDispositivoResponseTO.getDireccion() );
			responseTO.setEstado( solicitudDispositivoResponseTO.getEstado() );
			responseTO.setCiudad( solicitudDispositivoResponseTO.getCiudad() );
			responseTO.setColonia( solicitudDispositivoResponseTO.getColonia() );
			responseTO.setCodigo_postal( solicitudDispositivoResponseTO.getCodigoPostal() );
			responseTO.setTelefono( solicitudDispositivoResponseTO.getTelefono() );

			response.addAttribute( responseTO );
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		try{
			SolicitudDispositivoForm forma=(SolicitudDispositivoForm)getFormBean();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			int indexEstadoSeleccionado=-1;//TODO ERC verificar que no haya HC
			SolicitudDispositivoRequestTO solicitudDispositivoRequestTO = new SolicitudDispositivoRequestTO();
			SolicitudDispositivoRequestTO paso2 = (SolicitudDispositivoRequestTO)session.getAttribute(SOLICITUD_DISPOSITIVO_DATOS_CUENTA);
			solicitudDispositivoRequestTO.setUser( clienteTO.getUserName() );
			
			if(forma.getModificacion_direccion().equalsIgnoreCase("SI")){
				Catalogos_DispositivosTO catalogoTO =(Catalogos_DispositivosTO)session.getAttribute(LISTA_ESTADOS_DISPOSITIVO);
				ArrayList<String>coleccionEstados=(ArrayList<String>)catalogoTO.getColeccion_estados();
				if(coleccionEstados != null && coleccionEstados.size()>0){
					indexEstadoSeleccionado=coleccionEstados.indexOf(forma.getEstado_entrega_dispositivos());
				}
				solicitudDispositivoRequestTO.setModificarDireccion(true);
				solicitudDispositivoRequestTO.setDireccion(forma.getDireccion_entrega_dispositivos());
				solicitudDispositivoRequestTO.setEstado(String.valueOf(indexEstadoSeleccionado));
				solicitudDispositivoRequestTO.setCiudad(forma.getCiudad_entrega_dispositivos());
				solicitudDispositivoRequestTO.setColonia(forma.getColonia_entrega_dispositivos());
				solicitudDispositivoRequestTO.setCodigoPostal(forma.getCodigo_postal_entrega_dispositivos());
				solicitudDispositivoRequestTO.setTelefono(forma.getTelefono_entrega_dispositivos());
			}else{
				solicitudDispositivoRequestTO.setModificarDireccion(false);
			}
			
			//Personas autoirizadas para recibir los biometricos.
			solicitudDispositivoRequestTO.setNombre1(forma.getPrimer_nombre_persona_autorizada());
			solicitudDispositivoRequestTO.setApellidoPaterno1(forma.getPrimer_apellido_paterno_persona_autorizada());
			solicitudDispositivoRequestTO.setApellidoMaterno1(forma.getPrimer_apellido_materno_autorizada());
			
			solicitudDispositivoRequestTO.setNombre2(forma.getSegundo_nombre_persona_autorizada());
			solicitudDispositivoRequestTO.setApellidoPaterno2(forma.getSegundo_apellido_paterno_persona_autorizada());
			solicitudDispositivoRequestTO.setApellidoMaterno2(forma.getSegundo_apellido_materno_persona_autorizada());
			solicitudDispositivoRequestTO.setSubmith("CONTINUAR");
			
			ResourceFacadeSL facadeSL = getDelegate();
			SolicitudDispositivoResponseTO solicitudDispositivoResponseTO =	facadeSL.setDataSolicitudDispositivo( solicitudDispositivoRequestTO );
			
			Lugar_Entrega_DispositivosTO lugarTO = new Lugar_Entrega_DispositivosTO();
			PedidoTO pedidoTO = solicitudDispositivoResponseTO.getPedidoTO();
			lugarTO.setTitular( pedidoTO.getTitular() );
			lugarTO.setCuenta_cargo( pedidoTO.getCuentaCargo() );
			lugarTO.setComision( solicitudDispositivoResponseTO.getComision() );
			if(paso2.getTipoDispositivo().equalsIgnoreCase("TOKEN")){
				lugarTO.setComision( "0.00" );
				lugarTO.setIva_comision( "0.00" );
				lugarTO.setTotal( "0.00" );
//				lugarTO.setIva_comision( solicitudDispositivoResponseTO.getIvaComision()[0] );
				lugarTO.setDispositivo( pedidoTO.getDispositivo()[0] );
				lugarTO.setCantidad(  pedidoTO.getCantidad()[0]  );
			}else{
				lugarTO.setIva_comision( pedidoTO.getIVA()[1] );
				lugarTO.setDispositivo( pedidoTO.getDispositivo()[1] );
				lugarTO.setCantidad(  pedidoTO.getCantidad()[1]  );
				lugarTO.setTotal( pedidoTO.getTotal() );
			}
			
			lugarTO.setForma_envio( pedidoTO.getFormaEnvio() );
			lugarTO.setConcepto( pedidoTO.getConcepto() );
			lugarTO.setValidar_nip(solicitudDispositivoResponseTO.getValidarNIP());
		
			lugarTO.setCiudad( solicitudDispositivoResponseTO.getCiudad() );
			lugarTO.setCodigo_postal( solicitudDispositivoResponseTO.getCodigoPostal() );
			lugarTO.setColonia( solicitudDispositivoResponseTO.getColonia() );
			lugarTO.setDireccion( solicitudDispositivoResponseTO.getDireccion() );
			lugarTO.setEstado( solicitudDispositivoResponseTO.getEstado() );
			String nombre = solicitudDispositivoResponseTO.getApellidoPaterno1() + " " +
							solicitudDispositivoResponseTO.getApellidoMaterno1() + " " +
							solicitudDispositivoResponseTO.getNombre1();
			lugarTO.setNombre_primera_persona_autorizada( nombre.trim() );
			nombre = solicitudDispositivoResponseTO.getApellidoPaterno2() + " " +
					solicitudDispositivoResponseTO.getApellidoMaterno2() + " " +
					solicitudDispositivoResponseTO.getNombre2(); 
			lugarTO.setNombre_segunda_persona_autorizada( nombre.trim() );
			lugarTO.setTelefono( solicitudDispositivoResponseTO.getTelefono() );
			response.addAttribute( lugarTO );
			session.addAttribute( "Lugar_Entrega_DispositivosTO", lugarTO );
			
			DispositivoHuellaTO dispositivoHuellaTO = solicitudDispositivoResponseTO.getDispositivoHuellaTO();
			if (dispositivoHuellaTO!= null) {
				HuellaTO huellaTO = new HuellaTO();
				huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
				huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			
				response.addAttribute(huellaTO);
			}
			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			SolicitudDispositivoForm forma=(SolicitudDispositivoForm)getFormBean();
			SolicitudDispositivoRequestTO solicitudDispositivoRequestTO = new SolicitudDispositivoRequestTO();
			solicitudDispositivoRequestTO.setUser( clienteTO.getUserName() );
			
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_NIP)) {
				solicitudDispositivoRequestTO.setOpcionActivacion(OPCION_NIP);
				solicitudDispositivoRequestTO.setNip( forma.getNip().toString() );
			} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
				solicitudDispositivoRequestTO.setOpcionActivacion(OPCION_HUELLA);
				solicitudDispositivoRequestTO.setClave(forma.getHuella_seguridad());
			}
			
			ResourceFacadeSL facadeSL = getDelegate();
			SolicitudDispositivoResponseTO solicitudDispositivoResponseTO =	facadeSL.setConfirmSolicitudDispositivo(solicitudDispositivoRequestTO);
			
			Lugar_Entrega_DispositivosTO lugarTO = ( Lugar_Entrega_DispositivosTO ) session.getAttribute( "Lugar_Entrega_DispositivosTO" );
			Confirmacion_Entrega_DispositivosTO confirmaTO = new Confirmacion_Entrega_DispositivosTO();
			confirmaTO.setConcepto( solicitudDispositivoResponseTO.getPedidoTO().getConcepto() );
			confirmaTO.setFolio_solicitud( solicitudDispositivoResponseTO.getPedidoTO().getFolioBD() );

			confirmaTO.setLugar_entrega( lugarTO );
			response.addAttribute( confirmaTO );
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
}
