package com.bancoazteca.eservice.command.transferencias.internacionales;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.TransferenciasInternacionalesRequestTO;
import com.bancoazteca.elite.beans.TransferenciasInternacionalesResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Envio_DineroTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.Transferencia_Internacional_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.Transferencias_Internacionales_TO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.pagoservicios.tiempoaire.Tiempo_AireCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class TransferenciaInternacionalCommand extends CommandBase {

	private static final Logger log = Logger
			.getLogger(Tiempo_AireCommand.class);

	public Response solicitud(Session session) throws Exception {
		log.info("metodo solicitud transferencia internacional");
		Response response = new Response();
		TransferenciasInternacionalesRequestTO internacionalesRequestTO = new TransferenciasInternacionalesRequestTO();
		TransferenciasInternacionalesResponseTO internacionalesResponseTO = new TransferenciasInternacionalesResponseTO();
		Transferencias_Internacionales_TO transferencias_Internacionales_TO = new Transferencias_Internacionales_TO();
		HashMap<String, String> errors = null;
		try {			
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			internacionalesRequestTO.setUser(clienteTO.getUserName());
			internacionalesResponseTO = bean.getTransferenciaInternacionalInvocacion(internacionalesRequestTO);
			
			if(internacionalesResponseTO != null){
				if(internacionalesResponseTO.getRestriccionHorario()!= null){
					//regresamos mensaje de error en caso de que transferencia internacional fuera de horario
					errors = new HashMap<String, String>();
					errors.put("restriccion_horario", internacionalesResponseTO.getRestriccionHorario());
					returnErrorMap(response, errors);
				}else{
					//tipo valor
					session.addAttribute("tipoCambio", internacionalesResponseTO.getValorDolar());
					
					//tipo valor formateado					
					transferencias_Internacionales_TO.setTipo_cambio(internacionalesResponseTO.getTipoCambioFormateado());
					
					//cuentas
					if(internacionalesResponseTO.getCuentas() != null){
						Collection<Cuenta_CargoTO> cuentasTransaccionesInter = new ArrayList<Cuenta_CargoTO>();
						Cuenta_CargoTO cuentaCargoTO = null;
						for(CuentaTransaccionesTO cuentaTransaccionTO : internacionalesResponseTO.getCuentas()){
							cuentaCargoTO = new Cuenta_CargoTO();
							String cuenta=Formatter.removeSpaces(cuentaTransaccionTO.getCuentaFormateada());
							CuentaTO cuentaTO = (CuentaTO)super.getAccountsPrdicate(clienteTO.getCuentas(),cuenta);// CollectionUtils.find(clienteTO.getCuentas(), predicate );
							if (cuentaTO!=null){
								cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));			
								cuentaCargoTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
								cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
								cuentasTransaccionesInter.add(cuentaCargoTO);
							}
						}
						transferencias_Internacionales_TO.setColeccion_cuentas(cuentasTransaccionesInter);
					}
					session.addAttribute(CommandConstantes.TRANSFERENCIA_INTERNACIONAL_RESPONSE, internacionalesResponseTO);
				}
			}
			response.addAttribute(transferencias_Internacionales_TO);
		} catch (EliteDataException e) {
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		return response;
	}

	public Response validacion(Session session) throws Exception {
		log.info("metodo validacion transferencia internacional");
		Response response = new Response();
		TransferenciasInternacionalesRequestTO internacionalesRequestTO = new TransferenciasInternacionalesRequestTO();
		TransferenciasInternacionalesResponseTO internacionalesResponseTO = new TransferenciasInternacionalesResponseTO();
		TransferenciasInternacionalesResponseTO internacionalesResponseCuentas = new TransferenciasInternacionalesResponseTO();		
		Transferencia_Internacional_EjecucionTO transferencia_Internacional_EjecucionTO = new Transferencia_Internacional_EjecucionTO();
		try {
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			TransferenciaInternacionalForm internacionalForm = (TransferenciaInternacionalForm) getFormBean();
			internacionalesResponseCuentas = (TransferenciasInternacionalesResponseTO) session.getAttribute(TRANSFERENCIA_INTERNACIONAL_RESPONSE);
			
			internacionalesRequestTO.setUser(clienteTO.getUserName());
			internacionalesRequestTO.setDestino(internacionalForm.getCuenta_destino());
			internacionalesRequestTO.setClaveBanco(internacionalForm.getClave_banco());
			internacionalesRequestTO.setBeneficiario(internacionalForm.getNombre_beneficiario());
			internacionalesRequestTO.setApellidoPaterno(internacionalForm.getApellido_paterno_beneficiario());
			internacionalesRequestTO.setApellidoMaterno(internacionalForm.getApellido_materno_beneficiario()==null ? "":internacionalForm.getApellido_materno_beneficiario());
			internacionalesRequestTO.setNacionalidad(internacionalForm.getNacionalidad_beneficiario());
			internacionalesRequestTO.setDomicilio(internacionalForm.getDomicilio_beneficiario()==null ? "":internacionalForm.getDomicilio_beneficiario());
			internacionalesRequestTO.setDia(internacionalForm.getDia_nacimiento_beneficiario()==null ? "":internacionalForm.getDia_nacimiento_beneficiario());
			internacionalesRequestTO.setMes(internacionalForm.getMes_nacimiento_beneficiario()==null ? "":internacionalForm.getMes_nacimiento_beneficiario());
			internacionalesRequestTO.setAnio(internacionalForm.getAnio_nacimiento_beneficiario()==null ? "":internacionalForm.getAnio_nacimiento_beneficiario());
			internacionalesRequestTO.setLugarNacimiento(internacionalForm.getLugar_nacimiento_beneficiario()==null ? "":internacionalForm.getLugar_nacimiento_beneficiario());
			internacionalesRequestTO.setReferencia(internacionalForm.getConcepto_transferencia());
			internacionalesRequestTO.setImporteDlls(internacionalForm.getImporte_dolares());
			
			String cuentaCargo=internacionalForm.getCuenta_cargo();
			if (cuentaCargo.length()==14){
				String cuentaFormateada = Formatter.split4CharsTokens(cuentaCargo);
				CuentaPredicate predicate1 = new CuentaPredicate();
				predicate1.setCuenta(cuentaFormateada);
				CuentaTransaccionesTO cuentaTransaccionesTO = (CuentaTransaccionesTO) CollectionUtils.find(internacionalesResponseCuentas.getCuentas(), predicate1 );
				internacionalesRequestTO.setOrigen(cuentaTransaccionesTO.getIndex());
			}		
			internacionalesRequestTO.setTipoCambio((String)session.getAttribute("tipoCambio"));

			internacionalesResponseTO = bean.getTransferenciaInternacionalEnvioDatos(internacionalesRequestTO);
			
			if(internacionalesResponseTO != null){
				
				//cuentas
				if(internacionalesResponseTO.getCuentas() != null){
					for(CuentaTransaccionesTO cuentaTransaccionTO : internacionalesResponseTO.getCuentas()){
						if(cuentaTransaccionTO.getIndex().equals(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getOrigen())){
							transferencia_Internacional_EjecucionTO.setCuenta_cargo(Formatter.formatSplittedCuenta(cuentaTransaccionTO.getNumero()));
						}
					}
				}
				
				//Datos del Ordenante
				transferencia_Internacional_EjecucionTO.setTitular_cuenta(clienteTO.getNombreCompleto());
				
				//Datos del Beneficiario
				transferencia_Internacional_EjecucionTO.setNombre_banco_destino(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getNombreBanco());
				transferencia_Internacional_EjecucionTO.setPais_banco_destino(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getNombrePaisBanco());
				transferencia_Internacional_EjecucionTO.setClave_swift_codigo_aba(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getClaveBanco());
				transferencia_Internacional_EjecucionTO.setNombre_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getBeneficiario());
				transferencia_Internacional_EjecucionTO.setApellido_paterno_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getApellidoPaterno());
				transferencia_Internacional_EjecucionTO.setApellido_materno_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getApellidoMaterno());
				transferencia_Internacional_EjecucionTO.setNacionalidad_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getNacionalidad());
				transferencia_Internacional_EjecucionTO.setDomicilio_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getDomicilio());
				transferencia_Internacional_EjecucionTO.setFecha_nacimiento_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getFechaNacimiento());
				transferencia_Internacional_EjecucionTO.setLugar_nacimiento_beneficiario(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getLugarNacimiento());
				transferencia_Internacional_EjecucionTO.setCuenta_destino(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getDestinoFormateado());
				
				//Datos de Operación
				transferencia_Internacional_EjecucionTO.setImporte_dolares(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getAmmountdlls());
				transferencia_Internacional_EjecucionTO.setTipo_cambio(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getTipoCambio());
				transferencia_Internacional_EjecucionTO.setImporte_pesos(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getAmmount());
				transferencia_Internacional_EjecucionTO.setComision_pesos(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getFee());
				transferencia_Internacional_EjecucionTO.setIva_comision_pesos(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getIvaFee());
				transferencia_Internacional_EjecucionTO.setTotal_pesos(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getTotalFee());
				transferencia_Internacional_EjecucionTO.setConcepto_transferencias(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getReferencia());
				transferencia_Internacional_EjecucionTO.setFecha_operacion(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getOperation());
				transferencia_Internacional_EjecucionTO.setFecha_aplicacion(
						Formatter.formatDate(internacionalesResponseTO.getConfirmarTransferenciaInternacionalTO().getApplication()));
				
				response.addAttribute(transferencia_Internacional_EjecucionTO);
				session.addAttribute("datosPaso2", transferencia_Internacional_EjecucionTO);
				//huella
				HuellaTO huellaTO = new HuellaTO();
				DispositivoHuellaTO dispositivoHuellaTO = internacionalesResponseTO.getDispositivoHuellaTO();
				if( dispositivoHuellaTO != null ) {
					huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
					huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
				}	
				response.addAttribute( huellaTO );
				
			}
		} catch (EliteDataException e) {
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION,null);
			}
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		log.info("metodo ejecucion transferencia internacional");
		Response response = new Response();
		TransferenciasInternacionalesRequestTO internacionalesRequestTO = new TransferenciasInternacionalesRequestTO();
		TransferenciasInternacionalesResponseTO internacionalesResponseTO = new TransferenciasInternacionalesResponseTO();
		Transferencia_Internacional_EjecucionTO transferencia_Internacional_EjecucionTO = new Transferencia_Internacional_EjecucionTO();
		try {
			TransferenciaInternacionalForm internacionalForm = (TransferenciaInternacionalForm) getFormBean();
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			internacionalesRequestTO.setUser(clienteTO.getUserName());			
			
			if (internacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				internacionalesRequestTO.setOptionDispositive(OPCION_HUELLA);
				internacionalesRequestTO.setClave(internacionalForm.getHuella_seguridad().toString());
			}else if (internacionalForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				internacionalesRequestTO.setOptionDispositive(OPCION_TOKEN);
				internacionalesRequestTO.setTokencode(internacionalForm.getClave_seguridad().toString());
			}
			
			internacionalesResponseTO = bean.getEjecutarTransferenciaInternacional(internacionalesRequestTO);
			
			transferencia_Internacional_EjecucionTO = (Transferencia_Internacional_EjecucionTO)session.getAttribute("datosPaso2");
			response.addAttribute(transferencia_Internacional_EjecucionTO);
		} catch (EliteDataException e) {
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {
				MensajesTO mensajesTO = buildMessages((Map) e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION,null);
			}
		}
		return response;
	}
	
	
	private class CuentaPredicate implements Predicate {
		private String cuenta;	
		public boolean evaluate(Object obj) {
			if (obj.getClass().getName().endsWith("CuentaTO")){
				CuentaTO cuentaTO = (CuentaTO) obj;
				String temp;
				if (cuenta.length()==20)temp=cuentaTO.getNumero();
				else temp=cuentaTO.getCuentaFormateada().replace(" ","");
				if (temp.equals(cuenta)) {
					return true;
				} else {
					return false;
				}
			}
			else {
				CuentaTransaccionesTO cuentaTransaccionesTO=(CuentaTransaccionesTO) obj;
				String temp=cuentaTransaccionesTO.getCuentaFormateada();
				if (temp.equals(cuenta)) {
					return true;
				} else {
					return false;
				}
			}		
		}

		public String getCuenta() {
			return cuenta;
		}
	
		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}	
}