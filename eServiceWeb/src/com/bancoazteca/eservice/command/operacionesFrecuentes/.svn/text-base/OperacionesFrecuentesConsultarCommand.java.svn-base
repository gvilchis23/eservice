package com.bancoazteca.eservice.command.operacionesFrecuentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.OperacionFrecuenteTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Operacion_Frecuente_Pago_ServiciosTO;
import com.bancoazteca.eservice.command.base.beans.Operacion_Frecuente_Pago_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Operacion_Frecuente_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.beans.Operacion_Frecuente_TransferenciasTO;
import com.bancoazteca.eservice.command.base.beans.Operacion_Frecuente_Transferencias_TercerosTO;
import com.bancoazteca.eservice.command.base.beans.Operaciones_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class OperacionesFrecuentesConsultarCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		//OPERACIONES FRECUENTES
		OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO = new OperacionesFrecuentesRequestTO();
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		
		Operaciones_FrecuentesTO operacionFrecuente = new Operaciones_FrecuentesTO();
 
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL=getDelegate();
			operacionesFrecuentesRequestTO.setUsuarioAlnova(clienteTO.getNumero());
			operacionesFrecuentesRequestTO.setUser(clienteTO.getUserName());
			operacionesFrecuentesResponseTO = resourceFacadeSL.getDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
			
			if(operacionesFrecuentesResponseTO.getListaDatos() != null){
				Collection <OperacionFrecuenteTO> listaDatos = (Collection <OperacionFrecuenteTO>)operacionesFrecuentesResponseTO.getListaDatos();
				
				Operacion_Frecuente_Transferencias_TercerosTO transTerceros = null;
				Operacion_Frecuente_TransferenciasTO transferenciasTO = null;
				Operacion_Frecuente_Pago_TarjetaTO pagoTarjetaTO = null;
				Operacion_Frecuente_Pago_ServiciosTO pagoServiciosTO = null;
				Operacion_Frecuente_Tiempo_AireTO tiempoAireTO = null;
				
				Collection <Operacion_Frecuente_Transferencias_TercerosTO> coleccion_transferencias_terceros = null;
				Collection <Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_spei = null;
				Collection <Operacion_Frecuente_TransferenciasTO> coleccion_transferencias_tef = null;
				Collection <Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_azteca = null;
				Collection <Operacion_Frecuente_Pago_TarjetaTO> coleccion_pago_tarjeta_otros_bancos = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_telmex = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_axtel = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_cfe = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_iusacell = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_movistar = null;
				Collection <Operacion_Frecuente_Pago_ServiciosTO> coleccion_pago_servicio_sky = null;
				Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_telcel = null;
				Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_unefon = null;
				Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_iusacell = null;
				Collection <Operacion_Frecuente_Tiempo_AireTO> coleccion_tiempo_aire_movistar = null;
				
				for(OperacionFrecuenteTO operacionFrecuenteTO : listaDatos){
					if(operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("TRANSFERENCIAS.TERCEROS")){
						transTerceros = new Operacion_Frecuente_Transferencias_TercerosTO();
						
						transTerceros.setAlias_operacion_frecuente(operacionFrecuenteTO.getAlias());
						transTerceros.setUsuario_alnova(operacionFrecuenteTO.getUsuario());
						transTerceros.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones());
						transTerceros.setClave_operacion(operacionFrecuenteTO.getClave_operacion());
						
						String cadenaDatos = operacionFrecuenteTO.getCadena_datos();
						String [] datosSplit = cadenaDatos.split(";");
						for(int i=0; i<datosSplit.length; i++){
							String dato = datosSplit[i];
							if(dato.contains("cuenta_cargo")){
								transTerceros.setCuenta_cargo(devuelveCadena(dato));
							}else if(dato.contains("cuenta_destino")){
								transTerceros.setCuenta_destino(devuelveCadena(dato));
							}else if(dato.contains("concepto")){
								transTerceros.setConcepto(devuelveCadena(dato));
							}else if(dato.contains("referencia")){
								transTerceros.setReferencia(devuelveCadena(dato));
							}else if(dato.contains("importe")){
								transTerceros.setImporte(devuelveCadena(dato));
							}
						}	
						if(coleccion_transferencias_terceros == null){
							coleccion_transferencias_terceros = new ArrayList<Operacion_Frecuente_Transferencias_TercerosTO>();
						}						
						coleccion_transferencias_terceros.add(transTerceros);
						
					}else if(operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("TRANSFERENCIAS.SPEI") ||
							operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("TRANSFERENCIAS.TEF")){
						
						transferenciasTO = new Operacion_Frecuente_TransferenciasTO();
						
						transferenciasTO.setAlias_operacion_frecuente(operacionFrecuenteTO.getAlias());
						transferenciasTO.setUsuario_alnova(operacionFrecuenteTO.getUsuario());
						transferenciasTO.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones());
						transferenciasTO.setClave_operacion(operacionFrecuenteTO.getClave_operacion());
						
						String cadenaDatos = operacionFrecuenteTO.getCadena_datos();
						String [] datosSplit = cadenaDatos.split(";");
						for(int i=0; i<datosSplit.length; i++){
							String dato = datosSplit[i];
							if(dato.contains("cuenta_cargo")){
								transferenciasTO.setCuenta_cargo(devuelveCadena(dato));
							}else if(dato.contains("tipo_cuenta_destino")){
								transferenciasTO.setTipo_cuenta_destino(devuelveCadena(dato));
							}else if(dato.contains("banco_destino")){
								transferenciasTO.setBanco_destino(devuelveCadena(dato));
							}else if(dato.contains("rfc_curp")){
								transferenciasTO.setRfc_curp(devuelveCadena(dato));
							}else if(dato.contains("beneficiario")){
								transferenciasTO.setBeneficiario(devuelveCadena(dato));
							}else if(dato.contains("importe")){
								transferenciasTO.setImporte(devuelveCadena(dato));
							}else if(dato.contains("concepto")){
								transferenciasTO.setConcepto(devuelveCadena(dato));
							}else if(dato.contains("referencia")){
								transferenciasTO.setReferencia(devuelveCadena(dato));
							}else if(dato.contains("fecha_aplicacion")){
								transferenciasTO.setFecha_aplicacion(devuelveCadena(dato));
							}else if(dato.contains("rfc_beneficiario")){
								transferenciasTO.setRfc_beneficiario(devuelveCadena(dato));
							}else if(dato.contains("iva_deducir")){
								transferenciasTO.setIva_deducir(devuelveCadena(dato));
							}else if(dato.contains("deducir_impuestos")){
								transferenciasTO.setDeducir_impuestos(devuelveCadena(dato));
							}else if(dato.contains("cuenta_destino")){
								transferenciasTO.setCuenta_destino(devuelveCadena(dato));
							}
						}
						if(operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("TRANSFERENCIAS.SPEI")){
							if(coleccion_transferencias_spei == null){
								coleccion_transferencias_spei = new ArrayList<Operacion_Frecuente_TransferenciasTO>();
							}
							coleccion_transferencias_spei.add(transferenciasTO);
						}else{
							if(coleccion_transferencias_tef == null){
								coleccion_transferencias_tef = new ArrayList<Operacion_Frecuente_TransferenciasTO>();
							}
							coleccion_transferencias_tef.add(transferenciasTO);
						}
						
						
					}else if(operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("PAGO_TARJETA_AZTECA") || 
							 operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("PAGO_SERVICIO.PAGO_CREDIT")){
						pagoTarjetaTO = new Operacion_Frecuente_Pago_TarjetaTO();
						
						pagoTarjetaTO.setAlias_operacion_frecuente(operacionFrecuenteTO.getAlias());
						pagoTarjetaTO.setUsuario_alnova(operacionFrecuenteTO.getUsuario());
						pagoTarjetaTO.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones());
						pagoTarjetaTO.setClave_operacion(operacionFrecuenteTO.getClave_operacion());
						
						String cadenaDatos = operacionFrecuenteTO.getCadena_datos();
						String [] datosSplit = cadenaDatos.split(";");
						for(int i=0; i<datosSplit.length; i++){
							String dato = datosSplit[i];
							if(dato.contains("cuenta_cargo")){
								pagoTarjetaTO.setCuenta_cargo(devuelveCadena(dato));
							}else if(dato.contains("numero_tarjeta")){
								pagoTarjetaTO.setNumero_tarjeta(devuelveCadena(dato));
							}else if(dato.contains("importe")){
								pagoTarjetaTO.setImporte(devuelveCadena(dato));
							}else if(dato.contains("nombre_banco")){
								pagoTarjetaTO.setNombre_banco(devuelveCadena(dato));
							}
						}
						
						if(operacionFrecuenteTO.getOperaciones().equalsIgnoreCase("PAGO_TARJETA_AZTECA")){
							if(coleccion_pago_tarjeta_azteca == null){
								coleccion_pago_tarjeta_azteca = new ArrayList<Operacion_Frecuente_Pago_TarjetaTO>();
							}							
							coleccion_pago_tarjeta_azteca.add(pagoTarjetaTO);
						}else{
							pagoTarjetaTO.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones()+"O");
							if(coleccion_pago_tarjeta_otros_bancos == null){
								coleccion_pago_tarjeta_otros_bancos = new ArrayList<Operacion_Frecuente_Pago_TarjetaTO>();
							}							
							coleccion_pago_tarjeta_otros_bancos.add(pagoTarjetaTO);
						}
						
					}else if(operacionFrecuenteTO.getOperaciones().contains("PAGO_SERVICIO.")){
						pagoServiciosTO = new Operacion_Frecuente_Pago_ServiciosTO();
						
						pagoServiciosTO.setAlias_operacion_frecuente(operacionFrecuenteTO.getAlias());
						pagoServiciosTO.setUsuario_alnova(operacionFrecuenteTO.getUsuario());
						pagoServiciosTO.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones());
						pagoServiciosTO.setClave_operacion(operacionFrecuenteTO.getClave_operacion());
						
						String cadenaDatos = operacionFrecuenteTO.getCadena_datos();
						String [] datosSplit = cadenaDatos.split(";");
						for(int i=0; i<datosSplit.length; i++){
							String dato = datosSplit[i];
							if(dato.contains("cuenta_cargo")){
								pagoServiciosTO.setCuenta_cargo(devuelveCadena(dato));
							}else if(dato.contains("numero_referencia")){
								pagoServiciosTO.setNumero_referencia(devuelveCadena(dato));
							}else if(dato.contains("importe")){
								pagoServiciosTO.setImporte(devuelveCadena(dato));
							}else if(dato.contains("concepto_pago")){
								pagoServiciosTO.setConcepto_pago(devuelveCadena(dato));
							}else if(dato.contains("digito_verificador")){
								pagoServiciosTO.setDigito_verificador(devuelveCadena(dato));
							}
						}
						
						if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.TELMEX")){
							if(coleccion_pago_servicio_telmex == null){
								coleccion_pago_servicio_telmex = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}							
							coleccion_pago_servicio_telmex.add(pagoServiciosTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.SKY")){
							if(coleccion_pago_servicio_sky == null){
								coleccion_pago_servicio_sky = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}							
							coleccion_pago_servicio_sky.add(pagoServiciosTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.IUSACELL")){
							if(coleccion_pago_servicio_iusacell == null){
								coleccion_pago_servicio_iusacell = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}							
							coleccion_pago_servicio_iusacell.add(pagoServiciosTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.MOVISTAR")){
							if(coleccion_pago_servicio_movistar == null){
								coleccion_pago_servicio_movistar = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}							
							coleccion_pago_servicio_movistar.add(pagoServiciosTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.AXTEL")){
							if(coleccion_pago_servicio_axtel == null){
								coleccion_pago_servicio_axtel = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}							
							coleccion_pago_servicio_axtel.add(pagoServiciosTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("PAGO_SERVICIO.CFE")){
							if(coleccion_pago_servicio_cfe == null){
								coleccion_pago_servicio_cfe = new ArrayList<Operacion_Frecuente_Pago_ServiciosTO>();
							}
							coleccion_pago_servicio_cfe.add(pagoServiciosTO);
						}
						
					}else if(operacionFrecuenteTO.getOperaciones().contains("TIEMPO_AIRE.")){
						tiempoAireTO = new Operacion_Frecuente_Tiempo_AireTO();
						
						tiempoAireTO.setAlias_operacion_frecuente(operacionFrecuenteTO.getAlias());
						tiempoAireTO.setUsuario_alnova(operacionFrecuenteTO.getUsuario());
						tiempoAireTO.setOperacion_frecuente(operacionFrecuenteTO.getOperaciones());
						tiempoAireTO.setClave_operacion(operacionFrecuenteTO.getClave_operacion());
						
						String cadenaDatos = operacionFrecuenteTO.getCadena_datos();
						String [] datosSplit = cadenaDatos.split(";");
						for(int i=0; i<datosSplit.length; i++){
							String dato = datosSplit[i];
							if(dato.contains("cuenta_cargo")){
								tiempoAireTO.setCuenta_cargo(devuelveCadena(dato));
							}else if(dato.contains("monto")){
								tiempoAireTO.setMonto(devuelveCadena(dato));
							}else if(dato.contains("iva")){
								tiempoAireTO.setIva(devuelveCadena(dato));
							}else if(dato.contains("folio_aclaracion")){
								tiempoAireTO.setFolio_aclaracion(devuelveCadena(dato));
							}else if(dato.contains("comision")){
								tiempoAireTO.setComision(devuelveCadena(dato));
							}else if(dato.contains("numero_telefonico")){
								tiempoAireTO.setNumero_telefonico(devuelveCadena(dato));
							}else if(dato.contains("carrier")){
								tiempoAireTO.setCarrier(devuelveCadena(dato));
							}
						}
						if(operacionFrecuenteTO.getOperaciones().equals("TIEMPO_AIRE.TELCEL")){
							if(coleccion_tiempo_aire_telcel == null){
								coleccion_tiempo_aire_telcel = new ArrayList<Operacion_Frecuente_Tiempo_AireTO>();
							}							
							coleccion_tiempo_aire_telcel.add(tiempoAireTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("TIEMPO_AIRE.MOVISTAR")){
							if(coleccion_tiempo_aire_movistar == null){
								coleccion_tiempo_aire_movistar = new ArrayList<Operacion_Frecuente_Tiempo_AireTO>();
							}							
							coleccion_tiempo_aire_movistar.add(tiempoAireTO);
						}else if(operacionFrecuenteTO.getOperaciones().equals("TIEMPO_AIRE.IUSACELL")){
							if(coleccion_tiempo_aire_iusacell == null){
								coleccion_tiempo_aire_iusacell = new ArrayList<Operacion_Frecuente_Tiempo_AireTO>();
							}							
							coleccion_tiempo_aire_iusacell.add(tiempoAireTO);
						}else{
							if(coleccion_tiempo_aire_unefon == null){
								coleccion_tiempo_aire_unefon = new ArrayList<Operacion_Frecuente_Tiempo_AireTO>();
							}							
							coleccion_tiempo_aire_unefon.add(tiempoAireTO);
						}
					}
				}
				
				operacionFrecuente.setColeccion_transferencias_terceros(coleccion_transferencias_terceros);
				operacionFrecuente.setColeccion_transferencias_spei(coleccion_transferencias_spei);
				operacionFrecuente.setColeccion_transferencias_tef(coleccion_transferencias_tef);
				operacionFrecuente.setColeccion_pago_tarjeta_azteca(coleccion_pago_tarjeta_azteca);
				operacionFrecuente.setColeccion_pago_tarjeta_otros_bancos(coleccion_pago_tarjeta_otros_bancos);
				operacionFrecuente.setColeccion_pago_servicio_telmex(coleccion_pago_servicio_telmex);
				operacionFrecuente.setColeccion_pago_servicio_axtel(coleccion_pago_servicio_axtel);
				operacionFrecuente.setColeccion_pago_servicio_cfe(coleccion_pago_servicio_cfe);
				operacionFrecuente.setColeccion_pago_servicio_iusacell(coleccion_pago_servicio_iusacell);
				operacionFrecuente.setColeccion_pago_servicio_movistar(coleccion_pago_servicio_movistar);
				operacionFrecuente.setColeccion_pago_servicio_sky(coleccion_pago_servicio_sky);
				operacionFrecuente.setColeccion_tiempo_aire_telcel(coleccion_tiempo_aire_telcel);
				operacionFrecuente.setColeccion_tiempo_aire_unefon(coleccion_tiempo_aire_unefon);
				operacionFrecuente.setColeccion_tiempo_aire_iusacell(coleccion_tiempo_aire_iusacell);
				operacionFrecuente.setColeccion_tiempo_aire_movistar(coleccion_tiempo_aire_movistar);
			}			
			response.addAttribute(operacionFrecuente);

		}catch(EliteDataException e) {
			buildErrorResponse(e, response);			
		}
		
		return response;
	}
	
	private String devuelveCadena(String cadena){
		int ini = cadena.indexOf("/");
		String cadenaResultante = cadena.substring(ini+1, cadena.length());
		if(cadenaResultante.equalsIgnoreCase("null")){
			cadenaResultante="";
		}
		return cadenaResultante;
	}
}
