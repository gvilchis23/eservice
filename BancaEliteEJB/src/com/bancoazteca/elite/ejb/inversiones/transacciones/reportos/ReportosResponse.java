package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos;

import java.util.List;
import java.util.Map;

public class ReportosResponse{
	
	private Map<String,String> parametros;
	private List<Map<String,String>> resumenes;
	
	public ReportosResponse(Map<String, String> parametros) {
		this.parametros=parametros;
	}
	public ReportosResponse(List<Map<String,String>>  resumenes) {
		this.resumenes=resumenes;
	}
	
	public String getAttribute(String key){
		return parametros.get(key);
	}

	public Map<String, String> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}
	public List<Map<String,String>>  getResumenes() {
		return resumenes;
	}
	
}