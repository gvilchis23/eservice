package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class HuellaDigitalTO implements Serializable{
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 7936834688191393132L;
	
	
	private String user;
	//Fields
	private String huella;
	private String biometrico;
	private int mano;
	private int dedo;
	
	public static final int MANO_IZQUIERDA = 1;	
	public static final int MANO_DERECHA = 2;
	
	public static final int DEDO_PULGAR = 1;
	public static final int DEDO_INDICE = 2;
	public static final int DEDO_MEDIO = 3;
	public static final int DEDO_ANULAR = 4;
	public static final int DEDO_MENIQUE = 5;
	
	/**
	 * La igualdad de la huella, se valida a trav&eacute;s de su biom&eacute;trico.
	 */
	public boolean equals(Object obj) {
		boolean retVal = false;
		if (obj instanceof HuellaDigitalTO) {
			HuellaDigitalTO huellaDigitalTO = (HuellaDigitalTO) obj;
			retVal = huellaDigitalTO.biometrico.equals(this.biometrico);
		}
		return retVal;
	}
	
	/**
	 * Indica si una huella esta localizada, es decir, si se conoce
	 * de que mano y dedo es.
	 */
	public boolean isLocalizada(){
		return mano != 0 && dedo != 0;
	}
	
	public String getNombreDedo(){
		String nombreDedo;
		if(dedo != 0){
			switch(dedo){
				case DEDO_PULGAR:
					nombreDedo = "PULGAR";
					break;
				case DEDO_INDICE:
					nombreDedo = "INDICE";
					break;
				case DEDO_MEDIO:
					nombreDedo = "MEDIO";
					break;
				case DEDO_ANULAR:
					nombreDedo = "ANULAR";
					break;
				case DEDO_MENIQUE:
					nombreDedo = "MEÑIQUE";
					break;
				default:
					nombreDedo = "";
			}		
		}else{
			nombreDedo = "";
		}
		return nombreDedo;
	}

	public String getNombreMano(){
		String nombreMano;
		if(mano != 0){
			switch(mano){
				case MANO_IZQUIERDA:
					nombreMano = "IZQUIERDA";
					break;
				case MANO_DERECHA:
					nombreMano = "DERECHA";
					break;
				default:
					nombreMano = "";
			}		
		}else{
			nombreMano = "";
		}
		return nombreMano;
	}

	/**
	 * @return the huella
	 */
	public String getHuella() {
		return huella;
	}

	/**
	 * @param huella the huella to set
	 */
	public void setHuella(String huella) {
		this.huella = huella;
	}

	/**
	 * @return the biometrico
	 */
	public String getBiometrico() {
		return biometrico;
	}

	/**
	 * @param biometrico the biometrico to set
	 */
	public void setBiometrico(String biometrico) {
		this.biometrico = biometrico;
	}

	/**
	 * @return the mano
	 */
	public int getMano() {
		return mano;
	}

	/**
	 * @param mano the mano to set
	 */
	public void setMano(int mano) {
		this.mano = mano;
	}

	/**
	 * @return the dedo
	 */
	public int getDedo() {
		return dedo;
	}

	/**
	 * @param dedo the dedo to set
	 */
	public void setDedo(int dedo) {
		this.dedo = dedo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
