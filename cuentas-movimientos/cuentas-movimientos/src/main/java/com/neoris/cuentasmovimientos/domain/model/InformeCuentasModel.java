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
public class InformeCuentasModel {
	
	private Date fecha;
	private String nombreCLiente;
	private String numeroCuenta;
	private String tipoMovimiento;
	private Double saldoInicial;
	private String estado;
	private Double valorMovimiento;
	private Double saldoMovimiento;

}

