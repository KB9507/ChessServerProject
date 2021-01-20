package com.kb.chess.server;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.kb.chess.server.database.User;

public class RegistrationForm {
	
	@Id
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=1, max=20)
	private String nickname;
	
	@NotBlank
	@Size(min=6)
	private String password;
	
	public RegistrationForm(String email, String nickname, String password) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(email, nickname, passwordEncoder.encode(password));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
