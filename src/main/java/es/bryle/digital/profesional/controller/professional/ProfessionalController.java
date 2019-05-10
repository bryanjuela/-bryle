package es.bryle.digital.profesional.controller.professional;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.service.interfaces.AdminService;
import es.bryle.digital.profesional.service.interfaces.SalesService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/controller/professional-operations")
public class ProfessionalController {

	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value = "Recuperación de todos los profesionales",
			notes = "Recupera un listado con todos los profesionales de la BD")
	@RequestMapping(value = "/professional-list", method= RequestMethod.GET)
	public String getProfessionals(Map<String, Object> model){
		List<ProfessionalVO> professionals= adminService.getProfessionals();
		if(!professionals.isEmpty() || professionals!= null) {
			model.put("professionals", professionals);
			System.out.println("professional-list GET");
		}	
		return "/admin";
	}
	
	@ApiOperation(value = "Formulario de profesional",
			notes = "Redirecciona a la vista para crear un nuevo profesional")
	@RequestMapping(value = "/create-comercial", method= RequestMethod.GET)
	public String createProfessional(Map<String, Object> model) {
		ProfessionalVO professionalVO= new ProfessionalVO();
		model.put("professionalVO", professionalVO);
		return "/comercial";
	}
	
	@ApiOperation(value = "Creación de un profesional",
			notes = "Crear un usuario con el rol de 'USER'")
	@RequestMapping(value = "/professional", method= RequestMethod.POST)
	public String createProfessional(@Valid ProfessionalVO professionalVO, 
			final BindingResult bindingResult){
		
		if(!bindingResult.hasErrors()) {
			if(professionalVO== null){
				return "redirect:/controller/professional-operations/create-comercial";
			}
			System.out.println("professional POST");
			Integer result= adminService.createProfessional(professionalVO);
			
			if(result== 1)
				return "redirect:/controller/professional-operations/professional-list";
			if(result== -1 || result== -2)
				return "redirect:/controller/professional-operations/create-comercial";
		}
		
		return "redirect:/controller/professional-operations/create-comercial";
	}
	
	@ApiOperation(value = "Eliminacion de un profesional",
			notes = "Elimina al profesional y todas sus ventas de la BD ")
	@RequestMapping(value = "/delete-professional/{id}")
	public String deleteProfessional(@PathVariable("id")Long id){
		
		if(id!= null && id> 0) {
			Integer result= adminService.deleteProfessional(id);
			if(result== 1)
				return "redirect:/controller/professional-operations/professional-list";
			/* PAGINA DE ERROR
			 * if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			if(result== -2)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
		}
		return "redirect:/controller/professional-operations/professional-list";
	}
}
