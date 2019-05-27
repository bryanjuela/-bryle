package es.bryle.digital.profesional.controller.auth;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.bryle.digital.profesional.service.interfaces.AuthUserService;
import io.swagger.annotations.ApiOperation;

@Controller
//@RequestMapping("/controller/user-operations")
public class AuthUserController {

	private static final String REDIRECT= "redirect:";
	private static final String ROOT_PATH= "/controller/user-operations";
	
	@Autowired
	private AuthUserService authUserService;
	
	@ApiOperation(value = "Login de usuario", 
			notes = "Si no está logeado, manda a la página de login")
	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public String login(Authentication principal) {
		System.out.println("login adfsadfd");
		
		if(principal!= null) {
			System.out.println("Principal: "+principal.getName());
			return REDIRECT+"/controller/professional-operations/professional-list";
		}	
		
		System.out.println("nada");
		return "login";
	}
	
	
	@ApiOperation(value = "Logout de usuario", 
			notes = "Cierra la sesión y vuelve a la vista de login")
	@RequestMapping(value= "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return REDIRECT+"/login";
	}
	
	
	@ApiOperation(value = "Vista de recuperar contraseña", 
			notes = "Redirecciona a la vista de recuperar contraseña")
	@RequestMapping(value= "/reset-password", method = RequestMethod.GET)
	public String resetPassword() {
		return "/recuperar";
	}
	
}
