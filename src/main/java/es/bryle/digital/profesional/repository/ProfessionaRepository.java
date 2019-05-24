package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.auth.User;

public interface ProfessionaRepository extends JpaRepository<Professional, Long> {

	public Professional findByDni(String dni);
	
	public Professional findByUser(User user);
}
