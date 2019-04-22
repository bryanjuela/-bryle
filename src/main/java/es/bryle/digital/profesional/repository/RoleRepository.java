package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.auth.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByType(String type);
	
	
}
