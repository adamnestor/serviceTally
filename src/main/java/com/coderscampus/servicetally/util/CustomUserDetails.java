package com.coderscampus.servicetally.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.domain.UsersType;

public class CustomUserDetails implements UserDetails {

	private Users user;

	public CustomUserDetails(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UsersType usersType = user.getUserTypeId();
		List<SimpleGrantedAuthority> authorites = new ArrayList<>();
		authorites.add(new SimpleGrantedAuthority(usersType.getUserTypeName()));
		return authorites;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
