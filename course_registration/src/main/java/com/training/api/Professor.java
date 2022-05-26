package com.training.api;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Professor extends User{
	
	private String professorId;

	public String getProfessorId() {
		return professorId;
	}
}

