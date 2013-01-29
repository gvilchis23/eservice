package com.bancoazteca.eservice.command.estadocuenta.mercadodinero;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EstadoCuentaMercadoDineroForm extends FormBean {
	
	private String anio_periodo;
	private String mes_periodo;
	private String portal;
	
	public String getAnio_periodo() {
		return anio_periodo;
	}


	public void setAnio_periodo(String anio_periodo) {
		this.anio_periodo = anio_periodo;
	}


	public String getMes_periodo() {
		return mes_periodo;
	}


	public void setMes_periodo(String mes_periodo) {
		this.mes_periodo = mes_periodo;
	}


	public String getPortal() {
		return portal;
	}


	public void setPortal(String portal) {
		this.portal = portal;
	}


	public MessageErrors validate(){
		MessageErrors errors = new MessageErrors();
		
		if( getComando().equalsIgnoreCase("SOLICITUD") ) {
			if (Validator.isEmptyData(portal)){			
				errors.add("portal","validator.estado.cuenta.portal.reportos");
			}
		}
		
		if( getComando().equalsIgnoreCase("VALIDACION") ) {
			if (Validator.isEmptyData(portal)){			
				errors.add("portal","validator.estado.cuenta.portal.reportos");
			}
			if (Validator.isEmptyData(anio_periodo)){			
				errors.add("anio_periodo","validator.estado.cuenta.anio");
			}
		}
		
		if( getComando().equalsIgnoreCase("EJECUCION") ) {
			if (Validator.isEmptyData(portal)){			
				errors.add("portal","validator.estado.cuenta.portal.reportos");
			}
			if (Validator.isEmptyData(anio_periodo)){			
				errors.add("anio_periodo","validator.estado.cuenta.anio");
			}
			if (Validator.isEmptyData(mes_periodo)){			
				errors.add("mes_periodo","validator.estado.cuenta.mes");
			}
		}
		
		return errors;
	}

	
}
