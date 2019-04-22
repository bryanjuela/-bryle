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
		
		return null;
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
		return new ResponseEntity<>(HttpStatus.SEE_OTHER);
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
		return new ResponseEntity<>(HttpStatus.SEE_OTHER);
	}
}//class
