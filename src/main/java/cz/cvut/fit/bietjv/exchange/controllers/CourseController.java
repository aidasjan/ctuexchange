package cz.cvut.fit.bietjv.exchange.controllers;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/api/courses")
    public String index() {
        List<Course> records = courseService.getAll();
        return (new Gson()).toJson(records);
    }
}
