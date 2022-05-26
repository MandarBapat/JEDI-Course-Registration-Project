package com.training.api;

public class CourseRegistration {
	public String rollNumber;
	public String courseId;

	public CourseRegistration() {
		
	}

	public CourseRegistration(String rollNumber, String courseId) {
		super();
		this.rollNumber = rollNumber;
		this.courseId = courseId;
	}
	

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	@Override
	public boolean equals(Object o) {
		if(!(o instanceof CourseRegistration)) {
			return false;
		}
		return this.rollNumber == ((CourseRegistration)o).rollNumber && this.courseId == ((CourseRegistration)o).courseId;
	}
	
//	//@Override
//	public int hashcode() {
//		return Integer.valueOf(studentId).hashCode();
//	}
	
	
}
