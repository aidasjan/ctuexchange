package cz.cvut.fit.bietjv.exchange;

import cz.cvut.fit.bietjv.exchange.business.CourseService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.CourseRepository;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.TagRepository;
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
public class CourseServiceTests {

    @MockBean
    private CourseRepository courseRepository;

	@SpyBean
	private CourseService courseService;

	@MockBean
	private TagRepository tagRepository;

	@MockBean
	private UniversityRepository universityRepository;

	List<Course> courses = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		University u1 = new University(1, "CTU", "Prague");

		Course c1 = new Course(1, "MA", "Math", 6, u1);
		Course c2 = new Course(2, "IT", "IT", 6, u1);

		Tag t1 = new Tag(1, "Science");

		courses.add(c1);
		courses.add(c2);

		given(this.courseRepository.findAll()).willReturn(courses);
		given(this.courseRepository.findById(1)).willReturn(java.util.Optional.of(c1));
		given(this.courseRepository.findById(2)).willReturn(java.util.Optional.of(c2));
		given(this.courseRepository.existsById(1)).willReturn(true);
		given(this.courseRepository.existsById(2)).willReturn(true);
		given(this.universityRepository.findById(1)).willReturn(java.util.Optional.of(u1));
		given(this.tagRepository.findById(1)).willReturn(java.util.Optional.of(t1));
	}

	@Test
	public void indexTest() {
		List<Course> result = courseService.index();
		assertThat(result).usingRecursiveComparison().isEqualTo(courses);
	}

    @Test
    public void addTest() {
	    CourseDto dto = new CourseDto("Physics", "PH", 6, 1);
        Course result = courseService.add(dto);
		ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Physics");
        assertThat(result.getName()).isEqualTo("Physics");
    }

	@Test
	public void updateTest() {
		CourseDto dto = new CourseDto("Physics", "PH", 6, 1);
		Course result = courseService.update(2, dto);
		ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
		verify(courseRepository).save(argument.capture());
		assertThat(argument.getValue().getName()).isEqualTo("Physics");
		assertThat(result.getName()).isEqualTo("Physics");
	}

	@Test
	public void addCourseTagTest() {
		boolean result = courseService.addCourseTag(1, 1);
		ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
		verify(courseRepository).save(argument.capture());
		assertThat(argument.getValue().getTags().iterator().next().getName()).isEqualTo("Science");
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void removeCourseTagTest() {
		boolean result = courseService.removeCourseTag(2, 1);
		ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
		verify(courseRepository).save(argument.capture());
		assertThat(argument.getValue().getTags().iterator().hasNext()).isEqualTo(false);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void deleteTest() {
		boolean result = courseService.destroy(1);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		verify(courseRepository).deleteById(argument.capture());
		assertThat(argument.getValue()).isEqualTo(1);
		assertThat(result).isEqualTo(true);
	}

}