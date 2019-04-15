package es.bryle.digital.profesional.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "sale_date")
	private Date saleDate;
	
	@OneToOne
	@JoinColumn(name = "car")
	private Car car;
	
	@ManyToOne
	@JoinColumn
	private Professional professional;
	
}
