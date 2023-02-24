package site.itwill09.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//Command Controller : Ŭ���̾�Ʈ ��û�� ó���ϱ� ���� Ŭ���� - Model
// => Spring Framework���� �����ϴ� Controller �������̽��� ��ӹ޾� �ۼ�
public class ListController implements Controller {
	//handleRequest : ��û�� ó���ϱ� ���� Front Controller�� �ڵ� ȣ���ϴ� �޼ҵ�
	// => HttpServletRequest ��ü�� HttpServletResponse ��ü�� �����޾� ��û ó��
	// => �� ���� ������ ModelAndView ��ü�� �����Ͽ� ��ȯ
	//ModelAndView : ���� ���� ������ �����ϱ� ���� Ŭ����
	// => ��û ó�� ����� �������� ���� ������ �����Ͽ� Front Controller���� ��ȯ 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��û�� ó���ϱ� ���� Service Ŭ������ �޼ҵ� ȣ�� - ó������� ��ȯ�޾� ����
		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("aaa","ȫ�浿"));
		memberList.add(new Member("bbb","�Ӳ���"));
		memberList.add(new Member("ccc","����ġ"));
		
		//ModelAndView ��ü ���� - �޼ҵ� ��ȯ��
		ModelAndView modelAndView=new ModelAndView();
		
		//ModelAndView.addObject(String attributeName, Object attributeValue)
		// => ModelAndView ��ü�� ó�� ����� �����ϱ� ���� �޼ҵ�
		// => HttpServletRequest.setAttribute() �޼ҵ�� ������ ��� ����
		// => ��������(JSP)���� EL�� �̿��Ͽ� ���� ó��
		modelAndView.addObject("memberList", memberList);
		
		//ModelAndView.addObject(String viewName) : ���������� ����(ViewName)�� �����ϴ� �޼ҵ�
		modelAndView.setViewName("member_list");
		
		return modelAndView;
	}

}













