package com.neoris.cuentasmovimientos.domain.model;



import java.math.BigDecimal;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaModel {
	
	    private Long id;

	    private String numeroCuenta;

	    private String tipoCuenta;

	    private Double saldoInicial;

	    private String estado;
	    
	    private ClienteModel cliente;
	    
	    private Long idCliente;

	    private List<MovimientoModel> movimientos;
}
