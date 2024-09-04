package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ThymeleafController {
	
	@GetMapping("Thymeleaf")
	public String ThymeleafTest(EmpVO empVO, Model model) {
		model.addAttribute("emp", empVO);
		return "test/main";
		// classpath:/templates/test/main.html -> 이 경로를 통해 만들어줘야 한다.
	}// end of ThymeleafTest
}// end of class
