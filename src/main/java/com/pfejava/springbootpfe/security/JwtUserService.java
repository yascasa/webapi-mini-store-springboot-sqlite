package com.pfejava.springbootpfe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	
	public AppUser loadUserByUsernameAndPassword(String userName, String password) throws UsernameNotFoundException{
		AppUser user = this.userRepository.getUserByUserName(userName, password);
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
