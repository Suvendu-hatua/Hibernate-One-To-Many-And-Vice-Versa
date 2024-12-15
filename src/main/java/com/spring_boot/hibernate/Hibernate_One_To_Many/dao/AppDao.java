package com.spring_boot.hibernate.Hibernate_One_To_Many.dao;

import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Course;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Instructor;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Review;

public interface AppDao {

    public void addInstructorWithDetailsAndCourse(Instructor instructor);
    public Instructor findInstructorById(int id);
    public Course findCourseById(int id);
    public void deleteCourseById(int id);
    public void deleteInstructorById(int id);
    public void addCourse(int id,Course course);
    public Instructor findInstructorWithFetchCourseById(int id);

    public void addCourseAndReviews(Course course);
    public void addReviewWithCourseId(int id, Review review);
    public Course findCourseWithFetchReviewById(int id);
    public void deleteReviewById(int id);

}
