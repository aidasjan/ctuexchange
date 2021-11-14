package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseRecommendationDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.StudentDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.presentation.StudentController;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.business.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ExchangeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {
	@Autowired
	private TestRestTemplate template;

	@MockBean
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	List<Student> students = new ArrayList<>();
	Gson gson = new Gson();

	@BeforeEach
	public void setUp() throws Exception {
        University u1 = new University(1, "CTU", "Prague");
        students.add(new Student(1, "John", "Smith", u1));
		students.add(new Student(2, "James", "Williams", u1));
		Course c1 = new Course(1, "C1", "Test Course", 6, u1);
		CourseRecommendationDto cr = new CourseRecommendationDto(c1, 3);
		List<CourseRecommendationDto> courseRecommendationDtos = List.of(cr);
        given(this.studentService.index()).willReturn(students);
        given(this.studentService.recommendCourses(1)).willReturn(courseRecommendationDtos);
	}

	@Test
	public void indexTest() {
        ResponseEntity<String> response = template.getForEntity("/api/students", String.class);
        Student[] result = gson.fromJson(response.getBody(), Student[].class);
		assertThat(result[0].getName()).isEqualTo("John");
        assertThat(result[1].getName()).isEqualTo("James");
	}

    @Test
    public void addTest() {
	    StudentDto data = new StudentDto("Test", "Student", 1);
        template.postForEntity("/api/students", data, Student.class);
        ArgumentCaptor<StudentDto> argument = ArgumentCaptor.forClass(StudentDto.class);
        verify(studentService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Test");
    }

    @Test
    public void updateTest() {
        StudentDto data = new StudentDto("Test", "Student", 1);
        template.put("/api/students/1", data);
        ArgumentCaptor<StudentDto> argument = ArgumentCaptor.forClass(StudentDto.class);
        verify(studentService).update(eq(1), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Test");
    }

    @Test
    public void deleteTest() {
        template.delete("/api/students/1");
        verify(studentService).destroy(eq(1));
    }

    @Test
    public void addCourseTest() {
        template.postForEntity("/api/students/1/courses/2", null, Student.class);
        verify(studentService).addStudentCourse(eq(1), eq(2));
    }

    @Test
    public void removeCourseTest() {
        template.delete("/api/students/1/courses/2");
        verify(studentService).removeStudentCourse(eq(1), eq(2));
    }

    @Test
    public void getCourseRecommendationsTest() {
        ResponseEntity<String> response = template.getForEntity("/api/students/1/courseRecommendations", String.class);
        CourseRecommendationDto[] result = gson.fromJson(response.getBody(), CourseRecommendationDto[].class);
        assertThat(result[0].getCourse().getName()).isEqualTo("Test Course");
        assertThat(result[0].getCompatibilityScore()).isEqualTo(3);
    }
}