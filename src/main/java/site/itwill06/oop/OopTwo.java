package site.itwill06.oop;

public class OopTwo {
	/*
	public void beforeLog() {
		System.out.println("### 메소드 명령 실행 전 기록될 내용 ###");
	}
	*/
	
	OopLogger logger=new OopLogger();
	
	public void display1() {
		//System.out.println("### 메소드 명령 실행 전 기록될 내용 ###");
		//beforeLog();
		logger.beforeLog();
		System.out.println("*** OopTwo 클래스의 display1() 메소드 호출 ***");
	}
	
	public void display2() {
		//System.out.println("### 메소드 명령 실행 전 기록될 내용 ###");
		//beforeLog();
		logger.beforeLog();
		System.out.println("*** OopTwo 클래스의 display2() 메소드 호출 ***");
	}
	
	public void display3() {
		//System.out.println("### 메소드 명령 실행 전 기록될 내용 ###");
		//beforeLog();
		logger.beforeLog();
		System.out.println("*** OopTwo 클래스의 display3() 메소드 호출 ***");
	}
}
