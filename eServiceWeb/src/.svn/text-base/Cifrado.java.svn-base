import com.bancoazteca.eservice.util.security.SecureDataUtil;

public class Cifrado {
	
	 public static String probarCifrado(String textoClaro){
			System.out.println("\n************** Cifrado ***************\n");
			String ciphered = "";
			try{
				ciphered = new SecureDataUtil().cryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), textoClaro);
				System.out.println("El cifrado de "+textoClaro+" es: "+ciphered);
				
			}catch(Exception e){
				System.out.println("Error al cifrar el texto "+textoClaro);
			}
			System.out.println("\n************** Cifrado ***************\n");
			return ciphered;
		}
	 public static String probarDesCifrado(String textoClaro){
			System.out.println("\n************** Cifrado ***************\n");
			String ciphered = "";
			try{
				ciphered = new SecureDataUtil().decryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), textoClaro);
				System.out.println("El decifrado de "+textoClaro+" es: "+ciphered);
				
			}catch(Exception e){
				System.out.println("Error al descifrar el texto "+textoClaro);
			}
			System.out.println("\n************** Cifrado ***************\n");
			return ciphered;
		}
	 public static void main (String... args ){

		 probarCifrado("password");
	 }
}
