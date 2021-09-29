package cz.cvut.fit.bietjv.exchange.services;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UniversityRepository universityRepository;


    public List<Course> index() {
        List<Course> allRecords = new ArrayList<>();
        courseRepository.findAll().forEach(allRecords::add);
        return allRecords;
    }

    public Course add(CourseDto courseDto) {
        Optional<University> university = universityRepository.findById(courseDto.getUniversityId());
        if (university.isPresent()) {
            Course course = courseDto.map(university.get());
            courseRepository.save(course);
            return course;
        }
        return null;
    }

    public Course update(int id, CourseDto courseDto) {
        Optional<Course> course = courseRepository.findById(id);
        Optional<University> university = universityRepository.findById(courseDto.getUniversityId());
        if (course.isPresent() && university.isPresent()) {
            Course savedCourse = course.get();
            savedCourse.setName(courseDto.getName());
            savedCourse.setCode(courseDto.getCode());
            savedCourse.setUniversity(university.get());
            courseRepository.save(savedCourse);
            return savedCourse;
        }
        return null;
    }

    public boolean destroy(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
