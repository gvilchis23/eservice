package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public
class AlnovaResponse implements Serializable{
	private final
	Map __map;
	private final
	String __message;
	private final
	String __messageCode;
	private final
	String __messageReal;
	private final
	String __alnovaRequest;
	private final
	String __alnovaResponse;

	public
	AlnovaResponse( Map map, String alnovaRequest, String alnovaResponse, String message )
	{
		final int length;
		final int indexA;

		__message=message;
		__map=map;
		__alnovaRequest=alnovaRequest;
		__alnovaResponse=alnovaResponse;
		length=message.length( );
		indexA=message.indexOf( '(' );
		if( indexA >= 0 )
		{
			final
			int indexB;

			indexB=message.indexOf( ')', indexA );
			if( indexB >= 0 )
			{
				__messageCode=message.substring( indexA+1, indexB );
				if( indexB+2 < length )
				{
					__messageReal=message.substring( indexB+2 );
				}
				else
				{
					__messageReal="";
				}
			}
			else
			{
				__messageReal="";
				__messageCode="";
			}
		}
		else
		{
			__messageReal="";
			__messageCode="";
		}
	}

	public
	String getAttribute( String key )
	{
		final
		Object object;
		final
		String value;

		object=__map.get( key );
		if( object != null )
		{
			if( object instanceof List )
			{
				final
				List list;

				list=(List)object;
				value=(String)list.get( 0 );
			}
			else
			{
				value=(String)object;
			}
		}
		else
		{
			value=null;
		}
		return value;
	}
	public
	List getAttributeList( String key )
	{
		final
		Object object;
		final
		List list;

		object=__map.get( key );
		if( object != null )
		{
			if( object instanceof List )
			{
				list=Collections.unmodifiableList((List)object);
			}
			else
			{
				list=Collections.singletonList( object );
			}
		}
		else
		{
			list=Collections.EMPTY_LIST;
		}
		return list;
	}
	public
	String getMessage( )
	{
		return __message;
	}
	public
	String getMessageCode( )
	{
		return __messageCode;
	}
	public
	String getMessageReal( )
	{
		return __messageReal;
	}
	private
	boolean isError( )
	{
		final
		boolean result;

		return __messageCode != null
			&& __messageCode.length( ) >= 3
			&& __messageCode.charAt( 2 ) == 'E';
	}
	public
	String alnovaRequest( )
	{
		return __alnovaRequest;
	}
	public
	String alnovaResponse( )
	{
		return __alnovaResponse;
	}
}
