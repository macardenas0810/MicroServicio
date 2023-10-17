package com.neoris.cliente.infraestructure.entrypoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.cliente.domain.model.ClienteModel;
import com.neoris.cliente.domain.usercase.ClienteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteUseCase clienteUseCase;
	
	
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerCliente(){
		
		return clienteUseCase.obtenerClientes();
	}
	
	
	
	@GetMapping("/porId/{id}")
	public ResponseEntity<?> obtenerClientePorId(@PathVariable(name = "id")  Long id){
		
		return clienteUseCase.obtenerClientePorId(id);
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteModel cliente){
		
		return clienteUseCase.guardarCliente(cliente,true);
		
	}
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteModel cliente){
		return clienteUseCase.guardarCliente(cliente,false);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable(name = "id")  Long id){
		return clienteUseCase.eliminarCliente(id);
	}
	
	
	
}
