/**
 * 
 */
package com.bancoazteca.elite.ejb.alnova.beans;

/**
 * @author Paul Edgar Diaz Islas
 *
 */
public class F752ObtieneFechaHabilRequestTO extends EjbAlnovaRequestTO{

	private static final long serialVersionUID = 5351981447579962344L;
	private static final String[] keys={"FECENT","NUMDIAS"};
	
	public F752ObtieneFechaHabilRequestTO() {
		super("F752",keys);
	}
	
	public void setFechaEntrada(String fechaEntrada){
		addParameter("FECENT", fechaEntrada);
	}
	public String getFechaEntrada(){
		return getAttribute("FECENT");
	}
	public void setNumeroDiasCalcular(String numeroDiasCalcular){
		addParameter("NUMDIAS", numeroDiasCalcular);
	}
	public String getNumeroDiasCalcular(){
		return getAttribute("NUMDIAS");
	}
	
	
	
}