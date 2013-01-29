package com.bancoazteca.eservice.command.reportos.consultaPlazoTasa;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosCacheTO;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaCacheTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class ConsultaPlazoTasaCommand extends CommandBase {
	private static final Logger log = Logger.getLogger(ConsultaPlazoTasaCommand.class);
	public Response execute(Session session) {
		Response response = new Response();
		
		ConsultaPlazoTasaForm form= (ConsultaPlazoTasaForm) getFormBean();
		
		try {
			ResourceFacadeSegundoSL facadeSegundoSL = getDelegateSegundo();
			
			log.info("*****************************************************");
			log.info("Actualización Reportos Plazo-Tasa");
			log.info("Plazo Inversion: "+form.getPlazo_inversion());
			log.info("Tasa Inversion: "+form.getTasa_inversion());
			log.info("*****************************************************");
			
			//<plazo_inversion value="7 dias,14 dias,21 dias,28 dias"/>
			//<tasa_inversion value="4.29%,4.29%,4.29%,4.29%"/>
			
			String plazo=form.getPlazo_inversion();
			String tasa=form.getTasa_inversion();
			
			String[] plazos=plazo.split(",");
			String[] tasas=tasa.split(",");
			
			PlazoTasaCacheTO plazoTasaCacheTO=new PlazoTasaCacheTO();
			
			Collection<PlazoTasaTO>plazoTasa=new ArrayList<PlazoTasaTO>();
			PlazoTasaTO plazoTasaTO=null;
			
			for (int i=0;i<plazos.length;i++) {
				plazos[i] = Formatter.removeSpacesLeftRight(plazos[i]);
				tasas[i] = Formatter.removeSpacesLeftRight(tasas[i]);
				String plazoTemp[]=plazos[i].split(" ");
				plazoTasaTO=new PlazoTasaTO();
				plazoTasaTO.setPlazo(Formatter.removeSpacesLeftRight(plazoTemp[0]));
				plazoTasaTO.setTasa(Formatter.removeSpacesLeftRight(tasas[i].replace("%","")));
				plazoTasaTO.setTipoPlazo(Formatter.removeSpacesLeftRight(plazoTemp[1]));
				
				plazoTasa.add(plazoTasaTO);
				
			}
			plazoTasaCacheTO.setPlazosTasas(plazoTasa);
			plazoTasaCacheTO.setFecha_operacion(form.getFecha_operacion());
			plazoTasaCacheTO.setHora_operacion(form.getHora_operacion());
			facadeSegundoSL.setDatosPlazoTasa(plazoTasaCacheTO);
			
			
		}catch(ServiceLocatorException e){
			e.printStackTrace();
		}
		return response;
	}
}
