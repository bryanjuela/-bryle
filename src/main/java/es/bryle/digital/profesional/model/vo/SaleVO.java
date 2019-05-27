package es.bryle.digital.profesional.model.vo;

import java.util.Date;

public class SaleVO {

	private Long id;
	//private Date saleDate;
	private String saleDate;
	private Long car;
	private Long professional;
	private String price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}*/

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}

	public Long getProfessional() {
		return professional;
	}

	public void setProfessional(Long professional) {
		this.professional = professional;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
