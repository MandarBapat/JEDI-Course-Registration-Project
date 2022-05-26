package com.training.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.api.CourseDetails;
import com.training.api.CourseRegistration;
import com.training.api.Student;
import com.training.db.CourseRegistrationDAO;
import com.training.db.CourseRegistrationJdbcImpl;
import com.training.db.StudentDAO;
import com.training.db.CourseDetailsDAO;
import com.training.db.CourseDetailsJdbcImpl;

//import com.training.api.Product;
//import com.training.db.ProductDAO;

@Path("/registrations")
public class RegistrationResource {
	
	public int index;
	public static List<CourseRegistration> registrationList;
	public HashMap<Integer, Integer> map;
	
	CourseRegistrationDAO dao;
	CourseDetailsDAO courseDao;
	StudentDAO studentDao;
	
	
	public RegistrationResource(CourseRegistrationDAO dao, CourseDetailsDAO courseDAO, StudentDAO studentDAO) {
		index = 0;
		RegistrationResource.registrationList = new ArrayList<CourseRegistration>();
		map = new HashMap<>();
		this.dao = dao;
		this.courseDao = courseDAO;
		this.studentDao = studentDAO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeRegistration(List<CourseRegistration> registrations) {
		
		ArrayList<CourseRegistration> result = new ArrayList<CourseRegistration>();
		for(CourseRegistration toBeAdded: registrations) {
			int id = dao.insertRegistration(toBeAdded);
			System.out.println("Id is " + id);
			if(id != 0) {
				result.add(toBeAdded);
			}
		}
		
		return Response.created(URI.create("/registrations/")).entity(result).build();
		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{regId}")
	public Response dropRegistration(@PathParam("regId") int regId) {
		
		boolean deleted = dao.deleteById(regId);
		if(deleted) {
			return Response.noContent().build();
		}else {
			throw new WebApplicationException(404);
		}
	}
	
	@POST
	@Path("/addDrop/start")
	public Response startAddDrop() { 
		
		CourseRegistrationJdbcImpl.addOrDrop = true;
		
		return Response.noContent().build();
		
	}
	
	@POST
	@Path("/addDrop/stop")
	public Response stopAddDrop() {   
		
		CourseRegistrationJdbcImpl.addOrDrop = false;
		
		return Response.noContent().build();
		
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/professor/{professorId}/course-{courseid}")
	public List<Student> getStudentsByCourse(@PathParam("professorId") String professorId, @PathParam("courseid") String courseid) {
		
		// if the course is handled by professor then only allow them, otherwise return unauthorized exception. 
		
		List<CourseDetails> profCourseList= courseDao.findCoursesByProfessor(professorId);
		CourseDetails reqCourse = courseDao.findByCourseId(courseid).get();
		boolean check = false;
		
		for(CourseDetails course: profCourseList) {
			if(course.getCourseId().equals(reqCourse.getCourseId())) {
				check = true;
				break;
			}
		}
		
		if(!check) {
			throw new WebApplicationException(401);
		}
		
		List<CourseRegistration> requiredCourses = dao.findByCourseId(courseid);
		
		List<Student> students = new ArrayList<Student>();
		for(CourseRegistration reg:requiredCourses) {
//			System.out.println("Number of courses is " + requiredCourses.size());
//			System.out.println("Course Id is " + reg.getCourseId() + " Roll Number is " + reg.getRollNumber());
//			System.out.println("Student Roll Number is " + studentDao.findByStudentRollNumber(reg.getRollNumber()).get().getRollNumber());
				
			students.add(studentDao.findByStudentRollNumber(reg.getRollNumber()).get());
		}
		
		return students;
	}
	
}
