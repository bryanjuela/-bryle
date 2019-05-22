package es.bryle.digital.profesional.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.Car;
import es.bryle.digital.profesional.model.mapper.CarMapper;
import es.bryle.digital.profesional.model.mapper.CarVOMapper;
import es.bryle.digital.profesional.model.mapper.ProfessionalMapper;
import es.bryle.digital.profesional.model.mapper.ProfessionalVOMapper;
import es.bryle.digital.profesional.model.mapper.SaleVOMapper;
import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.SaleVO;
import es.bryle.digital.profesional.repository.AuthorityRepository;
import es.bryle.digital.profesional.repository.CarRepository;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.repository.RoleRepository;
import es.bryle.digital.profesional.repository.SaleRepository;
import es.bryle.digital.profesional.repository.UserRepository;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;
import es.bryle.digital.profesional.service.interfaces.SalesService;

@Service("salesService")
public class SalesServiceImpl implements SalesService {

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
	private CarMapper carMapper;
	@Autowired
	private CarVOMapper carVOMapper;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private ProfessionalMapper professionalMapper;
	@Autowired
	private ProfessionalVOMapper professionalVOMapper;
	@Autowired
	private SaleVOMapper saleVOMapper;
	
	@Override
	public Integer createSale(SaleVO saleVO) {
		return null;
	}

	@Override
	public Integer deleteSale(Long id) {
		return null;
	}

	@Override
	public Integer editSale(SaleVO saleVO) {
		return null;
	}

	@Override
	public List<SaleVO> getSales() {
		return null;
	}

	@Override
	public SaleVO getOneSale(Long id) {
		return null;
	}
//--------------------------------------------------------------
	@Override
	public Integer createCar(CarVO carVO) {
		String bastidor= carVO.getNumBastidor();
		if(bastidor!= null && bastidor.trim().length()> 0) {
			Car existCar= carRepository.findByNumBastidor(bastidor);
			if(existCar== null) {
				Car newCar= carMapper.mapper(carVO);
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
	public CarVO getOneCar(Long id) {
		Optional<Car> car= carRepository.findById(id);
		if(car.isPresent()) {
			return carVOMapper.mapper(car.get());
		}
		return null;
	}

}
