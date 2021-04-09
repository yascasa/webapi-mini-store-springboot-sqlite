package com.pfejava.springbootpfe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfejava.springbootpfe.common.AppMessages;
import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.mapper_vo_bo.UserMapper;
import com.pfejava.springbootpfe.security.JwtUserService;
import com.pfejava.springbootpfe.security.TokenUtil;
import com.pfejava.springbootpfe.services.UserService;
import com.pfejava.springbootpfe.vo.ErrorResponseVO;
import com.pfejava.springbootpfe.vo.LoginVO;
import com.pfejava.springbootpfe.vo.UserVO;

@RestController
@RequestMapping("/public")
@CrossOrigin
public class AuthController {
	
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private UserService userService;
    
	@PostMapping(value={"/login", "/login/"})
	public HttpEntity<?>  singin(@RequestBody  LoginVO loginVO) {
		try {
			AppUser userBO = jwtUserService.loadUserByUsernameAndPassword(loginVO.getUsername(), loginVO.getPassword());
			if(userBO == null) return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.AUTHENTICATION_FAILED), HttpStatus.BAD_REQUEST);
			UserVO userVO = UserMapper.UserBoToVo(userBO);
			String token = tokenUtil.generateToken(userBO);
			userVO.setToken(token);
			return new ResponseEntity<Object>(userVO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.INTERNAL_ERROR_SERVER),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value={"/signup", "/signup/"})
	public HttpEntity<?>  save(@RequestBody  LoginVO loginVO) {
		try {
			AppUser userBO =  userService.loadUserByUsername(loginVO.getUsername());
			if(userBO != null) return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.EMAIL_USED), HttpStatus.BAD_REQUEST);
			userBO = new AppUser(loginVO.getUsername(),loginVO.getUsername(),loginVO.getPassword(), false);
			userService.save(userBO);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponseVO(AppMessages.INTERNAL_ERROR_SERVER),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
