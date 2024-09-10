package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class LoginUserVO implements UserDetails { // LoginUserVO <-얘는 사실상 setter가 필요없다, userVO가 들어와야 한다. 이 VO는 습관적으로 어노테이션 사용 금지!
	private UserVO userVO;
	
	public LoginUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { //계정 만료여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정 잠금여부
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 패스워드 만료여부
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 사용여부
		return true;
	}

}
