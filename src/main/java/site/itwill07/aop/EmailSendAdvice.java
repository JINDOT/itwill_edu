package site.itwill07.aop;

import org.aspectj.lang.JoinPoint;

//���� ���� ���� �α� ����� �����ϴ� Ŭ���� - Advice
public class EmailSendAdvice {
	//���� ���� �� ����� ����� �����ϱ� ���� �޼ҵ� - Before Advice �޼ҵ�
	// => JoinPoint �Ű������� Ÿ�ٸ޼ҵ� ������ �����޾� �Ű������� �̿��Ͽ� �α� ��� ����
	public void beforeLog(JoinPoint joinPoint) {
		String email=(String)joinPoint.getArgs()[0];
		String subject=(String)joinPoint.getArgs()[1];
		System.out.println("[�޼���]<"+email+">�Կ��� <"+subject+"> ������ �̸����� �����մϴ�.");
	}
	
	//���� ������ ������ �Ŀ� ����� ����� �����ϱ� ���� �޼ҵ� - After Returning Advice �޼ҵ�
	// => Ÿ�ٸ޼ҵ��� ��ȯ���� �����޾� �Ű������� �����Ͽ� �α� ��� ����
	public void successLog(String email) {
		System.out.println("[�޼���]<"+email+">�Կ��� �̸����� ���������� ���� �Ͽ����ϴ�.");
	}
	
	//���� ������ ������ �Ŀ� ����� ����� �����ϱ� ���� �޼ҵ� - After Throwing Advice �޼ҵ�
	// => Ÿ�ٸ޼ҵ忡�� �߻��� ���ܸ� �����޾� �Ű������� �����Ͽ� �α� ��� ����
	public void failLog(Exception exception) {
		System.out.println("[����]�̸��� ���� ���� = "+exception.getMessage());
	}
}
