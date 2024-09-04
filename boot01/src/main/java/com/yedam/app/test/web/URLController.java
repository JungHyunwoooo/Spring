package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Bean 등록, Web과 관련된 부분 담당
public class URLController {
	// URL 등록하는 부분 확인
	// path 등록하면 모든 메서드를 접근한다는 의미
	//@RequestMapping(path = "/test", method = RequestMethod.GET)
	@GetMapping("/test")
	@ResponseBody // => AJAX용 순수하게 데이터만 반환하는 컨트롤러라는 의미
	public String urlGetTest(String keyword) {
		return "Server Response : Get Method\n Select - " + keyword;
	}// end of public

	// URL 등록하는 부분 확인
	// path 등록하면 모든 메서드를 접근한다는 의미
	//@RequestMapping(path = "/test", method = RequestMethod.POST)
	@PostMapping("/test")
	@ResponseBody // => AJAX용 순수하게 데이터만 반환하는 컨트롤러라는 의미
	public String urlPostTest(String keyword) {
		return "Server Response : Post Method\n Select - " + keyword;
	} // end of public

}// end of class