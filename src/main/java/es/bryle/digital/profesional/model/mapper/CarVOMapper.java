package es.bryle.digital.profesional.model.mapper;

import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Car;
import es.bryle.digital.profesional.model.vo.CarVO;

@Service
public class CarVOMapper {

	public CarVO mapper(Car source) {
		return mapper(source, new CarVO());
	}

	public CarVO mapper(Car source, CarVO target) {
		
		if(source.getId()!= null)
			target.setId(source.getId());
		
		if(source.getMarca()!= null)
			target.setMarca(source.getMarca());
		
		if(source.getModelo()!= null)
			target.setModelo(source.getModelo());
		
		if(source.getNumBastidor()!= null)
			target.setNumBastidor(source.getNumBastidor());
		
		if(source.getEstado()!= null)
			target.setEstado(source.getEstado());
		
		if(source.getAnyo()!= null)
			target.setAnyo(source.getAnyo());
		
		if(source.getPrecio()!= null)
			target.setPrecio(source.getPrecio());
		
		if(source.getKm()!= null)
			target.setKm(source.getKm());
		
		return target;
	}
}//class
