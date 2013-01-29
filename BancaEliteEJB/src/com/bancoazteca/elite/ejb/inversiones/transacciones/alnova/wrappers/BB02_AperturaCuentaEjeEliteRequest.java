package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.beans.BeneficiarioTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasEntradaTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class BB02_AperturaCuentaEjeEliteRequest extends AlnovaRequest {

	private static final long serialVersionUID = -1152134419923251743L;
	
	//public static final String[] keys={"FUNCION","REFEREN","ENTRADA","CTAASOC","PRNTSCO","CHANNEL","SALIDA"};
	public static final String[] keys={"FUNCION","REFEREN","ENTRADA","CTAASOC","CHANNEL","SALIDA"};
	private char[] entrada=new char[288];
	
	{
		for (int i = 0; i < entrada.length; i++) {
			entrada[i]=' ';
		}
		
	}
	
	/**
	 * Establece la Operacion a realizar y los keys de los atrubutos a utilizar 
	 */
	public BB02_AperturaCuentaEjeEliteRequest(){
		super("BB02", keys);
		addParameter("SALIDA","");
	}
	
	/* ****************************
	 * 		Getter and setters 
	 * 			fill MAP
	 * ****************************/
	/**
	 * Operacion 
	 * @param alfanumerico<br>
	 * longitud 2<br>
	 * AP Apertura
	 */
	public void setFuncion(String value){
		addParameter("FUNCION",value );
	}
	/**
	 * Referencia unica por cada apertura
	 * @param alfanumerico<br>
	 */
	public void setReferencia(String value){
		addParameter("REFEREN",value );
	}
	/**
	 * Cuenta asociada
	 * @param numeric<br>
	 */
	public void setCuentaAsociada(String value){
		addParameter("CTAASOC",value );
	}
	/**
	 * Parentesco
	 * @param value
	 */
	public void setParentesco(String value){
		addParameter("PRNTSCO", value);
	}
	/**
	 * Canal
	 * @param value
	 */
	public void setChanel(String value){
		addParameter("CHANNEL", value);
	}
	/**
	 * setEntrada<br>
	 * ============================================================<br>
	 * Este metodo se ejecuta al final de llenar todo el objeto<br>
	 * para que se carguen los datos en el Mapa del padre. <br>
	 */
	public void setEntrada(){
		addParameter("ENTRADA",getEntrada() );
	}
	/**
	 * setEntrada<br>
	 * ============================================================<br>
	 * Este metodo envia una cadena ya formateada con el formato solicitado en el  <br>
	 * 
	 * Posición 1-2, Código de producto <br>
	 * Posición 3-6  Entidad            <br>
	 * Posición 7-10 Sucursal<br>
	 * Posición 11-18 Empleado<br>
	 * Posición 19-22 Subproducto<br>
	 * Posición 23-30 Numero de cliente<br>
	 * Posición 31-33 Moneda<br>
	 * Posición 34-49 Monto (0000000000000000)<br>
	 * Posición 50-52 código de aplicación (000)<br>
	 * Posición 53-60 Cliente tutor<br>
	 * Posición 61-110 Beneficiario 1<br>
	 * Posición 111-115 Porcentaje del beneficiario 1<br>
	 * Posición 116-117 Contador de beneficiario<br>
	 * Posición 118-167 Beneficiario 2 <br>
	 * Posición 168-172 Porcentaje del beneficiario 2<br>
	 * Posición 173-174 Contador de beneficiario<br>
	 * Posición 175-224 Beneficiario 3<br>
	 * Posición 225-229 Porcentaje del beneficiario 3<br>
	 * Posición 230-231 Contador de beneficiario<br>
	 * Posición 232-281 Beneficiario 4<br>
	 * Posición 282-286 Porcentaje del beneficiario 4<br>
	 * Posición 287-288 Contador de beneficiario<br>
	 */
	public void setEntrada(String value){
		addParameter("ENTRADA",value );
	}
	/**
	 * setEntrada<br>
	 * ============================================================<br>
	 * Set Parametro entrada con el objeto apertura de cuentas TO
	 * @param com.bancoazteca.elite.ejb.inversiones.beans.aperturaCuentasEntradaTO
	 */
/*	public void setEntrada(AperturaCuentasEntradaTO value){
		StringBuffer buffer= new StringBuffer();
		buffer.append(value.getCodigoProducto());
		buffer.append(value.getEntidad());
		buffer.append(value.getSucursal());
		buffer.append(value.getEmpleado());
		buffer.append(value.getSubProducto());
		buffer.append(value.getNumeroCliente());
		buffer.append(value.getMoneda());
		buffer.append(value.getMonto());
		buffer.append(value.getCogidoAplicacion());
		buffer.append(value.getClienteTutor());
		
		for(int i=0;i<value.getBeneficiarios().size();i++){
			BeneficiarioTO beneficiario= value.getBeneficiarios().get(i);
			buffer.append(beneficiario.getNombreCompleto());
			buffer.append(beneficiario.getPorcentaje());
			buffer.append("0"+(i+1));
					break;
		}
		addParameter("ENTRADA",buffer.toString());
	}
	*/
	
	
	/* ****************************
	 * 		Getter and setters 
	 * 			fill ENTRADA
	 * ****************************/
	/**
	 * Codigo del producto
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitud 2<br>
	 */
	public void setCodigoProducto(String value){
		replaceRigth(value, 0, 1, ' ');
	}
	/**
	 * Entidad perteneciente
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitud 4<br>
	 */
	public void setEntidad(String value){
		replaceRigth(value, 2, 5, ' ');
	}
	/**
	 * numero de Sucursal 
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 4<br>
	 */
	public void setSucursal(String value){
		replaceRigth(value, 6, 9, ' ');
	}
	/**
	 * Numero de empleado
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 8<br>
	 */
	public void setEmpleado(String value){
		replaceRigth(value, 10, 17, ' ');
	}
	/**
	 * Numero de subproducto
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 4<br>
	 */
	public void setSubproducto(String value){
		replaceRigth(value, 18, 21, ' ');
	}
	/**
	 * Numero de cliente
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 8<br>
	 */
	public void setNumeroCliente(String value){
		replaceRigth(value, 22, 29, ' ');
	}
	/**
	 * Codigo de moneda de apertura de cuenta
	 * @param java.lang.String <br>
	 * alfanumerico<br>
	 * longitug 3<br>
	 */
	public void setMoneda(String value){
		replaceRigth(value, 30, 32, ' ');
	}
	/**
	 * Monto de apertura de cuenta
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 16<br>
	 */
	public void setMonto(String value){
		replaceLeft(value.replace(".", "").replace(",", ""), 33, 48, '0');
	}
	/**
	 * Numero de codigo de operacion 
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 3<br>
	 */
	public void setCodigoAplicacion(String value){
		replaceRigth(value, 49, 51, ' ');
	}
	/**
	 * Numero del cliente tutor 
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 8<br>
	 */
	public void setClienteTutor(String value){
		replaceRigth(value, 52, 59, ' ');
	}
	
	
	
	/**
	 * Nombre del beneficiario 1
	 * @param java.lang.String <br>
	 * alfanuerico<br>
	 * longitug 50<br>
	 */
	public void setBeneficiario1(String value){
		replaceRigth(value, 60, 109, ' ');
	}
	/**
	 * Porcentaje de que se le otorga al beneficiario 1 
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 5<br>
	 */
	public void setPorcentajeBeneficiario1(String value){
		replaceLeft(value.replace(".", "").replace(",", ""), 110, 114, '0');
	}
	/**
	 * Contador del beneficiario 1
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 2<br>
	 */
	public void setContadorBeneficiario1(String value){
		replaceRigth(value, 115, 116, ' ');
	}
	
	
	
	/**
	 * Nombre del beneficiario 2
	 * @param java.lang.String <br>
	 * alfanumerico<br>
	 * longitug 50<br>
	 */
	public void setBeneficiario2(String value){
		replaceRigth(value, 117, 166, ' ');
	}
	/**
	 * Porcentaje de que se le otorga al beneficiario 2
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 5<br>
	 */
	public void setPorcentajeBeneficiario2(String value){
		replaceLeft(value.replace(".", "").replace(",", ""), 167, 171, '0');
	}
	/**
	 * Contador del beneficiario 2
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 2<br>
	 */
	public void setContadorBeneficiario2(String value){
		replaceRigth(value, 172, 173, ' ');
	}
	
	
	
	/**
	 * Nombre del beneficiario 3
	 * @param java.lang.String <br>
	 * alfanumerico<br>
	 * longitug 50<br>
	 */
	public void setBeneficiario3(String value){
		replaceRigth(value, 174, 223, ' ');
	}
	/**
	 * Porcentaje de que se le otorga al beneficiario 3
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 5<br>
	 */
	public void setPorcentajeBeneficiario3(String value){
		replaceLeft(value.replace(".", "").replace(",", ""), 224, 228, '0');
	}
	/**
	 * Contador del beneficiario 3
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 2<br>
	 */
	public void setContadorBeneficiario3(String value){
		replaceRigth(value, 229, 230, ' ');
	}
	
	
	
	/**
	 * Nombre del beneficiario 4
	 * @param java.lang.String <br>
	 * alfanumerico<br>
	 * longitug 50<br>
	 */
	public void setBeneficiario4(String value){
		replaceRigth(value, 231, 280, ' ');
	}
	/**
	 * Porcentaje de que se le otorga al beneficiario 4
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 5<br>
	 */
	public void setPorcentajeBeneficiario4(String value){
		replaceLeft(value.replace(".", "").replace(",", ""), 281, 285, '0');
	}
	/**
	 * Contador del beneficiario 4
	 * @param java.lang.String <br>
	 * numerico<br>
	 * longitug 2<br>
	 */
	public void setContadorBeneficiario4(String value){
		replaceRigth(value, 286, 287, ' ');
	}
	/**
	 * Obtiene el valor de la entrada que es un arreglo de chars
	 * @return java.lang.String value of entrada array of char
	 */
	private String getEntrada(){
		StringBuffer buffer= new StringBuffer();
		for(char c:entrada){
			//if(c!='\u0000')
				buffer.append(c);
		}
		return buffer.toString().trim();
	}
	/**
	 * replaceRigth<br>
	 * ==============================================<br>
	 * Método que remplaza en el arreglo de c 
	 * @param java.lang.String: contiene el valor a remplazar  
	 * @param int: posicion inicial a sustituir
	 * @param fin: posicion final a sustituir
	 * @param char value: valor con el que se llenara <br> 
	 * el arrego en los espacios vacios de la derecha
	 */
	private void replaceRigth(String value,int inicio,int fin,char fill){
		int j=0;
		for(int i=inicio;i<=fin;i++,j++){
			try{
				this.entrada[i]=value.charAt(j);
			}catch(StringIndexOutOfBoundsException stringIndexOutOfBoundsException){
				this.entrada[i]=fill;
			}
		}
	}
	/**
	 * replaceLeft<br>
	 * ==============================================<br>
	 * Método que remplaza en el arreglo entrada en las posiciones de inicio a fin 
	 * @param java.lang.String: contiene el valor a remplazar  
	 * @param int: posicion inicial a sustituir
	 * @param fin: posicion final a sustituir
	 * @param char value: valor con el que se llenara <br> 
	 * el arrego en los espacios vacios de la izquierda
	 */
	private void replaceLeft(String value,int inicio,int fin,char fill){
		int j=0;
		int spaces=(fin-inicio)-(value.length()-1);
		for(int i=inicio;i<=fin;i++){
			try{
				if(spaces> 0){
					this.entrada[i]=fill;
					spaces--;
				}else{
					this.entrada[i]=value.charAt(j++);
				}
			}catch(StringIndexOutOfBoundsException stringIndexOutOfBoundsException){
				this.entrada[i]=fill;
			}
		}
	}
	
	public String getFuncion(){
		return getAttribute("FUNCION");
	}


	public String getReferencia(){
		return getAttribute("REFEREN");
	}


	public String getDatosEntrada(){
		return getAttribute("ENTRADA");
	}


	public String getCuentaAsociada(){
		return getAttribute("CTAASOC");
	}


	public String getCanal(){
		return getAttribute("CHANNEL");
	}


	public String getSalida(){
		return getAttribute("SALIDA");
	}
	
}
