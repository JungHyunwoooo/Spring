package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	@Autowired // 필드주입
	private EmpMapper empMapper;
	/*
	@Test
	void empList() {
		//전체조회 : 입력X -> 출력, 1건 이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}
	
	@Test
	void empInfo() {
		//단건조회 : 입력, 사원번호(100) -> 출력, 사원이름(king)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
	}
	
	
	@Test
	void empInsert() {
		//사원등록 : 입력, 사원이름, 이메일, 업무
		//		  -> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdHong333@gmail.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	@Test
	void empInsertFull() throws ParseException {
		//사원등록 : 입력, 사원이름, 이메일, 입사일자, 업무, 급여
		//		  -> 출력, 1
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Han");
		empVO.setEmail("jhHan@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(new Date());
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	
	@Test
	void empDelete() {
		int result = empMapper.deleteEmpInfo(211);
		assertEquals(result, 1);
		
		result = empMapper.deleteEmpInfo(212);
		assertEquals(result, 1);
	}
	*/
	
	@Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(206);
		empVO.setLastName("Kim");
		empVO.setJobId("SA_REP");
		/*
		 * EmpVO findVO = empMapper.selectEmpInfo(empVO);
		 * findVO.setEmail("kjHong@naver.com");
		 */
		
		int result
		= empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		assertEquals(result, 1);
	}

	
	
}// end of class
