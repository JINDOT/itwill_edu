package site.itwill10.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {
	//입력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	@RequestMapping("/method_input")
	public String inputOne() {
		return "method_input1";
	}
	
	//전달값을 반환받아 출력페이지에 제공하고 출력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => POST 방식으로 요청하여 처리하는 메소드 - GET 방식 요청 가능
	@RequestMapping("/method_output")
	public String outputOne(HttpServletRequest request) throws UnsupportedEncodingException {
		//HttpServletRequest.setCharacterEncoding(String encoding) : 전달값에 대한 캐릭터셋을 변경하는 메소드
		// => UnsupportedEncodingException 발생 - 예외처리
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output1";
	}
	
	//입력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => 요청 처리 메소드에 대한 요청이름(URL)가 동일한 경우 에러 발생
	//method 속성 : 클라이언트의 요청방식을 속성값으로 설정
	// => 클라이언트의 요청이름이 동일한 경우 요청방식에 의해 요청 처리 메소드 호출
	// => RequestMethod(Enum)의 상수필드를 속성값으로 사용
	@RequestMapping(value="/method", method = RequestMethod.GET)
	public String inputTwo() {
		return "method_input2";
	}
	
	//전달값을 반환받아 출력페이지에 제공하고 출력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => POST 방식으로 요청하여 처리하는 메소드 - GET 방식 요청 불가능
	@RequestMapping(value="/method", method = RequestMethod.POST)
	public String outputTwo(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output2";
	}
}




