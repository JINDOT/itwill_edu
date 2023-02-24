package site.itwill10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//요청 처리 메소드의 실행결과를 뷰페이지(JSP)에게 제공하는 방법
//1.ModelAndView 객체를 이용하여 처리결과 저장  
// => 요청  처리 메소드는 반드시 ModelAndView 객체 반환
//2.HttpServletRequest 객체를 이용하여 처리결과 저장
//3.Model 객체를 이용하여 처리결과 저장

@Controller
public class ResultController {
	/*
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult() {
		ModelAndView modelAndView=new ModelAndView("result_display");
		//ModelAndView.addObject(String attributeName, Object attributeValue)
		// => 뷰페이지에서 사용 가능하도록 처리결과(객체)를 저장하는 메소드(Request Scope)
		modelAndView.addObject("mavName", "홍길동");
		return modelAndView;
	}
	*/
	
	//요청 처리 메소드는 Front Controller에 의해 자동 호출 메소드
	// => 요청 처리 메소드에 매개변수를 선언하면 Spring Container로부터 객체(Spring Bean)를 
	//    제공받아 매개변수에 자동 저장
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult(ModelAndView modelAndView) {
		modelAndView.setViewName("result_display");
		modelAndView.addObject("mavName", "홍길동");
		return modelAndView;
	}
	
	@RequestMapping("/resultRequest")
	public String requestResult(HttpServletRequest request) {
		request.setAttribute("requestName", "임꺽정");
		return "result_display";
	}
	
	//Model : 처리결과를 저장하기 위한 객체
	@RequestMapping("/resultModel")
	public String modelResult(Model model) {
		//Model.addAttribute(String attributeName, Object attributeValue)
		// => 뷰페이지에서 사용 가능하도록 처리결과(객체)를 저장하는 메소드(Request Scope)		
		model.addAttribute("modelName", "전우치");
		return "result_display";
	}
}










