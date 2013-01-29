package com.bancoazteca.elite.ejb.alnova.beans;

public class B065AltaDatosComplementariosResponseTO extends EjbAlnovaResponseTO{

	   private static final long serialVersionUID = 1L;
		

		public String getMensaje(){
			return super.alnovaResponse.getMessage();
		}
		
		public String getCodigoMensaje(){
			return super.alnovaResponse.getMessageCode();
		}
		
		public String getMensajeReal(){
			return super.alnovaResponse.getMessageReal();
		}
	
}