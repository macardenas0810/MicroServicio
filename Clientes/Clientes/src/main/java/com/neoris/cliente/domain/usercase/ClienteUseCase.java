package com.neoris.cliente.domain.usercase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neoris.cliente.domain.model.ClienteModel;
import com.neoris.cliente.domain.model.gateway.ClienteGateway;
import com.neoris.cliente.infraestructure.util.ObtenerRespuesta;
import com.neoris.cliente.infraestructure.util.ValidacionesCliente;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ClienteUseCase {

	
	private final ClienteGateway clienteGateway;
	
	
	public ResponseEntity<?> obtenerClientes(){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(clienteGateway.obtenerTodos(),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	
	
	public ResponseEntity<?> obtenerClientePorId(Long id){
		 ResponseEntity<?> respuesta = null;
		 try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(clienteGateway.buscarPorId(id),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
		
	}
	
	
	public  ResponseEntity<?> guardarCliente(ClienteModel cliente,boolean creacion){
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			msg =  validaciones(cliente,creacion);
	        if(!"Ok".equals(msg)) {
	        	log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.BAD_REQUEST);
				return respuesta;
	        }
	        
	        respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(clienteGateway.guardar(cliente),"Ok"), HttpStatus.OK);
	        
		} catch (Exception e) {
			msg =String.format("se presentó un error al guardar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
		
		
		
	}
	
	
	public  ResponseEntity<?> eliminarCliente(Long id){
		
		ResponseEntity<?> respuesta = null;
		String msg ="Ok";
		try {
			clienteGateway.eliminar(id);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"registro eliminado. "), HttpStatus.OK);
		} catch (Exception e) {
			msg =String.format("se presentó un error al eliminar el registro. Causa:  %s", e.getMessage()); 
			log.error(msg);
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return respuesta;
		
		
	}
	
	
	
	
	
	private String validaciones(ClienteModel cliente,boolean creacion) {
		String msg ="Ok";
		
        if(! ValidacionesCliente.validarNull(cliente.getNombre()) ) {
        	msg ="Se debe configurar el nombre del cliente"; 
		}
        if(! ValidacionesCliente.validarNull(cliente.getGenero()) ) {
        	msg ="Se debe configurar el genero del cliente"; 
		}
        if(! ValidacionesCliente.validarNull(cliente.getNombre()) ) {
        	msg ="Se debe configurar el nombre del cliente"; 
		}
		if(! ValidacionesCliente.validarNull(cliente.getIdentificacion()) ) {
			msg ="Se debe configurar el número de idenficación"; 
		}
		if(! ValidacionesCliente.validarNull(cliente.getDireccion()) ) {
			msg ="Se debe configurar la dirección del cliente"; 
		}
		if(! ValidacionesCliente.validarNull(cliente.getTelefono()) ) {
			msg ="Se debe configurar el telefono del cliente"; 
		}

		if(! ValidacionesCliente.validarInt(cliente.getEdad()) ) {
			msg ="La edad del cliente no puede ser igual a 0"; 
		}
        
        
        if(! ValidacionesCliente.validarNull(cliente.getContrasena()) ) {
        	msg ="Se debe configurar una contraseña para el cliente"; 
		}
        
        if(! ValidacionesCliente.validarNull(cliente.getEstado()) ) {
        	msg ="Se debe configurar el estado para el cliente"; 
		}
        
        if (!creacion && !ValidacionesCliente.validarLong(cliente.getId())) {
            msg = "El id del cliente no puede ser igual a Null";
        }

        
        
        return msg;
		
	}
	
	
	
	
	
}
