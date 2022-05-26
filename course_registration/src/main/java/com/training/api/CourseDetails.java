package com.training.api;

public class CourseDetails {
	private String courseName;
	private String courseId;
	private String offeredIn;
	private byte numCredits;
	private String professorId;
	private String prerequisites;
	private int numStudentsRegistered;
	
	public CourseDetails() {}

	public CourseDetails(String courseName, String courseId, String offeredIn,
	byte numCredits, String prerequisites, String professorId,
	int numStudentsRegistered) {
//		super();
		this.courseName = courseName;
		this.courseId = courseId;
		this.offeredIn = offeredIn;
		this.numCredits = numCredits;
		this.prerequisites = prerequisites;
		this.professorId = professorId;
		this.numStudentsRegistered = numStudentsRegistered;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOfferedIn() {
		return offeredIn;
	}

	public void setOfferedIn(String offeredIn) {
		this.offeredIn = offeredIn;
	}

	public byte getNumCredits() {
		return numCredits;
	}

	public void setNumCredits(byte numCredits) {
		this.numCredits = numCredits;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getNumStudentsRegistered() {
		return numStudentsRegistered;
	}

	public void setNumStudentsRegistered(int numStudentsRegistered) {
		this.numStudentsRegistered = numStudentsRegistered;
	}

	@Override
	public boolean equals(Object o) {
		CourseDetails d1 = (CourseDetails)o;
		return courseId == d1.courseId;
	}
	
	@Override
	public int hashCode() {
		return courseId.length();
	}
	
}
