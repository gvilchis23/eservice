package com.bancoazteca.eservice.command.pagoservicios.pagotarjetaazteca;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.EnvioMailRequestTO;
import com.bancoazteca.elite.beans.EnvioMailResponseTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaResponseTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Envio_Mail_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EnvioMailCommand extends CommandBase {
		
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
			PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO = new PagoTarjetaAztecaRequestTO();	
			FrecuentesRequestTO frecuentesRequestTO=new FrecuentesRequestTO();
			Tarjetas_FrecuentesTO tarjetas_FrecuentesTO = new Tarjetas_FrecuentesTO();
			pagoTarjetaAztecaRequestTO.setUser(clienteTO.getUserName());			
			PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = resourceFacadeSL.solicitudPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			Collection<CuentaVO>  cuentas  = pagoTarjetaAztecaResponseTO.getCuentas();		
			Collection<Cuenta_CargoTO> coleccionCuentas = new ArrayList<Cuenta_CargoTO>();
	
			Cuenta_CargoTO cuenta_CargoTO=null;
					
			for(CuentaVO cuentaVO:cuentas)
			{	
				
				String subProduct = cuentaVO.getSubproducto();
				if(!Validator.isEmptyData(subProduct)&& !subProduct.equals("00") ){
					
					cuenta_CargoTO=new Cuenta_CargoTO();
					String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaVO.getNumero()));
					cuenta_CargoTO.setNumero_cuenta(cuentaFormateada);
					cuenta_CargoTO.setProducto(cuentaVO.getDescripcion());
					cuenta_CargoTO.setSaldo_disponible(cuentaVO.getDisponible());
					coleccionCuentas.add(cuenta_CargoTO);
					
				}			
			}
			
		
			
			Cuentas_CargoTO cuentas_CargoTO=new Cuentas_CargoTO();
			cuentas_CargoTO.setColeccion_cuentas(coleccionCuentas);
			
			response.addAttribute(cuentas_CargoTO);
			response.addAttribute(tarjetas_FrecuentesTO);
			//session.addAttribute(CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA, coleccion_tarjetas_frecuentesTO);
			session.addAttribute(CUENTAS_CARGO, coleccionCuentas);		
			return response;
	}
}
