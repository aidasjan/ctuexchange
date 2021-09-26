package cz.cvut.fit.bietjv.exchange.controllers;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/api/universities")
    public String index() {
        List<University> records = universityService.getAll();
        return (new Gson()).toJson(records);
    }
}
