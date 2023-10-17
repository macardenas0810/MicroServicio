package com.neoris.cliente.infraestructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.neoris.cliente.domain.model.PersonaModel;
import com.neoris.cliente.infraestructure.driveradapter.PersonaEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PersonaMapper {
	
	/*public PersonaModel pasarAPersonaModel(PersonaEntity personaEntity) {
		return PersonaModel.builder()
				           .id(personaEntity.getId())
				           .nombre(personaEntity.getNombre())
				           .genero(personaEntity.getGenero())
				           .edad(personaEntity.getEdad())
				           .identificacion(personaEntity.getIdentificacion())
				           .direccion(personaEntity.getDireccion())
				           .telefono(personaEntity.getTelefono())
				           .build();
	}
	
	
	public PersonaEntity pasarAPersonaEntity(PersonaModel personaModel) {
		return PersonaEntity.builder()
				           .id(personaModel.getId())
				           .nombre(personaModel.getNombre())
				           .genero(personaModel.getGenero())
				           .edad(personaModel.getEdad())
				           .identificacion(personaModel.getIdentificacion())
				           .direccion(personaModel.getDireccion())
				           .telefono(personaModel.getTelefono())
				           .build();
	}
	
	
	
	public List<PersonaModel> toListPersonaModel(List<PersonaEntity> entity){
		List<PersonaModel> personasModel = entity.stream().map( x -> {
			return PersonaModel.builder()
				               .id(x.getId())
				               .nombre(x.getNombre())
				               .genero(x.getGenero())
				               .edad(x.getEdad())
				               .identificacion(x.getIdentificacion())
				               .direccion(x.getDireccion())
				               .telefono(x.getTelefono())
				               .build();
		                  }
				          ).collect(Collectors.toList());
		
		
		return personasModel;
		
		
		
	}
	*/

}
