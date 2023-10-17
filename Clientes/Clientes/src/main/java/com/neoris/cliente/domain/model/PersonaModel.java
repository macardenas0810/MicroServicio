package com.neoris.cliente.domain.model;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonaModel {
	

	private Long id;
    private String nombre;
    private String genero;
    private Integer  edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}
