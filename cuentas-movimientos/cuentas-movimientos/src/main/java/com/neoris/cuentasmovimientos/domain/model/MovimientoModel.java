package com.neoris.cuentasmovimientos.domain.model;


import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoModel {
	
	    private Long id;
	    private Date fecha;
	    private String tipoMovimiento;
	    private Double valor;
	    private Double saldo;
	    private CuentaModel cuenta;


}
