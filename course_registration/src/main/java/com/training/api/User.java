package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class User{
	
	protected String username;
	protected String password;
	protected String role;
	protected String name;
	protected String email;
	protected String department;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

