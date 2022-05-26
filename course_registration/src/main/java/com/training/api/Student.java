package com.training.api;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Student extends User {
	
	private byte whichSemester;
	private String rollNumber;
	private int studentId;
	
	public byte getWhichSemester() {
		return whichSemester;
	}
	public void setWhichSemester(byte whichSemester) {
		this.whichSemester = whichSemester;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
