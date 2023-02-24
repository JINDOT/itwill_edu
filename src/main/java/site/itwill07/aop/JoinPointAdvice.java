package site.itwill07.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//Ⱦ�ܰ��ɸ���� ����� Ŭ����
public class JoinPointAdvice {
	//Around Advice �޼ҵ带 ������ �ٸ� Advice �޼ҵ�� ��ȯ���� void�� �����ϰ� �Ű����� �̼���
	// => Around Advice �޼ҵ带 ������ �ٸ� Advice �޼ҵ�� JoinPoint Ÿ�Է� �Ű����� ���� ����
	
	//JoinPoint : Ÿ�ٸ޼ҵ�(�ٽɰ��ɸ��)�� ������ �����ϱ� ���� ��ü
	// => Advice �޼ҵ尡 ȣ��� ��� Spring Container�� ���� Ÿ�ٸ޼ҵ��� ������ JoinPoint �Ű������� �ڵ� ����
	// => Advice �޼ҵ��� Ⱦ�ܰ����ڵ忡�� Ÿ�ٸ޼ҵ��� ������ Ȱ���� ��� ���
	
	//Before Advice �޼ҵ�
	public void displayTarget(JoinPoint joinPoint) {
		//System.out.println("[before]�ٽɰ��ɸ�� ���� ���� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		//JoinPoint.getTarget() : Ÿ�ٸ޼ҵ带 ȣ���ϴ� ��ü(Spring Bean)�� ��ȯ�ϴ� �޼ҵ�
		// => �ٽɰ��ɸ���� ����� Ŭ������ ��ü�� Object Ÿ������ ��ȯ
		//Object.getClass() : ��ü�� ���� Ŭ���� ����(Clazz)�� ��ȯ�ϴ� �޼ҵ�
		//Class.getName() : Ŭ���� ����(Clazz)���� Ŭ������(��Ű�� ����)�� ��ȯ�ϴ� �޼ҵ� 
		//System.out.println(joinPoint.getTarget().getClass().getName());
		
		//Class.getSimpleName() : Ŭ���� ����(Clazz)���� Ŭ������(��Ű�� ������)�� ��ȯ�ϴ� �޼ҵ� 
		//System.out.println(joinPoint.getTarget().getClass().getSimpleName());
		
		//JoinPoint.getSignature() : Ÿ�ٸ޼ҵ��� ����(Signature ��ü)�� ��ȯ�ϴ� �޼ҵ�
		//Signature.getName() : Signature ��ü���� Ÿ�ٸ޼ҵ���� ��ȯ�ϴ� �޼ҵ�
		//System.out.println(joinPoint.getSignature().getName());
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		//System.out.println("[before]"+className+" Ŭ������ "+methodName+"() �޼ҵ� ȣ��");
		
		//JoinPoint.getArgs() : Ÿ�ٸ޼ҵ��� �Ű�����(��)�� Object �迭�� ��ȯ�ϴ� �޼ҵ�
		Object[] objects=joinPoint.getArgs();
		System.out.println("[before]"+className+" Ŭ������ "+methodName
				+"() �޼ҵ��� �Ű����� ���� = "+objects.length);
	}
	
	//After Returning Advice �޼ҵ�
	// => Object Ÿ���� �Ű����� ���� ���� : Ÿ�ٸ޼ҵ��� ��ȯ���� ���޹޾� ����
	// => Ÿ�ٸ޼ҵ��� ��ȯ���� ������ ��� Object Ÿ�� ��� ��ȯ ��ü�� Ŭ���� Ÿ�� ���
	// => Ÿ�ٸ޼ҵ��� ��ȯ���� �Ű������� ����ǵ��� after-returning ������Ʈ���� returning �Ӽ��� �ݵ�� ���� 
	public void displayName(Object object) {
		//System.out.println("[after-returning]�ٽɰ��ɸ���� ���������� ����� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		if(object instanceof String) {
			String name=(String)object;
			System.out.println("[after-returning]"+name+"�� ȯ���մϴ�.");
		}
	}
	
	//After Throwing Advice �޼ҵ�
	// => Exception Ÿ���� �Ű����� ���� ���� : Ÿ�ٸ޼ҵ� ����� �߻��� ���ܸ� ���޹޾� ����
	// => Ÿ�ٸ޼ҵ��� ���ܰ� �Ű������� ����ǵ��� after-throwing ������Ʈ���� throwing �Ӽ��� �ݵ�� ���� 
	public void displayException(Exception exception) {
		//System.out.println("[after-throwing]�ٽɰ��ɸ�� ����� ���ܰ� �߻��� ��� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		System.out.println("[after-throwing]Ÿ�ٸ޼ҵ忡�� �߻��� ���� = "+exception.getMessage());
	}
	
	//Around Advice �޼ҵ�� �Ϲ������� ��ȯ���� Object Ÿ������ �����ϰ� ProceedingJoinPoint  
	//Ÿ������ �Ű������� �����Ͽ� �ۼ� - Throwable Ŭ������ ���� ����
	//ProceedingJoinPoint : Ÿ�ٸ޼ҵ�(�ٽɰ��ɸ��)�� ������ �����ϱ� ���� ��ü
	// => ProceedingJoinPoint ��ü�� Ÿ�ٸ޼ҵ带 ���� ȣ���Ͽ� �ٽɰ��ɸ���� ��� ����
	// => Ÿ�ٸ޼ҵ��� ��ȯ���� �����޾� Around Advice �޼ҵ��� ��ȯ������ ���
	// => Ÿ�ٸ޼ҵ� ����� �߻��Ǵ� ���ܴ� Throwable Ÿ������ ���޹޾� ���� ó��
	
	//Around Advice �޼ҵ�
	public Object display(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[around]�ٽɰ��ɸ�� ���� ���� ���ԵǾ� ����Ǵ� Ⱦ�ܰ��ɸ��");
		
		//ProceedingJoinPoint.proceed() : Ÿ�ٸ޼ҵ带 ȣ���ϴ� �޼ҵ�
		Object object=joinPoint.proceed();
		
		System.out.println("[around]�ٽɰ��ɸ�� ���� �Ŀ� ���ԵǾ� ����Ǵ� Ⱦ�ܰ��ɸ��");
		return object;		
	}
}













