package com.andersenlab.project;

import com.andersenlab.dao.CourseDao;
import com.andersenlab.dao.StudentDao;
import com.andersenlab.entities.Course;
import com.andersenlab.entities.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class for creating commandLine application
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class Menu {
    static void startMenu(StudentDao studentDao, CourseDao courseDao, BufferedReader reader) throws IOException, SQLException {
        System.out.println("Hello, choose the base from which you want to work");
        System.out.println("1 - Students database");
        System.out.println("2 - Courses database");
        System.out.println("3 - Exit");
        int a = Integer.parseInt(reader.readLine());
        switch (a) {
            case 1:
                studentMenu(studentDao, reader, courseDao);
                break;
            case 2:
                courseMenu(courseDao, reader, studentDao);
                break;
            case 3:
                System.exit(1);
                break;
            default:
                System.out.println("You pushed wrong button, please think little bit and try again");
                startMenu(studentDao, courseDao, reader);
                break;
        }
    }

    static void courseMenu(CourseDao courseDao, BufferedReader reader, StudentDao studentDao) throws IOException, SQLException {
        System.out.println("Welcome to Courses database");
        System.out.println("Select what you want to do with the database");
        System.out.println("1 - Show list of Courses");
        System.out.println("2 - Add course to database");
        System.out.println("3 - Remove course from database");
        System.out.println("4 - Find course by id");
        System.out.println("5 - Back");
        int c = Integer.parseInt(reader.readLine());

        switch (c) {
            case 1:
                showCourseTable(courseDao, reader, studentDao);
                break;
            case 2:
                addCourse(courseDao, reader, studentDao);
                break;

            case 3:
                removeCourse(courseDao, reader, studentDao);
                break;
            case 4:
                findCourse(courseDao, reader, studentDao);
                break;
            case 5:
                startMenu(studentDao, courseDao, reader);
            default:
                System.out.println("You pushed wrong button, please think a little and try again");
                courseMenu(courseDao, reader, studentDao);
                break;

        }
    }

    private static void showCourseTable(CourseDao courseDao, BufferedReader reader, StudentDao studentDao) throws SQLException, IOException {
        List<Course> courses = courseDao.getCouses();
        System.out.println("~~~~~~~~~TABLE COURSES~~~~~~~~~~");
        System.out.println("       id       ||     name     ");
        System.out.println("--------------------------------");
        for (Course course : courses) {
            System.out.println("       " + course.getCourseId() + "           " + course.getCourseName());
        }
        System.out.println();
        courseMenu(courseDao, reader, studentDao);
    }

    private static void addCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao) throws IOException, SQLException {
        System.out.println("Please enter course name");
        String name = reader.readLine();
        Course course = new Course(name);
        courseDao.addCourse(course);
        System.out.println("Course is successfully added " + course.toString());
        courseMenu(courseDao, reader, studentDao);
    }

    private static void removeCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao) throws IOException, SQLException {
        System.out.println("Please enter the  ID of the course you want to delete");
        try {
            long id = Long.parseLong(reader.readLine());
            Course course1 = courseDao.getCourse(id);
            courseDao.deleteCourse(course1);
            System.out.println("Course is successfully removed");
            courseMenu(courseDao, reader, studentDao);
        } catch (NumberFormatException e) {
            System.out.println("You pushed wrong button, please think a little and try again");
            removeCourse(courseDao, reader, studentDao);
        }
    }

    private static void findCourse(CourseDao courseDao, BufferedReader reader, StudentDao studentDao) throws IOException, SQLException {
        System.out.println("Please enter the  ID of the course you want to find");
        try {
            long id1 = Long.parseLong(reader.readLine());
            Course course2 = courseDao.getCourse(id1);
            System.out.println(course2.toString());
            courseMenu(courseDao, reader, studentDao);
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("You trying to enter string format value, please enter a number");
            findCourse(courseDao, reader, studentDao);
        }

    }

    static void studentMenu(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws IOException, SQLException {
        System.out.println("Welcome to Students database");
        System.out.println("Select what you want to do with the database");
        System.out.println("1 - Show list of students");
        System.out.println("2 - Add student to database");
        System.out.println("3 - Add course to student");
        System.out.println("4 - Remove student from database");
        System.out.println("5 - Find student by id");
        System.out.println("6 - Back");
        int b = Integer.parseInt(reader.readLine());
        switch (b) {
            case 1:
                showStudentTable(studentDao, reader, courseDao);
                break;
            case 2:
                addStudent(studentDao, reader, courseDao);
                break;
            case 3:
                addCourseToStudent(studentDao, reader, courseDao);
                break;

            case 4:
                removeStudent(studentDao, reader, courseDao);
                break;
            case 5:
                findStudent(studentDao, reader, courseDao);
                break;
            case 6:
                startMenu(studentDao, courseDao, reader);
            default:
                System.out.println("You pushed wrong button, please think a little and try again");
                studentMenu(studentDao, reader, courseDao);
                break;

        }
    }

    private static void addStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws IOException, SQLException {
        System.out.println("Please enter student name");
        String name = reader.readLine();
        Student student = new Student(name);
        studentDao.addStudent(student);
        System.out.println("Student is successfully added to database" + student.toString());
        System.out.println();
        studentMenu(studentDao, reader, courseDao);
    }

    private static void showStudentTable(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws SQLException, IOException {
        List<Student> students = studentDao.getStudents();
        System.out.println("~~~~~~~~TABLE STUDENTS~~~~~~~~~~");
        System.out.println("       id       ||     name     ");
        System.out.println("--------------------------------");
        for (Student student : students) {
            System.out.println("       " + student.getStudentId() + "             " + student.getStudentName());
        }
        System.out.println();
        studentMenu(studentDao, reader, courseDao);
    }

    private static void addCourseToStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws IOException, SQLException {
        try {
            System.out.println("Please enter the student ID that you want to add the course to");
            long id3 = Long.parseLong(reader.readLine());
            Student student1 = new Student(studentDao.getStudent(id3).getStudentName());
            System.out.println("Please enter the course that you want to add to the student");
            String nameAddedCourse = reader.readLine();
            Course course = new Course(nameAddedCourse);
            student1.getCourses().add(course);
            studentDao.addStudent(student1);
            studentDao.deleteStudent(studentDao.getStudent(id3));
            System.out.println("Course is successfully added to student");
            System.out.println();
            studentMenu(studentDao, reader, courseDao);
        } catch (NumberFormatException e) {
            System.out.println("You trying to enter string format value to ID field, please enter a number");
            addCourseToStudent(studentDao, reader, courseDao);
        }

    }

    private static void findStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws IOException, SQLException {
        System.out.println("Please enter the student ID of the student you want to find");
        try {
            long id1 = Long.parseLong(reader.readLine());
            Student student2 = studentDao.getStudent(id1);
            System.out.println(student2.toString());
            studentMenu(studentDao, reader, courseDao);
        } catch (NumberFormatException e) {
            System.out.println("You trying to enter string format value, please enter a number");
            findStudent(studentDao, reader, courseDao);
        }

    }

    private static void removeStudent(StudentDao studentDao, BufferedReader reader, CourseDao courseDao) throws IOException, SQLException {
        System.out.println("Please enter the ID of the student you want to delete");
        try {
            long id = Long.parseLong(reader.readLine());
            Student student123 = studentDao.getStudent(id);
            studentDao.deleteStudent(student123);
            System.out.println("Student is successfully removed");
            studentMenu(studentDao, reader, courseDao);
        } catch (NumberFormatException e) {
            System.out.println("You trying to enter string format value, please enter a number");
            removeStudent(studentDao, reader, courseDao);
        }
    }

}
