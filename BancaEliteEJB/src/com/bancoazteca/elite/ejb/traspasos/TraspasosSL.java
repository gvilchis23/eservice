package com.bancoazteca.elite.ejb.traspasos;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.beans.TraspasosPropiasRequestTO;
import com.bancoazteca.elite.beans.TraspasosPropiasResponsetTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 26, 2008.
 */
public interface TraspasosSL extends EJBLocalObject {
	
	public TraspasosPropiasResponsetTO propiasInvocaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException;
	public TraspasosPropiasResponsetTO propiasPreparaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException ;	
	public TraspasosPropiasResponsetTO ejecutaPropiasTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException ;

}
