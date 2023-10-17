package com.neoris.cuentasmovimientos.infraestructure.driveradapter;


import java.util.List;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUENTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NUMERO_CUENTA", unique = true, nullable = false)
    private String numeroCuenta;

    @Column(name="TIPO_CUENTA", nullable = false)
    private String tipoCuenta;

    @Column(name="SALDO_INICIAL", nullable = false)
    private Double saldoInicial;

    @Column(nullable = false)
    private String estado;
    
    @Column(name = "ID_CLIENTE",   nullable = false)
    private Long idCliente;
    
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<MovimientoEntity> movimientos;

}
