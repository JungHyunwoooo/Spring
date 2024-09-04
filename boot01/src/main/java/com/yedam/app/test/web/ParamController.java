package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	// Content-type : application/x-www-form-urlencoded
	// QueryString(질의문자열) : key=value&key=value&...
	// Method : 상관하지 않는다.
	
	// QueryString => 커맨드 객체 (어노테이션 X, 객체)
	@RequestMapping(path="comobj",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}// end of commandObject
	
	@RequestMapping(path="reqparm",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
			(@RequestParam Integer employeeId,	// 필수
						   String lastName,		// 생략가능
			 @RequestParam(name="message",
					 	   defaultValue="No message",
					 	   required = true) String msg) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}// end of commandObject
	
	//
	// Content-type : application/json
	// JSON포맷 : @RequestBody, 배열 or 객체
	// Method : POST, PUT
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
		
	} // end of requestBody
	
	// 배열로 받기
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList
				(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		} // end of for문
		return result;
		
	} // end of requestBodyList
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method는 상관없음
	// Content-type도 상관없음			//		 path/
	@RequestMapping("path/{id}") // id=yk => path/yk {} 위치에 있는 데이터를 id라는 이름으로 받겠다. => warning 의미는 스프링부트에서 해당 부분을 다른 걸로 변경해서 사용하라고 권장하기 때문
	@ResponseBody
	public String pathVariable(@PathVariable String id) { // 이때 이 id가 어떤건지 지정하는 위치
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	
	
}// end of class
