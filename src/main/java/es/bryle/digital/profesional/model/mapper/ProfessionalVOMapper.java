package es.bryle.digital.profesional.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;

@Service
public class ProfessionalVOMapper {

	@Autowired
	private SaleVOMapper saleVOMapper;
	
	public ProfessionalVO mapper(Professional source) {
		return mapper(source, new ProfessionalVO());
	}

	public ProfessionalVO mapper(Professional source, ProfessionalVO target) {
		
		if(source.getId()!= null)
			target.setId(source.getId());
		
		if(source.getDni()!= null)
			target.setDni(source.getDni());
		
		if(source.getFirstName()!= null)
			target.setFirstName(source.getFirstName());
		
		if(source.getLastName()!= null)
			target.setLastName(source.getLastName());
		
		if(source.getUser()!= null)
			target.setUser(source.getUser().getEmail());
		
		if(source.getSales()!= null) {
			source.getSales().forEach(element-> {
				target.getSales().add(saleVOMapper.mapper(element));
			});
		}
			
		return target;
	}
	
}//class
