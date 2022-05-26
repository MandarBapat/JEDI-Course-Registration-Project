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


public class CourseDetailsJdbcImpl implements CourseDetailsDAO {

	ConnectionUtil connectionUtil;

	public CourseDetailsJdbcImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	@Override
	public Optional<CourseDetails> findByCourseId(String courseId) {
		Optional<CourseDetails> opt = Optional.empty();
		try (Connection c = connectionUtil.getConnection();) {

			String sql = "SELECT * FROM course_details WHERE course_id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, courseId);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				CourseDetails p = mapRowToObject(rs);

				opt = Optional.of(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opt;
	}

	private CourseDetails mapRowToObject(ResultSet rs) throws SQLException {
		CourseDetails p = new CourseDetails();
		p.setCourseId(rs.getString(1));
		p.setCourseName(rs.getString(2));
		p.setPrerequisites(rs.getString(3));
		p.setProfessorId(rs.getString(4));
		p.setNumCredits(rs.getByte(5));
		p.setOfferedIn(rs.getString(6));
		return p;
	}

	@Override
	public List<CourseDetails> findAll(int semester) {
		List<CourseDetails> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT * FROM course_details";
			PreparedStatement s = c.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String temp = rs.getString(6);
				if(temp.contains(""+semester)) {
					CourseDetails p = mapRowToObject(rs);
					all.add(p);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	@Override
	public List<CourseDetails> findCoursesByProfessor(String professorId) {
		List<CourseDetails> all = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "select * from course_details where professor_id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, professorId);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				CourseDetails p = mapRowToObject(rs);
				all.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

}
