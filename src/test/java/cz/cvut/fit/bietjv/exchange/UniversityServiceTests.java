package cz.cvut.fit.bietjv.exchange;

import cz.cvut.fit.bietjv.exchange.business.UniversityService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityCreditsDto;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.UniversityDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;
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
public class UniversityServiceTests {

    @MockBean
    private UniversityRepository universityRepository;

	@SpyBean
	private UniversityService universityService;

	List<University> universities = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		University u1 = new University(1, "CTU", "Prague");
		University u2 = new University(2, "KTU", "Kaunas");

		universities.add(u1);
		universities.add(u2);

		given(this.universityRepository.findAll()).willReturn(universities);
		given(this.universityRepository.findById(1)).willReturn(java.util.Optional.of(u1));
		given(this.universityRepository.findById(2)).willReturn(java.util.Optional.of(u2));
		given(this.universityRepository.existsById(1)).willReturn(true);
		given(this.universityRepository.existsById(2)).willReturn(true);
		given(this.universityRepository.getUniversityCredits()).willReturn(List.of(new UniversityCreditsDto("CTU", 50)));
	}

	@Test
	public void indexTest() {
		List<University> result = universityService.index();
		assertThat(result).usingRecursiveComparison().isEqualTo(universities);
	}

    @Test
    public void addTest() {
	    UniversityDto dto = new UniversityDto("VU", "Vilnius");
        University result = universityService.add(dto);
		ArgumentCaptor<University> argument = ArgumentCaptor.forClass(University.class);
        verify(universityRepository).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("VU");
        assertThat(result.getName()).isEqualTo("VU");
    }

	@Test
	public void updateTest() {
		UniversityDto dto = new UniversityDto("VU", "Vilnius");
		University result = universityService.update(2, dto);
		ArgumentCaptor<University> argument = ArgumentCaptor.forClass(University.class);
		verify(universityRepository).save(argument.capture());
		assertThat(argument.getValue().getName()).isEqualTo("VU");
		assertThat(result.getName()).isEqualTo("VU");
	}

	@Test
	public void deleteTest() {
		boolean result = universityService.destroy(1);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		verify(universityRepository).deleteById(argument.capture());
		assertThat(argument.getValue()).isEqualTo(1);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void getUniversityCreditsTest() {
		List<UniversityCreditsDto> result = universityService.getUniversityCredits();
		verify(universityRepository).getUniversityCredits();
		assertThat(result.get(0).getCredits()).isEqualTo(50);
	}

}