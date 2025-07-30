package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class CustomUserDetails implements UserDetails {
	
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user= user;
	}
	
	public User getUser() {
		return user;
	}
	
	//ログイン情報
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	//ログイン情報
	public String getLoginId() {
		return user.getLoginId();
	}
	
	//ユーザーの名前、アプリ内の表示に使うかも
	public String getUsername() {
		return user.getName();
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	}

}
