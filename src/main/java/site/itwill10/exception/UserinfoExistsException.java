package site.itwill10.exception;

import site.itwill10.dto.Userinfo;

//회원등록시 사용자가 입력한 아이디가 이미 존재하는 경우 동작되는 예외클래스
public class UserinfoExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	//예외 처리 메소드(Exception Handler Method)에게 예외 처리에 필요한 정보를 제공하기 위한 필드
	private Userinfo userinfo;
	
	public UserinfoExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserinfoExistsException(Userinfo userinfo, String message) {
		super(message);
		this.userinfo=userinfo;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
}
