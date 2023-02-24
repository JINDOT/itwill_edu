package site.itwill08.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//Spring DAO ����� �̿��Ͽ� DAO Ŭ���� �ۼ� : spring-jdbc ���̺귯��
// => JdbcTemplate Ŭ������ ���ø� �޼ҵ带 ȣ���Ͽ� DAO Ŭ������ �޼ҵ� �ۼ�
public class StudentDAOImpl implements StudentDAO {
	//Bean Configuration File���� DAO Ŭ������ Spring Bean���� ����� �� JdbcTemplate Ŭ������
	//Spring Bean�� �ʵ忡 ������ ó�� - DI(Dependency Injection)
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
		//JdbcTemplate.update(String sql, Object... args) : SQL ���(INSERT, UPDATE, DELETE)��
		//DBMS ������ �����Ͽ� �����ϴ� �޼ҵ� - �������� ���� ��ȯ
		// => �Ű������� SQL ��ɰ� InParameter�� ����� ���� ���ʴ�� �����Ͽ� �޼ҵ� ȣ��
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
			// => SQL ���(SELECT)�� DBMS ������ �����Ͽ� �����ϴ� �޼ҵ�
			// => �������� �˻������ ���׸����� ������ ��ü ��ȯ�� ��� ����ϴ� �޼ҵ�
			// => SQL ��ɰ� InParameter�� ���ް�, ���������� �Ű������� �����Ͽ� �޼ҵ� ȣ��
			// => InParameter�� ���ް��� Object �迭�� �ʱⰪ���� �����Ͽ� ���ʴ�� ����
			// => RowMapper ��ü�� ���޹޾� �˻������ Java �ڷ���(���׸�)���� �����Ͽ� ��ȯ
			/*
			return jdbcTemplate.queryForObject(sql, new Object[] {no}, new RowMapper<Student>() {
				//�˻����� �÷����� ���׸����� ������ ��ȯ Java Ÿ���� �ʵ忡 ����ǵ��� �����ϴ� �޼ҵ�
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
			//EmptyResultDataAccessException : queryForObject() �޼ҵ忡 ���� ���޵� SQL �����
			//�˻������ �������� ���� ��� �߻��Ǵ� ����
			return null;
		}
	}

	@Override
	public List<Student> selectStudentList() {
		String sql="select * from student order by no";
		//JdbcTemplate.query(String sql, Object[] args, RowMapper<T> rowMapper)
		// => SQL ���(SELECT)�� DBMS ������ �����Ͽ� �����ϴ� �޼ҵ�
		// => �������� �˻������ List ��ü�� ��ȯ�� ��� ����ϴ� �޼ҵ�
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
	
	//RowMapper �������̽��� ��ӹ��� �ڽ�Ŭ���� - ���� ����� �����ϴ� Ŭ����
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






