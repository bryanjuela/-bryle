package es.bryle.digital.profesional.model.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.repository.UserRepository;

@Service
public class ProfessionalMapper {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfessionaRepository professionalRepository;
	@Autowired
	private SaleMapper saleMapper;
	
	public Professional mapper(ProfessionalVO source) {
		return mapper(source, new Professional());
	}

	public Professional mapper(ProfessionalVO source, Professional target) {
		
			if(source.getDni()!= null) {
				if(professionalRepository.findByDni(source.getDni())== null)
					target.setDni(source.getDni());
			}
			
			if(source.getFirstName()!= null)
				target.setFirstName(source.getFirstName());
			
			if(source.getLastName()!= null)
				target.setLastName(source.getLastName());
			
			if(source.getUser()!= null) {
				User user= userRepository.findByEmail(source.getUser());
				if(user!= null && !user.getId().equals(target.getUser().getId()))
					return null;
					
				if(target.getUser()!= null)
					target.getUser().setEmail(source.getUser());
			}
			
			if(source.getSales()!= null && !source.getSales().isEmpty()) {
				source.getSales().forEach(element-> {
					target.getSales().add(saleMapper.mapper(element));
				});
			}
		
		return target;
	}
}
