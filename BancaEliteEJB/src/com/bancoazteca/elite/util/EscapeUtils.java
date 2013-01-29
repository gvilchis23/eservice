package com.bancoazteca.elite.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Banco Azteca S.A. [JFAV] Octubre 31, 2008.
 * 
 * Realiza el formateo de cadenas, quit&aacute;ndo caracteres de escape de
 * c&oacute;digo HTML a String.
 */
public class EscapeUtils {
	
	/**
	 * Método estático que formatea a String los Action Errors recuperados de
	 * eBanking.
	 * 
	 * @param errors
	 * @return HashMap newErrors.
	 */
	public static Map<String, String> unescapeActionErrors(
			Map<String, String> errors) {
		Map<String, String> newErrors = new HashMap<String, String>();
		for (Iterator<String> it = errors.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String originalError = errors.get(key);
			String newError = getStringFromHtml(originalError);
			newErrors.put(key, newError);
		}
		return newErrors;
	}
	/**
	 * Elimina los caracteres de escape HTML de una cadena.
	 * @param originalError
	 * @return String formattedError.
	 */
	public static String getStringFromHtml(String originalError) {
		String newError = (StringEscapeUtils.unescapeHtml(originalError).replaceAll("\\<.*?>", ""));
		return newError;
	}
}
