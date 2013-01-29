package com.bancoazteca.elite.ejb.alnova.beans;

public class B023CuentaConRedMovilAztecaWEBResponseTO extends EjbAlnovaResponseTO{
	
	 private static final long serialVersionUID = 1L;
		

	 public String getCuentaAlnova(){
			return super.alnovaResponse.getAttribute("CTAALNV");
		}

		public String getEstatus(){
			return super.alnovaResponse.getAttribute("ESTATUS");
		}

}
