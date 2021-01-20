package com.kb.chess.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kb.chess.server.database.User;
import com.kb.chess.server.database.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user != null) { return user; }
		throw new UsernameNotFoundException("Podany login nie istnieje");
	}

}
