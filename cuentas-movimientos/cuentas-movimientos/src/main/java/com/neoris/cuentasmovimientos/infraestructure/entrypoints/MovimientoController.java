package com.neoris.cuentasmovimientos.infraestructure.entrypoints;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.neoris.cuentasmovimientos.domain.model.MovimientoModel;
import com.neoris.cuentasmovimientos.domain.usercase.MovimientoUseCase;



@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
	
	@Autowired
	private  MovimientoUseCase movimientoUseCase;
	
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerMovimientos(){
		
		return movimientoUseCase.obtenerMovimientos();
	}
	
	
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerClientePorId(@PathVariable(name = "id")  Long id){
		
		return movimientoUseCase.obtenerMovimientoPorId(id);
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoModel movimiento){
		
		return movimientoUseCase.guardarMovimiento(movimiento,true);
		
	}
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarMovimiento(@RequestBody MovimientoModel movimiento){
		return movimientoUseCase.guardarMovimiento(movimiento,false);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarMovimiento(@PathVariable(name = "id")  Long id){
		return movimientoUseCase.eliminarMovimiento(id);
	}
	
	
    @GetMapping("/informe-estado-cuenta")
    public ResponseEntity<?> obtenerMovimientosPorRangoDeFecha(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        
       
    	return movimientoUseCase.generarInforme(fechaInicio, fechaFin);
    }
	
	

}
