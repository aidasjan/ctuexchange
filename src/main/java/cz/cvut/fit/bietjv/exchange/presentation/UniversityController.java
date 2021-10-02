package cz.cvut.fit.bietjv.exchange.presentation;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.business.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;
    
    @GetMapping("/api/universities")
    public List<University> index() {
        return universityService.index();
    }

    @PostMapping("/api/universities")
    public ResponseEntity<University> add(@RequestBody UniversityDto universityDto) {
        University record = universityService.add(universityDto);
        if (record == null) {
            return new ResponseEntity<University>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<University>(record, HttpStatus.OK);
    }

    @PutMapping("/api/universities/{id}")
    public ResponseEntity<University> update(@PathVariable(value="id") int id, @RequestBody UniversityDto universityDto) {
        University record = universityService.update(id, universityDto);
        if (record == null) {
            return new ResponseEntity<University>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<University>(record, HttpStatus.OK);
    }

    @DeleteMapping("/api/universities/{id}")
    public ResponseEntity<University> destroy(@PathVariable(value="id") int id) {
        boolean idDestroyed = universityService.destroy(id);
        if (!idDestroyed) {
            return new ResponseEntity<University>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<University>(HttpStatus.OK);
    }
}
