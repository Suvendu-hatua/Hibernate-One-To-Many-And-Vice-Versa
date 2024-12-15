package com.spring_boot.hibernate.Hibernate_One_To_Many;

import com.spring_boot.hibernate.Hibernate_One_To_Many.dao.AppDao;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Course;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Instructor;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.InstructorDetails;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateOneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao dao){
		return runner->{
//			createInstructorWithCoursesAndInstructorDetails(dao);
//			addCourse(dao);
//			findCourseByCourseId(dao);
//			deleteCourseByCourseId(dao);
//			deleteInstructorByInstructorId(dao);
			addCourseAndReviews(dao);
		};
	}

	private void addCourseAndReviews(AppDao dao) {
		//Create an instance of Course.
		Course course=new Course("Go Programming","Complete Beginner friendly course.");
		//Create multiple instances of reviews.
		Review review1=new Review("Golang Course is good","best course for absolute for beginners.");
		Review review2=new Review("Good Understanding","best course for absolute for beginners.");

		//Adding reviews to Course.
		course.addReview(review1);
		course.addReview(review2);
		//Saving course instance to dB.
		System.out.println("Saving course with reviews");
		dao.addCourseAndReviews(course);
		System.out.println("Done!!");

	}

	private void deleteInstructorByInstructorId(AppDao dao) {
		int id=2;
		dao.deleteInstructorById(id);
		System.out.println("Done!!");
	}

	private void deleteCourseByCourseId(AppDao dao) {

		int id=4;
		dao.deleteCourseById(id);
		System.out.println("Done!!");
	}

	private void findCourseByCourseId(AppDao dao) {
		int courseId=2;
		Course course=dao.findCourseById(2);

		System.out.println(course);
	}

	private void addCourse(AppDao dao){
		//Creating an instance of Course.
		Course course1=new Course("Basics of Java Programming","Complete beginner friendly course you should avail for.");

		System.out.println("Saving Course into DB.....");
		dao.addCourse(1,course1);
//		dao.addCourse(course1);
		System.out.println("Done!!!");
	}

	private void createInstructorWithCoursesAndInstructorDetails(AppDao dao) {

		//Creating an instance of Instructor
		Instructor instructor=new Instructor("Suvendu","Hatua","suvendu.hatua2024@gmail.com");
		//Creating an instance of InstructorDetails
		InstructorDetails instructorDetails=new InstructorDetails("http://www.youtube.com/suvendu-hatua","Cricket and Coding");
		//Creating some instances of Courses.
		Course course1=new Course("C++ Course","Complete beginner friendly course you should avail for.");
		Course course2=new Course("Angular Course","Beginner friendly course");
		Course course3=new Course("Data Structures","Complete Data Structure and Algorithms Course with example and well explained in Java along with chapter wise Quiz.");

		//Adding
		instructor.setInstructorDetails(instructorDetails);
		instructor.addCourse(course1);
		instructor.addCourse(course2);
		instructor.addCourse(course3);

		//saving into DB
		System.out.println("Saving Instructor into Database.");
		dao.addInstructorWithDetailsAndCourse(instructor);
		System.out.println("Done!!!");
	}
}
