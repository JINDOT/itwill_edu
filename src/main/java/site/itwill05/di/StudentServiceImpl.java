package site.itwill05.di;

import java.util.List;

//Service Ŭ���� : ���α׷� ���࿡ �ʿ��� ����� �����ϱ� ���� Ŭ���� - ���۳�Ʈ(���)
// => DAO Ŭ������ �޼ҵ带 ȣ���Ͽ� ���α׷� ���࿡ �ʿ��� ����� �޼ҵ�� ����
// => Service Ŭ������ ����Ǿ ���α׷��� ������ �ּ�ȭ �ϱ� ���� �������̽��� ��ӹ޾� �ۼ�
public class StudentServiceImpl implements StudentService {
	/*
	//StudentJdbcDAO ��ü�� �ʵ忡 �����Ͽ� ���԰��� ����
	// => Constructor �Ǵ� Setter �޼ҵ带 �̿��Ͽ� ��ü�� ���޹޾� �ʵ忡 ���� 
	//private StudentJdbcDAO studentJdbcDAO;
	
	//�ʵ��� �ڷ����� ����Ǹ� �ʵ� ���� �����ڿ� �޼ҵ带 ��� ����
	private StudentMybatisDAO studentMybatisDAO;
	
	public StudentServiceImpl() {
		System.out.println("### StudentServiceImpl Ŭ������ �⺻ ������ ȣ�� ###");
	}

	public StudentServiceImpl(StudentJdbcDAO studentJdbcDAO) {
		super();
		this.studentJdbcDAO = studentJdbcDAO;
		System.out.println("### StudentServiceImpl Ŭ������ �Ű������� ����� ������ ȣ�� ###");
	}

	public StudentJdbcDAO getStudentJdbcDAO() {
		return studentJdbcDAO;
	}

	public void setStudentJdbcDAO(StudentJdbcDAO studentJdbcDAO) {
		this.studentJdbcDAO = studentJdbcDAO;
		System.out.println("*** StudentServiceImpl Ŭ������ setStudentJdbcDAO() �޼ҵ� ȣ�� ***");
	}

	@Override
	public void addStudent(Student student) {
		System.out.println("*** StudentServiceImpl Ŭ������ addStudent() �޼ҵ� ȣ�� ***");
		studentJdbcDAO.insertStudent(student);
	}

	@Override
	public Student getStudent(int num) {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudnet() �޼ҵ� ȣ�� ***");
		return studentJdbcDAO.selectStudent(num);
	}

	@Override
	public List<Student> getStudentList() {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudentList() �޼ҵ� ȣ�� ***");
		return studentJdbcDAO.selectStudentList();
	}
	*/
	
	//DAO Ŭ������ ��ӹ��� �������̽��� �̿��Ͽ� �ʵ带 �����ϸ� ��� �ڽ�Ŭ������ ��ü�� ���޹޾� ���� - ��������
	// => �������̽� �ʵ�� �޼ҵ带 ȣ���ϸ� �ʵ忡 ����� �ڽ�Ŭ���� ��ü�� �޼ҵ� ȣ�� - �������̵� ������
	// => ���յ��� ���� ���������� ȿ���� ���� - �ڽ�Ŭ������ ��ü�� ����ŵ� Ŭ������ ��ġ�� ���� �ּ�ȭ 
	private StudentDAO studentDAO;
	
	public StudentServiceImpl() {
		System.out.println("### StudentServiceImpl Ŭ������ �⺻ ������ ȣ�� ###");
	}

	public StudentServiceImpl(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
		System.out.println("### StudentServiceImpl Ŭ������ �Ű������� ����� ������ ȣ�� ###");
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		System.out.println("*** StudentServiceImpl Ŭ������ setStudentDAO() �޼ҵ� ȣ�� ***");
	}

	@Override
	public void addStudent(Student student) {
		System.out.println("*** StudentServiceImpl Ŭ������ addStudent() �޼ҵ� ȣ�� ***");
		studentDAO.insertStudent(student);
	}

	@Override
	public Student getStudent(int num) {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudnet() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudent(num);
	}

	@Override
	public List<Student> getStudentList() {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudentList() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudentList();
	}
}
