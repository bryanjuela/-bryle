package es.bryle.digital.profesional.model.vo;

import java.util.List;
import java.util.Set;

import es.bryle.digital.profesional.model.entities.auth.Role;


public class UserVO {

	private Long id;
	private String email;
	//private String password;
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
