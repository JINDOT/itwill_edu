package site.itwill05.di;

import java.util.List;

//학생정보를 관리하는 DAO 클래스가 반그시 상속받아야 되는 인터페이스
// => 객체간의 결합도를 낮추어 유지보수의 효율성 증가
public interface StudentDAO {
	int insertStudent(Student student);
	Student selectStudent(int num);
	List<Student> selectStudentList();
}
