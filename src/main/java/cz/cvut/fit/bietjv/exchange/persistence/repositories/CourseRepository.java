package cz.cvut.fit.bietjv.exchange.persistence.repositories;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> { }
