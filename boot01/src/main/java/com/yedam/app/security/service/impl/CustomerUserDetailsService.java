package com.yedam.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.mapper.UserMapper;
import com.yedam.app.security.service.LoginUserVO;
import com.yedam.app.security.service.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 얘를 쓰면 final은 필수로 넣어줘야 함!
public class CustomerUserDetailsService implements UserDetailsService{
	private final UserMapper userMapper;
	
	// 인증을 진행할 때 사용하는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Mapper를 활용해서 DB에 접근
		UserVO userVO = userMapper.getUserInfo(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new LoginUserVO(userVO);
	}

}// end of class
