package com.bancoazteca.eservice.command.saldos;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJBException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuentas_clienteTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class DetallesSaldoPorCuentasCommand extends CommandBase{

	Logger log=Logger.getLogger(DetallesSaldoPorCuentasCommand.class);

	public Response ejecucion(Session session) throws ServiceLocatorException, EJBException, LoginException, SessionExpiredException, UsuarioException
	{
		
		Response response=new Response();
		DetallesSaldoPorCuentasForm form =(DetallesSaldoPorCuentasForm)getFormBean();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		String cuenta=form.getNumero_de_cuenta();

		Collection<com.bancoazteca.eservice.command.base.beans.CuentaTO> cuentas_coleccion=new ArrayList<com.bancoazteca.eservice.command.base.beans.CuentaTO>();
		com.bancoazteca.eservice.command.base.beans.CuentaTO cuentaTOBaseBeans=null;

		try{

			ResourceFacadeSL facade = null;	
			cuentaTOBaseBeans=null;
			LoginRequestTO loginRequestTO;
			loginRequestTO =  new LoginRequestTO();
			loginRequestTO.setUser(clienteTO.getUserName());
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@2222");
			log.info("userName: "+clienteTO.getUserName());
			loginRequestTO.setAccountOnDemand(cuenta);
			log.info("cuenta: "+cuenta);
			facade = getDelegate();
			LoginResponseTO loginResponseTO=facade.getOnDemandDetailAccounts(loginRequestTO);
			Collection<CuentaTO>coleccion_cuentas=loginResponseTO.getClienteTO().getCuentas();
			CuentaTO cuentaTO = null;
			Predicate predicate;
			if(cuenta.equalsIgnoreCase("*"))
			{
				for (CuentaTO cuentaTemp : coleccion_cuentas){
					cuentaTOBaseBeans=new com.bancoazteca.eservice.command.base.beans.CuentaTO();
					cuentaTOBaseBeans.setCuenta_clabe(cuentaTemp.getCuenctaClabeFormateada());
					cuentaTOBaseBeans.setNumero_cuenta(Formatter.removeSpaces(cuentaTemp.getCuentaFormateada()));
					cuentaTOBaseBeans.setProducto(cuentaTemp.getDescripcion());
					
					cuentaTOBaseBeans.setSaldo_disponible("0.00");
					cuentaTOBaseBeans.setSaldo_retenido("0.00");
					cuentaTOBaseBeans.setSaldo_total("0.00");
					
					if(cuentaTemp.getDisponible() != null){
						cuentaTOBaseBeans.setSaldo_disponible(cuentaTemp.getDisponible().toString());
					}
					if(cuentaTemp.getRetenido() != null){
						cuentaTOBaseBeans.setSaldo_retenido(cuentaTemp.getRetenido().toString());
					}
					if(cuentaTemp.getBalance() != null){
						cuentaTOBaseBeans.setSaldo_total(cuentaTemp.getBalance().toString());
					}
										
					cuentas_coleccion.add(cuentaTOBaseBeans);
				}
			}else{
				String[] coleccionDeCuentas=cuenta.split("@");
				for (String cuentaTemp : coleccionDeCuentas){
					predicate = new FindAccountVoByNumber(cuentaTemp);
					cuentaTO = (CuentaTO) CollectionUtils.find(coleccion_cuentas,predicate);
					cuentaTOBaseBeans=new com.bancoazteca.eservice.command.base.beans.CuentaTO();
					cuentaTOBaseBeans.setCuenta_clabe(cuentaTO.getCuenctaClabeFormateada());
					cuentaTOBaseBeans.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));
					cuentaTOBaseBeans.setProducto(cuentaTO.getDescripcion());
					
					cuentaTOBaseBeans.setSaldo_disponible("0.00");
					cuentaTOBaseBeans.setSaldo_retenido("0.00");
					cuentaTOBaseBeans.setSaldo_total("0.00");
					
					if(cuentaTO.getDisponible() != null){
						cuentaTOBaseBeans.setSaldo_disponible(cuentaTO.getDisponible().toString());
					}
					if(cuentaTO.getRetenido() != null){
						cuentaTOBaseBeans.setSaldo_retenido(cuentaTO.getRetenido().toString());
					}
					if(cuentaTO.getBalance() != null){
						cuentaTOBaseBeans.setSaldo_total(cuentaTO.getBalance().toString());
					}
					cuentas_coleccion.add(cuentaTOBaseBeans);
				}
			}
			log.info("coloca cuentas en colleccion");
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}

		Cuentas_clienteTO cuentas_clienteTO=new Cuentas_clienteTO();
		cuentas_clienteTO.setColeccion_cuentas(cuentas_coleccion);
		response.addAttribute(cuentas_clienteTO);

		return response;
	}

	private class FindAccountVoByNumber implements Predicate{

		private String account;

		public FindAccountVoByNumber(String account){
			this.account = account;
		}

		public boolean evaluate(Object obj) {
			CuentaTO cuentaTO = (CuentaTO) obj;
			log.info("cuentaNumero: "+cuentaTO.getNumero());
			if (Formatter.removeSpaces(cuentaTO.getCuentaFormateada()).equals(account)){
				return true;
			}else{
				return false;
			}
		}
	}

	private boolean existeCuenta(Collection<CuentaTO> cuentasTO,String numero_cuenta) {
		if(getAccountsPrdicate(cuentasTO,numero_cuenta)==null)
		{
			return false;
		}
		return true;
	}
}