package exitProject.domain.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter /* Setter는 nono -> BUILDER 이용해서 setting */
@NoArgsConstructor
@Entity
public class Posts {
    /* Entity Class - Class 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭 */

    /* PK는 왠만해선 Long id로, 필요할 경우 unique Key 추가 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
