package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;

public interface WSFactoryInterface {

	Object ejecuta(Object objeto)throws InversionesGenericException;
}
