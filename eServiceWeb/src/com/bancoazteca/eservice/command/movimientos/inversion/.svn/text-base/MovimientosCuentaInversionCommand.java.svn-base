package com.bancoazteca.eservice.command.movimientos.inversion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaInversionTO;
import com.bancoazteca.elite.beans.MovimientoInversionTO;
import com.bancoazteca.elite.beans.MovimientosCuentaInversionRequest;
import com.bancoazteca.elite.beans.MovimientosCuentasInversionTO;
import com.bancoazteca.elite.util.EscapeUtils;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Movimiento_inversionTO;
import com.bancoazteca.eservice.command.base.beans.Movimientos_cuentas_inversionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class MovimientosCuentaInversionCommand extends CommandBase {

	
	public Response ejecucion(Session session) throws Exception{
		Response response=new Response();
		
		MovimientosInversionForm form=(MovimientosInversionForm)getFormBean();
		
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Collection<CuentaInversionTO> colecciónInversiones = clienteTO.getInversiones();
		
		String cuentaSolicitada=form.getCuenta_inversion();
		
		CuentaInversionTO cuentaInversionSelecionada = getAccountsInversionPrdicate(colecciónInversiones, cuentaSolicitada);
		String producto=null;
		if(cuentaInversionSelecionada==null){
			response.setStatus(-1,"Lo sentimos, el número de cuenta es incorrecto.",null);
		}else{

				String index=cuentaInversionSelecionada.getIndice();
				producto = cuentaInversionSelecionada.getProducto();
				
				MovimientosCuentaInversionRequest movimientosCuentaInversionRequest=new MovimientosCuentaInversionRequest();
				movimientosCuentaInversionRequest.setIndiceCuenta(index);
				movimientosCuentaInversionRequest.setUsuario(clienteTO.getUserName());
				movimientosCuentaInversionRequest.setProducto(cuentaInversionSelecionada.getProducto());
				MovimientosCuentasInversionTO movimientosInversion = getDelegate().getMovimientosCuentasInversion(movimientosCuentaInversionRequest);
				String cuentaTemp=null;
				
				SimpleDateFormat dateFormatParse=new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormatGeneral=new SimpleDateFormat("dd MMM yyyy");
				
				
				cuentaSolicitada=cuentaInversionSelecionada.getNumero();
				Movimientos_cuentas_inversionTO movimientos_cuenta_inversionTO=null;
				for (CuentaInversionTO cuentaInversionTO : movimientosInversion.getInversiones()){
					if(cuentaInversionTO!=null){
						cuentaTemp=cuentaInversionTO.getNumeroCuenta();
						if(cuentaTemp.equals(cuentaSolicitada)){
							movimientos_cuenta_inversionTO=new Movimientos_cuentas_inversionTO();
							movimientos_cuenta_inversionTO.setCuenta_clabe(cuentaInversionTO.getClabe());
							movimientos_cuenta_inversionTO.setDias_faltantes(cuentaInversionTO.getDiasFaltantes());
							movimientos_cuenta_inversionTO.setDias_transcurridos(cuentaInversionTO.getDiasTranscurridos());
							movimientos_cuenta_inversionTO.setDisponible(cuentaInversionTO.getDisponible());
							
							Date fechaTemp=dateFormatParse.parse(cuentaInversionTO.getFechaInicioInversion());
							movimientos_cuenta_inversionTO.setFecha_inicio_inversion(dateFormatGeneral.format(fechaTemp));
							
							fechaTemp=dateFormatParse.parse(cuentaInversionTO.getFechaVencimiento());
							movimientos_cuenta_inversionTO.setFecha_vencimiento(dateFormatGeneral.format(fechaTemp));
							
							if(cuentaInversionTO.getInstruccion()!=null){
								movimientos_cuenta_inversionTO.setInstruccion(EscapeUtils.getStringFromHtml(cuentaInversionTO.getInstruccion()));
							}
							movimientos_cuenta_inversionTO.setInteres_bruto(cuentaInversionTO.getInteresBrutoGanar());
							movimientos_cuenta_inversionTO.setIntereses_generados_a_la_fecha(cuentaInversionTO.getInteresGeneradoSAFecha());
							movimientos_cuenta_inversionTO.setInteres_neto(cuentaInversionTO.getInteresNetoGanar());
							movimientos_cuenta_inversionTO.setMonto_invertido(cuentaInversionTO.getMontoInvertido());
							movimientos_cuenta_inversionTO.setTotal_cargos(cuentaInversionTO.getTotalCargos());
							movimientos_cuenta_inversionTO.setTotal_abonos(cuentaInversionTO.getTotalAbonos());
							Collection<MovimientoInversionTO> movimientos=cuentaInversionTO.getMovimientos();
							
							Collection<Movimiento_inversionTO> movimientosRetiro=new ArrayList<Movimiento_inversionTO>();
							Collection<Movimiento_inversionTO> movimientosDeposito=new ArrayList<Movimiento_inversionTO>();
							Collection<Movimiento_inversionTO> movimientosInteres=new ArrayList<Movimiento_inversionTO>();

							Collection<Movimiento_inversionTO> movColeccion=new ArrayList<Movimiento_inversionTO>();
							
							Movimiento_inversionTO movimiento=null;
							if(movimientos!=null){
								for (MovimientoInversionTO movimientoInversionTO : movimientos) {
									
									movimiento=new Movimiento_inversionTO();
									movimiento.setBalance(movimientoInversionTO.getBalance());
									movimiento.setConcepto(movimientoInversionTO.getConcepto());
									movimiento.setImporte(movimientoInversionTO.getImporte());
									movimiento.setNumero_movimiento(movimientoInversionTO.getNumero());
									movimiento.setTasa_bruta_anual(movimientoInversionTO.getTasaBrutaAnual());
									if(producto.equals("14")){
										Date date=new Date(movimientoInversionTO.getFecha());
										movimiento.setFecha(dateFormatGeneral.format(date));
									}else{
										movimiento.setFecha(dateFormatGeneral.format(dateFormatParse.parse(movimientoInversionTO.getFecha())));
									}
									
									
									
									if(producto.equals("07") && buscaMovimiento(movColeccion, movimiento)==null){
										movColeccion.add(movimiento);
									}else{
										String tipoMov=movimientoInversionTO.getTipoMovimiento();
										if(tipoMov!=null){
											if(tipoMov.equals("retiro")){
												movimientosRetiro.add(movimiento);
											}else if(tipoMov.equals("deposito")){
												movimientosDeposito.add(movimiento);
											}else{
												movimientosInteres.add(movimiento);
											}
										}
										
									}
									
								}
							}

							movimientos_cuenta_inversionTO.setMovimientos_ganare(movColeccion);
							
							movimientos_cuenta_inversionTO.setMovimientos_deposito(movimientosDeposito);
							movimientos_cuenta_inversionTO.setMovimientos_intereses(movimientosInteres);
							movimientos_cuenta_inversionTO.setMovimientos_retiro(movimientosRetiro);
							
							movimientos_cuenta_inversionTO.setNumero_cuenta(formatearCuenta(cuentaInversionTO.getNumeroCuenta()));
							movimientos_cuenta_inversionTO.setPlazo_inversion(cuentaInversionTO.getPlazoInversion());
							movimientos_cuenta_inversionTO.setRetenido(cuentaInversionTO.getRetenido());
							movimientos_cuenta_inversionTO.setTasa_bruta(cuentaInversionTO.getTasaBruta());
							movimientos_cuenta_inversionTO.setTasa_neta(cuentaInversionTO.getTasaNeta());
							movimientos_cuenta_inversionTO.setTipo_cuenta(cuentaInversionTO.getTipo());
							movimientos_cuenta_inversionTO.setUnidades_compradas(cuentaInversionTO.getUnidadesComp());
							movimientos_cuenta_inversionTO.setBalance(cuentaInversionTO.getBalance());
						}
					}
				}
				
				if(movimientos_cuenta_inversionTO==null){
					response.setStatus(-1,"Lo sentimos, la cuenta no contiene movimientos.",null);
				}else{
					response.addAttribute(movimientos_cuenta_inversionTO);
				}
			}
	
		return response;
	}
	
	
	protected Movimiento_inversionTO buscaMovimiento(Collection<Movimiento_inversionTO> collectionCuentas, Movimiento_inversionTO param){
		
		MovimientoPredicate movPredicate = new MovimientoPredicate();
		movPredicate.setMovimiento(param);
		
		Movimiento_inversionTO movimiento = (Movimiento_inversionTO) CollectionUtils.find(collectionCuentas, movPredicate);
		return movimiento;
	}

	private class MovimientoPredicate implements Predicate {
		private Movimiento_inversionTO movimiento;

		public boolean evaluate(Object obj) {
			Movimiento_inversionTO movimiento = (Movimiento_inversionTO) obj;
			
			if (movimiento.equals(this.movimiento)){
				return true;
			}else{
				return false;
			}
		}

		public Movimiento_inversionTO getMovimiento() {
			return movimiento;
		}

		public void setMovimiento(Movimiento_inversionTO movimiento) {
			this.movimiento = movimiento;
		}
		
	}
	
}