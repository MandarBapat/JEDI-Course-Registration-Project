package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.training.api.Student;

public class StudentJdbcImpl implements StudentDAO{

	ConnectionUtil connectionUtil;

	public StudentJdbcImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	@Override
	public Optional<Student> findByStudentId(int id) {
		Optional<Student> opt = Optional.empty();
		try (Connection c = connectionUtil.getConnection();) {

			String sql = "SELECT * FROM student WHERE student_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Student p = mapRowToObject(rs);
				p.setStudentId(id);

				opt = Optional.of(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opt;
	}
	
	@Override
	public Optional<Student> findByStudentRollNumber(String rollNumber) {
		Optional<Student> opt = Optional.empty();
		try (Connection c = connectionUtil.getConnection();) {

			String sql = "SELECT * FROM student WHERE roll_number=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Student p = mapRowToObject(rs);
				p.setRollNumber(rollNumber);

				opt = Optional.of(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opt;
	}

	private Student mapRowToObject(ResultSet rs) throws SQLException {
		Student p = new Student();
		p.setWhichSemester(rs.getByte(2));
		p.setRollNumber(rs.getString(3));
		return p;
	}


	@Override
	public boolean updateSemester(byte semester, int id) {
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "UPDATE student SET which_semester=? WHERE student_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setByte(1, semester);
			s.setInt(2, id);
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByStudentId(int id) {
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM student WHERE student_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, id);
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
