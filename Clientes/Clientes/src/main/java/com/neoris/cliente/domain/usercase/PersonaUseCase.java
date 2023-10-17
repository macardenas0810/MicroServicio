package com.neoris.cliente.domain.usercase;

import com.neoris.cliente.domain.model.gateway.PersonaGateway;
import com.neoris.cliente.infraestructure.util.ObtenerRespuesta;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neoris.cliente.domain.model.PersonaModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PersonaUseCase {

	private final PersonaGateway personaGateway;
	
	
	public ResponseEntity<?> obtenerPersonas(){
		    ResponseEntity<?> respuesta = null;
			try {
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(personaGateway.obtenerTodos(),"Ok"), HttpStatus.OK);
			} catch (Exception e) {
				String msg =String.format("se presentó un error al obtener los registros. Causa:  %s", e.getMessage()); 
				log.error(msg);
				respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,"Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    	return respuesta;
	}
	
	public ResponseEntity<?> guardarPersona(PersonaModel persona){
		ResponseEntity<?> respuesta = null;
		try {
			respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(personaGateway.guardar(persona),"Ok"), HttpStatus.OK);
		} catch (Exception e) {
			String msg = String.format("se presento un error al guardar el registro  %s, causa:  %s",persona.getNombre(), e.getMessage());
            log.error(msg);
            respuesta  = new ResponseEntity<>(ObtenerRespuesta.devolverRespuesta(null,msg), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return respuesta; 
	}
	
	
	
	
	
	

	
}
