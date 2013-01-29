package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AlnovaRequest implements Serializable
{ 
	private final Map<String,String> __map;
	private final String __nombre;
	private final String[] __keys;
	private final boolean __needChannel;

	public	AlnovaRequest( String nombre, String[] keys )
	{
		this( nombre, keys, false );
	}

	public	AlnovaRequest( String nombre, String[] keys, boolean needChannel )
	{
		__map=new HashMap<String,String>( );
		__nombre=nombre;
		__keys=keys;
		__needChannel=needChannel;
	}

	public void addParameter( String key, String value )
	{
		__map.put( key, value );
	}
	public Map map( )
	{
		return __map;
	}
	public String[] keys( )
	{
		return __keys;
	}
	public boolean needChannel( )
	{
		return __needChannel;
	}

	public void reset( )
	{
		__map.clear( );
	}
	public String nombre( )
	{
		return __nombre;
	}
	
	public String getAttribute(String key){
		return __map.get(key);
	}
}
