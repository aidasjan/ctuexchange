package cz.cvut.fit.bietjv.exchange.business;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityCreditsDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.TagRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService extends AbstractCrudService<Course, CourseDto, CourseRepository> {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private TagRepository tagRepository;

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
            Course newCourse = courseDto.map();
            newCourse.setId(id);
            newCourse.setUniversity(university.get());
            repository.save(newCourse);
            return newCourse;
        }
        return null;
    }

    public boolean addCourseTag(int courseId, int tagId) {
        Optional<Course> course = repository.findById(courseId);
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (course.isPresent() && tag.isPresent()) {
            Course savedCourse = course.get();
            savedCourse.addTag(tag.get());
            repository.save(savedCourse);
            return true;
        }
        return false;
    }

    public boolean removeCourseTag(int courseId, int tagId) {
        Optional<Course> course = repository.findById(courseId);
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (course.isPresent() && tag.isPresent()) {
            Course savedCourse = course.get();
            savedCourse.removeTag(tag.get());
            repository.save(savedCourse);
            return true;
        }
        return false;
    }
}
