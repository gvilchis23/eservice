package com.bancoazteca.elite.ejb.alnova.beans;



public class BB02AperturaCuentasResponseTO extends EjbAlnovaResponseTO{
	private static final long serialVersionUID = 1L;


	public String getCuentaUnica(){
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

	public String getCuentaClabe(){
		return super.alnovaResponse.getAttribute("CLABE");
	}

	public String getNumeroMovimiento(){
		return super.alnovaResponse.getAttribute("NUMMOV");
	}

	public String getNumeroCuenta(){
		String cac= super.alnovaResponse.getAttribute("CAC");
		String numcte= super.alnovaResponse.getAttribute("NUMCTE");
		if(cac!= null){
			return cac;
		}else{
			return numcte;
		}
	}
}