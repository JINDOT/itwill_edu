package site.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class ExecutionTimeAdvice {
	private static final Logger logger=LoggerFactory.getLogger(ExecutionTimeAdvice.class);
	
	//Ÿ�ٸ޼ҵ��� ����� ����Ǵ� ó���ð�(ms)�� ����Ͽ� ����ϱ� ���� �޼ҵ�
	// => Around Advice �޼ҵ�
	public Object timeWatchLog(ProceedingJoinPoint joinPoint) throws Throwable {
		//StopWatch : �ð��� �����ϱ� ���� ����� �����ϴ� Ŭ����
		StopWatch stopWatch=new StopWatch();
		
		//StopWatch.strat() : �ð� ���� ���� �޼ҵ�
		stopWatch.start();
		
		Object object=joinPoint.proceed();//Ÿ�ٸ޼ҵ� ȣ�� - �ٽɰ����ڵ� ����
	
		//StopWatch.strat() : �ð� ���� ���� �޼ҵ�
		stopWatch.stop();
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();	
		
		//StopWatch.getTotalTimeMillis() : ������ �ð�(ms)�� ��ȯ�ϴ� �޼ҵ�
		logger.info(className+" Ŭ������ "+methodName+" �޼ҵ� ���� �ð� = "
				+stopWatch.getTotalTimeMillis()+"ms");
		
		return object;
	}
}




