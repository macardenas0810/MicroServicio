package com.neoris.cuentasmovimientos.domain.model.gateway;

import java.util.List;

import com.neoris.cuentasmovimientos.domain.model.CuentaModel;



public interface CuentaGateway {

	
	CuentaModel guardar(CuentaModel cliente);
	void eliminar(Long id);
	CuentaModel buscarPorId(Long id);
	List<CuentaModel> obtenerTodos();
}
