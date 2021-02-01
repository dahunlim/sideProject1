package exitProject.web.dto;

import exitProject.domain.dao.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // Entity Class와 Controller 에서 사용할 Dto는 분리해서~
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title)
                            .content(content)
                            .author(author)
                            .build();
    }

}
