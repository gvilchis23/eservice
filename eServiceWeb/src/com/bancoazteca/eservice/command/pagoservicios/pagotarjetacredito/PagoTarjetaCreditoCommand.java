package com.bancoazteca.eservice.command.pagoservicios.pagotarjetacredito;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.PagoTarjetaCreditoRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaCreditoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Pago_tarjeta_creditoTO;
import com.bancoazteca.eservice.command.base.beans.Pago_tarjetasTO;
import com.bancoazteca.eservice.command.base.beans.Tarjetas_creditoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.pagoservicios.pagotarjetaazteca.PagoTarjetaAztecaCommand;
import com.bancoazteca.eservice.command.response.Response;

public class PagoTarjetaCreditoCommand extends CommandBase {

	private static final String UNICA="unica";
	private static final String TARJETAS_CREDITO="tarjetasCredito";
	
	@SuppressWarnings("unchecked")
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		DecimalFormat df = new DecimalFormat("####################.00");
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		Collection<Tarjetas_creditoTO> collection_tarjetas= new ArrayList<Tarjetas_creditoTO>();
		Collection<Cuenta_CargoTO> collection_cuentas= new ArrayList<Cuenta_CargoTO>();
//		Collection<CuentaTO> cuentas=clienteTO.getCuentas();
//		Iterator<CuentaTO> iterador = cuentas.iterator();
		CuentaTO cuentaTemp=null;
		Cuenta_CargoTO cuentaTO=null;
		try{
			PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO = new PagoTarjetaCreditoRequestTO();			
			pagoTarjetaCreditoRequestTO.setUser(clienteTO.getUserName());			
			PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = resourceFacadeSL.setInitialTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			ArrayList<PagoTarjetaCreditoResponseTO> tarjetas =(ArrayList<PagoTarjetaCreditoResponseTO>) pagoTarjetaCreditoResponseTO.getTarjetasCredito();
			
			if(tarjetas != null &&  tarjetas.size()==1){
				synchronized(session){
					PagoTarjetaCreditoResponseTO tarjeta = tarjetas.get(0);
					tarjeta.setTarjetaUnica(UNICA);
					session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE, tarjeta);
					Tarjetas_creditoTO tarjetas_credito= new Tarjetas_creditoTO();
					tarjetas_credito.setNumero_tarjeta(tarjeta.getNumeroTarjeta());
					tarjetas_credito.setSaldo(tarjeta.getImporteTotal());
					tarjetas_credito.setTipo_tarjeta(tarjeta.getTipo());
					collection_tarjetas.add(tarjetas_credito);
				}
			}else{
				pagoTarjetaCreditoResponseTO.setTarjetaUnica("");
				synchronized(session){
					Tarjetas_creditoTO tarjetas_credito;
					for(PagoTarjetaCreditoResponseTO obj:tarjetas){
						tarjetas_credito= new Tarjetas_creditoTO();
						tarjetas_credito.setNumero_tarjeta(obj.getNumeroTarjeta());
						tarjetas_credito.setSaldo(obj.getImporteTotal());
						tarjetas_credito.setTipo_tarjeta(obj.getTipo());
//						tarjetas_credito.setFecha_limite_pago(obj.getFechaLimiteDePago());
//						tarjetas_credito.setPago_minimo(obj.getPagoMinimo());
//						tarjetas_credito.setPago_no_generar_intereses(obj.getPagoParaNoGenerarIntereses());
						collection_tarjetas.add(tarjetas_credito);
					}
					session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE, pagoTarjetaCreditoResponseTO);
				}
			}
			
			session.addAttribute(TARJETAS_CREDITO, tarjetas);
			
			PagoTarjetaAztecaCommand command=new PagoTarjetaAztecaCommand();
			command.solicitud(session);
			collection_cuentas=(Collection<Cuenta_CargoTO>)session.getAttribute(CUENTAS_CARGO);
//			Iterator<Cuenta_CargoTO> iterador=coleccionCuentas.iterator();
//			
//			//Las cuentas se obtienen del cliente TO porque en el action se obtienen de la misma forma
//			while(iterador.hasNext()){
//				cuentaTemp = iterador.next();	
//				cuentaTO= new Cuenta_CargoTO();
//				String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(cuentaTemp.getNumero()));
//				cuentaTO.setNumero_cuenta(cuentaFormateada);
//				cuentaTO.setNumero_cuenta(cuentaTemp.getCuentaFormateada());
//				cuentaTO.setProducto(cuentaTemp.getDescripcion());
//				cuentaTO.setSaldo_disponible(df.format(cuentaTemp.getBalance()));
//				collection_cuentas.add(cuentaTO);
//			}
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		
		
		Pago_tarjetasTO pago_tarjetas= new Pago_tarjetasTO();
		pago_tarjetas.setColeccion_cuentas(collection_cuentas);
		pago_tarjetas.setColeccion_tarjetas_credito(collection_tarjetas);
		response.addAttribute(pago_tarjetas);
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		PagoTarjetaCreditoForm forma = (PagoTarjetaCreditoForm)getFormBean();
		DecimalFormat df = new DecimalFormat("####################.00");
		Pago_tarjeta_creditoTO pago_tarjeta_credito=new Pago_tarjeta_creditoTO();
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		Collection<PagoTarjetaCreditoResponseTO> tarjetas = null;
		synchronized (session) {
			tarjetas = (Collection<PagoTarjetaCreditoResponseTO>)session.getAttribute(TARJETAS_CREDITO);
		}
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO = new PagoTarjetaCreditoRequestTO();
			pagoTarjetaCreditoRequestTO.setUser(clienteTO.getUserName());
			ArrayList <LabelValueBean>  beans = (ArrayList<LabelValueBean>) clienteTO.getLabelValueBeans();
			for(int i=0; i<beans.size(); i++){
				String bean =""+ beans.get(i);
				if(bean.contains(forma.getCuenta_cargo())){
					pagoTarjetaCreditoRequestTO.setCuenta(""+i);
				}
			}
			pagoTarjetaCreditoRequestTO.setTarjeta(""+getTarjetaIndex(tarjetas, forma.getNumero_tarjeta()));
			pagoTarjetaCreditoRequestTO.setImporte(forma.getImporte());
			PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = resourceFacadeSL.setDataTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			
			Double importe = Double.parseDouble(pagoTarjetaCreditoResponseTO.getImporteTotal());
			pagoTarjetaCreditoResponseTO.setImporteTotal(importe.toString());
			Double comision = Double.parseDouble(pagoTarjetaCreditoResponseTO.getComision());
			pagoTarjetaCreditoResponseTO.setComision(comision.toString());
			
			forma.setComision(df.format(comision));
			
			CuentaTO cuentaTO= getAccountsPrdicate(clienteTO.getCuentas(), forma.getCuenta_cargo());
			List<PagoTarjetaCreditoResponseTO> tarjetasCredito =(ArrayList<PagoTarjetaCreditoResponseTO>)pagoTarjetaCreditoResponseTO.getTarjetasCredito();
			int indiceTarjeta = Integer.parseInt(pagoTarjetaCreditoResponseTO.getTarjeta());
			PagoTarjetaCreditoResponseTO tarjetaCredito =(PagoTarjetaCreditoResponseTO)tarjetasCredito.get(indiceTarjeta);
			
			pago_tarjeta_credito.setNumero_tarjeta(tarjetaCredito.getNumeroTarjeta());
			pago_tarjeta_credito.setTipo_tarjeta(tarjetaCredito.getTipo());
			pago_tarjeta_credito.setTitular_cuenta_cargo(tarjetaCredito.getNombreTitular());
			pago_tarjeta_credito.setTotal(pagoTarjetaCreditoResponseTO.getImporteTotal());
			pago_tarjeta_credito.setCuenta_cargo(cuentaTO.getNumero());
			pago_tarjeta_credito.setImporte(pagoTarjetaCreditoResponseTO.getImporteTotal());
			pago_tarjeta_credito.setBanco_emisor("Banco Azteca");
			pago_tarjeta_credito.setComision(pagoTarjetaCreditoResponseTO.getComision());

			response.addAttribute(pago_tarjeta_credito);
			synchronized(session){
				session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS, forma);
				session.addAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS_FORMATEADOS, pago_tarjeta_credito);
			}
//			DispositivoHuellaTO tdispositivoHuellaTO=pagoTarjetaCreditoResponseTO.getDispositivoHuellaTO();
//			HuellaTO huellaTO=new HuellaTO();
//			
//			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
//			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());
//			response.addAttribute(huellaTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {	
		Response response = new Response();
		PagoTarjetaCreditoForm datos = (PagoTarjetaCreditoForm)session.getAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS);
		Pago_tarjeta_creditoTO pago_tarjeta_credito = (Pago_tarjeta_creditoTO)session.getAttribute(PAGO_TARJETA_CREDITO_RESPONSE_DATOS_FORMATEADOS);
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO = new PagoTarjetaCreditoRequestTO();
			pagoTarjetaCreditoRequestTO.setUser(clienteTO.getUserName());
			pagoTarjetaCreditoRequestTO.setImporte(datos.getImporte());
			pagoTarjetaCreditoRequestTO.setTarjeta(datos.getNumero_tarjeta());
			pagoTarjetaCreditoRequestTO.setCuenta(datos.getCuenta_cargo());
			pagoTarjetaCreditoRequestTO.setComision(datos.getComision());
			
			resourceFacadeSL.setConfirmTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			
			synchronized(session){
				response.addAttribute(pago_tarjeta_credito);
			}
			super.updateBalance(session);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	private int getTarjetaIndex(Collection<PagoTarjetaCreditoResponseTO> tarjetas, String numeroTarjeta){
		Iterator<?> iterator = tarjetas.iterator();
		int indice = 0;
		while(iterator.hasNext()){
			PagoTarjetaCreditoResponseTO to = (PagoTarjetaCreditoResponseTO)iterator.next();
			if(to.getNumeroTarjeta().equalsIgnoreCase(numeroTarjeta)){
				return indice;
			}
			indice++;
		}
		return -1;
	}
	
}
