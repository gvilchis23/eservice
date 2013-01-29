package com.bancoazteca.eservice.command.cuentas.socioplus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.validator.MessageErrors;
/**
 * La clase CuentaSocioPlusForm es el que se encarga de obtener la información 
 * que necesitara el comando para realizar la apertura de cuentas socio plus.<br/>
 * Cuenta con un método ‘validate’ que es el encargado de realizar las validaciones de los datos ingresados en la forma.
 */
public class CuentaSocioPlusForm extends FormBean{


	
	private static final long serialVersionUID = 5635886137329209077L;
	private static final byte NUMERO_MAXIMO_BENEFICIARIOS=5;
	
	private String acepta_terminos;
	private String acepta_terminos_finales;
	private String cuenta_cargo;	
	private String monto_apertura;
	
	private Collection<BeneficiarioTO> coleccion_beneficiarios=new ArrayList<BeneficiarioTO>(NUMERO_MAXIMO_BENEFICIARIOS);
	
	private String huella_seguridad;
	private Cipher clave_seguridad;
	private String opcion_seguridad;

	public MessageErrors validate(){
		MessageErrors errors = new MessageErrors();
		
		if(getComando().equalsIgnoreCase("validacion")){
			if ( cuenta_cargo.equals("-1") ||  Validator.isEmptyData( cuenta_cargo) ){
				errors.add("cuenta_cargo",VALIDATOR_CUENTA_CARGO_EMPTY);
			}
			if ( Validator.isEmptyData(monto_apertura)){
				errors.add("monto_apertura",VALIDATOR_ACCOUNT_PARTNER_PLUS_AMOUNT);
			}else if( !Validator.checkDecimal( monto_apertura )  ){
				errors.add("monto_apertura",VALIDATOR_ACCOUNT_PARTNER_PLUS_AMOUNT_DECIMAL);
			} else if ( Double.parseDouble( monto_apertura ) <= 0){
				errors.add("monto_apertura",VALIDATOR_ACCOUNT_PARTNER_PLUS_ZERO);
			}
				
		}else if(getComando().equalsIgnoreCase("ejecucion")){
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errors.add("clave_seguridad",CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errors.add("huella_seguridad",HUELLA_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad",OPCION_SEGURIDAD);
			}
		}
		
		return errors;
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

	public String getHuella_seguridad() {
		return huella_seguridad;
	}
	public void setHuella_seguridad(String huella_seguridad) {
		this.huella_seguridad = huella_seguridad;
	}
	public Cipher getClave_seguridad() {
		return clave_seguridad;
	}
	public void setClave_seguridad(Cipher clave_seguridad) {
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

	public String getAcepta_terminos() {
		return acepta_terminos;
	}

	public void setAcepta_terminos(String acepta_terminos) {
		this.acepta_terminos = acepta_terminos;
	}

	public String getAcepta_terminos_finales() {
		return acepta_terminos_finales;
	}

	public void setAcepta_terminos_finales(String acepta_terminos_finales) {
		this.acepta_terminos_finales = acepta_terminos_finales;
	}

}
