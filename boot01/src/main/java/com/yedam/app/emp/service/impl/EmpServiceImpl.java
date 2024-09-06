package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // => AOP이 적용가능한 Bean
public class EmpServiceImpl implements EmpService {
	private EmpMapper empMapper;
	
	@Autowired //-> 생성자가 하나라서 오류 발생
	EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		// 정상적으로 등록이 되어있으면 등록한 employee_id를 리턴해준다.
		// 삼항연산자는 반드시 변수가 필요 
		// => 삼항연산자는 단독으로 사용할 수 없고 변수가 있을 경우에 사용
		//=> 반드시 값을 돌려주는 조건문이기 때문에 두 가지 중 값을 1개만 돌려준다면 삼항연산자가 편함
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		// Map에서 Value를 Object로 하게 되면 모든 데이터 타입의 값을 담을 수 있다.
		// java 내부에서 처리할 때는 Object로 처리하지 않는다, 내부에서 꺼낼 때는 어떤 타입인지 모르기 때문에 어렵다.
		boolean isSuccessed = false;
		
		int result
		= empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		/* 자바스크립트에서 AJAX로 던지면 나오는 DATA 형태
		 {
		 	"result" : true,
		 	"target" : {
		 					employeeId : 100,
		 					lastName : "King",
		 					...
		 				}
		 }
		 */
		
		return map; // return에는 null을 넣고 작업X, 놓칠 위험이 있기 때문에 어떤 값이라도 넣어라, OR 차라리 에러가 발생하게 NULL을 지우기!
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안 될 경우 : {}
		// 삭제가 될 경우   : { "employeeId" : 104 }
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		return map;
	}

}
