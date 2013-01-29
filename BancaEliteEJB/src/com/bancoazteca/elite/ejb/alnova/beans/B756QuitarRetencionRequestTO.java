package com.bancoazteca.elite.ejb.alnova.beans;

public class B756QuitarRetencionRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = -1152134419923251743L;	
	public static final String[] keys={"CAC","WHD","REMOVE","AMTINP","PAP","FREE","CODFCC"};
	
	public B756QuitarRetencionRequestTO(){
		super("B756",keys);
	}
	/**
	 * Numero de cuenta del cliente
	 * @param Alfanumerico<br>
	 * longitud maxima 20
	 */
	public void setCodigoCuentaCliente(String value){
		addParameter("CAC", value);
	}
	
	public String getCodigoCuentaCliente(){
		return getAttribute("CAC");
	}
	/**
	 * Numero de retencion<br>
	 * @param valor numerico<br>
	 * longitud 5<br>
	 * acompletar con 0 a la izquieda
	 */
	public void setNumeroRetencion(String value){
		addParameter("WHD", value);
	}
	/**
	 * Parametro de verificacion si la retencion se elimina permanentemente <br> 
	 * o de manera parcial.
	 * @param Alfanumerico<br>
	 * longitud maxima 1 <br>
	 * valores S/N<br>
	 */
	public void setRemueveRetencionParcialTotal(String value){
		addParameter("REMOVE",value);
	}
	/**
	 * Monto de la retencion
	 * @param Numerico <br>
	 * longitud 16<br>
	 * acompletar con 0 a la izquieda
	 */
	public void setMonto(String value){
		addParameter("AMTINP", value);
	}
	/**
	 * Establece el papel
	 * @param Alfanumerico<br>
	 * longitud 1<br>
	 */
	public void setPapel(String value){
		addParameter("PAP", value);
	}
	/**
	 * Campo libre
	 * @param Alfanumerico<br> 
	 * longitud maxima 12
	 */
	public void setFree(String value){
		addParameter("FREE", value);
	}
	/**
	 * Codigo de moneda
	 * @param Alfanumerico<br>
	 * logitud maxima 3
	 */
	public void setCodigoMonedaExtranjera(String value){
		addParameter("CODFCC", value);
	}
	
	
}
