package com.bancoazteca.elite.ejb.inversiones.beans;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import com.bancoazteca.elite.ejb.inversiones.utils.InversionesConstantes;

public class InversionesRequestTO implements InversionesConstantes{


	private String type;
	private String operation;
	private String portalSolicitante;
	private String idAlnova;

	public String getIdAlnova() {
		return idAlnova;
	}
	public void setIdAlnova(String idAlnova) {
		this.idAlnova = idAlnova;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getPortalSolicitante() {
		return portalSolicitante;
	}
	public void setPortalSolicitante(String portalSolicitante) {
		this.portalSolicitante = portalSolicitante;
	}

	public String toString(){
		StringBuffer string = new StringBuffer();
		try {
			
			Class cls = Class.forName(this.getClass().getCanonicalName().toString());
			Field declaredFieldList[] = cls.getDeclaredFields();
			Field fieldList[] = cls.getSuperclass().getDeclaredFields();
			ArrayList<String> arrayList=new ArrayList<String>();
			for (Field field : declaredFieldList) {
				arrayList.add(field.getName());
			}
			for(Field field:fieldList){
				arrayList.add(field.getName());	
			}
			
			String campo;
			String firstCharacter;
			String metodo;
			String value;
			
			Iterator<String> iteradorFields = arrayList.iterator();
			string.append("/*--------------------  "+cls.getSimpleName()+"  -----------------------*/\n");
			while(iteradorFields.hasNext()) {
			
				try{
					campo = iteradorFields.next();                                     
					firstCharacter=campo.substring(0,1).toUpperCase();
					metodo = campo.substring(1);
					metodo=firstCharacter+metodo;
					Method mtd = cls.getMethod("get"+metodo,(Class[])null);            	   
					value = (String)mtd.invoke(this,(Object[])null);
					string.append(campo+"=");
					string.append(value+"\n");
				}catch(Exception e){}
			}
			string.append("--------------------------------------------------------------------\n");
		}
		catch (Throwable e){
			System.out.println("Error");
		}
		return string.toString();
	}
}
