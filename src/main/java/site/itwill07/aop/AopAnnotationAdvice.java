package site.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
//@Aspect : Spring Bean���� ��ϵ� Ŭ������ �޼ҵ忡 Aspect ����� �����ϱ� ���� ������̼�
// => Bean Configuration File�� aspect ������Ʈ�� ������ ��� ����
@Aspect
public class AopAnnotationAdvice {
	private static final Logger logger=LoggerFactory.getLogger(AopAnnotationAdvice.class);
	
	//@Pointcut : �ٽɰ��ɸ���� Ÿ�ٸ޼ҵ�� �����ϱ� ���� ������̼�
	// => Bean Configuration File�� pointcut ������Ʈ�� ������ ��� ����
	// => Pointcut�� ���� ������ ���� ����
	// => �޼ҵ��� �Ű������� Pointcut�� ���������� �νĵǾ� �޼ҵ� ȣ���������� Pointcut ���
	//value �Ӽ� : Ÿ�ٸ޼ҵ带 �����ϱ� ���� ����(execution �Ǵ� within)�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ������� ���� ��� �Ӽ����� ���� ����
	@Pointcut("within(site.itwill07.aop.AopAnnotationBean)")
	public void aopPointcut() {}
	
	//@Before : Before Advice �޼ҵ带 �����ϴ� ������̼�
	// => Bean Configuration File�� before ������Ʈ�� ������ ��� ����
	//value �Ӽ� : Ÿ�ٸ޼ҵ带 �����ϱ� ���� ����(execution �Ǵ� within)�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ������� ���� ��� �Ӽ����� ���� ����
	// => @Pointcut ������̼��� ������ �޼ҵ带 ȣ���Ͽ� Ÿ�ٸ޼ҵ� ����
	//@Before(value = "within(site.itwill07.aop.AopAnnotationBean)")
	@Before("aopPointcut()")
	public void beforeLog() {
		logger.info("[before]�ٽɰ����ڵ� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//@AfterReturning : After Returning Advice �޼ҵ带 �����ϴ� ������̼�
	// => Bean Configuration File�� after-returning ������Ʈ�� ������ ��� ����
	//returning �Ӽ� : Ÿ�ٸ޼ҵ��� ��ȯ���� ���޹޾� ������ �Ű������� �̸��� �Ӽ������� ����
	@AfterReturning(value = "aopPointcut()", returning = "object")
	public void afterReturningLog(Object object) {
		logger.info("[after-returning]�ٽɰ����ڵ� ���� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//@AfterThrowing : After Throwing Advice �޼ҵ带 �����ϴ� ������̼�
	// => Bean Configuration File�� after-throwing ������Ʈ�� ������ ��� ����
	//throwing �Ӽ� : Ÿ�ٸ޼ҵ忡�� �߻��� ���ܸ� ���޹޾� ������ �Ű������� �̸��� �Ӽ������� ����
	@AfterThrowing(value = "aopPointcut()", throwing = "exception")
	public void afterThrowingLog(Exception exception) {
		logger.info("[after-throwing]�ٽɰ����ڵ� ���࿡ ���� ���ܰ� �߻��� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//@After : After Advice �޼ҵ带 �����ϴ� ������̼�
	// => Bean Configuration File�� after ������Ʈ�� ������ ��� ����
	@After("aopPointcut()")
	public void afterLog() {
		logger.info("[after]�ٽɰ����ڵ� ���� �Ŀ� ������ ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//@Around : Around Advice �޼ҵ带 �����ϴ� ������̼�
	// => Bean Configuration File�� around ������Ʈ�� ������ ��� ����
	@Around("aopPointcut()")
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[around]�ٽɰ����ڵ� ���� ���� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
		Object object=joinPoint.proceed();
		logger.info("[around]�ٽɰ����ڵ� ���� �Ŀ� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
		return object;
	}
}
