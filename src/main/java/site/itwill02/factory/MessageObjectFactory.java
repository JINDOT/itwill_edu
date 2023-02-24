package site.itwill02.factory;

//Factory 디자인 패턴이 적용된 클래스 : Factory 클래스 또는 Provider 클래스
// => 객체를 생성하여 반환하는 기능 제공
public class MessageObjectFactory {
	private static MessageObjectFactory _factory;
	
	//반환되는 객체 타입을 설정하기 위한 상수 필드 선언
	public final static int HELLO_MESSAGE=1;
	public final static int HI_MESSAGE=2;
	
	private MessageObjectFactory() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_factory=new MessageObjectFactory();
	}
	
	public static MessageObjectFactory getFactory() {
		return _factory;
	}
	
	//정수값을 전달받아 비교하여 인터페이스를 상속받은 클래스로 객체를 생성하여 반환하는 메소드
	public MessageObject getMessageObject(int messageObject) {
		MessageObject object=null;
		switch (messageObject) {
		case HELLO_MESSAGE:
			object=new HelloMessageObject();
			break;
		case HI_MESSAGE:
			object=new HiMessageObject();
			break;
		}
		
		return object;
	}
}











