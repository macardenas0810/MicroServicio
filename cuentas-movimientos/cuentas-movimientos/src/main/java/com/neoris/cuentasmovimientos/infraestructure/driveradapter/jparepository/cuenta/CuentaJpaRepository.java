package com.neoris.cuentasmovimientos.infraestructure.driveradapter.jparepository.cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.cuentasmovimientos.infraestructure.driveradapter.CuentaEntity;

@Repository
public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {

}
