/**
 * 
 */
package com.bancoazteca.elite.beans;

import java.io.Serializable;

/**
 * @author Paul Edgar Diaz Islas
 *
 */
public class TerminalAlnovaResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoTerminalAlnova;
	public String getCodigoTerminalAlnova() {
		return codigoTerminalAlnova;
	}
	public void setCodigoTerminalAlnova(String codigoTerminalAlnova) {
		this.codigoTerminalAlnova = codigoTerminalAlnova;
	}
	
}
