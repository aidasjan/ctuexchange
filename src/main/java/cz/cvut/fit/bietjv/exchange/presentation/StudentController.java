package cz.cvut.fit.bietjv.exchange.presentation;

import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseRecommendationDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.StudentDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.business.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/api/students")
    public List<Student> index() {
        return studentService.index();
    }

    @PostMapping("/api/students")
    public ResponseEntity<Student> add(@RequestBody StudentDto student) {
        Student record = studentService.add(student);
        if (record == null) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(record, HttpStatus.OK);
    }

    @PostMapping("/api/students/{id}/courseRecommendations")
    public ResponseEntity<List<CourseRecommendationDto>> getCourseRecommendations(@PathVariable(value="id") int id) {
        List<CourseRecommendationDto> recommendations = studentService.recommendCourses(id);
        if (recommendations == null) {
            return new ResponseEntity<List<CourseRecommendationDto>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<CourseRecommendationDto>>(recommendations, HttpStatus.OK);
    }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> update(@PathVariable(value="id") int id, @RequestBody StudentDto student) {
        Student record = studentService.update(id, student);
        if (record == null) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(record, HttpStatus.OK);
    }

    @PostMapping("/api/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> addCourse(@PathVariable(value="studentId") int studentId, @PathVariable(value="courseId") int courseId) {
        boolean isAdded = studentService.addStudentCourse(studentId, courseId);
        if (!isAdded) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @DeleteMapping("/api/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> removeCourse(@PathVariable(value="studentId") int studentId, @PathVariable(value="courseId") int courseId) {
        boolean isRemoved = studentService.removeStudentCourse(studentId, courseId);
        if (!isRemoved) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Student> destroy(@PathVariable(value="id") int id) {
        boolean idDestroyed = studentService.destroy(id);
        if (!idDestroyed) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Student>(HttpStatus.OK);
    }
}
