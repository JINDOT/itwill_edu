package site.itwill09.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//Command Controller : 클라이언트 요청을 처리하기 위한 클래스 - Model
// => Spring Framework에서 제공하는 Controller 인터페이스를 상속받아 작성
public class ListController implements Controller {
	//handleRequest : 요청을 처리하기 위해 Front Controller가 자동 호출하는 메소드
	// => HttpServletRequest 객체와 HttpServletResponse 객체를 제공받아 요청 처리
	// => 뷰 관련 정보를 ModelAndView 객체에 저장하여 반환
	//ModelAndView : 응답 관련 정보를 저장하기 위한 클래스
	// => 요청 처리 결과와 뷰페이지 관련 정보를 저장하여 Front Controller에게 반환 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//요청을 처리하기 위해 Service 클래스의 메소드 호출 - 처리결과를 반환받아 저장
		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("aaa","홍길동"));
		memberList.add(new Member("bbb","임꺽정"));
		memberList.add(new Member("ccc","전우치"));
		
		//ModelAndView 객체 생성 - 메소드 반환값
		ModelAndView modelAndView=new ModelAndView();
		
		//ModelAndView.addObject(String attributeName, Object attributeValue)
		// => ModelAndView 객체에 처리 결과를 저장하기 위한 메소드
		// => HttpServletRequest.setAttribute() 메소드와 유사한 기능 제공
		// => 뷰페이지(JSP)에서 EL를 이용하여 응답 처리
		modelAndView.addObject("memberList", memberList);
		
		//ModelAndView.addObject(String viewName) : 응답페이지 정보(ViewName)를 변경하는 메소드
		modelAndView.setViewName("member_list");
		
		return modelAndView;
	}

}













