package com.kb.chess.server;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kb.chess.server.database.User;
import com.kb.chess.server.database.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping()
	public RegistrationStatus processRegistration(@RequestBody @Valid RegistrationForm registrationForm,
			Errors errors) {
		
		if(errors.hasErrors()) {
			return RegistrationStatus.ERROR;
		}
		
		Optional<User> optUser = userRepository.findById(registrationForm.getEmail());
		if(optUser.isPresent()) {
			return RegistrationStatus.EXISTING_ACCOUNT;
		}
			userRepository.save(registrationForm.toUser(passwordEncoder));
			return RegistrationStatus.SUCCESS;
			
	}
}
