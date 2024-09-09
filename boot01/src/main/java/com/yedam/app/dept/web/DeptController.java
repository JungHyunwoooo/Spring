package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
		return "dept/info";
	}
	
	//등록 - 페이지
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	//등록 - 처리
		@PostMapping("deptInsert")
		public String deptInsertProcess(DeptVO deptVO) {
			int did = deptService.deptInsert(deptVO);
			
			String url = null;
			
			if(did > -1) {
				// 정상적으로 등록된 경우
				url = "redirect:deptInfo?departmentId="+did;
				// redirect: 가 가능한 경우는 GetMapping 경우밖에 없다
			}else {
				// 등록되지 않은 경우
				url = "redirect:deptList";
			}
			return url;
		} // end of deptInsertProcess
		
	//수정 - 페이지
		@GetMapping("deptUpdate") // deptUpdate?departmentId=value
		public String deptUpdateForm(DeptVO deptVO, Model model) {
			DeptVO findVO = deptService.deptInfo(deptVO);
			model.addAttribute("dept", findVO);
			return "dept/update";
		}// end of deptUpdateForm
		
		// 수정 - 처리 : Post, AJAX => QueryString
		//@PostMapping("deptUpdate")
		@ResponseBody // AJAX 반환하는 리턴타입에 대해 예외상황이 발생했다는 의미 , 응답이기 때문에 return 전에 해야 함
		public Map<String, Object>
			deptUpdateAJAXQueryString(DeptVO deptVO) {
			return deptService.deptUpdate(deptVO); //페이지가 필요없으니, model 없이 그냥 return 해 주면 된다.
		}// end of deptUpdateAJAXQueryString
		
	//수정 - 처리 : JSON
		@PostMapping("deptUpdate")
		@ResponseBody // AJAX
		public Map<String, Object>
			deptUpdateAJAXJSON(@RequestBody DeptVO deptVO) {
			return deptService.deptUpdate(deptVO); //페이지가 필요없으니, model 없이 그냥 return 해 주면 된다.
		} // end of deptUpdateAJAXJSON
		
	//삭제 - 처리
		@GetMapping("deptDelete")
		public String deptDelete(Integer departmentId) {
			deptService.deptDelete(departmentId);
			return "redirect:deptList";
		} //end of deptDelete
}
