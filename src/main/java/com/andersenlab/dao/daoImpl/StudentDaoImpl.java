package com.andersenlab.dao.daoImpl;

import com.andersenlab.dao.StudentDao;
import com.andersenlab.entities.Student;
import com.andersenlab.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;

/**
 * DAO for table student in database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class StudentDaoImpl implements StudentDao {

    /**
     * Method for adding some student to database
     *
     * @param student
     * @throws SQLException
     */
    public void addStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There was an exception in the method addStudent");
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method for removing some student from database
     *
     * @param student
     * @throws SQLException
     */
    public void deleteStudent(Student student) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There was an exception in the method deleteStudent");
        } finally {
            if (session != null) session.close();
        }

    }

    /**
     * Method for getting some student from database by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Student getStudent(long id) throws SQLException {
        Student result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Student) session.get(Student.class, id);
        } catch (Exception e) {
            System.out.println("There was an exception in the method getStudent");
        } finally {
            if (session != null) session.close();
        }
        return result;
    }
}
