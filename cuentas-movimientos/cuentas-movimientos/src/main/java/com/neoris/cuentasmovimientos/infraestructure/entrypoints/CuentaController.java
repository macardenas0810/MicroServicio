package com.neoris.cuentasmovimientos.infraestructure.entrypoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.neoris.cuentasmovimientos.domain.model.CuentaModel;
import com.neoris.cuentasmovimientos.domain.usercase.CuentaUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
public class CuentaController {
	
	
	private final CuentaUseCase cuentaUseCase;
	
	
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerCuentas(){
		
		return cuentaUseCase.obtenerCuentas();
	}
	
	
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerClientePorId(@PathVariable(name = "id")  Long id){
		
		return cuentaUseCase.obtenerCuentaPorId(id);
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCuenta(@RequestBody CuentaModel cuenta){
		
		return cuentaUseCase.guardarCuenta(cuenta,true);
		
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarCuenta(@RequestBody CuentaModel cuenta){
		return cuentaUseCase.guardarCuenta(cuenta,false);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCuenta(@PathVariable(name = "id")  Long id){
		return cuentaUseCase.eliminarCuenta(id);
	}
	
	

}
