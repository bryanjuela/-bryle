package es.bryle.digital.profesional.controller.auth;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;

@Controller
//@RequestMapping("/controller/user-operations")
public class AuthUserController {

	@ApiOperation(value = "Login de usuario", 
			notes = "Si no está logeado, manda a la página de login")
	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public String login(Principal principal) {
		System.out.println("login adfsadfd");
		
		if(principal!= null) {
			System.out.println("Principal: "+principal.getName());
			return "redirect:/controller/professional-operations/professional-list";
		}	
		
		System.out.println("nada");
		return "login";
	}
	
	
}
