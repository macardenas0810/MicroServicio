package com.neoris.cuentasmovimientos.infraestructure.mapper;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import com.neoris.cuentasmovimientos.domain.model.CuentaModel;
import com.neoris.cuentasmovimientos.domain.model.MovimientoModel;
import com.neoris.cuentasmovimientos.infraestructure.driveradapter.CuentaEntity;
import com.neoris.cuentasmovimientos.infraestructure.driveradapter.MovimientoEntity;

import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
public class Mappers {
	
	public CuentaModel pasarACuentaModel(CuentaEntity entity) {
		
		CuentaModel rta = CuentaModel.builder()
		          .id(entity.getId())
		          .numeroCuenta(entity.getNumeroCuenta())
		          .tipoCuenta(entity.getTipoCuenta())
		          .saldoInicial(entity.getSaldoInicial() )
		          .estado(entity.getEstado())
		          .idCliente(entity.getIdCliente())
		          //.movimientos(pasarAListMovimientoModel( entity.getMovimientos()))
		          .build();
		
		return rta;
		
	}
	
	
	public CuentaEntity pasarACuentaEntity(CuentaModel model) {
		return CuentaEntity.builder()
		                   .id(model.getId())
		                   .numeroCuenta(model.getNumeroCuenta())
		                   .tipoCuenta(model.getTipoCuenta())
		                   .saldoInicial(model.getSaldoInicial()  )
		                   .estado(model.getEstado())
		                   .idCliente(model.getIdCliente())
        		           //.movimientos(pasarAListMovimientoEntity(  model.getMovimientos()))
		                   .build();
		
	}
	
	public MovimientoModel pasarAMovimientoModel(MovimientoEntity entity) {
		MovimientoModel rta =MovimientoModel.builder()
							                .id(entity.getId())
							                .fecha(entity.getFecha())
							                .tipoMovimiento(entity.getTipoMovimiento())
							                .valor(entity.getValor())
							                .saldo(entity.getSaldo())
							                .cuenta( pasarACuentaModel(  entity.getCuenta()))
							                .build(); 
		
		return rta;
	}
	
	
	public MovimientoEntity pasarAMovimientoEntity(MovimientoModel model) {
		return MovimientoEntity.builder()
				               .id(model.getId())
	                           .fecha(model.getFecha())
	                           .tipoMovimiento(model.getTipoMovimiento())
	                           .valor(model.getValor())
	                           .saldo(model.getSaldo())
 	                           .cuenta( pasarACuentaEntity( model.getCuenta()))
				               .build();
		
	}
	
	
	public List<MovimientoEntity> pasarAListMovimientoEntity(List<MovimientoModel> models){
		List<MovimientoEntity> rta = new ArrayList<>();
		for(MovimientoModel model : models) {
			MovimientoEntity entity = pasarAMovimientoEntity(model);
			rta.add(entity);
		}		
		return rta;
		
	}
	
	
	
	public List<MovimientoModel> pasarAListMovimientoModel(List<MovimientoEntity> movimientos){
		List<MovimientoModel> rta = new ArrayList<>();
		for(MovimientoEntity entity : movimientos) {
			MovimientoModel model = pasarAMovimientoModel(entity);
			rta.add(model);
		}		
		return rta;
		
	}
	

}
