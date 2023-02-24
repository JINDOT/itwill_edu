package site.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Advice 클래스 : 횡단관심모듈이 선언된 클래스
//횡단관심모듈(CrossCutting Concern Module) : 횡단관심코드가 선언된 메소드
//횡단관심코드 : 핵심관심코드 실행 전 또는 후에 실행될 부가적인 명령
// => 로그, 보안(권한 처리), 트렌젝션 처리, 예외처리 등의 명령
public class StudentAdvice {
	private static final Logger logger=LoggerFactory.getLogger(StudentAdvice.class);
	
	//타겟메소드(핵심관심모듈) 실행 전 삽입될 명령을 작성하기 위한 메소드(횡단관심모듈) 
	// => JoinPoint : Before Advice
	// => JoinPoint : 핵심관심모듈을 기준으로 횡단관심모듈이 삽입될 위치 표현 
	public void beforeLog() {
		logger.info("[before]핵심관심코드 실행 전 삽입되어 실행될 횡단관심코드");
	}
	
	//타겟메소드(핵심관심모듈) 실행 후 삽입될 명령을 작성하기 위한 메소드(횡단관심모듈)
	// => 타겟메소드에서 발생되는 예외에 상관없이 무조건 삽입되어 실행
	// => JoinPoint : After Advice
	public void afterLog() {
		logger.info("[after]핵심관심코드 실행 후에 무조건 삽입되어 실행될 횡단관심코드");
	}

	//타겟메소드(핵심관심모듈) 실행 후 삽입될 명령을 작성하기 위한 메소드(횡단관심모듈)
	// => 타겟메소드에서 예외가 발생하지 않은 경우 삽입되어 실행
	// => JoinPoint : After Returning Advice
	public void afterReturningLog() {
		logger.info("[after-returning]핵심관심코드 정상 실행 후 삽입되어 실행될 횡단관심코드");
	}
	
	//타겟메소드(핵심관심모듈) 실행 후 삽입될 명령을 작성하기 위한 메소드(횡단관심모듈)
	// => 타겟메소드에서 예외가 발생된 경우 삽입되어 실행
	// => JoinPoint : After Throwing Advice
	public void afterThrowingLog() {
		logger.info("[after-throwing]핵심관심코드 실행에 대한 예외가 발생된 후 삽입되어 실행될 횡단관심코드");
	}

	//타겟메소드(핵심관심모듈) 실행 전후 삽입될 명령을 작성하기 위한 메소드(횡단관심모듈)
	// => JoinPoint : Around Advice
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[around]핵심관심코드 실행 전에 삽입되어 실행될 횡단관심코드");
		Object object=joinPoint.proceed();//타겟메소드(핵심관심모듈) 호출
		logger.info("[around]핵심관심코드 실행 후에 삽입되어 실행될 횡단관심코드");
		return object;
	}
}














