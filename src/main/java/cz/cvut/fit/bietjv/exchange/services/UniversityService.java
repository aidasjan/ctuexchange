package cz.cvut.fit.bietjv.exchange.services;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository repository;

    public List<University> getAll() {
        List<University> allRecords = new ArrayList<>();
        repository.findAll().forEach(allRecords::add);
        return allRecords;
    }
}
