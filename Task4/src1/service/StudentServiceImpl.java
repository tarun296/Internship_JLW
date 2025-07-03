package service;

import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import exception.StudentNotFoundException;
import model.Student;
import util.LoggerUtil;

public class StudentServiceImpl implements StudentService {
	
	private StudentDAO studentDAO = new StudentDAOImpl();
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void addStudent(Student student) {
        if (student.getName() == null || student.getName().isEmpty()) {
            logger.warning("Invalid student name.");
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        studentDAO.addStudent(student);
        logger.info("Student added: " + student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public void updateStudent(Student student) {
        Optional<Student> existing = Optional.ofNullable(studentDAO.getStudentById(student.getId()));
        if (existing.isPresent()) {
            studentDAO.updateStudent(student);
            logger.info("Student updated: " + student);
        } else {
            logger.warning("Student not found for update.");
            throw new StudentNotFoundException("Student with ID " + student.getId() + " not found.");
        }
    }

    @Override
    public void deleteStudent(int id) {
        Optional<Student> existing = Optional.ofNullable(studentDAO.getStudentById(id));
        if (existing.isPresent()) {
            studentDAO.deleteStudent(id);
            logger.info("Student deleted: ID " + id);
        } else {
            logger.warning("Student not found for delete.");
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return Optional.ofNullable(studentDAO.getStudentById(id));
    }


}
