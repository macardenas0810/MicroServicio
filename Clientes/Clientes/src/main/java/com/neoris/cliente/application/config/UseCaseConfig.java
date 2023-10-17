package com.neoris.cliente.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neoris.cliente.domain.model.gateway.ClienteGateway;
import com.neoris.cliente.domain.usercase.ClienteUseCase;


@Configuration
public class UseCaseConfig {

	
	@Bean
	public ClienteUseCase clienteGateway(ClienteGateway gateway) {
		return new ClienteUseCase(gateway);
	}
	
	

	
}
