package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class AlertsDataResponseTO implements Serializable{


	private static final long serialVersionUID = -8773292041255154178L;
	
	private Map<String, String> initialData;
	private Collection<AlertasTO> alertasTO;
	private DatosEliteTO DatosEliteTO;
	private Collection<AlertsAcountsTO> alertsAcounts;
	private Collection<AlertsCardsTO> alertsCards;
	private String sClaveConfirmacion;
	
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	private Map<String, String> initialCompCel;
	
	public Collection<AlertsCardsTO> getAlertsCards() {
		return alertsCards;
	}
	public void setAlertsCards(Collection<AlertsCardsTO> alertsCards) {
		this.alertsCards = alertsCards;
	}
	public Collection<AlertsAcountsTO> getAlertsAcounts() {
		return alertsAcounts;
	}
	public void setAlertsAcounts(Collection<AlertsAcountsTO> alertsAcounts) {
		this.alertsAcounts = alertsAcounts;
	}
	public Map<String, String> getInitialData() {
		return initialData;
	}
	public void setInitialData(Map<String, String> initialData) {
		this.initialData = initialData;
	}
	public Collection<AlertasTO> getAlertasTO() {
		return alertasTO;
	}
	public void setAlertasTO(Collection<AlertasTO> alertasTO) {
		this.alertasTO = alertasTO;
	}
	public DatosEliteTO getDatosEliteTO() {
		return DatosEliteTO;
	}
	public void setDatosEliteTO(DatosEliteTO datosEliteTO) {
		DatosEliteTO = datosEliteTO;
	}
	public String getSClaveConfirmacion() {
		return sClaveConfirmacion;
	}
	public void setSClaveConfirmacion(String claveConfirmacion) {
		sClaveConfirmacion = claveConfirmacion;
	}
	public Map<String, String> getInitialCompCel() {
		return initialCompCel;
	}
	public void setInitialCompCel(Map<String, String> initialCompCel) {
		this.initialCompCel = initialCompCel;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
}
