package site.itwill04.bean;

public class InitDestroyMethodBean {
	public InitDestroyMethodBean() {
		System.out.println("### InitDestroyMethodBean 클래스의 기본 생성자 호출 ###");
	}
	
	//객체 생성 후 초기화 작업을 위해 한번만 호출하기 위한 메소드
	public void init() {
		System.out.println("*** InitDestroyMethodBean 클래스의 init() 메소드 호출 ***");
	}
	
	//객체 소멸 전 마무리 작업을 위해 한번만 호출하기 위한 메소드
	public void destory() {
		System.out.println("*** InitDestroyMethodBean 클래스의 destory() 메소드 호출 ***");
	}
	
	public void display() {
		System.out.println("*** InitDestroyMethodBean 클래스의 display() 메소드 호출 ***");
	}
}
