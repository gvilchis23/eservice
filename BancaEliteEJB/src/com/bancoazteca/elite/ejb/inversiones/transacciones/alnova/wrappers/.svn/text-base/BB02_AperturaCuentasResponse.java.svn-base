package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class BB02_AperturaCuentasResponse{
	
	AlnovaResponse response;
	
	public BB02_AperturaCuentasResponse(AlnovaResponse response) {
		this.response=response;
	}
	/**
	 * Get Numero de cuenta unica
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getCuentaUnica(){
		return this.response.getAttribute("ACC");
	}
	/**
	 * Get Numero de sucursal o Numero de oficina.
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getSucursal(){
		return this.response.getAttribute("CENT");
	}
	/**
	 * Get Digitos
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getDigitos(){
		return this.response.getAttribute("DIGITS");
	}
	/**
	 * Get Entidad
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getEntidad(){
		return this.response.getAttribute("ENTCOD");
	}
	/**
	 * Get Monto
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getMonto(){
		return this.response.getAttribute("AMTINP");
	}
	/**
	 * Get Tipo moneda
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getMoneda(){
		return this.response.getAttribute("CODFCC");
	}
	/**
	 * Get Nombre beneficiario
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getNombreBeneficiario(){
		return this.response.getAttribute("HLDTYP");
	}
	/**
	 * Get Fecha solicitud de apertura de cuenta
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getFechaSolicitudOperacion(){
		return this.response.getAttribute("RQDAT");
	}
	/**
	 * Get Numero cuenta clave
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getCuentaClabe(){
		return this.response.getAttribute("CLABE");
	}
	/**
	 * Get Numero de movimiento
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getNumeroMovimiento(){
		return this.response.getAttribute("NUMMOV");
	}
	/**
	 * Get Numero de cuenta
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getNumeroCuenta(){
		String cac= this.response.getAttribute("CAC");
		String numcte= this.response.getAttribute("NUMCTE");
		if(cac!= null){
			return cac;
		}else{
			return numcte;
		}
	}
	/**
	 * Get Mensaje completo
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getMensaje(){
		return response.getMessage();
	}
	/**
	 * Get Numero solamente el codigo del mensaje
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getCodigoMensaje(){
		return response.getMessageCode();
	}
	/**
	 * Get Mensaje del response
	 * @return Object <code>java.lang.String</code><br>
	 */
	public String getMensajeReal(){
		return response.getMessageReal();
	}
	
}