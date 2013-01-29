package com.bancoazteca.eservice.command.pagoservicios.tarjeta_otrosbancos;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosResponseTO;
import com.bancoazteca.elite.beans.TarjetaCreditoOtrosBancosTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosRequestTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Tarjeta_Otros_Bancos_EjecucionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PagoTarjetaOtrosBancosCommand extends CommandBase{

	public Response solicitud (Session session) throws Exception{
		Response response = new Response();


		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);

			PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO = new PagoTarjetaOtrosBancosRequestTO();
			tarjetaOtrosBancosRequestTO.setUser(clienteTO.getUserName());
			PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = resourceFacadeSL.getInitialTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);

			TransferenciaTercerosRequestTO tercerosRequestTO = new TransferenciaTercerosRequestTO();
			tercerosRequestTO.setUser(clienteTO.getUserName());
			TransferenciaTercerosResponseTO tercerosResponseTO = resourceFacadeSL.getTransferenciaTercerosInvocacion(tercerosRequestTO);

			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			tarjetaOtrosBancosResponseTO.setCuentas(tercerosResponseTO.getCuentas());
			String ctaFormateada;
			for(CuentaTransaccionesTO cuentasTransacciones: tarjetaOtrosBancosResponseTO.getCuentas()){
				ctaFormateada = Formatter.removeSpaces(cuentasTransacciones.getCuentaFormateada());
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				CuentaTO cuentaTO = super.getAccountsPrdicate(clienteTO.getCuentas(),ctaFormateada);
				if(cuentaTO!=null){
					cuentaCargoTO.setNumero_cuenta(ctaFormateada);
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCargoTO.setSaldo_disponible(String.valueOf(cuentaTO.getDisponible()));
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}

			Cuentas_CargoTO cuentas_CargoTO = new Cuentas_CargoTO();
			cuentas_CargoTO.setColeccion_cuentas(cuentaCollectionTO);

			session.addAttribute(PAGO_SERVICIO_TARJETA_OTROS_BANCOS_RESPONSE, tarjetaOtrosBancosResponseTO);

			response.addAttribute(cuentas_CargoTO);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response validacion(Session session)throws Exception{
		Response response = new Response();
		PagoTarjetaOtrosBancosForm pagoTarjetaOtrosBancosForm = (PagoTarjetaOtrosBancosForm)getFormBean();
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = (PagoTarjetaOtrosBancosResponseTO) session.getAttribute(PAGO_SERVICIO_TARJETA_OTROS_BANCOS_RESPONSE);
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			Collection<CuentaTransaccionesTO> cuentasTransacciones = tarjetaOtrosBancosResponseTO.getCuentas();
			String indexCuentaCargo = "";
			String tarjetaFormateada = "";
			for(CuentaTransaccionesTO cuentas: tarjetaOtrosBancosResponseTO.getCuentas()){
				if(Formatter.removeSpaces(cuentas.getCuentaFormateada()).equalsIgnoreCase(pagoTarjetaOtrosBancosForm.getCuenta_cargo())){
					indexCuentaCargo = cuentas.getIndex(); 
					tarjetaFormateada = cuentas.getCuentaFormateada();
					break;
				}
			}

			PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO = new PagoTarjetaOtrosBancosRequestTO();
			tarjetaOtrosBancosRequestTO.setUser(clienteTO.getUserName());
			tarjetaOtrosBancosRequestTO.setOrigen(Formatter.removeSpaces(indexCuentaCargo));
			tarjetaOtrosBancosRequestTO.setTarjeta(pagoTarjetaOtrosBancosForm.getNumero_tarjeta());
			tarjetaOtrosBancosRequestTO.setImporte(pagoTarjetaOtrosBancosForm.getImporte());

			tarjetaOtrosBancosResponseTO = resourceFacadeSL.getDataTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			tarjetaOtrosBancosResponseTO.setCuentas(cuentasTransacciones);
			tarjetaOtrosBancosResponseTO.setTarjetaFormateada(tarjetaFormateada);

			//....................
			TarjetaCreditoOtrosBancosTO tarjetaCredito =  tarjetaOtrosBancosResponseTO.getTarjetaCredito();
			Collection<CuentaTransaccionesTO> cuentasTransaccionesTO = tarjetaOtrosBancosResponseTO.getCuentas();
			tarjetaOtrosBancosResponseTO.setTarjetaCredito(tarjetaCredito);
			tarjetaOtrosBancosResponseTO.setCuentas(cuentasTransaccionesTO);

			Tarjeta_Otros_Bancos_EjecucionTO tarjetaOtrosBancosTO = new Tarjeta_Otros_Bancos_EjecucionTO();
			tarjetaOtrosBancosTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			tarjetaOtrosBancosTO.setCuenta_cargo(Formatter.removeSpaces(tarjetaOtrosBancosResponseTO.getTarjetaFormateada()));
			tarjetaOtrosBancosTO.setImporte(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getAmount()));
			tarjetaOtrosBancosTO.setComision(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getComision()));
			tarjetaOtrosBancosTO.setIva(String.valueOf(Double.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString())));
			tarjetaOtrosBancosTO.setTotal_pago(String.valueOf(Double.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString())));
			tarjetaOtrosBancosTO.setFecha_aplicacion(Formatter.formatDate(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFechaAplicacion()));

			tarjetaOtrosBancosTO.setNumero_tarjeta(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getNumero());
			tarjetaOtrosBancosTO.setBanco(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getBanco());
			tarjetaOtrosBancosTO.setTipo_tarjeta(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getTipoTarjeta());
			tarjetaOtrosBancosTO.setFolio_aclaraciones(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFolio());
			//........
			
			
			
			DispositivoHuellaTO dispositivoHuellaTO = tarjetaOtrosBancosResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());

			session.addAttribute(PAGO_SERVICIO_TARJETA_OTROS_BANCOS_RESPONSE, tarjetaOtrosBancosResponseTO);

			response.addAttribute(huellaTO);
			response.addAttribute(tarjetaOtrosBancosTO);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}

		return response;
	}

	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		PagoTarjetaOtrosBancosForm pagoTarjetaOtrosBancosForm = (PagoTarjetaOtrosBancosForm)getFormBean();
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = (PagoTarjetaOtrosBancosResponseTO) session.getAttribute(PAGO_SERVICIO_TARJETA_OTROS_BANCOS_RESPONSE);
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);

		ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaOtrosBancosTO = tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO();
		String tarjetaFormateada = tarjetaOtrosBancosResponseTO.getTarjetaFormateada();
		Collection<CuentaTransaccionesTO> cuentasTransaccionesTO = tarjetaOtrosBancosResponseTO.getCuentas();
		TarjetaCreditoOtrosBancosTO tarjetaCredito =  tarjetaOtrosBancosResponseTO.getTarjetaCredito();

		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();

			String indexCuentaCargo = "";
			for(CuentaTransaccionesTO cuentas: tarjetaOtrosBancosResponseTO.getCuentas()){
				if(Formatter.removeSpaces(cuentas.getCuentaFormateada()).equalsIgnoreCase((Formatter.removeSpaces(tarjetaFormateada)))){
					indexCuentaCargo = cuentas.getIndex(); 
					break;
				}
			}
			PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO = new PagoTarjetaOtrosBancosRequestTO();
			tarjetaOtrosBancosRequestTO.setUser(clienteTO.getUserName());
			tarjetaOtrosBancosRequestTO.setOrigen(indexCuentaCargo);
			tarjetaOtrosBancosRequestTO.setTarjeta(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getTarjeta());
			tarjetaOtrosBancosRequestTO.setImporte(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporte()));
			tarjetaOtrosBancosRequestTO.setComision(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getComision()));		
			if(pagoTarjetaOtrosBancosForm.getOpcion_seguridad().equalsIgnoreCase(super.TAG_TOKEN)){
				tarjetaOtrosBancosRequestTO.setOptionDispositive(super.OPCION_TOKEN);
				tarjetaOtrosBancosRequestTO.setTokencode(pagoTarjetaOtrosBancosForm.getClave_seguridad().toString());
			} else if(pagoTarjetaOtrosBancosForm.getOpcion_seguridad().equalsIgnoreCase(super.TAG_HUELLA)){
				tarjetaOtrosBancosRequestTO.setOptionDispositive(super.OPCION_HUELLA);
				tarjetaOtrosBancosRequestTO.setClave(pagoTarjetaOtrosBancosForm.getHuella_seguridad().toString());		
			}

			tarjetaOtrosBancosResponseTO = resourceFacadeSL.getConfirmTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			tarjetaOtrosBancosResponseTO.setTarjetaFormateada(tarjetaFormateada);
			tarjetaOtrosBancosResponseTO.setConfirmacionPagoTarjetaOtrosBancosTO(confirmacionPagoTarjetaOtrosBancosTO);
			tarjetaOtrosBancosResponseTO.setTarjetaCredito(tarjetaCredito);
			tarjetaOtrosBancosResponseTO.setCuentas(cuentasTransaccionesTO);

			Tarjeta_Otros_Bancos_EjecucionTO tarjetaOtrosBancosTO = new Tarjeta_Otros_Bancos_EjecucionTO();
			tarjetaOtrosBancosTO.setTitular_cuenta_cargo(clienteTO.getNombreCompleto());
			tarjetaOtrosBancosTO.setCuenta_cargo(Formatter.removeSpaces(tarjetaOtrosBancosResponseTO.getTarjetaFormateada()));
			tarjetaOtrosBancosTO.setImporte(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getAmount()));
			tarjetaOtrosBancosTO.setComision(String.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getComision()));
			tarjetaOtrosBancosTO.setIva(String.valueOf(Double.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getIvaComision().toString())));
			tarjetaOtrosBancosTO.setTotal_pago(String.valueOf(Double.valueOf(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getImporteTotal().toString())));
			tarjetaOtrosBancosTO.setFecha_aplicacion(Formatter.formatDate(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFechaAplicacion()));

			tarjetaOtrosBancosTO.setNumero_tarjeta(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getNumero());
			tarjetaOtrosBancosTO.setBanco(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getBanco());
			tarjetaOtrosBancosTO.setTipo_tarjeta(tarjetaOtrosBancosResponseTO.getTarjetaCredito().getTipoTarjeta());
			tarjetaOtrosBancosTO.setFolio_aclaraciones(tarjetaOtrosBancosResponseTO.getConfirmacionPagoTarjetaOtrosBancosTO().getFolio());
			
			
			response.addAttribute(tarjetaOtrosBancosTO);

			super.updateBalance(session);

		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
}
