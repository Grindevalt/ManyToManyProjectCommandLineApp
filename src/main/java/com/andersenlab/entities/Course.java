package com.andersenlab.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity "course" for database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<Student>();

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method for getting list of students in some course
     *
     * @return Set of students
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * Method for setting list of students in some course
     *
     * @param students
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    /**
     * Getter for parameter Id
     *
     * @return
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Setter for parameter Id
     *
     * @param courseId
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter for parameter courseName
     *
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Setter for parameter courseName
     *
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method equals for Course class
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseId != null ? !courseId.equals(course.courseId) : course.courseId != null) return false;
        if (courseName != null ? !courseName.equals(course.courseName) : course.courseName != null) return false;
        return students != null ? students.equals(course.students) : course.students == null;
    }

    /**
     * Method hashCode for Course class
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }

    /**
     * Method toString for Course class
     *
     * @return
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", students=" + students +
                '}';
    }
}
