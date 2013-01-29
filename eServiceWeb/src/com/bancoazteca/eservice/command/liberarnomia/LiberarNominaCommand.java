package com.bancoazteca.eservice.command.liberarnomia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import com.bancoazteca.elite.beans.ArchivoNominaTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.ERecibosNominaRequestTO;
import com.bancoazteca.elite.beans.ERecibosNominaResponseTO;
import com.bancoazteca.elite.beans.ReciboCabeceraTO;
import com.bancoazteca.elite.beans.ReciboDetalleTO;
import com.bancoazteca.elite.beans.ReciboMensajeTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.CuentaTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_pagoTO;
import com.bancoazteca.eservice.command.base.beans.Generalidades_recibo_electronicoTO;
import com.bancoazteca.eservice.command.base.beans.Acuerdo_conformidadTO;
import com.bancoazteca.eservice.command.base.beans.Recibo_ElectronicoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_recibo_electronicoTO;
import com.bancoazteca.eservice.command.base.beans.Recibos_ElectronicosTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class LiberarNominaCommand extends CommandBase {
	private final String RECIBOS_ELECTRONICOS="recibos_electronicos";
	private final String RECIBOS_NOMINA_HUELLA="recibosNomina";
	private final String HUELLA="huella_recibos";
	private final int MAXIMO_NUMERO_LIBERACIONES=2;
	
	public Response solicitud(Session session) throws Exception{
		Response response=new Response();
		ResourceFacadeSL facade=getDelegate();
		ArrayList<Recibo_ElectronicoTO> coleccion_recibos_electronicos=new ArrayList<Recibo_ElectronicoTO>();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ERecibosNominaRequestTO request=new ERecibosNominaRequestTO();
			request.setUser(clienteTO.getUserName());
			request.setFlagPathUpload(false);
			ERecibosNominaResponseTO recibosNomina=facade.liberaERecibosNominaInit(request);
			Recibos_ElectronicosTO recibos_electronicos=new Recibos_ElectronicosTO();
			
			if(recibosNomina.getResponseConsultaNominaRetencionesTO()!=null){
				for (ArrayList<ArchivoNominaTO> lista : recibosNomina.getTiposRecibos()) {
					if(lista!=null){
						for (ArchivoNominaTO archivoNominaTO : lista) {
						if (archivoNominaTO.getTipoCheckbox().equalsIgnoreCase("00")) {
							System.out.println("El recibo "+archivoNominaTO.getLlave()+ " no sera mostrado por que ya fue liberado, libere el concentrado.");
							break;
						}
						Recibo_ElectronicoTO electronicoTO = new Recibo_ElectronicoTO();
						electronicoTO.setConcentrado(archivoNominaTO.getBanderaSumatoria());
						electronicoTO.setFolio_operacion(archivoNominaTO.getFolioOperacion());
						electronicoTO.setNss_empleado(archivoNominaTO.getNssEmpleado());
						electronicoTO.setNumero_cuenta_abono(archivoNominaTO.getCuentaAbono());
						electronicoTO.setClave_compania(archivoNominaTO.getCompania());
						electronicoTO.setNumero_recibos(archivoNominaTO.getTipoCheckbox());
						electronicoTO.setMonto_deducciones(archivoNominaTO.getDeducciones());
						electronicoTO.setId_recibo(archivoNominaTO.getLlave());
						electronicoTO.setFecha_inicio_periodo(archivoNominaTO.getFechaInicioPeriodo());
						electronicoTO.setConcepto_pago(archivoNominaTO.getConcepto());
						electronicoTO.setRfc_empleado(archivoNominaTO.getRfcEmpleado());
						electronicoTO.setMonto_percepcion(archivoNominaTO.getPercepciones());
						electronicoTO.setNumero_periodo(archivoNominaTO.getNumPeriodo());
						electronicoTO.setNumero_registro(archivoNominaTO.getNumeroRegistro());	
						electronicoTO.setImporte_pago(archivoNominaTO.getImporte());
						electronicoTO.setFecha_fin_periodo(archivoNominaTO.getFechaFinPeriodo());
						
						coleccion_recibos_electronicos.add(electronicoTO);
					}
					}
				}
			}
			
			if(coleccion_recibos_electronicos != null && coleccion_recibos_electronicos.size()>0){
				if(coleccion_recibos_electronicos.size()<3){
					recibos_electronicos.setLiberacion_multiple("false");
				}else{
					recibos_electronicos.setLiberacion_multiple("true");
				}
			}
			
			//huella
			DispositivoHuellaTO dispositivoHuellaTO = recibosNomina.getDispositivoHuellaTO();
			HuellaTO huellaTO = new HuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}				
			
			CuentaTO cuentaNomina = new CuentaTO();
			cuentaNomina.setNumero_cuenta(recibosNomina.getNominaLo().getCuenta().replaceAll(" ", ""));
			cuentaNomina.setCuenta_clabe(recibosNomina.getCuentaTO().getClabe());
			Double disponible=recibosNomina.getCuentaTO().getDisponible();
			if(disponible!=null){
				cuentaNomina.setSaldo_disponible(disponible.toString());
			}
			Double retenido=recibosNomina.getCuentaTO().getRetenido();
			if(retenido!=null){
				cuentaNomina.setSaldo_retenido(retenido.toString());
			}
			Double total=recibosNomina.getCuentaTO().getBalance();
			if(total!=null){
				cuentaNomina.setSaldo_total(total.toString());
			}
			
			recibos_electronicos.setColeccion_recibos_electronicos(coleccion_recibos_electronicos);
			recibos_electronicos.setCuenta(cuentaNomina);
			
			session.addAttribute(RECIBOS_ELECTRONICOS, recibos_electronicos);
//			session.addAttribute(HUELLA, huellaTO);
			response.addAttribute(huellaTO);
			
			response.addAttribute(recibos_electronicos);
		} catch (EliteDataException e) {
			e.printStackTrace();
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response validacion( Session session )throws Exception {
		
		Response response=new Response();
		
		//response=ver_detalle(session);
		Recibos_ElectronicosTO recibos_electronicos = (Recibos_ElectronicosTO)session.getAttribute(RECIBOS_ELECTRONICOS);
		HuellaTO huella = (HuellaTO)session.getAttribute(HUELLA);
		
		LiberarNominaForm forma=(LiberarNominaForm)getFormBean();
		
		Collection<Recibo_ElectronicoTO> recibosElectronicos=recibos_electronicos.getColeccion_recibos_electronicos();
		ArrayList<Recibo_ElectronicoTO> recibosElectronicosSeleccionados=new ArrayList<Recibo_ElectronicoTO>();
				
		if(recibosElectronicos != null && !recibosElectronicos.isEmpty() ){
			int numeroRecibos=recibosElectronicos.size();
			String colecionRecibos=forma.getColeccion_recibos();
			StringTokenizer tokenizer=new StringTokenizer(colecionRecibos,",");
			if(numeroRecibos>MAXIMO_NUMERO_LIBERACIONES && tokenizer.countTokens()<MAXIMO_NUMERO_LIBERACIONES+1){
				try{
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("recibos_maximos", "Debe seleccionar todos sus recibos a liberar.");
					throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
				}catch(EliteDataException e){
					buildErrorResponse(e, response);
					return response;
				}
			}
			while(tokenizer.hasMoreElements()){
				String idRecibo=(String)tokenizer.nextElement();
				Iterator<Recibo_ElectronicoTO> iterador=recibosElectronicos.iterator();
				while(iterador.hasNext()){
					Recibo_ElectronicoTO eRecibo_Electronico=iterador.next();
					eRecibo_Electronico.setConcentrado("prueba");
					if(eRecibo_Electronico.getId_recibo().equalsIgnoreCase(idRecibo)){
						System.out.println("Se selecciono el recibo: "+eRecibo_Electronico.getId_recibo());
						recibosElectronicosSeleccionados.add(eRecibo_Electronico);
						break;
					}
				}
			}
			recibos_electronicos = new Recibos_ElectronicosTO();
			recibos_electronicos.setColeccion_recibos_electronicos(recibosElectronicosSeleccionados);
		}
		
		session.addAttribute(RECIBOS_NOMINA_HUELLA, recibos_electronicos);
		
		response.addAttribute(recibos_electronicos);
		//response.addAttribute(huella);
		
		return response;
	}
	
	
	public Response ejecucion(Session session)throws Exception{
		System.out.println("iniciando ejecucion en liberacion de nomina");
		Response response=new Response(); 
		ResourceFacadeSL facade=getDelegate();

		Recibos_ElectronicosTO recibosElectronicos = (Recibos_ElectronicosTO)session.getAttribute(RECIBOS_NOMINA_HUELLA);
		ArrayList<Recibo_ElectronicoTO> coleccion_recibos_electronicos=new ArrayList<Recibo_ElectronicoTO>();
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ERecibosNominaRequestTO request=new ERecibosNominaRequestTO();
		LiberarNominaForm forma = ( LiberarNominaForm ) getFormBean();
		
		try{
			request.setUser(clienteTO.getUserName());
			System.out.println("Seteando dispositivo de seguridad");
			if (forma.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ){
				request.setClave( forma.getHuella_seguridad().toString() );
				request.setOptionDispositive( OPCION_HUELLA );
				request.setFlagEjecucion(false);
			}
			else if (forma.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ){
				request.setTokenCode( forma.getClave_seguridad().toString() );
				request.setOptionDispositive( OPCION_TOKEN );
				request.setFlagEjecucion(true);
			}
			
			System.out.println("obteniendo la coleccion de recibos seleccionados.");
			ArrayList<Recibo_ElectronicoTO> coleccionRecibos=(ArrayList<Recibo_ElectronicoTO>) recibosElectronicos.getColeccion_recibos_electronicos();
			System.out.println("Se seleccionaron: "+coleccionRecibos.size()+1+" recibos");
			
			Iterator<Recibo_ElectronicoTO> iterador=coleccionRecibos.iterator();
			String coleccionLlaves="";
			while(iterador.hasNext()){
				Recibo_ElectronicoTO to=iterador.next();
				coleccionLlaves+=to.getId_recibo()+",";
			}			

			request.setColeccionLlaves( coleccionLlaves );
			request.setMethod( "liberarFromConsulta" );
			
			System.out.println("Por realizar peticion");
			ERecibosNominaResponseTO recibosNomina=facade.liberaERecibosNominaEjecutar(request);
			System.out.println("Peticion realizada");
			
			ArrayList<Recibo_ElectronicoTO> listaRecibosLiberadosConfirmados=new ArrayList<Recibo_ElectronicoTO>();
			
				System.out.println("Por recorrer y verificar la lista de recibos seleccionados");
				if(recibosNomina.getTiposRecibos()==null||recibosNomina.getTiposRecibos().isEmpty()){
					System.out.println("Lista de recibos liberados adecuadamente");
					listaRecibosLiberadosConfirmados.addAll(recibosElectronicos.getColeccion_recibos_electronicos());
				}else{
					System.out.println("Existen recibos en la lista... verificando");
					for(Recibo_ElectronicoTO eReciboElectronicoTO : recibosElectronicos.getColeccion_recibos_electronicos()) {//coleccion de recibos seleccionados
						boolean encontrado=false;
						boolean agregado=false;
						forLista:for (ArrayList<ArchivoNominaTO> lista : recibosNomina.getTiposRecibos()) {//el array de arrays
							for (ArchivoNominaTO archivoNominaTO : lista) {
								if(archivoNominaTO.getLlave().equalsIgnoreCase(eReciboElectronicoTO.getId_recibo())){//el array con recibos
									encontrado=true;
									if(archivoNominaTO.getTipoCheckbox().equalsIgnoreCase("00")){
										listaRecibosLiberadosConfirmados.add(eReciboElectronicoTO);
										agregado=true;
										break forLista;
									}else{
										System.out.println("El recibo: "+archivoNominaTO.getLlave()+" no fue liberado");
									}
								}
							}
						}
						if(!encontrado&&!agregado){
							listaRecibosLiberadosConfirmados.add(eReciboElectronicoTO);
						}
					}
				}
//			}
			
			
			
			CuentaTO cuentaNomina = new CuentaTO();
			cuentaNomina.setNumero_cuenta(recibosNomina.getNominaLo().getCuenta().replaceAll(" ", ""));
			cuentaNomina.setCuenta_clabe(recibosNomina.getCuentaTO().getClabe());
			cuentaNomina.setProducto(recibosNomina.getCuentaTO().getDescripcion());
			Double disponible=recibosNomina.getCuentaTO().getDisponible();
			if(disponible!=null){
				cuentaNomina.setSaldo_disponible(disponible.toString());
			}
			Double retenido=recibosNomina.getCuentaTO().getRetenido();
			if(retenido!=null){
				cuentaNomina.setSaldo_retenido(retenido.toString());
			}
			Double total=recibosNomina.getCuentaTO().getBalance();
			if(total!=null){
				cuentaNomina.setSaldo_total(total.toString());
			}
			
			recibosElectronicos = new Recibos_ElectronicosTO();
			recibosElectronicos.setColeccion_recibos_electronicos(listaRecibosLiberadosConfirmados);
						
			response.addAttribute(recibosElectronicos);
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	
	public Response ver_detalle(Session session)throws Exception{
				
		System.out.println("servicio Ver detalle.........");
			
		Response response=new Response(); 
		ResourceFacadeSL facade=getDelegate();

		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		ERecibosNominaRequestTO request=new ERecibosNominaRequestTO();
		LiberarNominaForm forma = ( LiberarNominaForm ) getFormBean();
		
		ERecibosNominaResponseTO recibosNomina=null;
		
		try{
			request.setUser(clienteTO.getUserName());
			request.setMethod("detalle");
			request.setLlave(forma.getColeccion_recibos());
			
			Detalle_recibo_electronicoTO detalleRecibo = new Detalle_recibo_electronicoTO();;
			recibosNomina = facade.liberaERecibosNominaDetalle(request);
				
			if(recibosNomina.getReciboDetalle()!=null){
				
				detalleRecibo = new Detalle_recibo_electronicoTO();
				ReciboCabeceraTO reciboCabeceraTO = recibosNomina.getReciboDetalle().getCabecera();
				detalleRecibo.setGeneralidades_recibo_electronico(new Generalidades_recibo_electronicoTO());
				
				detalleRecibo.getGeneralidades_recibo_electronico().setAnio(reciboCabeceraTO.getAnio());
				detalleRecibo.getGeneralidades_recibo_electronico().setCentro_costos(reciboCabeceraTO.getCentroCostos());
				detalleRecibo.getGeneralidades_recibo_electronico().setClave_departamento(reciboCabeceraTO.getClaveDepto());
				detalleRecibo.getGeneralidades_recibo_electronico().setCredito_salario(reciboCabeceraTO.getCreditoSalario());
				detalleRecibo.getGeneralidades_recibo_electronico().setFecha_final_pago(reciboCabeceraTO.getFechaFinPago());
				detalleRecibo.getGeneralidades_recibo_electronico().setFecha_inicio_pago(reciboCabeceraTO.getFechaIniPago());
				detalleRecibo.getGeneralidades_recibo_electronico().setFecha_pago(reciboCabeceraTO.getFechaPago());
				detalleRecibo.getGeneralidades_recibo_electronico().setId_recibo(reciboCabeceraTO.getLlave());
				detalleRecibo.getGeneralidades_recibo_electronico().setNombre_compania(reciboCabeceraTO.getNombreCompania());
				detalleRecibo.getGeneralidades_recibo_electronico().setNombre_empleado(reciboCabeceraTO.getNombreEmpleado());
				detalleRecibo.getGeneralidades_recibo_electronico().setNumero_compania(reciboCabeceraTO.getNumCompania());
				detalleRecibo.getGeneralidades_recibo_electronico().setNumero_empleado(reciboCabeceraTO.getNumEmpleado());
				detalleRecibo.getGeneralidades_recibo_electronico().setNumero_detalle(reciboCabeceraTO.getNumeroDetalle());
				detalleRecibo.getGeneralidades_recibo_electronico().setNumero_empresa(reciboCabeceraTO.getNumeroEmpresa());
				detalleRecibo.getGeneralidades_recibo_electronico().setNumero_imss(reciboCabeceraTO.getNumeroIMSS());
				detalleRecibo.getGeneralidades_recibo_electronico().setPeriodo(reciboCabeceraTO.getPeriodo());
				detalleRecibo.getGeneralidades_recibo_electronico().setRegistro_patronal(reciboCabeceraTO.getRegsitroPatronal());
				detalleRecibo.getGeneralidades_recibo_electronico().setRfc_empleado(reciboCabeceraTO.getRfcEmpleado());
				detalleRecibo.getGeneralidades_recibo_electronico().setSalario_base(reciboCabeceraTO.getSalarioBaseCotiza());
				detalleRecibo.getGeneralidades_recibo_electronico().setSalario_diario(reciboCabeceraTO.getSalarioDiario());
				detalleRecibo.getGeneralidades_recibo_electronico().setTipo_compania(reciboCabeceraTO.getTipoCompania());
				detalleRecibo.getGeneralidades_recibo_electronico().setTipo_periodo(reciboCabeceraTO.getTipoPeriodo());
				detalleRecibo.getGeneralidades_recibo_electronico().setTipo_registro(reciboCabeceraTO.getTipoRegistro());
				detalleRecibo.getGeneralidades_recibo_electronico().setTotal_creditos(reciboCabeceraTO.getTotalCreditos());
				detalleRecibo.getGeneralidades_recibo_electronico().setTotal_deduccciones(reciboCabeceraTO.getTotalDeduccciones());
				detalleRecibo.getGeneralidades_recibo_electronico().setTotal_final(reciboCabeceraTO.getTotalFinal());
				detalleRecibo.getGeneralidades_recibo_electronico().setTotal_letra(reciboCabeceraTO.getTotalLetra());
				detalleRecibo.getGeneralidades_recibo_electronico().setTotal_percepciones(reciboCabeceraTO.getTotalPercepciones());
				detalleRecibo.getGeneralidades_recibo_electronico().setVales_despensa(reciboCabeceraTO.getValesDespensa());
				
				if(recibosNomina.getReciboDetalle().getMensajes()!=null){
					Iterator it = recibosNomina.getReciboDetalle().getMensajes().iterator();
					ReciboMensajeTO reciboMensajeTO = null;
					Acuerdo_conformidadTO reciboDetalleMensajeTO = new Acuerdo_conformidadTO();
					ArrayList<Acuerdo_conformidadTO> coleccion = new ArrayList<Acuerdo_conformidadTO>();
					
					while(it.hasNext()){
						reciboDetalleMensajeTO = new Acuerdo_conformidadTO();
						reciboMensajeTO = (ReciboMensajeTO) it.next();
						reciboDetalleMensajeTO.setDetalle_numero(reciboMensajeTO.getDetalleNumero());
						reciboDetalleMensajeTO.setId_recibo(reciboMensajeTO.getLlave());
						reciboDetalleMensajeTO.setMensaje(reciboMensajeTO.getMensaje());
						reciboDetalleMensajeTO.setTipo_registro(reciboMensajeTO.getTipoRegsitro());	
						coleccion.add(reciboDetalleMensajeTO);	
					}	
					
					detalleRecibo.setColeccion_acuerdos_conformidad(coleccion);
				}
				
				if(recibosNomina.getReciboDetalle().getPercepcionDeduccions()!=null){
					
				
					Iterator iteratorMensajes = recibosNomina.getReciboDetalle().getPercepcionDeduccions().iterator();
					ReciboDetalleTO reciboDetalleTO = new ReciboDetalleTO();
					
				
					Detalle_pagoTO reciboDetalleCredito = new Detalle_pagoTO();
					Detalle_pagoTO reciboDetallePercepciones = new Detalle_pagoTO();
					Detalle_pagoTO reciboDetalleDeducciones = new Detalle_pagoTO();
					
					
					ArrayList<Detalle_pagoTO> coleccionCredito = new ArrayList<Detalle_pagoTO>();
					ArrayList<Detalle_pagoTO> coleccionDeduccion = new ArrayList<Detalle_pagoTO>();
					ArrayList<Detalle_pagoTO> coleccionPercepcion = new ArrayList<Detalle_pagoTO>();
					
					
					while(iteratorMensajes.hasNext()){
						reciboDetalleDeducciones = new Detalle_pagoTO();
						reciboDetallePercepciones=new Detalle_pagoTO();
						reciboDetalleCredito=new Detalle_pagoTO();
						reciboDetalleTO = (ReciboDetalleTO)iteratorMensajes.next();
						if(reciboDetalleTO.getConcepto().equals("DED")){
							
							reciboDetalleDeducciones.setAnio(reciboDetalleTO.getAnio());
							reciboDetalleDeducciones.setConcepto(reciboDetalleTO.getConcepto());
							reciboDetalleDeducciones.setConcepto_nominal(reciboDetalleTO.getConceptoNominal());
							reciboDetalleDeducciones.setDescripcion(reciboDetalleTO.getDescripcion());
							reciboDetalleDeducciones.setDetalle_numero(reciboDetalleTO.getDetalleNumero());
							reciboDetalleDeducciones.setDias(reciboDetalleTO.getDias());
							reciboDetalleDeducciones.setImporte(reciboDetalleTO.getImporte());
							reciboDetalleDeducciones.setId_recibo(reciboDetalleTO.getLlave());
							reciboDetalleDeducciones.setNumero_compania(reciboDetalleTO.getNumCompania());
							reciboDetalleDeducciones.setNumero_empleado(reciboDetalleTO.getNumEmpleado());
							reciboDetalleDeducciones.setNumero_empresa(reciboDetalleTO.getNumEmpresa());
							reciboDetalleDeducciones.setPeriodo(reciboDetalleTO.getPeriodo());
							reciboDetalleDeducciones.setTipo_compania(reciboDetalleTO.getTipoCompania());
							reciboDetalleDeducciones.setTipo_periodo(reciboDetalleTO.getTipoPeriodo());
							reciboDetalleDeducciones.setTipo_registro(reciboDetalleTO.getTipoRegsitro());
						
							coleccionDeduccion.add(reciboDetalleDeducciones);		
						}else if(reciboDetalleTO.getConcepto().equals("PER")){
							
							reciboDetallePercepciones.setAnio(reciboDetalleTO.getAnio());
							reciboDetallePercepciones.setConcepto(reciboDetalleTO.getConcepto());
							reciboDetallePercepciones.setConcepto_nominal(reciboDetalleTO.getConceptoNominal());
							reciboDetallePercepciones.setDescripcion(reciboDetalleTO.getDescripcion());
							reciboDetallePercepciones.setDetalle_numero(reciboDetalleTO.getDetalleNumero());
							reciboDetallePercepciones.setDias(reciboDetalleTO.getDias());
							reciboDetallePercepciones.setImporte(reciboDetalleTO.getImporte());
							reciboDetallePercepciones.setId_recibo(reciboDetalleTO.getLlave());
							reciboDetallePercepciones.setNumero_compania(reciboDetalleTO.getNumCompania());
							reciboDetallePercepciones.setNumero_empleado(reciboDetalleTO.getNumEmpleado());
							reciboDetallePercepciones.setNumero_empresa(reciboDetalleTO.getNumEmpresa());
							reciboDetallePercepciones.setPeriodo(reciboDetalleTO.getPeriodo());
							reciboDetallePercepciones.setTipo_compania(reciboDetalleTO.getTipoCompania());
							reciboDetallePercepciones.setTipo_periodo(reciboDetalleTO.getTipoPeriodo());
							reciboDetallePercepciones.setTipo_registro(reciboDetalleTO.getTipoRegsitro());
							coleccionPercepcion.add(reciboDetallePercepciones);
						}else if(reciboDetalleTO.getConcepto().equals("CRE")){
							
							reciboDetalleCredito.setAnio(reciboDetalleTO.getAnio());
							reciboDetalleCredito.setConcepto(reciboDetalleTO.getConcepto());
							reciboDetalleCredito.setConcepto_nominal(reciboDetalleTO.getConceptoNominal());
							reciboDetalleCredito.setDescripcion(reciboDetalleTO.getDescripcion());
							reciboDetalleCredito.setDetalle_numero(reciboDetalleTO.getDetalleNumero());
							reciboDetalleCredito.setDias(reciboDetalleTO.getDias());
							reciboDetalleCredito.setImporte(reciboDetalleTO.getImporte());
							reciboDetalleCredito.setId_recibo(reciboDetalleTO.getLlave());
							reciboDetalleCredito.setNumero_compania(reciboDetalleTO.getNumCompania());
							reciboDetalleCredito.setNumero_empleado(reciboDetalleTO.getNumEmpleado());
							reciboDetalleCredito.setNumero_empresa(reciboDetalleTO.getNumEmpresa());
							reciboDetalleCredito.setPeriodo(reciboDetalleTO.getPeriodo());
							reciboDetalleCredito.setTipo_compania(reciboDetalleTO.getTipoCompania());
							reciboDetalleCredito.setTipo_periodo(reciboDetalleTO.getTipoPeriodo());
							reciboDetalleCredito.setTipo_registro(reciboDetalleTO.getTipoRegsitro());
							coleccionCredito.add(reciboDetalleCredito);
							
						}else{
							System.out.println("Agregar else por default");
						}
					}		
					detalleRecibo.setColeccion_deducciones(coleccionDeduccion);
					detalleRecibo.setColeccion_percepciones(coleccionPercepcion);
					detalleRecibo.setColeccion_creditos(coleccionCredito);
				}				
				
			}	
			if(recibosNomina.getCuentaLoTO() != null){
				detalleRecibo.getGeneralidades_recibo_electronico().setCuenta_deposito(recibosNomina.getCuentaLoTO().getCuenta());
			}
			//huella
			HuellaTO huellaTO = new HuellaTO();
			DispositivoHuellaTO dispositivoHuellaTO = recibosNomina.getDispositivoHuellaTO();
			if( dispositivoHuellaTO != null ) {
				huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
				huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
			}
			response.addAttribute(huellaTO);
			
			response.addAttribute(detalleRecibo);
			
		}catch (EliteDataException e) {
			e.printStackTrace();
			buildErrorResponse(e, response);
		}
		return response;
	}		
	
	
	

	public Response ejecucion_recibos(Session session)throws Exception{
		Response response=new Response(); 
		Map<String, String> errors = null;
		ERecibosNominaRequestTO eRecibosNominaRequestTO=new ERecibosNominaRequestTO();
		StringBuilder coleccionLlaves = null;
		ERecibosNominaResponseTO recibosNomina = new ERecibosNominaResponseTO();
		Recibo_ElectronicoTO electronicoTO = new Recibo_ElectronicoTO();
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			eRecibosNominaRequestTO.setUser(clienteTO.getUserName());
			Recibos_ElectronicosTO recibos_electronicos = (Recibos_ElectronicosTO)session.getAttribute(RECIBOS_ELECTRONICOS);
			LiberarNominaForm forma=(LiberarNominaForm)getFormBean();
			ResourceFacadeSL facade=getDelegate();
			Collection<Recibo_ElectronicoTO> recibosElectronicos=recibos_electronicos.getColeccion_recibos_electronicos();	
			
			if(recibosElectronicos != null && !recibosElectronicos.isEmpty() ){
				ArrayList<Recibo_ElectronicoTO> recibosElectronicosSeleccionados=null;
				int numeroRecibos=recibosElectronicos.size();
				String colecionRecibos=forma.getColeccion_recibos();
				StringTokenizer tokenizer=new StringTokenizer(colecionRecibos,",");
				//validamos el no. maximo de recibos que puede seleccionar el usuario
				if(numeroRecibos>MAXIMO_NUMERO_LIBERACIONES && tokenizer.countTokens()<MAXIMO_NUMERO_LIBERACIONES+1){
					//regresamos mensaje de error en caso de que se tenga mas de 2 recibos por liberar.
					errors = new HashMap<String, String>();
					errors.put("recibos_maximos","Debe seleccionar todos sus recibos a liberar.");
					returnErrorMap(response, errors);
				}else{
					//guardamos en una colleccion los recibos seleccionados por el usuario
					boolean flag = false;
					while(tokenizer.hasMoreElements()){
						String idRecibo = (String)tokenizer.nextElement();
						Iterator<Recibo_ElectronicoTO> iterador = recibosElectronicos.iterator();
						while(iterador.hasNext()){
							Recibo_ElectronicoTO eRecibo_Electronico = iterador.next();
							if(eRecibo_Electronico.getId_recibo().equalsIgnoreCase(idRecibo)){
								flag = true;
								System.out.println("Se selecciono el recibo: "+eRecibo_Electronico.getId_recibo());
								if(recibosElectronicosSeleccionados!=null && recibosElectronicosSeleccionados.size()>0){
									//pequeño ciclo que valida si existen recibos duplicados.
									int tamanoReciboElectronico = recibosElectronicosSeleccionados.size();
//									for(int i=0; i<recibosElectronicosSeleccionados.size(); i++){
									for(int i=0; i<tamanoReciboElectronico; i++){
										Recibo_ElectronicoTO reciboElectronico = (Recibo_ElectronicoTO)recibosElectronicosSeleccionados.get(i);
										if(!reciboElectronico.getId_recibo().equalsIgnoreCase(idRecibo)){
											recibosElectronicosSeleccionados.add(eRecibo_Electronico);
										}else{
											errors = new HashMap<String, String>();
											errors.put("recibos_maximos","Existen recibos duplicados.");
											return returnErrorMap(response, errors);
										}
									}
								}else{
									recibosElectronicosSeleccionados = new ArrayList<Recibo_ElectronicoTO>();
									recibosElectronicosSeleccionados.add(eRecibo_Electronico);																		
								}
							}
						}
						//validacion en caso de que el usuario ingrese un recibo inexistente.
						if(!flag){
							errors = new HashMap<String, String>();
							errors.put("recibos_maximos","El recibo "+idRecibo+" proporcionado es incorrecto, por favor intente nuevamente.");
							return returnErrorMap(response, errors);
						}else{
							flag = false;
						}
					}
				}
				
				coleccionLlaves = new StringBuilder(); 
				for(Recibo_ElectronicoTO reciboElectronicoTO: recibosElectronicosSeleccionados){
					coleccionLlaves.append(reciboElectronicoTO.getId_recibo()+",");
				}
				eRecibosNominaRequestTO.setColeccionLlaves( coleccionLlaves.toString() );
				eRecibosNominaRequestTO.setMethod( "liberarFromConsulta" );
				
				if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
					eRecibosNominaRequestTO.setOptionDispositive(OPCION_HUELLA);
					eRecibosNominaRequestTO.setClave(forma.getHuella_seguridad().toString());
					eRecibosNominaRequestTO.setFlagEjecucion(false);
				}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
					eRecibosNominaRequestTO.setOptionDispositive(OPCION_TOKEN);
					eRecibosNominaRequestTO.setTokenCode(forma.getClave_seguridad().toString());
					eRecibosNominaRequestTO.setFlagEjecucion(true);
				}
				eRecibosNominaRequestTO.setFlagPathUpload(true);
				recibosNomina = facade.liberaERecibosNominaEjecutar(eRecibosNominaRequestTO);
			}
			
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
}
