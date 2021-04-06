package com.pfejava.springbootpfe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pfejava.springbootpfe.common.RoleType;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtUserService userService;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	private final String[] PUBLIC_ENDPOINTS = { "/public/**", };
	private final String[] ADMIN_ENDPOINTS = { "/admin/**", };
	private final String[] USER_ENDPOINTS = { "/user/**", };

	@Bean
	AuthFilter authFilter() {
		return new AuthFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.antMatchers(PUBLIC_ENDPOINTS).permitAll()
				.antMatchers(ADMIN_ENDPOINTS).hasAuthority(RoleType.Names.ADMIN)
				.antMatchers(USER_ENDPOINTS).hasAuthority(RoleType.Names.USER)
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}