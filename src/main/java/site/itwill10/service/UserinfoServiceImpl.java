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

//Service Ŭ������ �޼ҵ�� ��� ����� �߻��Ǵ� ������ ���� ���� �߻�
// => �߻��� ���ܴ� Controller Ŭ�������� ���� ó���ϵ��� ����
@Service
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	@Transactional
	@Override
	public void addUserinfo(Userinfo userinfo) throws UserinfoExistsException {
		if(userinfoDAO.selectUserinfo(userinfo.getUserid())!=null) {
			throw new UserinfoExistsException(userinfo, "�̹� ������� ���̵� �Է� �Ͽ����ϴ�.");
		}
		
		//����ڷκ��� �Է¹޾� ���޵� ��й�ȣ�� ��ȣȭ ��ȯ�Ͽ� �ʵ尪 ����
		// => ��û ó�� �޼ҵ� �Ǵ� JavaBean(DTO) Ŭ������ Setter �޼ҵ忡�� ���� ����
		//��ȣȭ ����� �����ϴ� jbcrypt ���̺귯���� ���� ó���Ͽ� ��� - pom.xml
		//BCrypt.hashpw(String password, String salt) : ���޹��� ��й�ȣ�� ÷������ ������ ��
		//��ȣȭ ó���Ͽ� ��ȯ�ϴ� �޼ҵ�
		//BCrypt.gensalt(int log_bounds) : ÷������ ���̸� ���޹޾� ÷������ �����Ͽ� ��ȯ�ϴ� �޼ҵ�
		// => �Ű������� ÷������ ���� ���� ���� ���� - �⺻�� : 10
		userinfo.setPassword(BCrypt.hashpw(userinfo.getPassword(), BCrypt.gensalt()));
		
		userinfoDAO.insertUserinfo(userinfo);
	}

	@Transactional
	@Override
	public void modifyUserinfo(Userinfo userinfo) throws UserinfoNotFoundException {
		if(userinfoDAO.selectUserinfo(userinfo.getUserid())==null) {
			throw new UserinfoNotFoundException("���̵��� ȸ�������� �������� �ʽ��ϴ�.");
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
			throw new UserinfoNotFoundException("���̵��� ȸ�������� �������� �ʽ��ϴ�.");
		}
		userinfoDAO.deleteUserinfo(userid);
	}

	@Override
	public Userinfo getUserinfo(String userid) throws UserinfoNotFoundException {
		Userinfo userinfo=userinfoDAO.selectUserinfo(userid);
		if(userinfo==null) {
			throw new UserinfoNotFoundException("���̵��� ȸ�������� �������� �ʽ��ϴ�.");
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
		if(authUserinfo==null) {//���̵� �˻� - ���� ���� : ���� �߻�
			throw new LoginAuthFailException("���̵��� ȸ�������� �������� �ʽ��ϴ�.", userinfo.getUserid());
		}
		
		//��й�ȣ �˻� - ���� ���� : ���� �߻�
		//BCrypt.checkpw(String plaintext, String hashed) : �Ϲ� ���ڿ��� ��ȣȭ ó���� ���ڿ���
		//���Ͽ� ���� ��� true�� ��ȯ�ϴ� �޼ҵ�
		if(!BCrypt.checkpw(userinfo.getPassword(), authUserinfo.getPassword())) {
			throw new LoginAuthFailException("���̵� ���ų� ��й�ȣ�� ���� �ʽ��ϴ�.", userinfo.getUserid());
		}
		
		//���ܰ� �߻����� ���� ��� ���� ����
	}
}
