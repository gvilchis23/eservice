/**
 * 
 */
package com.bancoazteca.elite.ejb.alnova.beans;

/**
 * @author Paul Edgar Diaz Islas
 *
 */
public class F752ObtieneFechaHabilResponseTO extends EjbAlnovaResponseTO{

	private static final long serialVersionUID = 5351981447579962343L;
	
	public String getFechaSalida(){
		return super.alnovaResponse.getAttribute("FECSAL");
	}
	
	
}