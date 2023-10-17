package com.neoris.cuentasmovimientos.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neoris.cuentasmovimientos.domain.model.gateway.CuentaGateway;
import com.neoris.cuentasmovimientos.domain.usercase.CuentaUseCase;
import com.neoris.cuentasmovimientos.domain.usercase.MovimientoUseCase;
import com.neoris.cuentasmovimientos.domain.model.gateway.MovimientoGateway;

@Configuration
public class UseCaseConfig {
	
	
	@Bean
	public CuentaUseCase cuentaGateway(CuentaGateway gateway) {
		return new CuentaUseCase(gateway);
	}
	
	
	@Bean
	public MovimientoUseCase MovimientoGateway(MovimientoGateway gateway,CuentaGateway cGateway) {
		return new MovimientoUseCase(gateway,cGateway);
	}

}
