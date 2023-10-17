package com.neoris.cuentasmovimientos.infraestructure.driveradapter.jparepository.movimiento;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.cuentasmovimientos.infraestructure.driveradapter.MovimientoEntity;

@Repository
public interface MovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {
	
	List<MovimientoEntity> findByCuentaId(Long cuentaId);
	
	 List<MovimientoEntity> findByFechaBetween(Date fechaInicio, Date fechaFin);

}
