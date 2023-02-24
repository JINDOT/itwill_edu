package site.itwill10.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.itwill10.dao.UserinfoDAO;
import site.itwill10.dto.Userinfo;
import site.itwill10.exception.LoginAuthFailException;
import site.itwill10.exception.UserinfoExistsException;
import site.itwill10.exception.UserinfoNotFoundException;

//Service 클래스의 메소드는 명령 실행시 발생되는 문제에 대한 예외 발생
// => 발생된 예외는 Controller 클래스에서 예외 처리하도록 전달
@Service
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	@Transactional
	@Override
	public void addUserinfo(Userinfo userinfo) throws UserinfoExistsException {
		if(userinfoDAO.selectUserinfo(userinfo.getUserid())!=null) {
			throw new UserinfoExistsException(userinfo, "이미 사용중인 아이디를 입력 하였습니다.");
		}
		
		//사용자로부터 입력받아 전달된 비밀번호를 암호화 변환하여 필드값 변경
		// => 요청 처리 메소드 또는 JavaBean(DTO) 클래스의 Setter 메소드에서 변경 가능
		//암호화 기능을 제공하는 jbcrypt 라이브러리를 빌드 처리하여 사용 - pom.xml
		//BCrypt.hashpw(String password, String salt) : 전달받은 비밀번호에 첨가물을 삽입한 후
		//암호화 처리하여 반환하는 메소드
		//BCrypt.gensalt(int log_bounds) : 첨가물의 길이를 전달받아 첨가물을 생성하여 반환하는 메소드
		// => 매개변수에 첨가물의 길이 전달 생략 가능 - 기본값 : 10
		userinfo.setPassword(BCrypt.hashpw(userinfo.getPassword(), BCrypt.gensalt()));
		
		userinfoDAO.insertUserinfo(userinfo);
	}

	@Transactional
	@Override
	public void modifyUserinfo(Userinfo userinfo) throws UserinfoNotFoundException {
		if(userinfoDAO.selectUserinfo(userinfo.getUserid())==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		
		String password=userinfo.getPassword();
		if(password!=null && !password.equals("")) {
			userinfo.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		}
		
		userinfoDAO.updateUserinfo(userinfo);
	}

	@Transactional
	@Override
	public void removeUserinfo(String userid) throws UserinfoNotFoundException {
		if(userinfoDAO.selectUserinfo(userid)==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		userinfoDAO.deleteUserinfo(userid);
	}

	@Override
	public Userinfo getUserinfo(String userid) throws UserinfoNotFoundException {
		Userinfo userinfo=userinfoDAO.selectUserinfo(userid);
		if(userinfo==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		return userinfo;
	}

	@Override
	public List<Userinfo> getUserinfoList() {
		return userinfoDAO.selectUserinfoList();
	}

	@Override
	public void loginAuth(Userinfo userinfo) throws LoginAuthFailException {
		Userinfo authUserinfo=userinfoDAO.selectUserinfo(userinfo.getUserid());
		if(authUserinfo==null) {//아이디 검사 - 인증 실패 : 예외 발생
			throw new LoginAuthFailException("아이디의 회원정보가 존재하지 않습니다.", userinfo.getUserid());
		}
		
		//비밀번호 검사 - 인증 실패 : 예외 발생
		//BCrypt.checkpw(String plaintext, String hashed) : 일반 문자열과 암호화 처리된 문자열을
		//비교하여 같은 경우 true를 반환하는 메소드
		if(!BCrypt.checkpw(userinfo.getPassword(), authUserinfo.getPassword())) {
			throw new LoginAuthFailException("아이디가 없거나 비밀번호가 맞지 않습니다.", userinfo.getUserid());
		}
		
		//예외가 발생하지 않은 경우 인증 성공
	}
}
