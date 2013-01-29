package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosRequest;

public class ReportosAltaClienteRequestTO extends ReportosRequest{
	
	private static String[] keysResponse={"STATUS"};
	private static String[] keysRequest={"PORTAL","NUM_CUS","CTACHEQ","EMAIL","APE_PAT","APE_MAT"
										,"NOMLARGO","RFC","IACTIV_ECONO","CURP","FALTA","DIREC","COLONIA"
										,"CIUDAD","CODIGOP","DELEGACION","CLABE"};
	

	public ReportosAltaClienteRequestTO(){
		super(keysResponse,keysRequest,"TAC01");
	}
	
	public void setPortal(String value){
		addParameter("PORTAL", value);
	}
	
	public void setNumeroIdentificacionAlnova(String value){
		addParameter("NUM_CUS", value);
	}
	
	public void setCuentaCargo(String value){
		addParameter("CTACHEQ", value);
	}
	public void setCorreoElectronico(String value){
		addParameter("EMAIL", value);
	}
	public void setApellidoPaterno(String value){
		addParameter("APE_PAT", value);
	}
	public void setApellidoMaterno(String value){
		addParameter("APE_MAT", value);
	}
	public void setNombre(String value){
		addParameter("NOMLARGO", value);
	}
	public void setRfc(String value){
		addParameter("RFC", value);
	}
	public void setActividadEconomica(String value){
		addParameter("IACTIV_ECONO", value);
	}
	public void setCurp(String value){
		addParameter("CURP", value);
	}
	public void setFechaAlta(String value){
		addParameter("FALTA", value);
	}
	public void setCalleNumero(String value){
		addParameter("DIREC", value);
	}
	public void setColonia(String value){
		addParameter("COLONIA", value);
	}
	public void setCiudad(String value){
		addParameter("CIUDAD", value);
	}
	public void setCodigoPostal(String value){
		addParameter("CODIGOP", value);
	}
	public void setDelegacion(String value){
		addParameter("DELEGACION", value);
	}
	public void setCuentaClabe(String value){
		addParameter("CLABE", value);
	}
	
	public String getPortal(){
		return getAttribute("PORTAL");
	}


	public String getNumeroIdentificacionAlnova(){
		return getAttribute("NUM_CUS");
	}


	public String getCuentaCargo(){
		return getAttribute("CTACHEQ");
	}


	public String getCorreoElectronico(){
		return getAttribute("EMAIL");
	}


	public String getApellidoPaterno(){
		return getAttribute("APE_PAT");
	}


	public String getApellidoMaterno(){
		return getAttribute("APE_MAT");
	}


	public String getNombre(){
		return getAttribute("NOMLARGO");
	}


	public String getRfc(){
		return getAttribute("RFC");
	}


	public String getActividadEconomica(){
		return getAttribute("IACTIV_ECONO");
	}


	public String getCurp(){
		return getAttribute("CURP");
	}


	public String getFechaAlta(){
		return getAttribute("FALTA");
	}


	public String getCalleNumero(){
		return getAttribute("DIREC");
	}


	public String getColonia(){
		return getAttribute("COLONIA");
	}


	public String getCiudad(){
		return getAttribute("CIUDAD");
	}


	public String getCodigoPostal(){
		return getAttribute("CODIGOP");
	}


	public String getDelegacion(){
		return getAttribute("DELEGACION");
	}


	public String getCuentaClabe(){
		return getAttribute("CLABE");
	}
	
}
