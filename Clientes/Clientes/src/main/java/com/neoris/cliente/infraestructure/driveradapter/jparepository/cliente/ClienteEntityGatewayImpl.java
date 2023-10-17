package com.neoris.cliente.infraestructure.driveradapter.jparepository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.neoris.cliente.domain.model.ClienteModel;
import com.neoris.cliente.domain.model.gateway.ClienteGateway;
import com.neoris.cliente.infraestructure.driveradapter.ClienteEntity;
import com.neoris.cliente.infraestructure.mapper.ClienteMapper;
import com.neoris.cliente.infraestructure.util.RegistroNoEncontradoException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClienteEntityGatewayImpl implements ClienteGateway {
	
	private final ClienteJpaRepository clienteJpaRepository;
	private final ClienteMapper clienteMapper;
	

	@Override
	public ClienteModel buscarPorId(Long id) {
		
		Optional<ClienteEntity> cliente =clienteJpaRepository.findById(id);
		ClienteEntity clienteEntity = cliente.orElse(null);
		ClienteModel model = null;
		if (clienteEntity != null) {
			model = clienteMapper.pasarAClienteModel(clienteEntity);
		}else {
			String msg = String.format( "No se encontró registros para el id: %s",id);
			log.error(msg);
			throw new RegistroNoEncontradoException(msg);
		}
		
		return model;
	}

	@Override
	public List<ClienteModel> obtenerTodos() {

		return clienteMapper.toListClienteModel( clienteJpaRepository.findAll());
	}
	
	
	
	@Override
	public ClienteModel guardar(ClienteModel cliente) {
        ClienteEntity clienteEntity = clienteMapper.pasarAClienteEntity(cliente);
        clienteEntity = clienteJpaRepository.save(clienteEntity); 
        cliente = clienteMapper.pasarAClienteModel(clienteEntity);
		return cliente;
	}

	@Override
	public void eliminar(Long id) {
		clienteJpaRepository.deleteById(id);
		
	}



}
