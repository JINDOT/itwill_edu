package site.itwill10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//��û ó�� �޼ҵ��� �������� ��������(JSP)���� �����ϴ� ���
//1.ModelAndView ��ü�� �̿��Ͽ� ó����� ����  
// => ��û  ó�� �޼ҵ�� �ݵ�� ModelAndView ��ü ��ȯ
//2.HttpServletRequest ��ü�� �̿��Ͽ� ó����� ����
//3.Model ��ü�� �̿��Ͽ� ó����� ����

@Controller
public class ResultController {
	/*
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult() {
		ModelAndView modelAndView=new ModelAndView("result_display");
		//ModelAndView.addObject(String attributeName, Object attributeValue)
		// => ������������ ��� �����ϵ��� ó�����(��ü)�� �����ϴ� �޼ҵ�(Request Scope)
		modelAndView.addObject("mavName", "ȫ�浿");
		return modelAndView;
	}
	*/
	
	//��û ó�� �޼ҵ�� Front Controller�� ���� �ڵ� ȣ�� �޼ҵ�
	// => ��û ó�� �޼ҵ忡 �Ű������� �����ϸ� Spring Container�κ��� ��ü(Spring Bean)�� 
	//    �����޾� �Ű������� �ڵ� ����
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult(ModelAndView modelAndView) {
		modelAndView.setViewName("result_display");
		modelAndView.addObject("mavName", "ȫ�浿");
		return modelAndView;
	}
	
	@RequestMapping("/resultRequest")
	public String requestResult(HttpServletRequest request) {
		request.setAttribute("requestName", "�Ӳ���");
		return "result_display";
	}
	
	//Model : ó������� �����ϱ� ���� ��ü
	@RequestMapping("/resultModel")
	public String modelResult(Model model) {
		//Model.addAttribute(String attributeName, Object attributeValue)
		// => ������������ ��� �����ϵ��� ó�����(��ü)�� �����ϴ� �޼ҵ�(Request Scope)		
		model.addAttribute("modelName", "����ġ");
		return "result_display";
	}
}










