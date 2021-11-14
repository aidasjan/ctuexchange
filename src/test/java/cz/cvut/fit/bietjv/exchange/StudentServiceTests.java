package cz.cvut.fit.bietjv.exchange;

import cz.cvut.fit.bietjv.exchange.business.CourseService;
import cz.cvut.fit.bietjv.exchange.business.StudentService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseRecommendationDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.StudentDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.StudentRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.UniversityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

	@MockBean
	private CourseService courseService;

	@SpyBean
	private StudentService studentService;

	List<Student> students = new ArrayList<>();
	List<Course> courses = new ArrayList<>();

	University u1;
	University u2;

	Student s1;
	Student s2;
	Student s3;

	@BeforeEach
	public void setUp() throws Exception {

		u1 = new University(1, "CTU", "Prague");
		u2 = new University(2, "KTU", "Kaunas");

		s1 = new Student(1, "John", "Smith", u1);
		s2 = new Student(2, "James", "Williams", u1);
		s3 = new Student(3, "Robert", "Smith", u1);

		Tag t1 = new Tag(1, "Technology");
		Tag t2 = new Tag(2, "Language");
		Tag t3 = new Tag(3, "Chemistry");

		Course c11 = new Course(1, "MA1", "Math", 6, u1);
		c11.addTag(t1);
		courses.add(c11);
		Course c12 = new Course(2, "IT1", "IT", 6, u1);
		c12.addTag(t1);
		courses.add(c12);
		Course c13 = new Course(3, "EN1", "English", 6, u1);
		c13.addTag(t2);
		courses.add(c13);
		Course c14 = new Course(4, "CH1", "Chemistry", 6, u1);
		c11.addTag(t3);
		courses.add(c14);
		Course c21 = new Course(5, "M1", "Math", 6, u2);
		c21.addTag(t1);
		courses.add(c21);
		Course c22 = new Course(6, "I1", "IT", 6, u2);
		c22.addTag(t1);
		courses.add(c22);
		Course c23 = new Course(7, "F1", "French", 6, u2);
		c23.addTag(t2);
		courses.add(c23);
		Course c24 = new Course(8, "P1", "Physics", 6, u2);
		c24.addTag(t1);
		courses.add(c24);

		s2.addCourse(c11);
		s3.addCourse(c11);
		s3.addCourse(c12);
		s3.addCourse(c13);
		s3.addCourse(c14);
		students.add(s1);
		students.add(s2);
		students.add(s3);
		given(this.studentRepository.findAll()).willReturn(students);
		given(this.universityRepository.findById(1)).willReturn(java.util.Optional.of(u1));
		given(this.universityRepository.findById(2)).willReturn(java.util.Optional.of(u2));
		given(this.studentRepository.findById(1)).willReturn(java.util.Optional.of(s1));
		given(this.studentRepository.findById(2)).willReturn(java.util.Optional.of(s2));
		given(this.studentRepository.findById(3)).willReturn(java.util.Optional.of(s3));
		given(this.studentRepository.existsById(1)).willReturn(true);
		given(this.studentRepository.existsById(2)).willReturn(true);
		given(this.studentRepository.existsById(3)).willReturn(true);
		given(this.courseRepository.findById(1)).willReturn(java.util.Optional.of(c11));
		given(this.courseService.index()).willReturn(courses);
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
		assertThat(argument.getValue().getCourses().iterator().next().getName()).isEqualTo("Math");
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

	@Test
	public void recommendCoursesTest() {
		List<CourseRecommendationDto> result = studentService.recommendCourses(3);
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(argument.capture());
		assertThat(argument.getValue().getId()).isEqualTo(3);
		assertThat(result.size()).isEqualTo(3);
		assertThat(result.get(0).getCourse().getCode()).isEqualTo("M1");
		assertThat(result.get(1).getCourse().getCode()).isEqualTo("I1");
		assertThat(result.get(2).getCourse().getCode()).isEqualTo("P1");
		assertThat(s3.getCourses().size()).isEqualTo(7);
	}

}