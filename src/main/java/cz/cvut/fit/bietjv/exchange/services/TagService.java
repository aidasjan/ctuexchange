package cz.cvut.fit.bietjv.exchange.services;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.TagRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> getAll() {
        List<Tag> allRecords = new ArrayList<>();
        repository.findAll().forEach(allRecords::add);
        return allRecords;
    }
}
