package com.bancoazteca.eservice.command.base.beans;

public class Catalogo_estatus_chequesTO {
	private String value;
	private String label;
	public Catalogo_estatus_chequesTO() {
	}
	public Catalogo_estatus_chequesTO(String value, String label) {
		this.label=label;
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
