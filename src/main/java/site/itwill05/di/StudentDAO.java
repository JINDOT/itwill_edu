package site.itwill05.di;

import java.util.List;

//�л������� �����ϴ� DAO Ŭ������ �ݱ׽� ��ӹ޾ƾ� �Ǵ� �������̽�
// => ��ü���� ���յ��� ���߾� ���������� ȿ���� ����
public interface StudentDAO {
	int insertStudent(Student student);
	Student selectStudent(int num);
	List<Student> selectStudentList();
}
