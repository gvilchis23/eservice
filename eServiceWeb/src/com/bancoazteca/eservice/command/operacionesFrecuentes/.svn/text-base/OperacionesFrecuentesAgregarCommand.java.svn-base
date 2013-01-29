package com.bancoazteca.eservice.command.operacionesFrecuentes;

import java.util.HashMap;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Operaciones_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class OperacionesFrecuentesAgregarCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		//OPERACIONES FRECUENTES
		OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO = new OperacionesFrecuentesRequestTO();
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		Operaciones_FrecuentesTO operacionFrecuente = new Operaciones_FrecuentesTO();
		HashMap<String, String> errors = null;
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL=getDelegate();
			OperacionesFrecuentesForm forma = (OperacionesFrecuentesForm)getFormBean();		
			operacionesFrecuentesRequestTO.setUser(clienteTO.getUserName());
			operacionesFrecuentesRequestTO.setUsuarioAlnova(clienteTO.getNumero());
			if(forma.getAlias().length()>20){
				operacionesFrecuentesRequestTO.setAlias(forma.getAlias().substring(0, 20));
			}else{
				operacionesFrecuentesRequestTO.setAlias(forma.getAlias());
			}
			operacionesFrecuentesRequestTO.setOperacion(forma.getOperacion_frecuente().toUpperCase());	
			
			StringBuilder cadenaDatos = new StringBuilder();
			cadenaDatos.append("cuenta_cargo/");
			cadenaDatos.append(forma.getCuenta_cargo());
			cadenaDatos.append(";");
			if(forma.getOperacion_frecuente().equalsIgnoreCase("TRANSFERENCIAS.TERCEROS")){
				cadenaDatos.append("cuenta_destino/");
				cadenaDatos.append(forma.getCuenta_destino());
				cadenaDatos.append(";");
				cadenaDatos.append("concepto/");
				cadenaDatos.append(forma.getConcepto());
				cadenaDatos.append(";");
				cadenaDatos.append("referencia/");
				cadenaDatos.append(forma.getReferencia());
				cadenaDatos.append(";");
				cadenaDatos.append("importe/");
				cadenaDatos.append(forma.getImporte());
				cadenaDatos.append(";");
			}else if(forma.getOperacion_frecuente().equalsIgnoreCase("TRANSFERENCIAS.SPEI") ||
					 forma.getOperacion_frecuente().equalsIgnoreCase("TRANSFERENCIAS.TEF")){
				cadenaDatos.append("cuenta_destino/");
				cadenaDatos.append(forma.getCuenta_destino());
				cadenaDatos.append(";");
				cadenaDatos.append("tipo_cuenta_destino/");
				cadenaDatos.append(forma.getTipo_cuenta_destino());
				cadenaDatos.append(";");
				cadenaDatos.append("banco_destino/");
				cadenaDatos.append(forma.getBanco_destino());
				cadenaDatos.append(";");
				cadenaDatos.append("rfc_curp/");
				cadenaDatos.append(forma.getRfc_curp());
				cadenaDatos.append(";");
				cadenaDatos.append("beneficiario/");
				cadenaDatos.append(forma.getBeneficiario());
				cadenaDatos.append(";");
				cadenaDatos.append("importe/");
				cadenaDatos.append(forma.getImporte());
				cadenaDatos.append(";");
				cadenaDatos.append("concepto/");
				cadenaDatos.append(forma.getConcepto());
				cadenaDatos.append(";");
				cadenaDatos.append("referencia/");
				cadenaDatos.append(forma.getReferencia());
				cadenaDatos.append(";");
				cadenaDatos.append("fecha_aplicacion/");
				cadenaDatos.append(forma.getFecha_aplicacion());
				cadenaDatos.append(";");
				cadenaDatos.append("rfc_beneficiario/");
				cadenaDatos.append(forma.getRfc_beneficiario());
				cadenaDatos.append(";");
				cadenaDatos.append("iva_deducir/");
				cadenaDatos.append(forma.getIva_deducir());
				cadenaDatos.append(";");
				cadenaDatos.append("deducir_impuestos/");
				cadenaDatos.append(forma.getDeducir_impuestos());
				cadenaDatos.append(";");
			}else if(forma.getOperacion_frecuente().equalsIgnoreCase("PAGO_TARJETA_AZTECA") || 
					 forma.getOperacion_frecuente().equalsIgnoreCase("PAGO_SERVICIO.PAGO_CREDITO")){
				cadenaDatos.append("numero_tarjeta/");
				cadenaDatos.append(forma.getNumero_tarjeta());
				cadenaDatos.append(";");
				cadenaDatos.append("importe/");
				cadenaDatos.append(forma.getImporte());
				cadenaDatos.append(";");
				if(forma.getOperacion_frecuente().equalsIgnoreCase("PAGO_SERVICIO.PAGO_CREDITO")){
					operacionesFrecuentesRequestTO.setOperacion("PAGO_SERVICIO.PAGO_CREDIT");
					cadenaDatos.append("nombre_banco/");
					cadenaDatos.append(forma.getNombre_banco());
					cadenaDatos.append(";");
				}else{
					cadenaDatos.append("nombre_banco/");
					cadenaDatos.append("Banco Azteca");
					cadenaDatos.append(";");
				}
			}else if(forma.getOperacion_frecuente().contains("PAGO_SERVICIO.")){
				cadenaDatos.append("numero_referencia/");
				cadenaDatos.append(forma.getNumero_referencia());
				cadenaDatos.append(";");
				cadenaDatos.append("importe/");
				cadenaDatos.append(forma.getImporte());
				cadenaDatos.append(";");
				cadenaDatos.append("concepto_pago/");
				cadenaDatos.append(forma.getConcepto_pago());
				cadenaDatos.append(";");
				cadenaDatos.append("digito_verificador/");
				cadenaDatos.append(forma.getDigito_verificador());
				cadenaDatos.append(";");
			}else if(forma.getOperacion_frecuente().contains("TIEMPO_AIRE.")){
				cadenaDatos.append("monto/");
				cadenaDatos.append(forma.getMonto());
				cadenaDatos.append(";");
				cadenaDatos.append("iva/");
				cadenaDatos.append(forma.getIva());
				cadenaDatos.append(";");
				cadenaDatos.append("folio_aclaracion/");
				cadenaDatos.append(forma.getFolio_aclaracion());
				cadenaDatos.append(";");
				cadenaDatos.append("comision/");
				cadenaDatos.append(forma.getComision());
				cadenaDatos.append(";");
				cadenaDatos.append("numero_telefonico/");
				cadenaDatos.append(forma.getNumero_telefonico());
				cadenaDatos.append(";");
				cadenaDatos.append("carrier/");
				cadenaDatos.append(forma.getCarrier());
				cadenaDatos.append(";");
			}
			
			operacionesFrecuentesRequestTO.setCadenaDatos(cadenaDatos.toString());
			operacionesFrecuentesResponseTO = resourceFacadeSL.setDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
			
			if(operacionesFrecuentesResponseTO.getMensaje().equalsIgnoreCase(OPERACION_FRECUENTE_EXITO)){
				operacionFrecuente.setMensaje_operacion_frecuente("La operacion frecuente ha sido guardada con exito.");
			}else if(operacionesFrecuentesResponseTO.getMensaje().equalsIgnoreCase(OPERACION_FRECUENTE_EXCEDE)){
				errors = new HashMap<String, String>();
				errors.put("mensaje_operacion_frecuente", "La operacion frecuente No ha sido guardada," +
						" debido a que ha alcanzado el maximo número de operaciones registradas. " +
						"Solo son permitidas 50 operaciones frecuentes para su registro.");
				return returnErrorMap(response, errors);
			}else{
				errors = new HashMap<String, String>();
				errors.put("mensaje_operacion_frecuente","Estimado usuario, lo sentimos por el momento no es posible procesar su petición. " +
							"Nuestros ingenieros ya estan trabajando para darle solución. Por su comprensión GRACIAS.");
				return returnErrorMap(response, errors);
			}
			response.addAttribute(operacionFrecuente);
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);			
		}
		return response;
	}
	
}
