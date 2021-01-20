package com.kb.chess.server.database;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
	
	private static final long serialVersionUID = 2839146209407086701L;

	@Id
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=1, max=20)
	private String nickname;
	
	@NotBlank
	@Column(length=60)
	@Size(min=6)
	private String password;
	
	private User() {}
	
	public User(String email, String nickname, String password) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User: " + email + nickname + password;
	}
	

	public String getEmail() {
		return email;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
