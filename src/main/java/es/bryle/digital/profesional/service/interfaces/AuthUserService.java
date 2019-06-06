package es.bryle.digital.profesional.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.entities.auth.User;

@Service("authUserService")
public interface AuthUserService extends UserDetailsService{

	String resetPassword(String email);
	
	Boolean changePassword(String newPass);
	
	User getCurrentUser();
	
	Boolean isEqualRolCurrentUser(String role);
}
