package com.bancoazteca.eservice.ws;



import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.transport.http.AxisServlet;

public class ProcessWS extends AxisServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<< Inicio Headers >>>>>>>>>>>>>>>>>>>>>>>>>");
			Enumeration<String> headerNames=req.getHeaderNames();
			while(headerNames.hasMoreElements()){
				String headerName=headerNames.nextElement();
				String headerValue=req.getHeader(headerName);
				System.out.println("Header: "+headerName+" contiene: "+headerValue);
			}
			System.out.println("<<<<<<<<<<<<<<<<<<<< Fin Headers >>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			System.out.println("Ocurrio una excepcion: "+e.getMessage());
		}
		
		
		try {
			System.out.println("++++++++++++++++++++++ Inicio Cookie+++++++++++++++++++++++++++");
			Cookie[] cookies=req.getCookies();
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if(cookie!=null){
						String cookieName=cookie.getName();
						String cookieDomain=cookie.getDomain();
						String cookiePath=cookie.getPath();
						System.out.println("Cookie: "+cookieName+" Dominio: "+cookieDomain+" ruta: "+cookiePath);
					}else{
						System.out.println("Cookie nula");
					}
				}
			}
			else {
				System.out.println("No hay cookies");
			}
			System.out.println("++++++++++++++++++++++ Fin Cookie+++++++++++++++++++++++++++");
		} catch (Exception e) {
			System.out.println("Ocurrio una excepcion: "+e.getMessage());
		}
		
		super.service(req, resp);
	}

}
