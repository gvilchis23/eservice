package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class B520_TransaccionRequest extends AlnovaRequest {

	private static final long serialVersionUID = -1152134419923251743L;
	
	public static final String[] keys={"AMTINP", "DEBCAC", "CODFCC", "DEBTXT", "DEBKEY", "DEBUSR", "DETAIL", "CRECAC","CRETXT","CREKEY","CREUSR","VALIDPWD"};
	
	/**
	 * Establece la Operacion a realizar y los keys de los atrubutos a utilizar 
	 */
	public B520_TransaccionRequest() {
		super("B520", keys);
	}
	/**
	 * Monto del transpaso
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setMonto(String value){
		addParameter("AMTINP", value);
	}
	/**
	 * Descripcion de operacion.
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setDescripcionOperacion(String value){
		addParameter("DETAIL", value);
	}
	/**
	 * Tipo de moneda
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setMoneda(String value){
		addParameter("CODFCC", value);
	}
	
	/**
	 * Numero cuenta cargo
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setCuentaCargo(String value){
		addParameter("DEBCAC", value);
	}
	/**
	 * Referencia cuenta cargo
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setReferenciaCuentaCargo(String value){
		addParameter("DEBTXT", value);
	}
	/**
	 * Codigo de operacion cuenta cargo
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setCodigoOperacionCuentaCargo(String value){
		addParameter("DEBKEY", value);
	}
	/**
	 * Nombre titular cuenta cargo
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setTitularCuentaCargo(String value){
		addParameter("DEBUSR", value);	
	}
	/**
	 * Numero cuenta deposito
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setCuentaDeposito(String value){
		addParameter("CRECAC", value);
	}
	/**
	 * Referencia cuenta deposito
	 * @param Objeto <code> java.lang.String</code>  
	 */
	public void setReferenciaCuentaDeposito(String value){
		addParameter("CRETXT", value);
	}
	/**
	 * Codigo de operacion cuenta deposito
	 * @param Objeto <code> java.lang.String</code>  
	 */
	public void setCodigoOperacionCuentaDeposito(String value){
		addParameter("CREKEY", value);	
	}
	/**
	 * Nombre titular cuenta deposito
	 * @param Objeto <code> java.lang.String</code>
	 */
	public void setTitularCuentaDeposito(String value){
		addParameter("CREUSR", value);
	}
	/**
	 * Tipo de validacion
	 * @param value
	 */
	public void setTipoValidacion(String value){
		addParameter("VALIDPWD", value);
	}
	
	public String getMonto(){
		return getAttribute("AMTINP");
	}


	public String getCuentaCargo(){
		return getAttribute("DEBCAC");
	}


	public String getMoneda(){
		return getAttribute("CODFCC");
	}


	public String getReferanciaCuentaCargo(){
		return getAttribute("DEBTXT");
	}


	public String getCodigoOperacionCuentaCargo(){
		return getAttribute("DEBKEY");
	}


	public String getTitularCuentaCargo(){
		return getAttribute("DEBUSR");
	}


	public String getDetalles(){
		return getAttribute("DETAIL");
	}


	public String getCuentaDeposito(){
		return getAttribute("CRECAC");
	}


	public String getReferenciaCuentaDeposito(){
		return getAttribute("CRETXT");
	}


	public String getCodigoOperacionCuentaDeposito(){
		return getAttribute("CREKEY");
	}


	public String getTitularCuentaDeposito(){
		return getAttribute("CREUSR");
	}


	public String getTipoValidacion(){
		return getAttribute("VALIDPWD");
	}

	
	
}
