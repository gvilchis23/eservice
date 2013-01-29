package com.bancoazteca.elite.ejb.alnova;

import java.io.IOException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.bancoazteca.elite.ejb.alnova.beans.BB02AperturaCuentasRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaTransactions;


public class AlnovaEjbSLBean implements SessionBean {

	private static final long serialVersionUID = 5040696197901868927L;
	
	
	private SessionContext context;


	/**
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext aContext) {
		context = aContext;
	}

	/**
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() {

	}

	/**
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() {

	}

	/**
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() {

	}

	/**
	 * See section 7.10.3 of the EJB 2.0 specification See section 7.11.3 of the
	 * EJB 2.1 specification
	 */
	public void ejbCreate() {
	}
	
	protected SessionContext getContext() {
		return context;
	}

	protected void setContext(SessionContext context) {
		this.context = context;
	}
	
	public EjbAlnovaResponseTO ejecutaTransaccion(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws  IOException, AlnovaException, InstantiationException, IllegalAccessException, ClassNotFoundException{
			
					
		AlnovaTransactions alnovaTransactions=new AlnovaTransactions(ejbAlnovaRequestTO);
		
		if(ejbAlnovaRequestTO instanceof BB02AperturaCuentasRequestTO){
			((BB02AperturaCuentasRequestTO)ejbAlnovaRequestTO).setEntrada();
		}

		
		AlnovaResponse response = alnovaTransactions.ejecuta(ejbAlnovaRequestTO);
		String idTrans=ejbAlnovaRequestTO.getIdtransaccion();
		String descrip=ejbAlnovaRequestTO.getDescripcionTransaccion();
		String nombreBean= idTrans+descrip;
		
		
		EjbAlnovaResponseTO respuesta= getInstancia(nombreBean);
		
		respuesta.setIdTransaccion(idTrans);
		respuesta.setAlnovaResponse(response);
		return respuesta; 
	}	
	
	@SuppressWarnings("unchecked")
	private EjbAlnovaResponseTO getInstancia(String bean) throws  InstantiationException, IllegalAccessException, ClassNotFoundException{
		EjbAlnovaResponseTO respuesta;
		Class<EjbAlnovaResponseTO> clazz;
		try{
			clazz=(Class<EjbAlnovaResponseTO>)Class.forName("com.bancoazteca.elite.ejb.alnova.beans."+bean+"ResponseTO");
			
		}catch(ClassNotFoundException e){
			 clazz=(Class<EjbAlnovaResponseTO>)Class.forName("com.bancoazteca.elite.ejb.alnova.utils.AlnovaGenericResponseTO");
		}
		
		respuesta=(EjbAlnovaResponseTO)clazz.newInstance();
		return respuesta;
		
	}
}