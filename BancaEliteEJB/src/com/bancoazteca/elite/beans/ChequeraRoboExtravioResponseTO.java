package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ChequeraRoboExtravioResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<CuentaTO> cuentas;
	private String cuenta ;
	private String tipoCuenta;
	private ClienteTO clienteVO ;
	private ArrayList<TalonarioTO> chequerascuenta;
	private String fechaSolicitud;
	private String dia;
	private String mes;
	private String a_o;
	private String tipoOperacion;

	private int motivoBloqueoRoboExtravio;
	private String confirmacion = "S";
	private String numeroChequera;
	private String chequeInicial;
	private String chequeFinal;
	private String cuentaFormat;
	private String descChequera;
	private String cuentaDescripcion;
	

	private int numeroCheque=0;
	private int chequeInicialB=0;
	private int chequeFinalB=0;

	private String rutaPdf ;
	private String message;
	private String method;
	private boolean consultoChequeras ;
	private String chequera;
	private String producto;

	
	private Collection errores;
	private String clave;
	private String tokencode;
	private String newpin ;
	private String confnewpin;
	
//    FormatoChequeVO formatoChequeVO  = new FormatoChequeVO ();

	private String cheques;
    private String fechaIni; 
    
    private String estatusTalonario;
    private String estatusChequera;

	private String folioOperacion;


	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public ClienteTO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteTO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getA_o() {
		return a_o;
	}

	public void setA_o(String a_o) {
		this.a_o = a_o;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public int getMotivoBloqueoRoboExtravio() {
		return motivoBloqueoRoboExtravio;
	}

	public void setMotivoBloqueoRoboExtravio(int motivoBloqueoRoboExtravio) {
		this.motivoBloqueoRoboExtravio = motivoBloqueoRoboExtravio;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getNumeroChequera() {
		return numeroChequera;
	}

	public void setNumeroChequera(String numeroChequera) {
		this.numeroChequera = numeroChequera;
	}

	public String getChequeInicial() {
		return chequeInicial;
	}

	public void setChequeInicial(String chequeInicial) {
		this.chequeInicial = chequeInicial;
	}

	public String getChequeFinal() {
		return chequeFinal;
	}

	public void setChequeFinal(String chequeFinal) {
		this.chequeFinal = chequeFinal;
	}

	public int getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(int numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public int getChequeInicialB() {
		return chequeInicialB;
	}

	public void setChequeInicialB(int chequeInicialB) {
		this.chequeInicialB = chequeInicialB;
	}

	public int getChequeFinalB() {
		return chequeFinalB;
	}

	public void setChequeFinalB(int chequeFinalB) {
		this.chequeFinalB = chequeFinalB;
	}

	public String getRutaPdf() {
		return rutaPdf;
	}

	public void setRutaPdf(String rutaPdf) {
		this.rutaPdf = rutaPdf;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isConsultoChequeras() {
		return consultoChequeras;
	}

	public void setConsultoChequeras(boolean consultoChequeras) {
		this.consultoChequeras = consultoChequeras;
	}

	public String getChequera() {
		return chequera;
	}

	public void setChequera(String chequera) {
		this.chequera = chequera;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Collection getErrores() {
		return errores;
	}

	public void setErrores(Collection errores) {
		this.errores = errores;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTokencode() {
		return tokencode;
	}

	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}

	public String getNewpin() {
		return newpin;
	}

	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}

	public String getConfnewpin() {
		return confnewpin;
	}

	public void setConfnewpin(String confnewpin) {
		this.confnewpin = confnewpin;
	}

	public String getCheques() {
		return cheques;
	}

	public void setCheques(String cheques) {
		this.cheques = cheques;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getEstatusTalonario() {
		return estatusTalonario;
	}

	public void setEstatusTalonario(String estatusTalonario) {
		this.estatusTalonario = estatusTalonario;
	}

	public String getFolioOperacion() {
		return folioOperacion;
	}

	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion = folioOperacion;
	}

	public ArrayList<CuentaTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<CuentaTO> cuentas) {
		this.cuentas = cuentas;
	}

	public ArrayList<TalonarioTO> getChequerascuenta() {
		return chequerascuenta;
	}

	public void setChequerascuenta(ArrayList<TalonarioTO> chequerascuenta) {
		this.chequerascuenta = chequerascuenta;
	}

	public String getCuentaFormat() {
		return cuentaFormat;
	}

	public void setCuentaFormat(String cuentaFormat) {
		this.cuentaFormat = cuentaFormat;
	}

	public String getDescChequera() {
		return descChequera;
	}

	public void setDescChequera(String descChequera) {
		this.descChequera = descChequera;
	}

	public String getCuentaDescripcion() {
		return cuentaDescripcion;
	}

	public void setCuentaDescripcion(String cuentaDescripcion) {
		this.cuentaDescripcion = cuentaDescripcion;
	}

	public String getEstatusChequera() {
		return estatusChequera;
	}

	public void setEstatusChequera(String estatusChequera) {
		this.estatusChequera = estatusChequera;
	} 


}
