package cz.cvut.fit.bietjv.exchange.services;

import cz.cvut.fit.bietjv.exchange.persistence.models.Student;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        repository.findAll().forEach(allStudents::add);
        return allStudents;
    }
}
