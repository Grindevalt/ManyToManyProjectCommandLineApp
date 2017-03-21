package com.andersenlab.project;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.entities.Course;
import com.andersenlab.entities.Student;
import com.andersenlab.util.Factory;

import java.sql.SQLException;


/**
 * Main class where all functionality are implemented
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class Project {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws SQLException {
        Factory factory = Factory.getInstance();
        StudentDao studentDao = factory.getStudentDao();
        CourseDao courseDao = factory.getCourseDao();

        Student valera = new Student("Valera");
        Student katya = new Student("Katya");
        Student vasya = new Student("Vasya");
        Student gosha = new Student("Gosha");


        Course history = new Course("History");
        Course geography = new Course("Geography");
        Course chemistry = new Course("Chemistry");
        Course physics = new Course("Physics");


        valera.getCourses().add(history);
        valera.getCourses().add(geography);
        valera.getCourses().add(physics);
        studentDao.addStudent(valera);

        katya.getCourses().add(chemistry);
        katya.getCourses().add(geography);
        studentDao.addStudent(katya);

        vasya.getCourses().add(physics);
        vasya.getCourses().add(history);
        studentDao.addStudent(vasya);

        gosha.getCourses().add(chemistry);
        gosha.getCourses().add(physics);
        studentDao.addStudent(gosha);

    }
}