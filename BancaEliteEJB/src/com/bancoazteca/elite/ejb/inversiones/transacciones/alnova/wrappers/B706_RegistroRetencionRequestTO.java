package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class B706_RegistroRetencionRequestTO extends AlnovaRequest {

	public static final String[] keys={"CAC","AMTINP","COD","OBSERV","WHTDD","MATDAT","AUTHORI","PAP","FREE","CODFCC","PURFLG"};
	
	private String idAlnova;
	private String portal;
	
	public static final String TRANSACCION="B706";
	
	public B706_RegistroRetencionRequestTO(){
		super(TRANSACCION,keys);
	}
	
	public void setCodigoCuentaCliente(String value){
		addParameter("CAC", value);
	}
	
	public void setMonto(String value){
		addParameter("AMTINP", value);
	}
	
	public void setCodigoOperacion(String value){
		addParameter("COD",value);
	}
	
	public void setObservaciones(String value){
		addParameter("OBSERV", value);
	}
	
	public void setDiasRetencion(String value){
		addParameter("WHTDD", value);
	}
	
	public void setFechaVencimiento(String value){
		addParameter("MATDAT", value);
	}
	
	public void setAutorizacion(String value){
		addParameter("AUTHORI", value);
	}
	
	public void setPapel(String value){
		addParameter("PAP", value);
	}
	
	public void setCodigoMonedaExtranjera(String value){
		addParameter("CODFCC", value);
	}
	
	public void setSecuritPurch(String value){
		addParameter("PURFLG", value);
	}
	
	public void setFree(String value){
		addParameter("FREE", value);
	}
	
	public String getCodigoCuentaCliente(){
		return getAttribute("CAC");
	}


	public String getMonto(){
		return getAttribute("AMTINP");
	}


	public String getCodigoOperacion(){
		return getAttribute("COD");
	}


	public String getObservaciones(){
		return getAttribute("OBSERV");
	}


	public String getDiasRetencion(){
		return getAttribute("WHTDD");
	}


	public String getFechaVencimiento(){
		return getAttribute("MATDAT");
	}


	public String getAutorizacion(){
		return getAttribute("AUTHORI");
	}


	public String getPapel(){
		return getAttribute("PAP");
	}


	public String getCodigoMonedaExtranjera(){
		return getAttribute("CODFCC");
	}

	public String getSecuritPurch(){
		return getAttribute("PURFLG");
	}
	
	public String getFree(){
		return getAttribute("FREE");
	}

	public String getIdAlnova() {
		return idAlnova;
	}

	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

}
