package es.bryle.digital.profesional.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.service.interfaces.AdminService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/controller/admin-profile")
public class AdminController {

	//private static final String ROLE_USER= "USER";
	//private static final String ROLE_ADMIN= "ADMIN";
	
	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value = "Cracion de un profesional",
			notes = "Crear un usuario con el rol de 'USER'")
	@RequestMapping(value = "/professional", method= RequestMethod.POST)
	public ResponseEntity<?> createProfessional(@RequestBody ProfessionalVO professionalVO, 
			final BindingResult bindingResult){
		
		if(!bindingResult.hasErrors()) {
			if(professionalVO== null){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Integer result= adminService.createProfessional(professionalVO);
			
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.CREATED);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ApiOperation(value = "Recuperación de un profesional",
			notes = "Recupera un profesional de la BD con el id correspondiente")
	@RequestMapping(value = "/professional/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> getProfessional(@PathVariable("id") Long id){
		if(id!= null && id> 0) {
			ProfessionalVO professional= adminService.getProfessional(id);
			if(professional!= null)
				return new ResponseEntity<>(professional, HttpStatus.OK);
			else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "Recuperación de todos los profesionales",
			notes = "Recupera un listado con todos los profesionales de la BD")
	@RequestMapping(value = "/professional", method= RequestMethod.GET)
	public ResponseEntity<?> getProfessionals(){
		List<ProfessionalVO> professionals= adminService.getProfessionals();
		if(professionals.isEmpty() || professionals== null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(professionals, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Eliminacion de un profesional",
			notes = "Elimina al profesional y todas sus ventas de la BD ")
	@RequestMapping(value = "/professional/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteProfessional(@PathVariable("id")Long id){
		
		if(id!= null && id> 0) {
			Integer result= adminService.deleteProfessional(id);
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.OK);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ApiOperation(value = "Edición de un profesional",
			notes = "Edita el profesional siempre que exista en la BD")
	@RequestMapping(value = "/professional", method= RequestMethod.PUT)
	public ResponseEntity<?> editProfessional(@RequestBody ProfessionalVO professionalVO){
		
		if(professionalVO!= null) {
			Integer result= adminService.editProfessional(professionalVO);
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.OK);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ApiOperation(value = "Creación de un nuevo coche",
			notes = "Crea un nuevo coche si no existe ya en la BD")
	@RequestMapping(value = "/car", method= RequestMethod.POST)
	public ResponseEntity<?> crateCar(@RequestBody CarVO carVO){
		
		if(carVO!= null) {
			Integer result= adminService.createCar(carVO);
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.OK);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.FOUND);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ApiOperation(value = "Eliminacion de un coche",
			notes = "Elimina el coche de la BD ")
	@RequestMapping(value = "/car/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteCar(@PathVariable("id")Long id){
		
		if(id!= null && id> 0) {
			Integer result= adminService.deleteCar(id);
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.OK);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ApiOperation(value = "Recuperación de todos los coches",
			notes = "Recupera un listado con todos los coches de la BD")
	@RequestMapping(value = "/car", method= RequestMethod.GET)
	public ResponseEntity<?> getCars(){
		List<CarVO> cars= adminService.getCars();
		if(cars.isEmpty() || cars== null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Recuperación de un coche",
			notes = "Recupera un coche de la BD con el id correspondiente")
	@RequestMapping(value = "/car/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> getCar(@PathVariable("id") Long id){
		if(id!= null && id> 0) {
			CarVO car= adminService.getCar(id);
			if(car!= null)
				return new ResponseEntity<>(car, HttpStatus.OK);
			else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@ApiOperation(value = "Edición de un coche",
			notes = "Edita el coche siempre que exista en la BD")
	@RequestMapping(value = "/car", method= RequestMethod.PUT)
	public ResponseEntity<?> editCar(@RequestBody CarVO carVO){
		
		if(carVO!= null) {
			Integer result= adminService.editCar(carVO);
			if(result== 1)
				return new ResponseEntity<>(HttpStatus.OK);
			if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}//class
