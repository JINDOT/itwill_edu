package site.itwill08.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//Spring DAO 기능을 이용하여 DAO 클래스 작성 : spring-jdbc 라이브러리
// => JdbcTemplate 클래스의 템플릿 메소드를 호출하여 DAO 클래스의 메소드 작성
public class StudentDAOImpl implements StudentDAO {
	//Bean Configuration File에서 DAO 클래스를 Spring Bean으로 등록할 때 JdbcTemplate 클래스의
	//Spring Bean을 필드에 인젝션 처리 - DI(Dependency Injection)
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertStudent(Student student) {
		String sql="insert into student values(?,?,?,?,?)";
		//JdbcTemplate.update(String sql, Object... args) : SQL 명령(INSERT, UPDATE, DELETE)을
		//DBMS 서버에 전달하여 실행하는 메소드 - 조작행의 갯수 반환
		// => 매개변수에 SQL 명령과 InParameter에 저장될 값을 차례대로 전달하여 메소드 호출
		return jdbcTemplate.update(sql, student.getNo(), student.getName()
				, student.getPhone(), student.getAddress(), student.getBirthday());
	}

	@Override
	public int updateStudent(Student student) {
		String sql="update student set name=?,phone=?,address=?,birthday=? where no=?";
		return jdbcTemplate.update(sql, student.getName(), student.getPhone()
				, student.getAddress(), student.getBirthday(), student.getNo());
	}

	@Override
	public int deleteStudent(int no) {
		return jdbcTemplate.update("delete from student where no=?", no);
	}

	@Override
	public Student selectStudent(int no) {
		try {
			String sql="select * from student where no=?";
			//JdbcTemplate.queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)
			// => SQL 명령(SELECT)을 DBMS 서버에 전달하여 실행하는 메소드
			// => 단일행의 검색결과를 제네릭으로 설정한 객체 반환할 경우 사용하는 메소드
			// => SQL 명령과 InParameter의 전달값, 매핑정보를 매개변수에 전달하여 메소드 호출
			// => InParameter의 전달값은 Object 배열의 초기값으로 나열하여 차례대로 전달
			// => RowMapper 객체를 전달받아 검색결과를 Java 자료형(제네릭)으로 매핑하여 반환
			/*
			return jdbcTemplate.queryForObject(sql, new Object[] {no}, new RowMapper<Student>() {
				//검색행의 컬럼값을 제네릭으로 설정된 반환 Java 타입의 필드에 저장되도록 설정하는 메소드
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student student=new Student();
					student.setNo(rs.getInt("no"));
					student.setName(rs.getString("name"));
					student.setPhone(rs.getString("phone"));
					student.setAddress(rs.getString("address"));
					student.setBirthday(rs.getString("birthday"));
					return student;
				}
			});
			*/
			return jdbcTemplate.queryForObject(sql, new Object[] {no}, new StudentRowMapper());
		} catch (EmptyResultDataAccessException e) {
			//EmptyResultDataAccessException : queryForObject() 메소드에 의해 전달된 SQL 명령의
			//검색결과가 존재하지 않을 경우 발생되는 예외
			return null;
		}
	}

	@Override
	public List<Student> selectStudentList() {
		String sql="select * from student order by no";
		//JdbcTemplate.query(String sql, Object[] args, RowMapper<T> rowMapper)
		// => SQL 명령(SELECT)을 DBMS 서버에 전달하여 실행하는 메소드
		// => 다중행의 검색결과를 List 객체로 반환할 경우 사용하는 메소드
		/*
		return jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student=new Student();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday"));
				return student;
			}
		});
		*/
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}
	
	//RowMapper 인터페이스를 상속받은 자식클래스 - 매핑 기능을 제공하는 클래스
	public class StudentRowMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student=new Student();
			student.setNo(rs.getInt("no"));
			student.setName(rs.getString("name"));
			student.setPhone(rs.getString("phone"));
			student.setAddress(rs.getString("address"));
			student.setBirthday(rs.getString("birthday"));
			return student;
		}
	}
}






