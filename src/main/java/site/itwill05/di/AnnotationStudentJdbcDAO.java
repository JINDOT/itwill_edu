package site.itwill05.di;

import java.util.List;

import org.springframework.stereotype.Repository;

//@Component : 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 기본적으로 클래스명을 beanName으로 설정하며 value 속성을 이용하여 beanName 변경 가능
//@Component
//@Component("studentDAO")

//@Repository : DAO 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 기본적으로 클래스명을 beanName으로 설정하며 value 속성을 이용하여 beanName 변경 가능
//@Repository("studentDAO")
@Repository
public class AnnotationStudentJdbcDAO implements StudentDAO {
	public AnnotationStudentJdbcDAO() {
		System.out.println("### AnnotationStudentJdbcDAO 클래스의 기본 생성자 호출 ###");
	}
	
	@Override
	public int insertStudent(Student student) {
		System.out.println("*** AnnotationStudentJdbcDAO 클래스의 insertStudent(Student student) 메소드 호출 ***");
		return 0;
	}

	@Override
	public Student selectStudent(int num) {
		System.out.println("*** AnnotationStudentJdbcDAO 클래스의 selectStudent(int num) 메소드 호출 ***");
		return null;
	}

	@Override
	public List<Student> selectStudentList() {
		System.out.println("*** AnnotationStudentJdbcDAO 클래스의 selectStudentList() 메소드 호출 ***");
		return null;
	}
}
