package cz.cvut.fit.bietjv.exchange.controllers;

import cz.cvut.fit.bietjv.exchange.persistence.models.Student;
import cz.cvut.fit.bietjv.exchange.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/api/students")
    public String index() {
        List<Student> students = studentService.getAllStudents();
        Gson gson = new Gson();
        return gson.toJson(students);
    }
}
