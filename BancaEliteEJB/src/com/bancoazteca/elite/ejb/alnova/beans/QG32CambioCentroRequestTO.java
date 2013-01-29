package com.bancoazteca.elite.ejb.alnova.beans;

public class QG32CambioCentroRequestTO extends EjbAlnovaRequestTO{
	
  private static final long serialVersionUID = 1L;
	
	private static String[] keys={"ENTCEN","FECCAM","PLANIF","SUSTPOR","TERMIN"};

	public QG32CambioCentroRequestTO() {
		super("QG32", keys);
	}
	
	public void setEntcen(String user){
		super.addParameter("ENTCEN",user);
	}
	
	public void setFeccam(String user){
		super.addParameter("FECCAM",user);
	}
	public void setPlanif(String user){
		super.addParameter("PLANIF",user);
	}
	
	public void setSustpor(String user){
		super.addParameter("SUSTPOR",user);
	}
	public void setTermin(String user){
		super.addParameter("TERMIN",user);
	}
	

}
