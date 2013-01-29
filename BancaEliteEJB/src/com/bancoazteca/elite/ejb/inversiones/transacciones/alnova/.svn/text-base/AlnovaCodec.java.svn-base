package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bancoazteca.elite.util.Formatter;

public final class AlnovaCodec
{
	public static final String MESSAGE_KEY="~MESSAGE";
	public static final String REQUEST_KEY="~REQUEST";
	public static final String RESPONSE_KEY="~RESPONSE";

	private AlnovaCodec( ){}

	public static final String encode( Map map, String[] keys, String branch, String entity, String user, String terminal )
	{
		return encode( map, keys, branch, entity, user, terminal, null );
	}
	
	public static final String encode( Map map, String[] keys, String branch, String entity, String user, String terminal, String channel )
	{
		final StringBuffer buffer;
		int lenght;
		int i;

		buffer=new StringBuffer( );
		buffer.append( "~USER/" ).append( user );
		buffer.append( "~BRANCH/" ).append( branch );
		buffer.append( "~ENTITY/" ).append( entity );
		buffer.append( "~TERMINAL/" ).append( terminal );
		if( channel != null )
		{
			channel=channel.trim( );
			if( channel.length( ) > 0 )
			{
				buffer.append( "~CHANNEL/" ).append( channel );
			}
		}
		lenght=keys.length;
		for( i=0; i<lenght; i++ )
		{
			final
			String key;
			final
			String value;

			key=keys[ i ];
			value=(String)map.get( key );
			buffer.append( '~' );
			buffer.append( key );
			buffer.append( '/' );
			buffer.append( value );
		}
		return buffer.substring( 1 ).toString( );
	}
	public static final
	Map decode( Map map, String string )
	{
		final
		int length;
		int index1;
		final
		String __message;
		final
		String __messageReal;
		final
		String __messageCode;

		length=string.length( );
		index1=0;
		while( index1 < length )
		{
			final
			int index2;
			final
			int index3;
			final
			String key;
			final
			String value;
			final
			Object oldValue;

			index3=string.indexOf( '~', index1 );
			if( index3 < 0 )
			{
				break;
			}
			index2=string.indexOf( '/', index1 );
			if( index2 >= 0 )
			{
				if( index2 > index3 )
				{
					break;
				}
			}
			key=string.substring( index1, index2 );
			value=Formatter.removeSpacesLeftRight(string.substring( index2+1, index3 ));
			
			oldValue=map.get( key );
			if( oldValue != null )
			{
				final
				List list;

				if( oldValue instanceof List )
				{
					
					list=(List)oldValue;
				}
				else
				{
					list=new ArrayList( );
					list.add(map.get(key));
					map.put( key, list );
				}
				list.add( value );
			}
			else
			{
				map.put( key, value );
			}
			index1=index3+1;
		}
		if( index1 < length )
		{
			map.put( MESSAGE_KEY, string.substring( index1 ) );
		}
/*
		if( index1 < length )
		{
			final
			Object object;
			final
			Object value;
			final
			String message;

			message=string.substring( index1 );
			object=map.get( MESSAGE_KEY );
			if( map != null )
			{
				final
				List list;

				if( object instanceof List )
				{
					list=(List)object;
				}
				else
				{
					list=new ArrayList( );
				}
				list.add( message );
				value=list;
			}
			else
			{
				value=message;
			}
			map.put( MESSAGE_KEY, value );
		}
*/
		return map;
	}
}
