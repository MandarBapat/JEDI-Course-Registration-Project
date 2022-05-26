package com.training.db;

import java.util.List;
import java.util.Optional;

import com.training.api.CourseRegistration;

public interface CourseRegistrationDAO {

	public List<CourseRegistration> findByCourseId(String courseId);
	public List<CourseRegistration> findByRollNumber(String rollNumber);
	public int insertRegistration(CourseRegistration o1);
	public boolean deleteById(int id);

	public List<CourseRegistration> findCoursesByStudent(String rollNumber);
}

