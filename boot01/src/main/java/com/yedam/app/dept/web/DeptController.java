package com.yedam.app.dept.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	private DeptService deptService;
	
	@Autowired
	DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	//전체조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
	 	//페이지에 전달
		model.addAttribute("depts", list);
		return "dept/list";
		// 리턴되는 classpath:/templates/dept/list.html
	}
	//단건조회
	
	//등록 - 페이지
	
	//등록 - 처리
	
	//수정 - 페이지
	
	//수정 - 처리 : JSON
	
	//삭제 - 처리
}
