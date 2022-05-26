package com.training.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.api.CourseDetails;
import com.training.api.CourseRegistration;
import com.training.api.Grade;
import com.training.db.CourseDetailsDAO;
import com.training.db.GradeDAO;



@Path("/grades")
public class GradeResource {

	

	GradeDAO dao;
	
	public GradeResource(GradeDAO dao) {
		this.dao = dao;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{rollNumber}")
	public List<Grade> viewGradesByRollNumber(@PathParam("rollNumber") String rollNumber){
		System.out.println(rollNumber);
		return dao.findGradesByRollNumber(rollNumber);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{courseId}/{rollNumber}")
	public Response editGrade(@PathParam("courseId") String courseId, @PathParam("rollNumber") String rollNumber, String grade) {
		dao.updateGrade(rollNumber, courseId, grade);
		return Response.noContent().build();
	}
}
