package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.controllers.StudentController;
import cz.cvut.fit.bietjv.exchange.persistence.models.Student;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import cz.cvut.fit.bietjv.exchange.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = ExchangeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

	@Autowired
	private TestRestTemplate template;

	@MockBean
	private StudentRepository studentRepository;

	@SpyBean
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	List<Student> students = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		students.add(new Student(1, "John", "Smith", 1));
		students.add(new Student(2, "Test", "User", 1));
		given(this.studentRepository.findAll()).willReturn(students);
	}

	@Test
	public void getAllStudents() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/api/students", String.class);
		Gson gson = new Gson();
		assertThat(response.getBody()).isEqualTo(gson.toJson(students));
	}
}