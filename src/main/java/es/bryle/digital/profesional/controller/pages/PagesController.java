package es.bryle.digital.profesional.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;
import io.swagger.annotations.ApiOperation;

@Controller
public class PagesController {

	@Autowired
	private AuthUserService authUserService;
	
	@ApiOperation(value = "Vista de bonus",
			notes = "Muestra la vista de bonus, que por el momento está en construcción")
	@RequestMapping(value = "/bonus", method= RequestMethod.GET)
	public String bonus(Model model) {
		model.addAttribute("redirectPage", "/home");
		return "/building";
	}
	
	@ApiOperation(value = "Vista principal de la página",
			notes = "Muestra la vista principal de página al entrar")
	@RequestMapping(value = "/home", method= RequestMethod.GET)
	public String homePage(Model model) {
		User user= authUserService.getCurrentUser();
		model.addAttribute("tabFragment", "us");
		model.addAttribute("nombre", user.getProfessional().getFirstName());
		return "/index";
	}
}
