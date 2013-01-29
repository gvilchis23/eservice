package com.bancoazteca.elite.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Banco Azteca S.A. [JFAV] Enero 21, 2009.
 */
public class XmlDecoderX {
	
	private static final Logger $log = Logger.getLogger(XmlDecoderX.class);
	
	private static HashMap<String, String> mappedBeans; 
	
	/**
	 * M&eacute;todo de entrada para utilizar esta clase.
	 * 
	 * @param tagName Del cual se obtendrá el objeto a deserializar.
	 * @param messageElement Obtenido generalmente del DAO.
	 * @param mappedBeans Mapa que debe contener TODOS los bean's mapeados que est&eacute;n aninados en el messageElement.
	 * @return {@link Object} Necesita hacerse cast al objeto de retorno.
	 */
	public Object toDeserialize(String tagName, MessageElement messageElement, HashMap<String, String> mappedBeans) {
		Object objectBase = null;
		mappedBeans = mappedBeans;
		Node nodeBase = messageElement.getElementsByTagName(tagName).item(0);
		if(nodeBase !=null ) {
			objectBase = buildJavaObject(nodeBase);
			$log.info("Successfull Object Creation of type: " + objectBase);
		} else {
			$log.fatal("ERROR: Failure Object Creation. Tag not found in XML: " + tagName);
		}
		return objectBase;
	}
	
	private Object buildJavaObject(Node xml) {
		String clazzType = null;
		Object built = null;
		NodeList childs = null;
		clazzType = getObjectType(xml);
		childs = xml.getChildNodes();
		if (childs != null && childs.getLength() > 0 || clazzType.equals("array")) {
			built = buildComplexObject(clazzType, xml);
		} else {
			built = buildSimpleObject(xml);
		}
		return built;
	}
	
	private Object buildComplexObject(String clazzType, Node xml) {
		Class<?> clazz = null;
		Object complexObject = null;
		NodeList childs = null;
		if(clazzType.equalsIgnoreCase("collection")) {
			complexObject = new ArrayList<Object>();
		} else if(clazzType.equalsIgnoreCase("map")) {
			complexObject = new HashMap<String, Object>();
		} else if(clazzType.equalsIgnoreCase("array")) {
			complexObject = buildArray(xml);
		} else if(clazzType.equalsIgnoreCase("bean")) {
			try {
				if(mappedBeans.containsKey(xml.getNodeName())) {
					clazz = Class.forName(mappedBeans.get(xml.getNodeName()));
					complexObject = clazz.newInstance();
				} else {
					$log.error("ERROR: Bean Not Found for tag --> " +xml.getNodeName());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			complexObject = new Object();
		}
		childs = xml.getChildNodes();
		if(complexObject instanceof Map) {
			addMapProperties(complexObject, childs);
		} else if(complexObject instanceof Object[]) {
			addArrayElements(complexObject, childs);
		} else {
			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);
				Object tempObject = buildJavaObject(child);
				addPropertyToComplex(complexObject, child, tempObject);
			}
		}
		return complexObject;
	}
	
	private Object buildArray(Node xml) {
		String arrayString = null;
		Object[] array = null;
		if(xml.getChildNodes() != null && xml.getChildNodes().getLength() > 0) {
			array = (Object[])Array.newInstance(Object.class, xml.getChildNodes().getLength());
		} else {
			arrayString = (xml.getAttributes()).getNamedItem("array").getNodeValue();
			array = arrayString.split(",");
		}
		return array;
	}
	
	private void addArrayElements(Object complexObject, NodeList childs) {
		for(int i = 0; i < childs.getLength(); i++) {
			((Object[])complexObject)[i] = buildJavaObject(childs.item(i));
		}
	}

	@SuppressWarnings("unchecked")
	private void addMapProperties(Object complexObject, NodeList childs) {
		String key = null;
		Object keyValue = null;
		for(int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			key = child.getAttributes().getNamedItem("key").getNodeValue(); 
			keyValue = buildJavaObject(child.getFirstChild());
			((Map)complexObject).put(key, keyValue);
		}
	}

	@SuppressWarnings("unchecked")
	private void addPropertyToComplex(Object complexObject, Node child, Object childObject) {
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		try {
			if(complexObject instanceof Collection<?>) {
				((Collection)complexObject).add(childObject);
			} else if(complexObject instanceof Map<?, ?>) {
				((Map)complexObject).put(child.getNodeName(), childObject);
			} else {
				if(childObject instanceof String) {
					if(childObject != null && propertyUtilsBean.getPropertyType(complexObject, child.getNodeName()) != null) {
						Class obtainedClazz = childObject.getClass();
						Class declaredClazz = propertyUtilsBean.getPropertyType(complexObject, child.getNodeName());
						if (!obtainedClazz.getName().equalsIgnoreCase(declaredClazz.getName())) {
							Object converted = ConvertUtils.convert((String)childObject,declaredClazz);
							propertyUtilsBean.setProperty(complexObject, child.getNodeName(), converted);
						} else {
							propertyUtilsBean.setProperty(complexObject, child.getNodeName(), childObject);
						}
					}
				} else {
					propertyUtilsBean.setProperty(complexObject, child.getNodeName(), childObject);
				}
			}
		} catch (IllegalArgumentException e) {
			$log.info("WARNING: Wrong data type for Property: " + child.getNodeName());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			$log.info("ERROR: Property Not Found: -" + child.getNodeName() + "- in Class: " + complexObject.getClass().getSimpleName());
		}
	}
	
	private Object buildSimpleObject(Node xml) {
		String simpleObject = null;
		try {
			if(xml.getAttributes().getLength() > 0 && xml.getNodeName().equalsIgnoreCase("item")) {
				simpleObject = xml.getAttributes().getNamedItem("itemValue").getNodeValue();
			} else {
				simpleObject = xml.getAttributes().getNamedItem("value").getNodeValue();
			}
		} catch (Exception e) {
		$log.debug("Warning: Found NULL value in XML for Property: " + xml.getNodeName());;
		}
		return simpleObject;
	}
	
	private String getObjectType(Node element) {
		String objectType = null;
		if(element.getAttributes().getLength() > 0 && element.getAttributes().getNamedItem("type") != null) {
			objectType = (element.getAttributes()).getNamedItem("type").getNodeValue();
		} else {
			objectType = element.getNodeName();
		}
		return objectType;
	}
}
