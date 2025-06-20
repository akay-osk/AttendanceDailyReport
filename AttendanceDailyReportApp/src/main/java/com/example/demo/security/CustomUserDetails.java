package com.example.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

//UserDetails : Spring Security がユーザー情報を扱うための標準インターフェース
//CustomUserDetails : 自作の User エンティティを UserDetails に適合させるラッパー
public class CustomUserDetails implements UserDetails {
	
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user= user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));		//ここ修正必要かもしれない
	}

	@Override
	public String getPassword() {
		return user.getPassword();		//DBのpassword(ハッシュ化済)
	}

	@Override
	public String getUsername() {
		return user.getUsername();		//DBのusername
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
