package com.bancoazteca.elite.ejb.inversiones.beans;

import java.util.ArrayList;
import java.util.List;

import com.bancoazteca.elite.beans.BeneficiarioTO;

public class AperturaCuentasEntradaTO {
	private static final int NUMERO_MAXIMO_BENEFICIARIOS=4;
	private String codigoProducto;
	private String subProducto;
	private String numeroCliente;
	private String moneda;
	private String monto;
	private List<BeneficiarioTO> beneficiarios= new ArrayList<BeneficiarioTO>(NUMERO_MAXIMO_BENEFICIARIOS);
	private double porcentaje=0.0; 
	
	
	
	/**
	 * Codigo del producto
	 * @return java.lang.String
	 * numerico<br>
	 * longitud 2<br>
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * Codigo del producto
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitud 2<br>
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	/**
	 * Numero de subproducto
	 * @return java.lang.String
	 * numerico<br>
	 * longitug 4<br>
	 */
	public String getSubProducto() {
		return subProducto;
	}
	/**
	 * Numero de subproducto
	 * @param java.lang.String
	 * numerico<br>
	 * longitug 4<br>
	 */
	public void setSubProducto(String subproducto) {
		this.subProducto = subproducto;
	}
	/**
	 * Numero de cliente
	 * @return java.lang.String
	 * numerico<br>
	 * longitug 8<br>
	 */
	public String getNumeroCliente() {
		return numeroCliente;
	}
	/**
	 * Numero de cliente
	 * @param java.lang.String
	 * numerico<br>
	 * longitug 8<br>
	 */
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	/**
	 * Codigo de moneda de apertura de cuenta
	 * @return java.lang.String
	 * alfanumerico<br>
	 * longitug 3<br>
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * Codigo de moneda de apertura de cuenta
	 * @param java.lang.String
	 * alfanumerico<br>
	 * longitug 3<br>
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * Monto de apertura de cuenta
	 * @return java.lang.String<br>
	 * numerico<br>
	 * longitug 16<br>
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * Monto de apertura de cuenta<br>
	 * Encapsulamiento de los montos a como los recibe alnova<br>
	 * @param java.lang.String
	 * numerico<br>
	 * longitug 16<br>
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	/**
	 * Numero de codigo de operacion 
	 * @return java.lang.String
	 * numerico<br>
	 * longitug 3<br>
	 */
	
	
	/**
	 * addBeneficiario<br>
	 * ===================================================<br>
	 * Agregar beneficiarios a la lista de beneficiarios.<br>
	 * Maximo numero de beneficiarios 4    <br>
	 * Valida que el porcentaje no exceda el 100% <br>
	 * @param com.bancoazteca.elite.beans.BeneficiarioTO
	 * @return boolean <br>
	 * true: si se agrego<br>
	 * false: no se pudo agregar<br>
	 */
	public boolean addBeneficiario(BeneficiarioTO beneficiario){
		double auxiliar;
		try{
			if( beneficiarios.size() < NUMERO_MAXIMO_BENEFICIARIOS ){
				auxiliar=porcentaje+Double.parseDouble(beneficiario.getPorcentaje());
				if( auxiliar <= 100 ){
					this.porcentaje=auxiliar;
					this.beneficiarios.add(beneficiario);
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Obtiene el arreglo de beneficiarios
	 * @return java.util.List<com.bancoazteca.elite.beans.BeneficiarioTO>
	 */
	public List<BeneficiarioTO> getBeneficiarios() {
		return beneficiarios;
	}
	
	public void setBeneficiarios(List<BeneficiarioTO> beneficiarios) {
		this.beneficiarios=beneficiarios;
	}
	
	public String toString (){
		StringBuffer result= new StringBuffer();
		result.append("AperturaCuentasEntradaTO: \n");
		result.append("codigoProducto: "+codigoProducto);
		result.append("subproducto: "+subProducto);
		result.append("numeroCliente: "+numeroCliente);
		result.append("moneda: "+moneda);
		result.append("monto: "+monto);
		result.append("beneficiarios: "+beneficiarios);
		return result.toString();
	}
}
