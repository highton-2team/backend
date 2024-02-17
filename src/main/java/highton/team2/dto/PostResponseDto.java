package highton.team2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private String userId;
    private Long postId;
    private String title;
    private String content;
}
