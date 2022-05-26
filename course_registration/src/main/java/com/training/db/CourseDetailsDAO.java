package com.training.db;

import java.util.List;
import java.util.Optional;

import com.training.api.CourseDetails;

public interface CourseDetailsDAO {

	public Optional<CourseDetails> findByCourseId(String courseId);
	public List<CourseDetails> findAll(int semester);
	public List<CourseDetails> findCoursesByProfessor(String professorId);
}
