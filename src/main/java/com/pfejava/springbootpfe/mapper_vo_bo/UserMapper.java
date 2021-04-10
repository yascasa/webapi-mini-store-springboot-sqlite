package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.AppUser;
import com.pfejava.springbootpfe.vo.UserVO;

public class UserMapper {
	
	public static AppUser UserVoToBo(UserVO user){
		try {			
			AppUser bo = new AppUser();
			bo.setUserID(user.getId());
			bo.setUserEmail(user.getEmail());
			bo.setUserName(user.getUserName());
			bo.setUserAddress(user.getAddress());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static UserVO UserBoToVo(AppUser user){
		try {
			UserVO vo = new UserVO();
			vo.setId(user.getUserID());
			vo.setEmail(user.getUserEmail());
			vo.setUserName(user.getUserName());
			vo.setAddress(user.getUserAddress());
			if(user.getIsAdmin()) {
				vo.setAdmin(true);
			}
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
