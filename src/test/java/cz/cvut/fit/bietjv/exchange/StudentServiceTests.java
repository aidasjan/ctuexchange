package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.business.AbstractCrudService;
import cz.cvut.fit.bietjv.exchange.business.StudentService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.StudentDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import cz.cvut.fit.bietjv.exchange.presentation.StudentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ExchangeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceTests {

	@MockBean
	private StudentRepository studentRepository;

    @MockBean
    private UniversityRepository universityRepository;

    @MockBean
    private CourseRepository courseRepository;

	@SpyBean
	private StudentService studentService;

	List<Student> students = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		University u1 = new University(1, "CTU", "Prague");
		Student s1 = new Student(1, "John", "Smith", u1);
		Student s2 = new Student(2, "James", "Williams", u1);
		Course c1 = new Course(1, "C1", "Test Course", 6);
		students.add(s1);
		students.add(s2);
		s2.addCourse(c1);
		given(this.studentRepository.findAll()).willReturn(students);
		given(this.universityRepository.findById(1)).willReturn(java.util.Optional.of(u1));
		given(this.studentRepository.findById(1)).willReturn(java.util.Optional.of(s1));
		given(this.studentRepository.findById(2)).willReturn(java.util.Optional.of(s2));
		given(this.studentRepository.existsById(1)).willReturn(true);
		given(this.studentRepository.existsById(2)).willReturn(true);
		given(this.courseRepository.findById(1)).willReturn(java.util.Optional.of(c1));
	}

	@Test
	public void indexTest() {
		List<Student> result = studentService.index();
		assertThat(result).usingRecursiveComparison().isEqualTo(students);
	}

    @Test
    public void addTest() {
	    StudentDto dto = new StudentDto("John", "Smith", 1);
        Student result = studentService.add(dto);
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("John");
        assertThat(result.getName()).isEqualTo("John");
    }

	@Test
	public void updateTest() {
		StudentDto dto = new StudentDto("Josh", "Williams", 1);
		Student result = studentService.update(2, dto);
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(argument.capture());
		assertThat(argument.getValue().getName()).isEqualTo("Josh");
		assertThat(result.getName()).isEqualTo("Josh");
	}

	@Test
	public void deleteTest() {
		boolean result = studentService.destroy(1);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		verify(studentRepository).deleteById(argument.capture());
		assertThat(argument.getValue()).isEqualTo(1);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void addStudentCourseTest() {
		boolean result = studentService.addStudentCourse(1, 1);
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(argument.capture());
		assertThat(argument.getValue().getCourses().iterator().next().getName()).isEqualTo("Test Course");
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void removeStudentCourseTest() {
		boolean result = studentService.removeStudentCourse(2, 1);
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(argument.capture());
		assertThat(argument.getValue().getCourses().iterator().hasNext()).isEqualTo(false);
		assertThat(result).isEqualTo(true);
	}

}