package com.bancoazteca.elite.ejb.alnova.beans;

public class P011AltaDatosClienteResponseTO extends EjbAlnovaResponseTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String getCodigoTransaccion(){
		return super.alnovaResponse.getAttribute("TRANSCO");
	}
	
	
	public String getFechaPrincipal(){
		return super.alnovaResponse.getAttribute("HEADDAT");
	}

	public String getUsuario(){
		return super.alnovaResponse.getAttribute("USER");
	}


	public String getNumeroCliente(){
		return super.alnovaResponse.getAttribute("CUSNUM");
	}


	public String getNacionalidad(){
		return super.alnovaResponse.getAttribute("NATCOD");
	}


	public String getDescripcionNacionalidad(){
		return super.alnovaResponse.getAttribute("NATDES");
	}


	public String getPersona(){
		return super.alnovaResponse.getAttribute("SSUBJ");
	}


	public String getCodigoIdentificacion(){
		return super.alnovaResponse.getAttribute("CUSCOD");
	}


	public String getCodigoIdentificacion2(){
		return super.alnovaResponse.getAttribute("CUSCOD2");
	}


	public String getNombre(){
		return super.alnovaResponse.getAttribute("CUSNM");
	}


	public String getApellidoPaterno(){
		return super.alnovaResponse.getAttribute("SURNM");
	}


	public String getApellidoMaterno(){
		return super.alnovaResponse.getAttribute("SFNM");
	}


	public String getNopmbreTarjetaCredito(){
		return super.alnovaResponse.getAttribute("CRDNUM");
	}


	public String getDescripcionDireccion(){
		return super.alnovaResponse.getAttribute("DESADR");
	}
	
	public String getTipoDireccion(){
		return super.alnovaResponse.getAttribute("ADRTYP");
	}
	
	public String getDescripcionTipoDireccion(){
		return super.alnovaResponse.getAttribute("ADRTDES");
	}
	
	public String getIdentificacionDireccion(){
		return super.alnovaResponse.getAttribute("ADRID");
	}
	
	public String getDireccion(){
		return super.alnovaResponse.getAttribute("ADDRESS");
	}
	
	public String getNumero(){
		return super.alnovaResponse.getAttribute("ADRNUM");
	}
	
	public String getPiso(){
		return super.alnovaResponse.getAttribute("FLR");
	}
	
	public String getPuerta(){
		return super.alnovaResponse.getAttribute("DOOR");
	}
	
	public String getCodigoCiudad(){
		return super.alnovaResponse.getAttribute("CTYCOD");
	}
	
	public String getCiudad(){
		return super.alnovaResponse.getAttribute("CITY");
	}
	
	public String getCodigoPais(){
		return super.alnovaResponse.getAttribute("CODCOUNTRY");
	}
	
	public String getCodigoPostal(){
		return super.alnovaResponse.getAttribute("ZIPCOD");
	}
	
	public String getNumeroTelefonico(){
		return super.alnovaResponse.getAttribute("TPHNUM");
	}
	
	public String getNumeroFax(){
		return super.alnovaResponse.getAttribute("TELEX");
	}
	
	public String getEstadoCivil(){
		return super.alnovaResponse.getAttribute("MRST");
	}
	
	public String getSexo(){
		return super.alnovaResponse.getAttribute("GENDER");
	}
	
	public String getFechaNacimiento(){
		return super.alnovaResponse.getAttribute("BIRTHDA");
	}
	
	public String getNumeroDomicilioAlterno(){
		return super.alnovaResponse.getAttribute("ALTNUM");
	}
	
	public String getIdioma(){
		return super.alnovaResponse.getAttribute("LNGCOD");
	}
	
	public String getActividadEconomicaFisica(){
		return super.alnovaResponse.getAttribute("NOC");
	}
	
	public String getActividadEconomicaMoral(){
		return super.alnovaResponse.getAttribute("NCBA");
	}
	
	public String getUltimoNumero(){
		return super.alnovaResponse.getAttribute("LASTNUM");
	}
	
	public String getNivelAcceso(){
		return super.alnovaResponse.getAttribute("ACSLEV");
	}
	
	public String getDatoPrimordial(){
		return super.alnovaResponse.getAttribute("SNRDAT");
	}
	
	public String getNumeroConfirmacion(){
		return super.alnovaResponse.getAttribute("CONFNUM");
	}
	
	public String getCodigoSegmento(){
		return super.alnovaResponse.getAttribute("SEGCOD");
	}
	
	public String getDescripcionSegmento(){
		return super.alnovaResponse.getAttribute("SEGDES");
	}
	
	public String getCodigoCanal(){
		return super.alnovaResponse.getAttribute("CHANCOD");
	}
	
	public String getCanalPreferente(){
		return super.alnovaResponse.getAttribute("PREFCHA");
	}
	
	public String getEstatusCliente(){
		return super.alnovaResponse.getAttribute("CUSST");
	}
	
	public String getDescripcionCanal(){
		return super.alnovaResponse.getAttribute("CHANDES");
	}
	
	public String getBono(){
		return super.alnovaResponse.getAttribute("BONUS");
	}
	public String getColonia(){
		return super.alnovaResponse.getAttribute("TOWN");
	}
	public String getFechaRegistro(){
		return super.alnovaResponse.getAttribute("REGDATE");
	}
	public String getUltimaFechaModificacion(){
		return super.alnovaResponse.getAttribute("LASTMOD");
	}
	public String getRegistroSucursal(){
		return super.alnovaResponse.getAttribute("REGBRN");
	}
	public String getModificacionSucursal(){
		return super.alnovaResponse.getAttribute("MODBRN");
	}
	public String getHoraModificacion(){
		return super.alnovaResponse.getAttribute("MODTIM");
	}
	public String getLada(){
		return super.alnovaResponse.getAttribute("LADA");
	}
	public String getIndicadorFiduciario(){
		return super.alnovaResponse.getAttribute("FIDUC");
	}
	public String getCorreoElectronico(){
		return super.alnovaResponse.getAttribute("EMAIL");
	}
	public String getRfc(){
		return super.alnovaResponse.getAttribute("RFCTIT");
	}
	public String getCurp(){
		return super.alnovaResponse.getAttribute("CURPTIT");
	}
	public String getRelacionCliente(){
		return super.alnovaResponse.getAttribute("CRELCTE");
	}
	public String getNombre2(){
		return super.alnovaResponse.getAttribute("NAME2");
	}
}