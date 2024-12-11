package com.spring_boot.hibernate.Hibernate_One_To_Many.dao;

import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Course;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDaoImpl implements AppDao{
    private EntityManager entityManager;

    @Autowired
    public  AppDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void addInstructorWithDetailsAndCourse(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {

        return entityManager.find(Instructor.class,id);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course=findCourseById(id);
        //removing relationship with instructor.
        course.getInstructor().setCourses(null);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor=findInstructorById(id);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void addCourse(Course course) {
        entityManager.persist(course);
    }
}
