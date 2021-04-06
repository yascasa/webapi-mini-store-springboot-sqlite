package com.pfejava.springbootpfe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.mapper_vo_bo.UserMapper;
import com.pfejava.springbootpfe.security.JwtUserService;
import com.pfejava.springbootpfe.security.TokenUtil;
import com.pfejava.springbootpfe.vo.LoginVO;
import com.pfejava.springbootpfe.vo.UserVO;

@RestController
@RequestMapping("/public")
public class AuthController {
	
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private JwtUserService jwtUserService;

    //@Autowired
    //private AuthenticationManager authenticationManager;
    
	@PostMapping(value={"/login", "/login/"})
	public HttpEntity<?>  singin(@RequestBody  LoginVO loginVO) {
		try {
			AppUser userBO =  jwtUserService.loadUserByUsername(loginVO.getUsername());
			if(userBO == null) return new ResponseEntity<Object>(userBO, HttpStatus.NO_CONTENT);
			UserVO userVO = UserMapper.UserBoToVo(userBO);
			String token = tokenUtil.generateToken(userBO);
			userVO.setToken(token);
			return new ResponseEntity<Object>(userVO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
