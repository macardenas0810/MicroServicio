package com.neoris.cuentasmovimientos.domain.usercase;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import  com.neoris.cuentasmovimientos.infraestructure.util.ObtenerRespuesta;
import com.neoris.cuentasmovimientos.domain.model.CuentaModel;
import com.neoris.cuentasmovimientos.domain.model.gateway.CuentaGateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CuentaUseCase {
	
	private final CuentaGateway cuentaGateway;
	
	
	public ResponseEntity<?> obtenerCuentas(){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(cuentaGateway.obtenerTodos(),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	
	public ResponseEntity<?> obtenerCuentaPorId(Long id){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(cuentaGateway.buscarPorId(id),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	
	public  ResponseEntity<?> guardarCuenta(CuentaModel cuenta,boolean creacion){
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			msg =  validaciones(cuenta,creacion);
	        if(!"Ok".equals(msg)) {
	        	log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.BAD_REQUEST);
				return respuesta;
	        }
	        
	        respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(cuentaGateway.guardar(cuenta),"Ok"), HttpStatus.OK);
	        
		} catch (Exception e) {
			msg =String.format("se presentó un error al guardar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
	
	}
	
	
	public  ResponseEntity<?> eliminarCuenta(Long id){
		
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			cuentaGateway.eliminar(id);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"registro eliminado. "), HttpStatus.OK);
		} catch (Exception e) {
			msg =String.format("se presentó un error al eliminar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
		
		
	}
	
	
	private String validaciones(CuentaModel cuenta,boolean creacion) {
		String msg ="Ok";
		return msg;
	
	}

}
