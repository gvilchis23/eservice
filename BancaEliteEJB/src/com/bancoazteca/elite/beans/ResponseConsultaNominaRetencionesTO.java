package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.List;
/**
 * @author Banco Azteca S.A. [JFAV] Diciembre 26, 2008.
 */
public class ResponseConsultaNominaRetencionesTO implements Serializable {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = -2598097885185287865L;

	//fields
	private String errorCode;
	private String errorDescription;
	private List<ArchivoNominaTO> element;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the element
	 */
	public List<ArchivoNominaTO> getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(List<ArchivoNominaTO> element) {
		this.element = element;
	}

}
