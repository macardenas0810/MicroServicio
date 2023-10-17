package com.neoris.cliente.domain.model.gateway;

import java.util.List;

import com.neoris.cliente.domain.model.ClienteModel;


public interface ClienteGateway {
	
	ClienteModel guardar(ClienteModel cliente);
	void eliminar(Long id);
	ClienteModel buscarPorId(Long id);
	List<ClienteModel> obtenerTodos();


}
