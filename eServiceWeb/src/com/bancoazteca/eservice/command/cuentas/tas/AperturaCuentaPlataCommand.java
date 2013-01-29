package com.bancoazteca.eservice.command.cuentas.tas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJBException;

import com.bancoazteca.elite.beans.AperturaCuentaPlataRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaPlataResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConfirmacionAperturaCuentaPlataTO;
import com.bancoazteca.elite.beans.CotizacionOnzaPlataLibertadTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Ejecucion_apertura_cuenta_plataTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Solicitud_apertura_cuenta_plataTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AperturaCuentaPlataCommand extends CommandBase {

	@SuppressWarnings("unchecked")
	public Response solicitud(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException, IOException{

		ClienteTO clienteTO =(ClienteTO)session.getAttribute(CLIENTE_TO);
		AperturaCuentaPlataFrom forma = (AperturaCuentaPlataFrom) getFormBean();
		forma.setAceptar_contrato("on");
		
		Response response=null;

		AperturaCuentaPlataRequestTO cuentaPlataRequestTO = new AperturaCuentaPlataRequestTO();

		try{

			ResourceFacadeSL bean = getDelegate();
			cuentaPlataRequestTO.setUser(clienteTO.getUserName());



			bean.getCuentaPlataInvocacion(cuentaPlataRequestTO);
			bean.getCuentaPlataContrato(cuentaPlataRequestTO);


			cuentaPlataRequestTO.setAceptar(forma.getAceptar_contrato());
			cuentaPlataRequestTO.setAceptarContrato(forma.getAceptar_contrato());
			AperturaCuentaPlataResponseTO cuentaPlataResponseTO = bean.getCuentaPlataAceptarContrato(cuentaPlataRequestTO);

			session.addAttribute(CUENTA_PLATA_RESPONSE, cuentaPlataResponseTO);

			Collection<CuentaLoTO>cuentas = cuentaPlataResponseTO.getCuentas();
			Cuenta_CargoTO cuenta_cargoTO=null;
			Collection<Cuenta_CargoTO> collectionCuentasCargoTO=new ArrayList<Cuenta_CargoTO>();

			for(CuentaLoTO cuenta:cuentas)
			{
				CuentaTO cuentaClieteTO = super.getAccountsPrdicate(clienteTO.getCuentas(),Formatter.removeSpaces(cuenta.getCuentaFormateada()));
				if(cuentaClieteTO!=null){
					cuenta_cargoTO=new Cuenta_CargoTO();
					cuenta_cargoTO.setNumero_cuenta(Formatter.removeSpaces(cuenta.getCuentaFormateada()));
					cuenta_cargoTO.setProducto(cuentaClieteTO.getDescripcion());
					cuenta_cargoTO.setSaldo_disponible(cuentaClieteTO.getDisponible().toString());						
					collectionCuentasCargoTO.add(cuenta_cargoTO);
				}
			}
			response=new Response();

			String contrato_plata=PropertiesService.getInstance().getPropertie("AperturaCuentaPlata.properties","apertura.cuenta.plata.contrato.plata");
			String contrato_productos_servicios=PropertiesService.getInstance().getPropertie("AperturaCuentaPlata.properties","apertura.cuenta.plata.contrato.productos.servicios");
			Solicitud_apertura_cuenta_plataTO solicitud_apertura_cuenta_plataTO=new Solicitud_apertura_cuenta_plataTO();

			solicitud_apertura_cuenta_plataTO.setColeccion_cuentas(collectionCuentasCargoTO);
			solicitud_apertura_cuenta_plataTO.setContrato_plata(contrato_plata);
			solicitud_apertura_cuenta_plataTO.setContrato_productos_servicios(contrato_productos_servicios);

			response.addAttribute(solicitud_apertura_cuenta_plataTO);

		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;				
	}

	@SuppressWarnings("unchecked")
	public Response validacion(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException{

		ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
		AperturaCuentaPlataFrom forma = (AperturaCuentaPlataFrom) getFormBean();

		if(forma.getAceptar_contrato().equalsIgnoreCase("si"))
		{
			forma.setAceptar_contrato("on");
		}else forma.setAceptar_contrato("off");

		Response response=new Response();

		AperturaCuentaPlataRequestTO cuentaPlataRequestTO = new AperturaCuentaPlataRequestTO();
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = (AperturaCuentaPlataResponseTO) session.getAttribute(CUENTA_PLATA_RESPONSE);
		String referencia = cuentaPlataResponseTO.getReferencia();
		String minimoApertura = cuentaPlataResponseTO.getMinimoApertura();
		Collection<CuentaLoTO> cuentas = cuentaPlataResponseTO.getCuentas();
		Collection<CotizacionOnzaPlataLibertadTO> cotizaciones = cuentaPlataResponseTO.getCotizaciones();
		try{
			ResourceFacadeSL bean = getDelegate();

			cuentaPlataRequestTO.setUser(clienteTO.getUserName());
			cuentaPlataRequestTO.setCuentaCargo(forma.getCuenta_cargo());
			cuentaPlataRequestTO.setMontoMonedas(forma.getMonto_apertura());

			Collection<BeneficiarioTO> coleccionBeneficiariosTO=forma.getColeccion_beneficiarios();
			Iterator<BeneficiarioTO> iteraBeneficiados= coleccionBeneficiariosTO.iterator();


			BeneficiarioTO beneficiario=iteraBeneficiados.next();
			cuentaPlataRequestTO.setNombresBenUno(beneficiario.getNombre_beneficiario());
			cuentaPlataRequestTO.setPaternoBenUno(beneficiario.getPrimer_apellido());
			cuentaPlataRequestTO.setMaternoBenUno(beneficiario.getSegundo_apellido());
			cuentaPlataRequestTO.setPorcentajeBenUno(beneficiario.getPorcentaje());

			if(iteraBeneficiados.hasNext()){
				beneficiario=iteraBeneficiados.next();
				cuentaPlataRequestTO.setNombresBenDos(beneficiario.getNombre_beneficiario());
				cuentaPlataRequestTO.setPaternoBenDos(beneficiario.getPrimer_apellido());
				cuentaPlataRequestTO.setMaternoBenDos(beneficiario.getSegundo_apellido());
				cuentaPlataRequestTO.setPorcentajeBenDos(beneficiario.getPorcentaje());
			}
			if(iteraBeneficiados.hasNext()){
				beneficiario=iteraBeneficiados.next();
				cuentaPlataRequestTO.setNombresBenTres(beneficiario.getNombre_beneficiario());
				cuentaPlataRequestTO.setPaternoBenTres(beneficiario.getPrimer_apellido());
				cuentaPlataRequestTO.setMaternoBenTres(beneficiario.getSegundo_apellido());
				cuentaPlataRequestTO.setPorcentajeBenTres(beneficiario.getPorcentaje());
			}
			if(iteraBeneficiados.hasNext()){
				beneficiario=iteraBeneficiados.next();
				cuentaPlataRequestTO.setNombresBenCuatro(beneficiario.getNombre_beneficiario());
				cuentaPlataRequestTO.setPaternoBenCuatro(beneficiario.getPrimer_apellido());
				cuentaPlataRequestTO.setMaternoBenCuatro(beneficiario.getSegundo_apellido());
				cuentaPlataRequestTO.setPorcentajeBenCuatro(beneficiario.getPorcentaje());
			}

			cuentaPlataRequestTO.setNipPlata(forma.getNip_cuenta().toString());
			cuentaPlataRequestTO.setNipPlataAgain(forma.getNip_cuenta().toString());


			cuentaPlataRequestTO.setUtil("getPrecioMoneda");
			cuentaPlataRequestTO.setUtilConfirmacion("precioMoneda");
			bean.getCuentaPlataEnvioDatos(cuentaPlataRequestTO);


			cuentaPlataRequestTO.setUtil("goToData");
			cuentaPlataRequestTO.setUtilConfirmacion("getData");

			cuentaPlataResponseTO = bean.getCuentaPlataEnvioDatos(cuentaPlataRequestTO);
			cuentaPlataResponseTO.setReferencia(referencia);
			cuentaPlataResponseTO.setMinimoApertura(minimoApertura);
			cuentaPlataResponseTO.setCuentas(cuentas);
			cuentaPlataResponseTO.setCotizaciones(cotizaciones);


			cuentaPlataResponseTO.setCuentaCargoFormateada(forma.getCuenta_cargo());
			session.addAttribute(CUENTA_PLATA_RESPONSE, cuentaPlataResponseTO);
			if(cuentaPlataResponseTO.getDispositivoHuellaTO() != null) {
				session.addAttribute(DISPOSITIVO_HUELLA, cuentaPlataResponseTO.getDispositivoHuellaTO());
			}		

			DispositivoHuellaTO tdispositivoHuellaTO=cuentaPlataResponseTO.getDispositivoHuellaTO();

			HuellaTO huellaTO=new HuellaTO();

			huellaTO.setLlave_publica(tdispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(tdispositivoHuellaTO.getLongitudHuella());

			response.addAttribute(huellaTO);

		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session) throws ServiceLocatorException, EJBException, CuentasException, SessionExpiredException, LoginException, UsuarioException{

		ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
		AperturaCuentaPlataFrom forma = (AperturaCuentaPlataFrom) getFormBean();
		Response response=new Response();
		AperturaCuentaPlataRequestTO cuentaPlataRequestTO = new AperturaCuentaPlataRequestTO();
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = (AperturaCuentaPlataResponseTO) session.getAttribute(CUENTA_PLATA_RESPONSE);

		try{
			ResourceFacadeSL bean = getDelegate();
			cuentaPlataRequestTO.setUser(clienteTO.getUserName());

			String opc_seg=forma.getOpcion_seguridad();

			String cta_cargo=cuentaPlataResponseTO.getCuentaCargoFormateada();
			String cta_cargo_temp="";
			Collection<CuentaLoTO>cuentasLo=cuentaPlataResponseTO.getCuentas();


			for (CuentaLoTO cuentaLo : cuentasLo) {

				cta_cargo_temp=Formatter.removeSpaces(cuentaLo.getCuentaFormateada());

				if(cta_cargo.equals(cta_cargo_temp))
				{
					cta_cargo_temp=cuentaLo.getCuenta();

					Matcher mtch;
					Pattern p=Pattern.compile("([A-Z]*[A-Z]\\s)+");
					mtch=p.matcher(cta_cargo_temp);
					if(mtch.find())
						cta_cargo_temp=cta_cargo_temp.substring(mtch.start(),mtch.end());

					break;
				}
			}



			if(opc_seg.equalsIgnoreCase(TAG_HUELLA)){
				cuentaPlataRequestTO.setOptionDispositive(OPCION_HUELLA);
				cuentaPlataRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else{
				cuentaPlataRequestTO.setOptionDispositive(OPCION_TOKEN);
				cuentaPlataRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}


			AperturaCuentaPlataResponseTO ctaPlataEjecucionResponseTO = bean.getCuentaPlataEjecutar(cuentaPlataRequestTO);
			cuentaPlataResponseTO.setCtaPlata(ctaPlataEjecucionResponseTO.getCtaPlata());
			cuentaPlataResponseTO.setFolioPlata(ctaPlataEjecucionResponseTO.getFolioPlata());



			super.updateBalance(session);

			ConfirmacionAperturaCuentaPlataTO confirmacionAperturaCuentaPlataTO=cuentaPlataResponseTO.getAperturaCuentaPlataTO();
			Collection<BeneficiarioTO> coleccionBeneficiarios= new ArrayList<BeneficiarioTO>();
			BeneficiarioTO beneficiarioTO=new BeneficiarioTO();

			beneficiarioTO.setNombre_beneficiario(confirmacionAperturaCuentaPlataTO.getNombresBenUno());
			beneficiarioTO.setPrimer_apellido(confirmacionAperturaCuentaPlataTO.getPaternoBenUno());
			beneficiarioTO.setSegundo_apellido(confirmacionAperturaCuentaPlataTO.getMaternoBenUno());
			beneficiarioTO.setPorcentaje(confirmacionAperturaCuentaPlataTO.getPorcentajeBenUno());
			coleccionBeneficiarios.add(beneficiarioTO);

			if(confirmacionAperturaCuentaPlataTO.getNombresBenDos()!=null && !confirmacionAperturaCuentaPlataTO.getNombresBenDos().equalsIgnoreCase(""))
			{
				beneficiarioTO=new BeneficiarioTO();
				beneficiarioTO.setNombre_beneficiario(confirmacionAperturaCuentaPlataTO.getNombresBenDos());
				beneficiarioTO.setPrimer_apellido(confirmacionAperturaCuentaPlataTO.getPaternoBenDos());
				beneficiarioTO.setSegundo_apellido(confirmacionAperturaCuentaPlataTO.getMaternoBenDos());
				beneficiarioTO.setPorcentaje(confirmacionAperturaCuentaPlataTO.getPorcentajeBenDos());
				coleccionBeneficiarios.add(beneficiarioTO);
			}

			if(confirmacionAperturaCuentaPlataTO.getNombresBenTres()!=null && !confirmacionAperturaCuentaPlataTO.getNombresBenTres().equalsIgnoreCase(""))
			{
				beneficiarioTO=new BeneficiarioTO();
				beneficiarioTO.setNombre_beneficiario(confirmacionAperturaCuentaPlataTO.getNombresBenTres());
				beneficiarioTO.setPrimer_apellido(confirmacionAperturaCuentaPlataTO.getPaternoBenTres());
				beneficiarioTO.setSegundo_apellido(confirmacionAperturaCuentaPlataTO.getMaternoBenTres());
				beneficiarioTO.setPorcentaje(confirmacionAperturaCuentaPlataTO.getPorcentajeBenTres());
				coleccionBeneficiarios.add(beneficiarioTO);
			}
			if(confirmacionAperturaCuentaPlataTO.getNombresBenCuatro()!=null && !confirmacionAperturaCuentaPlataTO.getNombresBenCuatro().equalsIgnoreCase(""))
			{
				beneficiarioTO=new BeneficiarioTO();
				beneficiarioTO.setNombre_beneficiario(confirmacionAperturaCuentaPlataTO.getNombresBenCuatro());
				beneficiarioTO.setPrimer_apellido(confirmacionAperturaCuentaPlataTO.getPaternoBenCuatro());
				beneficiarioTO.setSegundo_apellido(confirmacionAperturaCuentaPlataTO.getMaternoBenCuatro());
				beneficiarioTO.setPorcentaje(confirmacionAperturaCuentaPlataTO.getPorcentajeBenCuatro());
				coleccionBeneficiarios.add(beneficiarioTO);
			}


			Ejecucion_apertura_cuenta_plataTO ejecucion_apertura_cuenta_plataTO=new Ejecucion_apertura_cuenta_plataTO();

			ejecucion_apertura_cuenta_plataTO.setColeccion_beneficiarios(coleccionBeneficiarios);
			ejecucion_apertura_cuenta_plataTO.setCuenta_cargo(cuentaPlataResponseTO.getCuentaCargoFormateada());
			ejecucion_apertura_cuenta_plataTO.setPrecio_moneda(Formatter.removeSpaces(cuentaPlataResponseTO.getPrecioMoneda()));
			ejecucion_apertura_cuenta_plataTO.setMonto_apertura_monedas(cuentaPlataResponseTO.getAperturaCuentaPlataTO().getMontoMonedas());
			ejecucion_apertura_cuenta_plataTO.setMonto_apertura_precio(cuentaPlataResponseTO.getMontoNPesos());
			ejecucion_apertura_cuenta_plataTO.setReferencia_cuenta_cargo(cuentaPlataResponseTO.getReferencia());

			ejecucion_apertura_cuenta_plataTO.setProducto_cuenta_cargo(cta_cargo_temp);
			ejecucion_apertura_cuenta_plataTO.setIva_comision("0.0");
			ejecucion_apertura_cuenta_plataTO.setComision("0.0");


			response.addAttribute(ejecucion_apertura_cuenta_plataTO);

		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;	
	}

}