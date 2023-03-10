package site.itwill05.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentApp {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("05-1_di.xml");
		System.out.println("=================================================================");
		Student student1=context.getBean("student1", Student.class);
		System.out.println(student1);
		System.out.println("=================================================================");
		Student student2=context.getBean("student2", Student.class);
		System.out.println(student2);
		System.out.println("=================================================================");
		Student student3=context.getBean("student3", Student.class);
		System.out.println(student3);
		System.out.println("=================================================================");
		Student student4=context.getBean("student4", Student.class);
		System.out.println(student4);
		System.out.println("=================================================================");		
		Student student5=context.getBean("student5", Student.class);
		System.out.println(student5);
		System.out.println("=================================================================");
		Student student6=context.getBean("student6", Student.class);
		System.out.println(student6);
		System.out.println("=================================================================");

		//프로그램 실행에 필요한 기능을 Service 클래스의 Spring Bean을 제공받아 메소드를 호출하여 구현
		/*
		StudentServiceImpl studentServiceImpl=context.getBean("studentServiceImpl",StudentServiceImpl.class);
		
		studentServiceImpl.addStudent(student1);
		studentServiceImpl.getStudnet(1000);
		studentServiceImpl.getStudentList();
		*/
		
		StudentService studentService=context.getBean("studentService", StudentService.class);
		
		studentService.addStudent(student1);
		studentService.getStudent(1000);
		studentService.getStudentList();
		System.out.println("=================================================================");		
		((ClassPathXmlApplicationContext)context).close();
	}
}







