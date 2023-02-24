package site.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {
	@RequestMapping("/forward_print")
	public String forward(Model model) {
		//������������ ����� ��ü ���� - Request Scope
		model.addAttribute("name", "ȫ�浿");
		
		//ViewName ��ȯ >> ��������(JSP)�� ������ �̵�
		//������ �̵� : ���������� �ٸ� �����α׷�(JSP)���� �����带 �̵��Ͽ� ���� ó��
		// => Ŭ���̾�Ʈ�� ��û URL �̺���, Request Scope�� �����Ǵ� ��ü ���
		return "redirect/forward_display";
	}
	
	/*
	@RequestMapping("/redirect_print")
	public String redirect(Model model) {
		model.addAttribute("name", "�Ӳ���");
		return "redirect/redirect_display";
	}
	*/
	
	@RequestMapping("/redirect_print")
	public String redirect() {
		return "redirect/redirect_display";
	}
	
	//RedirectAttributes : �����̷�Ʈ �̵��� ���� ȣ��� ��û ó�� �޼ҵ��� ����������
	//�Ӽ����� �����ϱ� ���� ��ü
	@RequestMapping("/redirect")
	public String redirect(RedirectAttributes attributes) {
		//model.addAttribute("name", "�Ӳ���");
		
		//RedirectAttributes.addFlashAttribute(String attributeName, Object attributeValue)
		// => �����̷�Ʈ �̵��� ���� ȣ��� ��û ó�� �޼ҵ��� ���������� �Ӽ���� 
		//    �Ӽ����� �����Ͽ� �����ϴ� �޼ҵ�
		attributes.addFlashAttribute("name", "�Ӳ���");
		
		//��ȯ�Ǵ� ViewName�� redirect ���λ�(NameSpace)�� ����ϸ� �����̷�Ʈ �̵�
		// => ViewName�� ���� ��������(JSP) ��� Ŭ���̾�Ʈ���� URL �ּ� ����
		//�����̷�Ʈ �̵� : Ŭ���̾�Ʈ���� URL �ּҸ� �����Ͽ� ���û�� �޾� ���� ó��
		// => Ŭ���̾�Ʈ�� ��û URL ����, Request Scope�� �����Ǵ� ��ü �̻��
		return "redirect:/redirect_print";
	}
}






