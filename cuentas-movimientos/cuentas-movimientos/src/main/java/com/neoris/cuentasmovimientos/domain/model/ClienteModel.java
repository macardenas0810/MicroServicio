package com.neoris.cuentasmovimientos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteModel {
	

	private Long id;
    private String nombre;
    private String genero;
    private Integer  edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasena;
    private String estado;

}
