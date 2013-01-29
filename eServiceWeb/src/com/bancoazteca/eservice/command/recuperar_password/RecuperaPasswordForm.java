package com.bancoazteca.eservice.command.recuperar_password;

//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;

//import org.apache.log4j.Logger;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class RecuperaPasswordForm extends FormBean {
	
//	private static final Logger log = Logger.getLogger(RecuperaPasswordForm.class);
	
	private String tarjeta_cuenta;
	private Cipher nip;
	private Cipher confirmacion_nip;
	private String dia_nacimiento;
	private String mes_nacimiento;
	private String anio_nacimiento;
	private String fecha_nacimiento;
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	private Cipher nuevo_password;
	private Cipher confirmacion_nuevo_password;
	private String correo_electronico;
	private String numero_celular;
	private String compania_celular;
	

	public MessageErrors validate() {
		MessageErrors error = new MessageErrors();	
//		if(tarjeta_cuenta == null || tarjeta_cuenta.trim().length() == 0){
//			error.add("tarjeta_cuenta", TARJETA_CUENTA_ERROR);
//		}
//		if(nip == null || nip.toString().length() == 0){
//			error.add("nip", NIP_ERROR);
//		}
//		
//		if(fecha_nacimiento != null ){
//
//			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
//	        try {
//	        	Date fecha_nac = new Date();
//				fecha_nac = formatoFecha.parse(fecha_nacimiento);
//		        String resultado = formatoFecha.format(fecha_nac);
//		        dia_nacimiento = resultado.substring(0, 2);
//		        String cero = resultado.substring(3, 4);
//		        String mesObtenido = "";
//		        if(cero.equalsIgnoreCase("0")){
//		        	mesObtenido = resultado.substring(4, 5);
//		        }else{
//		        	mesObtenido = resultado.substring(3, 5);
//		        }		    
//		        int mes = Integer.parseInt(mesObtenido);
//		        mes_nacimiento = String.valueOf(mes - 1);
//		        anio_nacimiento = resultado.substring(6, 10);
//				log.info("dia de nacimiento:"+ dia_nacimiento);
//				log.info("mes de nacimiento:"+ mes_nacimiento);
//				log.info("anio de nacimiento:"+ anio_nacimiento);
//			} catch (Exception e) {
//				e.getMessage();
//			}
//
//		}
//		
//		if(dia_nacimiento == null || dia_nacimiento.trim().length() == 0
//				|| dia_nacimiento.trim().length() > 2){
//			error.add("dia_nacimiento", DIA_ERROR);
//		}
//		if(mes_nacimiento == null || mes_nacimiento.trim().length() == 0
//				|| mes_nacimiento.trim().length() > 2){
//			error.add("mes_nacimiento", MES_NACIMIENTO_ERROR);
//		}
//		
//		if(anio_nacimiento == null || anio_nacimiento.trim().length() == 0 
//				|| anio_nacimiento.trim().length() < 4 ){
//			error.add("anio_nacimiento", ANIO_NACIMIENTO_ERROR);
//		}
//		
//		if(nombres == null || nombres.trim().length() == 0){
//			error.add("nombres", NOMBRES_ERROR);
//		}
//		if(apellido_paterno == null || apellido_paterno.trim().length() == 0){
//			error.add("apellido_paterno", APELLIDO_PATERNO_ERROR);
//		}
//		if(apellido_materno == null || apellido_materno.trim().length() == 0){
//			error.add("apellido_materno", APELLIDO_MATERNO_ERROR);
//		}
//		if(nuevo_password == null || nuevo_password.toString().length() == 0){
//			error.add("new_password", NEW_PASSWORD_ERROR);
//		}
		
		return error;
	}

	
	public String getTarjeta_cuenta() {
		return tarjeta_cuenta;
	}	
	public void setTarjeta_cuenta(String tarjeta_cuenta) {
		this.tarjeta_cuenta = tarjeta_cuenta;
	}
	public String getDia_nacimiento() {
		return dia_nacimiento;
	}
	public void setDia_nacimiento(String dia_nacimiento) {
		this.dia_nacimiento = dia_nacimiento;
	}
	public String getMes_nacimiento() {
		return mes_nacimiento;
	}
	public void setMes_nacimiento(String mes_nacimiento) {
		this.mes_nacimiento = mes_nacimiento;
	}
	public String getAnio_nacimiento() {
		return anio_nacimiento;
	}
	public void setAnio_nacimiento(String anio_nacimiento) {
		this.anio_nacimiento = anio_nacimiento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Cipher getNip() {
		return nip;
	}
	public void setNip(Cipher nip) {
		this.nip = nip;
	}

	public Cipher getNuevo_password() {
		return nuevo_password;
	}


	public void setNuevo_password(Cipher nuevo_password) {
		this.nuevo_password = nuevo_password;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}


	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}


	public String getNumero_celular() {
		return numero_celular;
	}


	public void setNumero_celular(String numero_celular) {
		this.numero_celular = numero_celular;
	}


	public String getCompania_celular() {
		return compania_celular;
	}


	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}


	public Cipher getConfirmacion_nip() {
		return confirmacion_nip;
	}


	public void setConfirmacion_nip(Cipher confirmacion_nip) {
		this.confirmacion_nip = confirmacion_nip;
	}


	public Cipher getConfirmacion_nuevo_password() {
		return confirmacion_nuevo_password;
	}


	public void setConfirmacion_nuevo_password(Cipher confirmacion_nuevo_password) {
		this.confirmacion_nuevo_password = confirmacion_nuevo_password;
	}

}
