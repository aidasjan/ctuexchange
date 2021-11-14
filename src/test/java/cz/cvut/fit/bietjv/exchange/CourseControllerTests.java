package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.business.CourseService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.presentation.CourseController;
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
public class CourseControllerTests {
	@Autowired
	private TestRestTemplate template;

	@MockBean
	private CourseService courseService;

	@InjectMocks
	private CourseController courseController;

	List<Course> courses = new ArrayList<>();
	Gson gson = new Gson();

	@BeforeEach
	public void setUp() throws Exception {
        courses.add(new Course("MA", "Math", 6));
		courses.add(new Course("IT", "IT", 6));
        given(this.courseService.index()).willReturn(courses);
	}

	@Test
	public void indexTest() {
        ResponseEntity<String> response = template.getForEntity("/api/courses", String.class);
        Course[] result = gson.fromJson(response.getBody(), Course[].class);
		assertThat(result[0].getName()).isEqualTo("Math");
        assertThat(result[1].getName()).isEqualTo("IT");
	}

    @Test
    public void addTest() {
	    CourseDto data = new CourseDto("Physics", "PH", 6, 1);
        template.postForEntity("/api/courses", data, Course.class);
        ArgumentCaptor<CourseDto> argument = ArgumentCaptor.forClass(CourseDto.class);
        verify(courseService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Physics");
    }

    @Test
    public void updateTest() {
        CourseDto data = new CourseDto("Physics", "PH", 6, 1);
        template.put("/api/courses/1", data);
        ArgumentCaptor<CourseDto> argument = ArgumentCaptor.forClass(CourseDto.class);
        verify(courseService).update(eq(1), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Physics");
    }

    @Test
    public void addTagTest() {
        template.postForEntity("/api/courses/1/tags/2", null, Student.class);
        verify(courseService).addCourseTag(eq(1), eq(2));
    }

    @Test
    public void removeTagTest() {
        template.delete("/api/courses/1/tags/2");
        verify(courseService).removeCourseTag(eq(1), eq(2));
    }

    @Test
    public void deleteTest() {
        template.delete("/api/courses/1");
        verify(courseService).destroy(eq(1));
    }
}