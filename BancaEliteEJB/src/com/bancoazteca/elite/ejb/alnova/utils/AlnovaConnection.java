package com.bancoazteca.elite.ejb.alnova.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;
import com.bancoazteca.elite.util.PropertiesService;


public class AlnovaConnection{

	private static String $_url;
	private static String $_branch;
	private static String $_entity;
	private static String $_user;
	private static String $_terminal;
	private static String $_chanel;
	
	private String request;
	
	Logger $_log=Logger.getLogger(AlnovaConnection.class);

	public AlnovaConnection(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws IOException{
		String idPortal;
		try {
			idPortal = ejbAlnovaRequestTO.getIdAplicacion().toLowerCase();
		} catch (NullPointerException e) {
			idPortal="app_banca_elite";
		}
		String propertiesName="ConfiguracionesAlnova.properties";
		$_log.info("AlnovaConnection: "+idPortal);
		PropertiesService properties=PropertiesService.getInstance();	
		$_url=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.url");			
		$_entity=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.entity");
		$_user=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.user");
		$_chanel=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.chanel");

		if(ejbAlnovaRequestTO.getConfiguracionTerminal()!=null){
			$_terminal= ejbAlnovaRequestTO.getConfiguracionTerminal();
		}else{
			$_terminal=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.terminal");
		}
		if(ejbAlnovaRequestTO.getConfiguracionSucursal()!=null){
			$_branch= ejbAlnovaRequestTO.getConfiguracionSucursal();
		}else{
			$_branch=properties.getPropertie(propertiesName,idPortal+".webservice.alnova.config.branch");
		}
	}


	public	AlnovaResponse executewithURL( AlnovaRequestAutomaticMulticall request, String url )throws TerminalNotAvailableException{
		final String terminal;
		final AlnovaResponse response;
		$_url = url;
		terminal=TerminalManager.acquire( );
		try
		{
			return execute( request, terminal,url );
		}
		finally
		{
			TerminalManager.release( terminal );
		}
	}

	public AlnovaResponse execute( AlnovaRequestAutomaticMulticall request )throws TerminalNotAvailableException{
		final String terminal;
		final AlnovaResponse response;

		terminal=TerminalManager.acquire( );
		try{
			return execute( request, terminal,null );
		}
		finally{
			TerminalManager.release( terminal );
		}
	}

	private AlnovaResponse execute( AlnovaRequestAutomaticMulticall request, String terminal, String urls )throws TerminalNotAvailableException{
		final Map resultMap;
		final Map map;
		final List messagesList;
		String message;
		String alnovaRequest;
		String alnovaResponse;

		resultMap=new HashMap( );
		map=new HashMap( );
		messagesList=new ArrayList( );
		message="";
		alnovaResponse="";
		while( true )
		{
			final Iterator iterator;

			System.out.println( request.isFirsthCall( ) );
			execute( map, request, terminal, (!request.isFirsthCall( )? alnovaResponse.substring( 0, alnovaResponse.lastIndexOf( '~' ) ): null),urls );
			message=(String)map.remove( AlnovaCodec.MESSAGE_KEY );
			alnovaRequest=(String)map.remove( AlnovaCodec.REQUEST_KEY );
			alnovaResponse=(String)map.remove( AlnovaCodec.RESPONSE_KEY );
			messagesList.add( message );
			iterator=map.keySet( ).iterator( );
			while( iterator.hasNext( ) )
			{
				final
				Object key;
				final
				Object value;
				final
				Object resultValue;

				key=iterator.next( );
				resultValue=resultMap.get( key );
				value=map.get( key );
				if( resultValue != null )
				{
					final
					List resultList;

					if( resultValue instanceof List )
					{
						resultList=(List)resultValue;
					}
					else
					{
						resultList=new ArrayList( );
						resultList.add( resultValue );
						resultMap.put( key, resultList );
					}
					if( value instanceof List )
					{
						resultList.addAll( (List)value );
					}
					else
					{
						resultList.add( value );
					}
				}
				else
				{
					resultMap.put( key, value );
				}
			}
			if( !request.hasMoreCalls( new AlnovaResponse( map, alnovaRequest, alnovaResponse, message ) ) )
			{
				break;
			}
			map.clear( );
		}
		return new AlnovaResponse( resultMap, alnovaRequest, alnovaResponse, message );
	}
	public AlnovaResponse executewithURL( EjbAlnovaRequestTO request,String url )
	{
		final
		Map map;
		final
		String message;
		final
		String alnovaRequest;
		final
		String alnovaResponse;


		map=new HashMap( );
		execute( map, request, "NKRL", null, url );
		message=(String)map.remove( AlnovaCodec.MESSAGE_KEY );
		alnovaRequest=(String)map.remove( AlnovaCodec.REQUEST_KEY );
		alnovaResponse=(String)map.remove( AlnovaCodec.RESPONSE_KEY );
		return new AlnovaResponse( map, alnovaRequest, alnovaResponse, message );
	}

	public AlnovaResponse execute( EjbAlnovaRequestTO request ){
		final Map map;
		final String message;
		final String alnovaRequest;
		final String alnovaResponse;

		map=new HashMap( );
		execute( map, request, $_terminal, null,null );
		message=(String)map.remove( AlnovaCodec.MESSAGE_KEY );
		alnovaRequest=(String)map.remove( AlnovaCodec.REQUEST_KEY );
		alnovaResponse=(String)map.remove( AlnovaCodec.RESPONSE_KEY );
		return new AlnovaResponse( map, alnovaRequest, alnovaResponse, message );
	}

	private void execute( Map map, EjbAlnovaRequestTO request, String terminal, String extra, String Url )
	{
		final String result;
		final String query;
		final String nombre;

		try
		{
			if( request.needChannel( ) )
			{
				query=AlnovaCodec.encode( request.map( ), request.keys( ), $_branch, $_entity, $_user, terminal, $_chanel ) + (extra != null?  ('~' + extra): "");
			}
			else
			{
				System.out.println( extra );
				query=AlnovaCodec.encode( request.map( ), request.keys( ), $_branch, $_entity, $_user, terminal ) + (extra != null? '~' + extra: "");
			}
		}
		catch( RuntimeException exception )
		{
			exception.printStackTrace( );
			throw exception;
		}
		
		map.put(AlnovaCodec.REQUEST_KEY,query);
		
		nombre=request.nombre( );
		
		try
		{
			
			final URL url;
			final HttpURLConnection connection; 
			final String string;
			InputStream inputStream;

			string=$_url + "?mtIdTransaccion=" + nombre + "&mtDatosEntrada=" + URLEncoder.encode(query);
			url=new URL( string );
			$_log.info("URL ALNOVA:  "+url);
			this.request=url.toString();
			connection=(HttpURLConnection)url.openConnection( );
			
			if( connection.getResponseCode( ) == HttpURLConnection.HTTP_OK )
			{
				final BufferedReader reader;
				String data;
				final int size;
				final int index1;
				int index2;

				try{
					inputStream=connection.getInputStream( );
				}
				catch( IOException exception )
				{
					exception.printStackTrace( );
					inputStream = url.openStream( );
				}

				reader=new BufferedReader( new InputStreamReader( inputStream ) );
				reader.readLine( );
				data=reader.readLine( );
				index1=data.indexOf( '>' ) + 1;
				index2=data.indexOf( '<', index1 );

				while( index2 < 0 )
				{
					final
					String line;

					line=reader.readLine( );
					if( line == null )
					{
						break;
					}
					data += line;
					index2=data.indexOf( '<', index1 );
				}
				result=data.substring( index1, index2 );
				map.put( AlnovaCodec.RESPONSE_KEY, result);
			}
			else
			{
				result="";
			}
		}
		catch( MalformedURLException exception )
		{
			exception.printStackTrace( );
			throw new IllegalStateException( );
		}
		catch( IOException exception )
		{
			exception.printStackTrace( );
			throw new IllegalStateException( );
		}
		AlnovaCodec.decode( map, result );
	}


	public String getRequest() {
		return request;
	}


	public void setRequest(String request) {
		this.request = request;
	}
}
