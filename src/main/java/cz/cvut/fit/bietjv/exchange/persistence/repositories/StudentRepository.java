package cz.cvut.fit.bietjv.exchange.persistence.repositories;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> { }
