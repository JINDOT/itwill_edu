package site.itwill.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import site.itwill10.dto.PointBoard;
import site.itwill10.dto.PointUser;
import site.itwill10.service.PointBoardService;

//Spring Framework�� ���� ���α׷� �׽�Ʈ ���
//1.junit ���̺귯���� spring-test ���̺귯�� ���� ó�� - pom.xml => scope ������Ʈ ����
//2.[src/test/resources] ������ log4j.xml ����
//3.[src/test/java] ������ �׽�Ʈ Ŭ������ �ۼ��Ͽ� ����

//�Ϲ������� �׽�Ʈ ���α׷��� Service Ŭ������ �޼ҵ带 �˻��ϱ� ���� �������� �ۼ�

//@RunWith : �׽�Ʈ Ŭ������ �޼ҵ带 �����ϱ� ���� Ŭ����(Clazz)�� �����ϴ� ������̼�
//value �Ӽ� : �׽�Ʈ Ŭ������ �����ϱ� ���� Ŭ����(Clazz)�� �Ӽ������� ����
// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� �ۼ� ����
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration : WebApplicationContext(Spring Container)�� ����Ͽ� Spring Bean�� 
//�����ϵ��� �����ϴ� ������̼� 
@WebAppConfiguration
//@ContextConfiguration : �׽�Ʈ Ŭ�������� ����ϴ� Spring Bean�� ����ϱ� ���� Bean 
//Configuration File�� �����ϴ� ������̼�
//locations �Ӽ� : Bean Configuration File ��θ� �迭 ������ �Ӽ������� ����
// => Bean Configuration File ��δ� file ���λ縦 ����Ͽ� ����
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
//		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//@FixMethodOrder : �׽�Ʈ �޼ҵ��� ȣ������� �����ϴ� ������̼�
//value �Ӽ� : MethodSorters �ڷ���(Enum)�� ����ʵ� �� �ϳ��� �Ӽ������� ����
// => MethodSorters.DEFAULT : �׽�Ʈ �޼ҵ��� HashCode(�޸� �ּ�)�� �̿��Ͽ� ȣ����� ���� - ȣ����� ��Ȯ��
// => MethodSorters.JVM : �׽�Ʈ �޼ҵ带 JVM�� �ҷ��� ������� ȣ����� ���� - ȣ����� ��Ȯ��
// => MethodSorters.NAME_ASCENDING : �׽�Ʈ �޼ҵ��� �̸����� ȣ����� ���� - ȣ����� Ȯ��
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PointBoardServiceTest {
	private static final Logger logger=LoggerFactory.getLogger(PointBoardServiceTest.class);
	/*
	//Service Ŭ������ Spring Bean�� �ʵ忡 ������ ó��
	@Autowired
	private PointUserService pointUserService;
	
	//@Test : �׽�Ʈ �޼ҵ带 �����ϴ� ������̼�
	// => Runner Ŭ������ ���� ȣ��Ǿ� ����Ǵ� �׽�Ʈ �޼ҵ�
	@Test
	public void testAddPointUser() {
		PointUser user=new PointUser();
		user.setId("abc");
		user.setName("ȫ�浿");
		
		PointUser addUser=pointUserService.addPointUser(user);
		
		logger.info("���̵� = "+addUser.getId()
			+", �̸� = "+addUser.getName()+", ����Ʈ = "+addUser.getPoint());
	}
	*/
	
	@Autowired
	private PointBoardService pointBoardService;
	
	@Test
	public void testAddPointBoard() {
		PointBoard board=new PointBoard();
		board.setWriter("xxx");
		board.setSubject("�׽�Ʈ");
		
		PointUser user=pointBoardService.addPointBoard(board);
		logger.info("���̵� = "+user.getId()+", �̸� = "+user.getName()+", ����Ʈ = "+user.getPoint());
	}
	
	@Test
	public void testGetPointBoardList() {
		List<PointBoard> boardList=pointBoardService.getPointBoardList();
		
		if(boardList.isEmpty()) {
			logger.info("����� �Խñ��� �ϳ��� �����ϴ�.");
		} else {
			for(PointBoard board:boardList) {
				logger.info("�۹�ȣ = "+board.getNum()+", �ۼ��� = "+board.getWriter()+", ���� = "+board.getSubject());
			}
		}
	}

	/*
	@Test
	public void testErasePointBoard() {
		PointUser user=pointBoardService.erasePointBoard(4);
		logger.info("���̵� = "+user.getId()+", �̸� = "+user.getName()+", ����Ʈ = "+user.getPoint());
	}
	 */
}

















