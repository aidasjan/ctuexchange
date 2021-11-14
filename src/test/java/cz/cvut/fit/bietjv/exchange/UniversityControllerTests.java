package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.business.UniversityService;
import cz.cvut.fit.bietjv.exchange.business.UniversityService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.CourseRecommendationDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
import cz.cvut.fit.bietjv.exchange.presentation.UniversityController;
import cz.cvut.fit.bietjv.exchange.presentation.UniversityController;
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
public class UniversityControllerTests {
	@Autowired
	private TestRestTemplate template;

	@MockBean
	private UniversityService universityService;

	@InjectMocks
	private UniversityController universityController;

	List<University> universities = new ArrayList<>();
	Gson gson = new Gson();

	@BeforeEach
	public void setUp() throws Exception {
        universities.add(new University(1, "CTU", "Prague"));
		universities.add(new University(2, "KTU", "Kaunas"));
        given(this.universityService.index()).willReturn(universities);
	}

	@Test
	public void indexTest() {
        ResponseEntity<String> response = template.getForEntity("/api/universities", String.class);
        University[] result = gson.fromJson(response.getBody(), University[].class);
		assertThat(result[0].getName()).isEqualTo("CTU");
        assertThat(result[1].getName()).isEqualTo("KTU");
	}

    @Test
    public void addTest() {
	    UniversityDto data = new UniversityDto("University", "City");
        template.postForEntity("/api/universities", data, University.class);
        ArgumentCaptor<UniversityDto> argument = ArgumentCaptor.forClass(UniversityDto.class);
        verify(universityService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("University");
    }

    @Test
    public void updateTest() {
        UniversityDto data = new UniversityDto("University", "City");
        template.put("/api/universities/1", data);
        ArgumentCaptor<UniversityDto> argument = ArgumentCaptor.forClass(UniversityDto.class);
        verify(universityService).update(eq(1), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("University");
    }

    @Test
    public void deleteTest() {
        template.delete("/api/universities/1");
        verify(universityService).destroy(eq(1));
    }

    @Test
    public void universitiesCreditsTest() {
        template.getForEntity("/api/universities/credits", null, University.class);
        verify(universityService).getUniversityCredits();
    }
}