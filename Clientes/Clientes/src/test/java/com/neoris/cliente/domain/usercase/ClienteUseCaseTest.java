package com.neoris.cliente.domain.usercase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.neoris.cliente.domain.model.ClienteModel;
import com.neoris.cliente.domain.model.gateway.ClienteGateway;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ClienteUseCaseTest {
	 @Mock
	 ClienteGateway clienteGateway;
	 @InjectMocks
	 ClienteUseCase clienteUseCase;
	

    @BeforeEach
    void setUp() {
    	 

    }

    @Test
    void obtenerClientes() {
    	List<ClienteModel> data = new ArrayList<>();
    	ClienteModel cliente1 = ClienteModel.builder()
							    			.id(1L)
							    			.edad(30)
							    			.nombre("Carlos")
    			                            .build();
    	
    	ClienteModel cliente2 = ClienteModel.builder()
    			.id(1L)
    			.edad(30)
    			.nombre("Andres")
                .build();
    	
    	data.add(cliente1);
    	data.add(cliente2);
    	
    	 when(clienteGateway.obtenerTodos()).thenReturn(data );
    	ResponseEntity<?> respuesta = null;
    	respuesta = clienteUseCase.obtenerClientes();
    	assertTrue(respuesta !=null);
    }

    @Test
    void obtenerClientePorId() {
    	ClienteModel cliente1 = ClienteModel.builder()
    			.id(1L)
    			.edad(30)
    			.nombre("Carlos")
                .build();
    	when(clienteGateway.buscarPorId(1L)).thenReturn(cliente1 );
    	ResponseEntity<?> respuesta = null;
    	respuesta = clienteUseCase.obtenerClientePorId(1L);
    	assertTrue(respuesta !=null);
    }

}