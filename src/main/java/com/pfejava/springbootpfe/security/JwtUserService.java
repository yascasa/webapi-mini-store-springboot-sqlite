package com.pfejava.springbootpfe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.repositories.UserRepository;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public AppUser loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser user = this.userRepository.findByUserName(userName);
		return user;
	}

}
