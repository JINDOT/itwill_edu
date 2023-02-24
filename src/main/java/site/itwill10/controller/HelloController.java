package site.itwill10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller : 클라이언트의 요청을 처리하는 다수의 메소드가 선언된 Controller 클래스를 
//Spring Bean으로 등록하는 어노테이션 - Command Controller 클래스와 유사
// => 기본적으로 클래스 이름을 beanName으로 설정 - value 속성을 이용하여 beanName 변경 가능
// => Controller 인터페이스를 상속받지 않아도 Controller 클래스로 작성 가능
// => 다수의 클라이언트 요청에 대해 @RequestMapping 어노테이션을 사용한 메소드로 요청 처리
@Controller
public class HelloController {
	private static final Logger logger=LoggerFactory.getLogger(HelloController.class);
	//요청 처리 메소드에서 Front Controller에게 ViewName를 전달하는 방법
	// => Front Controller는 ViewName를 제공받아 ViewResolver 클래스를 이용하여 JSP 파일로
	//    변환된 경로로 포워드 이동하여 응답 처리
	//1.요청 처리 메소드의 반환형을 void로 선언하면 메소드명을 ViewName으로 제공 
	//2.요청 처리 메소드의 반환형을 String로 선언하면 반환값(문자열)을 ViewName으로 제공 
	//3.요청 처리 메소드의 반환형을 ModelAndView로 선언하면 ModelAndView 객체를 이용하여 ViewName 제공 
	
	//클라이언트의 요청을 처리하기 위한 메소드
	//@RequestMapping : 클라이언트 요청정보(URL)를 제공받아 처리메소드를 호출하기 위한 어노테이션
	//value 속성 : 클라이언트 요청정보(URL)를 속성값으로 설정
	// => 다른 속성이 없는 경우 속성값만 설정 가능
	// => 다른 요청 처리 메소드와 요청정보(URL)이 중복될 경우 WAS 실행시 에러 발생
	@RequestMapping(value = "/hello")
	public void hello() {
		//요청에 대한 처리명령 구현 - Service 클래스의 메소드 호출
		logger.info("/hello 요청 >> HelloController 클래스의 hello() 메소드 호출");
	}
	
	@RequestMapping("/helloViewName")
	public String helloViewName() {
		logger.info("/helloViewName 요청 >> HelloController 클래스의 helloViewName() 메소드 호출");
		return "hello";
	}
	
	@RequestMapping("/helloModelAndView")
	public ModelAndView helloModelAndView() {
		logger.info("/helloModelAndView 요청 >> HelloController 클래스의 helloModelAndView() 메소드 호출");
		
		/*
		ModelAndView modelAndView=new ModelAndView();
		//ModelAndView.setViewName(String viewName) : ModelAndView 객체의 ViewName를 변경하는 메소드
		modelAndView.setViewName("hello");
		*/
		
		//생성자로 ViewName를 전달받아 초기화 작업 가능
		ModelAndView modelAndView=new ModelAndView("hello");
		
		return modelAndView;
	}
}












