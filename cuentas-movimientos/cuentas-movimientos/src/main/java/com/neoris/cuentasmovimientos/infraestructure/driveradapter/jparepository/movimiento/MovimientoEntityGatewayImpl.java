package com.neoris.cuentasmovimientos.infraestructure.driveradapter.jparepository.movimiento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


import com.neoris.cuentasmovimientos.domain.model.MovimientoModel;
import com.neoris.cuentasmovimientos.domain.model.gateway.MovimientoGateway;
import com.neoris.cuentasmovimientos.infraestructure.driveradapter.MovimientoEntity;
import com.neoris.cuentasmovimientos.infraestructure.mapper.Mappers;
import com.neoris.cuentasmovimientos.infraestructure.util.RegistroNoEncontradoException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MovimientoEntityGatewayImpl implements MovimientoGateway {
	
	private final MovimientoJpaRepository movimientoJpaRepository;
	private final Mappers mappers;
	
	
	@Override
	public MovimientoModel buscarPorId(Long id) {
		 Optional<MovimientoEntity> movimiento = movimientoJpaRepository.findById(id);
		 MovimientoEntity entity = movimiento.orElse(null);
		 MovimientoModel model = null;
		 if(entity !=null) {
			 model = mappers.pasarAMovimientoModel(entity);
		 }else {
			 String msg = String.format( "No se encontró registros para el id: %s",id);
				log.error(msg);
				throw new RegistroNoEncontradoException(msg);
		 }
		return model;
	}
	
	
	@Override
	public List<MovimientoModel> obtenerTodos() {
		List<MovimientoEntity> listMovimientos = movimientoJpaRepository.findAll();
		List<MovimientoModel> rta = new ArrayList<>();
		if(!listMovimientos.isEmpty()) {
			for(MovimientoEntity movimiento : listMovimientos){
				MovimientoModel model = mappers.pasarAMovimientoModel(movimiento);
				rta.add(model);
			}			
		}
		
		return rta;
	}



	
	@Override
	public MovimientoModel guardar(MovimientoModel movimiento) {
		 MovimientoEntity entity = mappers.pasarAMovimientoEntity(movimiento);
		 entity = movimientoJpaRepository.save(entity);
		 movimiento = mappers.pasarAMovimientoModel(entity);
		return movimiento;
	}

	@Override
	public void eliminar(Long id) {
		movimientoJpaRepository.deleteById(id);
	}

	
	@Override
	public List<MovimientoModel> obtenerPorIdCuenta(Long id) {
		List<MovimientoEntity> listMovimientos = movimientoJpaRepository.findByCuentaId(id);
		List<MovimientoModel> rta = new ArrayList<>();
		if(!listMovimientos.isEmpty()) {
			for(MovimientoEntity movimiento : listMovimientos){
				MovimientoModel model = mappers.pasarAMovimientoModel(movimiento);
				rta.add(model);
			}
			if(!rta.isEmpty()) {
				rta.sort(Comparator.comparing(MovimientoModel::getId).reversed());
		    
			}
		}
		return rta;
	}


	@Override
	public List<MovimientoModel> generarInforme(Date fechaInicial, Date fechaFinal) {
		List<MovimientoEntity> listMovimientos = movimientoJpaRepository.findByFechaBetween(fechaInicial, fechaFinal);
		List<MovimientoModel> rta = new ArrayList<>();
		if(!listMovimientos.isEmpty()) {
			for(MovimientoEntity movimiento : listMovimientos){
				MovimientoModel model = mappers.pasarAMovimientoModel(movimiento);
				rta.add(model);
			}
		}
		return rta;
	}





}
