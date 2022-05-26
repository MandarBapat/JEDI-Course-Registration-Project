package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.training.api.CourseDetails;
import com.training.api.CourseRegistration;

public class CourseRegistrationJdbcImpl implements CourseRegistrationDAO{

	ConnectionUtil connectionUtil;
	public static boolean addOrDrop;

	public CourseRegistrationJdbcImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
		addOrDrop = false;
	}

	@Override
	public int insertRegistration(CourseRegistration toBeInserted) {
		int id = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql_command = "SELECT * FROM registration WHERE course_id = ?";
	 		PreparedStatement s1 = c.prepareStatement(sql_command);
	 		s1.setString(1, toBeInserted.getCourseId());
			
	 		ResultSet rs1 = s1.executeQuery();
	 		
	 		
	 		int num_students = 0;
			while(rs1.next()) {
				num_students = num_students + 1;
			}
			
			System.out.println("Number of students in the course " + toBeInserted.getCourseId() + " is " + num_students);
			
			if(num_students >= 10 || (addOrDrop && num_students < 3)) {
				throw new SQLException();
			}
			
			
			
			String sql = "INSERT IGNORE INTO registration (roll_number, course_id) VALUES(?,?)";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setString(1, toBeInserted.getRollNumber());
			s.setString(2, toBeInserted.getCourseId());
			s.executeUpdate();
			// following code is for retrieving generated PK
			ResultSet keys = s.getGeneratedKeys();
			keys.next();
			id = keys.getInt(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public List<CourseRegistration> findByCourseId(String courseId) {
		List<CourseRegistration> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {

			String sql = "SELECT * FROM registration WHERE course_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, courseId);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				CourseRegistration p = mapRowToObject(rs);

				all.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	private CourseRegistration mapRowToObject(ResultSet rs) throws SQLException {
		CourseRegistration p = new CourseRegistration();
		
		p.setRollNumber(rs.getString(2));
		p.setCourseId(rs.getString(3));
		return p;
	}

	@Override
	public List<CourseRegistration> findByRollNumber(String rollNumber) {
		List<CourseRegistration> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT * FROM registration WHERE roll_number=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				CourseRegistration p = mapRowToObject(rs);
				all.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}


	@Override
	public boolean deleteById(int id) {
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM registration WHERE registration_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, id);
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<CourseRegistration> findCoursesByStudent(String rollNumber) {
		List<CourseRegistration> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "select course_id from registration where roll_number = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				CourseRegistration reg = mapRowToObject(rs);
				all.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

}
