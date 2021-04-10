package com.pfejava.springbootpfe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	    private Long id;
	    private String email;
	    private String userName;
	    private String address;
	    private String token;
	    private boolean isAdmin = false;
}

