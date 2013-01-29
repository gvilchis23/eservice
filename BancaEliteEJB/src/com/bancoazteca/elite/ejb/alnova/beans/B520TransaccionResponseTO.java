package com.bancoazteca.elite.ejb.alnova.beans;


public class B520TransaccionResponseTO extends EjbAlnovaResponseTO{
	
	private static final long serialVersionUID = -4085403319933797100L;
	
	public String getNumeroCuentaGenerada(){
		return super.alnovaResponse.getAttribute("ACC");
	}

	public String getSucursal(){
		return super.alnovaResponse.getAttribute("CENT");
	}

	public String getDigitos(){
		return super.alnovaResponse.getAttribute("DIGITS");
	}

	public String getEntidad(){
		return super.alnovaResponse.getAttribute("ENTCOD");
	}

	public String getMonto(){
		return super.alnovaResponse.getAttribute("AMTINP");
	}

	public String getMoneda(){
		return super.alnovaResponse.getAttribute("CODFCC");
	}

	public String getNombreBeneficiario(){
		return super.alnovaResponse.getAttribute("HLDTYP");
	}

	public String getFechaSolicitudOperacion(){
		return super.alnovaResponse.getAttribute("RQDAT");
	}

	public String getClave(){
		return super.alnovaResponse.getAttribute("CLABE");
	}

	public String getNumeroMovimiento(){
		return super.alnovaResponse.getAttribute("NUMMOV");
	}
}