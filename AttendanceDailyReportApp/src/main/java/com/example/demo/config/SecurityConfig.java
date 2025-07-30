package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.CustomLoginSuccessHandler;
import com.example.demo.servic.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/login", "/css/**", "/js/**").permitAll()	// ログイン画面は誰でもOK
					.anyRequest().authenticated()
					)
			.formLogin(form -> form
					.loginPage("/login")	// ログイン画面のパス
	                .successHandler(customLoginSuccessHandler) 
					.permitAll()
			)
			.logout(logout -> logout
					.logoutSuccessUrl("/login?logout")	// ログアウト後
					.permitAll()
			);
			
		return http.build();

	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
