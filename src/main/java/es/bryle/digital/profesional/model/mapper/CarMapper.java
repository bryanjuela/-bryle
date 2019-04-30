package es.bryle.digital.profesional.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Car;
import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.repository.CarRepository;

@Service
public class CarMapper {

	@Autowired
	private CarRepository carRepository;
	
	public Car mapper(CarVO source) {
		return mapper(source, new Car());
	}

	public Car mapper(CarVO source, Car target) {
		
		if(source.getNumBastidor()!= null) {
			if(carRepository.findByNumBastidor(source.getNumBastidor())== null)
				target.setNumBastidor(source.getNumBastidor());
		}
		
		if(source.getMarca()!= null)
			target.setMarca(source.getMarca());
		
		if(source.getModelo()!= null)
			target.setModelo(source.getModelo());
		
		if(source.getEstado()!= null)
			target.setEstado(source.getEstado());
		
		if(source.getAnyo()!= null)
			target.setAnyo(source.getAnyo());
		
		if(source.getPrecio()!= null)
			target.setPrecio(source.getPrecio());
		
		return target;
	}
	
}//class
