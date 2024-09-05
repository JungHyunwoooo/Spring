package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller // Route : 사용자의 요청(endpoint)와 그에 대한 처리, 라우터를 등록하는 역할을 맡는다.
// URI + METHOD => Service => View
@RequiredArgsConstructor // 롬복이 제공하는 어노테이션이며, final을 가지는 필드를 골라 매개변수로 갖게되는 생성자를 만듦
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;
	
	@Autowired
	EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	// GET  : 조회, 빈페이지
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList")
	public String empList(Model model) { // Model SpringFrameWork 쪽 임포트
							// Model = Request + Response
		// 1) 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); //타임리프할 때는 얘를 항상 잘 찾아야 한다. 우리가 여는 파일에 데이터를 전달하기 위한 메서드. (emps<- 이걸로 호출을 한다.)
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정
		// prefix + return + suffix => 실제 경로를 생성해준다/ViewResolver
		// classpath:/templates/emp/list.html => 이렇게 완성이 되어야 한다.
	}
	// 단건조회 : Get => QueryString(커맨드 객체 or @RequestParam)
	@GetMapping("empInfo") //empInfo?employeeId=value
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		// HttpServletRequest.setAttribute();
		return "emp/info";
		// classpath(곧 resources):/templates/emp/info.html => 실제경로
	}// end of empInfo
	
	// 등록 - 페이지 : Get / **페이지는 post 방식으로 열 수 없다.
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}// end of empInsertForm
	
	// 등록 - 처리 : POST => form 태그를 통해 submit(페이지)를 호출하겠다
	//				   => QueryString(커맨드 객체)
	@PostMapping("empInsert")
	public String empIsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		
		String url = null;
		
		if(eid > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId="+eid;
			// redirect: 가 가능한 경우는 GetMapping 경우밖에 없다
		}else {
			// 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	} // end of empIsertProcess
	
	
	// 수정 - 페이지 : Get, 조건이 필요 <=> 사실상 단건조회와 같다.
	@GetMapping("empUpdate") // empUpdate?employeeId=value
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}// end of empInfo
	
	// 수정 - 처리 : Post, AJAX => QueryString
	//@PostMapping("empUpdate")
	@ResponseBody // AJAX 반환하는 리턴타입에 대해 예외상황이 발생했다는 의미 , 응답이기 때문에 return 전에 해야 함
	public Map<String, Object>
		empUpdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO); //페이지가 필요없으니, model 없이 그냥 return 해 주면 된다.
	}// end of empUpdateAJAXQueryString
	
	// 수정 - 처리 : AJAX => JSON (@RequestBody) // 위아래 같아서 하나는 주석처리 해야 오류 해결
	@PostMapping("empUpdate")
	@ResponseBody // AJAX
	public Map<String, Object>
		empUpdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO); //페이지가 필요없으니, model 없이 그냥 return 해 주면 된다.
	} // end of empUpdateAJAXJSON
	
	// 삭제 - 처리 : Get => QueryString( @RequestParam )
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	} //end of empDelete

}// end of class
