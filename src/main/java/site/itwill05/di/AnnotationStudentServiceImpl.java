package site.itwill05.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


//@Component("studentService")

//@Service : Service Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
// => �⺻������ Ŭ�������� beanName���� �����ϸ� value �Ӽ��� �̿��Ͽ� beanName ���� ����
@Service("studentService")
public class AnnotationStudentServiceImpl implements StudentService {
	//@Autowired : �ʵ忡 Spring Bean�� �ڵ����� DI ����� �����ϱ� ���� ����ϴ� ������̼�
	// => bean ������Ʈ�� autowire �Ӽ��� [byType] �Ӽ����� ���� ������� ������ ó��
	// => �ʵ��� Setter �޼ҵ带 �������� �ʾƵ� Setter Injection ��� ����
	//������)�ʵ��� �ڷ����� �������̽��� ��� �ڽ�Ŭ������ Spring Bean�� ��������� ������ ���Կ� ���� ���� �߻�
	//�ذ��-1)�ڽ�Ŭ������ Spring Bean �� �ϳ��� beanName�� �ʵ��� ���� �̸����� ����
	// => �ڽ�Ŭ������ Spring Bean�� ������ ��ϵ� ��� [byName] �Ӽ����� ������ ������� ������ ó��
	//�ذ��-2)@Qualifier ������̼��� �̿��Ͽ� ������ ó���� Spring Bean ����
	//@Qualifier : ������ ó���ϰ��� �ϴ� Spring Bean�� �����ϱ� ���� ������̼�
	// => @Autowired ������̼ǿ� ���ӵ� ������̼�
	// => value �Ӽ��� �Ӽ������� ������ ó���� Spring Bean�� beanName�� ���� - �ٸ� �Ӽ��� ���� ��� �Ӽ����� ����
	@Autowired
	//@Qualifier("annotationStudentJdbcDAO")
	@Qualifier("annotationStudentMybatisDAO")
	//@Resource : Spring Framework�� @Autowired ������̼��� �������� ������� ������̼�
	// => Java ���̺귯���� ���� �����Ǵ� ������̼� : �ٸ� Framework���� ��� ����
	// => �⺻������ [byName] �������� ������ ó��
	//@Inject : Spring Framework�� @Autowired ������̼��� �������� ������� ������̼�
	// => Java ���̺귯���� ���� �����Ǵ� ������̼� : �ٸ� Framework���� ��� ����
	// => �⺻������ [byType] �������� ������ ó��
	private StudentDAO studentDAO; 
	
	public AnnotationStudentServiceImpl() {
		System.out.println("### AnnotationStudentServiceImpl Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	@Override
	public void addStudent(Student student) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ addStudent() �޼ҵ� ȣ�� ***");
		studentDAO.insertStudent(student);
	}

	@Override
	public Student getStudent(int num) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ getStudnet() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudent(num);
	}

	@Override
	public List<Student> getStudentList() {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ getStudentList() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudentList();
	}
}
