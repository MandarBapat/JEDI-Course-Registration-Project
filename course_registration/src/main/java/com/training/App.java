package com.training;

import com.training.db.ConnectionUtil;
import com.training.db.CourseDetailsJdbcImpl;
import com.training.db.CourseRegistrationJdbcImpl;
import com.training.db.GradeJdbcImpl;
import com.training.db.StudentJdbcImpl;
//import com.training.db.ProductDAOJdbcImpl;
import com.training.resources.CourseResource;
import com.training.resources.GradeResource;
//import com.training.resources.ProductResource;
import com.training.resources.RegistrationResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration>{

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		
		ConnectionUtil connectionUtil = new ConnectionUtil(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());

		CourseDetailsJdbcImpl courseDao = new CourseDetailsJdbcImpl(connectionUtil);
		CourseResource courseResource = new CourseResource(courseDao);
		
		CourseRegistrationJdbcImpl regDao = new CourseRegistrationJdbcImpl(connectionUtil);
		
		StudentJdbcImpl studentDao = new StudentJdbcImpl(connectionUtil);
		RegistrationResource registerResource = new RegistrationResource(regDao, courseDao, studentDao);
		
		GradeJdbcImpl gradeDao = new GradeJdbcImpl(connectionUtil);
		GradeResource gradeResource = new GradeResource(gradeDao);
		
		environment.jersey().register(registerResource);
		environment.jersey().register(courseResource);
		environment.jersey().register(gradeResource);
		
	}

}
