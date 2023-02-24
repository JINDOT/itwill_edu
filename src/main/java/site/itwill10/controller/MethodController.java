package site.itwill10.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {
	//�Է��������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	@RequestMapping("/method_input")
	public String inputOne() {
		return "method_input1";
	}
	
	//���ް��� ��ȯ�޾� ����������� �����ϰ� ����������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => POST ������� ��û�Ͽ� ó���ϴ� �޼ҵ� - GET ��� ��û ����
	@RequestMapping("/method_output")
	public String outputOne(HttpServletRequest request) throws UnsupportedEncodingException {
		//HttpServletRequest.setCharacterEncoding(String encoding) : ���ް��� ���� ĳ���ͼ��� �����ϴ� �޼ҵ�
		// => UnsupportedEncodingException �߻� - ����ó��
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output1";
	}
	
	//�Է��������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => ��û ó�� �޼ҵ忡 ���� ��û�̸�(URL)�� ������ ��� ���� �߻�
	//method �Ӽ� : Ŭ���̾�Ʈ�� ��û����� �Ӽ������� ����
	// => Ŭ���̾�Ʈ�� ��û�̸��� ������ ��� ��û��Ŀ� ���� ��û ó�� �޼ҵ� ȣ��
	// => RequestMethod(Enum)�� ����ʵ带 �Ӽ������� ���
	@RequestMapping(value="/method", method = RequestMethod.GET)
	public String inputTwo() {
		return "method_input2";
	}
	
	//���ް��� ��ȯ�޾� ����������� �����ϰ� ����������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => POST ������� ��û�Ͽ� ó���ϴ� �޼ҵ� - GET ��� ��û �Ұ���
	@RequestMapping(value="/method", method = RequestMethod.POST)
	public String outputTwo(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output2";
	}
}




