package com.neoris.cliente.infraestructure.driveradapter;

import javax.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PERSONA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class PersonaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
    private String nombre;
    private String genero;
    private Integer  edad;
    @Column(unique = true, nullable = false)
    private String identificacion;
    private String direccion;
    private String telefono;


}
