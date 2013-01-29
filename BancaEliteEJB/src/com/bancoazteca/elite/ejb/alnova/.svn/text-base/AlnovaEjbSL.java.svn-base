package com.bancoazteca.elite.ejb.alnova;


import java.io.IOException;

import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 20, 2008.
 */

public interface AlnovaEjbSL extends EJBLocalObject {
	
	public EjbAlnovaResponseTO ejecutaTransaccion(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws  IOException, AlnovaException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
}