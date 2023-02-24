package site.itwill10.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice : 예외 처리 메소드만 선언된 컨트롤러 클래스에서 사용하는 어노테이션
// => 모든 컨트롤러 클래스의 요청 처리 메소드에서 발생되는 예외에 처리 처리 가능
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public String execeptionHandler(Exception exception) {
		//exception.printStackTrace();//예외 기록
		return "userinfo/user_error";
	}
}
