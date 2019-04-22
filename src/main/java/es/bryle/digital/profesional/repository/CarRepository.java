package es.bryle.digital.profesional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.bryle.digital.profesional.model.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
