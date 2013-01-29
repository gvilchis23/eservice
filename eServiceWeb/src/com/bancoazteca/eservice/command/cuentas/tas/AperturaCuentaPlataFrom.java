package com.bancoazteca.eservice.command.cuentas.tas;

import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.validator.MessageErrors;

public class AperturaCuentaPlataFrom extends FormBean {

	private static final Logger log= Logger.getLogger(AperturaCuentaPlataFrom.class);
	private String aceptar_contrato;
	private String cuenta_cargo;
	private String monto_apertura;
	private Collection<BeneficiarioTO> coleccion_beneficiarios;
	private Cipher nip_cuenta;
	private String clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;
	
	public void reset(){
		aceptar_contrato=null;
		cuenta_cargo=null;
		monto_apertura=null;
		nip_cuenta=null;
		clave_seguridad=null;
		opcion_seguridad=null;
	}


	public MessageErrors validate() {
		
		MessageErrors errors = new MessageErrors();

		if (getComando().equalsIgnoreCase("validacion")) {
			errors = validateEnvioDatosCtaPlata(errors);
			if(!errors.isErrors())
				errors = validateEnvioDatosCtaPlataCotizacion(errors);
			if(!errors.isErrors())	
				errors = validateContratosCtaPlata(errors);
		}

		if (getComando().equals("ejecucion")){
			if(getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if (Validator.isEmptyData(huella_seguridad)){			
					errors.add("huella_seguridad","error.device.fingerPrint.empty");
				}
			}else if(getOpcion_seguridad().equalsIgnoreCase(CommandBase.TAG_TOKEN)) {
				if (Validator.isEmptyData(clave_seguridad)){
					errors.add("clave_seguridad","error.device.token.empty");
				}else if (clave_seguridad.length()!=6){
					errors.add("clave_seguridad","error.device.token.size");
				}else if (!Validator.checkNumeric(clave_seguridad)){
					errors.add("clave_seguridad","error.device.token.character");
				}
			} else {
				errors.add("opcion_seguridad","error.security.devices.optionDispositive.empty");
			}
		}
		return errors;
	}

	private MessageErrors validateContratosCtaPlata(MessageErrors errors) {
		if(aceptar_contrato == null || !aceptar_contrato.equalsIgnoreCase("si")){
			errors.add("aceptar_contrato","error.account.partner.plata.aggre");
		}
		return errors;
	}

	private MessageErrors validateEnvioDatosCtaPlata(MessageErrors errors) {

		if(cuenta_cargo.equals("-1") || Validator.isEmptyData(cuenta_cargo)){
			errors.add("cuenta_argo","error.account.partner.plata.account");
		}
	
		
		int porcentaje=0;
		String num="";
		
		log.info("coleccion_ben "+coleccion_beneficiarios);
		
		if(coleccion_beneficiarios!=null && coleccion_beneficiarios.size()>=1){
		
			
			for(BeneficiarioTO beneficiario:coleccion_beneficiarios){

				num=beneficiario.getPorcentaje();
				
				if(Validator.checkNumeric(num))
				{
					porcentaje+=Integer.parseInt(num);
				}
				
				if(Validator.isEmptyData(beneficiario.getNombre_beneficiario())){
					errors.add("beneficiariosTO","error.account.partner.plata.people");
				}
				if(Validator.isEmptyData(beneficiario.getPrimer_apellido())){
					errors.add("beneficiariosTO","error.account.partner.plata.people");
				}
				if(Validator.isEmptyData(beneficiario.getSegundo_apellido())){
					errors.add("beneficiariosTO","error.account.partner.plata.people");
				}
			}
		}else errors.add("beneficiariosTO","error.account.partner.plata.people.missing");


		if(nip_cuenta==null || Validator.isEmptyData(nip_cuenta.toString())){
			errors.add("nip_cuenta","error.account.partner.plata.nip");
		}
		if(Validator.isEmptyData(monto_apertura) || !Validator.checkNumeric(monto_apertura)){
			errors.add("monto_apertura","error.account.partner.plata.amount");
		}else if (!Validator.checkDecimal(monto_apertura)) {
			errors.add("monto_apertura","error.account.partner.plata.amount");
		}else if (Double.parseDouble(monto_apertura)<=0) {
			errors.add("monto_apertura","error.account.partner.plata.zero");
		}else if (Double.parseDouble(monto_apertura)<30) {
			errors.add("monto_apertura","error.account.partner.plata.account.amount");
		}
		
		log.info("porcentaje: "+porcentaje);
		if(Validator.isEmptyData(Integer.toString(porcentaje)) || porcentaje!=100){
			errors.add("porcentaje","error.account.partner.plata.porcentaje");
		}
		
		return errors;
	}

	private MessageErrors validateEnvioDatosCtaPlataCotizacion(MessageErrors errors) {
		if(cuenta_cargo.equals("-1") || Validator.isEmptyData(cuenta_cargo)){
			errors.add("cuenta_cargo","error.account.partner.plata.account");
		}
		if(Validator.isEmptyData(monto_apertura) || !Validator.checkNumeric(monto_apertura)){
			errors.add("monto_apertura", "error.account.partner.plata.amount");
		}else if (!Validator.checkDecimal(monto_apertura)) {
			errors.add("monto_apertura","error.account.partner.plata.amount");
		}else if (Double.parseDouble(monto_apertura)<=0) {
			errors.add("monto_apertura","error.account.partner.plata.zero");
		}else if (Double.parseDouble(monto_apertura)<30) {
			errors.add("monto_apertura","error.account.partner.plata.account.amount");
		}
		return errors;
	}


	public String getAceptar_contrato() {
		return aceptar_contrato;
	}


	public void setAceptar_contrato(String aceptar_contrato) {
		this.aceptar_contrato = aceptar_contrato;
	}


	public String getCuenta_cargo() {
		return cuenta_cargo;
	}


	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}


	public String getMonto_apertura() {
		return monto_apertura;
	}


	public void setMonto_apertura(String monto_apertura) {
		this.monto_apertura = monto_apertura;
	}

	public Cipher getNip_cuenta() {
		return nip_cuenta;
	}


	public void setNip_cuenta(Cipher nip_cuenta) {
		this.nip_cuenta = nip_cuenta;
	}


	public String getClave_seguridad() {
		return clave_seguridad;
	}


	public void setClave_seguridad(String clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}


	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}


	public void setOpcion_seguridad(String opcion_seguridad) {
		this.opcion_seguridad = opcion_seguridad;
	}


	public Collection<BeneficiarioTO> getColeccion_beneficiarios() {
		return coleccion_beneficiarios;
	}


	public void setColeccion_beneficiarios(
			Collection<BeneficiarioTO> coleccion_beneficiarios) {
		this.coleccion_beneficiarios = coleccion_beneficiarios;
	}


	public String getHuella_seguridad() {
		return huella_seguridad;
	}


	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
}