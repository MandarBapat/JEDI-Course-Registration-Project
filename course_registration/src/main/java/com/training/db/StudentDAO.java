package com.training.db;
import java.util.Optional;

import com.training.api.Student;

public interface StudentDAO {
	public Optional<Student> findByStudentId(int id);
	public boolean updateSemester(byte semester, int id);
	public boolean deleteByStudentId(int id);
	public Optional<Student> findByStudentRollNumber(String rollNumber);
}
