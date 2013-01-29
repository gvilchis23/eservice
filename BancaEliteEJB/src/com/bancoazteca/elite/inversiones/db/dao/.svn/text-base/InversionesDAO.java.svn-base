package com.bancoazteca.elite.inversiones.db.dao;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.db.beans.AltaClienteRequestTO;
import com.bancoazteca.elite.db.beans.InsertaCompraInversionTO;
import com.bancoazteca.elite.db.beans.InsertaTransaccionAlnovaTO;
import com.bancoazteca.elite.db.beans.InsertaTransaccionTO;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B520_TransaccionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B520_TransaccionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentaEjeEliteRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentasResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAltaClienteRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.utils.InversionesConstantes;



public class InversionesDAO extends InversionesDAOAbstracto{
	
	Logger logger=Logger.getLogger(InversionesDAO.class);
	
	public int registroRetencionAlnova(B706_RegistroRetencionResponseTO registroRetencionResponseTO, B706_RegistroRetencionRequestTO registroRetencionRequestTO,String response,String request,String status) throws InversionesGenericException{
		
		logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%          Almacenar Retencion alnova");

		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		
		InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();
		
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha=dateFormat.format(new Date());
		insertaTransaccionTO.setFechaOperacion(fecha);
		
		Integer idcliente=inversionesDAOAbstracto.busquedaCliente(registroRetencionRequestTO.getIdAlnova());
		Integer idEmpresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_ALNOVA);
		Integer idOperacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.OPERACION_RETENCION_ALNOVA);
		
		insertaTransaccionTO.setIdCliente(idcliente.toString());
		insertaTransaccionTO.setIdEmpresa(idEmpresa.toString());
		insertaTransaccionTO.setIdOperacion(idOperacion.toString());
		insertaTransaccionTO.setNombreAplicacion(registroRetencionRequestTO.getPortal());
		insertaTransaccionTO.setNumeroCuenta(registroRetencionRequestTO.getCodigoCuentaCliente());
		insertaTransaccionTO.setPeticion(request);
		insertaTransaccionTO.setRespuesta(response);
		insertaTransaccionTO.setStatus(status);
		insertaTransaccionTO.setUsuarioModifico(registroRetencionRequestTO.getIdAlnova());
		
		Integer idTransaccion=inversionesDAOAbstracto.insertaTransaccion(insertaTransaccionTO);
		
		if(registroRetencionResponseTO!=null){
			InsertaTransaccionAlnovaTO insertaTransaccionAlnovaTO=new InsertaTransaccionAlnovaTO();
			insertaTransaccionAlnovaTO.setCodigoOperacion(registroRetencionRequestTO.nombre());
			insertaTransaccionAlnovaTO.setFolioOperacion(registroRetencionResponseTO.getNumeroRetencion());
			insertaTransaccionAlnovaTO.setIdTransaccion(idTransaccion.toString());
			insertaTransaccionAlnovaTO.setUsuarioModifico(registroRetencionRequestTO.getIdAlnova());
			idTransaccion = inversionesDAOAbstracto.insertaTransaccionAlnova(insertaTransaccionAlnovaTO);
		}
		return idTransaccion;
	}
	
	public int registroCompraInversionReportos(ReportosAperturaRequestTO aperturaRequestTO,ReportosAperturaResponseTO aperturaResponse,String response,String request, String status) throws InversionesGenericException{
		
		logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%          Almacenar Compra Inversion");
		
		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();
		
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha = dateFormat.format(new Date());

		Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_REPORTOS);
		Integer operacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.OPERACION_COMPRA_INVERSION);
		Integer idCliente=inversionesDAOAbstracto.busquedaCliente(aperturaRequestTO.getNumeroIdentificacionAlnova());
		
		insertaTransaccionTO.setFechaOperacion(fecha);
		insertaTransaccionTO.setIdCliente(idCliente.toString());
		insertaTransaccionTO.setIdEmpresa(empresa.toString());
		insertaTransaccionTO.setIdOperacion(operacion.toString());
		insertaTransaccionTO.setNombreAplicacion(aperturaRequestTO.getPortal());
		insertaTransaccionTO.setNumeroCuenta(aperturaRequestTO.getCuentaCargo());
		insertaTransaccionTO.setPeticion(request);
		insertaTransaccionTO.setRespuesta(response);
		insertaTransaccionTO.setStatus(status);
		insertaTransaccionTO.setUsuarioModifico(aperturaRequestTO.getNumeroIdentificacionAlnova());
		
		Integer transaccionId=inversionesDAOAbstracto.insertaTransaccion(insertaTransaccionTO);
		
		if(aperturaResponse!=null){
			
			InsertaCompraInversionTO compraInversionTO=new InsertaCompraInversionTO();
			compraInversionTO.setFolioOperacion(aperturaResponse.getFolioOperacion());
			compraInversionTO.setIdTransaccion(transaccionId.toString());
			compraInversionTO.setIdTransaccionAlnova(aperturaRequestTO.getIdRetencion());
			compraInversionTO.setMontoInversion(aperturaRequestTO.getMontoInversion());
			compraInversionTO.setNumeroContrato(aperturaResponse.getNumeroContrato());
			compraInversionTO.setPlazo(aperturaRequestTO.getPlazo());
			compraInversionTO.setTasa(aperturaRequestTO.getTasa());
			compraInversionTO.setUsuarioModifico(aperturaRequestTO.getNumeroIdentificacionAlnova());
			inversionesDAOAbstracto.InsertaCompraInversion(compraInversionTO);
			
		}
		
		return transaccionId;
	}
	
	public int altaClienteReportos(ReportosAltaClienteRequestTO altaClienteRequestTO, String request, String response,String status) throws InversionesGenericException{
		
		logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%          Almacenar Cliente");

		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();

		Integer idCliente=-1;

		idCliente=inversionesDAOAbstracto.busquedaClienteSimple(altaClienteRequestTO.getNumeroIdentificacionAlnova());
		
		AltaClienteRequestTO altaClienteBaseDatos=new AltaClienteRequestTO();
		
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha = dateFormat.format(new Date());

		Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_REPORTOS);
		Integer operacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.OPERACION_ALTA_CLIENTE);
		
		
		if(idCliente==-1){	
			
			altaClienteBaseDatos.setIdAlnova(altaClienteRequestTO.getNumeroIdentificacionAlnova());
			altaClienteBaseDatos.setApellidoMaterno(altaClienteRequestTO.getApellidoMaterno());
			altaClienteBaseDatos.setApellidoPaterno(altaClienteRequestTO.getApellidoPaterno());
			altaClienteBaseDatos.setNombre(altaClienteRequestTO.getNombre());
			altaClienteBaseDatos.setStatusActivo("1");
			altaClienteBaseDatos.setCurp(altaClienteRequestTO.getCurp());
			altaClienteBaseDatos.setUsuarioModifico(altaClienteRequestTO.getNumeroIdentificacionAlnova());
			
			idCliente=inversionesDAOAbstracto.altaCliente(altaClienteBaseDatos);
			
		}
		
			InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();
			insertaTransaccionTO.setFechaOperacion(fecha);
			insertaTransaccionTO.setIdCliente(idCliente.toString());
			insertaTransaccionTO.setIdEmpresa(empresa.toString());
			insertaTransaccionTO.setIdOperacion(operacion.toString());
			insertaTransaccionTO.setNombreAplicacion(altaClienteRequestTO.getPortal());
			insertaTransaccionTO.setNumeroCuenta(altaClienteRequestTO.getCuentaCargo());
			insertaTransaccionTO.setPeticion(request);
			insertaTransaccionTO.setRespuesta(response);
			insertaTransaccionTO.setStatus(status);
			insertaTransaccionTO.setUsuarioModifico(altaClienteRequestTO.getNumeroIdentificacionAlnova());
			
			inversionesDAOAbstracto.insertaTransaccion(insertaTransaccionTO);
			
		
		return idCliente;
	}

	public void registroTransferencia(B520_TransaccionResponse transaccionResponse,
			B520_TransaccionRequest transaccionRequest, String request, String response,String status,String idAlnova,String aplicacion) throws InversionesGenericException {
		
		InversionesDAO inversionesDAO=new InversionesDAO();
		
		
		InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();
		
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha = dateFormat.format(new Date());

		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_ALNOVA);
		Integer operacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.TRANSFERENCIA_DINERO);
		Integer idCliente=inversionesDAOAbstracto.busquedaClienteSimple(idAlnova);
		
		insertaTransaccionTO.setFechaOperacion(fecha);
		insertaTransaccionTO.setIdCliente(idCliente.toString());
		insertaTransaccionTO.setIdEmpresa(empresa.toString());
		insertaTransaccionTO.setIdOperacion(operacion.toString());
		insertaTransaccionTO.setNombreAplicacion(aplicacion);
		insertaTransaccionTO.setNumeroCuenta(transaccionRequest.getCuentaCargo());
		insertaTransaccionTO.setPeticion(request);
		insertaTransaccionTO.setRespuesta(response);
		insertaTransaccionTO.setStatus(status);
		insertaTransaccionTO.setUsuarioModifico(idAlnova);
		
		Integer idTransaccion=inversionesDAO.insertaTransaccion(insertaTransaccionTO);
		
		if(transaccionResponse!=null){
			
			InsertaTransaccionAlnovaTO insertaTransaccionAlnovaTO=new InsertaTransaccionAlnovaTO();
			
			insertaTransaccionAlnovaTO.setCodigoOperacion(transaccionRequest.nombre());
			insertaTransaccionAlnovaTO.setFolioOperacion(transaccionResponse.getNumeroMovimiento());
			insertaTransaccionAlnovaTO.setIdTransaccion(idTransaccion.toString());
			insertaTransaccionAlnovaTO.setUsuarioModifico(idAlnova);
			
			inversionesDAO.insertaTransaccionAlnova(insertaTransaccionAlnovaTO);
		}
	}

	public void insertaAperturaCuentasAlnova( BB02_AperturaCuentaEjeEliteRequest aperturaCuentaRequest,BB02_AperturaCuentasResponse aperturaCuentaresponse, 
			String request,String response, String status,String idAlnova,String aplicacion) throws InversionesGenericException {

		InversionesDAO inversionesDAO=new InversionesDAO();


		InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();

		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha = dateFormat.format(new Date());
		
		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_BANCA_ELITE);
		Integer operacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.OPERACION_APERTURA_CUENTA_EJE);
		Integer idCliente=inversionesDAOAbstracto.busquedaClienteSimple(idAlnova);

		insertaTransaccionTO.setFechaOperacion(fecha);
		insertaTransaccionTO.setIdCliente(idCliente.toString());
		insertaTransaccionTO.setIdEmpresa(empresa.toString());
		insertaTransaccionTO.setIdOperacion(operacion.toString());
		insertaTransaccionTO.setNombreAplicacion(aplicacion);
		insertaTransaccionTO.setNumeroCuenta(aperturaCuentaRequest.getCuentaAsociada());
		insertaTransaccionTO.setPeticion(request);
		insertaTransaccionTO.setRespuesta(response);
		insertaTransaccionTO.setStatus(status);
		insertaTransaccionTO.setUsuarioModifico(idAlnova);

		Integer idTransaccion=inversionesDAO.insertaTransaccion(insertaTransaccionTO);

		if(aperturaCuentaresponse!=null){

			InsertaTransaccionAlnovaTO insertaTransaccionAlnovaTO=new InsertaTransaccionAlnovaTO();

			insertaTransaccionAlnovaTO.setCodigoOperacion(aperturaCuentaRequest.nombre());
			insertaTransaccionAlnovaTO.setFolioOperacion(aperturaCuentaresponse.getNumeroMovimiento());
			insertaTransaccionAlnovaTO.setIdTransaccion(idTransaccion.toString());
			insertaTransaccionAlnovaTO.setUsuarioModifico(idAlnova);

			inversionesDAO.insertaTransaccionAlnova(insertaTransaccionAlnovaTO);
		}
	}

	public void insertaQuitaRetencion(B756_QuitarRetencionResponse quitaRetencionResponse, B756_QuitarRetencionRequest quitarRetencionRequest,String idAlnova,String portal,String request,String response,String status) throws InversionesGenericException{
		
		InversionesDAO inversionesDAO=new InversionesDAO();


		InsertaTransaccionTO insertaTransaccionTO=new InsertaTransaccionTO();

		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha = dateFormat.format(new Date());
		
		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		Integer empresa=inversionesDAOAbstracto.busquedaEmpresa(InversionesConstantes.EMPRESA_BANCA_ELITE);
		Integer operacion=inversionesDAOAbstracto.busquedaOperacion(InversionesConstantes.OPERACION_APERTURA_CUENTA_EJE);
		Integer idCliente=inversionesDAOAbstracto.busquedaClienteSimple(idAlnova);

		insertaTransaccionTO.setFechaOperacion(fecha);
		insertaTransaccionTO.setIdCliente(idCliente.toString());
		insertaTransaccionTO.setIdEmpresa(empresa.toString());
		insertaTransaccionTO.setIdOperacion(operacion.toString());
		insertaTransaccionTO.setNombreAplicacion(portal);
		insertaTransaccionTO.setNumeroCuenta(quitarRetencionRequest.getCodigoCuentaCliente());
		insertaTransaccionTO.setPeticion(request);
		insertaTransaccionTO.setRespuesta(response);
		insertaTransaccionTO.setStatus(status);
		insertaTransaccionTO.setUsuarioModifico(idAlnova);

		Integer idTransaccion=inversionesDAO.insertaTransaccion(insertaTransaccionTO);

		if(quitaRetencionResponse!=null){
			InsertaTransaccionAlnovaTO insertaTransaccionAlnovaTO=new InsertaTransaccionAlnovaTO();
			insertaTransaccionAlnovaTO.setCodigoOperacion(quitarRetencionRequest.nombre());
			insertaTransaccionAlnovaTO.setIdTransaccion(idTransaccion.toString());
			insertaTransaccionAlnovaTO.setUsuarioModifico(idAlnova);
			inversionesDAO.insertaTransaccionAlnova(insertaTransaccionAlnovaTO);
		}
	}
	
	
	public Collection<PlazoTasaTO> busquedaPlazoTasa() throws InversionesGenericException{
		
		InversionesDAOAbstracto inversionesDAOAbstracto=new InversionesDAOAbstracto();
		Collection<PlazoTasaTO> plazoTasa = null;
		plazoTasa = inversionesDAOAbstracto.busquedaPlazoTasa();
		return plazoTasa;
		
		
	}
}