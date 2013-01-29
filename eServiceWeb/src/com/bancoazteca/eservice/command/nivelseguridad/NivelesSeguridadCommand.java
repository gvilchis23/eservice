package com.bancoazteca.eservice.command.nivelseguridad;

import static com.bancoazteca.elite.beans.SecurityLevelTO.SECURITY_LEVEL_HIGH;
import static com.bancoazteca.elite.beans.SecurityLevelTO.SECURITY_LEVEL_MEDIUM;
import static com.bancoazteca.elite.beans.SecurityLevelTO.TRANFERS_LEVEL_HIGH;
import static com.bancoazteca.elite.beans.SecurityLevelTO.TRANFERS_LEVEL_LOW;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ChangeSecurityLevelRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.SecurityAmountsTO;
import com.bancoazteca.elite.beans.SecurityDataTO;
import com.bancoazteca.elite.beans.SecurityLevelTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Niveles_seguridadTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class NivelesSeguridadCommand extends CommandBase {

	private static final Logger $log = Logger.getLogger(NivelesSeguridadCommand.class);
	private static final String MOVIMIENTOS="MOVIMIENTOS";
	private static final String TRANSFERENCIAS="TRANSFERENCIAS";
	/**
	 * Redirige el flujo a la pantalla inicial de Niveles de Seguridad,
	 * extrayendo los datos necesarios del SecurityDataTO del ClienteTO que se
	 * recuperó en el Login.
	 * @param session
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 * @throws UsuarioException
	 */
	public Response solicitud(Session session)throws EJBException, SessionExpiredException, EliteDataException,UsuarioException {
		Response response= new Response();
		Map<String, String> errors= new HashMap<String, String>();
		NivelesSeguridadForm forma = (NivelesSeguridadForm) getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Niveles_seguridadTO niveles_seguridad = new Niveles_seguridadTO();
		int nivelMovs = 0;
		int nivelTransf = 0;

		if (clienteTO != null && clienteTO.getSecurityData() != null) {
			SecurityDataTO securityDataTO = clienteTO.getSecurityData();
			SecurityLevelTO levelTO = securityDataTO.getSecLevelVO();
			SecurityAmountsTO amountsTO = securityDataTO.getAmountsVO();
			nivelMovs = Integer.parseInt(levelTO.getNivelMovimientos());

			if (nivelMovs < 4) {
				niveles_seguridad.setNivel_seguridad_movimientos(forma.getSecurytyLabel("5",MOVIMIENTOS));
				$log.info("Se configur\u00F3 el Nivel de Movimientos por Default");
			} else {
				niveles_seguridad.setNivel_seguridad_movimientos(forma.getSecurytyLabel(""+nivelMovs,MOVIMIENTOS));
			}
			nivelTransf = Integer.parseInt(levelTO.getNivelTransferencias());
			$log.info("NIVEL DE SEGURIDAD: " + nivelTransf);
			if(nivelTransf!=0 && nivelTransf!=1 ){
				nivelTransf=0;  
			}
				
			niveles_seguridad.setNivel_seguridad_transferencias(forma.getSecurytyLabel(""+nivelTransf,TRANSFERENCIAS));

			if (nivelTransf == TRANFERS_LEVEL_HIGH) {
				niveles_seguridad.setMonto_maximo_transferencias_mismo_banco(amountsTO.getMaxTerceros().doubleValue());
				niveles_seguridad.setMonto_maximo_transferencias_otro_banco(amountsTO.getMaxInterbancario().doubleValue());
				niveles_seguridad.setMonto_maximo_pago_servicios(amountsTO.getMaxPagos().doubleValue());
				niveles_seguridad.setMonto_maximo_transferencias_internacionales(amountsTO.getMaxInternacionales().doubleValue());
			}
			response.addAttribute(niveles_seguridad);
		} else {
			errors.put("nivel_seguridad", "No se ha encontrado información de seguridad");
			returnErrorMap(response, errors);
			
		}
		return response;
	}

	/**
	 * Redirige el flujo hacia la pantalla de la configuraci&oacute;n
	 * seleccionada por el usuario para realizar el cambio de nivel.
	 * @param session
	 * @return
	 * @throws EJBException
	 * @throws EliteDataException
	 * @throws SessionExpiredException
	 */
	public Response validacion(Session session) throws EJBException,EliteDataException, SessionExpiredException {
		Response response= new Response();
		ResourceFacadeSL facadeSL = null;
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO = new ChangeSecurityLevelRequestTO();
		Niveles_seguridadTO niveles_seguridadTO=new Niveles_seguridadTO();
		final String step = "1";
		final String method = "confirma";
		int validationLevel = -1;

		NivelesSeguridadForm forma = (NivelesSeguridadForm) getFormBean();
		$log.info("Nuevo nivel Movimientos seleccionado: "+ forma.getNivel_seguridad_movimientos());
		$log.info("Nivel Transferencias: " + forma.getNivel_seguridad_transferencias());
		$log.info("Nivel Frecuentes: " + forma.getNivel_seguridad_frecuentes());

		try {
			facadeSL = getDelegate();
			validationLevel = getValidationLevel(session, forma.getNivelSeguridadMovimientos());

			changeSecurityLevelRequestTO.setUser(clienteTO.getUserName());
			changeSecurityLevelRequestTO.setPaso(step);
			changeSecurityLevelRequestTO.setMethod(method);
			changeSecurityLevelRequestTO.setNivelMovimientos(""+forma.getNivelSeguridadMovimientos());
			changeSecurityLevelRequestTO.setNivelTransferencias(""+forma.getNivelSeguridadTransferencias());
			changeSecurityLevelRequestTO.setNivelFrecuentes(""+forma.getNivelSeguridadFrecuentes());
			changeSecurityLevelRequestTO.setNivelValidacion(validationLevel);
			
			//Agregamos a la respuesta los datos que el usuario haya seleccionado
			
			niveles_seguridadTO.setNivel_seguridad_movimientos(changeSecurityLevelRequestTO.getNivelMovimientos());
			niveles_seguridadTO.setNivel_seguridad_frecuentes(changeSecurityLevelRequestTO.getNivelFrecuentes());
			niveles_seguridadTO.setNivel_seguridad_transferencias(changeSecurityLevelRequestTO.getNivelTransferencias());

			
			if (forma.getNivelSeguridadTransferencias() == TRANFERS_LEVEL_HIGH) {
				changeSecurityLevelRequestTO.setTransfMismo(forma.getMonto_maximo_transferencias_mismo_banco());
				changeSecurityLevelRequestTO.setTransfOtro(forma.getMonto_maximo_transferencias_otro_banco());
				changeSecurityLevelRequestTO.setTransfInter(forma.getMonto_maximo_transferencias_internacionales_bancarias());
				changeSecurityLevelRequestTO.setPagoServicios(forma.getMonto_maximo_pago_servicios());
				
				//Agreagamos los datos de salida
				
				niveles_seguridadTO.setMonto_maximo_pago_servicios(Double.parseDouble(changeSecurityLevelRequestTO.getPagoServicios()));		
				niveles_seguridadTO.setMonto_maximo_transferencias_internacionales(Double.parseDouble(changeSecurityLevelRequestTO.getTransfInter()));
				niveles_seguridadTO.setMonto_maximo_transferencias_mismo_banco(Double.parseDouble(changeSecurityLevelRequestTO.getTransfMismo()));
				niveles_seguridadTO.setMonto_maximo_transferencias_otro_banco(Double.parseDouble(changeSecurityLevelRequestTO.getTransfOtro()));
	
			}
			ChangeSecurityLevelResponseTO changeSecurityLevelResponseTO = facadeSL.modifySecurityLevel(changeSecurityLevelRequestTO);
			DispositivoHuellaTO dispositivoHuella= changeSecurityLevelResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO=new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuella.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuella.getLongitudHuella());
			
			response.addAttribute(niveles_seguridadTO);
			response.addAttribute(huellaTO);
			session.addAttribute(NIVELES_SEGURIDAD, forma);
		} catch(Exception e){
			buildErrorResponse(new EliteDataException("Error",e,Errors.VALIDATION_CODE), response);
		}
		return response;
	}

	/**
	 * Ejecuta el cambio de nivel seguridad a trav&eacute;s de eBanking.
	 * @param session
	 * @return
	 * @throws EJBException
	 * @throws EliteDataException
	 * @throws SessionExpiredException
	 */
	public Response ejecucion(Session session) throws EJBException,EliteDataException, SessionExpiredException {
		Response response= new Response();
		$log.info("Method: executeChange");
		NivelesSeguridadForm forma = (NivelesSeguridadForm) getFormBean();
		NivelesSeguridadForm datos = (NivelesSeguridadForm) session.getAttribute(NIVELES_SEGURIDAD);
		
		final String step = "2";
		final String method = "ejecuta";
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL facade = null;
		ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO = new ChangeSecurityLevelRequestTO();
		try {
			changeSecurityLevelRequestTO.setUser(clienteTO.getUserName());
			changeSecurityLevelRequestTO.setPaso(step);
			changeSecurityLevelRequestTO.setMethod(method);
			changeSecurityLevelRequestTO.setNivelMovimientos(String.valueOf(datos.getNivelSeguridadMovimientos()));
			changeSecurityLevelRequestTO.setNivelTransferencias(String.valueOf(datos.getNivelSeguridadTransferencias()));
			changeSecurityLevelRequestTO.setNivelFrecuentes(String.valueOf(datos.getNivelSeguridadFrecuentes()));
			if (datos.getNivelSeguridadTransferencias() == TRANFERS_LEVEL_LOW ) {
				datos.setMonto_maximo_transferencias_mismo_banco("");
				datos.setMonto_maximo_transferencias_otro_banco("");
				datos.setMonto_maximo_transferencias_internacionales_bancarias("");
				datos.setMonto_maximo_pago_servicios("");
			}
			changeSecurityLevelRequestTO.setTransfMismo(datos.getMonto_maximo_transferencias_mismo_banco());
			changeSecurityLevelRequestTO.setTransfOtro(datos.getMonto_maximo_transferencias_otro_banco());
			changeSecurityLevelRequestTO.setTransfInter(datos.getMonto_maximo_transferencias_internacionales_bancarias());
			changeSecurityLevelRequestTO.setPagoServicios(datos.getMonto_maximo_pago_servicios());

			String opc_seg=forma.getOpcion_seguridad();
			 
			if(opc_seg.equalsIgnoreCase(TAG_HUELLA)){
				changeSecurityLevelRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else{
				changeSecurityLevelRequestTO.setTokenCode(forma.getClave_seguridad().toString());
			}
			try {
				facade = getDelegate();
				ChangeSecurityLevelResponseTO changeSecurityLevelResponseTO = facade.waitChangeSecurityLevel(changeSecurityLevelRequestTO);
				$log.info("response: " + changeSecurityLevelResponseTO);
				ChangeSecurityLevelResponseTO changeSecurityLevelResponse= facade.executeChangeLevel(changeSecurityLevelRequestTO);
				String mensaje = changeSecurityLevelResponse.getMessage();
				$log.info("Mensaje: " + mensaje);
				updateSessionSecurityData(session, datos ,response);
			} catch (UsuarioException e) {
				throw new EliteDataException("Error", e,Errors.EXCEPTION_CODE);
			} catch (ServiceLocatorException e) {
				throw new EliteDataException("Error", e,Errors.EXCEPTION_CODE);
			}
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	/**
	 * Actualiza los Niveles de Seguridad en el objeto session a los objetos
	 * SecurityData y SecurityTemp
	 * 
	 * @param session
	 * @param forma
	 */
	private void updateSessionSecurityData(Session session,NivelesSeguridadForm datosModificados,Response response) {
		Niveles_seguridadTO niveles_seguridad= new Niveles_seguridadTO();
		
		niveles_seguridad.setNivel_seguridad_frecuentes(
				datosModificados.getNivel_seguridad_frecuentes()
				);
		niveles_seguridad.setNivel_seguridad_movimientos(
				datosModificados.getNivel_seguridad_transferencias()
				);
		niveles_seguridad.setNivel_seguridad_transferencias(
				datosModificados.getNivel_seguridad_transferencias()
				);
		niveles_seguridad.setMonto_maximo_pago_servicios(Double.parseDouble(
				Validator.isEmptyData(datosModificados.getMonto_maximo_pago_servicios())?"0.00":datosModificados.getMonto_maximo_pago_servicios()
				));
		niveles_seguridad.setMonto_maximo_transferencias_internacionales(Double.parseDouble(
				Validator.isEmptyData(datosModificados.getMonto_maximo_transferencias_internacionales_bancarias())?"0.00":datosModificados.getMonto_maximo_transferencias_internacionales_bancarias()
				));
		niveles_seguridad.setMonto_maximo_transferencias_mismo_banco(Double.parseDouble(
				Validator.isEmptyData(datosModificados.getMonto_maximo_transferencias_mismo_banco())?"0.00":datosModificados.getMonto_maximo_transferencias_mismo_banco()
				));
		niveles_seguridad.setMonto_maximo_transferencias_otro_banco(Double.parseDouble(
				Validator.isEmptyData(datosModificados.getMonto_maximo_transferencias_otro_banco())?"0.00":datosModificados.getMonto_maximo_transferencias_otro_banco()
				));
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		String nivelMovs = String.valueOf(datosModificados.getNivel_seguridad_movimientos());
		String nivelTransf = String.valueOf(datosModificados.getNivel_seguridad_transferencias());
		String nivelFrecs = String.valueOf(datosModificados.getNivel_seguridad_frecuentes());

		clienteTO.getSecurityData().getSecLevelVO().setNivelMovimientos(getNivelSeguridadNumerico(nivelMovs,2));
		clienteTO.getSecurityData().getSecLevelVO().setNivelTransferencias(getNivelSeguridadNumerico(nivelTransf,1));
		clienteTO.getSecurityData().getSecLevelVO().setNivelFrecuentes(nivelFrecs);

		clienteTO.getSecurityData().getAmountsVO().setMaxTerceros(Validator.isEmptyData(
				datosModificados.getMonto_maximo_transferencias_mismo_banco()) ? null: new BigDecimal(datosModificados.getMonto_maximo_transferencias_mismo_banco())
		);
		clienteTO.getSecurityData().getAmountsVO().setMaxInterbancario(Validator.isEmptyData(
				datosModificados.getMonto_maximo_transferencias_otro_banco()) ? null: new BigDecimal(datosModificados.getMonto_maximo_transferencias_otro_banco())
		);
		clienteTO.getSecurityData().getAmountsVO().setMaxPagos(	
				Validator.isEmptyData(datosModificados.getMonto_maximo_pago_servicios()) ? null: new BigDecimal(datosModificados.getMonto_maximo_pago_servicios())
		);
		clienteTO.getSecurityData().getAmountsVO().setMaxInternacionales(
				Validator.isEmptyData(datosModificados.getMonto_maximo_transferencias_internacionales_bancarias()) ? null: new BigDecimal(datosModificados.getMonto_maximo_transferencias_internacionales_bancarias())
		);
		clienteTO.setSecurityTemp(clienteTO.getSecurityData());
		session.addAttribute(CLIENTE_TO, clienteTO);
		response.addAttribute(niveles_seguridad);
	}

	/**
	 * Verifica el tipo de validaci&oacute;n para realizarla con token o huella.
	 * @param session
	 * @param nivelSeleccionado
	 * @return
	 */
	private int getValidationLevel(Session session,int nivelSeleccionado) {

		int nivelValidacion = -1;
		SecurityLevelTO securityLevelTO = ((ClienteTO) session.getAttribute(CLIENTE_TO)).getSecurityData().getSecLevelVO();
		int nivelMovimientos = Integer.parseInt(securityLevelTO.getNivelMovimientos());
		if ((nivelMovimientos == SECURITY_LEVEL_HIGH || nivelMovimientos == SECURITY_LEVEL_MEDIUM)
				|| (nivelSeleccionado == SECURITY_LEVEL_HIGH || nivelSeleccionado == SECURITY_LEVEL_MEDIUM)) {
			nivelValidacion = TRANFERS_LEVEL_HIGH;
		} else {
			nivelValidacion = TRANFERS_LEVEL_LOW;
		}
		return nivelValidacion;
	}
	
	private String getNivelSeguridadNumerico(String nivelAlfanumerico, int tipo){
		int nivelNumerico = 0;
		// Nivel de Transferencia
		if(tipo == 1){
			if(nivelAlfanumerico.equals("BAJO")){
				nivelNumerico = TRANFERS_LEVEL_LOW;						
			}else{
				nivelNumerico = TRANFERS_LEVEL_HIGH;
			} 
		// Nivel de Movimientos	
		}else{
			if(nivelAlfanumerico.equals("MEDIO")){
				nivelNumerico = SECURITY_LEVEL_MEDIUM;					
			}else if(nivelAlfanumerico.equals("ALTO")){
				nivelNumerico = SECURITY_LEVEL_HIGH;
			}else{
				nivelNumerico = 5;
			}
			
		}
		
		
		return String.valueOf(nivelNumerico);
	}
}
