package site.itwill07.aop;

import org.springframework.stereotype.Component;

@Component("aopBean")
public class AopAnnotationBean {
	public void display1() {
		System.out.println("*** AopAnnotaionBean Ŭ������ display1() �޼ҵ� ȣ�� ***");
	}
	
	public void display2() {
		System.out.println("*** AopAnnotaionBean Ŭ������ display2() �޼ҵ� ȣ�� ***");
	}
	
	public void display3() {
		System.out.println("*** AopAnnotaionBean Ŭ������ display3() �޼ҵ� ȣ�� ***");
	}
}
