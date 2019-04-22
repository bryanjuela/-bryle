package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
