package com.kb.chess.server;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kb.chess.server.database.User;
import com.kb.chess.server.database.UserRepository;

@RestController
@RequestMapping(consumes="application/json")
public class AuthenticationController {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
}
