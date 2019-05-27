package es.bryle.digital.profesional.model.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.Sale;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.model.vo.SaleVO;

@Service
public class ProfessionalVOMapper {

	@Autowired
	private SaleVOMapper saleVOMapper;
	
	public ProfessionalVO mapper(Professional source) {
		return mapper(source, new ProfessionalVO());
	}

	@SuppressWarnings("null")
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
		
		if(source.getSales()!= null && !source.getSales().isEmpty()) {
			Set<SaleVO> saleList = new HashSet<>();
			for(Sale element: source.getSales()) {
				//target.getSales().add(saleVOMapper.mapper(element));
				saleList.add(saleVOMapper.mapper(element));
			}
			target.setSales(saleList);
		}
		
		if(source.getAddress()!= null)
			target.setAddress(source.getAddress());
		
		if(source.getPhone()!= null)
			target.setPhone(source.getPhone());
			
		return target;
	}
	
}//class
