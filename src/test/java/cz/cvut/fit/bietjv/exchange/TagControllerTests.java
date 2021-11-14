package cz.cvut.fit.bietjv.exchange;

import com.google.gson.Gson;
import cz.cvut.fit.bietjv.exchange.business.TagService;
import cz.cvut.fit.bietjv.exchange.persistence.dtos.TagDto;
import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;
import cz.cvut.fit.bietjv.exchange.presentation.TagController;
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
public class TagControllerTests {
	@Autowired
	private TestRestTemplate template;

	@MockBean
	private TagService tagService;

	@InjectMocks
	private TagController tagController;

	List<Tag> tags = new ArrayList<>();
	Gson gson = new Gson();

	@BeforeEach
	public void setUp() throws Exception {
        tags.add(new Tag(1, "Science"));
		tags.add(new Tag(2, "Language"));
        given(this.tagService.index()).willReturn(tags);
	}

	@Test
	public void indexTest() {
        ResponseEntity<String> response = template.getForEntity("/api/tags", String.class);
        Tag[] result = gson.fromJson(response.getBody(), Tag[].class);
		assertThat(result[0].getName()).isEqualTo("Science");
        assertThat(result[1].getName()).isEqualTo("Language");
	}

    @Test
    public void addTest() {
	    TagDto data = new TagDto("Science");
        template.postForEntity("/api/tags", data, Tag.class);
        ArgumentCaptor<TagDto> argument = ArgumentCaptor.forClass(TagDto.class);
        verify(tagService).add(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Science");
    }

    @Test
    public void updateTest() {
        TagDto data = new TagDto("Science");
        template.put("/api/tags/1", data);
        ArgumentCaptor<TagDto> argument = ArgumentCaptor.forClass(TagDto.class);
        verify(tagService).update(eq(1), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Science");
    }

    @Test
    public void deleteTest() {
        template.delete("/api/tags/1");
        verify(tagService).destroy(eq(1));
    }
}