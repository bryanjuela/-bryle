package es.bryle.digital.profesional.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Car;
import es.bryle.digital.profesional.model.entities.Professional;
import es.bryle.digital.profesional.model.entities.Sale;
import es.bryle.digital.profesional.model.entities.auth.Authority;
import es.bryle.digital.profesional.model.entities.auth.Role;
import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.mapper.CarMapper;
import es.bryle.digital.profesional.model.mapper.CarVOMapper;
import es.bryle.digital.profesional.model.mapper.ProfessionalMapper;
import es.bryle.digital.profesional.model.mapper.ProfessionalVOMapper;
import es.bryle.digital.profesional.model.mapper.SaleVOMapper;
import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.model.vo.SaleVO;
import es.bryle.digital.profesional.repository.AuthorityRepository;
import es.bryle.digital.profesional.repository.CarRepository;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.repository.RoleRepository;
import es.bryle.digital.profesional.repository.SaleRepository;
import es.bryle.digital.profesional.repository.UserRepository;
import es.bryle.digital.profesional.service.interfaces.ProfessionalService;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;

@Service("adminService")
public class ProfessionalServiceImpl implements ProfessionalService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProfessionaRepository professionalRepository;
	@Autowired 
	private CarRepository carRepository;
	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private CarVOMapper carVOMapper;
	@Autowired
	private ProfessionalMapper professionalMapper;
	@Autowired
	private ProfessionalVOMapper professionalVOMapper;
	@Autowired
	private SaleVOMapper saleVOMapper;
	
	private static final String ROLE_PROFESSIONAL= "professional";
	private static final String ROLE_ADMIN= "admin";
	private static final String ALL_AUTHORITY= "all";
	private static final String RESTRICTED_AUTHORITY= "restricted";
	
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
				Role rol=  roleRepository.findByType(ROLE_PROFESSIONAL); 
				List<Role> roles= new ArrayList<>();
				roles.add(rol);
				
				Set<Authority> authorities= authorityRepository.findByName(RESTRICTED_AUTHORITY);
				
				User user = new User();
				user.setEmail(email);
				//user.setPassword("12345");
				user.setProfessional(professional);
				user.setRoles(roles);
				user.setAuthorities(authorities);
				professional.setUser(user);
				
				professionalRepository.save(professional);
				userRepository.save(user);
				
				String pass= authUserService.resetPassword(email);
				System.out.println(pass);
				
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
			
		}
		
		
		return totalProfessionals;
	}

	@Override
	public Integer deleteProfessional(Long id) {
		
		//comprobar que el id no sea nulo
		if(id!= null && id> 0) {
			Optional<Professional> professional= professionalRepository.findById(id);
			
			//si existe el profesional, eliminarlo
			if(professional.isPresent()) {
				professionalRepository.deleteById(id);
				
				return 1;
			}
			return -1;
		}
		return -2;
	}

	@Override
	public Integer editProfessional(ProfessionalVO professionalVO) {
		Optional<Professional> professional= professionalRepository.findById(professionalVO.getId());
		//Professional professional= professionalRepository.findByUser(professionalVO.getUser());
		//comprobar si existe en la BD
		if(professional!= null) {
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

	@Override
	public ProfessionalVO getProfessional(Long id) {
		Optional<Professional> professional= professionalRepository.findById(id);
		if(professional.isPresent()) {
			ProfessionalVO psVO= professionalVOMapper.mapper(professional.get());
			return psVO;
		}
		
		return null;
	}

	/*@Override
	public List<CarVO> getCars() {
		List<Car> carList= carRepository.findAll();
		List<CarVO> totalCars= new ArrayList<>();
		
		if(!carList.isEmpty()) {
			for(Car car: carList) 
				totalCars.add(carVOMapper.mapper(car));
			return totalCars;
		}
		
		return null;
	}

	@Override
	public CarVO getCar(Long id) {
		Optional<Car> car= carRepository.findById(id);
		if(car.isPresent()) {
			return carVOMapper.mapper(car.get());
		}
		return null;
	}

	@Override
	public Integer createCar(CarVO car) {
		String bastidor= car.getNumBastidor();
		if(bastidor!= null && bastidor.trim().length()> 0) {
			Car existCar= carRepository.findByNumBastidor(bastidor);
			if(existCar== null) {
				Car newCar= carMapper.mapper(car);
				if(newCar!= null) {
					carRepository.save(newCar);
					return 1;
				}
			}
			return -1;
		}
		
		return -2;
	}

	@Override
	public Integer deleteCar(Long id) {
		Optional<Car> existCar= carRepository.findById(id);
		if(existCar.isPresent()) {
			carRepository.deleteById(id);
			return 1;
		}
			
		return -1;
	}

	@Override
	public Integer editCar(CarVO carVO) {
		Optional<Car> car= carRepository.findById(carVO.getId());
		if(car.isPresent()) {
			Car existBastidor= carRepository.findByNumBastidor(carVO.getNumBastidor());
			
			if(existBastidor!= null ) {
				Car newCar= carMapper.mapper(carVO, car.get());
				if(newCar!= null) {
					carRepository.save(newCar);
					return 1;
				}
				return -1;
			}
			return -2;
		}
		return -1;
	}

	@Override
	public List<SaleVO> getSales() {
		List<Sale> saleList= saleRepository.findAll();
		List<SaleVO> totalSales= new ArrayList<>();
		
		if(!saleList.isEmpty()) {
			for(Sale sale: saleList) {
				totalSales.add(saleVOMapper.mapper(sale));
			}
			return totalSales;
		}
		
		return null;
	}*/

}//class
