package site.itwill04.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAttributeApp {
	public static void main(String[] args) {
		System.out.println("========== Spring Container �ʱ�ȭ �� ==========");
		ApplicationContext context=new ClassPathXmlApplicationContext("04-2_beanAttribute.xml");
		System.out.println("========== Spring Container �ʱ�ȭ �� ==========");
		//ApplicationContext.getBean(String beanName) : beanName�� �����Ͽ� Spring Container�κ���
		//�ش� Spring Bean�� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
		// => Object Ÿ������ Spring Bean�� ��ȯ�ϹǷ� ����� ��ü ����ȯ�Ͽ� ���
		//InitDestroyMethodBean bean=(InitDestroyMethodBean)context.getBean("initDestroyMethodBean");
		
		//ApplicationContext.getBean(String beanName, Class<T> clazz) : beanName�� �����Ͽ� 
		//Spring Container�κ��� Spring Bean�� �˻��Ͽ� ��ü ����ȯ �� ��ȯ�ϴ� �޼ҵ�
		InitDestroyMethodBean bean=context.getBean("initDestroyMethodBean",InitDestroyMethodBean.class);
		
		//bean.init();
		bean.display();
		//bean.destory();
		System.out.println("================================================");
		context.getBean("lazyInitBean", LazyInitBean.class);
		System.out.println("================================================");
		ScopeBean bean1=context.getBean("singleton", ScopeBean.class);
		ScopeBean bean2=context.getBean("singleton", ScopeBean.class);
		ScopeBean bean3=context.getBean("singleton", ScopeBean.class);
		
		System.out.println("bean1 = "+bean1);
		System.out.println("bean2 = "+bean2);
		System.out.println("bean3 = "+bean3);
		System.out.println("================================================");
		ScopeBean bean4=context.getBean("prototype", ScopeBean.class);
		ScopeBean bean5=context.getBean("prototype", ScopeBean.class);
		ScopeBean bean6=context.getBean("prototype", ScopeBean.class);
		
		System.out.println("bean4 = "+bean4);
		System.out.println("bean5 = "+bean5);
		System.out.println("bean6 = "+bean6);
		System.out.println("================================================");
		((ClassPathXmlApplicationContext)context).close();
		System.out.println("================================================");
	}
}




