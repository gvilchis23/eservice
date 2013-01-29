package com.bancoazteca.eservice.command.cuentas.socioplus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusRequestTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusResponseTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Apertura_cuenta_socio_plusTO;
import com.bancoazteca.eservice.command.base.beans.BeneficiarioTO;
import com.bancoazteca.eservice.command.base.beans.Catalogo_ParentescoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_socio_plusTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Resumen_cuenta_socio_plusTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
/**
 * La clase CuentaSocioPlusCommand es la implementación del comando de aperturas de cuenta 
 * socio plus, la apertura se realiza invocando al los métodos del ResourceFacadeSL. <br/>
 * Así como también esta clase es la encargada de subir al mapa de respuesta los datos 
 * necesarios para que el serializador los transforme en xml.
 * En el bloque estático se inicializa  un catalogo que en el portal 
 * de personas físicas no se sube a sesión y se es necesario mostrar en la vista.
 * 
 */
public class CuentaSocioPlusCommand extends CommandBase {
	private static final String ACEPTAR_CONTRATO="on";
	private static final String FORMATER_FECHA_VALOR_UNIDAD="dd 'de' MMMM 'de' yyyy";
	
	private static final List<Catalogo_ParentescoTO> parentescos;
	static {
		parentescos=new ArrayList<Catalogo_ParentescoTO>();
		parentescos.add(new Catalogo_ParentescoTO("0","Padre"));
		parentescos.add(new Catalogo_ParentescoTO("1","Madre"));
		parentescos.add(new Catalogo_ParentescoTO("2","Hermano(a)"));
		parentescos.add(new Catalogo_ParentescoTO("3","Hijo(a)"));
		parentescos.add(new Catalogo_ParentescoTO("4","Esposo(a)"));
		parentescos.add(new Catalogo_ParentescoTO("5","Otros"));
	}
	
	public Response solicitud(Session session) throws Exception {
		Response response = new Response();
		Cuenta_socio_plusTO cuenta_socio_plus= new Cuenta_socio_plusTO();
		ResourceFacadeSL bean = getDelegate();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO = new CuentaSocioPlusRequestTO();	
		cuentaSocioPlusRequestTO.setUser(clienteTO.getUserName());
		try{
			//TODO ERC eliminar hc
			CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO =bean.validaCuentaSocioPlus(cuentaSocioPlusRequestTO);
			if( new Boolean(cuentaSocioPlusResponseTO.getValidaCuentaSocioPlus()).booleanValue() ){
				bean.setPartnerPlusStartOpenAccount(cuentaSocioPlusRequestTO);
				cuentaSocioPlusRequestTO.setAceptar(ACEPTAR_CONTRATO);
				cuentaSocioPlusResponseTO = bean.setPartnerPlusStartAceptConditions(cuentaSocioPlusRequestTO);
				String indices_cuentas="";
				for(CuentaLoTO cuentaLoTO:cuentaSocioPlusResponseTO.getCuentas()){
					indices_cuentas=cuentaLoTO.getIndex()+"|";
				}
				Collection<CuentaTO> cuentas = clienteTO.getCuentas();
				Collection<Cuenta_CargoTO> cuentas_cargo= new ArrayList<Cuenta_CargoTO>();
				Cuenta_CargoTO temp;
				for(CuentaTO obj: cuentas){
					if(indices_cuentas.contains(obj.getIndex())){
						temp= new Cuenta_CargoTO();
						String cuentaFormateada = Formatter.removeSpaces(formatearCuenta(obj.getNumero()));
						temp.setNumero_cuenta(cuentaFormateada);
						temp.setProducto(obj.getDescripcion());
						temp.setSaldo_disponible(String.valueOf(obj.getBalance()));
						cuentas_cargo.add(temp);
					}
					
				}
				cuenta_socio_plus.setColeccion_cuentas(cuentas_cargo);
				cuenta_socio_plus.setMonto_minimo(Validator.isEmptyData(cuentaSocioPlusResponseTO.getMontoMinimo())?"":cuentaSocioPlusResponseTO.getMontoMinimo());
				cuenta_socio_plus.setValor_unidad(Validator.isEmptyData(cuentaSocioPlusResponseTO.getValorUnidad())?"":cuentaSocioPlusResponseTO.getValorUnidad());
				cuenta_socio_plus.setFecha_valor_unidad(Formatter.formatFecha(FORMATER_FECHA_VALOR_UNIDAD, new Date()));
				
				cuenta_socio_plus.setColeccion_parentescos(parentescos);
				
				response.addAttribute(cuenta_socio_plus);
			}else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("ACCESO_DENEGADO","Estimado usuario, lo sentimos su perfil no es el apropiado para realizar la apertura de la cuenta socio plus.");
				throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
			
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		return response;
	}

	public Response validacion(Session session) throws Exception {
		Response response = new Response();
		Map<String, String> errors= new HashMap<String , String>();
		
		ResourceFacadeSL bean = getDelegate();
		
		CuentaSocioPlusForm forma = (CuentaSocioPlusForm) getFormBean();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO = new CuentaSocioPlusRequestTO();
		cuentaSocioPlusRequestTO.setUser(clienteTO.getUserName());		
		Resumen_cuenta_socio_plusTO resumen_apertura= new Resumen_cuenta_socio_plusTO();
		try{	
			String[] primerNombreArray 		= new String[5];
			String[] segundoNombreArray 	= new String[5];
			String[] primerApellidoArray 	= new String[5];
			String[] segundoApellidoArray 	= new String[5];
			String[] edadArray 				= new String[5];
			String[] parentescoArray 		= new String[5];
			String[] porcentajeArray 		= new String[5];		
			BeneficiarioTO beneficiarioAux;
			ArrayList<BeneficiarioTO> beneficiarios=(ArrayList<BeneficiarioTO>)forma.getColeccion_beneficiarios();
			double porcentaje_total=0;
			if(beneficiarios != null && beneficiarios.size() > 0)
			for (int i=0 ; i < beneficiarios.size() ; i++){
				
				beneficiarioAux= beneficiarios.get(i);
				String primerNombre = beneficiarioAux.getNombre_beneficiario();
				String segundoNombre = beneficiarioAux.getSegundo_nombre_beneficiario();
				String primerApellido = beneficiarioAux.getPrimer_apellido();
				String segundoApellido = beneficiarioAux.getSegundo_apellido();
				String edad = beneficiarioAux.getEdad();		
				String parentesco = beneficiarioAux.getParentesco();
				String porcentaje = beneficiarioAux.getPorcentaje();
				
				porcentaje_total+=Double.parseDouble(porcentaje);
				if( porcentaje_total > 100){
					errors.put("beneficiarios","El porcentaje de beneficiarios no debe de exceder del 100%." );
					return returnErrorMap(response, errors);
				}
				
				if (primerNombre!=null){
					primerNombreArray[i] = primerNombre.toString();
					segundoNombreArray[i] = segundoNombre.toString();
					primerApellidoArray[i] = primerApellido.toString();
					segundoApellidoArray[i] = segundoApellido.toString();
					edadArray[i] = edad.toString();
					parentescoArray[i] = parentesco.toString();
					porcentajeArray[i] = porcentaje.toString();
				}
				
			}
			cuentaSocioPlusRequestTO.setPrimerNombre(primerNombreArray);
			cuentaSocioPlusRequestTO.setSegundoNombre(segundoNombreArray);
			cuentaSocioPlusRequestTO.setPrimerApellido(primerApellidoArray);
			cuentaSocioPlusRequestTO.setSegundoApellido(segundoApellidoArray);
			cuentaSocioPlusRequestTO.setEdad(edadArray);
			cuentaSocioPlusRequestTO.setParentesco(parentescoArray);
			cuentaSocioPlusRequestTO.setPorcentaje(porcentajeArray);
			
			cuentaSocioPlusRequestTO.setMontoInvertir(forma.getMonto_apertura());
			CuentaTO cuentaTO = getAccountsPrdicate(clienteTO.getCuentas(), forma.getCuenta_cargo());
			cuentaSocioPlusRequestTO.setCuentaOrigen(cuentaTO.getIndex());
			
			Collection<BeneficiarioTO> auxiliarBeneficiarios= new ArrayList<BeneficiarioTO>();
			CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = bean.setPartnerPlusData(cuentaSocioPlusRequestTO);
			if( cuentaSocioPlusResponseTO != null && cuentaSocioPlusResponseTO.getSocioPlusTO() != null ){
				Collection<com.bancoazteca.elite.beans.BeneficiarioTO> coleccionBeneficiariosSocioPlus = cuentaSocioPlusResponseTO.getSocioPlusTO().getBeneficiarios(); 
				
				BeneficiarioTO obj;
				if(coleccionBeneficiariosSocioPlus != null){
					for(com.bancoazteca.elite.beans.BeneficiarioTO objeto:coleccionBeneficiariosSocioPlus){
						obj= new BeneficiarioTO();
						obj.setNombre_beneficiario(Validator.isEmptyData(objeto.getNomBeneficiario())?"":objeto.getNomBeneficiario());
						obj.setSegundo_nombre_beneficiario(Validator.isEmptyData(objeto.getNomBeneficiario2())?"":objeto.getNomBeneficiario2());
						obj.setPrimer_apellido(Validator.isEmptyData(objeto.getApPaterno())?"":objeto.getApPaterno());
						obj.setSegundo_apellido(Validator.isEmptyData(objeto.getApMaterno())?"":objeto.getApMaterno());
						obj.setParentesco(Validator.isEmptyData(objeto.getParentesco())?"":objeto.getParentesco());
						obj.setPorcentaje(Validator.isEmptyData(objeto.getPorcentaje())?"":objeto.getPorcentaje());
						obj.setPorcentaje(Validator.isEmptyData(objeto.getPorcentaje())?"":objeto.getPorcentaje());
						auxiliarBeneficiarios.add(obj);
					}
				}
			}
			resumen_apertura.setCuenta_cargo(Validator.isEmptyData(cuentaSocioPlusResponseTO.getSocioPlusTO().getDescCuenta())?"":cuentaSocioPlusResponseTO.getSocioPlusTO().getDescCuenta());
			resumen_apertura.setMonto_invertir(Validator.isEmptyData(String.valueOf(cuentaSocioPlusResponseTO.getSocioPlusTO().getMonto()))?"":String.valueOf(cuentaSocioPlusResponseTO.getSocioPlusTO().getMonto()));
			resumen_apertura.setComision("0.00");
			resumen_apertura.setIva_comision("0.00");
			resumen_apertura.setTotal_unidades(Validator.isEmptyData(cuentaSocioPlusResponseTO.getSocioPlusTO().getNumUnidades())?"":cuentaSocioPlusResponseTO.getSocioPlusTO().getNumUnidades());
			resumen_apertura.setReferencia("Apertura Cuenta Socio Plus");
			resumen_apertura.setValor_unidad(Validator.isEmptyData(cuentaSocioPlusResponseTO.getSocioPlusTO().getValorUnidad())?"":cuentaSocioPlusResponseTO.getSocioPlusTO().getValorUnidad());
			resumen_apertura.setFecha_valor_unidad(Formatter.formatFecha(FORMATER_FECHA_VALOR_UNIDAD, new Date()));
			resumen_apertura.setColeccion_beneficiarios(auxiliarBeneficiarios);
			
			DispositivoHuellaTO dispositivoHuellaTO=cuentaSocioPlusResponseTO.getDispositivoHuellaTO();
			HuellaTO huellaTO=new HuellaTO();
			
			huellaTO.setLlave_publica(dispositivoHuellaTO.getLlavePublica());
			huellaTO.setLongitud_huella(dispositivoHuellaTO.getLongitudHuella());
			
			session.addAttribute(SOCIO_PLUS_RESPONSE, resumen_apertura);
			
			response.addAttribute(resumen_apertura);
			response.addAttribute(huellaTO);
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception {
		Response response= new Response();
		Map<String, String> errors= new HashMap<String, String>();
		Apertura_cuenta_socio_plusTO  apertura_cuenta_socio_plus= new Apertura_cuenta_socio_plusTO();
		
		ResourceFacadeSL bean = getDelegate();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO = new CuentaSocioPlusRequestTO();
		cuentaSocioPlusRequestTO.setUser(clienteTO.getUserName());	
		CuentaSocioPlusForm forma = (CuentaSocioPlusForm) getFormBean();
		Resumen_cuenta_socio_plusTO datos=(Resumen_cuenta_socio_plusTO)session.getAttribute(SOCIO_PLUS_RESPONSE);
		try{					
			String opc_seg=forma.getOpcion_seguridad();
			 
			if(opc_seg.equalsIgnoreCase(TAG_HUELLA)){
				cuentaSocioPlusRequestTO.setOptionDispositive(Integer.parseInt(OPCION_HUELLA));
				cuentaSocioPlusRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else{
				cuentaSocioPlusRequestTO.setOptionDispositive(Integer.parseInt(OPCION_TOKEN));
				cuentaSocioPlusRequestTO.setTokenCode(forma.getClave_seguridad().toString());
			}
			CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO=bean.setPartnerPlusConfirmData(cuentaSocioPlusRequestTO);
			if(		cuentaSocioPlusResponseTO != null && 
					cuentaSocioPlusResponseTO.getClienteTO() != null && 
					cuentaSocioPlusResponseTO.getClienteTO().getCuentas()!= null ){
				clienteTO.setCuentas(cuentaSocioPlusResponseTO.getClienteTO().getCuentas());
				session.addAttribute(CLIENTE_TO, clienteTO);
				LoginRequestTO loginRequestTO = new LoginRequestTO();
				loginRequestTO.setUser(clienteTO.getAlias());
				loginRequestTO.setReload(true);
				bean.getOnDemandAccounts(loginRequestTO);
			}
			
			apertura_cuenta_socio_plus.setCuenta_cargo( Validator.isEmptyData(cuentaSocioPlusResponseTO.getCuentaCargo())? "" : cuentaSocioPlusResponseTO.getCuentaCargo() );
			apertura_cuenta_socio_plus.setMonto_invertir(datos.getMonto_invertir());
			apertura_cuenta_socio_plus.setComision(datos.getComision());
			apertura_cuenta_socio_plus.setIva_comision(datos.getIva_comision());
			apertura_cuenta_socio_plus.setTotal_unidades(datos.getTotal_unidades());
			apertura_cuenta_socio_plus.setReferencia(datos.getReferencia());
			apertura_cuenta_socio_plus.setValor_unidad(datos.getValor_unidad());
			
			
			
			apertura_cuenta_socio_plus.setFecha_valor_unidad(datos.getFecha_valor_unidad());
			apertura_cuenta_socio_plus.setColeccion_beneficiarios(datos.getColeccion_beneficiarios());
			
			apertura_cuenta_socio_plus.setNumero_cuenta_socio_plus(Validator.isEmptyData(cuentaSocioPlusResponseTO.getNumeroCuentaSocioPlus())?"":cuentaSocioPlusResponseTO.getNumeroCuentaSocioPlus());
			apertura_cuenta_socio_plus.setUnidades_adquiridas(Validator.isEmptyData(cuentaSocioPlusResponseTO.getUnidadesAdquiridas())?"":cuentaSocioPlusResponseTO.getUnidadesAdquiridas());
			apertura_cuenta_socio_plus.setFolio_apertura(Validator.isEmptyData(cuentaSocioPlusResponseTO.getFolioApertura())?"":cuentaSocioPlusResponseTO.getFolioApertura());
			
			response.addAttribute(apertura_cuenta_socio_plus);
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}finally{
			forma.setClave_seguridad(null);
			forma.setHuella_seguridad(null);
		}
		return response;
	}
	
	public Response pdf(Session session) throws Exception {
		Response response= new Response();
		ResourceFacadeSL bean = getDelegate();
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO = new CuentaSocioPlusRequestTO();
		cuentaSocioPlusRequestTO.setUser(clienteTO.getUserName());		
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = bean.getSocioPlusPDF(cuentaSocioPlusRequestTO);
		byte[] pdf = cuentaSocioPlusResponseTO.getPdf();
		session.addAttribute("SOCIO_PLUS_PDF", pdf);
		return response;
	}
	
}
