package cz.cvut.fit.bietjv.exchange.controllers;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/api/tags")
    public String index() {
        List<Tag> records = tagService.getAll();
        return (new Gson()).toJson(records);
    }
}
