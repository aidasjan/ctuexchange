package cz.cvut.fit.bietjv.exchange.services;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getAll() {
        List<Course> allRecords = new ArrayList<>();
        repository.findAll().forEach(allRecords::add);
        return allRecords;
    }
}
