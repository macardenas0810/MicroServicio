package com.neoris.cuentasmovimientos.domain.usercase;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neoris.cuentasmovimientos.domain.model.CuentaModel;
import com.neoris.cuentasmovimientos.domain.model.InformeCuentasModel;
import com.neoris.cuentasmovimientos.domain.model.MovimientoModel;
import com.neoris.cuentasmovimientos.domain.model.gateway.MovimientoGateway;
import com.neoris.cuentasmovimientos.domain.model.gateway.CuentaGateway;
import com.neoris.cuentasmovimientos.infraestructure.util.ObtenerRespuesta;
import com.neoris.cuentasmovimientos.infraestructure.util.Validaciones;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MovimientoUseCase {
	
	private final MovimientoGateway movimientoGateway;
	private final CuentaGateway CuentaGateway;
	
	
	public ResponseEntity<?> obtenerMovimientos(){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(movimientoGateway.obtenerTodos(),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	
	public ResponseEntity<?> obtenerMovimientoPorId(Long id){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(movimientoGateway.buscarPorId(id),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	public  ResponseEntity<?> guardarMovimiento(MovimientoModel movimiento,boolean creacion){
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			msg =  validaciones(movimiento,creacion);
	        if(!"Ok".equals(msg)) {
	        	log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.BAD_REQUEST);
				return respuesta;
	        }
	        
	        
	        	List<MovimientoModel> movimientos = movimientoGateway.obtenerPorIdCuenta(movimiento.getCuenta().getId());
	        	/*si tiene movimientos anteriores 
	        	 * la cuenta toma 
	        	 * del ultimo movimiento el saldo
	        	 * 
	        	 * de lo contrario toma el salgo inicial de cuenta
	        	 **/
	        	if(!movimientos.isEmpty()) {
	        		Double saldo =0.0;
	        		MovimientoModel movimientoAnt = movimientos.get(0);
	        		if(!creacion && movimiento.getId() !=movimientoAnt.getId()) {
	        			msg = "No se puede actualizar, existen movimientos posteriores";
	        			log.error(msg);
	    				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.BAD_REQUEST);
	    				return respuesta;
	        			
	        		}else {
	        			   if(!creacion) {
	        				   MovimientoModel movimientoAnt2 = null;
	   	        			try {
	   	        				movimientoAnt2 = movimientos.get(1);
	   	        				saldo = movimientoAnt2.getSaldo();
	   						} catch (Exception e) {
	   							
	   						}finally {
	   							movimientoAnt2=null;
	   							saldo = movimiento.getCuenta().getSaldoInicial();
	   						}
	        				   
	        			   }else {
	        				   saldo = movimientoAnt.getSaldo();
	        			   }
	        		}

	        		if((saldo + movimiento.getValor() ) >=0) {
	        			movimiento.setSaldo(saldo + movimiento.getValor() );
	        		}else {
	        			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"Saldo no disponible, por los movimientos anteriormente realizados"), HttpStatus.BAD_REQUEST);
	        			return respuesta;
	        		}
	        	}else {
	        		if( (movimiento.getCuenta().getSaldoInicial() + movimiento.getValor()) >=0) {
	        			movimiento.setSaldo(movimiento.getCuenta().getSaldoInicial() + movimiento.getValor());	
	        		}else {
	        			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"Saldo no disponible, revise el saldo inicial de su cuenta"), HttpStatus.BAD_REQUEST);
	        			return respuesta;
	        		}
	        		
	        	}
	        
	        
	        respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(movimientoGateway.guardar(movimiento),"Ok"), HttpStatus.OK);
	        
		} catch (Exception e) {
			msg =String.format("se presentó un error al guardar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
	
	}
	
	
	public  ResponseEntity<?> eliminarMovimiento(Long id){
		
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			movimientoGateway.eliminar(id);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"registro eliminado. "), HttpStatus.OK);
		} catch (Exception e) {
			msg =String.format("se presentó un error al eliminar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
		
		
	}

	
	
	public ResponseEntity<?> generarInforme(Date fechaInicial, Date fechaFinal){
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		List<MovimientoModel> movimientos =new ArrayList<>();
		List<InformeCuentasModel> datosInforme =new ArrayList<>();
		try {
		    movimientos = movimientoGateway.generarInforme(fechaInicial, fechaFinal);
		    if(movimientos.isEmpty()) {
		    	msg ="No se encontro registros con los filtros dados.";
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.NOT_FOUND);
				return respuesta;
		    }
		    List<CuentaModel> cuentas = CuentaGateway.obtenerTodos();
		    for(MovimientoModel movimiento : movimientos) {
		    	CuentaModel CuentaModel = cuentas.stream().filter(x ->  x.getIdCliente() == movimiento.getCuenta().getIdCliente()  ).findFirst().orElse(null);
		    	InformeCuentasModel model = InformeCuentasModel.builder()
		    			                                       .fecha(movimiento.getFecha())
		    			                                       .nombreCLiente(CuentaModel.getCliente().getNombre())
		    			                                       .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
		    			                                       .tipoMovimiento(movimiento.getTipoMovimiento())
		    			                                       .saldoInicial(movimiento.getCuenta().getSaldoInicial())
		    			                                       .estado(movimiento.getCuenta().getEstado())
		    			                                       .valorMovimiento(movimiento.getValor())
		    			                                       .saldoMovimiento(movimiento.getSaldo())
		    			                                       .build();
		    	
		    	datosInforme.add(model);
		    }
		    
		   
		    respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(datosInforme,msg), HttpStatus.OK);
		    
		} catch (Exception e) {
			msg =String.format("se presentó un error al generar el informe. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return respuesta;
		
		
	}
	
	
	
	private String validaciones(MovimientoModel movimiento,boolean creacion) {
		String msg ="Ok";
		
        if( Validaciones.validarDate(movimiento.getFecha()) ) {
        	msg ="Se debe configurar la fecha del movimiento"; 
		}
        
        if( Validaciones.validarNull(movimiento.getTipoMovimiento()) ) {
        	msg ="Se debe configurar tipo movimiento "; 
		}
        
        if( Validaciones.validarNull(String.valueOf( movimiento.getValor())) ) {
        	msg ="Se debe configurar el valor del movimiento"; 
		}
        
        
        if( !Validaciones.validarLong(movimiento.getCuenta().getId() )) {
        	msg ="Se debe configurar la cuenta del movimiento"; 
		}
        
        if (!creacion && !Validaciones.validarLong(movimiento.getId())) {
            msg = "El id del movimiento no puede ser igual a Null";
        }
        
		
		return msg;
	
	}

}
