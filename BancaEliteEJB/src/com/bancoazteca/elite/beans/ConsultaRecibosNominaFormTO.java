package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Banco Azteca S.A. [JFAV] Enero 21, 2009.
 */
public class ConsultaRecibosNominaFormTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 6450635507541114565L;
	
	//Fields
	private String tokencode;
	private String newpin;
	private String clave;
	private String llave;
	private String method;
	private String leyenda;
	private String llaveAnterior;
	private String llaveSiguiente;
	
	private String paso;
	private String numEmpleado;
	private CuentaLoTO nominaLo;
	private String coleccionLlaves;
	private CuentaTO nomina;
	private String cteAlnova;
	private ResponseConsultaRecibosTO recib;
	private ResponseConsultaNominaRetencionesTO ret;
	private ArrayList<ArrayList<ArchivoNominaTO>> listaDeRecibos;
	/**
	 * @return the tokencode
	 */
	public String getTokencode() {
		return tokencode;
	}
	/**
	 * @param tokencode the tokencode to set
	 */
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	/**
	 * @return the newpin
	 */
	public String getNewpin() {
		return newpin;
	}
	/**
	 * @param newpin the newpin to set
	 */
	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the llave
	 */
	public String getLlave() {
		return llave;
	}
	/**
	 * @param llave the llave to set
	 */
	public void setLlave(String llave) {
		this.llave = llave;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the leyenda
	 */
	public String getLeyenda() {
		return leyenda;
	}
	/**
	 * @param leyenda the leyenda to set
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	/**
	 * @return the llaveAnterior
	 */
	public String getLlaveAnterior() {
		return llaveAnterior;
	}
	/**
	 * @param llaveAnterior the llaveAnterior to set
	 */
	public void setLlaveAnterior(String llaveAnterior) {
		this.llaveAnterior = llaveAnterior;
	}
	/**
	 * @return the llaveSiguiente
	 */
	public String getLlaveSiguiente() {
		return llaveSiguiente;
	}
	/**
	 * @param llaveSiguiente the llaveSiguiente to set
	 */
	public void setLlaveSiguiente(String llaveSiguiente) {
		this.llaveSiguiente = llaveSiguiente;
	}
	/**
	 * @return the paso
	 */
	public String getPaso() {
		return paso;
	}
	/**
	 * @param paso the paso to set
	 */
	public void setPaso(String paso) {
		this.paso = paso;
	}
	/**
	 * @return the numEmpleado
	 */
	public String getNumEmpleado() {
		return numEmpleado;
	}
	/**
	 * @param numEmpleado the numEmpleado to set
	 */
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	/**
	 * @return the nominaLo
	 */
	public CuentaLoTO getNominaLo() {
		return nominaLo;
	}
	/**
	 * @param nominaLo the nominaLo to set
	 */
	public void setNominaLo(CuentaLoTO nominaLo) {
		this.nominaLo = nominaLo;
	}
	/**
	 * @return the coleccionLlaves
	 */
	public String getColeccionLlaves() {
		return coleccionLlaves;
	}
	/**
	 * @param coleccionLlaves the coleccionLlaves to set
	 */
	public void setColeccionLlaves(String coleccionLlaves) {
		this.coleccionLlaves = coleccionLlaves;
	}
	/**
	 * @return the nomina
	 */
	public CuentaTO getNomina() {
		return nomina;
	}
	/**
	 * @param nomina the nomina to set
	 */
	public void setNomina(CuentaTO nomina) {
		this.nomina = nomina;
	}
	/**
	 * @return the cteAlnova
	 */
	public String getCteAlnova() {
		return cteAlnova;
	}
	/**
	 * @param cteAlnova the cteAlnova to set
	 */
	public void setCteAlnova(String cteAlnova) {
		this.cteAlnova = cteAlnova;
	}
	/**
	 * @return the recib
	 */
	public ResponseConsultaRecibosTO getRecib() {
		return recib;
	}
	/**
	 * @param recib the recib to set
	 */
	public void setRecib(ResponseConsultaRecibosTO recib) {
		this.recib = recib;
	}
	/**
	 * @return the ret
	 */
	public ResponseConsultaNominaRetencionesTO getRet() {
		return ret;
	}
	/**
	 * @param ret the ret to set
	 */
	public void setRet(ResponseConsultaNominaRetencionesTO ret) {
		this.ret = ret;
	}
	public ArrayList<ArrayList<ArchivoNominaTO>> getListaDeRecibos() {
		return listaDeRecibos;
	}
	public void setListaDeRecibos(ArrayList<ArrayList<ArchivoNominaTO>> listaDeRecibos) {
		this.listaDeRecibos = listaDeRecibos;
	}
}
