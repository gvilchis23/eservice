package com.bancoazteca.elite.command.base;

public class CommandException extends Throwable{

	private static final long serialVersionUID = -9094461744877960576L;

	public CommandException(String message){
		super(message);
	}
	
	public CommandException(String message,Throwable e){
		super(message,e);
	}
	
	public CommandException(Throwable e){
		super(e.getMessage());
	}

}
