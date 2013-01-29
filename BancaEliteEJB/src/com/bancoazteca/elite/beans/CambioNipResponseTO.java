package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;


public class CambioNipResponseTO implements Serializable {

	private static final long serialVersionUID = -3442054262783690050L;
	
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String mensajeTransaccion;
	private Collection<MediosPagoTO> tarjetasMediosPago;
	private Collection<CancelacionElementTO> tarjetasCambioNip;
	
	public Collection<MediosPagoTO> getTarjetasMediosPago() {
		return tarjetasMediosPago;
	}
	public void setTarjetasMediosPago(Collection<MediosPagoTO> tarjetasMediosPago) {
		this.tarjetasMediosPago = tarjetasMediosPago;
	}
	public Collection<CancelacionElementTO> getTarjetasCambioNip() {
		return tarjetasCambioNip;
	}
	public void setTarjetasCambioNip(
			Collection<CancelacionElementTO> tarjetasCambioNip) {
		this.tarjetasCambioNip = tarjetasCambioNip;
	}
	public String getMensajeTransaccion() {
		return mensajeTransaccion;
	}
	public void setMensajeTransaccion(String mensajeTransaccion) {
		this.mensajeTransaccion = mensajeTransaccion;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	
	
	
}
