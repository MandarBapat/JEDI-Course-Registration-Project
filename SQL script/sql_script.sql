DROP DATABASE course_registration_course;

CREATE DATABASE course_registration_course;

USE course_registration_course;


CREATE TABLE user(
	user_id SERIAL,
	PRIMARY KEY(user_id),
	username VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
	email VARCHAR(255),
	role VARCHAR(50),
    department VARCHAR(50)
);

INSERT INTO user (username, password, name, email, role, department) VALUES 
("hackerman1" , "test1234", "Mandar Bapat", "hello@yahoo.com", "student", "CSE"), 
("hackerman2", "test12345", "Hemanth Chitti", "bye@gmail.com", "student", "CSE"), 
("hackerman3", "test123456", "Manoj Kumar", "who@outlook.com", "professor", "EE");


CREATE TABLE professor(
    professor_id VARCHAR(50),
    UNIQUE (professor_id),
    user_id SERIAL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

INSERT INTO professor (professor_id, user_id) VALUES ("CSE2019B123", 1), ("EE2020C456", 2);

CREATE TABLE course_details(
  course_id VARCHAR(10),
	PRIMARY KEY (course_id),
  course_name VARCHAR(50),
	prerequisites VARCHAR(255),
	professor_id VARCHAR(50),
	FOREIGN KEY (professor_id) REFERENCES professor(professor_id),
  num_credits TINYINT,
  offered_in VARCHAR(50)
);

INSERT INTO course_details (course_id, course_name, prerequisites, professor_id, num_credits, offered_in) 
VALUES
("ESC101A", "Introduction to Electronics", "MTH101A, MTH102A", "CSE2019B123", 14, "1,4,5,6"),
("MTH101A", "Real Analysis", "", "EE2020C456", 11, "1");

-- insert into course_details (coursename, department, prerequisites, id_professor) values ("DiscreteMath", "Math", "Algebra", 1), ("QuantumMechanics", "Physics", "12th basics", 2);

CREATE TABLE student(
    student_id SERIAL,
    PRIMARY KEY(student_id),
    FOREIGN KEY (student_id) REFERENCES user(user_id),
    which_semester TINYINT,
    roll_number VARCHAR(50),
    UNIQUE (roll_number)
);

INSERT INTO student (which_semester, roll_number) VALUES
(6, "IITK190473"),
(5, "47510IITB");



CREATE TABLE registration(
    registration_id SERIAL,
    roll_number VARCHAR(50),
    FOREIGN KEY (roll_number) REFERENCES student(roll_number),
    course_id VARCHAR(50),
    FOREIGN KEY (course_id) REFERENCES course_details(course_id)
);
CREATE UNIQUE INDEX ix_uq ON registration (roll_number, course_id);

INSERT INTO registration (roll_number, course_id) VALUES
("IITK190473", "ESC101A"),
("47510IITB", "MTH101A");

CREATE TABLE grade(
    roll_number VARCHAR(50),
    PRIMARY KEY (roll_number),
    course_id VARCHAR(10),
    FOREIGN KEY (course_id) REFERENCES course_details(course_id),
    grade_obtained VARCHAR(10)
);

INSERT INTO grade (roll_number, course_id, grade_obtained) VALUES
("IITK190473", "ESC101A", "A"),
("47510IITB", "MTH101A", "A"),
("47511IITB", "MTH101A", "D");
