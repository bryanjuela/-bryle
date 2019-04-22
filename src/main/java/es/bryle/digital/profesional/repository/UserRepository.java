package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.auth.User;


public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
