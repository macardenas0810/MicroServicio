package com.neoris.cliente.domain.model.gateway;

import java.util.List;

import com.neoris.cliente.domain.model.PersonaModel;

public interface PersonaGateway {
	
	PersonaModel guardar(PersonaModel persona);
	void eliminar(Long id);
	PersonaModel buscarPorId(Long id);
	List<PersonaModel> obtenerTodos();
	PersonaModel actualizar(PersonaModel persona);

}
