package com.kufar.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kufar.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UserDetailsImpl(Long id, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(UserEntity user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
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
