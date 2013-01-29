package com.bancoazteca.eservice.command.transferencias.spei;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.ejb.EJBException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.BancoSpeiTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConfirmarTransferenciaSpeiTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.CuentaVisibleSpeiTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.beans.SpeiProgramadoRequestTO;
import com.bancoazteca.elite.beans.TerminalAlnovaResponseTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiResponseTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.alnova.beans.F752ObtieneFechaHabilRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.F752ObtieneFechaHabilResponseTO;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Envio_DineroTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Token_ValidacionTO;
import com.bancoazteca.eservice.command.base.beans.Transferencias_Spei_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.Validacion_huellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.frecuentes.transferencias.ConsultaFrecuentesTransferenciasCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.command.validahuella.ValidaHuellaCommand;
import com.bancoazteca.eservice.command.validahuella.ValidaHuellaForm;
import com.bancoazteca.eservice.command.validatoken.ValidaTokenCommand;
import com.bancoazteca.eservice.command.validatoken.ValidaTokenForm;
import com.bancoazteca.eservice.validator.Errors;

public class TransferenciaSpeiCommand  extends CommandBase{
	
	private static final String FRECUENTE_BLOQUEADA="frecuenteBloqueada";
	private static final String DATOS_TRANSFERENCIAS_PETICION_SPEI="datosTrasnferenciaSpei";
	private static final String DATOS_TRANSFERENCIAS_RESPUESTA_SPEI="datosPeticionSpei";
	private static final String IGNORAR_PROCESO_FRECUENTE="ignorarFrecuente";
	private static final String HORA_ALTA_FRECUENTE="horaAltaFrecuente";
	public  static final String DATOS_FINALES="datosFinalesSpei30";
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL bean = getDelegate();
		
		session.deleteAttribute(FRECUENTE_BLOQUEADA);
		session.deleteAttribute(DATOS_TRANSFERENCIAS_PETICION_SPEI);
		session.deleteAttribute(DATOS_TRANSFERENCIAS_RESPUESTA_SPEI);
		session.deleteAttribute(IGNORAR_PROCESO_FRECUENTE);
		session.deleteAttribute(HORA_ALTA_FRECUENTE);
		session.deleteAttribute(DATOS_FINALES);
		
		TransferenciasSpeiRequestTO speiRequestTO = new TransferenciasSpeiRequestTO();
		TransferenciasSpeiResponseTO speiResponseTO = new TransferenciasSpeiResponseTO();						
		
		speiRequestTO.setUser(clienteTO.getUserName());
		try{
			speiResponseTO = bean.getTransferenciaSpeiInvocacion(speiRequestTO);
				
			Envio_DineroTO solicitudEnviosTO= new Envio_DineroTO();
			solicitudEnviosTO.setOperacion_programada("false");
			
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			for(CuentaVisibleSpeiTO cuentaVisibleSpeiTO: speiResponseTO.getCuentasSpei()){
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				String cuenta=cuentaVisibleSpeiTO.getLabel();
				if (cuenta.contains(" ")){
					cuenta=cuenta.split(" ")[0];
				}
				CuentaTO cuentaTO = (CuentaTO)super.getAccountsPrdicate(clienteTO.getCuentas(),cuenta);// CollectionUtils.find(clienteTO.getCuentas(), predicate );
				if (cuentaTO!=null){
					cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));			
					cuentaCargoTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			
			if (cuentaCollectionTO!=null){
				solicitudEnviosTO.setColeccion_cuentas(cuentaCollectionTO);
			}
			
			if(speiResponseTO.getMessage()!=null){
				solicitudEnviosTO.setNota_aplicacion_spei("Le recordamos que las transferencias tipo SPEI dejan de operar a las 5:25 pm, por lo cual se realizará una operación programada para el siguiente día hábil.");
				solicitudEnviosTO.setOperacion_programada("true");
				session.addAttribute(IGNORAR_PROCESO_FRECUENTE, "true");
			}
			solicitudEnviosTO.setFecha_aplicacion(speiResponseTO.getFechaProgramacion());
			solicitudEnviosTO.setCurp(speiResponseTO.getCurp());
			session.addAttribute(CommandConstantes.TRANSFERENCIA_SPEI_RESPONSE, speiResponseTO);
			response.addAttribute(solicitudEnviosTO);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response validacion(Session session) throws Exception{
		Response response = new Response();		

		boolean transferenciaFrecuenteBloqueada=false;
		TransferenciaSpeiForm forma = (TransferenciaSpeiForm) getFormBean();	
		String fechaAplicacion = forma.getFecha_aplicacion();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		String procesoFrecuente=(String)session.getAttribute(IGNORAR_PROCESO_FRECUENTE);
		

		if(!new Boolean(procesoFrecuente)&& esHoy(fechaAplicacion)){
			
			Cuenta_FrecuenteTO cuenta_FrecuenteTO=buscaFrecuente(forma.getCuenta_destino(),session);
			
			if(cuenta_FrecuenteTO!=null && isSpeiNormalAM(cuenta_FrecuenteTO.getFecha_modificacion())){
				if(isSpeiNormalPM(cuenta_FrecuenteTO.getFecha_modificacion())){
					session.addAttribute(FRECUENTE_BLOQUEADA, cuenta_FrecuenteTO);
					session.addAttribute(HORA_ALTA_FRECUENTE, cuenta_FrecuenteTO.getFecha_modificacion());
					transferenciaFrecuenteBloqueada=true;
				}else{
					fechaAplicacion=sumaDiaSpei(cuenta_FrecuenteTO.getFecha_modificacion(),clienteTO.getAlias());
					
					if(fechaAplicacion==null){
						HashMap<String, String> errors = new HashMap<String, String>();
						errors.put("fecha_aplicacion","No se pudo programar su transferencia ya que no existe un siguiente dia hábil." );
						return returnErrorMap(response, errors);
					}
					
				}
			}
		}
			
					
		try{
			ResourceFacadeSL bean = getDelegate();
			
			TransferenciasSpeiRequestTO speiRequestTO = new TransferenciasSpeiRequestTO();
			TransferenciasSpeiResponseTO speiResponseTO  = (TransferenciasSpeiResponseTO)session.getAttribute(TRANSFERENCIA_SPEI_RESPONSE);

			speiRequestTO.setUser(clienteTO.getUserName());
			String cuenta=forma.getCuenta_cargo();
			if (cuenta.length()==14){
				String cuentaFormateada = Formatter.split4CharsTokens(cuenta);
//				cuenta=cuenta.substring(0,4)+" "+cuenta.substring(4,8)+" "+cuenta.substring(8,12)+" "+cuenta.substring(12,14);
				CuentaPredicate predicate1 = new CuentaPredicate();
				predicate1.setCuenta(cuentaFormateada);
				CuentaTransaccionesTO cuentaTransaccionesTO = (CuentaTransaccionesTO) CollectionUtils.find(speiResponseTO.getCuentas(), predicate1 );
				if (cuentaTransaccionesTO!=null)speiRequestTO.setOrigen(cuentaTransaccionesTO.getIndex());	
			}
			speiRequestTO.setRfcord(forma.getRfc_curp());
			speiRequestTO.setDestino(forma.getCuenta_destino());

			BancosSpeiPredicate predicate = new BancosSpeiPredicate();
			predicate.setBanco(forma.getBanco_destino());				
			BancoSpeiTO bancoSpeiTO = (BancoSpeiTO) CollectionUtils.find( speiResponseTO.getBancos(), predicate );
			
			if(bancoSpeiTO == null){
				Throwable e= new Throwable();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bancosSpei ", "Por favor ingrese un Banco valido");
				Object errorData = map;
				int level=1;
				throw  new EliteDataException(e,errorData,level);
			}
			speiRequestTO.setBancos(bancoSpeiTO.getIndex());
			
			if (forma.getTipo_cuenta_destino().equalsIgnoreCase("CLABE")){
				speiRequestTO.setTipo("0");
			}else if (forma.getTipo_cuenta_destino().equalsIgnoreCase("TARJETA")){
				speiRequestTO.setTipo("1");
			}
			speiRequestTO.setBeneficiario(forma.getBeneficiario());
			speiRequestTO.setImpuesto("");
			//speiRequestTO.setBenefIVA("1");//benefiva		//10 ?????????
			//speiRequestTO.setBenefRFC("");//????????????

			speiRequestTO.setImporte(forma.getImporte());
			speiRequestTO.setReferencia(forma.getReferencia());
			speiRequestTO.setCobranza(forma.getConcepto());
			

			
			
			if(!Validator.isEmptyData(fechaAplicacion)){
				if(Validator.checkFecha(fechaAplicacion)){
					HashMap<String, String> errores = new HashMap<String, String>();
					errores.put("fecha_aplicacion",Errors.TRANSFERENCIAS_SPEI_FECHA_APLICACION_REQUERIDA );
					returnErrorMap(response, errores);
					
				}else{
					speiRequestTO.setFechaProgramacion(fechaAplicacion);
//					speiRequestTO.setFechaProgramacion(forma.getFecha_aplicacion());
				}
				
			}else{
				speiRequestTO.setFechaProgramacion(speiResponseTO.getFechaProgramacion());
			}
			
			
			if("si".equalsIgnoreCase(forma.getDeducir_impuestos())){
				speiRequestTO.setImpuesto("1");
				speiRequestTO.setBenefRFC(forma.getRfc_beneficiario());
				speiRequestTO.setBenefIVA(forma.getIva_deducir());
			}else{
				speiRequestTO.setImpuesto("2");
			}
			
			speiRequestTO.setTipotefspeiStr("4");
			

			TransferenciasSpeiResponseTO responseTO  = bean.getTransferenciaSpeiEnvioDatos(speiRequestTO);
			
			speiResponseTO.setConfirmarTransferenciaSpeiTO(responseTO.getConfirmarTransferenciaSpeiTO());
			speiResponseTO.setDispositivoHuellaTO(responseTO.getDispositivoHuellaTO());

			HuellaTO huella=new HuellaTO();
			huella.setLlave_publica(responseTO.getDispositivoHuellaTO().getLlavePublica());
			huella.setLongitud_huella(responseTO.getDispositivoHuellaTO().getLongitudHuella());
			
	
			Transferencias_Spei_EjecucionTO transferenciaSpeiEjecucionTO = new Transferencias_Spei_EjecucionTO();
			cuenta = Formatter.formatSplittedCuenta(responseTO.getConfirmarTransferenciaSpeiTO().getNumCta()).replace(" ","");
//			transferenciaSpeiEjecucionTO.setComision(responseTO.getConfirmarTransferenciaSpeiTO().getComision());
//			transferenciaSpeiEjecucionTO.setConcepto(responseTO.getConfirmarTransferenciaSpeiTO().getCobranza());
//			transferenciaSpeiEjecucionTO.setCuenta_cargo(cuenta);
//			transferenciaSpeiEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getDestino());
//			transferenciaSpeiEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramacion());
//			transferenciaSpeiEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaSpeiTO().getFolio());
//			transferenciaSpeiEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaSpeiTO().getImportef());
//			transferenciaSpeiEjecucionTO.setIva(responseTO.getConfirmarTransferenciaSpeiTO().getIvacomision());
//			transferenciaSpeiEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferencia());
//			transferenciaSpeiEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaSpeiTO().getRfcord());
//			transferenciaSpeiEjecucionTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
//			transferenciaSpeiEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBeneficiario());
			
			transferenciaSpeiEjecucionTO.setTitular_cuenta_cargo(responseTO.getConfirmarTransferenciaSpeiTO().getNomord());
			transferenciaSpeiEjecucionTO.setCuenta_cargo(cuenta);
			transferenciaSpeiEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaSpeiTO().getImportef());
			transferenciaSpeiEjecucionTO.setComision(responseTO.getConfirmarTransferenciaSpeiTO().getComision());
			transferenciaSpeiEjecucionTO.setIva(responseTO.getConfirmarTransferenciaSpeiTO().getIvacomision());
			transferenciaSpeiEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferencia());
			transferenciaSpeiEjecucionTO.setConcepto(responseTO.getConfirmarTransferenciaSpeiTO().getCobranza());
			transferenciaSpeiEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaSpeiTO().getRfcord());
			transferenciaSpeiEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBeneficiario());
			transferenciaSpeiEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getDestino());
			transferenciaSpeiEjecucionTO.setBanco_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBankDesc());
			transferenciaSpeiEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramacion());
			transferenciaSpeiEjecucionTO.setFecha_operacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaOperation());
			transferenciaSpeiEjecucionTO.setHora_operacion(responseTO.getConfirmarTransferenciaSpeiTO().getHoraOperation());
			transferenciaSpeiEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaSpeiTO().getFolio());
			transferenciaSpeiEjecucionTO.setClave_rastreo(responseTO.getConfirmarTransferenciaSpeiTO().getNumMovimiento());
			transferenciaSpeiEjecucionTO.setNumero_referencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferenciaNum());
			transferenciaSpeiEjecucionTO.setOperacion_programada(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramada());
			
			if(transferenciaFrecuenteBloqueada){
				Cuenta_FrecuenteTO frecuente=(Cuenta_FrecuenteTO)session.getAttribute(FRECUENTE_BLOQUEADA);
				
				transferenciaSpeiEjecucionTO.setMensaje_frecuente_programada("Recuerde que la transferencia se efectuara a las: "+getHoraTraspaso(frecuente.getFecha_modificacion())+" aproximadamente.");
				speiRequestTO.setFrecuenteBloqueada(transferenciaFrecuenteBloqueada);
				session.addAttribute(DATOS_TRANSFERENCIAS_PETICION_SPEI, speiRequestTO);
				session.addAttribute(DATOS_TRANSFERENCIAS_RESPUESTA_SPEI, responseTO.getConfirmarTransferenciaSpeiTO());
			}
			
			response.addAttribute(transferenciaSpeiEjecucionTO);
			response.addAttribute(huella);			
		}catch(EliteDataException e){
				buildErrorResponse(e, response);
		}																		
		return response;	
	}
	

	public Response ejecucion(Session session) throws Exception, XmlDecodeException {
		Response response = new Response();	
		try{			
			TransferenciaSpeiForm forma = (TransferenciaSpeiForm) getFormBean();
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			TransferenciasSpeiRequestTO speiRequestTO = (TransferenciasSpeiRequestTO)session.getAttribute(DATOS_TRANSFERENCIAS_PETICION_SPEI);
			
			if(speiRequestTO==null){
				speiRequestTO=new TransferenciasSpeiRequestTO();
			}
			
			Transferencias_Spei_EjecucionTO transferenciaSpeiEjecucionTO = new Transferencias_Spei_EjecucionTO();//(Transferencias_Spei_EjecucionTO)session.getAttribute(CommandConstantes.TRANSFERENCIAS_SPEI_EJECUCION);
			speiRequestTO.setUser(clienteTO.getUserName());
//			speiRequestTO.setEmailDestino("");
			speiRequestTO.setClave("nada");
			speiRequestTO.setTokencode("nada");
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				speiRequestTO.setOptionDispositive(OPCION_HUELLA);
				speiRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				speiRequestTO.setOptionDispositive(OPCION_TOKEN);
				speiRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}
			
			TransferenciasSpeiResponseTO responseTO=null;
			
			
			Cuenta_FrecuenteTO frecuenteTO=(Cuenta_FrecuenteTO)session.getAttribute(FRECUENTE_BLOQUEADA);
			String fechaProgramacion=speiRequestTO.getFechaProgramacion();

			if(speiRequestTO.isFrecuenteBloqueada() && esHoy(fechaProgramacion) && frecuenteTO!=null && isContinuaBloqueada(frecuenteTO.getFecha_modificacion())){
				
				if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
					boolean huellaValida=false;
					
					huellaValida=verificaHuella(session, forma);
					if(!huellaValida){
						HashMap<String, String> errors = null;	
						errors = new HashMap<String, String>();
						errors.put("huella","Estimado usuario la huella es incorrecta.");
						return returnErrorMap(response, errors);
					}
					
				}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
					boolean tokenValido=false;
					tokenValido=validaToken(session, forma);
					if(!tokenValido){
						HashMap<String, String> errors = null;	
						errors = new HashMap<String, String>();
						errors.put("token","Estimado usuario, la clave de seguridad es incorrecta");
						return returnErrorMap(response, errors);
					}
				}
				
				
				ConfirmarTransferenciaSpeiTO speiTO  = (ConfirmarTransferenciaSpeiTO)session.getAttribute(DATOS_TRANSFERENCIAS_RESPUESTA_SPEI);
//				Cuenta_FrecuenteTO frecuenteTO=(Cuenta_FrecuenteTO)session.getAttribute(FRECUENTE_BLOQUEADA);
				SpeiProgramadoRequestTO speiRequest30TO=new SpeiProgramadoRequestTO();
				speiRequest30TO.setEjecucionTO(speiTO);
				speiRequest30TO.setRequestTO(speiRequestTO);
				speiRequest30TO.setFechaAplicacionAlnova(speiTO.getFechaProgramacion());
				speiRequest30TO.setHoraAplicacionFrecuente(frecuenteTO.getFecha_modificacion());
				speiRequest30TO.setIdUsuario(clienteTO.getNumero());
				speiRequest30TO.setIdEmail(clienteTO.getEmail());
				speiRequest30TO.setNivelSeguridad(clienteTO.getSecurityData().getSecLevelVO().getSecurityLevel());
				speiRequest30TO.setUser(clienteTO.getAlias());
				speiRequest30TO.setIpCliente(forma.getIp_cliente());
				
				responseTO  = bean.insertaSpei30(speiRequest30TO);
				
				session.addAttribute(DATOS_FINALES, speiRequest30TO);
				
				transferenciaSpeiEjecucionTO.setMensaje_frecuente_programada("Recuerde que la transferencia se efectuara a las: "+getHoraTraspaso(frecuenteTO.getFecha_modificacion())+" aproximadamente.");
			}else{
				/*  */
				responseTO  = bean.getEjecutarTransferenciaSpei(speiRequestTO);
				super.updateBalance(session);
			}
			
			
			
			String cuenta = Formatter.formatSplittedCuenta(responseTO.getConfirmarTransferenciaSpeiTO().getNumCta()).replace(" ","");
//			transferenciaSpeiEjecucionTO.setComision(responseTO.getConfirmarTransferenciaSpeiTO().getComision());
//			transferenciaSpeiEjecucionTO.setConcepto(responseTO.getConfirmarTransferenciaSpeiTO().getCobranza());
//			transferenciaSpeiEjecucionTO.setCuenta_cargo(cuenta);
//			transferenciaSpeiEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getDestino());
//			transferenciaSpeiEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramacion());
//			transferenciaSpeiEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaSpeiTO().getFolio());
//			transferenciaSpeiEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaSpeiTO().getImportef());
//			transferenciaSpeiEjecucionTO.setIva(responseTO.getConfirmarTransferenciaSpeiTO().getIvacomision());
//			transferenciaSpeiEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferencia());
//			transferenciaSpeiEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaSpeiTO().getRfcord());
//			transferenciaSpeiEjecucionTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
//			transferenciaSpeiEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBeneficiario());
			
			transferenciaSpeiEjecucionTO.setTitular_cuenta_cargo(responseTO.getConfirmarTransferenciaSpeiTO().getNomord());
			transferenciaSpeiEjecucionTO.setCuenta_cargo(cuenta);
			transferenciaSpeiEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaSpeiTO().getImportef());
			transferenciaSpeiEjecucionTO.setComision(responseTO.getConfirmarTransferenciaSpeiTO().getComision());
			transferenciaSpeiEjecucionTO.setIva(responseTO.getConfirmarTransferenciaSpeiTO().getIvacomision());
			transferenciaSpeiEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferencia());
			transferenciaSpeiEjecucionTO.setConcepto(responseTO.getConfirmarTransferenciaSpeiTO().getCobranza());
			transferenciaSpeiEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaSpeiTO().getRfcord());
			transferenciaSpeiEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBeneficiario());
			transferenciaSpeiEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaSpeiTO().getDestino());
			transferenciaSpeiEjecucionTO.setBanco_destino(responseTO.getConfirmarTransferenciaSpeiTO().getBankDesc());
			//transferenciaSpeiEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaAplicattion());
			transferenciaSpeiEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramacion());
			transferenciaSpeiEjecucionTO.setFecha_operacion(responseTO.getConfirmarTransferenciaSpeiTO().getFechaOperation());
			transferenciaSpeiEjecucionTO.setHora_operacion(responseTO.getConfirmarTransferenciaSpeiTO().getHoraOperation());
			
			
			transferenciaSpeiEjecucionTO.setOperacion_programada("false");
			if(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramada() != null ) {
				if(responseTO.getConfirmarTransferenciaSpeiTO().getFechaProgramada().equalsIgnoreCase("true") ) {
					transferenciaSpeiEjecucionTO.setOperacion_programada("true");
				}
			}
			transferenciaSpeiEjecucionTO.setClave_rastreo(responseTO.getConfirmarTransferenciaSpeiTO().getNumMovimiento());			
			transferenciaSpeiEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaSpeiTO().getFolio());
			transferenciaSpeiEjecucionTO.setNumero_referencia(responseTO.getConfirmarTransferenciaSpeiTO().getReferenciaNum());
			
			response.addAttribute(transferenciaSpeiEjecucionTO);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;									
	}

	private Cuenta_FrecuenteTO buscaFrecuente(String cuenta_destino,Session session) throws EJBException, EliteDataException, ServiceLocatorException, CuentasException, SessionExpiredException {
		ConsultaFrecuentesTransferenciasCommand frecuentesCommand=new ConsultaFrecuentesTransferenciasCommand();
		Collection<Cuenta_FrecuenteTO> coleccion_cuentas_frecuentes = frecuentesCommand.listaFrecuentes(session, "SPEI").getColeccion_cuentas_frecuentes();
		for (Cuenta_FrecuenteTO cuenta_FrecuenteTO : coleccion_cuentas_frecuentes) {
			if(cuenta_FrecuenteTO.getNumero_cuenta().equalsIgnoreCase(cuenta_destino)&& "bloqueada".equalsIgnoreCase(cuenta_FrecuenteTO.getEstado())){
				return cuenta_FrecuenteTO;
			}
		}
		return null;
	}
	
	private boolean validaToken(Session session, TransferenciaSpeiForm forma) throws Exception, XmlDecodeException {
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		ValidaTokenForm tokenForm=new ValidaTokenForm();
		tokenForm.setToken_code(forma.getClave_seguridad());
		tokenForm.setUser(clienteTO.getAlias());
		ValidaTokenCommand command=new ValidaTokenCommand();
		command.setFormBean(tokenForm);
		Response responseToken=command.ejecutar(session);
		
		Collection<?> cl=responseToken.getDataService();
		Iterator<?> it=cl.iterator();
		
		Token_ValidacionTO tokenValidation =null;
		
		while (it.hasNext()) {
			Object object = (Object) it.next();
			
			if (object instanceof Token_ValidacionTO) {
				tokenValidation = (Token_ValidacionTO) object;
				break;
			}
		}
		
		String valido=tokenValidation.getValido();
		
		return new Boolean(valido).booleanValue();
	}

	private boolean verificaHuella(Session session, TransferenciaSpeiForm forma)
			throws Exception {
		ValidaHuellaForm huellaforma = new ValidaHuellaForm();
		huellaforma.setHuella_seguridad(forma.getHuella_seguridad());
		ValidaHuellaCommand comando=new ValidaHuellaCommand();
		comando.setFormBean(huellaforma);
		Response validador=comando.ejecucion(session);
		Collection<?> dt=validador.getDataService();
		Iterator<?> it=dt.iterator();
		Validacion_huellaTO validacionHuella=null;
		while (it.hasNext()) {
			Object object = (Object) it.next();
			if (object instanceof Validacion_huellaTO) {
				validacionHuella = (Validacion_huellaTO) object;
				return validacionHuella.isHuella_valida();
			}
		}
		return false;
	}


	private boolean esHoy(String fechaAplicacion) {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		
		Date hoyDate=calendar.getTime();
		
		String hoy=dateFormat.format(hoyDate);
		
		if(hoy.equalsIgnoreCase(fechaAplicacion)){
			return true;
		}
		return false;
	}
	
	
	private boolean isSpeiNormalAM(String fechaFrecuente){
		boolean today=false;
		int horas_madrugada=0;
		int minutos_madrugada=0;
		int minutos_bloqueo=0; 
		try {			
			horas_madrugada=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.horas.madrugada"));
			minutos_madrugada=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.madrugada"));
			minutos_bloqueo=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.bloqueo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int spei = comparaHoras(fechaFrecuente, horas_madrugada, minutos_madrugada, minutos_bloqueo);
		
		if(0>spei){
			System.out.println("La frecuente se activara despues de las "+horas_madrugada+":"+minutos_madrugada+" Spei viable");
			today=true;
		}else if(spei>=0){
			System.out.println("La frecuente se activara antes de las "+horas_madrugada+":"+minutos_madrugada+" Spei inviable");
			today=false;
		}
		return today;
	}

	
	private boolean isSpeiNormalPM(String fechaFrecuente){
		boolean today=false;
		int horas_tarde=0;
		int minutos_tarde=0;
		int minutos_bloqueo=0; 
		try {			
			horas_tarde=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.horas.tarde"));
			minutos_tarde=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.tarde"));
			minutos_bloqueo=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.bloqueo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int spei = comparaHoras(fechaFrecuente, horas_tarde, minutos_tarde, minutos_bloqueo);
		
		if(0>spei){
			System.out.println("La frecuente se activara despues de las "+horas_tarde+":"+minutos_tarde+" Spei inviable");
			today=false;
		}else if(spei>=0){
			System.out.println("La frecuente se activara antes de las "+horas_tarde+":"+minutos_tarde+" Spei viable");
			today=true;
		}
		return today;
		
	}
	
	private int comparaHoras(String fechaFrecuente, int horas, int minutos, int minutos_bloqueo) {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, horas);
		calendar.set(Calendar.MINUTE, minutos);
		Date limiteSpei=calendar.getTime();
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);
		Date frecuente=null;
		try {
			frecuente = simpleDateFormat.parse(fechaFrecuente);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar calendarFrecuente=Calendar.getInstance();
		calendarFrecuente.setTime(frecuente);
		calendarFrecuente.add(Calendar.MINUTE, minutos_bloqueo);
		
		frecuente=calendarFrecuente.getTime();
		
		System.out.println("Limite SPEI:  "+simpleDateFormat.format(limiteSpei));
		System.out.println("Hora de frec: "+simpleDateFormat.format(frecuente));
		
		int spei=limiteSpei.compareTo(frecuente);
		return spei;
	}
	
	private boolean isDiaFestivo(Calendar fechaSpei){
		PropertiesService propertiesService=PropertiesService.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE_PROPERTIES);
		boolean isDiaFestivo=false;
		
		try {
			String listaDias=propertiesService.getPropertie("DiasInhabiles.properties", "eservices.spei.dias.inhabiles");
			String fechaSpeiDate=simpleDateFormat.format(fechaSpei.getTime());
			StringTokenizer token=new StringTokenizer(listaDias,",");
			ciclo:while(token.hasMoreTokens()){
					String fechaInhabil=token.nextToken();
					if(fechaSpeiDate.equalsIgnoreCase(fechaInhabil)){
						isDiaFestivo=true;
						System.out.println("El dia elegido fue festivo");
						break ciclo;
					}
					
				}
			if(!isDiaFestivo){
				System.out.println("EL dia no fue festivo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDiaFestivo;
	}
	
	
	private String sumaDiaSpei(String fechaSpei,String usuario) throws Exception {
		String fechaProgramada=null;
		Date spei=null;
		boolean isDiaFestivo=false;
		int numeroIntentos=0;
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);

		try {
			spei=simpleDateFormat.parse(fechaSpei);
		} catch (ParseException e) {}
		simpleDateFormat.applyPattern(CuentasFrecuentesTO.FORMAT_DATE_PROPERTIES);
		fechaSpei=simpleDateFormat.format(spei);
		ResourceFacadeSL facade=getDelegate();
		String codigoTerminalAlnova=null;
		try{
			
			TerminalAlnovaResponseTO obtieneTerminalAlnova = facade.obtieneTerminalAlnova(usuario);
			codigoTerminalAlnova=obtieneTerminalAlnova.getCodigoTerminalAlnova();
//			codigoTerminalAlnova="RV45";
			
			do{
				isDiaFestivo=false;
				
				F752ObtieneFechaHabilRequestTO requestTO= new F752ObtieneFechaHabilRequestTO();
				requestTO.setFechaEntrada(fechaSpei);
				requestTO.setNumeroDiasCalcular(Integer.toString(1));
				requestTO.setIdAplicacion("app_banca_elite");
				requestTO.setConfiguracionTerminal(codigoTerminalAlnova);
				requestTO.setIdtransaccion("F752");
				requestTO.setDescripcionTransaccion("ObtieneFechaHabil");
				F752ObtieneFechaHabilResponseTO responseTO=(F752ObtieneFechaHabilResponseTO)getDelegateSegundo().ejecutaTransaccionAlnova(requestTO);

				String fechaSalida=responseTO.getFechaSalida();
				Date fechaAlnova=simpleDateFormat.parse(fechaSalida);
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(fechaAlnova);
				
				isDiaFestivo=isDiaFestivo(calendar);
				
				if(isDiaFestivo){
					fechaSpei=fechaSalida;
				}else{
					fechaProgramada=fechaSalida;
				}
				++numeroIntentos;
			}while(isDiaFestivo && numeroIntentos< 10);
			
			if(numeroIntentos>=10){
				fechaProgramada=null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(codigoTerminalAlnova!=null && !"terminal no disponible".equalsIgnoreCase(codigoTerminalAlnova)){
				System.out.println("Liberando la terminal: "+codigoTerminalAlnova);
				facade.liberaTerminalAlnova(usuario, codigoTerminalAlnova);
			}
		}	
		
		if(fechaProgramada!=null){
			simpleDateFormat.applyPattern("yyyy-MM-dd");
			Date nuevaFecha=simpleDateFormat.parse(fechaProgramada);
			simpleDateFormat.applyPattern("dd-MM-yyyy");
			fechaProgramada=simpleDateFormat.format(nuevaFecha);
		}
		
		return fechaProgramada;
	}
	
	/*private String sumaDiaSpei(String fechaSpei){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);
		Date spei=null;
		String fechaProgramada=null;
		try {
			
			
			spei=simpleDateFormat.parse(fechaSpei);
			System.out.println("Fecha de SPEI antes: "+simpleDateFormat.format(spei));
			
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(spei);
			
			System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
			int dias=1;
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
				dias=3;
			}
			
			calendar.add(Calendar.DAY_OF_MONTH, dias);
			
			calendar=calculaDiaFestivo(calendar);
			
			
			spei=calendar.getTime();
			
			System.out.println("Fecha de SPEI despues: "+simpleDateFormat.format(spei));
			
			fechaProgramada=simpleDateFormat.format(spei);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaProgramada;
	}*/
	
	
	private String getHoraTraspaso(String fechaFrecuente){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);
		
		int tiempo_traspaso=0;
		
		try {
			tiempo_traspaso=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.traspaso.estimado"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Date fecha=null;
		try {
			fecha = simpleDateFormat.parse(fechaFrecuente);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MINUTE, tiempo_traspaso);
		fecha=calendar.getTime();
		simpleDateFormat.applyPattern("HH:mm");
		String hora=simpleDateFormat.format(fecha);
		return hora;
	}
	
	private boolean isContinuaBloqueada(String horaAplicacion){
		boolean isBloqueada=false;
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);
		
		int tiempo_traspaso=0;
		
		try {
			tiempo_traspaso=Integer.parseInt(PropertiesService.getInstance().getPropertie("Spei30.properties", "spei.30.minutos.bloqueo"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			tiempo_traspaso=30;
		} catch (IOException e1) {
			e1.printStackTrace();
			tiempo_traspaso=30;
		} catch(Exception e){
			e.printStackTrace();
			tiempo_traspaso=30;
		} 
		
		Date fecha=null;
		try {
			fecha = simpleDateFormat.parse(horaAplicacion);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date actual=Calendar.getInstance().getTime();
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MINUTE, tiempo_traspaso);
		fecha=calendar.getTime();
		
		int comparacion=fecha.compareTo(actual);
		
		if(comparacion>0){
			isBloqueada=true;
		}else{
			isBloqueada=false;
		}
		
		return isBloqueada;
	}
	
	private class CuentaPredicate implements Predicate {

		private String cuenta;

		public boolean evaluate(Object obj) {
			CuentaTransaccionesTO cuentaTransaccionesTO=(CuentaTransaccionesTO) obj;
			String temp=cuentaTransaccionesTO.getCuentaFormateada();
			if (temp.equals(cuenta)) {
				return true;
			} else {
				return false;
			}		
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}	

	private class BancosSpeiPredicate implements Predicate {
		private String banco;
		public boolean evaluate(Object obj) {
			BancoSpeiTO bancoSpeiTO = (BancoSpeiTO) obj;
				if (bancoSpeiTO.getDescription().equalsIgnoreCase(banco)) {
					return true;
				} else {
					return false;
				}				
		}
		public String getBanco() {
			return banco;
		}
		public void setBanco(String banco) {
			this.banco = banco;
		}
	}
	
	
	
	
}