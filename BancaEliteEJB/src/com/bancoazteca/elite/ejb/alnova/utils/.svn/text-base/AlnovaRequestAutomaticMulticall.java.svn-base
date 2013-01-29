package com.bancoazteca.elite.ejb.alnova.utils;

import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public abstract class AlnovaRequestAutomaticMulticall
	extends EjbAlnovaRequestTO
{
	private final
	String[] __keys;

	public
	AlnovaRequestAutomaticMulticall( String nombre, String[] keys, String[] keys2 )
	{
		super( nombre, keys );
		__keys=keys2;
	}
	public
	AlnovaRequestAutomaticMulticall( String nombre, String[] keys, String[] keys2, boolean needChannel )
	{
		super( nombre, keys );
		__keys=keys2;
	}

	public
	String[] keys( )
	{
		final
		String[] keys;

		if( isFirsthCall( ) )
		{
			keys=super.keys();
		}
		else
		{
			keys=__keys;
		}
		return keys;
	}

	public abstract
	boolean needResponse( );
	public abstract
	boolean isFirsthCall( );
	public abstract
	boolean hasMoreCalls( AlnovaResponse response );
}
