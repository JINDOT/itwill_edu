package site.itwill10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import site.itwill10.dto.Userinfo;
import site.itwill10.exception.LoginAuthFailException;
import site.itwill10.exception.UserinfoExistsException;
import site.itwill10.exception.UserinfoNotFoundException;
import site.itwill10.service.UserinfoService;

@Controller
@RequestMapping("/userinfo")
public class UserinfoController {
	@Autowired
	private UserinfoService userinfoService;
	
	/*
	//회원정보를 입력하는 요청 처리 메소드
	//비로그인 사용자이거나 관리자가 아닌 경우 인위적 예외 발생
	// => 예외를 전달하면 예외 처리 메소드가 호출되어 예외 처리 - 에러페이지로 응답 처리
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(HttpSession session) throws Exception {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
			//return "userinfo/user_error";
			throw new Exception();
		}
		return "userinfo/user_write";
	}
	*/
	
	//인터셉터를 이용하여 로그인 사용자 또는 관리자가 아닌 경우 요청 처리 메소드가 동작되지 않도록 설정
	//인터셉터(Interceptor) : Front Controller에 의해 요청 처리 메소드가 호출되기 전에 필요한
	//명령을 실행하는 기능 - 권한 처리
	// => 인터셉터 클래스를 작성하고 Bean Configuration File(servlet-context.xml)에서 
	//    인터셉터가 동작되도록 설정(AOP)
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() throws Exception {
		return "userinfo/user_write";
	}
	
	/*
	//회원정보를 전달받아 USERINFO 테이블에 저장하는 요청 처리 메소드
	//Service 클래스의 메소드 호출시 발생되는 예외를 요청 처리 메소드에서 직접 예외 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Userinfo userinfo, Model model) {
		try {
			userinfoService.addUserinfo(userinfo);
		} catch (UserinfoExistsException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("userinfo", userinfo);
			return "userinfo/user_write";
		} catch (Exception e) {
			e.printStackTrace();
			return "userinfo/user_error";
		}
		return "redirect:/userinfo/login";
	}
	*/
	
	//회원정보를 전달받아 USERINFO 테이블에 저장하는 요청 처리 메소드
	//Service 클래스의 메소드 호출시 발생되는 예외를 요청 처리 메소드에서 직접 처리하지 않고
	//Front Controller에게 전달
	//Front Controller는 예외 처리 메소드(Exception Handler Method)가 선언된 경우 예외 처리
	//메소드를 호출하여 예외 처리
	// => 예외 처리 메소드가 선언되지 않은 경우 클라이언트에게 에러코드(500) 전달
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Userinfo userinfo) throws UserinfoExistsException {
		userinfoService.addUserinfo(userinfo);
		return "redirect:/userinfo/login";
	}
	
	//인증정보(아이디와 비밀번호)를 입력하는 요청 처리 메소드 
	// => 환영 메세지를 출력하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "userinfo/user_login";
	}
	
	//인증정보를 전달받아 USERINFO 테이블에 저장된 회원정보와 비교하여 인증 처리하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, HttpSession session) 
			throws LoginAuthFailException, UserinfoNotFoundException {
		//Service 클래스의 인증 처리 메소드 호출
		// => 인증 실패 : LoginAuthFailException 예외 발생 - 예외 처리 메소드로 예외 처리
		userinfoService.loginAuth(userinfo);
		
		//인증 성공(예외 미발생) - HttpSession 객체에 권한 관련 정보를 객체 속성값으로 저장
		session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		
		return "userinfo/user_login";
	}
	
	//로그아웃 처리하는 요청 처리 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/userinfo/login";
	}
	
	//회원목록을 출력하는 요청 처리 메소드 
	// => 로그인 사용자만 요청 가능하도록 인터셉터 이용
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		return "userinfo/user_list";
	}
	
	//회원상세정보를 출력하는 요청 처리 메소드
	// => 로그인 사용자만 요청 가능하도록 인터셉터 이용
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_view";
	}
	
	//회원정보를 변경하는 페이지를 출력하는 요청 처리 메소드
	// => 관리자만 요청 가능하도록 인터셉터 이용
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_modify";
	}
	
	//회원정보를 변경하는 요청 처리 메소드
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Userinfo userinfo, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.modifyUserinfo(userinfo);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//변경회원이 현재 로그인 사용자인 경우 세션에 저장된 권한 관련 정보 변경
		if(loginUserinfo.getUserid().equals(userinfo.getUserid())) {
			session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		}
		
		return "redirect:/userinfo/view?userid="+userinfo.getUserid();
	}

	//회원정보를 삭제하는 요청 처리 메소드
	// => 관리자만 요청 가능하도록 인터셉터 이용
	@RequestMapping(value = "/remove")
	public String remove(@RequestParam String userid, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.removeUserinfo(userid);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}
		return "redirect:/userinfo/list";
	}
		
	//@ExceptionHandler : 컨트롤러 클래스의 요청 처리 메소드에서 발생된 예외를 처리하기 위한 
	//예외 처리 메소드를 설정하는 어노테이션
	//value 속성 : 예외 처리할 예외클래스(Clazz)를 속성값으로 설정
	// => 다른 속성이 없으면 속성값만 설정 가능
	//예외 처리 메소드의 매개변수에는 예외 관련 정보를  전달받아 저장되며 예외 처리 후 ViewName 반환 - JSP 응답
	@ExceptionHandler(UserinfoExistsException.class)
	public String execeptionHandler(UserinfoExistsException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userinfo", exception.getUserinfo());
		return "userinfo/user_write";
	}
	
	@ExceptionHandler(LoginAuthFailException.class)
	public String execeptionHandler(LoginAuthFailException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userid", exception.getUserid());
		return "userinfo/user_login";
	}
	
	/*
	//Exception를 처리하는 예외 처리 메소드와 명령이 동일한 경우 생략 가능
	@ExceptionHandler(UserinfoNotFoundException.class)
	public String execeptionHandler(UserinfoNotFoundException exception) {
		return "userinfo/user_error";
	}
	*/
	
	/*
	@ExceptionHandler(Exception.class)
	public String execeptionHandler(Exception exception) {
		return "userinfo/user_error";
	}
	*/
}




