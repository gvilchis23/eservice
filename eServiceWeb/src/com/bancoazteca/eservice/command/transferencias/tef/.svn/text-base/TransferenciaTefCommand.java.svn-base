package com.bancoazteca.eservice.command.transferencias.tef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.BancoTefTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.TransferenciasTEFRequestTO;
import com.bancoazteca.elite.beans.TransferenciasTEFResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Envio_DineroTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.Transferencias_TEF_EjecucionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class TransferenciaTefCommand  extends CommandBase{

	public Response solicitud(Session session) throws Exception {
		Response response = new Response();		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ResourceFacadeSL bean = getDelegate();

		TransferenciasTEFRequestTO tefRequestTO = new TransferenciasTEFRequestTO();
		TransferenciasTEFResponseTO tefResponseTO = new TransferenciasTEFResponseTO();						

		tefRequestTO.setUser(clienteTO.getUserName());
		try{
			tefResponseTO = bean.getTransferenciaTefInvocacion(tefRequestTO);
				
			Envio_DineroTO solicitudEnviosTO= new Envio_DineroTO();
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			for(CuentaTransaccionesTO cuentaTransaccionesTO: tefResponseTO.getCuentasTef()){
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				String cuenta=cuentaTransaccionesTO.getNumero();
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
			
			solicitudEnviosTO.setFecha_aplicacion(tefResponseTO.getFechaprogramacion());
			
			session.addAttribute(CommandConstantes.TRANSFERENCIA_TEF_RESPONSE, tefResponseTO);
			response.addAttribute(solicitudEnviosTO);

		}catch(EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS || e.getLevel() == EliteRules.LEVEL_PATHS) {				
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();		
		TransferenciaTefForm forma = (TransferenciaTefForm) getFormBean();			
			try{
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			TransferenciasTEFRequestTO tefRequestTO = new TransferenciasTEFRequestTO();
			
			
			
			TransferenciasTEFResponseTO tefResponseTO  = (TransferenciasTEFResponseTO)session.getAttribute(TRANSFERENCIA_TEF_RESPONSE);

			tefRequestTO.setUser(clienteTO.getUserName());
			String cuenta=forma.getCuenta_cargo();
			if (cuenta.length()==14){
				cuenta=cuenta.substring(0,4)+" "+cuenta.substring(4,8)+" "+cuenta.substring(8,12)+" "+cuenta.substring(12,14);
				CuentaPredicate predicate1 = new CuentaPredicate();
				predicate1.setCuenta(cuenta);
				CuentaTransaccionesTO cuentaTransaccionesTO = (CuentaTransaccionesTO) CollectionUtils.find(tefResponseTO.getCuentasTef(), predicate1 );
				if (cuentaTransaccionesTO!=null)tefRequestTO.setOrigen(cuentaTransaccionesTO.getIndex());	
			}
			//tefRequestTO.set.setRfcord(forma.getRfc_curp());
			tefRequestTO.setDestino(forma.getCuenta_destino());

			BancosTEFPredicate predicate = new BancosTEFPredicate();
			predicate.setBanco(forma.getBanco_destino());				
			BancoTefTO bancoTefTO = (BancoTefTO) CollectionUtils.find( tefResponseTO.getBancosTef(), predicate );

			tefRequestTO.setBanco(bancoTefTO.getIndex());
			if (forma.getTipo_cuenta_destino().equalsIgnoreCase("CLABE")){
				tefRequestTO.setTipo("0");
			}else if (forma.getTipo_cuenta_destino().equalsIgnoreCase("TARJETA")){
				tefRequestTO.setTipo("2");
			}
			tefRequestTO.setBeneficiario(forma.getBeneficiario());
			//tefRequestTO.setImpuesto("");
			//tefRequestTO.setBenefIVA("1");//benefiva		//10 ?????????
			//tefRequestTO.setBenefRFC("");//????????????

			tefRequestTO.setImporte(forma.getImporte());
			tefRequestTO.setReferencia(forma.getReferencia());
			//tefRequestTO.set.setCobranza(forma.getConcepto());
			tefRequestTO.setFechaProgramacion(tefResponseTO.getFechaprogramacion());
			//tefRequestTO.setTipo("2");
			//tefRequestTO.setImpuesto("2");
			tefRequestTO.setTiporealStr("4");//???????????????
			TransferenciasTEFResponseTO responseTO  = bean.getTransferenciaTefEnvioDatos(tefRequestTO);
			
			tefResponseTO.setConfirmarTransferenciaTefTO(responseTO.getConfirmarTransferenciaTefTO());
			tefResponseTO.setDispositivoHuellaTO(responseTO.getDispositivoHuellaTO());
			
			Transferencias_TEF_EjecucionTO transferenciaTEFEjecucionTO = new Transferencias_TEF_EjecucionTO();
			
			transferenciaTEFEjecucionTO.setComision(responseTO.getConfirmarTransferenciaTefTO().getComision().toString());
			
			transferenciaTEFEjecucionTO.setCuenta_cargo(cuenta);
			transferenciaTEFEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaTefTO().getDestino());
			transferenciaTEFEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaTefTO().getFechaProgramacion());
			transferenciaTEFEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaTefTO().getFolio());
			transferenciaTEFEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaTefTO().getImporte().toString());

//			transferenciaTEFEjecucionTO.setIva(responseTO.getConfirmarTransferenciaTefTO().getIva());
			transferenciaTEFEjecucionTO.setIva(responseTO.getConfirmarTransferenciaTefTO().getIvacomision().toString());
			
			transferenciaTEFEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaTefTO().getReferencia());
			transferenciaTEFEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaTefTO().getRfc());
			transferenciaTEFEjecucionTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			transferenciaTEFEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaTefTO().getBeneficiario());
			
			response.addAttribute(transferenciaTEFEjecucionTO);
			session.addAttribute(CommandConstantes.TRANSFERENCIA_TEF_RESPONSE, tefResponseTO);	
			

			HuellaTO huella=new HuellaTO();
			huella.setLlave_publica(responseTO.getDispositivoHuellaTO().getLlavePublica());
			huella.setLongitud_huella(responseTO.getDispositivoHuellaTO().getLongitudHuella());

			response.addAttribute(huella);			
		}catch(EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS || e.getLevel() == EliteRules.LEVEL_PATHS) {				
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}																		
		return response;	
	}

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();	
		try{			
			TransferenciaTefForm forma = (TransferenciaTefForm) getFormBean();
			ResourceFacadeSL bean = getDelegate();
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			TransferenciasTEFRequestTO tefRequestTO = new TransferenciasTEFRequestTO();
			Transferencias_TEF_EjecucionTO transferenciaTEFEjecucionTO = new Transferencias_TEF_EjecucionTO();
			tefRequestTO.setUser(clienteTO.getUserName());
			tefRequestTO.setEmailDestino("");
			tefRequestTO.setClave("nada");
			tefRequestTO.setTokencode("nada");
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				tefRequestTO.setOptionDispositive(OPCION_HUELLA);
				tefRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				tefRequestTO.setOptionDispositive(OPCION_TOKEN);
				tefRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}

			TransferenciasTEFResponseTO responseTO  = bean.getEjecutarTransferenciaTef(tefRequestTO);
			super.updateBalance(session);
			String cuenta = Formatter.formatSplittedCuenta(responseTO.getConfirmarTransferenciaTefTO().getCuentaCargo());
			transferenciaTEFEjecucionTO.setComision(responseTO.getConfirmarTransferenciaTefTO().getComision().toString());
			//transferenciaTEFEjecucionTO.setConcepto(responseTO.getConfirmarTransferenciaTefTO().getCobranza());
			transferenciaTEFEjecucionTO.setCuenta_cargo(cuenta);
			transferenciaTEFEjecucionTO.setCuenta_destino(responseTO.getConfirmarTransferenciaTefTO().getDestino());
//			transferenciaTEFEjecucionTO.setFecha_aplicacion(responseTO.getConfirmarTransferenciaTefTO().getFechaProgramacion());
			transferenciaTEFEjecucionTO.setFecha_aplicacion(Formatter.formatDate(responseTO.getConfirmarTransferenciaTefTO().getFechaProgramacion()));
			transferenciaTEFEjecucionTO.setFolio_aclaracion(responseTO.getConfirmarTransferenciaTefTO().getFolio());
			transferenciaTEFEjecucionTO.setImporte(responseTO.getConfirmarTransferenciaTefTO().getImporte().toString());
			transferenciaTEFEjecucionTO.setIva(String.valueOf(responseTO.getConfirmarTransferenciaTefTO().getIvacomision()));
			transferenciaTEFEjecucionTO.setReferencia(responseTO.getConfirmarTransferenciaTefTO().getReferencia());
			transferenciaTEFEjecucionTO.setRfc_curp(responseTO.getConfirmarTransferenciaTefTO().getRfc());
			transferenciaTEFEjecucionTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			transferenciaTEFEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTransferenciaTefTO().getBeneficiario());
			transferenciaTEFEjecucionTO.setClave_rastreo(responseTO.getConfirmarTransferenciaTefTO().getClave());
//			transferenciaTEFEjecucionTO.setFecha_operacion(responseTO.getConfirmarTransferenciaTefTO().getOperation());
			transferenciaTEFEjecucionTO.setFecha_operacion(Formatter.formatDate(responseTO.getConfirmarTransferenciaTefTO().getOperation()));
			
			response.addAttribute(transferenciaTEFEjecucionTO);

		}catch(EliteDataException e){
			if (e.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {				
				MensajesTO mensajesTO = buildMessages((Map)e.getErrorData());
				response.addAttribute(mensajesTO);
				response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
			}
		}
		return response;									
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

	private class BancosTEFPredicate implements Predicate {
		private String banco;
		public boolean evaluate(Object obj) {
			BancoTefTO bancoTefTO = (BancoTefTO) obj;
				if (bancoTefTO.getDescripcion().equalsIgnoreCase(banco)) {
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
