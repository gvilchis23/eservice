package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class B520_TransaccionResponse{
	
	AlnovaResponse response;
	
	public B520_TransaccionResponse(AlnovaResponse response) {
		this.response=response;
	}
	public String getNumeroCuentaGenerada(){
		return this.response.getAttribute("ACC");
	}
	public String getSucursal(){
		return this.response.getAttribute("CENT");
	}
	public String getDigitos(){
		return this.response.getAttribute("DIGITS");
	}
	public String getEntidad(){
		return this.response.getAttribute("ENTCOD");
	}
	public String getMonto(){
		return this.response.getAttribute("AMTINP");
	}
	public String getMoneda(){
		return this.response.getAttribute("CODFCC");
	}
	public String getNombreBeneficiario(){
		return this.response.getAttribute("HLDTYP");
	}
	public String getFechaSolicitudOperacion(){
		return this.response.getAttribute("RQDAT");
	}
	public String getClave(){
		return this.response.getAttribute("CLABE");
	}
	public String getNumeroMovimiento(){
		return this.response.getAttribute("NUMMOV");
	}

	public String getMensaje(){
		return response.getMessage();
	}
	
	public String getCodigoMensaje(){
		return response.getMessageCode();
	}
	
	public String getMensajeReal(){
		return response.getMessageReal();
	}
	
}