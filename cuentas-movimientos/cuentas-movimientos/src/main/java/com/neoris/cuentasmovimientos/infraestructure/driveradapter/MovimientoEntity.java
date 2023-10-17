package com.neoris.cuentasmovimientos.infraestructure.driveradapter;


import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIMIENTOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private Date fecha;
    @Column(name="TIPO_MOVIMIENTO", nullable = false)
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "ID_CUENTA", nullable = false)
    private CuentaEntity cuenta;

}
