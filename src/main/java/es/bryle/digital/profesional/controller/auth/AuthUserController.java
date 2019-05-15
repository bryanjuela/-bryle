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

	@ApiOperation(value = "", 
			notes = "")
	//@RequestMapping(value= "/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public String login(@RequestParam(value= "error", required= false) String error, 
			@RequestParam(value= "error", required= false) String logout, 
			Model model, Principal principal, Authentication authentication) {
		System.out.println("login adfsadfd");
		
		if(authentication!= null)
			System.out.println(authentication.getName()+" "+authentication.getPrincipal());
		
		if(principal!= null) {
			System.out.println("Principal: "+principal.getName());
			return "redirect:/controller/professional-operations/professional-list";
		}	
		
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		
		System.out.println("nada");
		return "/login";
	}
	
	
}
