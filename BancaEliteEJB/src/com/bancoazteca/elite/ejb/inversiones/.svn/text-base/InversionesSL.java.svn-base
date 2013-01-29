package com.bancoazteca.elite.ejb.inversiones;

import java.io.IOException;

import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.beans.ActualizacionSaldoPorCuentaResponseTO;
import com.bancoazteca.elite.beans.ConciliacionRequestTO;
import com.bancoazteca.elite.beans.Conciliacion_ResponseTO;
import com.bancoazteca.elite.beans.LoginConciliacionRequestTO;
import com.bancoazteca.elite.beans.LoginConciliacionResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;

public interface InversionesSL extends EJBLocalObject {

	public InversionesResponseTO compra_inversion_reportos(InversionesRequestTO request) throws InversionesGenericException, IOException;

	public InversionesResponseTO plazo_tasa_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;

	public void alta_cliente_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;

	public B706_RegistroRetencionResponseTO retencion_alnova(B706_RegistroRetencionRequestTO registroRetencionRequestTO) throws InversionesGenericException, IOException;

	public InversionesResponseTO comprobante_inversion_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;

	public InversionesResponseTO resumen_operaciones_vigentes_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;

	public InversionesResponseTO resumen_operaciones_historicas_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;

	public CompraInversionReportosResponseTO apertura_cuenta_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException, IOException;

	public B756_QuitarRetencionResponse quitar_retencion(B756_QuitarRetencionRequest requestWrapper,String idAlnova,String portal) throws InversionesGenericException;

	public AperturaCuentasResponseTO apertura_cuenta_eje_elite(InversionesRequestTO request) throws InversionesGenericException;
	
	public InversionesResponseTO valida_plazo_tasa_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException;
	 
	public InversionesResponseTO busqueda_cuenta_inversion_cliente(InversionesRequestTO inversionesRequestTO);
	
	public LoginConciliacionResponseTO getLoginConciliacion(LoginConciliacionRequestTO loginConciliacionRequestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException ;
	
	public Conciliacion_ResponseTO conciliacionOperacionesMercadoDinero(ConciliacionRequestTO conciliacionRequestTO) throws InversionesGenericException;
	
	public ReportosEstadoCuentaResponseTO estadoCuentaMercadoDineroEjecucion(ReportosEstadoCuentaRequestTO requestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException ;
	
	public ActualizacionSaldoPorCuentaResponseTO actualizar_saldo_cuenta_alnova(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException ;

	public InversionesResponseTO fecha_apertura_reportos(InversionesRequestTO request) throws InversionesGenericException;

	public InversionesResponseTO consulta_periodo_anios_reportos(InversionesRequestTO request) throws InversionesGenericException;
	
	public InversionesResponseTO consulta_periodo_meses_reportos(InversionesRequestTO request) throws InversionesGenericException;
	
}