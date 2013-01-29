package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova;

public abstract class AlnovaRequestAutomaticMulticall
	extends AlnovaRequest
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
