package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.bryle.digital.profesional.model.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public Car findByNumBastidor(String numBastidor);
}
