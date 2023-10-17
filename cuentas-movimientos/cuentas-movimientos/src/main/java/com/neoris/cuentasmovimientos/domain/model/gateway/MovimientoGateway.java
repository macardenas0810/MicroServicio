package com.neoris.cuentasmovimientos.domain.model.gateway;

import java.util.Date;
import java.util.List;

import com.neoris.cuentasmovimientos.domain.model.MovimientoModel;



public interface MovimientoGateway {
	
	MovimientoModel guardar(MovimientoModel cliente);
	void eliminar(Long id);
	MovimientoModel buscarPorId(Long id);
	List<MovimientoModel> obtenerTodos();
	List<MovimientoModel> obtenerPorIdCuenta(Long id);
	List<MovimientoModel> generarInforme(Date fechaInicial, Date fechaFinal);


}
