package com.bancoazteca.elite.ejb.alnova.beans;

public class B065AltaDatosComplementariosRequestTO extends EjbAlnovaRequestTO {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final String[] keys ={"ENT","CENTER","ACC","FUNDCON","EXPADMI","PAYROLL","CRDPYMT","TDACCT","FEEPYMT","PRVPYMT","USEOTH1",
		"USEOTH2","USEOTH3","FORTRXS","TRVCHKS","CURTRXS","OPROTH1","OPROTH2","OPROTH3","CHGSMON","CHGSMOT",
		"DEPSMON","DEPSMOT","OPTOMON","OPTOMOT","INCODSC","INCOSRC","CODRTNO","MENSAJE"};
		
		

	public B065AltaDatosComplementariosRequestTO() {
		super("B065", keys);
	}

	public String getCodigoEntidad() {
		return getAttribute("ENT");
	}

	public String getCentroCuenta() {
		return getAttribute("CENTER");
	}

	public String getNumeroCuenta() {
		return getAttribute("ACC");
	}

	public String getConcentracionFondos() {
		return getAttribute("FUNDCON");
	}

	public String getAdministracionGastosIngresos() {
		return getAttribute("EXPADMI");
	}

	public String getPagoNomina() {
		return getAttribute("PAYROLL");
	}

	public String getPagoCreditos() {
		return getAttribute("CRDPYMT");
	}

	public String getCuentaPuenteInversion() {
		return getAttribute("TDACCT");
	}

	public String getPagoComisiones() {
		return getAttribute("FEEPYMT");
	}

	public String getPagoProveedores() {
		return getAttribute("PRVPYMT");
	}

	public String getAdicionalUno() {
		return getAttribute("USEOTH1");
	}

	public String getAdicionalDos() {
		return getAttribute("USEOTH2");
	}

	public String getAdicionalTres() {
		return getAttribute("USEOTH3");
	}

	public String getGirosTransferenciasExtranjero() {
		return getAttribute("FORTRXS");
	}

	public String getChequesViajero() {
		return getAttribute("TRVCHKS");
	}

	public String getCompraventaDivisas() {
		return getAttribute("CURTRXS");
	}

	public String getAdicionalCuatro() {
		return getAttribute("OPROTH1");
	}

	public String getAdicionalCinco() {
		return getAttribute("OPROTH2");
	}

	public String getAdicionalSeis() {
		return getAttribute("OPROTH3");
	}

	public String getNumeroMensualCargos() {
		return getAttribute("CHGSMON");
	}

	public String getImporteMensualCargos() {
		return getAttribute("CHGSMOT");
	}

	public String getNumeroMensualAbonos() {
		return getAttribute("DEPSMON");
	}

	public String getImporteMensualAbonos() {
		return getAttribute("DEPSMOT");
	}

	public String getNumeroTotalOperacionesMensual() {
		return getAttribute("OPTOMON");
	}

	public String getImporteTotalOperacionesMensual() {
		return getAttribute("OPTOMOT");
	}

	public String getProcedenciaRecursos() {
		return getAttribute("INCODSC");
	}

	public String getPrincipalFuenteIngresos() {
		return getAttribute("INCOSRC");
	}

	public String getCodigoRetorno() {
		return getAttribute("CODRTNO");
	}

	public String getMensaje() {
		return getAttribute("MENSAJE");
	}

	public void setCodigoEntidad(String value) {
		addParameter("ENT", value);
	}

	public void setCentroCuenta(String value) {
		addParameter("CENTER", value);
	}

	public void setNumeroCuenta(String value) {
		addParameter("ACC", value);
	}

	public void setConcentracionFondos(String value) {
		addParameter("FUNDCON", value);
	}

	public void setAdministracionGastosIngresos(String value) {
		addParameter("EXPADMI", value);
	}

	public void setPagoNomina(String value) {
		addParameter("PAYROLL", value);
	}

	public void setPagoCreditos(String value) {
		addParameter("CRDPYMT", value);
	}

	public void setCuentaPuenteInversion(String value) {
		addParameter("TDACCT", value);
	}

	public void setPagoComisiones(String value) {
		addParameter("FEEPYMT", value);
	}

	public void setPagoProveedores(String value) {
		addParameter("PRVPYMT", value);
	}

	public void setAdicionalUno(String value) {
		addParameter("USEOTH1", value);
	}

	public void setAdicionalDos(String value) {
		addParameter("USEOTH2", value);
	}

	public void setAdicionalTres(String value) {
		addParameter("USEOTH3", value);
	}

	public void setGirosTransferenciasExtranjero(String value) {
		addParameter("FORTRXS", value);
	}

	public void setChequesViajero(String value) {
		addParameter("TRVCHKS", value);
	}

	public void setCompraventaDivisas(String value) {
		addParameter("CURTRXS", value);
	}

	public void setAdicionalCuatro(String value) {
		addParameter("OPROTH1", value);
	}

	public void setAdicionalCinco(String value) {
		addParameter("OPROTH2", value);
	}

	public void setAdicionalSeis(String value) {
		addParameter("OPROTH3", value);
	}

	public void setNumeroMensualCargos(String value) {
		addParameter("CHGSMON", value);
	}

	public void setImporteMensualCargos(String value) {
		addParameter("CHGSMOT", value);
	}

	public void setNumeroMensualAbonos(String value) {
		addParameter("DEPSMON", value);
	}

	public void setImporteMensualAbonos(String value) {
		addParameter("DEPSMOT", value);
	}

	public void setNumeroTotalOperacionesMensual(String value) {
		addParameter("OPTOMON", value);
	}

	public void setImporteTotalOperacionesMensual(String value) {
		addParameter("OPTOMOT", value);
	}

	public void setProcedenciaRecursos(String value) {
		addParameter("INCODSC", value);
	}

	public void setPrincipalFuenteIngresos(String value) {
		addParameter("INCOSRC", value);
	}

	public void setCodigoRetorno(String value) {
		addParameter("CODRTNO", value);
	}

	public void setMensaje(String value) {
		addParameter("MENSAJE", value);
	}
	
}