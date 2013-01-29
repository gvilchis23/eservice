package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class EstadoCuentaInteraccionPeriodosTO {
	private Collection<String> periodos;

	public void setPeriodos(Collection<String> periodos) {
		this.periodos = periodos;
	}

	public Collection<String> getPeriodos() {
		return periodos;
	}		
}
