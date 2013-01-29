package com.bancoazteca.eservice.command.cuentas.ejeelite;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.validator.MessageErrors;
/**
 * La clase CuentaEjeEliteForm es el que se encarga de obtener la información 
 * que necesitara el comando para realizar la apertura de cuentas eje elite.<br/>
 * Cuenta con un método ‘validate’ que es el encargado de realizar las validaciones de los datos ingresados en la forma.
 */
public class CuentaEjeEliteForm extends FormBean {
	private static final int NUMERO_MAXIMO_BENEFICIARIOS=4;
	
	private String contrato;
	private String cuenta_origen;
	private String monto="";
	private String portal;
	
	private String huella_seguridad;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	private Collection<BeneficiarioTO> coleccion_beneficiarios=new ArrayList<BeneficiarioTO>(NUMERO_MAXIMO_BENEFICIARIOS);
	
	private double montoMinimo;
	public MessageErrors validate(){
		MessageErrors errors = new MessageErrors();
		
		DecimalFormat df = new DecimalFormat("$###,###,###,###.00");
		if(getComando().equalsIgnoreCase("validacion")){
			if(cuenta_origen.equalsIgnoreCase("-1")){
				errors.add("cuenta_origen", VALIDATOR_CUENTA_ORIGEN_EMPTY);
			}
			if(!validaMonto(errors)){
				errors.add("monto", VALIDATOR_TAS_MONTO_MINIMO,df.format(montoMinimo));
			}
			if(coleccion_beneficiarios==null || coleccion_beneficiarios.size()==0){
				errors.add("coleccion_beneficiarios", VALIDATOR_BENEFICIARIOS_EMPRY);
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
	private boolean validaMonto(MessageErrors errors ){
		PropertiesService propertiesService=PropertiesService.getInstance();
		try{
			montoMinimo=Integer.parseInt(propertiesService.getPropertie("CuentasInversion.properties","montominimo.cuenta.elite"));
			double montoApertura=Double.parseDouble(monto);
			if(montoApertura<montoMinimo){
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getCuenta_origen() {
		return cuenta_origen;
	}
	public void setCuenta_origen(String cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
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
	
	
}
