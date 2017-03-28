package com.andersenlab.project;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.util.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


/**
 * Main class where all functionality are implemented
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class Project {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws SQLException, IOException {
        Factory factory = Factory.getInstance();
        StudentDao studentDao = factory.getStudentDao();
        CourseDao courseDao = factory.getCourseDao();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Menu.startMenu(studentDao, courseDao, reader);
        }finally {
            reader.close();
        }
    }
}