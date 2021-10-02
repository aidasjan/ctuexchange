package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService extends AbstractCrudService<Course, CourseDto, CourseRepository> {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public Course add(CourseDto courseDto) {
        Optional<University> university = universityRepository.findById(courseDto.getUniversityId());
        if (university.isPresent()) {
            Course course = courseDto.map();
            course.setUniversity(university.get());
            repository.save(course);
            return course;
        }
        return null;
    }

    @Override
    public Course update(int id, CourseDto courseDto) {
        Optional<Course> course = repository.findById(id);
        Optional<University> university = universityRepository.findById(courseDto.getUniversityId());
        if (course.isPresent() && university.isPresent()) {
            Course savedCourse = course.get();
            savedCourse.setName(courseDto.getName());
            savedCourse.setCode(courseDto.getCode());
            savedCourse.setUniversity(university.get());
            repository.save(savedCourse);
            return savedCourse;
        }
        return null;
    }
}
