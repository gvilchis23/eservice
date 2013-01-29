package com.bancoazteca.eservice.command.localizasucursal;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.WsInformacionTiendas.Estado;
import com.bancoazteca.elite.WsInformacionTiendas.Municipio;
import com.bancoazteca.elite.WsInformacionTiendas.Tienda;
import com.bancoazteca.elite.beans.LocalizaSucursalRequestTO;
import com.bancoazteca.elite.beans.LocalizaSucursalResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.SessionManagerUtil;
import com.bancoazteca.eservice.command.base.beans.Entidad_FederativaTO;
import com.bancoazteca.eservice.command.base.beans.Localizar_Sucursal_TO;
import com.bancoazteca.eservice.command.base.beans.MunicipioTO;
import com.bancoazteca.eservice.command.base.beans.TiendaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class LocalizaSucursalCommand extends CommandBase {
	
	private static final Logger log= Logger.getLogger(LocalizaSucursalCommand.class);
	
	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();
		HashMap<String, String> errors = null;
		try{
			LocalizaSucursalForm forma = (LocalizaSucursalForm)getFormBean();
			if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_ESTADOS")){
				localizar_Sucursal_TO = obtener_estados(forma);
			}else if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_MUNICIPIOS")){
				localizar_Sucursal_TO = obtener_municipios(forma);
			}else if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA")){
				localizar_Sucursal_TO = obtener_tienda(forma);
			}else if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA_CP")){
				localizar_Sucursal_TO = obtener_tienda_cp(forma);
			}else if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDA_PALABRA")){
				localizar_Sucursal_TO = obtener_tienda_palabra(forma);
			}else if(forma.getTipo_servicio().equalsIgnoreCase("OBTENER_TIENDAS")){
				localizar_Sucursal_TO = obtener_tiendas(forma);
			}else{
//				regresamos mensaje de error en caso de que el tipo de operacion no coincida con el de la cuenta
				errors = new HashMap<String, String>();
				errors.put("tipo_servicio","El tipo de servicio para esta cuenta es incorrecto, verifique su petición e inténtelo nuevamente.");
				returnErrorMap(response, errors);
			}
			response.addAttribute(localizar_Sucursal_TO);
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		return response;
	}
	
//	obtenerEstados
	public Localizar_Sucursal_TO obtener_estados(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtienen los municipios de cada entidad federativa");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();
		
		int idPais = Integer.parseInt(forma.getId_pais());
		localizaSucursalRequestTO.setIdPais(idPais);
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneEstados(localizaSucursalRequestTO);
		
		Estado []estados = localizaSucursalResponseTO.getEstados();
		Entidad_FederativaTO entidad_FederativaTO = null;
		Collection<Entidad_FederativaTO> coleccion_entidades_federativas = new ArrayList<Entidad_FederativaTO>();
		for(int i=0; i<estados.length; i++){
			Estado estado = estados[i];
			entidad_FederativaTO = new Entidad_FederativaTO();
			entidad_FederativaTO.setIdentificador_entidad_federativa(""+estado.getIdEstado());
			entidad_FederativaTO.setNombre_entidad_federativa(estado.getNombreEstado().toUpperCase());
			coleccion_entidades_federativas.add(entidad_FederativaTO);
		}
		localizar_Sucursal_TO.setColeccion_entidades_federativas(coleccion_entidades_federativas);
		return localizar_Sucursal_TO;
	}
	
//	obtenerMunicipios
	public Localizar_Sucursal_TO obtener_municipios(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtienen los municipios de cada entidad federativa");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();

		int idPais = Integer.parseInt(forma.getId_pais());
		int idEstado = Integer.parseInt(forma.getId_estado());
		localizaSucursalRequestTO.setIdPais(idPais);
		localizaSucursalRequestTO.setIdEstado(idEstado);
		localizaSucursalRequestTO.setCanales(forma.getCanales());
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneMunicipios(localizaSucursalRequestTO);
		
		Municipio []municipios = localizaSucursalResponseTO.getMunicipios();
		MunicipioTO municipioTO = null;
		Collection<MunicipioTO> coleccion_municipios = new ArrayList<MunicipioTO>();
		for(int i=0; i<municipios.length; i++){
			Municipio municipio = municipios[i];
			municipioTO = new MunicipioTO();
			municipioTO.setIdentificador_entidad_federativa(""+municipio.getIdEstado());
			municipioTO.setNombre_entidad_federativa(municipio.getNombreEstado().toUpperCase());
			municipioTO.setIdentificador_municipio(""+municipio.getIdMunicipio());
			municipioTO.setNombre_municipio(municipio.getNombreMunicipio().toUpperCase());
			coleccion_municipios.add(municipioTO);
		}
		localizar_Sucursal_TO.setColeccion_municipios(coleccion_municipios);
		return localizar_Sucursal_TO;
	}
	
//	ObtenerInformacionTienda
	public Localizar_Sucursal_TO obtener_tienda(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtien info de una tienda");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();

		int idPais = Integer.parseInt(forma.getId_pais());
		int numeroTienda = Integer.parseInt(forma.getNumero_tienda());
		localizaSucursalRequestTO.setIdPais(idPais);
		localizaSucursalRequestTO.setNumeroTienda(numeroTienda);
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneTienda(localizaSucursalRequestTO);
		
		Tienda []tienda = localizaSucursalResponseTO.getTienda();
		TiendaTO tiendaTO = new TiendaTO();
		
		if(tienda!=null && tienda.length>0){				
			tiendaTO.setIdentificador_entidad_federativa(""+tienda[0].getIdEstado());
			tiendaTO.setNombre_entidad_federativa(tienda[0].getNombreEstado());
			tiendaTO.setIdentificador_municipio(""+tienda[0].getIdMunicipio());
			tiendaTO.setNombre_municipio(tienda[0].getNombreMunicipio());
			tiendaTO.setIdentificador_pais(""+tienda[0].getIdPais());
			tiendaTO.setIdentificador_canal(""+tienda[0].getIdCanal());
			tiendaTO.setNombre_canal(tienda[0].getNombreCanal());
			tiendaTO.setIdentificador_tienda(""+tienda[0].getIdTienda());
			tiendaTO.setNombre_tienda(tienda[0].getNombreTienda());
			tiendaTO.setCalle_tienda(tienda[0].getCalle());
			tiendaTO.setColonia_tienda(tienda[0].getColonia());
			tiendaTO.setPoblacion_tienda(tienda[0].getPoblacion());
			tiendaTO.setCodigoPostal_tienda(tienda[0].getCodigoPostal());
			tiendaTO.setLatitud_tienda(tienda[0].getLatitud());
			tiendaTO.setLongitud_tienda(tienda[0].getLongitud());
			tiendaTO.setTelefonos_tienda(tienda[0].getTelefono());
		}
		localizar_Sucursal_TO.setTienda(tiendaTO);
		return localizar_Sucursal_TO;
	}

//	ObtenerInformacionTiendaPorCp
	public Localizar_Sucursal_TO obtener_tienda_cp(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtien info de una tienda a traves de su codigo postal");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();

		int idPais = Integer.parseInt(forma.getId_pais());
		localizaSucursalRequestTO.setIdPais(idPais);
		localizaSucursalRequestTO.setCodigoPostal(forma.getCodigo_postal());
		localizaSucursalRequestTO.setCanales("1,2,3,4,85");
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneTiendaCP(localizaSucursalRequestTO);
		
		Tienda []tiendas = localizaSucursalResponseTO.getTienda();
		TiendaTO tiendaTO = null;			
		Collection<TiendaTO> coleccion_tiendas = new ArrayList<TiendaTO>();
		for(int i=0; i<tiendas.length; i++){
			Tienda tienda = tiendas[i];
			tiendaTO = new TiendaTO();
			tiendaTO.setIdentificador_entidad_federativa(""+tienda.getIdEstado());
			tiendaTO.setNombre_entidad_federativa(tienda.getNombreEstado());
			tiendaTO.setIdentificador_municipio(""+tienda.getIdMunicipio());
			tiendaTO.setNombre_municipio(tienda.getNombreMunicipio());
			tiendaTO.setIdentificador_pais(""+tienda.getIdPais());
			tiendaTO.setIdentificador_canal(""+tienda.getIdCanal());
			tiendaTO.setNombre_canal(tienda.getNombreCanal());
			tiendaTO.setIdentificador_tienda(""+tienda.getIdTienda());
			tiendaTO.setNombre_tienda(tienda.getNombreTienda());
			tiendaTO.setCalle_tienda(tienda.getCalle());
			tiendaTO.setColonia_tienda(tienda.getColonia());
			tiendaTO.setPoblacion_tienda(tienda.getPoblacion());
			tiendaTO.setCodigoPostal_tienda(tienda.getCodigoPostal());
			tiendaTO.setLatitud_tienda(tienda.getLatitud());
			tiendaTO.setLongitud_tienda(tienda.getLongitud());
			tiendaTO.setTelefonos_tienda(tienda.getTelefono());
			coleccion_tiendas.add(tiendaTO);
		}
		localizar_Sucursal_TO.setColeccion_tiendas(coleccion_tiendas);
		return localizar_Sucursal_TO;
	}

//	ObtenerInformacionTiendaPorPalabra
	public Localizar_Sucursal_TO obtener_tienda_palabra(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtien info de una tienda a traves de una palabra");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();

		int idPais = Integer.parseInt(forma.getId_pais());
		localizaSucursalRequestTO.setIdPais(idPais);
		localizaSucursalRequestTO.setPalabra(forma.getConsulta_palabra());
		localizaSucursalRequestTO.setCanales("1,2,3,4,85");
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneTiendaPalabra(localizaSucursalRequestTO);
		
		Tienda []tiendas = localizaSucursalResponseTO.getTienda();
		TiendaTO tiendaTO = null;			
		Collection<TiendaTO> coleccion_tiendas = new ArrayList<TiendaTO>();
		for(int i=0; i<tiendas.length; i++){
			Tienda tienda = tiendas[i];
			tiendaTO = new TiendaTO();
			tiendaTO.setIdentificador_entidad_federativa(""+tienda.getIdEstado());
			tiendaTO.setNombre_entidad_federativa(tienda.getNombreEstado());
			tiendaTO.setIdentificador_municipio(""+tienda.getIdMunicipio());
			tiendaTO.setNombre_municipio(tienda.getNombreMunicipio());
			tiendaTO.setIdentificador_pais(""+tienda.getIdPais());
			tiendaTO.setIdentificador_canal(""+tienda.getIdCanal());
			tiendaTO.setNombre_canal(tienda.getNombreCanal());
			tiendaTO.setIdentificador_tienda(""+tienda.getIdTienda());
			tiendaTO.setNombre_tienda(tienda.getNombreTienda());
			tiendaTO.setCalle_tienda(tienda.getCalle());
			tiendaTO.setColonia_tienda(tienda.getColonia());
			tiendaTO.setPoblacion_tienda(tienda.getPoblacion());
			tiendaTO.setCodigoPostal_tienda(tienda.getCodigoPostal());
			tiendaTO.setLatitud_tienda(tienda.getLatitud());
			tiendaTO.setLongitud_tienda(tienda.getLongitud());
			tiendaTO.setTelefonos_tienda(tienda.getTelefono());
			coleccion_tiendas.add(tiendaTO);
		}
		localizar_Sucursal_TO.setColeccion_tiendas(coleccion_tiendas);		
		return localizar_Sucursal_TO;
	}

//  ObtenerInformacionTiendas
	public Localizar_Sucursal_TO obtener_tiendas(LocalizaSucursalForm forma) throws Exception{
		log.info("Consulta en la cual se obtien info de un conjunto de tiendas");
		LocalizaSucursalRequestTO localizaSucursalRequestTO = new LocalizaSucursalRequestTO();
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();
		Localizar_Sucursal_TO localizar_Sucursal_TO = new Localizar_Sucursal_TO();

		int idPais = Integer.parseInt(forma.getId_pais());
		int idEstado = Integer.parseInt(forma.getId_estado());
		int idMunicipio = Integer.parseInt(forma.getId_municipio());
		localizaSucursalRequestTO.setIdPais(idPais);
		localizaSucursalRequestTO.setIdEstado(idEstado);
		localizaSucursalRequestTO.setIdMunicipio(idMunicipio);
		localizaSucursalRequestTO.setCanales("1,2,3,4,85");
		
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		localizaSucursalResponseTO = resourceFacadeSL.getObtieneTiendas(localizaSucursalRequestTO);
		
		Tienda []tiendas = localizaSucursalResponseTO.getTienda();
		TiendaTO tiendaTO = null;			
		Collection<TiendaTO> coleccion_tiendas = new ArrayList<TiendaTO>();
		for(int i=0; i<tiendas.length; i++){
			Tienda tienda = tiendas[i];
			tiendaTO = new TiendaTO();
			tiendaTO.setIdentificador_entidad_federativa(""+tienda.getIdEstado());
			tiendaTO.setNombre_entidad_federativa(tienda.getNombreEstado());
			tiendaTO.setIdentificador_municipio(""+tienda.getIdMunicipio());
			tiendaTO.setNombre_municipio(tienda.getNombreMunicipio());
			tiendaTO.setIdentificador_pais(""+tienda.getIdPais());
			tiendaTO.setIdentificador_canal(""+tienda.getIdCanal());
			tiendaTO.setNombre_canal(tienda.getNombreCanal());
			tiendaTO.setIdentificador_tienda(""+tienda.getIdTienda());
			tiendaTO.setNombre_tienda(tienda.getNombreTienda());
			tiendaTO.setCalle_tienda(tienda.getCalle());
			tiendaTO.setColonia_tienda(tienda.getColonia());
			tiendaTO.setPoblacion_tienda(tienda.getPoblacion());
			tiendaTO.setCodigoPostal_tienda(tienda.getCodigoPostal());
			tiendaTO.setLatitud_tienda(tienda.getLatitud());
			tiendaTO.setLongitud_tienda(tienda.getLongitud());
			tiendaTO.setTelefonos_tienda(tienda.getTelefono());
			coleccion_tiendas.add(tiendaTO);
		}
		localizar_Sucursal_TO.setColeccion_tiendas(coleccion_tiendas);		
		return localizar_Sucursal_TO;
	}
}
