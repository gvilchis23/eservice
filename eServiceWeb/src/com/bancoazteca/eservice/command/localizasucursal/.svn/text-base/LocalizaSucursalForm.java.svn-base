package com.bancoazteca.eservice.command.localizasucursal;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.eservice.validator.MessageErrors;

public class LocalizaSucursalForm extends FormBean {
	
	public String id_pais;	
	public String id_estado;	
	public String id_municipio;
	public String canales;
	public String numero_tienda;
	public String codigo_postal;
	public String consulta_palabra;
	public String tipo_servicio;

	public MessageErrors validate() {
		MessageErrors error = new MessageErrors();	
		String comando = getComando();
		
		
		if(comando.equalsIgnoreCase("EJECUCION")){
			if(Validator.isEmptyData(getId_pais())){
				error.add("id_pais", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_VACIO);
			}else if(!Validator.checkNumeric(getId_pais())){
				error.add("id_pais", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_ERROR);
			}else if(getId_pais().length() > 2){
				error.add("id_pais", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_ERROR);
			}else if(Validator.isEmptyData(getTipo_servicio())){
				error.add("tipo_servicio", Errors.VALIDATOR_LOCALIZACION_TIPO_SERVICIO_VACIO);
			}else{
				if(getTipo_servicio().equalsIgnoreCase("OBTENER_MUNICIPIOS")){
					if(Validator.isEmptyData(getId_estado())){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_VACIO);
					}else if(!Validator.checkNumeric(getId_estado())){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_ERROR);
					}else if(getId_estado().length()>3){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_ERROR);
					}
				}else if(getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA")){
					if(Validator.isEmptyData(getNumero_tienda())){
						error.add("numero_tienda", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_NUMEROTIENDA_VACIO);
					}else if(!Validator.checkNumeric(getNumero_tienda())){
						error.add("numero_tienda", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_ERROR);
					}
				}else if(getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA_CP")){
					if(Validator.isEmptyData(getCodigo_postal())){
						error.add("codigo_postal", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_CODIGOPOSTAL_VACIO);
					}else if(!Validator.checkNumeric(getCodigo_postal())){
						error.add("codigo_postal", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_CODIGOPOSTAL_ERROR);
					}else if(getCodigo_postal().length() != 5 ){
						error.add("codigo_postal", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_CODIGOPOSTAL_ERROR);
					}
				}else if(getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA_PALABRA")){
					if(Validator.isEmptyData(getConsulta_palabra())){
						error.add("consulta_palabra", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_PALABRA_VACIO);
					}
				}else if(getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDAS")){
					if(Validator.isEmptyData(getId_estado())){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_VACIO);
					}else if(!Validator.checkNumeric(getId_estado())){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_ERROR);
					}else if(getId_estado().length() > 2){
						error.add("id_estado", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_ERROR);
					}
					if(Validator.isEmptyData(getId_municipio())){
						error.add("id_municipio", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDMUNICIPIO_VACIO);
					}else if(!Validator.checkNumeric(getId_municipio())){
						error.add("id_municipio", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDMUNICIPIO_ERROR);
					}else if(getId_municipio().length() > 8){
						error.add("id_municipio", Errors.VALIDATOR_LOCALIZACION_SUCURSAL_IDMUNICIPIO_ERROR);
					}
				}
			}
		}
		return error;
	}

	public String getId_pais() {
		return id_pais;
	}
	public void setId_pais(String id_pais) {
		this.id_pais = id_pais;
	}
	public String getId_estado() {
		return id_estado;
	}
	public void setId_estado(String id_estado) {
		this.id_estado = id_estado;
	}
	public String getCanales() {
		return canales;
	}
	public void setCanales(String canales) {
		this.canales = canales;
	}
	public String getNumero_tienda() {
		return numero_tienda;
	}
	public void setNumero_tienda(String numero_tienda) {
		this.numero_tienda = numero_tienda;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getConsulta_palabra() {
		return consulta_palabra;
	}

	public void setConsulta_palabra(String consulta_palabra) {
		this.consulta_palabra = consulta_palabra;
	}

	public String getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(String id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getTipo_servicio() {
		return tipo_servicio;
	}

	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}
}
