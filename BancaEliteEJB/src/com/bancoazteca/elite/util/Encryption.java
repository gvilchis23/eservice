package com.bancoazteca.elite.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.bancoazteca.elite.ejb.exception.DecryptionException;
import com.bancoazteca.elite.ejb.exception.EncryptionException;
import com.bancoazteca.elite.ejb.exception.KeyGeneratorException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public final class Encryption {
	
	private static Encryption encrypter;	
	
	static	{
		encrypter = new Encryption();
	}
	
	private static final char[] KEYS = {
			'A','B','C','D','E','F','G','H','I','J','K','L','M',
			'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m',
			'n','o','p','q','r','s','t','u','v','w','x','y','z',
			'0','1','2','3','4','5','6','7','8','9','!','@','#',
			'$','%','^','&','*','(',')','-','_','+','=','|','?'
		};
	private Cipher cipher;
	
	private Encryption() {
		try {
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

	}
	synchronized public static Encryption getInstance() {
		if(encrypter == null)
			return new Encryption();
		
		return encrypter;
	}
	synchronized public byte[][] encriypt(String text) throws EncryptionException	{
		byte retValue[][] = new byte[2][];
		
		try {
			retValue[0] = getKey();
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(retValue[0], "DES"));
			retValue[1] = new BASE64Encoder().encode(cipher.doFinal(text.getBytes("UTF-8"))).getBytes();			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (KeyGeneratorException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		}
		
		return retValue;		
	}
	synchronized public byte[] decrypt(byte[] key, String text) throws DecryptionException	{
		byte retValue[] = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DES"));
			retValue = cipher.doFinal(new BASE64Decoder().decodeBuffer(text));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new DecryptionException(e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new DecryptionException(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new DecryptionException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DecryptionException(e);
		}
		return retValue;
	}
	synchronized private byte[] getKey() throws KeyGeneratorException	{
		StringBuffer buffer = new StringBuffer();
		byte[] retValue = null;
		
		Random r = new Random();
		int length = KEYS.length-1;
		for(int i = 0; i < 8; i+=2){
			buffer.append(KEYS[r.nextInt(length)]);
			buffer.append(KEYS[r.nextInt(length)]);
		}
		try {
			retValue = buffer.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new KeyGeneratorException(e);
		}
		return retValue;
	}	
}
