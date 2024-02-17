package highton.team2.dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllPostResponseDto {
    private Long id;
    private String title;
    private String preview; // 원본 내용을 127자로 자른 내용
    private String userId;
}
