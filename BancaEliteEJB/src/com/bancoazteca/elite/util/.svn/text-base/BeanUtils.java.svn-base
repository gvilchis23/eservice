package com.bancoazteca.elite.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class BeanUtils {
	
	
	@SuppressWarnings("unchecked")
	public static synchronized Object mapToBean(Map<String, String> map,Class clazz) throws IllegalAccessException{
		Object bean = null;
		try {
			bean = clazz.newInstance();		
			for (String key : map.keySet()){
				String value = map.get(key);
				try{
					PropertyUtils.setSimpleProperty(bean, key, value);
				}catch (Throwable e){}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IllegalAccessException(e.getMessage());
		}		
		return bean;
	}
	
	public static synchronized Object mapToEliteErros(Map<String, String> errors) throws IllegalAccessException {
		DynaBean dynaBean = null;
		try {
			List<DynaProperty> properties = new ArrayList<DynaProperty>();
			for (String key : errors.keySet()) {
				properties.add(new DynaProperty(key, String.class));
			}
			DynaProperty[] dynaProperties = (DynaProperty[]) properties.toArray();
			DynaClass dynaClass = new BasicDynaClass("EliteErrors", null, dynaProperties);

			dynaBean = dynaClass.newInstance();
			for (String key : errors.keySet()) {
				dynaBean.set(key, errors.get(key));
			}
		} catch (InstantiationException e) {			
			e.printStackTrace();
			throw new IllegalAccessException(e.getMessage());
		}
		return dynaBean;
	}

}
