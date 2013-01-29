package com.bancoazteca.eservice.command.dispositivos.firma.activacion;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ActivacionFirmaRequestTO;
import com.bancoazteca.elite.beans.ActivacionFirmaResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Activacion_Firma_AztecaTO;
import com.bancoazteca.eservice.command.base.beans.Compania_celularTO;
import com.bancoazteca.eservice.command.base.beans.Companias_celularesTO;
import com.bancoazteca.eservice.command.base.beans.Confirmacion_Firma_AztecaTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.TokenTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.elite.ejb.dispositivos.util.DispositivosTipoValidacionEnum;

public class ActivacionFirmaCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(ActivacionFirmaCommand.class);

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL facadeSL = getDelegate();
		
		ActivacionFirmaRequestTO activacionFirmaRequestTO = new ActivacionFirmaRequestTO();
		activacionFirmaRequestTO.setUser(clienteTO.getUserName());
		
		try {
			facadeSL.solicitudActivacionFirma(activacionFirmaRequestTO);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL facadeSL = getDelegate();

		ActivacionFirmaForm forma = (ActivacionFirmaForm)getFormBean();
		ActivacionFirmaRequestTO activacionFirmaRequestTO = new ActivacionFirmaRequestTO();
		activacionFirmaRequestTO.setUser(clienteTO.getUserName());
		
		String numeroSerie = (String)session.getAttribute(CONFIRMACION_NUMERO_SERIE);
		if (Validator.isEmptyData(numeroSerie)) {
			numeroSerie = forma.getNumero_serie();
		}
		
		DispositivosTipoValidacionEnum tipoValidacion = DispositivosTipoValidacionEnum.valueOf( forma.getTipo_validacion().toUpperCase() );

		activacionFirmaRequestTO.setNumeroSerie(numeroSerie);
		activacionFirmaRequestTO.setMetodo(tipoValidacion);
		
		ArrayList<Compania_celularTO> coleccion_companias = new ArrayList<Compania_celularTO>();
		switch( tipoValidacion ){
			case TOKEN1 : activacionFirmaRequestTO.setPreToken( forma.getClave_seguridad().toString() );
						  break;
						  
			case TOKEN2 : activacionFirmaRequestTO.setActToken( forma.getClave_seguridad().toString() );
							for (int i=0; i<=5; i++) {
								Compania_celularTO compania = new Compania_celularTO();
								switch (i) {
									case 0: compania.setCompania("SIN COMPANIA");
											break;
									case 1: {	compania.setCompania("UNEFON");
												break;							}
									case 2: {	compania.setCompania("MOVISTAR");
												break;							}
									case 3: {	compania.setCompania("NEXTEL");
												break;							}
									case 4: {	compania.setCompania("TELCEL");
												break;							}
									case 5: {	compania.setCompania("IUSACELL");
												break;							}	
								}
								coleccion_companias.add(compania);
							}
						  break;
		}
				
		try {
			ActivacionFirmaResponseTO activacionFirmaResponseTO = facadeSL.validacionActivacionFirma(activacionFirmaRequestTO);			

			if (forma.getTipo_validacion().equalsIgnoreCase(DispositivosTipoValidacionEnum.TOKEN2.toString())) {
				Companias_celularesTO companias = new Companias_celularesTO();
				companias.setColeccion_companias(coleccion_companias);
				
				Activacion_Firma_AztecaTO activacionFirmaTO = new Activacion_Firma_AztecaTO();
				
				activacionFirmaTO.setCorreo_electronico(activacionFirmaResponseTO.getEmail());
				activacionFirmaTO.setNumero_telefono(activacionFirmaResponseTO.getTelefono());
				activacionFirmaTO.setNumero_celular(activacionFirmaResponseTO.getTelefonoCel());
				int index = Integer.valueOf(activacionFirmaResponseTO.getCarrier()) - 1;
				activacionFirmaTO.setCompania_celular(coleccion_companias.get(index).getCompania());
				activacionFirmaTO.setValidar_nip(activacionFirmaResponseTO.getValidarNip());
				
				response.addAttribute(activacionFirmaTO);
				response.addAttribute(companias);
				session.addAttribute(LISTA_COMPANIAS, coleccion_companias);

				if (activacionFirmaResponseTO.getCuentas()!= null) {
					Cuenta_CargoTO cuentaCargo;
					CuentaTO cuentaTO;
					Cuentas_CargoTO cuentas = new Cuentas_CargoTO();
					cuentas.setColeccion_cuentas( new ArrayList<Cuenta_CargoTO>() );
					log.info("cuentas: size"+activacionFirmaResponseTO.getCuentas().size() );
					Collection<CuentaLoTO> coleccionCuentas = activacionFirmaResponseTO.getCuentas();
					for( CuentaLoTO cuenta : coleccionCuentas ){
						cuentaTO = super.getAccountsPrdicate( clienteTO.getCuentas(), Formatter.formatCuenta(cuenta.getNumero()) );
						cuentaCargo = new Cuenta_CargoTO();
						cuentaCargo.setNumero_cuenta( cuentaTO.getCuentaFormateada().replace(" ", "") );
						cuentaCargo.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
						cuentaCargo.setProducto(cuentaTO.getDescripcion());
						cuentas.getColeccion_cuentas().add( cuentaCargo );
					}
					session.addAttribute(LISTA_CUENTAS, coleccionCuentas);
					response.addAttribute( cuentas );
				}
				if (activacionFirmaResponseTO.getDispositivoHuellaTO() != null) {
					DispositivoHuellaTO dispositivoHuellaTO = activacionFirmaResponseTO.getDispositivoHuellaTO();
					HuellaTO huellaTO = new HuellaTO();
					huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
					huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
					
					response.addAttribute(huellaTO);
				}
			}
			else {
				Confirmacion_Firma_AztecaTO activacionFirmaTO = new Confirmacion_Firma_AztecaTO();
				activacionFirmaTO.setNumero_serie(activacionFirmaResponseTO.getNumeroSerie());
				activacionFirmaTO.setClave_seguridad(activacionFirmaResponseTO.getPreToken());

				response.addAttribute(activacionFirmaTO);
			}
			session.addAttribute(CONFIRMACION_NUMERO_SERIE, numeroSerie);

		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ArrayList<Compania_celularTO> coleccion_companias = (ArrayList<Compania_celularTO>) session.getAttribute(LISTA_COMPANIAS);
		ResourceFacadeSL facadeSL = getDelegate();
		
		ActivacionFirmaForm forma = (ActivacionFirmaForm)getFormBean();
		ActivacionFirmaRequestTO activacionFirmaRequestTO = new ActivacionFirmaRequestTO();
		activacionFirmaRequestTO.setUser(clienteTO.getUserName());
		activacionFirmaRequestTO.setEmail(forma.getCorreo_electronico());
		activacionFirmaRequestTO.setTelefonoParticular(forma.getNumero_telefono());
		activacionFirmaRequestTO.setTelefonoCelular(forma.getNumero_celular());
		
		int index = getIndexByCompania(coleccion_companias, forma.getCompania_celular() + 1);		
		activacionFirmaRequestTO.setCompaniaCelular("" + index);
		activacionFirmaRequestTO.setAceptoContrato(forma.getContrato_aceptado());
		
		if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_NIP)) {
			Collection<CuentaLoTO> coleccionCuentas = (Collection<CuentaLoTO>)session.getAttribute(LISTA_CUENTAS);
			int cuentaCargo = getIndexByCuentaCargo(coleccionCuentas, forma.getCuenta_cargo(), clienteTO);
			if(cuentaCargo == -1) {
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("cuenta_cargo", "Seleccione una cuenta");
				throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
			activacionFirmaRequestTO.setOpcionActivacion(OPCION_NIP);
			activacionFirmaRequestTO.setCuentaCargo("" + cuentaCargo);
			activacionFirmaRequestTO.setNip(forma.getNip().toString());
		} else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
			activacionFirmaRequestTO.setOpcionActivacion(OPCION_HUELLA);
			activacionFirmaRequestTO.setClave(forma.getHuella_seguridad());
		}

		try {
			ActivacionFirmaResponseTO activacionFirmaResponseTO = facadeSL.ejecucionActivacionFirma(activacionFirmaRequestTO);
			
			TokenTO tokenTO = new TokenTO();
			tokenTO.setFecha_activacion(activacionFirmaResponseTO.getFechaAct());
			tokenTO.setNumero_serie(activacionFirmaResponseTO.getNumeroSerie());
			
			response.addAttribute(tokenTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	private int getIndexByCompania(ArrayList<Compania_celularTO> coleccion_companias,
			String companiaBuscar) {
		int index = 0;
		int indexTmp = -1;
		for (Compania_celularTO compania: coleccion_companias) {
			if (compania.getCompania().equalsIgnoreCase(companiaBuscar)) {
				indexTmp = index;
				break;
			}
			index++;
		}
		return indexTmp;
	}
}
