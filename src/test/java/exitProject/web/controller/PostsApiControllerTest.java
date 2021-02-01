package exitProject.web.controller;

import exitProject.domain.dao.Posts;
import exitProject.domain.repository.PostsRepository;
import exitProject.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//JPA 기능까지 테스트할때에는 SpringBootTest 사용
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_게시글이_등록된다() throws Exception {
        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title)
                                                                    .content(content)
                                                                    .author(author)
                                                                    .build();

        String url = "http://localhost:" + port + "/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle().equals(title));
        assertThat(all.get(0).getContent().equals(content));
        assertThat(all.get(0).getAuthor().equals(author));
    }
}
