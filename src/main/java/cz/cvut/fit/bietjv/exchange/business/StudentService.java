package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.StudentDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService extends AbstractCrudService<Student, StudentDto, StudentRepository> {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student add(StudentDto studentDto) {
        Optional<University> university = universityRepository.findById(studentDto.getUniversityId());
        if (university.isPresent()) {
            Student student = studentDto.map();
            student.setUniversity(university.get());
            repository.save(student);
            return student;
        }
        return null;
    }

    @Override
    public Student update(int id, StudentDto studentDto) {
        Optional<Student> student = repository.findById(id);
        Optional<University> university = universityRepository.findById(studentDto.getUniversityId());
        if (student.isPresent() && university.isPresent()) {
            Student newStudent = studentDto.map();
            newStudent.setId(id);
            newStudent.setUniversity(university.get());
            repository.save(newStudent);
            return newStudent;
        }
        return null;
    }

    public boolean addStudentCourse(int studentId, int courseId) {
        Optional<Student> student = repository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            Student savedStudent = student.get();
            savedStudent.addCourse(course.get());
            repository.save(savedStudent);
            return true;
        }
        return false;
    }

    public boolean removeStudentCourse(int studentId, int courseId) {
        Optional<Student> student = repository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            Student savedStudent = student.get();
            savedStudent.removeCourse(course.get());
            repository.save(savedStudent);
            return true;
        }
        return false;
    }
}
