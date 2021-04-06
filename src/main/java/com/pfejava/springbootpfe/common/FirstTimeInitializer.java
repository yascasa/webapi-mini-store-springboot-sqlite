package com.pfejava.springbootpfe.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.services.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		// CHECK IF THERE NO USERS TO ADD ADMIN
		if( Utility.IterableCount(this.userService.findAll()) == 0) {
			AppUser u = new AppUser("zemraniyassine@gmail.com", "admin", "admin",true);
			this.userService.save(u);
		}	
	}
}
