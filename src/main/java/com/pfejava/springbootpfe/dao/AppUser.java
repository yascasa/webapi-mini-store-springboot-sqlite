package com.pfejava.springbootpfe.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pfejava.springbootpfe.common.RoleType;
import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//LOMBOKS
@Data
@AllArgsConstructor
@NoArgsConstructor



@Entity // This tells Hibernate to make a table out of this class
@Table(name = "AppUser")
public class AppUser implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7586716966740917121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
	
	@NotNull
    private String userEmail;
	
	@NotNull
    private String userName;
	
	@NotNull
    private String userPassword;
	
    private String userAddress = "";
    
    private Boolean isAdmin = false;
    
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    @Transient
    private List<GrantedAuthority> roles;
    
    public AppUser(String email, String userName , String password, boolean isAdmin) {
    	this.userEmail = email;
    	this.userName = userName;
    	this.userPassword = password;
    	this.isAdmin = isAdmin;
    }
    
	@Override
	public List<GrantedAuthority> getAuthorities() {
		roles = new ArrayList<GrantedAuthority>();
		if(this.isAdmin) {			
			roles.add(new SimpleGrantedAuthority(RoleType.Names.ADMIN));
		} else {
			roles.add(new SimpleGrantedAuthority(RoleType.Names.USER));
		}
		return roles;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getPassword();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}
	
	public String getUserName () {
		return this.userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<GrantedAuthority> getRoles() {
		return this.getAuthorities();
	}	
	
    
}
