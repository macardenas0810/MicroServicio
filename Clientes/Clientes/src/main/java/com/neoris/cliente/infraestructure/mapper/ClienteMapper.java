package com.neoris.cliente.infraestructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.neoris.cliente.domain.model.ClienteModel;
import com.neoris.cliente.infraestructure.driveradapter.ClienteEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ClienteMapper {

	public ClienteModel pasarAClienteModel(ClienteEntity clienteEntity) {
	return ClienteModel.builder()
			           .contrasena(clienteEntity.getContrasena())
			           .estado(clienteEntity.getEstado())
			           .id(clienteEntity.getId())
			           .nombre(clienteEntity.getNombre())
                       .genero(clienteEntity.getGenero())
                       .edad(clienteEntity.getEdad())
                       .identificacion(clienteEntity.getIdentificacion())
                       .direccion(clienteEntity.getDireccion())
                       .telefono(clienteEntity.getTelefono())
			           .build();
			           
}
	
	public ClienteEntity pasarAClienteEntity(ClienteModel clienteModel) {
		return ClienteEntity.builder()
	            .contrasena(clienteModel.getContrasena())
	            .estado(clienteModel.getEstado())
	            .id(clienteModel.getId())
	            .nombre(clienteModel.getNombre())
	            .genero(clienteModel.getGenero())
	            .edad(clienteModel.getEdad())
	            .identificacion(clienteModel.getIdentificacion())
	            .direccion(clienteModel.getDireccion())
	            .telefono(clienteModel.getTelefono())
	            .build();
	}
	
	
	public List<ClienteModel> toListClienteModel(List<ClienteEntity> entity){
		List<ClienteModel> clientesModel = entity.stream().map( x -> {
			return ClienteModel.builder()
			                   .contrasena(x.getContrasena())
			                   .estado(x.getEstado())
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
		
		
		return clientesModel;
		
		
		
	}
	
	
}
