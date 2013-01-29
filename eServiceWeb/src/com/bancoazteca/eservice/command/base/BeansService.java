package com.bancoazteca.eservice.command.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeansService {
	
	private static BeansService instance;
	
	private final String CONFIG_FILE = "Commands.xml";
		
	private final BeanFactory beanFactory;
	
	public static synchronized BeansService getInstance(){
		if (instance==null){
			instance = new BeansService();
		}
		return instance;
	}
	
	private BeansService(){
		ClassPathResource classPathResource = new ClassPathResource(CONFIG_FILE);
		beanFactory  = new XmlBeanFactory(classPathResource);
	}
	
	public synchronized Command getCommand(String idCommand){
		return (Command) this.beanFactory.getBean(idCommand);
	}
	
	

}
