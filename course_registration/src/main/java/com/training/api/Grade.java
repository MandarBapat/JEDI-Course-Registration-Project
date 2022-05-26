package com.training.api;

public class Grade {
	private String rollNumber;
	private String courseID;
	private String gradeObtained;
	private String courseName;
	private byte numCredits;
	
	public Grade() {}

	public Grade(String rollNumber, String courseID,
	String gradeObtained, String courseName,
	byte numCredits) {
		this.rollNumber = rollNumber;
		this.courseID = courseID;
		this.gradeObtained = gradeObtained;
		this.courseName = courseName;
		this.numCredits = numCredits;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getGradeObtained() {
		return gradeObtained;
	}

	public void setGradeObtained(String gradeObtained) {
		this.gradeObtained = gradeObtained;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public byte getNumCredits() {
		return numCredits;
	}

	public void setNumCredits(byte numCredits) {
		this.numCredits = numCredits;
	}

}
