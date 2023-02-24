package site.itwill10.exception;

//회원정보의 변경,삭제,검색시 아이디의 회원정보가 존재하지 않을경우 동작되는 예외클래스
public class UserinfoNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UserinfoNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserinfoNotFoundException(String message) {
		super(message);
	}
	
}
