package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 메모리상 인증정보 등록 => 테스트 전용
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder() //인증에 대해 처리하는 부분
				.username("user1") //계정
				.password(passwordEncoder().encode("1234")) //비밀번호 암호화
				.roles("USER") // ROLE_USER // 권한, 앞에껀 내가 넣을게 뒤에껀 니가 넣어줘
			  //.authorities("ROLE_USER") //권한
				.build(); //생성
		
		UserDetails admin = User.builder() //인증에 대해 처리하는 부분
				.username("admin1") //계정
				.password(passwordEncoder().encode("1234")) //비밀번호 암호화
			  //.roles("ADMIN") // ROLE_ADMIN // 권한, 앞에껀 내가 넣을게 뒤에껀 니가 넣어줘
			    .authorities("ROLE_ADMIN") //권한
				.build(); //생성
		
		return new InMemoryUserDetailsManager(user, admin);
		
	}
	
	//인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//적용될 Security 설정
		//URI에 적용될 권한을 어떻게 할 것인가? 이것이 가장 중요하다. 
		http
			.authorizeHttpRequests(authrize 
				-> authrize
				.requestMatchers("/", "/all").permitAll() //모든 사람이 다 사용하게끔 허용
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")//특정 권한을 가지고 있을 때 강제로 실행  // 실행 방향은 위에서 아래 순서로 확인됨
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated() // 최소한 인증은 받아야 한다라는 의미
			)
			.formLogin(formlogin -> formlogin
					  .defaultSuccessUrl("/all"))
			.logout(logout -> logout
					  .logoutSuccessUrl("/all"));
		
		return http.build();
	}
		
}
