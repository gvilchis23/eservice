package com.bancoazteca.elite.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.sax.SAXSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.DocumentInfo;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.QueryResult;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFinder {
	
	private static final Logger $log = Logger.getLogger(XMLFinder.class);
	
	public static String findFragment(String xml, String query){
		String xString = null;
		
		ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes());
		xString = findFragment(stream, query);
		return xString;
	}
	
	
	public static String findFragment(InputStream xml, String query){
		String xmlFragment = null;
		try {
			InputSource source = new InputSource(xml);
			SAXSource saxsource = new SAXSource( source);
			Configuration config = new Configuration();
			config.setHostLanguage(Configuration.XQUERY);
			config.setStripsAllWhiteSpace(true);
			
			StaticQueryContext staticQueryContext = new StaticQueryContext(config);
			XQueryExpression expression = staticQueryContext.compileQuery(query);
			DynamicQueryContext dynaContext = new DynamicQueryContext(config);
			DocumentInfo doc = staticQueryContext.buildDocument(saxsource);
			dynaContext.setContextItem(doc);
			SequenceIterator sequenceIterator = expression.iterator(dynaContext);
			Properties props = new Properties();
			props.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    props.setProperty(OutputKeys.ENCODING, "iso-8859-1");
		    props.setProperty(OutputKeys.METHOD, "xml");
		    props.setProperty(OutputKeys.INDENT, "no");
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			QueryResult.serializeSequence(sequenceIterator,config,baos,props);
			xmlFragment = baos.toString();
		} catch (XPathException e) {
			e.printStackTrace();
		}
			
		
		return xmlFragment;
		
	}
	
	public static DocumentInfo findFragment(InputSource xml, String query){
		DocumentInfo docInfo = null;
		try {
			InputSource is = xml;
			SAXSource saxsource = new SAXSource( is);
			Configuration config = new Configuration();
			config.setHostLanguage(Configuration.XQUERY);
			config.setStripsAllWhiteSpace(true);
			
			StaticQueryContext staticQueryContext = new StaticQueryContext(config);
			XQueryExpression expression = staticQueryContext.compileQuery(query);
			DynamicQueryContext dynaContext = new DynamicQueryContext(config);
			DocumentInfo doc = staticQueryContext.buildDocument(saxsource);
			dynaContext.setContextItem(doc);
			SequenceIterator sequenceIterator = expression.iterator(dynaContext);
			docInfo = QueryResult.wrap(sequenceIterator, config);
			
		} catch (XPathException e) {
			e.printStackTrace();
		}
		return docInfo;
	}
	
	public static Element getElement(String xml, String query) throws IOException{
		Element element = null;
		String respuesta = XMLFinder.findFragment(xml, query);
		respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" + respuesta;
		$log.info("getElement:" + respuesta);
		element = generateElement(respuesta);
		return element;
	}
	
	public static Element getElementWithTagSurround(String xml, String query, String tag) throws IOException{
		Element element = null;
		String respuesta = XMLFinder.findFragment(xml, query);
		String iniTag = "<" + tag + ">";
		String endTag = "</" + tag + ">";
		respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" + iniTag + respuesta + endTag;
		$log.info("getElementWithTagSurround: " + respuesta);
		element = generateElement(respuesta);
		return element;
	}
	
	private static Element generateElement(String respuesta) throws IOException{
		Element element = null;
		DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(respuesta.getBytes());
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document docRespuesta = builder.parse(bais);
			element = docRespuesta.getDocumentElement();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return element;
	}

}
