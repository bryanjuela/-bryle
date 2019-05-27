package es.bryle.digital.profesional.model.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

	@OneToOne(cascade= CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "car_id")
	private Car car;
	
	@Column(name = "price")
	private String price;
	
	@OneToOne(cascade= CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="professional_id", referencedColumnName= "id",
				foreignKey= @ForeignKey(name= "fk_professional_sale"))
	private Professional professional;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
