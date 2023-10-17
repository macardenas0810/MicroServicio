package com.neoris.cliente.infraestructure.driveradapter.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neoris.cliente.domain.model.PersonaModel;
import com.neoris.cliente.domain.model.gateway.PersonaGateway;
import com.neoris.cliente.infraestructure.driveradapter.PersonaEntity;
import com.neoris.cliente.infraestructure.mapper.PersonaMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioEntityGatewayImpl implements PersonaGateway{

	private final PersonaMapper personaMapper;
	private final PersonaJpaRepository personaJpaRepository;
	
	
	
	@Override
	public PersonaModel guardar(PersonaModel persona) {
	/*	PersonaEntity personaEntity = personaMapper.pasarAPersonaEntity(persona);
		return  personaMapper.pasarAPersonaModel( personaJpaRepository.save(personaEntity));
		*/
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersonaModel buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonaModel> obtenerTodos() {
		/*return personaMapper.toListPersonaModel( personaJpaRepository.findAll());*/
		return null;
	}

	@Override
	public PersonaModel actualizar(PersonaModel persona) {
		// TODO Auto-generated method stub
		return null;
	}

}
