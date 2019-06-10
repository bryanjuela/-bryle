package es.bryle.digital.profesional.controller.professional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.repository.ProfessionaRepository;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;
import es.bryle.digital.profesional.service.interfaces.ProfessionalService;
import es.bryle.digital.profesional.service.interfaces.SalesService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/controller/professional-operations")
public class ProfessionalController {

	@Autowired
	private ProfessionalService professionalService;
	@Autowired
	private AuthUserService authUserService;
	
	
	private static final String REDIRECT= "redirect:";
	private static final String ROOT_PATH= "/controller/professional-operations";
	private static final String ROLE_PROFESSIONAL= "professional";
	private static final String ROLE_ADMIN= "admin";
	
	@ApiOperation(value = "Recuperación de todos los profesionales",
			notes = "Recupera un listado con todos los profesionales de la BD")
	@RequestMapping(value = "/professional-list", method= RequestMethod.GET)
	public String getProfessionals(Map<String, Object> model, HttpServletRequest request){
		List<ProfessionalVO> professionals= professionalService.getProfessionals();
		List<ProfessionalVO> totalProfessional= new ArrayList<ProfessionalVO>();
		User user= authUserService.getCurrentUser();
		
		if(authUserService.isEqualRolCurrentUser(ROLE_ADMIN)) {
			for(int i= 0; i< professionals.size(); i++) {
				ProfessionalVO element= professionals.get(i);
				if(element.getUser()!= user.getEmail())
					totalProfessional.add(element);
			}
			professionals.clear();
			professionals.addAll(totalProfessional);
		}
		
		
		if(professionals== null) 
			professionals= new ArrayList<ProfessionalVO>();
		
		
		model.put("role", user.getRoles().get(0).getType());
		model.put("nombre", user.getProfessional().getFirstName());
		model.put("professionals", professionals);
		model.put("createButton", ROOT_PATH+"/create-comercial");
		model.put("tabFragment", "profesionales");
		return "/index";
	}
	
	@ApiOperation(value = "Formulario de profesional",
			notes = "Redirecciona a la vista para crear un nuevo profesional")
	@RequestMapping(value = "/create-comercial", method= RequestMethod.GET)
	public String newProfessional(Map<String, Object> model, RedirectAttributes flash) {
		if(authUserService.isEqualRolCurrentUser(ROLE_ADMIN)) {
			ProfessionalVO professionalVO= new ProfessionalVO();
			model.put("professionalVO", professionalVO);
			model.put("titulo", "Nuevo Comercial");
			return "/comercial";
		}else {
			flash.addFlashAttribute("error", "Lo sentimos, no tienes permisos para crear un profesional.");
			return REDIRECT+ROOT_PATH+"/professional-list";
		}
	}
	
	@ApiOperation(value = "Creación o edición de un profesional",
			notes = "Crear o edita un usuario con el rol de 'USER'")
	@RequestMapping(value = "/professional")
	public String createProfessional(@Valid ProfessionalVO professionalVO, 
			final BindingResult bindingResult, Model model, RedirectAttributes flash){
		
		if(!bindingResult.hasErrors()) {
			if(professionalVO== null){
				return "redirect:/controller/professional-operations/create-comercial";
			}
			System.out.println("professional POST");
			Integer result;
			String redirectPage;
			
			if(professionalVO.getId()== null) { 
				result= professionalService.createProfessional(professionalVO);
				redirectPage="/create-comercial";
				System.out.println("Nuevo"+result);
			}else { 
				result= professionalService.editProfessional(professionalVO);
				redirectPage="/edit-professional/"+professionalVO.getId();
				System.out.println("Editar"+result);
			}	
				
			if(result== 1) //todo correcto
				return REDIRECT+ROOT_PATH+ "/professional-list";
			if(result== -1 || result== -2) {//volver a cargar la página
				flash.addFlashAttribute("warning", "El email o dni ya existe");
				return REDIRECT+ROOT_PATH+ redirectPage;
			}	
		}
		
		model.addAttribute("mensaje", "PROFESSIONAL ERROR");
		model.addAttribute("redirectPage", ROOT_PATH+"/professional-list");
		return "/error_404";
	}
	
	@ApiOperation(value = "Eliminacion de un profesional",
			notes = "Elimina al profesional y todas sus ventas de la BD ")
	@RequestMapping(value = "/delete-professional/{id}")
	public String deleteProfessional(@PathVariable("id")Long id, Model model, RedirectAttributes flash){
		if(authUserService.isEqualRolCurrentUser(ROLE_ADMIN)) {
			if(id!= null && id> 0) {
				Integer result= professionalService.deleteProfessional(id);
				if(result== 1)
					return  REDIRECT+ROOT_PATH+"/professional-list";
				//PAGINA DE ERROR
				if(result== -1 || result== -2) {//volver a cargar la página
					 model.addAttribute("mensaje", "PROFESSIONAL ID NOT FOUND");
					 model.addAttribute("redirectPage", ROOT_PATH+"/professional-list");
					return "/error_404";
				}	
			}
			return  REDIRECT+ROOT_PATH+"/professional-list";
		}
		flash.addFlashAttribute("error", "Lo sentimos, no tienes permisos para eliminar un profesional");
		return  REDIRECT+ROOT_PATH+"/professional-list";
	}
	
	@ApiOperation(value = "Edición de un profesional",
			notes = "Redirecciona a la vista del comercial con los datos del comercial seleccionado")
	@RequestMapping(value = "/edit-professional/{id}")
	public String editProfessional(@PathVariable("id")Long id, Model model, RedirectAttributes flash){
		
		if(authUserService.isEqualRolCurrentUser(ROLE_ADMIN)) {
			if(id!= null && id> 0) {
				ProfessionalVO professionalVO= professionalService.getProfessional(id);
				if(professionalVO!= null) {
					model.addAttribute("professionalVO", professionalVO);
					model.addAttribute("titulo", "Editar Comercial");
					return "/comercial";
				}
			}
			 model.addAttribute("mensaje", "PROFESSIONAL ID NOT FOUND");
			 model.addAttribute("redirectPage", ROOT_PATH+"/professional-list");
			return "/error_404";
		}
		flash.addFlashAttribute("error", "Lo sentimos, no tienes permisos para editar un profesional");
		return  REDIRECT+ROOT_PATH+"/professional-list";
	}
}
