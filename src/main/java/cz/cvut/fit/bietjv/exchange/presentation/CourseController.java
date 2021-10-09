package cz.cvut.fit.bietjv.exchange.presentation;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.business.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    private final Gson gson = new Gson();

    @GetMapping("/api/courses")
    public List<Course> index() {
        return courseService.index();
    }

    @PostMapping("/api/courses")
    public ResponseEntity<Course> add(@RequestBody CourseDto course) {
        Course record = courseService.add(course);
        if (record == null) {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Course>(record, HttpStatus.OK);
    }

    @PostMapping("/api/courses/{courseId}/tags/{tagId}")
    public ResponseEntity<Course> addTag(@PathVariable(value="courseId") int courseId, @PathVariable(value="tagId") int tagId) {
        boolean isAdded = courseService.addCourseTag(courseId, tagId);
        if (!isAdded) {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Course>(HttpStatus.OK);
    }

    @DeleteMapping("/api/courses/{courseId}/tags/{tagId}")
    public ResponseEntity<Course> removeTag(@PathVariable(value="courseId") int courseId, @PathVariable(value="tagId") int tagId) {
        boolean isRemoved = courseService.removeCourseTag(courseId, tagId);
        if (!isRemoved) {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Course>(HttpStatus.OK);
    }

    @PutMapping("/api/courses/{id}")
    public ResponseEntity<Course> update(@PathVariable(value="id") int id, @RequestBody CourseDto course) {
        Course record = courseService.update(id, course);
        if (record == null) {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Course>(record, HttpStatus.OK);
    }

    @DeleteMapping("/api/courses/{id}")
    public ResponseEntity<Course> destroy(@PathVariable(value="id") int id) {
        boolean idDestroyed = courseService.destroy(id);
        if (!idDestroyed) {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Course>(HttpStatus.OK);
    }
}
