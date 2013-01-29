package com.bancoazteca.elite.ejb.alnova.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Random;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public
class TerminalManager
{
	private static final
	String DATABASE_NAME=NombreRecursosJNDI.EBANKINGDS;
	private static final
	int MAX_ATTEMPTS=32;
	private static final
	int NUM_TERMINALS=599;

	private static final
	String SELECT_QUERY="SELECT code FROM alnova_terminal WHERE busy = 0 AND id = {0,number} FOR UPDATE";
	private static final
	String UPDATE_QUERY="UPDATE alnova_terminal SET busy = {0,number} WHERE code = ''{1}''";
	private static final
	Integer BUSY_STATE=1;
	private static final
	Integer NOTBUSY_STATE=2;

	public static final
	String acquire( )
		throws TerminalNotAvailableException
	{
		final
		Connection connection;
		String terminal;

		connection=connection( );
		try
		{
			final
			Statement statement;

			try
			{
				statement=connection.createStatement( );
			}
			catch( SQLException exception )
			{
				exception.printStackTrace( );
				throw new IllegalStateException( "Al crear el statement" );
			}
			try
			{
				final
				Random random;
				int i;
				final
				Object[] args;
				int id;

				random=new Random( System.currentTimeMillis( ) );
				args=new Object[ 1 ];
				terminal=null;
				for( i=0; i<MAX_ATTEMPTS; i++ )
				{
					final
					String query;
					final
					ResultSet resultSet;
					final
					boolean result;

					id=random.nextInt( NUM_TERMINALS )+1;
					args[ 0 ]=new Integer( id );
					query=MessageFormat.format( SELECT_QUERY, args );
					System.out.println( query );
					try
					{
						resultSet=statement.executeQuery( query );
					}
					catch( SQLException exception )
					{
						exception.printStackTrace( );
						continue;
					}
					try
					{
						try
						{
							result=resultSet.next( );
						}
						catch( SQLException exception )
						{
							exception.printStackTrace( );
							continue;
						}
						if( result )
						{
							try
							{
								terminal=resultSet.getString( "code" );
								break;
							}
							catch( SQLException exception )
							{
								exception.printStackTrace( );
								continue;
							}
						}
					}
					finally
					{
						try
						{
							resultSet.close( );
						}
						catch( SQLException exception )
						{
							exception.printStackTrace( );
						}
					}
				}
				if( terminal != null )
				{
					final
					String query;
					int result;
					final
					Object[] args2={ BUSY_STATE, terminal };

					query=MessageFormat.format( UPDATE_QUERY, args2 );
					System.out.println( query );
					try
					{
						result=statement.executeUpdate( query );
					}
					catch( SQLException exception )
					{
						exception.printStackTrace( );
						throw new IllegalStateException( "No se pudo actualizar el estado de la terminal: " + terminal );
					}
				}
				else
				{
					throw new TerminalNotAvailableException( );
				}
			}
			finally
			{
				try
				{
					statement.close( );
				}
				catch( SQLException exception )
				{
					exception.printStackTrace( );
				}
			}
		}
		finally
		{
			try
			{
				connection.close( );
			}
			catch( SQLException exception )
			{
				exception.printStackTrace( );
			}
		}
		return terminal;
	}
	public static final
	void release( String terminal )
	{
		final
		Connection connection;

		connection=connection( );
		try
		{
			final
			Statement statement;

			try
			{
				statement=connection.createStatement( );
			}
			catch( SQLException exception )
			{
				exception.printStackTrace( );
				throw new IllegalStateException( "Al crear el statement" );
			}
			try
			{
				final
				String query;
				int result;
				final
				Object[] args={ NOTBUSY_STATE, terminal };

				query=MessageFormat.format( UPDATE_QUERY, args );
				System.out.println( query );
				try
				{
					result=statement.executeUpdate( query );
				}
				catch( SQLException exception )
				{
					exception.printStackTrace( );
				}
			}
			finally
			{
				try
				{
					statement.close( );
				}
				catch( SQLException exception )
				{
					exception.printStackTrace( );
				}
			}
		}
		finally
		{
			try
			{
				connection.close( );
			}
			catch( SQLException exception )
			{
				exception.printStackTrace( );
			}
		}
	}

	private static final
	Connection connection( )
	{
		final
	    InitialContext context;
	    final
	    DataSource dataSource;

	    try
	    {
			context=new InitialContext( );
		}
		catch( NamingException exception )
		{
			exception.printStackTrace( );
			throw new IllegalStateException( "Al tratar de inicializar el contexto" );
		}
	    try
	    {
			dataSource=(DataSource)context.lookup(DATABASE_NAME);
		}
		catch( NamingException exception )
		{
			exception.printStackTrace( );
			throw new IllegalStateException( "Al tratar de localizar el recurso " + DATABASE_NAME );
		}
	    try
	    {
			return dataSource.getConnection( );
		}
		catch( SQLException exception )
		{
			exception.printStackTrace( );
			throw new IllegalStateException( "Al tratar de obtener una conexion de el recurso" + DATABASE_NAME );
		}
	}
}
