package site.itwill07.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//횡단관심모듈이 선언된 클래스
public class JoinPointAdvice {
	//Around Advice 메소드를 제외한 다른 Advice 메소드는 반환형을 void로 선언하고 매개변수 미선언
	// => Around Advice 메소드를 제외한 다른 Advice 메소드는 JoinPoint 타입로 매개변수 선언 가능
	
	//JoinPoint : 타겟메소드(핵심관심모듈)의 정보를 저장하기 위한 객체
	// => Advice 메소드가 호출될 경우 Spring Container에 의해 타겟메소드의 정보가 JoinPoint 매개변수에 자동 저장
	// => Advice 메소드의 횡단관심코드에서 타겟메소드의 정보를 활용할 경우 사용
	
	//Before Advice 메소드
	public void displayTarget(JoinPoint joinPoint) {
		//System.out.println("[before]핵심관심모듈 실행 전에 삽입되어 실행될 횡단관심모듈");
		
		//JoinPoint.getTarget() : 타겟메소드를 호출하는 객체(Spring Bean)을 반환하는 메소드
		// => 핵심관심모듈이 선언된 클래스의 객체를 Object 타입으로 반환
		//Object.getClass() : 객체에 대한 클래스 정보(Clazz)를 반환하는 메소드
		//Class.getName() : 클래스 정보(Clazz)에서 클래스명(패키지 포함)을 반환하는 메소드 
		//System.out.println(joinPoint.getTarget().getClass().getName());
		
		//Class.getSimpleName() : 클래스 정보(Clazz)에서 클래스명(패키지 미포함)을 반환하는 메소드 
		//System.out.println(joinPoint.getTarget().getClass().getSimpleName());
		
		//JoinPoint.getSignature() : 타겟메소드의 정보(Signature 객체)를 반환하는 메소드
		//Signature.getName() : Signature 객체에서 타겟메소드명을 반환하는 메소드
		//System.out.println(joinPoint.getSignature().getName());
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		//System.out.println("[before]"+className+" 클래스의 "+methodName+"() 메소드 호출");
		
		//JoinPoint.getArgs() : 타겟메소드의 매개변수(값)를 Object 배열로 반환하는 메소드
		Object[] objects=joinPoint.getArgs();
		System.out.println("[before]"+className+" 클래스의 "+methodName
				+"() 메소드의 매개변수 갯수 = "+objects.length);
	}
	
	//After Returning Advice 메소드
	// => Object 타입의 매개변수 선언 가능 : 타겟메소드의 반환값을 전달받아 저장
	// => 타겟메소드의 반환형이 고정된 경우 Object 타입 대신 반환 객체의 클래스 타입 사용
	// => 타겟메소드의 반환값이 매개변수에 저장되도록 after-returning 엘리먼트에서 returning 속성을 반드시 설정 
	public void displayName(Object object) {
		//System.out.println("[after-returning]핵심관심모듈이 정상적으로 실행된 후 삽입되어 실행될 횡단관심모듈");
		
		if(object instanceof String) {
			String name=(String)object;
			System.out.println("[after-returning]"+name+"님 환영합니다.");
		}
	}
	
	//After Throwing Advice 메소드
	// => Exception 타입의 매개변수 선언 가능 : 타겟메소드 실행시 발생된 예외를 전달받아 저장
	// => 타겟메소드의 예외가 매개변수에 저장되도록 after-throwing 엘리먼트에서 throwing 속성을 반드시 설정 
	public void displayException(Exception exception) {
		//System.out.println("[after-throwing]핵심관심모듈 실행시 예외가 발생된 경우 삽입되어 실행될 횡단관심모듈");
		
		System.out.println("[after-throwing]타겟메소드에서 발생된 예외 = "+exception.getMessage());
	}
	
	//Around Advice 메소드는 일반적으로 반환형을 Object 타입으로 선언하고 ProceedingJoinPoint  
	//타입으로 매개변수를 선언하여 작성 - Throwable 클래스로 예외 전달
	//ProceedingJoinPoint : 타겟메소드(핵심관심모듈)의 정보를 저장하기 위한 객체
	// => ProceedingJoinPoint 객체로 타겟메소드를 직접 호출하여 핵심관심모듈의 명령 실행
	// => 타겟메소드의 반환값을 제공받아 Around Advice 메소드의 반환값으로 사용
	// => 타겟메소드 실행시 발생되는 예외는 Throwable 타입으로 전달받아 예외 처리
	
	//Around Advice 메소드
	public Object display(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[around]핵심관심모듈 실행 전에 삽입되어 실행되는 횡단관심모듈");
		
		//ProceedingJoinPoint.proceed() : 타겟메소드를 호출하는 메소드
		Object object=joinPoint.proceed();
		
		System.out.println("[around]핵심관심모듈 실행 후에 삽입되어 실행되는 횡단관심모듈");
		return object;		
	}
}













