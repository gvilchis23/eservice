package com.bancoazteca.eservice.utils.eserviceemail.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {
	
	
	private final String fullFileName; 
	public ReadFiles(String fullFileName) {
		this.fullFileName=fullFileName;
	}
	public String  readFileString(){
		FileReader fileReader;
		String stringFile = "";
		try {
			fileReader = new FileReader( fullFileName );
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while (bufferedReader.ready()){
				stringFile += bufferedReader.readLine().trim();
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException, Archivo no encontrado: " + fullFileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOExceptio, Error al leer el Archivo: " + fullFileName);
			e.printStackTrace();
		}
		return stringFile;
	}
	
	public BufferedReader readFileBufferedReader(){
		FileReader fileReader;
		BufferedReader bufferedReader=null;
		try {
			fileReader = new FileReader( fullFileName );
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException, Archivo no encontrado: "+ fullFileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOExceptio, Error al leer el Archivo: "+fullFileName);
			e.printStackTrace();
		}
		return bufferedReader;
	}
}
