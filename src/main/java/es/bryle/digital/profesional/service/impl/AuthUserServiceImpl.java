package es.bryle.digital.profesional.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.repository.UserRepository;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;

@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static final String PASSSWORD_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%&*-_=+?";
	
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
		User user= userRepository.findByEmail(email);
		if(user== null)
			throw new UsernameNotFoundException(email);
		return generatePassword(user);
	}

	private String generatePassword(User user) {
		String password = RandomStringUtils.random(8, PASSSWORD_CHARACTERS);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(user);
		return password;
	}

	@Override
	public Boolean changePassword(String newPass) {
		return null;
	}

	@Override
	public User getCurrentUser() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		return (User) auth.getPrincipal();
	}

	@Override
	public Boolean isEqualRolCurrentUser(String role) {
		return null;
	}

	@Override
	public Object getCurrentUser1() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		return auth.getPrincipal();
	}

}
