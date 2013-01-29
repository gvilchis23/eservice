package com.bancoazteca.elite.beans;

import java.util.ArrayList;

public class PresolicitudChequesResponseTO {
	private ArrayList<CuentaVO> cuentas;
	private String comision;
	private ArrayList<TalonarioVO> tiposChequeras;
	private RegistroFirmasVO registroFirmasVO;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public RegistroFirmasVO getRegistroFirmasVO() {
		return registroFirmasVO;
	}
	public void setRegistroFirmasVO(RegistroFirmasVO registroFirmasVO) {
		this.registroFirmasVO = registroFirmasVO;
	}
	public ArrayList<TalonarioVO> getTiposChequeras() {
		return tiposChequeras;
	}
	public void setTiposChequeras(ArrayList<TalonarioVO> tiposChequeras) {
		this.tiposChequeras = tiposChequeras;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public ArrayList<CuentaVO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<CuentaVO> cuentas) {
		this.cuentas = cuentas;
	}
	
}
