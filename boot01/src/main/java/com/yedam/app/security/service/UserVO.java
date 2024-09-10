package com.yedam.app.security.service;

import lombok.Data;

@Data
public class UserVO { // 어노테이션 사용 가능
	private String loginId;
	private String password;
	private String roleName;
}
