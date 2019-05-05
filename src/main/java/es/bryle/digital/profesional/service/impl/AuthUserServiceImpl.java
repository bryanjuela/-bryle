package es.bryle.digital.profesional.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.repository.UserRepository;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;

@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepository.findByEmail(username);
		if(user== null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	@Override
	public String resetPassword(String email) {
		return null;
	}

	@Override
	public Boolean changePassword(String newPass) {
		return null;
	}

	@Override
	public User getCurrentUser() {
		return null;
	}

	@Override
	public Boolean isEqualRolCurrentUser(String role) {
		return null;
	}

}
