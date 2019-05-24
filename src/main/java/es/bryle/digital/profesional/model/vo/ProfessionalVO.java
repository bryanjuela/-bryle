package es.bryle.digital.profesional.model.vo;

import java.util.Set;


public class ProfessionalVO {

	private Long id;
	private String dni;
	private String firstName;
	private String lastName;
	private String user;
	private Set<SaleVO> sales;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lasttName) {
		this.lastName = lasttName;
	}

	public Set<SaleVO> getSales() {
		return sales;
	}

	public void setSales(Set<SaleVO> sales) {
		this.sales = sales;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
