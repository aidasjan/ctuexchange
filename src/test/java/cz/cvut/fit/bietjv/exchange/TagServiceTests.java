package cz.cvut.fit.bietjv.exchange;

import cz.cvut.fit.bietjv.exchange.business.TagService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.TagDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.persistence.repositories.TagRepository;
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
public class TagServiceTests {

    @MockBean
    private TagRepository tagRepository;

	@SpyBean
	private TagService tagService;

	List<Tag> tags = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		Tag u1 = new Tag("Science");
		Tag u2 = new Tag("Language");

		tags.add(u1);
		tags.add(u2);

		given(this.tagRepository.findAll()).willReturn(tags);
		given(this.tagRepository.findById(1)).willReturn(java.util.Optional.of(u1));
		given(this.tagRepository.findById(2)).willReturn(java.util.Optional.of(u2));
		given(this.tagRepository.existsById(1)).willReturn(true);
		given(this.tagRepository.existsById(2)).willReturn(true);
	}

	@Test
	public void indexTest() {
		List<Tag> result = tagService.index();
		assertThat(result).usingRecursiveComparison().isEqualTo(tags);
	}

    @Test
    public void addTest() {
	    TagDto dto = new TagDto("IT");
        Tag result = tagService.add(dto);
		ArgumentCaptor<Tag> argument = ArgumentCaptor.forClass(Tag.class);
        verify(tagRepository).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("IT");
        assertThat(result.getName()).isEqualTo("IT");
    }

	@Test
	public void updateTest() {
		TagDto dto = new TagDto("IT");
		Tag result = tagService.update(2, dto);
		ArgumentCaptor<Tag> argument = ArgumentCaptor.forClass(Tag.class);
		verify(tagRepository).save(argument.capture());
		assertThat(argument.getValue().getName()).isEqualTo("IT");
		assertThat(result.getName()).isEqualTo("IT");
	}

	@Test
	public void deleteTest() {
		boolean result = tagService.destroy(1);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		verify(tagRepository).deleteById(argument.capture());
		assertThat(argument.getValue()).isEqualTo(1);
		assertThat(result).isEqualTo(true);
	}

}