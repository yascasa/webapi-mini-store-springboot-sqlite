package com.pfejava.springbootpfe.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfejava.springbootpfe.common.AppMessages;
import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.mapper_vo_bo.UserMapper;
import com.pfejava.springbootpfe.services.UserService;
import com.pfejava.springbootpfe.vo.ErrorResponseVO;
import com.pfejava.springbootpfe.vo.LoginVO;
import com.pfejava.springbootpfe.vo.UserVO;

@Controller
@RestController 
@CrossOrigin
public class UserController  implements UserDetailsService {
	@Autowired()
	private UserService userService;


	@RequestMapping(value="/admin/users", method = RequestMethod.GET, produces = "application/json")
	  public HttpEntity<Object> getUsers() { 
		try {
			List<UserVO> result = new ArrayList<UserVO>();
			Iterator<AppUser> intItr = userService.findAll().iterator();
			while(intItr.hasNext()) {
				AppUser u = intItr.next();
				result.add(UserMapper.UserBoToVo(u));
			}
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.AUTHENTICATION_FAILED),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET, produces = "application/json")
	  public HttpEntity<?> getUser(@PathVariable String id) { 
		try {
			Long longID = Long.parseLong(id);
			Optional<AppUser> user = userService.findById(longID); 
			if(user.isPresent()) {
				return new ResponseEntity<Object>(UserMapper.UserBoToVo(user.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.AUTHENTICATION_FAILED), HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.AUTHENTICATION_FAILED),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	
	@RequestMapping(value="/Oldlogin", method = RequestMethod.POST, produces = "application/json")
	  public HttpEntity<?> login(@RequestBody LoginVO login) { 
		try {
			AppUser user = userService.authentication(login.getUsername(), login.getPassword()); 
			if(user != null) { 
				//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
				//final String token = jwtTokenUtil.generateTokenUser(user);
				UserVO userVO = UserMapper.UserBoToVo(user);
				userVO.setToken("TODO");
				return new ResponseEntity<Object>(userVO, HttpStatus.OK);
			} else {
				ErrorResponseVO resp = new ErrorResponseVO(AppMessages.AUTHENTICATION_FAILED);
				return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
			}
		} catch (DisabledException e) {
			return new ResponseEntity<Object>(new Exception("USER_DISABLED", e) ,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<Object>(new Exception("INVALID_CREDENTIALS", e) ,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception e) {
			return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.INTERNAL_ERROR_SERVER) ,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }

	@Override
	@RequestMapping(value="/loadUserByUsername", method = RequestMethod.POST, produces = "application/json")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 AppUser user = userService.loadUserByUsername(username);
	        if (user == null) {
	           // throw new NotFoundException("User not found");
	        }
	        UserDetails u = (UserDetails) user;
	        return u;
	}
}