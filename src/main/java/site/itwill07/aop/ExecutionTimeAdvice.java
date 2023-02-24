package site.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class ExecutionTimeAdvice {
	private static final Logger logger=LoggerFactory.getLogger(ExecutionTimeAdvice.class);
	
	//타겟메소드의 명령이 실행되는 처리시간(ms)을 계산하여 기록하기 위한 메소드
	// => Around Advice 메소드
	public Object timeWatchLog(ProceedingJoinPoint joinPoint) throws Throwable {
		//StopWatch : 시간을 측정하기 위한 기능을 제공하는 클래스
		StopWatch stopWatch=new StopWatch();
		
		//StopWatch.strat() : 시간 측정 시작 메소드
		stopWatch.start();
		
		Object object=joinPoint.proceed();//타겟메소드 호출 - 핵심관심코드 실행
	
		//StopWatch.strat() : 시간 측정 종료 메소드
		stopWatch.stop();
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();	
		
		//StopWatch.getTotalTimeMillis() : 측정된 시간(ms)을 반환하는 메소드
		logger.info(className+" 클래스의 "+methodName+" 메소드 실행 시간 = "
				+stopWatch.getTotalTimeMillis()+"ms");
		
		return object;
	}
}




