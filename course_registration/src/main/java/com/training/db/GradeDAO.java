package com.training.db;

import java.util.List;

import com.training.api.Grade;

public interface GradeDAO {
	public List<Grade> findGradesByRollNumber(String rollNumber);
	public boolean updateGrade(String rollNumber, String courseId, String grade);
	public boolean deleteByRollNumberAndCourseId(String rollNumber, String courseId);
	public boolean deleteByRollNumber(String rollNumber);
}
