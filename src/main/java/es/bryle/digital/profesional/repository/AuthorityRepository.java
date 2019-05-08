package es.bryle.digital.profesional.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.auth.Authority;

public interface AuthorityRepository  extends JpaRepository<Authority, Long>{

	public Set<Authority> findByName(String name);
}
