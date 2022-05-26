package com.training.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.api.CourseDetails;
import com.training.api.CourseRegistration;
import com.training.db.CourseDetailsDAO;

//import com.training.api.Product;
//import com.training.db.ProductDAO;

@Path("/courses")
public class CourseResource {

	
	List<CourseDetails> courseList;
	CourseDetailsDAO dao;
	
	public CourseResource(CourseDetailsDAO dao) {
		this.courseList = new ArrayList<CourseDetails>();
		this.dao = dao;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{semesterid}")
	public List<CourseDetails> getAllCourses(@PathParam("semesterid") int semesterid){
		return dao.findAll(semesterid);
		//return courseList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/professor/{professorid}")
	public Response getCoursesByProfessor(@PathParam("professorid") String professorid) {
		
		return Response.ok(dao.findCoursesByProfessor(professorid)).build();

	}
}
