package es.bryle.digital.profesional.model.vo;

import java.util.Date;

public class SaleVO {

	private Long id;
	//private Date saleDate;
	private String saleDate;
	private String car;
	private String professional;
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
	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
