package com.bancoazteca.elite.ejb.alnova.beans;

public class B705BloqDesbCodigosResponseTO extends EjbAlnovaResponseTO {

	private static final long serialVersionUID = 1L;
	
	public String getClaveBloqueo(){
		return alnovaResponse.getAttribute("COD");
	}

	public String getDescripcionCodigo(){
		return alnovaResponse.getAttribute("CODDES");
	}
	
	public String getEntidad(){
		return alnovaResponse.getAttribute("ENTCOD");
	}
	public String getCentroRegistro(){
		return alnovaResponse.getAttribute("CENT");
	}
	
	public String getDigitosControl(){
		return alnovaResponse.getAttribute("DIGITS");
	}
	public String getNumeroCuenta(){
		return alnovaResponse.getAttribute("ACC");
	}
	
	public String getObservaciones(){
		return alnovaResponse.getAttribute("OBSERV");
	}
	public String getLineaFinal(){
		return alnovaResponse.getAttribute("LIN");
	}
	
	public String getLineaCondicional(){
		return alnovaResponse.getAttribute("COND1");
	}
	public String getSucursalModificacion(){
		return alnovaResponse.getAttribute("REGBRN");
	}
	
	public String getUsuarioModificacion(){
		return alnovaResponse.getAttribute("USER");
	}
	public String getFechaModificacion(){
		return alnovaResponse.getAttribute("HEADDAT");
	}
	
	public String getDatosCodigo(){
		return alnovaResponse.getAttribute("DATA");
	}
}