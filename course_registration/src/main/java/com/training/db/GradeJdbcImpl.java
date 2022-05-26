package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.training.api.CourseDetails;
import com.training.api.Grade;

public class GradeJdbcImpl implements GradeDAO{

	ConnectionUtil connectionUtil;

	public GradeJdbcImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}


	@Override
	public List<Grade> findGradesByRollNumber(String rollNumber){
		List<Grade> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {

			String sql = "SELECT * FROM grade WHERE roll_number=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Grade p = mapRowToObject(rs);
				p.setRollNumber(rollNumber);

				all.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	private Grade mapRowToObject(ResultSet rs) throws SQLException {
		Grade p = new Grade();
		p.setRollNumber(rs.getString(1));
		p.setCourseID(rs.getString(2));
		p.setGradeObtained(rs.getString(3));
		return p;
	}
	
	@Override
	public boolean updateGrade(String rollNumber, String courseId, String grade) {
			try (Connection c = connectionUtil.getConnection();) {
			String sql = "UPDATE grade SET grade_obtained=? WHERE roll_number=? AND course_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, grade);
			s.setString(2, rollNumber);
			s.setString(3, courseId);
			System.out.println("Grade: " + grade);
			System.out.println("Roll Number: " + rollNumber);
			System.out.println("Course ID: " + courseId);
			
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteByRollNumberAndCourseId(String rollNumber, String courseId){
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM grade WHERE roll_number=? AND course_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			s.setString(2, courseId);
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteByRollNumber(String rollNumber){
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM grade WHERE roll_number=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, rollNumber);
			int rowCount = s.executeUpdate();
			return (rowCount == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
