package site.itwill04.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration : Spring Container�� �����ϴ� Ŭ������ Spring Bean���� ����ϱ� ���� �����
//�����ϴ� ȯ�� ���� Ŭ������ �����ϱ� ���� ������̼�
// => Bean Configuration File�� beans ������Ʈ�� ������ ����� ����
@Configuration
public class AnnotationConfigurationBean {
	//@Bean : Spring Container�� �����ϱ� ���� Ŭ������ Spring Bean���� �����Ͽ� ��ȯ�ϴ� �޼ҵ忡
	//����ϴ� ������̼�
	// => Bean Configuration File�� bean ������Ʈ�� ������ ����� ����
	// => �⺻������ �޼ҵ���� beanName���� ����
	// => name �Ӽ��� �̿��Ͽ� beanName ���� ����
	@Bean
	public AnnotationBean annotationBean() {
		return new AnnotationBean();
	}
}
