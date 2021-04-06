package com.pfejava.springbootpfe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	
    @Autowired(required=true) //add this
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }  
    
	public Iterable<AppUser> findAll(){
		return this.userRepository.findAll();
	}
	
	public Optional<AppUser> findById(Long id){
		return this.userRepository.findById(id);
	}
	
	public AppUser save(AppUser u){
		return this.userRepository.save(u);
	}
	
	public boolean delete(Long id) {
		try {
			this.userRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public AppUser loadUserByUsername(String username) {
		try {
			AppUser result = this.userRepository.findByUserName(username);
			if(result != null) {
				return result;
			} else {				
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
	
	public AppUser authentication(String username, String password) {
		try {
			AppUser result = this.userRepository.getUserByUserName(username, password);
			if(result != null) {
				return result;
			} else {				
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
}
