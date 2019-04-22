package es.bryle.digital.profesional.model.vo;

import java.util.Date;

public class SaleVO {

	private Long id;
	private Date saleDate;
	private CarVO car;
	private ProfessionalVO professionalVO;
	
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

	public CarVO getCar() {
		return car;
	}

	public void setCar(CarVO car) {
		this.car = car;
	}

	public ProfessionalVO getProfessional() {
		return professionalVO;
	}

	public void setProfessional(ProfessionalVO professional) {
		this.professionalVO = professional;
	}
}
