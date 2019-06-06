package es.bryle.digital.profesional.model.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Car;
import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.Sale;
import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.vo.SaleVO;
import es.bryle.digital.profesional.repository.CarRepository;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;

@Service
public class SaleMapper {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ProfessionaRepository professionalRepository;
	@Autowired
	private AuthUserService authUserService;
	
	public Sale mapper(SaleVO source) {
		return mapper (source, new Sale());
	}

	public  Sale mapper(SaleVO source, Sale target) {
		if(source.getSaleDate()!= null) {
			try {
				String[] time= source.getSaleDate().split("-");
				String date= time[2]+"-"+time[1]+"-"+time[0];
				Date newDate= new SimpleDateFormat("dd-MM-yyyy").parse(date);
				target.setSaleDate(newDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}	
		
		if(source.getCar()!= null) {
			Optional<Car> car= carRepository.findById(Long.parseLong(source.getCar())); 
			if(car.isPresent()) {
				target.setCar(car.get());
			}else 
				return null;	
		}
		
		if(source.getProfessional()== null) {
			User user= authUserService.getCurrentUser();
			if(user!= null) {
				Optional<Professional> professional= professionalRepository.findById(user.getProfessional().getId());
				if(professional.isPresent()) {
					target.setProfessional(professional.get());
				}
			}
			
		}else {
			target.setProfessional(professionalRepository.findById(Long.parseLong(source.getProfessional())).get());
		}
		
		if(source.getPrice()!= null)
			target.setPrice(source.getPrice());
		
		return target;
	}
	
}
