/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bancoazteca.elite.command.base;

/**
 *
 * @author hb1
 */
public final class Encriptador {
	
	private final String llave = "TH3B37511022SB@6025e7TS3RV1C763Z";
	private static final String _b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk.mnopqrstuvwxyz-123456789+/=";


    public static String Encode(String data, String key){
        int keypos = 0;
        String binarydata = "";
        //convert the sting to a 8bit binary data
        for(int i=0;i<data.length();i++){
        	char c=data.charAt(i);
            char  temp= key.charAt(keypos);
            int xor = ((int)c^(int)temp)+(key.length());
            if (++keypos >= key.length()){
            	keypos = 0;
            }
            binarydata += DecToBinary(xor, 8);
        }
        int m = 0;
        String cipher = "";
        // splitt the binary string to 4 byte chunks and assign each chunk the proper b64 value
        for (int i = 0; i < binarydata.length(); i += 4){
            String dato=binarydata.substring(i, i+4);
            int v = BinToDec(dato);
            cipher += GetB64FromN(v * 4 + m);
            if (++m > 3){
            	m = 0;
            }
        }
        return cipher;
    }


    public String Crypt(String original) {
        return Encode(original,llave);
    }

    public static void main(String[] args) {
		System.out.println(new Encriptador().Crypt("123"));
//    	System.out.println(new Encriptador().Decrypt("gVmrIB"));
	}
    
    public String Decrypt(String cifrado){
        return Decode(cifrado,llave);
    }

    private static String Decode(String data, String key){
        int m = 0;
        String binarydata = "";
        // convert the b64 string to binary string
        for(int i=0;i<data.length();i++){
        	char c=data.charAt(i);
            int v = (GetNFromB64(c) - m) / 4;
            binarydata += DecToBinary(v, 4);
            if (++m > 3){
            	m = 0;
            }
        }

        // chop the 8bit binaries and mix back the key into it
        int keypos = 0;
        String decoded = "";
        for (int i = 0; i < binarydata.length(); i += 8){
            if (i + 8 > binarydata.length()){
            	break;
        	}
            int c = BinToDec(binarydata.substring(i, i+8));
            int dif1=(c - key.length());
            char nu=key.charAt(keypos);
            int dif2=(int)nu;
            int dc = dif1 ^ dif2;
            if (++keypos >= key.length()){
            	keypos = 0;
            }
            decoded +=(char)dc;
        }
        return decoded;
    }
    
 // expects a base64 character and returns it's position in the base64 alphabet
    private static int GetNFromB64(char n){
        return _b64.indexOf(n);
    }

    // expects a position in the base64 alphabet and returns it's base64 character.
    private static String GetB64FromN(int n){
        String salida="";
        if (n > _b64.length()){
            return "="; // well, we shouldn't reach this line. If we do, the encoding will be garbage anyway...
        }
        salida+=_b64.charAt(n);
        return salida;
    }

    private static String DecToBinary(int value, int longitud){
        // Declare a few variables we're going to need
        String binString = "";
        int i;
        while (value > 0){
            binString += value % 2;
            value /= 2;
        }

        // we need to reverse the binary string
        // that's why we are using this array stuff here.

        String reverseString = "";

        for (i=0;i<binString.length();i++){
        	reverseString = binString.charAt(i) + reverseString;
        }

        binString = reverseString;

        // do the padding here
       
        binString =pad(binString,longitud);

        return binString;
    }
 // expects the binary string and returns it's integer equivalent
    private static int BinToDec(String Binary){
        return Integer.parseInt(Binary, 2);
    }
    
    private static String pad(String original,int longitud){
    	int recorre=0;
    	String temporal="";

    	for(recorre=0;recorre<(longitud-original.length());recorre++)
    	{
    		temporal+="0";
    	}

    	return temporal+original;
    }
}