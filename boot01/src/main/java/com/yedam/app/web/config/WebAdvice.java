package com.yedam.app.web.config;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebAdvice {
	// 예외처리
	@ExceptionHandler(SQLException.class) //어노테이션을 등록하여 발생하는 에러를 등록 // SQLException-> 내가 지정한 메세지를 보여주기 위함
	public ResponseEntity<String> invokeError(SQLException e) {
		return new ResponseEntity<>("Error Message", HttpStatus.BAD_REQUEST); 
	}
	// 공통변수선언
	@ModelAttribute("contextPath") // 2)화면에서 사용하는 변수값을 확인할 수 있게 해준다.
	public String contextPath
			(HttpServletRequest request) {
		return request.getContextPath(); // 1)리턴되는 결과값을 2)화면에서 확인할 수 있게 해준다
	}
}
