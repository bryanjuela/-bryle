package es.bryle.digital.profesional.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "numero_bastidor")
	private String numBastidor;
	
	@Column(name = "anyo")
	private String anyo;
	
	@Column(name = "precio")
	private String precio;
	
	@Column(name = "estado")
	private String estado;
}
