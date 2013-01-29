package com.bancoazteca.eservice.command.nivelseguridad;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.bancoazteca.elite.beans.SecurityLevelTO;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class NivelesSeguridadForm extends FormBean{
	private static final Map<String, String> nivelesSeguridadTransferencias_keyLabel;
	private static final Map<String, String> nivelesSeguridadTransferencias_keyValue;
	private static final Map<String, String> nivelesSeguridadMovimientos_keyLabel;
	private static final Map<String, String> nivelesSeguridadMovimientos_keyValue;
	private static final String MOVIMIENTOS="MOVIMIENTOS";
	private static final String TRANSFERENCIAS="TRANSFERENCIAS";
	private static final String FRECUENTES="FRECUENTES";
	private static final String VALIDACION="VALIDACION";
	
	private String nivel_seguridad_movimientos;
	private String nivel_seguridad_transferencias;
	private String nivel_seguridad_frecuentes;
	private String nivel_seguridad_validacion;
	
	private String monto_maximo_transferencias_mismo_banco;
	private String monto_maximo_transferencias_otro_banco;
	private String monto_maximo_transferencias_internacionales_bancarias;
	private String monto_maximo_pago_servicios;

	private String huella_seguridad;
	private Cipher clave_seguridad;
	private String opcion_seguridad;
	
	static{
		nivelesSeguridadTransferencias_keyLabel=new HashMap<String, String>();
		nivelesSeguridadTransferencias_keyValue=new HashMap<String, String>();
		nivelesSeguridadMovimientos_keyLabel =new HashMap<String, String>();
		nivelesSeguridadMovimientos_keyValue =new HashMap<String, String>();
		
		nivelesSeguridadTransferencias_keyLabel.put("BAJO", "0");
		nivelesSeguridadTransferencias_keyLabel.put("ALTO", "1");
		nivelesSeguridadMovimientos_keyLabel.put("BAJO", "5");
		nivelesSeguridadMovimientos_keyLabel.put("MEDIO", "6");
		nivelesSeguridadMovimientos_keyLabel.put("ALTO", "4");
		
		nivelesSeguridadTransferencias_keyValue.put( "0","BAJO");
		nivelesSeguridadTransferencias_keyValue.put( "1","ALTO");
		nivelesSeguridadMovimientos_keyValue.put( "5","BAJO");
		nivelesSeguridadMovimientos_keyValue.put( "6","MEDIO");
		nivelesSeguridadMovimientos_keyValue.put( "4","ALTO");
	}
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		if (getComando().equalsIgnoreCase("validacion")) {
			if(getNivelSeguridadTransferencias() == 1) {
				if(getNivelSeguridadMovimientos() < 4 && getNivelSeguridadMovimientos() > 6) {
					errors.add("nivelMovimiento", VALIDATOR_NIVELES_SEGURIDAD_NIVEL_INVALIDO);
				}
				if(Validator.isEmptyData(getMonto_maximo_transferencias_mismo_banco())  || getMonto_maximo_transferencias_mismo_banco().equals("0")) {
					errors.add("transfMismo", VALIDATOR_NIVELES_SEGURIDAD_TRANS_MISMO_INVALIDO);
				}
				if(Validator.isEmptyData(getMonto_maximo_transferencias_otro_banco()) || getMonto_maximo_transferencias_otro_banco().equals("0")) {
					errors.add("transfOtro", VALIDATOR_NIVELES_SEGURIDAD_TRANS_OTRO_INVALIDO);
				}
				if(Validator.isEmptyData(getMonto_maximo_transferencias_internacionales_bancarias()) || getMonto_maximo_transferencias_internacionales_bancarias().equals("0")) {
					errors.add("transfInter", VALIDATOR_NIVELES_SEGURIDAD_TRANS_INTER_INVALIDO);
				}
				if(Validator.isEmptyData(getMonto_maximo_pago_servicios()) || getMonto_maximo_pago_servicios().equals("0")) {
					errors.add("pagoServicios", VALIDATOR_NIVELES_SEGURIDAD_PAGO_SERVICIOS_INVALIDO);
				}
			}
		}else if (getComando().equalsIgnoreCase("ejecucion")) {
			if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_TOKEN)){
				// Para validar que sea alfanumerico no se utilizo Validator debido a que truena en cadenas de mucha longitud 
				if(Validator.isEmptyData(clave_seguridad.toString()) || !Pattern.matches("^([A-Za-z0-9\\s]+)$", clave_seguridad.toString())){
					errors.add(CommandBase.TAG_TOKEN,CLAVE_SEGURIDAD);
				}
			}else if(opcion_seguridad.equalsIgnoreCase(CommandBase.TAG_HUELLA)){
				if(Validator.isEmptyData(huella_seguridad.toString()) || !Pattern.matches("^([0-9]+)$", huella_seguridad.toString())){
					errors.add(CommandBase.TAG_HUELLA,HUELLA_SEGURIDAD);
				}
			}else{
				errors.add("opcion_seguridad",OPCION_SEGURIDAD);
			}
		}
		return errors;
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


	public String getMonto_maximo_pago_servicios() {
		return monto_maximo_pago_servicios;
	}


	public void setMonto_maximo_pago_servicios(String monto_maximo_pago_servicios) {
		this.monto_maximo_pago_servicios = monto_maximo_pago_servicios;
	}


	public String getHuella_seguridad() {
		return huella_seguridad;
	}

	public String getNivel_seguridad_validacion() {
		return nivel_seguridad_validacion;
	}


	public void setNivel_seguridad_validacion(String nivel_seguridad_validacion) {
		this.nivel_seguridad_validacion = nivel_seguridad_validacion;
	}

	public String getNivel_seguridad_movimientos() {
		return nivel_seguridad_movimientos;
	}


	public void setNivel_seguridad_movimientos(String nivel_seguridad_movimientos) {
		this.nivel_seguridad_movimientos = nivel_seguridad_movimientos;
	}


	public String getNivel_seguridad_transferencias() {
		return nivel_seguridad_transferencias;
	}


	public void setNivel_seguridad_transferencias(
			String nivel_seguridad_transferencias) {
		this.nivel_seguridad_transferencias = nivel_seguridad_transferencias;
	}


	public String getNivel_seguridad_frecuentes() {
		return nivel_seguridad_frecuentes;
	}


	public void setNivel_seguridad_frecuentes(String nivel_seguridad_frecuentes) {
		this.nivel_seguridad_frecuentes = nivel_seguridad_frecuentes;
	}


	public String getMonto_maximo_transferencias_mismo_banco() {
		return monto_maximo_transferencias_mismo_banco;
	}


	public void setMonto_maximo_transferencias_mismo_banco(
			String monto_maximo_transferencias_mismo_banco) {
		this.monto_maximo_transferencias_mismo_banco = monto_maximo_transferencias_mismo_banco;
	}


	public String getMonto_maximo_transferencias_otro_banco() {
		return monto_maximo_transferencias_otro_banco;
	}


	public void setMonto_maximo_transferencias_otro_banco(
			String monto_maximo_transferencias_otro_banco) {
		this.monto_maximo_transferencias_otro_banco = monto_maximo_transferencias_otro_banco;
	}


	public String getMonto_maximo_transferencias_internacionales_bancarias() {
		return monto_maximo_transferencias_internacionales_bancarias;
	}


	public void setMonto_maximo_transferencias_internacionales_bancarias(
			String monto_maximo_transferencias_internacionales_bancarias) {
		this.monto_maximo_transferencias_internacionales_bancarias = monto_maximo_transferencias_internacionales_bancarias;
	}


	public int getNivelSeguridadMovimientos() {
		try{
			return Integer.parseInt(getSecurytyValue(this.getNivel_seguridad_movimientos(), MOVIMIENTOS));
		}catch(Exception e){
			return 0;
		}
	}

	public int getNivelSeguridadTransferencias() {
		try{
			return Integer.parseInt(getSecurytyValue(this.getNivel_seguridad_transferencias(), TRANSFERENCIAS));
		}catch(Exception e){
			return 0;
		}
		
	}
	
	public int getNivelSeguridadFrecuentes() {
		try{
			return Integer.parseInt(getSecurytyValue(this.getNivel_seguridad_transferencias(), FRECUENTES));
		}catch(Exception e){
			return 0;
		}
		
	}

	public int getNivelSeguridadValidacion() {
		try{
			return Integer.parseInt(getSecurytyValue(this.getNivel_seguridad_transferencias(), VALIDACION));
		}catch(Exception e){
			return 0;
		}
		
	}
	
	private String getSecurytyValue(String securityLebel ,String type){
		if(type.equalsIgnoreCase(TRANSFERENCIAS)){
			if(nivelesSeguridadTransferencias_keyLabel.containsKey(securityLebel.toUpperCase())){
				return nivelesSeguridadTransferencias_keyLabel.get(securityLebel.toUpperCase());
			}
		}else if(type.equalsIgnoreCase(MOVIMIENTOS)){
			if(nivelesSeguridadMovimientos_keyLabel.containsKey(securityLebel.toUpperCase())){
				return nivelesSeguridadMovimientos_keyLabel.get(securityLebel.toUpperCase());
			}
		}
		return null;
	}
	public String getSecurytyLabel(String securytyValue,String type){
		if(type.equalsIgnoreCase(TRANSFERENCIAS)){
			if(nivelesSeguridadTransferencias_keyValue.containsKey(securytyValue)){
				return nivelesSeguridadTransferencias_keyValue.get(securytyValue);
			}
		}else if (type.equalsIgnoreCase(MOVIMIENTOS)){
			if(nivelesSeguridadMovimientos_keyValue.containsKey(securytyValue)){
				return nivelesSeguridadMovimientos_keyValue.get(securytyValue);
			}
		}
		return null;
	}
	
}
