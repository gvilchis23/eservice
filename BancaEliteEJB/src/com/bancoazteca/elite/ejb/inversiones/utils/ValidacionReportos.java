package com.bancoazteca.elite.ejb.inversiones.utils;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.LEVEL_VALIDATION;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.db.beans.InsertaTransaccionTO;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.AltaClienteReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosPlazoTasaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosValidaPlazoTasaTO;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAOAbstracto;
import com.bancoazteca.elite.util.PropertiesService;


public class ValidacionReportos {
	
	Logger log=Logger.getLogger(ValidacionReportos.class);
	private static final String ERROR_VALIDACION = "error_validacion";
	
	
	  public void apertura(CompraInversionReportosRequestTO request)throws InversionesGenericException, NumberFormatException, IOException{
		  HashMap<String, String>mapaError=new HashMap<String,String>();
		  
		  PropertiesService propertiesService=PropertiesService.getInstance();
		  int montoMinimo=Integer.parseInt(propertiesService.getPropertie("CuentasInversion.properties", "montominimo.cuenta.mercadodinero"));
		  
		  if(!InversionRule.validaMonto(request.getMonto(),montoMinimo)){
			  mapaError.put("monto","proporcione un monto mayor o igual al minimo");
		  }

		  if(!mapaError.isEmpty()){
			  InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			  exceptionTO.setLevel(LEVEL_VALIDATION);
			  exceptionTO.setMessage("Error de validacion en algunos parametros");
			  exceptionTO.setErrorMap(mapaError);
			  persisteValidacion(request,mapaError);
			  throw new InversionesGenericException(exceptionTO);
		  }
	  }
	  
	  public void plazoTasa(ReportosPlazoTasaRequestTO plazoTasaRequestTO) throws InversionesGenericException{
		  HashMap<String, String>mapaError=new HashMap<String,String>();
		  if(!InversionRule.validaFecha(plazoTasaRequestTO.getFechaRegistro(),"yyyy/MM/dd")){
			  mapaError.put("fecha_registro","la fecha de registro es invalida");
		  }
		  if(!mapaError.isEmpty()){
			  InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			  exceptionTO.setLevel(LEVEL_VALIDATION);
			  exceptionTO.setMessage("Error de validacion en algunos parametros");
			  exceptionTO.setErrorMap(mapaError);
			  persisteValidacion(plazoTasaRequestTO,mapaError);
			  throw new InversionesGenericException(exceptionTO);
		  }
	  }
	  
	  
	  public void altaCliente(AltaClienteReportosRequestTO altaClienteRequestTO) throws InversionesGenericException{
		  HashMap<String, String>mapaError=new HashMap<String,String>();
		  if(!InversionRule.validaCuentaAlfanumerica(altaClienteRequestTO.getCuentaCargo(),30)){
			  mapaError.put("cuenta_cargo","La cuenta cargo es invalida");
		  }
		  if(!InversionRule.validaCuentaAlfanumerica(altaClienteRequestTO.getCuentaClabe(),18)){
			  mapaError.put("cuenta_clabe","La cuenta clabe es invalida");
		  }
		  if(!mapaError.isEmpty()){
			  InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			  exceptionTO.setLevel(LEVEL_VALIDATION);
			  exceptionTO.setMessage("Error de validacion en algunos parametros");
			  exceptionTO.setErrorMap(mapaError);
			  persisteValidacion(altaClienteRequestTO,mapaError);
			  throw new InversionesGenericException(exceptionTO);
		  }
	  }
	  
	public String formatoMontoAlnovaReportos(String monto){
		Double doubl=Double.parseDouble(monto);
		DecimalFormat format=new DecimalFormat("##################");
		monto=format.format(doubl);
		String subString=monto.substring((monto.length()-2));
		String subStrin2=monto.substring(0,monto.length()-2);
		return monto=subStrin2+"."+subString;	
	}


	public String formatoTasaBruta(String tasaBruta) {
		Double tasaNum=Double.parseDouble(tasaBruta);
		Locale LOCALE_MX = new Locale("es","mx");
		NumberFormat format=DecimalFormat.getInstance(LOCALE_MX);
		format.setMinimumFractionDigits(4);
		return format.format(tasaNum);
	}
	  
	  public void comprobanteInversion(ComprobanteInversionReportosRequestTO comprobanteInversionTO) throws InversionesGenericException{
		  HashMap<String, String>mapaError=new HashMap<String,String>();
		  if(!InversionRule.validaSoloNumericos(comprobanteInversionTO.getNumero_contrato(), 8)){
			  mapaError.put("numero_contrato","El numero de contrato es invalido");
		  }
		  if(!InversionRule.validaSoloNumericos(comprobanteInversionTO.getFolio_operacion(), 6)){
			  mapaError.put("folio_oeracion","El folio de la operacion es incorrecto");
		  }
		  if(!InversionRule.validaSoloNumericos(comprobanteInversionTO.getIdAlnova(), 8)){
			  mapaError.put("id_alnova","El numero de contrato es invalido");
		  }
		  if(!mapaError.isEmpty()){
			  InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			  exceptionTO.setLevel(LEVEL_VALIDATION);
			  exceptionTO.setMessage("Error de validacion en algunos parametros");
			  exceptionTO.setErrorMap(mapaError);
			  persisteValidacion(comprobanteInversionTO,mapaError);
			  throw new InversionesGenericException(exceptionTO);
		  }
	  }
	  
	  public String formatoFechaAlnovaReportosAltaCliente(String fecha){

		  StringBuilder stringBuilder=new StringBuilder(fecha);
		  if(!fecha.contains("/")){
			  stringBuilder.insert(2,"/");
			  stringBuilder.insert(5,"/");
			  fecha=stringBuilder.toString();
		  }

		  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/mm/yy");
		  Date date=null;
		  try {
			  date = simpleDateFormat.parse(fecha);
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }
		  
		  simpleDateFormat=new SimpleDateFormat("yyyy/mm/dd");
		  fecha=simpleDateFormat.format(date);
		  return fecha;
	  }
	  
	  private void persisteValidacion(InversionesRequestTO request,Map<String,String> mapaErrores) throws InversionesGenericException{
		  
		  StringBuilder builder=new StringBuilder(); 
		  for (String key : mapaErrores.keySet()) {
			 builder.append(key+": "+mapaErrores.get(key)+"\n");
		  }
		  builder.deleteCharAt(builder.length()-1);
		  
		  String peticion=request.toString();
		  log.info("############### Error en peticion al EJB  ###############\n\n");
		  log.info(peticion);
		  log.info("                    Mapa de errores\n");
		  log.info(builder.toString());
		  log.info("############# Fin: error en peticion al EJB  ###############\n\n");
		  
		  InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		  InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();
		  
		  DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		  String fecha =dateFormat.format(new Date());
		  insertaTransaccionTO.setFechaOperacion(fecha);
		  
		  Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(request.getType());
		  insertaTransaccionTO.setIdEmpresa(empresa.toString());
		  
		  Integer operacion=inversionesDAOAbstracto.busquedaOperacion(ERROR_VALIDACION);
		  insertaTransaccionTO.setIdOperacion(operacion.toString());
		  insertaTransaccionTO.setNombreAplicacion(request.getPortalSolicitante());
		  insertaTransaccionTO.setPeticion(peticion);
		  insertaTransaccionTO.setRespuesta(builder.toString());
		  insertaTransaccionTO.setStatus("ERROR DE VALIDACION");
		  
		  Integer idAlnova=inversionesDAOAbstracto.busquedaCliente(request.getIdAlnova());
		  insertaTransaccionTO.setIdCliente(idAlnova.toString());
		  
		  insertaTransaccionTO.setNumeroCuenta("null");
		  inversionesDAOAbstracto.insertaTransaccion(insertaTransaccionTO);
	  }
	  
	  public void validaDatosValidacionPlazoTasa(ReportosValidaPlazoTasaTO reportosValidaPlazoTasaTO){
		  Map errorMap=new HashMap<String,String>();
		  InversionRule inversionRule=new InversionRule();
		  
		  if(!inversionRule.validaFecha(reportosValidaPlazoTasaTO.getFechaOperacion(), inversionRule.FORMAT_DATE_YYYYMMDD)){
			  errorMap.put("fecha_operacion","La fecha de solicitud no tiene un formato correcto.");
		  }
	  }
}