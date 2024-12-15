package com.spring_boot.hibernate.Hibernate_One_To_Many.dao;

import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Course;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Instructor;
import com.spring_boot.hibernate.Hibernate_One_To_Many.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
        // Nullify the association between courses and the instructor
        for(Course course:instructor.getCourses()){
            course.setInstructor(null);
            entityManager.persist(course);
        }
        //Deleting instructor from DB
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void addCourse(int id,Course course) {
        Instructor instructor=findInstructorById(id);

        instructor.addCourse(course);

        //Due to Cascade.persist----->if any changes happen in Instructor automatically changes will be persisted.

//        entityManager.persist(course);
//        entityManager.merge(instructor);
    }

    @Override
    public Instructor findInstructorWithFetchCourseById(int id) {
        TypedQuery<Instructor> query =entityManager.createQuery("select i from instructor i join fetch i.courses where i.id=:data",Instructor.class);

        //setting parameter.
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void addCourseAndReviews(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void addReviewWithCourseId(int id, Review review) {

    }

    @Override
    public Course findCourseWithFetchReviewById(int id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {

    }
}
