package es.bryle.digital.profesional.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.auth.Role;
import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.mapper.ProfessionalMapper;
import es.bryle.digital.profesional.model.mapper.ProfessionalVOMapper;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.repository.RoleRepository;
import es.bryle.digital.profesional.repository.UserRepository;
import es.bryle.digital.profesional.service.interfaces.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProfessionaRepository professionalRepository;
	@Autowired
	private ProfessionalMapper professionalMapper;
	@Autowired
	private ProfessionalVOMapper professionalVOMapper;
	
	private static final String ROLE_USER= "USER";
	
	@Override
	public Integer createProfessional(ProfessionalVO professionalVO) {
		String email= professionalVO.getUser();
		String dni= professionalVO.getDni();
		
		if(email== null || email.trim().isEmpty() || 
			dni== null || dni.trim().isEmpty()){
			return -1;
		} else {
			//comprobar que no haya el mismo email en la BD
			User existEmail= userRepository.findByEmail(email);
			Professional existDni=professionalRepository.findByDni(dni);
			
			if(existEmail== null && existDni== null) {
				//mapear el objeto
				Professional professional= professionalMapper.mapper(professionalVO);
				Role rol=  roleRepository.findByType(ROLE_USER); 
				List<Role> roles= new ArrayList<>();
				roles.add(rol);
				
				User user = new User();
				user.setEmail(email);
				user.setContraseña("12345");
				user.setProfessional(professional);
				user.setRoles(roles);
				
				professionalRepository.save(professional);
				userRepository.save(user);
				return 1;
			}
			
			return -2;
				
		}
		
	}

	@Override
	public List<ProfessionalVO> getProfessionals() {
		
		List<Professional> professionalList= professionalRepository.findAll();
		List<ProfessionalVO> totalProfessionals= new ArrayList<>();
		
		if(!professionalList.isEmpty()) {
			for(Professional element: professionalList) {
				ProfessionalVO professional= professionalVOMapper.mapper(element);
				totalProfessionals.add(professional);
			}
			return totalProfessionals;
		}
		
		
		return null;
	}

	@Override
	public Integer deleteProfessional(Long id) {
		
		//comprobar que el id no sea nulo
		if(id!= null && id> 0) {
			Optional<Professional> professional= professionalRepository.findById(id);
			
			//si existe el profesional, eliminarlo
			if(professional.isPresent()) {
				professionalRepository.deleteById(id);
				userRepository.deleteById(professional.get().getUser().getId());
				return 1;
			}
			return -1;
		}
		
		return -2;
	}

	@Override
	public Integer editProfessional(ProfessionalVO professionalVO) {
		Optional<Professional> professional= professionalRepository.findById(professionalVO.getId());
		
		//comprobar si existe en la BD
		if(professional.isPresent()) {
			String email= professionalVO.getUser();
			String dni= professionalVO.getDni();
			
			Professional existDni= professionalRepository.findByDni(dni);
			User existEmail= userRepository.findByEmail(email);
			
			if(existDni!= null && existEmail!= null) {
				Professional newProfessional= professionalMapper.mapper(professionalVO, professional.get());
				if(newProfessional!= null) {
					professionalRepository.save(newProfessional);
					return 1;
				}
				return -1;
			}
			
			return -2;
		}
		
		return -1;
	}

}//class
