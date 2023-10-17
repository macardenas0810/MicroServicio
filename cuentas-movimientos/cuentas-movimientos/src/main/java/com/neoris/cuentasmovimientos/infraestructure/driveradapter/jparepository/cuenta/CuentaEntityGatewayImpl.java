package com.neoris.cuentasmovimientos.infraestructure.driveradapter.jparepository.cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.neoris.cuentasmovimientos.domain.model.ClienteModel;
import com.neoris.cuentasmovimientos.domain.model.CuentaModel;
import com.neoris.cuentasmovimientos.domain.model.gateway.CuentaGateway;
import com.neoris.cuentasmovimientos.infraestructure.driveradapter.CuentaEntity;
import com.neoris.cuentasmovimientos.infraestructure.driveradapter.consumeapi.IObtenerCliente;
import com.neoris.cuentasmovimientos.infraestructure.mapper.Mappers;
import com.neoris.cuentasmovimientos.infraestructure.util.RegistroNoEncontradoException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CuentaEntityGatewayImpl implements CuentaGateway {
	
	
	private final CuentaJpaRepository cuentaJpaRepository;
	private final Mappers cuentaMapper;
	private final IObtenerCliente iObtenerCliente;
	
	@Override
	public CuentaModel buscarPorId(Long id) {
		Optional<CuentaEntity> cuenta =cuentaJpaRepository.findById(id);
		CuentaEntity cuentaEntity = cuenta.orElse(null);
		CuentaModel model = null;
		if(cuentaEntity !=null) {
			ClienteModel cliente = iObtenerCliente.cliente(cuentaEntity.getIdCliente());
			model = cuentaMapper.pasarACuentaModel(cuentaEntity);
			model.setCliente(cliente);
		}else {
			String msg = String.format( "No se encontró registros para el id: %s",id);
			log.error(msg);
			throw new RegistroNoEncontradoException(msg);
		}
		
		return model;
	}
	

	@Override
	public List<CuentaModel> obtenerTodos() {
		List<CuentaEntity> listCuentas = cuentaJpaRepository.findAll();
		List<CuentaModel> rta = new ArrayList<>();
		if(!listCuentas.isEmpty()) {
			for(CuentaEntity cuenta : listCuentas) {
				ClienteModel cliente = iObtenerCliente.cliente(cuenta.getIdCliente());
				CuentaModel model = cuentaMapper.pasarACuentaModel(cuenta);
				model.setCliente(cliente);
				rta.add(model);
			}
		}
		
		
		return rta;
	}
	
	
	
	
	
	@Override
	public CuentaModel guardar(CuentaModel cuenta) {
		  CuentaEntity entity = cuentaMapper.pasarACuentaEntity(cuenta);
		  entity = cuentaJpaRepository.save(entity);
		  cuenta = cuentaMapper.pasarACuentaModel(entity);
		return cuenta;
	}

	@Override
	public void eliminar(Long id) {
		cuentaJpaRepository.deleteById(id);
	}




}
