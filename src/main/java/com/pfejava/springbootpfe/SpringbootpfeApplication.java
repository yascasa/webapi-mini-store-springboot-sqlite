package com.pfejava.springbootpfe;

import javax.annotation.security.DeclareRoles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.pfejava.springbootpfe.common.RoleType;

@SpringBootApplication
@DeclareRoles({RoleType.Names.ADMIN, RoleType.Names.USER})

public class SpringbootpfeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootpfeApplication.class, args);
	}
}
