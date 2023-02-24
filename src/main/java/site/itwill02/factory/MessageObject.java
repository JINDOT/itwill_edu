package site.itwill02.factory;

//인터페이스를 상속받은 클래스가 반드시 선언해야 되는 메소드를 추상메소드 선언
// => 인터페이스를 상속받은 클래스는 추상메소드를 무조건 오버라이드 선언 - 클래스의 작성 규칙 제공
//인테페이스로 참조변수를 생성하면 모든 자식 인스턴스 저장 가능
// => 인터페이스의 참조변수로 메소드를 호출하면 오버라이드 선언된 자식 인스턴스의 메소드 호출
// => 오버라이드의 의한 다형성 - 객체간의 결합도 감소
public interface MessageObject {
	String getMessage();
}
