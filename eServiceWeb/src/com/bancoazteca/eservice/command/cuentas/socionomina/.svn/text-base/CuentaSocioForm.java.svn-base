package com.bancoazteca.eservice.command.cuentas.socionomina;

import java.util.Collection;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.validator.MessageErrors;
/**
 * La clase CuentaSocioForm es el que se encarga de obtener la información 
 * que necesitara el comando para realizar la apertura de cuentas socio nomina.<br/>
 * Cuenta con un método ‘validate’ que es el encargado de realizar las validaciones de los datos ingresados en la forma.
 */
public class CuentaSocioForm extends FormBean {

	private static final long serialVersionUID = -1187145968813704365L;

	private String cuenta_origen;
	private String monto;
	private Collection<BeneficiarioTO> coleccion_beneficiarios;
	
	private String nip;
	private String nip_confirmar;

	private String clave_seguridad;
	private String opcion_seguridad;
	private String huella_seguridad;


	public MessageErrors validate() {

		MessageErrors errors = new MessageErrors();

		if (getComando().equalsIgnoreCase("validacion")) {
			errors = validateEnvioDatosCtaPlata(errors);	
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
					errors.add("clave_seguridad","error.device.token");
				}else if (!Validator.checkNumeric(clave_seguridad)){
					errors.add("clave_seguridad","error.device.token.character");
				}
			} else {
				errors.add("opcion_seguridad","error.security.devices.optionDispositive.empty");
			}
		}
		return errors;
	}

	private MessageErrors validateEnvioDatosCtaPlata(MessageErrors errors) {

		if(cuenta_origen.equals("-1") || Validator.isEmptyData(cuenta_origen)){
			errors.add("cuenta_argo","error.account.partner.plata.account");
		}


		int porcentaje=0;
		String num="";

		if(coleccion_beneficiarios!=null && !coleccion_beneficiarios.isEmpty()){

			for(BeneficiarioTO beneficiario:coleccion_beneficiarios){
				num=beneficiario.getPorcentaje();
				if(Validator.checkNumeric(num)){
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


		if(Validator.isEmptyData(monto) || !Validator.checkNumeric(monto)){
			errors.add("monto","error.account.partner.plata.amount");
		}else if (!Validator.checkDecimal(monto)) {
			errors.add("monto","error.account.partner.plata.amount");
		}else if (Double.parseDouble(monto)<=0) {
			errors.add("monto","error.account.partner.plata.zero");
		}else if (Double.parseDouble(monto)<30) {
			errors.add("monto","error.account.partner.plata.account.amount");
		}
		
		if(Validator.isEmptyData(Integer.toString(porcentaje)) || porcentaje!=100){
			errors.add("porcentaje","error.account.partner.plata.porcentaje");
		}

		if(nip.equals(nip_confirmar)){
			if(nip.length()==4){
				if(!Validator.checkNumeric(nip)){
					errors.add("nip","error.account.partner.nipnumeric");
				}
			}else{
				errors.add("nip","error.account.partner.niplength");
			}
		}else{
			errors.add("nip","error.account.partner.unequalnip");
		}
		
		return errors;
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
	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	public String getCuenta_origen() {
		return cuenta_origen;
	}
	public void setCuenta_origen(String cuentaOrigen) {
		this.cuenta_origen = cuentaOrigen;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getNip_confirmar() {
		return nip_confirmar;
	}
	public void setNip_confirmar(String nipConfirmar) {
		this.nip_confirmar = nipConfirmar;
	}
	public Collection<BeneficiarioTO> getColeccion_beneficiarios() {
		return coleccion_beneficiarios;
	}
	public void setColeccion_beneficiarios(
			Collection<BeneficiarioTO> coleccion_beneficiarios) {
		this.coleccion_beneficiarios = coleccion_beneficiarios;
	}
}