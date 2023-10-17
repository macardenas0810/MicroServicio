package com.neoris.cliente.infraestructure.driveradapter.jparepository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.cliente.infraestructure.driveradapter.ClienteEntity;


@Repository
public interface ClienteJpaRepository  extends JpaRepository<ClienteEntity, Long>  {

}
