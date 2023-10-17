package com.neoris.cuentasmovimientos.infraestructure.driveradapter.consumeapi;

import com.neoris.cuentasmovimientos.domain.model.ClienteModel;



public interface IObtenerCliente {

	ClienteModel cliente (Long id);
	
}
