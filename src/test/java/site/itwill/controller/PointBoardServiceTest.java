package site.itwill.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import site.itwill10.dto.PointBoard;
import site.itwill10.dto.PointUser;
import site.itwill10.service.PointBoardService;

//Spring Framework의 단위 프로그램 테스트 방법
//1.junit 라이브러리와 spring-test 라이브러리 빌드 처리 - pom.xml => scope 엘리먼트 제거
//2.[src/test/resources] 폴더의 log4j.xml 변경
//3.[src/test/java] 폴더에 테스트 클래스를 작성하여 실행

//일반적으로 테스트 프로그램은 Service 클래스의 메소드를 검사하기 위한 목적으로 작성

//@RunWith : 테스트 클래스의 메소드를 실행하기 위한 클래스(Clazz)를 설정하는 어노테이션
//value 속성 : 테스트 클래스를 실행하기 위한 클래스(Clazz)을 속성값으로 설정
// => 다른 속성이 없는 경우 속성값만 작성 가능
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration : WebApplicationContext(Spring Container)를 사용하여 Spring Bean를 
//관리하도록 설정하는 어노테이션 
@WebAppConfiguration
//@ContextConfiguration : 테스트 클래스에서 사용하는 Spring Bean을 등록하기 위한 Bean 
//Configuration File을 설정하는 어노테이션
//locations 속성 : Bean Configuration File 경로를 배열 형식의 속성값으로 설정
// => Bean Configuration File 경로는 file 접두사를 사용하여 설정
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
//		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//@FixMethodOrder : 테스트 메소드의 호출순서를 설정하는 어노테이션
//value 속성 : MethodSorters 자료형(Enum)의 상수필드 중 하나를 속성값으로 설정
// => MethodSorters.DEFAULT : 테스트 메소드의 HashCode(메모리 주소)를 이용하여 호출순서 설정 - 호출순서 불확실
// => MethodSorters.JVM : 테스트 메소드를 JVM이 불러온 순서대로 호출순서 설정 - 호출순서 불확실
// => MethodSorters.NAME_ASCENDING : 테스트 메소드의 이름으로 호출순서 설정 - 호출순서 확실
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PointBoardServiceTest {
	private static final Logger logger=LoggerFactory.getLogger(PointBoardServiceTest.class);
	/*
	//Service 클래스의 Spring Bean를 필드에 인젝션 처리
	@Autowired
	private PointUserService pointUserService;
	
	//@Test : 테스트 메소드를 설정하는 어노테이션
	// => Runner 클래스에 의해 호출되어 실행되는 테스트 메소드
	@Test
	public void testAddPointUser() {
		PointUser user=new PointUser();
		user.setId("abc");
		user.setName("홍길동");
		
		PointUser addUser=pointUserService.addPointUser(user);
		
		logger.info("아이디 = "+addUser.getId()
			+", 이름 = "+addUser.getName()+", 포인트 = "+addUser.getPoint());
	}
	*/
	
	@Autowired
	private PointBoardService pointBoardService;
	
	@Test
	public void testAddPointBoard() {
		PointBoard board=new PointBoard();
		board.setWriter("xxx");
		board.setSubject("테스트");
		
		PointUser user=pointBoardService.addPointBoard(board);
		logger.info("아이디 = "+user.getId()+", 이름 = "+user.getName()+", 포인트 = "+user.getPoint());
	}
	
	@Test
	public void testGetPointBoardList() {
		List<PointBoard> boardList=pointBoardService.getPointBoardList();
		
		if(boardList.isEmpty()) {
			logger.info("저장된 게시글이 하나도 없습니다.");
		} else {
			for(PointBoard board:boardList) {
				logger.info("글번호 = "+board.getNum()+", 작성자 = "+board.getWriter()+", 제목 = "+board.getSubject());
			}
		}
	}

	/*
	@Test
	public void testErasePointBoard() {
		PointUser user=pointBoardService.erasePointBoard(4);
		logger.info("아이디 = "+user.getId()+", 이름 = "+user.getName()+", 포인트 = "+user.getPoint());
	}
	 */
}

















