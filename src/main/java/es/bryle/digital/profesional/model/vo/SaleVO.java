package es.bryle.digital.profesional.model.vo;

import java.util.Date;

public class SaleVO {

	private Long id;
	private Date saleDate;
	private Long car;
	private Long professionalVO;
	
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

	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}

	public Long getProfessional() {
		return professionalVO;
	}

	public void setProfessional(Long professional) {
		this.professionalVO = professional;
	}
}
