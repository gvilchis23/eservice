package com.bancoazteca.eservice.command.chequera.roboextravio;

import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class RoboExtravioForm extends FormBean {

	private String tipo_solicitud;
	private String numero_cuenta;
	private String cheque_inicial;
	private String cheque_final;
	private String descripcion_chequera;
	private String tipo_cancelacion;
	private String fecha_solicitud;
	private String motivo_reporte;
	private String tipo_reporte;
	private String folios_cheques_boquear;
	private String estado_chequera;
	private String confirmacion_bloqueo;
		
	public String getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getCheque_inicial() {
		return cheque_inicial;
	}
	public void setCheque_inicial(String cheque_inicial) {
		this.cheque_inicial = cheque_inicial;
	}
	public String getCheque_final() {
		return cheque_final;
	}
	public void setCheque_final(String cheque_final) {
		this.cheque_final = cheque_final;
	}
	public String getDescripcion_chequera() {
		return descripcion_chequera;
	}
	public void setDescripcion_chequera(String descripcion_chequera) {
		this.descripcion_chequera = descripcion_chequera;
	}
	public String getTipo_cancelacion() {
		return tipo_cancelacion;
	}
	public void setTipo_cancelacion(String tipo_cancelacion) {
		this.tipo_cancelacion = tipo_cancelacion;
	}
	public String getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(String fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	public String getMotivo_reporte() {
		return motivo_reporte;
	}
	public void setMotivo_reporte(String motivo_reporte) {
		this.motivo_reporte = motivo_reporte;
	}
	public String getTipo_reporte() {
		return tipo_reporte;
	}
	public void setTipo_reporte(String tipo_reporte) {
		this.tipo_reporte = tipo_reporte;
	}
	public String getNumero_cheques_boquear() {
		return folios_cheques_boquear;
	}
	public void setNumero_cheques_boquear(String folios_cheques_boquear) {
		this.folios_cheques_boquear = folios_cheques_boquear;
	}
	public String getEstado_chequera() {
		return estado_chequera;
	}
	public void setEstado_chequera(String estado_chequera) {
		this.estado_chequera = estado_chequera;
	}
	public String getConfirmacion_bloqueo() {
		return confirmacion_bloqueo;
	}
	public void setConfirmacion_bloqueo(String confirmacion_bloqueo) {
		this.confirmacion_bloqueo = confirmacion_bloqueo;
	}
	
		
	public MessageErrors validate() {
		MessageErrors error = new MessageErrors();

		if (getComando().equalsIgnoreCase("VALIDACION")) {
			if (Validator.isEmptyData(tipo_reporte)) {
				error.add("tipo_reporte", EXTRAVIO_CHEQUES_TIPO_VALIDACION); 
			}
			try {
				ChequesEnum.TipoSolicitudEnum tipoSolicitud = ChequesEnum.TipoSolicitudEnum.valueOf( tipo_cancelacion.toUpperCase() );
				switch (tipoSolicitud) {
				case CHEQUE:
				case CHEQUERAS:
					break;

				default:
					error.add("tipo_validacion", "Seleccione opcion correcta");
				}
				if(Validator.isEmptyData(motivo_reporte)){
					if( !"Robo".equalsIgnoreCase( motivo_reporte ) && !"Extravio".equalsIgnoreCase( motivo_reporte ) )
						error.add( "motivo_reporte", "Motivo incorrecto" );
				}
			} catch (IllegalArgumentException e) {
				error.add("tipo_validacion", "Seleccione opcion correcta");
			}

		} else if (getComando().equalsIgnoreCase("EJECUCION")) {
			if(!confirmacion_bloqueo.equalsIgnoreCase("si")&& !confirmacion_bloqueo.equalsIgnoreCase("no")){
				
				error.add("tipo_validacion", "Selecione opcion correcta");
			}
				
				
		}
		return error;
	}
	
}
