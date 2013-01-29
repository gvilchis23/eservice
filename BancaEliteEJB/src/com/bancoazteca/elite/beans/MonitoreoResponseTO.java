package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

import com.bancoazteca.elite.monitoreo.db.dao.ConsultaMonitoreoTO;

public class MonitoreoResponseTO implements Serializable{
	
	private static final long serialVersionUID = 6868251671513530570L;
	
	private Collection<ConsultaMonitoreoTO> resultadoConsulta;

	public Collection<ConsultaMonitoreoTO> getResultadoConsulta() {
		return resultadoConsulta;
	}

	public void setResultadoConsulta(Collection<ConsultaMonitoreoTO> resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}
		
}
