package cz.cvut.fit.bietjv.exchange.presentation;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
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
    private final Gson gson = new Gson();

    @GetMapping("/api/students")
    public String index() {
        List<Student> records = studentService.getAll();
        return gson.toJson(records);
    }
}
