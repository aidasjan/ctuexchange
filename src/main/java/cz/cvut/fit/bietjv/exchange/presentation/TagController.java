package cz.cvut.fit.bietjv.exchange.presentation;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.TagDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.business.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/api/tags")
    public List<Tag> index() {
        return tagService.index();
    }

    @PostMapping("/api/tags")
    public ResponseEntity<Tag> add(@RequestBody TagDto tagDto) {
        Tag record = tagService.add(tagDto);
        if (record == null) {
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Tag>(record, HttpStatus.OK);
    }

    @PutMapping("/api/tags/{id}")
    public ResponseEntity<Tag> update(@PathVariable(value="id") int id, @RequestBody TagDto tagDto) {
        Tag record = tagService.update(id, tagDto);
        if (record == null) {
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Tag>(record, HttpStatus.OK);
    }

    @DeleteMapping("/api/tags/{id}")
    public ResponseEntity<Tag> destroy(@PathVariable(value="id") int id) {
        boolean idDestroyed = tagService.destroy(id);
        if (!idDestroyed) {
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Tag>(HttpStatus.OK);
    }
}
