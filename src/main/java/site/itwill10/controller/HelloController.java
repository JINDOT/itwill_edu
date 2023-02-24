package site.itwill10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller : Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �ټ��� �޼ҵ尡 ����� Controller Ŭ������ 
//Spring Bean���� ����ϴ� ������̼� - Command Controller Ŭ������ ����
// => �⺻������ Ŭ���� �̸��� beanName���� ���� - value �Ӽ��� �̿��Ͽ� beanName ���� ����
// => Controller �������̽��� ��ӹ��� �ʾƵ� Controller Ŭ������ �ۼ� ����
// => �ټ��� Ŭ���̾�Ʈ ��û�� ���� @RequestMapping ������̼��� ����� �޼ҵ�� ��û ó��
@Controller
public class HelloController {
	private static final Logger logger=LoggerFactory.getLogger(HelloController.class);
	//��û ó�� �޼ҵ忡�� Front Controller���� ViewName�� �����ϴ� ���
	// => Front Controller�� ViewName�� �����޾� ViewResolver Ŭ������ �̿��Ͽ� JSP ���Ϸ�
	//    ��ȯ�� ��η� ������ �̵��Ͽ� ���� ó��
	//1.��û ó�� �޼ҵ��� ��ȯ���� void�� �����ϸ� �޼ҵ���� ViewName���� ���� 
	//2.��û ó�� �޼ҵ��� ��ȯ���� String�� �����ϸ� ��ȯ��(���ڿ�)�� ViewName���� ���� 
	//3.��û ó�� �޼ҵ��� ��ȯ���� ModelAndView�� �����ϸ� ModelAndView ��ü�� �̿��Ͽ� ViewName ���� 
	
	//Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� �޼ҵ�
	//@RequestMapping : Ŭ���̾�Ʈ ��û����(URL)�� �����޾� ó���޼ҵ带 ȣ���ϱ� ���� ������̼�
	//value �Ӽ� : Ŭ���̾�Ʈ ��û����(URL)�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	// => �ٸ� ��û ó�� �޼ҵ�� ��û����(URL)�� �ߺ��� ��� WAS ����� ���� �߻�
	@RequestMapping(value = "/hello")
	public void hello() {
		//��û�� ���� ó����� ���� - Service Ŭ������ �޼ҵ� ȣ��
		logger.info("/hello ��û >> HelloController Ŭ������ hello() �޼ҵ� ȣ��");
	}
	
	@RequestMapping("/helloViewName")
	public String helloViewName() {
		logger.info("/helloViewName ��û >> HelloController Ŭ������ helloViewName() �޼ҵ� ȣ��");
		return "hello";
	}
	
	@RequestMapping("/helloModelAndView")
	public ModelAndView helloModelAndView() {
		logger.info("/helloModelAndView ��û >> HelloController Ŭ������ helloModelAndView() �޼ҵ� ȣ��");
		
		/*
		ModelAndView modelAndView=new ModelAndView();
		//ModelAndView.setViewName(String viewName) : ModelAndView ��ü�� ViewName�� �����ϴ� �޼ҵ�
		modelAndView.setViewName("hello");
		*/
		
		//�����ڷ� ViewName�� ���޹޾� �ʱ�ȭ �۾� ����
		ModelAndView modelAndView=new ModelAndView("hello");
		
		return modelAndView;
	}
}












