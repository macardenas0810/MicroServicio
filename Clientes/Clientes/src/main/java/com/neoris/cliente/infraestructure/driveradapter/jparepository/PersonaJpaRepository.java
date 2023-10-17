package com.neoris.cliente.infraestructure.driveradapter.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.cliente.infraestructure.driveradapter.PersonaEntity;

@Repository
public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Long> {

}
