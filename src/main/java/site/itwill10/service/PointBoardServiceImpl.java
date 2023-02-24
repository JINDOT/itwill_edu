package site.itwill10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.itwill10.dao.PointBoardDAO;
import site.itwill10.dao.PointUserDAO;
import site.itwill10.dto.PointBoard;
import site.itwill10.dto.PointUser;

//Service 클래스의 메소드에서 예외가 발생된 경우 예외 발생 전 실행된 모든 DAO 클래스 메소드의 
//SQL 명령(INSERT,UPDATE,DELETE)은 반드시 롤백 처리
// => Spring Framework에서는 일관성 있는 트렌젝션 처리를 제공하기 위해 TransactionManager 관련 클래스 제공
//1.TransactionManager 관련 클래스를 Spring Bean으로 등록 - root-context.xml
// => @Transactional 어노테이션 사용을 위해 annotation-driven 엘리먼트 추가
//2.AOP 기능을 사용하여 TransactionManager로 트렌젝션 처리되도록 설정 - servlet-context.xml
// => @Transactional 어노테이션을 이용하여 트렌젝션 자동 처리
@Service
public class PointBoardServiceImpl implements PointBoardService {
	@Autowired
	private PointBoardDAO pointBoardDAO; 
	
	@Autowired
	private PointUserDAO pointUserDAO;
	
	//게시글 정보를 전달받아 POINTBOARD 테이블에 저장하고 작성자 정보를 반환하는 메소드
	// => 게시글 작성자를 전달하여 POINTUSER 테이블에 저장된 포인트가 증가되도록 작성
	//@Transactional : 메소드에서 예외가 발생된 경우 예외 발생전 호출되어 실행된 DAO 클래스
	//메소드의 SQL 명령을 롤백 처리하는 어노테이션
	// => INSERT,DELETE,UPDATE 명령 관련 DAO 클래스의 메소드를 호출하는 Service 클래스의 메소드에 설정
	@Transactional
	@Override
	public PointUser addPointBoard(PointBoard board) {
		pointBoardDAO.insertPointBoard(board);
		
		//게시글 작성자가 POINTUSER 테이블에 저장된 사용자가 아닌 경우 예외 발생 
		if(pointUserDAO.selectPointUser(board.getWriter())==null) {
			throw new RuntimeException("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updatePlusPointUser(board.getWriter());
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//게시글 번호를 전달받아 POINTBOARD 테이블에 저장된 게시글을 삭제하고 작성자 정보를 반환하는 메소드
	// => 게시글 작성자를 전달하여 POINTUSER 테이블에 저장된 포인트가 감소되도록 작성
	@Transactional
	@Override
	public PointUser erasePointBoard(int num) {
		PointBoard board=pointBoardDAO.selectPointBoard(num);
		//삭제할 게시글이 존재하지 않는 경우 예외 발생
		if(board==null) {
			throw new RuntimeException("존재하지 않는 게시글입니다.");
		}
		String writer=board.getWriter();
		pointBoardDAO.deletePointBoard(num);
		pointUserDAO.updateMinusPointUser(writer);
		return pointUserDAO.selectPointUser(writer);
	}

	//POINTBOARD 테이블에 저장된 모든 게시글을 검색하여 반환하는 메소드
	@Override
	public List<PointBoard> getPointBoardList() {
		return pointBoardDAO.selectPointBoardList();
	}

}
