package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

import org.apache.struts.util.LabelValueBean;

public class ClienteTO implements Serializable {
		
	private static final long serialVersionUID = -5818105051324052466L;	
	
	private String nombreCompleto;
	private String alias;
	private String materno;
	private String paterno;
	private long telefono;
	private String apellidos;
	private String nombre;
	private String numero;
	private String email;
	private String ultimaActualizacionCuentas;
	private boolean dispositives;
	private String tieneOtrosCreditos;
	private String nacimiento;
	private boolean actualizacionCuenta;
	
	private Collection<CuentaTO> cuentas;
	private Collection<TarjetaBaseAlnova> tarjetasBaseAlnova;
	private Collection<TarjetaBaseAlnova> tarjetasInifite;
	private Collection<TarjetaBaseAlnova> goldenCards;
	private Collection<LabelValueBean> labelValueBeans;
	private TarjetaCorporativaCreditoTO tarjetacorporativa;
	private TarjetaCorporativaDebitoTO debitoCorp;
	private SecurityDataTO securityData;
	private SecurityDataTO securityTemp;
	private Collection<LabelValueBeanTO> unifyAllAccounts;
	private Collection<CreditoTO> creditos;
	
	private Collection<CuentaInversionTO> inversiones;
	private Collection<GanareMasTO> ganareMas;
		
	private String numContratoInvReportos;
	
	private String userName;
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Collection<CuentaTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTO>  cuentasTO) {
		this.cuentas = cuentasTO;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUltimaActualizacionCuentas() {
		return ultimaActualizacionCuentas;
	}
	public void setUltimaActualizacionCuentas(String ultimaActualizacionCuentas) {
		this.ultimaActualizacionCuentas = ultimaActualizacionCuentas;
	}	
	public Collection<TarjetaBaseAlnova> getTarjetasBaseAlnova() {
		return tarjetasBaseAlnova;
	}
	public void setTarjetasBaseAlnova(Collection<TarjetaBaseAlnova> tarjetasBaseAlnova) {
		this.tarjetasBaseAlnova = tarjetasBaseAlnova;
	}
	public TarjetaCorporativaCreditoTO getTarjetacorporativa() {
		return tarjetacorporativa;
	}
	public void setTarjetacorporativa(TarjetaCorporativaCreditoTO tarjetacorporativa) {
		this.tarjetacorporativa = tarjetacorporativa;
	}
	public TarjetaCorporativaDebitoTO getDebitoCorp() {
		return debitoCorp;
	}
	public void setDebitoCorp(TarjetaCorporativaDebitoTO debitoCorp) {
		this.debitoCorp = debitoCorp;
	}
	public SecurityDataTO getSecurityData() {
		return securityData;
	}
	public void setSecurityData(SecurityDataTO securityData) {
		this.securityData = securityData;
	}
	public SecurityDataTO getSecurityTemp() {
		return securityTemp;
	}
	public void setSecurityTemp(SecurityDataTO securityTemp) {
		this.securityTemp = securityTemp;
	}
	public boolean isDispositives() {
		return dispositives;
	}
	public void setDispositives(boolean dispositives) {
		this.dispositives = dispositives;
	}
	public Collection<LabelValueBean> getLabelValueBeans() {
		return labelValueBeans;
	}
	public void setLabelValueBeans(Collection<LabelValueBean> labelValueBeans) {
		this.labelValueBeans = labelValueBeans;
	}
	public Collection<TarjetaBaseAlnova> getTarjetasInifite() {
		return tarjetasInifite;
	}
	public void setTarjetasInifite(Collection<TarjetaBaseAlnova> tarjetasInifite) {
		this.tarjetasInifite = tarjetasInifite;
	}
	public Collection<TarjetaBaseAlnova> getGoldenCards() {
		return goldenCards;
	}
	public void setGoldenCards(Collection<TarjetaBaseAlnova> goldenCards) {
		this.goldenCards = goldenCards;
	}
	public Collection<LabelValueBeanTO> getUnifyAllAccounts() {
		return unifyAllAccounts;
	}
	public void setUnifyAllAccounts(Collection<LabelValueBeanTO> unifyAllAccounts) {
		this.unifyAllAccounts = unifyAllAccounts;
	}
	public String getTieneOtrosCreditos() {
		return tieneOtrosCreditos;
	}
	public void setTieneOtrosCreditos(String tieneOtrosCreditos) {
		this.tieneOtrosCreditos = tieneOtrosCreditos;
	}
	public Collection<CreditoTO> getCreditos() {
		return creditos;
	}
	public void setCreditos(Collection<CreditoTO> creditos) {
		this.creditos = creditos;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getNumContratoInvReportos() {
		return numContratoInvReportos;
	}
	public void setNumContratoInvReportos(String numContratoInvReportos) {
		this.numContratoInvReportos = numContratoInvReportos;
	}
	public Collection<CuentaInversionTO> getInversiones() {
		return inversiones;
	}
	public void setInversiones(Collection<CuentaInversionTO> inversiones) {
		this.inversiones = inversiones;
	}
	public Collection<GanareMasTO> getGanareMas() {
		return ganareMas;
	}
	public void setGanareMas(Collection<GanareMasTO> ganareMas) {
		this.ganareMas = ganareMas;
	}
	public boolean isActualizacionCuenta() {
		return actualizacionCuenta;
	}
	public void setActualizacionCuenta(boolean actualizacionCuenta) {
		this.actualizacionCuenta = actualizacionCuenta;
	}
	
}
